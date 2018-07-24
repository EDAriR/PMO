package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.PasswordListJDBC;
import com.syntrontech.pmo.JDBC.auth.RoleJDBC;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.auth.UserJDBC;
import com.syntrontech.pmo.JDBC.cip.*;
import com.syntrontech.pmo.JDBC.measurement.*;
import com.syntrontech.pmo.JDBC.pmo.PmoResultJDBC;
import com.syntrontech.pmo.JDBC.pmo.PmoUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.*;
import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.*;
import com.syntrontech.pmo.measurement.common.BloodPressureCaseStatus;
import com.syntrontech.pmo.measurement.common.GlucoseType;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.*;
import com.syntrontech.pmo.pmo.MeasurementPMOType;
import com.syntrontech.pmo.pmo.PmoResult;
import com.syntrontech.pmo.pmo.PmoUser;
import com.syntrontech.pmo.pmo.PmoStatus;
import com.syntrontech.pmo.syncare1.model.*;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;
import com.syntrontech.pmo.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Sync {

    private static Logger logger = LoggerFactory.getLogger(Sync.class);

    public static void main(String[] args) {

        // TODO 台東 預設 系統管理員 TENANT ==> TTSHB
        // 測試流程 ==> 新建TTSHB  TENANT 給 default user 權限後測試
        Sync sync = new Sync();

//        new SyncUnit().syncLocationToUnit();
//        new SyncDevice().syncDevice();
        sync.syncSystemUserToUserAndSubject();
    }

    public void syncSystemUserToUserAndSubject() {

        // 取出所有 未同步 且 角色為使用者的使用者
        List<String> userIds = new UserRoleJDBC().getAllUserRoles();

        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();
        UserJDBC userJDBC = new UserJDBC();
        SubjectJDBC subjectJDBC = new SubjectJDBC();
        EmergencyContactJDBC emergencyContactJDBC = new EmergencyContactJDBC();

        UserValueRecordJDBC userValueRecordJDBC = new UserValueRecordJDBC();

        PmoUserJDBC pmoUserJDBC = new PmoUserJDBC();

        PasswordListJDBC passwordListJDBC = new PasswordListJDBC();

        // 取出新的role 只要user 的  DEFAULT_TENANT_ADMIN  DEFAULT_USER  TTSHB
        Role newrole = new RoleJDBC().getRoleById("DEFAULT_USER");

        // 取出所有未同步量測紀錄數值 <body_value_record_id, UserValueRecordMappings>
        Map<Integer, List<UserValueRecordMapping>> userValueRecordMap = new UserValueRecordMappingJDBC()
                .getAllUserValueRecordMapping();

        List<SystemUser> users = userIds.stream()   // 找出未同步systemuser
                .map(id -> systemUserJDBC.getSystemUserById(id))
                .collect(Collectors.toList());

        for (SystemUser su : users) {

            logger.info("sync system user :" + su);
            if (su.getUserAccount() == null) {
                continue;
            }

            // 新增 user
            User user = userJDBC.insertUser(syncSystemUserToUser(su, newrole));
            System.out.println("user in new db : " + user);
            // 密碼
            passwordListJDBC.insertPassword(user, su.getUserPassword());
            // 新增 subject
            Subject subject = subjectJDBC.insertSubject(syncSystemUserToSubject(su));
            // 新增 緊急聯絡人 Alert = Y 為接受緊急通知
            if (su.getAlert() == YN.Y && su.getAlertNotifierName() != null)
                emergencyContactJDBC.insertEmergencyContact(syncSystemUserToEmergencyContact(su));

            // 取出該使用者所有血壓
            List<UserValueRecord> userValueRecords = userValueRecordJDBC.getOneBUserValueRecord(su.getUserId());

            // 同步至 新的血壓心跳 異常追蹤 異常追蹤log
            syncMeasurementBloodPressureHeartBeat(userValueRecords, userValueRecordMap, subject, su, userValueRecordJDBC);

            // 同步新的血糖
            syncBloodGlucose(su, userValueRecordMap, subject, userValueRecordJDBC);

            // BodyInfo 更新至新的身高體重
            BodyInfoJDBC bodyInfoJDBC = new BodyInfoJDBC();
            List<UserValueRecord> userBodyInfoValueRecords = userValueRecordJDBC.getOneUserAValueRecord(su.getUserId());
            userBodyInfoValueRecords.forEach(record -> {
                System.out.println("sync bodyInfo ");
                System.out.println("record " + record);
                System.out.println("subject " + subject);
                BodyInfo bodyInfo = bodyInfoJDBC.insert(turnValueRecordToBodyInfo(record, subject, userValueRecordMap));
                // update mapping / records
                updateUserValueRecordMapping(userValueRecordMap, record.getBodyValueRecordId());
                userValueRecordJDBC.updateUserValueRecord(record.getBodyValueRecordId());
            });

            // Biochemistry
            syncBiochemistry(su, subject, userValueRecordMap, userValueRecordJDBC);


            // PMO USER RESULT
            pmoUserJDBC.insert(turnSystemUserToPmoUser(su));
            // update systemUser sync status
            systemUserJDBC.updateSystemUser(su.getUserId());
            // ↓  不需要
            // ALBUM_NAME	varchar(45) NULL
            // ALBUM_TYPE	int(11) unsigned [0]	使用者mapping的相本為 -> 1;picasa, 2:無名 .....
            // ADVERTISMENT_STATUS 好康報報對於使用者的狀態_1: 此使用者尚未收到"新廣告通知了"(包含修改),2:此使用者已經收到"新廣告通知了
            new UserRoleJDBC().updateUserRoles(su.getUserId());

        }
    }

    private void syncBiochemistry(SystemUser su, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, UserValueRecordJDBC userValueRecordJDBC) {

        BiochemistryJDBC biochemistryJDBC = new BiochemistryJDBC();
        List<UserValueRecord> userBiochemistryJRecords = userValueRecordJDBC.getOneUserOtherValueRecord(su.getUserId());
        userBiochemistryJRecords.forEach(record -> {

            List<UserValueRecordMapping> values = userValueRecordMap.get(record.getBodyValueRecordId());

            UserValueRecordMapping value = values.get(0);

            Biochemistry biochemistry = new Biochemistry();

            biochemistry.setValue(value.getRecordValue());
            biochemistry.setGroupId(biochemistryJDBC.getGroupId());

            biochemistry = setMapping(biochemistry, value);

            // recordtime, latitude, longitude
            biochemistry.setRecordTime(record.getRecordDate());
            biochemistry.setLatitude("0");
            biochemistry.setLongitude("0");

            // status, createtime, createby, tenant_id, device_mac_address
            // private MeasurementStatusType status;
            biochemistry.setStatus(MeasurementStatusType.EXISTED);
            biochemistry.setCreateTime(record.getUpdateDate());
            biochemistry.setCreateBy("TTSB");
            biochemistry.setTenantId("TTSB");

            // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
            biochemistry = setBiochemistrySubjectInfo(biochemistry, subject, record);

            // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
            biochemistry = setBiochemistryUnitInfo(biochemistry, subject, record);

            Biochemistry newBiochemistry = biochemistryJDBC.insert(biochemistry);
            // TODO update mapping
            userValueRecordJDBC.updateUserValueRecord(record.getBodyValueRecordId());
    });
}

    private Biochemistry setBiochemistryUnitInfo(Biochemistry biochemistry, Subject subject, UserValueRecord record) {

        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(subject.getUnitId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
            biochemistry.setUnitId(record.getLocationId());
            biochemistry.setUnitName(record.getLocationName());
        }
        biochemistry.setUnitId(unit.getId());
        biochemistry.setUnitName(unit.getName());
        biochemistry.setParentUnitId(unit.getParentId());
        biochemistry.setParentUnitName(unit.getParentName());

        return biochemistry;

    }

    private Biochemistry setBiochemistrySubjectInfo(Biochemistry biochemistry, Subject subject, UserValueRecord record) {

        biochemistry.setSubjectSeq(subject.getSequence());
        biochemistry.setSubjectId(subject.getId());
        biochemistry.setSubjectName(subject.getName());
        biochemistry.setSubjectGender(subject.getGender());
        biochemistry.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), record.getRecordDate()));
        biochemistry.setSubjectUserId(subject.getUserId());
        biochemistry.setSubjectUserName(subject.getName());

        return biochemistry;
    }

    private Biochemistry setMapping(Biochemistry biochemistry, UserValueRecordMapping value) {

        // HbA1C 新 1 舊 13
        // Triglyceride 新 2 舊 15
        // Total_Cholesterol 新 3 舊 14
        // HDL 新 4 舊 16
        // LDL 新 5 舊 17
        // GOT 新 6 舊 18
        // GPT 新 7 舊 19
        // Creatinine/RF 新 8 舊 20
        int mappingTypeId = value.getMapping().getTypeId();
        long mappingsSeq = 0L;

        BiochemistryMappingsProject pro = null;
        switch (mappingTypeId) {
            case 13:
                pro = BiochemistryMappingsProject.HbA1C;
                mappingsSeq = 1L;
                break;
            case 15:
                pro = BiochemistryMappingsProject.Triglyceride;
                mappingsSeq = 2L;
                break;
            case 14:
                mappingsSeq = 3L;
                pro = BiochemistryMappingsProject.Total_Cholesterol;
                break;
            case 16:
                mappingsSeq = 4L;
                pro = BiochemistryMappingsProject.HDL_Cholesterol;
                break;
            case 17:
                mappingsSeq = 5L;
                pro = BiochemistryMappingsProject.LDL_Cholesterol;
                break;
            case 18:
                mappingsSeq = 6L;
                pro = BiochemistryMappingsProject.GOT;
                break;
            case 19:
                mappingsSeq = 7L;
                pro = BiochemistryMappingsProject.GPT;
                break;
            case 20:
                mappingsSeq = 8L;
                pro = BiochemistryMappingsProject.Creatinine;
                break;
        }
        biochemistry.setMappingsSeq(mappingsSeq);
        biochemistry.setMappingsProject(pro);

        return biochemistry;
    }

    private Biochemistry turnValueRecordToBiochemistry(UserValueRecord record, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap) {

        List<UserValueRecordMapping> values = userValueRecordMap.get(record.getBodyValueRecordId());
        if (values.size() == 0)
            return null;

        UserValueRecordMapping value = values.get(0);


        BiochemistryJDBC biochemistryJDBC = new BiochemistryJDBC();

        Biochemistry biochemistry = new Biochemistry();

        biochemistry.setValue(value.getRecordValue());
        biochemistry.setGroupId(biochemistryJDBC.getGroupId());

        // HbA1C 新 1 舊 13
        // Triglyceride 新 2 舊 15
        // Total_Cholesterol 新 3 舊 14
        // HDL 新 4 舊 16
        // LDL 新 5 舊 17
        // GOT 新 6 舊 18
        // GPT 新 7 舊 19
        // Creatinine/RF 新 8 舊 20
        int mappingTypeId = value.getMapping().getTypeId();
        long mappingsSeq = 0L;

        BiochemistryMappingsProject pro = null;
        switch (mappingTypeId) {
            case 13:
                pro = BiochemistryMappingsProject.HbA1C;
                mappingsSeq = 1L;
                break;
            case 15:
                pro = BiochemistryMappingsProject.Triglyceride;
                mappingsSeq = 2L;
                break;
            case 14:
                mappingsSeq = 3L;
                pro = BiochemistryMappingsProject.Total_Cholesterol;
                break;
            case 16:
                mappingsSeq = 4L;
                pro = BiochemistryMappingsProject.HDL_Cholesterol;
                break;
            case 17:
                mappingsSeq = 5L;
                pro = BiochemistryMappingsProject.LDL_Cholesterol;
                break;
            case 18:
                mappingsSeq = 6L;
                pro = BiochemistryMappingsProject.GOT;
                break;
            case 19:
                mappingsSeq = 7L;
                pro = BiochemistryMappingsProject.GPT;
                break;
            case 20:
                mappingsSeq = 8L;
                pro = BiochemistryMappingsProject.Creatinine;
                break;
        }
        biochemistry.setMappingsSeq(mappingsSeq);
        biochemistry.setMappingsProject(pro);

        // recordtime, latitude, longitude
        biochemistry.setRecordTime(record.getRecordDate());
        biochemistry.setLatitude("0");
        biochemistry.setLongitude("0");

        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        biochemistry.setStatus(MeasurementStatusType.EXISTED);
        biochemistry.setCreateTime(record.getUpdateDate());
        biochemistry.setCreateBy("TTSB");
        biochemistry.setTenantId("TTSB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        biochemistry.setSubjectSeq(subject.getSequence());
        biochemistry.setSubjectId(subject.getId());
        biochemistry.setSubjectName(subject.getName());
        biochemistry.setSubjectGender(subject.getGender());
        biochemistry.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), record.getRecordDate()));
        biochemistry.setSubjectUserId(subject.getUserId());
        biochemistry.setSubjectUserName(subject.getName());


        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(subject.getUnitId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
            biochemistry.setUnitId(record.getLocationId());
            biochemistry.setUnitName(record.getLocationName());
        }
        biochemistry.setUnitId(unit.getId());
        biochemistry.setUnitName(unit.getName());
        biochemistry.setUnitId(unit.getId());
        biochemistry.setUnitName(unit.getName());
        biochemistry.setParentUnitId(unit.getParentId());
        biochemistry.setParentUnitName(unit.getParentName());

        return biochemistry;

    }

    private BodyInfo turnValueRecordToBodyInfo(UserValueRecord record, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap) {

        List<UserValueRecordMapping> values = userValueRecordMap.get(record.getBodyValueRecordId());
        if (values.size() == 0)
            return null;

        Map<Integer, List<UserValueRecordMapping>> value = values.stream().collect(Collectors.groupingBy(v -> v.getMapping().getTypeId()));

        BodyInfo bodyInfo = new BodyInfo();

        // 3身高 4體重 5 BMI
        bodyInfo.setHeight(getRecordValue(value, 3));
        bodyInfo.setWeight(getRecordValue(value, 4));

        Double bmi = null;
        try {
            bmi = getRecordValue(value, 5);
        } catch (NumberFormatException e) {

        }
        bodyInfo.setBmi(bmi);

        // recordtime, latitude, longitude
        bodyInfo.setRecordTime(record.getRecordDate());
        bodyInfo.setLatitude("0");
        bodyInfo.setLongitude("0");


        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        bodyInfo.setStatus(MeasurementStatusType.EXISTED);
        bodyInfo.setCreateTime(record.getUpdateDate());
        bodyInfo.setCreateBy("TTSHB");
        bodyInfo.setTenantId("TTSHB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        bodyInfo.setSubjectSeq(subject.getSequence());
        bodyInfo.setSubjectId(subject.getId());
        bodyInfo.setSubjectName(subject.getName());
        bodyInfo.setSubjectGender(subject.getGender());
        bodyInfo.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), record.getRecordDate()));
        bodyInfo.setSubjectUserId(subject.getUserId());
        bodyInfo.setSubjectUserName(subject.getName());


        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(subject.getUnitId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
            bodyInfo.setUnitId(record.getLocationId());
            bodyInfo.setUnitName(record.getLocationName());
        }
        bodyInfo.setUnitId(unit.getId());
        bodyInfo.setUnitName(unit.getName());
        bodyInfo.setParentUnitId(unit.getParentId());
        bodyInfo.setParentUnitName(unit.getParentName());

        return bodyInfo;
    }

    private Double getRecordValue(Map<Integer, List<UserValueRecordMapping>> value, int type) {

        List<UserValueRecordMapping> record = value.get(type);

        try {
            if (record != null || record.size() > 0) {
                String v = record.get(0).getRecordValue();
                if (v != null)
                    return Double.valueOf(v);
                else
                    return null;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private void syncBloodGlucose(SystemUser su, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, Subject subject, UserValueRecordJDBC userValueRecordJDBC) {

        // sync 136 BG 取出使用者所有血糖
        BloodGlucoseJDBC bloodGlucoseJDBC = new BloodGlucoseJDBC();
        PmoResultJDBC pmoResultJDBC = new PmoResultJDBC();

        List<UserValueRecord> userBGValueRecords = userValueRecordJDBC.getOneBGUserValueRecord(su.getUserId());
        userBGValueRecords.forEach(bg -> {
            System.out.println("sync BloodGlucose");
            System.out.println("BloodGlucose " + bg);
            System.out.println("subject " + subject);
            BloodGlucose bloodGlucose = bloodGlucoseJDBC.insert(turnValueRecordToBloodGlucose(bg, subject, userValueRecordMap));
            userValueRecordJDBC.updateUserValueRecord(bg.getBodyValueRecordId());
            updateUserValueRecordMapping(userValueRecordMap, bg.getBodyValueRecordId());

            // PMO_result
            System.out.println(bloodGlucose);
            pmoResultJDBC.insert(turnOldRecordsToPmoResult(bg, bloodGlucose.getSubjectId(), bloodGlucose.getSequence(), MeasurementPMOType.BloodGlucose));

        });
    }

    private BloodGlucose turnValueRecordToBloodGlucose(UserValueRecord bg, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap) {

        List<UserValueRecordMapping> values = userValueRecordMap.get(bg.getBodyValueRecordId());
        GlucoseType type = GlucoseType.RANDOM_BLOOD_GLUCOSE;
        Integer glucoseValue = 0;
        if (values.size() == 0)
            return null;

        if(values.size() > 1){
            for(UserValueRecordMapping v:values){
                if(v.getMapping().getTypeId() == 2033){
                    String value = v.getRecordValue();
                    if(value.equals("0"))
                        type = GlucoseType.FASTING_BLOOD_GLUCOSE;
                    if(value.equals("1"))
                        type = GlucoseType.FASTING_BLOOD_GLUCOSE;
                }else if(v.getMapping().getTypeId() == 136){
                    try {
                        glucoseValue = Integer.valueOf(v.getRecordValue());
                    }catch (NumberFormatException e){
                        logger.warn("turnValueRecordToBloodGlucose : " + e.getMessage());
                    }
                }

            }
        }

        UserValueRecordMapping value = values.get(0);

        BloodGlucose testBloodGlucose = new BloodGlucose();

        testBloodGlucose.setGlucose(glucoseValue);
        // type_id = 2033
        testBloodGlucose.setGlucoseType(type);

        // recordtime, latitude, longitude
        testBloodGlucose.setRecordTime(bg.getRecordDate());
        testBloodGlucose.setLatitude("0");
        testBloodGlucose.setLongitude("0");


        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        testBloodGlucose.setStatus(MeasurementStatusType.EXISTED);
        testBloodGlucose.setCreateTime(bg.getUpdateDate());
        testBloodGlucose.setCreateBy("TTSB");
        testBloodGlucose.setTenantId("TTSB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        testBloodGlucose.setSubjectSeq(subject.getSequence());
        testBloodGlucose.setSubjectId(subject.getId());
        testBloodGlucose.setSubjectName(subject.getName());
        testBloodGlucose.setSubjectGender(subject.getGender());
        testBloodGlucose.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), bg.getRecordDate()));
        testBloodGlucose.setSubjectUserId(subject.getUserId());
        testBloodGlucose.setSubjectUserName(subject.getName());


        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(subject.getUnitId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
            testBloodGlucose.setUnitId(bg.getLocationId());
            testBloodGlucose.setUnitName(bg.getLocationName());
        }
        testBloodGlucose.setParentUnitId(unit.getParentId());
        testBloodGlucose.setParentUnitName(unit.getParentName());

        return testBloodGlucose;
    }


    private void syncMeasurementBloodPressureHeartBeat(List<UserValueRecord> userValueRecords,
                                                       Map<Integer, List<UserValueRecordMapping>> userValueRecordMap,
                                                       Subject subject, SystemUser su, UserValueRecordJDBC userValueRecordJDBC) {


        AbnormalBloodPressureJDBC abnormalBloodPressureJDBC = new AbnormalBloodPressureJDBC();
        AbnormalBloodPressureLogJDBC abnormalBloodPressureLogJDBC = new AbnormalBloodPressureLogJDBC();
        PmoResultJDBC pmoResultJDBC = new PmoResultJDBC();

        // 重算異常
        // 根據使用者身上異常追蹤狀態 例如為 2就醫確診異常
        // 重算後新增的全部異常log 處理狀態為舊資料狀態 2就醫確診異常
        List<BloodPressureHeartBeat> bloodPressureHeartBeats = new ArrayList<>();
        userValueRecords.forEach(old -> {

            BloodPressureHeartBeat bloodPressureHeartBeat = syncBloodPressureHeartBeat(userValueRecordMap, old, subject);

            // 更新舊 UserValueRecord 資料狀態
            userValueRecordJDBC.updateUserValueRecord(old.getBodyValueRecordId());

            // 連續兩筆紀錄為異常 做異常紀錄
            if (bloodPressureHeartBeats.size() > 0) {
                BloodPressureHeartBeat oldBloodPressureHeartBeat = bloodPressureHeartBeats.get(bloodPressureHeartBeats.size() - 1);

                // 判斷是否為異常
                if (isBloodPressureAbnormal(oldBloodPressureHeartBeat) && isBloodPressureAbnormal(bloodPressureHeartBeat)) {
                    AbnormalBloodPressure abnormalBloodPressure = abnormalBloodPressureJDBC
                            .insertAbnormalBloodPressure(
                                    turnNoarmalToAbnormal(bloodPressureHeartBeat, su));

                    // 尚未處理不需存log
                    if (su.getCaseStatus() != 0 || su.getCaseNote() != null)
                        abnormalBloodPressureLogJDBC
                                .insertAbnormalBloodPressure(
                                        turnBloodPressureAbnormalToLog(abnormalBloodPressure, su));
                }

            }

            System.out.println(old);
            System.out.println(bloodPressureHeartBeat);
            pmoResultJDBC.insert(turnOldRecordsToPmoResult(old, bloodPressureHeartBeat.getSubjectId(), bloodPressureHeartBeat.getSequence(), MeasurementPMOType.BloodPressure));

            updateUserValueRecordMapping(userValueRecordMap, old.getBodyValueRecordId());

            bloodPressureHeartBeats.add(bloodPressureHeartBeat);

        });
    }

    private void updateUserValueRecordMapping(Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, int bodyValueRecordId) {

        List<UserValueRecordMapping> values = userValueRecordMap.get(bodyValueRecordId);
        values.forEach(v -> new UserValueRecordMappingJDBC().updateUserValueRecordMapping(v.getUserValueRecordMappingId()));

    }


    private PmoUser turnSystemUserToPmoUser(SystemUser su) {
        PmoUser pmoUser = new PmoUser();

        // user_id
        pmoUser.setUserId(su.getUserAccount());
        // pmo_password
        pmoUser.setPmoPassword(su.getPmoPassword());
        // status

        PmoStatus pmoStatus = PmoStatus.NotSync;
        SystemUser.SystemUserPmoStatus ss = su.getPmoStatus();
        if (ss == SystemUser.SystemUserPmoStatus.Sync)
            pmoStatus = PmoStatus.Sync;
        if (ss == SystemUser.SystemUserPmoStatus.NotSync)
            pmoStatus = PmoStatus.NotSync;
        if (ss == SystemUser.SystemUserPmoStatus.Error)
            pmoStatus = PmoStatus.Error;
        pmoUser.setPmoStatus(pmoStatus);

        pmoUser.setTenantId("TTSHB");

        return pmoUser;

    }

    private PmoResult turnOldRecordsToPmoResult(UserValueRecord old, String userId, long sequence, MeasurementPMOType type) {

        PmoResult pmoResult = new PmoResult();

        pmoResult.setUserId(userId);
        // measurement_type
        pmoResult.setMeasurementType(type);
        // record_id
        pmoResult.setRecordId(sequence);
        // result
        pmoResult.setResult(old.getPmoResult());
        // status
        PmoStatus pmoStatus = PmoStatus.NotSync;
        UserValueRecord.RecordPmoStatus ss = old.getPmoStatus();
        if (ss == UserValueRecord.RecordPmoStatus.Sync)
            pmoStatus = PmoStatus.Sync;
        if (ss == UserValueRecord.RecordPmoStatus.NotSync)
            pmoStatus = PmoStatus.NotSync;
        if (ss == UserValueRecord.RecordPmoStatus.Error)
            pmoStatus = PmoStatus.Error;
        pmoResult.setPmoStatus(pmoStatus);
        // synctime
        pmoResult.setSynctime(old.getUpdateDate());

        return pmoResult;

    }

    private AbnormalBloodPressureLog turnBloodPressureAbnormalToLog(AbnormalBloodPressure abnormalBloodPressure, SystemUser su) {
        AbnormalBloodPressureLog abnormalBloodPressureLog = new AbnormalBloodPressureLog();

        // abnormal_blood_pressure_squence, case_status, subject_id, subject_name
        abnormalBloodPressureLog.setAbnormalBloodPressureSquence(abnormalBloodPressure.getBloodPressureSeq());
        abnormalBloodPressureLog.setCaseStatus(abnormalBloodPressure.getCaseStatus());
        abnormalBloodPressureLog.setSubjectId(abnormalBloodPressure.getSubjectId());
        abnormalBloodPressureLog.setSubjectName(abnormalBloodPressure.getSubjectName());

        // case_creator_user_id, case_creator_user_name, case_description, recordtime, tenant_id
        abnormalBloodPressureLog.setCaseCreatorUserId("TTSHB");
        abnormalBloodPressureLog.setCaseCreatorUserName("TTSB");
        abnormalBloodPressureLog.setCaseDescription(su.getCaseNote());
        System.out.println(su);

        if(su.getCaseUpdateDate() == null)
            abnormalBloodPressureLog.setChangeCaseStatusTime(su.getCaseUpdateDate());
        else
            abnormalBloodPressureLog.setChangeCaseStatusTime(abnormalBloodPressure.getLastChangeCaseStatusTime());

        abnormalBloodPressureLog.setTenantId(abnormalBloodPressure.getTenantId());

        if (abnormalBloodPressureLog.getChangeCaseStatusTime() == null)
            abnormalBloodPressureLog.setChangeCaseStatusTime(new Date());

        System.out.println(abnormalBloodPressureLog);
        return abnormalBloodPressureLog;

    }

    private AbnormalBloodPressure turnNoarmalToAbnormal(BloodPressureHeartBeat bloodPressureHeartBeat, SystemUser systemUser) {

        AbnormalBloodPressure abnormalBloodPressure = new AbnormalBloodPressure();

        abnormalBloodPressure.setSubjectSeq(bloodPressureHeartBeat.getSubjectSeq());

        abnormalBloodPressure.setSubjectId(bloodPressureHeartBeat.getSubjectId());
        abnormalBloodPressure.setBloodPressureSeq(bloodPressureHeartBeat.getSequence());

        abnormalBloodPressure.setSubjectName(bloodPressureHeartBeat.getSubjectName());
        abnormalBloodPressure.setSubjectGender(bloodPressureHeartBeat.getSubjectGender());
        abnormalBloodPressure.setSubjectAge(bloodPressureHeartBeat.getSubjectAge());
        abnormalBloodPressure.setSubjectUserId(bloodPressureHeartBeat.getSubjectUserId());
        abnormalBloodPressure.setSubjectUserName(bloodPressureHeartBeat.getSubjectUserName());
        abnormalBloodPressure.setSystolicPressure(bloodPressureHeartBeat.getSystolicPressure());
        abnormalBloodPressure.setDiastolicPressure(bloodPressureHeartBeat.getDiastolicPressure());
        abnormalBloodPressure.setHeartRate(bloodPressureHeartBeat.getHeartRate());
        abnormalBloodPressure.setRecordtime(bloodPressureHeartBeat.getRecordTime());
        abnormalBloodPressure.setCreateBy(bloodPressureHeartBeat.getCreateBy());

//        private BloodPressureCaseStatus caseStatus;
        // 0尚未處理  1就醫確診正常  2就醫確診異常  3拒絕就醫及複查  4無法聯繫
        BloodPressureCaseStatus casetStatus = null;
        switch (systemUser.getCaseStatus()) {
            case 0:
                casetStatus = BloodPressureCaseStatus.NOT_YET;
                break;
            case 1:
                casetStatus = BloodPressureCaseStatus.DIAGNOSIS_NORMAL;

                break;
            case 2:
                casetStatus = BloodPressureCaseStatus.DIAGNOSIS_ABNORMALITY;
                break;
            case 3:
                casetStatus = BloodPressureCaseStatus.REFUSE_DIAGNOSIS;
                break;
            case 4:
                casetStatus = BloodPressureCaseStatus.NOT_CONTACT;
                break;
            default:
        }
        abnormalBloodPressure.setCaseStatus(casetStatus);

//        private Date lastChangeCaseStatusTime;
        if(systemUser.getCaseUpdateDate() != null)
            abnormalBloodPressure.setLastChangeCaseStatusTime(systemUser.getCaseUpdateDate());
        else
            abnormalBloodPressure.setLastChangeCaseStatusTime(bloodPressureHeartBeat.getCreateTime());

        abnormalBloodPressure.setUnitId(bloodPressureHeartBeat.getUnitId());
        abnormalBloodPressure.setTenantId(bloodPressureHeartBeat.getTenantId());
        abnormalBloodPressure.setDeviceMacAddress(bloodPressureHeartBeat.getDeviceMacAddress());
        abnormalBloodPressure.setUnitName(bloodPressureHeartBeat.getUnitName());
//        private MeasurementStatusType status;
        abnormalBloodPressure.setStatus(bloodPressureHeartBeat.getStatus());
//        private String ruleDescription;
//        abnormalBloodPressure.setRuleDescription();

        abnormalBloodPressure.setParentUnitId(bloodPressureHeartBeat.getParentUnitId());
        abnormalBloodPressure.setParentUnitName(bloodPressureHeartBeat.getParentUnitName());
        abnormalBloodPressure.setDeviceId(bloodPressureHeartBeat.getDeviceId());

        return abnormalBloodPressure;
    }

    private boolean isBloodPressureAbnormal(BloodPressureHeartBeat record) {
        Integer age = record.getSubjectAge();
        Integer systolic = record.getSystolicPressure();
        Integer diastolic = record.getDiastolicPressure();

        if (isSystolicAbnormal(age, systolic) || isDiastolicAbnormal(diastolic)) {
            return true;
        }
        return false;
    }

    private boolean isSystolicAbnormal(Integer age, Integer systolic) {
        if (systolic > 150 || systolic == 150 || (age < 80 && systolic > 140 && systolic == 140)) {
            return true;
        }
        return false;
    }

    private boolean isDiastolicAbnormal(Integer diastolic) {
        if (diastolic > 90 || diastolic == 90) {
            return true;
        }
        return false;
    }

    private BloodPressureHeartBeat syncBloodPressureHeartBeat(
            Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, UserValueRecord old, Subject subject) {

        // 取出該筆紀錄的值
        List<UserValueRecordMapping> recordValues = userValueRecordMap.get(old.getBodyValueRecordId());
        // 7 收縮壓 8 舒張壓 9 心跳
        Map<Integer, List<UserValueRecordMapping>> values = recordValues.stream()
                .collect(Collectors.groupingBy(s -> s.getMapping().getTypeId()));

        BloodPressureHeartBeatJDBC bloodPressureHeartBeatJDBC = new BloodPressureHeartBeatJDBC();

        BloodPressureHeartBeat bloodPressureHeartBeat = bloodPressureHeartBeatJDBC
                .insertBloodPressureHeartBeat(turnOldRecordsToBloodPressureHeartBeat(old, values, subject));

        // 更新舊 UserValueRecordMapping 資料狀態
        recordValues.forEach(mapping -> new UserValueRecordMappingJDBC()
                .updateUserValueRecordMapping(mapping.getUserValueRecordMappingId())
        );
        return bloodPressureHeartBeat;
    }

    private BloodPressureHeartBeat turnOldRecordsToBloodPressureHeartBeat(
            UserValueRecord old, Map<Integer, List<UserValueRecordMapping>> values, Subject subject) {

        BloodPressureHeartBeat bloodPressureHeartBeat = new BloodPressureHeartBeat();

        // 7 收縮壓 8 舒張壓 9 心跳
        // systolic_pressure, diastolic_pressure, heart_rate
        List<UserValueRecordMapping> systolicPressure = values.get(7);
        if (systolicPressure.size() > 0)
            bloodPressureHeartBeat.setSystolicPressure(Integer.valueOf(systolicPressure.get(0).getRecordValue()));
        List<UserValueRecordMapping> diastolicPressure = values.get(8);
        if (diastolicPressure.size() > 0)
            bloodPressureHeartBeat.setDiastolicPressure(Integer.valueOf(diastolicPressure.get(0).getRecordValue()));
        // constraints:nullable: false
        List<UserValueRecordMapping> heartRate = values.get(9);
        if (heartRate.size() > 0)
            bloodPressureHeartBeat.setHeartRate(Integer.valueOf(heartRate.get(0).getRecordValue()));


        // recordtime, latitude, longitude
        bloodPressureHeartBeat.setRecordTime(old.getRecordDate());

        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        bloodPressureHeartBeat.setStatus(MeasurementStatusType.EXISTED);
        bloodPressureHeartBeat.setCreateTime(old.getUpdateDate());
        bloodPressureHeartBeat.setCreateBy(subject.getId());
        bloodPressureHeartBeat.setTenantId("TTSHB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        bloodPressureHeartBeat.setSubjectSeq(subject.getSequence());
        bloodPressureHeartBeat.setSubjectId(subject.getId());
        bloodPressureHeartBeat.setSubjectName(subject.getName());
        bloodPressureHeartBeat.setSubjectGender(subject.getGender());
        bloodPressureHeartBeat.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), old.getRecordDate()));
        bloodPressureHeartBeat.setSubjectUserId(subject.getUserId());
        bloodPressureHeartBeat.setSubjectUserName(subject.getName());


        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(subject.getUnitId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
            bloodPressureHeartBeat.setUnitId(old.getLocationId());
            bloodPressureHeartBeat.setUnitName(old.getLocationName());
        }
        bloodPressureHeartBeat.setUnitId(unit.getId());
        bloodPressureHeartBeat.setUnitName(unit.getName());
        bloodPressureHeartBeat.setParentUnitId(unit.getParentId());
        bloodPressureHeartBeat.setParentUnitName(unit.getParentName());

        return bloodPressureHeartBeat;
    }


    private EmergencyContact syncSystemUserToEmergencyContact(SystemUser su) {

        EmergencyContact emergencyContact = new EmergencyContact();

        emergencyContact.setSubjectId(su.getUserAccount());
        emergencyContact.setUserId(su.getUserAccount());
        emergencyContact.setTenantId("TTSHB");
        emergencyContact.setName(su.getAlertNotifierName());
        emergencyContact.setPhone(su.getAlertNotifierMobilePhone());
        emergencyContact.setEmail(su.getAlertNotifierEmail());
        emergencyContact.setStatus(ModelStatus.ENABLED);

        return emergencyContact;
    }

    private User syncSystemUserToUser(SystemUser su, Role newrole) {

        User user = new User();
        // sequence, id, name, tenant_id, source, meta
        user.setId(su.getUserAccount());
        user.setName(su.getUserDisplayName());
        user.setTenantId("TTSHB");
        user.setSource(Source.CREATE);

        // unit_ids, role_ids, emails, mobilephones, cards, permission_ids
//        user.setUnitIds(null); unitId 在 subject 上
        String[] roleIds = {newrole.getId()};
        user.setRoleIds(roleIds);

        String[] mails = {su.getUserEmail()};
        user.setEmails(mails);

        String[] mobilePhones = {su.getUserPhone()};
        user.setMobilePhones(mobilePhones);

//        感應卡功能取消 不同步  String[] cards = {"123456"};
//        user.setCards(cards);

        user.setPermissionIds(newrole.getPermissionIds());

        // createtime, createby, updatetime, updateby, status
        user.setCreateTime(su.getCreateTime());
        user.setCreateBy("TTSHB");
        user.setUpdateTime(su.getCreateTime());
        user.setUpdateBy("TTSHB");
        user.setStatus(ModelUserStatus.ENABLED);
        return user;
    }

    private Subject syncSystemUserToSubject(SystemUser su) {

        Subject subject = new Subject();

//        sequence, id, name, gender
        subject.setId(su.getUserAccount());
        subject.setName(su.getUserDisplayName());

        GenderType genderType;
        if (su.getSex() == Sex.male)
            genderType = GenderType.MALE;
        else
            genderType = GenderType.FEMALE;
        subject.setGender(genderType);

//        birthday, home_phone, address, ethnicity
        subject.setBirthday(su.getUserBirthday());
        subject.setHomePhone(su.getUserPhone());
        subject.setAddress(su.getUserAddress());

        // 種族註記，0：未填寫，1：漢族，2：客家，3：原住民，4：外籍
        // 0 預設為HAN
        int suEthnicity = su.getEthnicity();
        EthnicityType ethnicityType;

        switch (suEthnicity) {
            case 1:
                ethnicityType = EthnicityType.HAN;
                break;
            case 2:
                ethnicityType = EthnicityType.HAKKA;
                break;
            case 3:
                ethnicityType = EthnicityType.FOREIGN;
                break;
            default:
                ethnicityType = EthnicityType.HAN;
        }

        subject.setEthnicity(ethnicityType);

//        personal_history, family_history, smoke, drink

        ArrayList<PersonalHistoryType> personalHistoryTypes = new ArrayList();
        if (su.getWithHighBloodPressure() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.HYPERTENSION);
        if (su.getWithBrainAttack() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.STROKE);
        if (su.getWithDiabetesMellitus() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.DIABETES_MELLITUS);
        if (su.getWithHeartAttack() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.HEART_DISEASE);
        subject.setPersonalHistory(personalHistoryTypes);


        ArrayList<FamilyHistoryType> familyHistoryTypes = new ArrayList();
        if (su.getFamilyWithBrainAttack() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.STROKE);
        if (su.getFamilyWithDiabetesMellitus() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.DIABETES_MELLITUS);
        if (su.getFamilyWithHeartAttack() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.HEART_DISEASE);
        if (su.getFamilyWithHighBloodPressure() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.HYPERTENSION);
        subject.setFamilyHistory(familyHistoryTypes);

        SmokeType smokeType;
        switch (su.getFrequencyOfSmoking()) {
            case 1:
                smokeType = SmokeType.NONE;
                break;
            case 2:
                smokeType = SmokeType.SOCIALITY;
                break;
            case 3:
                smokeType = SmokeType.OFTEN;
                break;
            case 4:
                smokeType = SmokeType.ALWAYS;
                break;
            default:
                smokeType = SmokeType.NONE;

        }
        subject.setSmoke(smokeType);

        DrinkType drinkType;
        switch (su.getFrequencyOfDrinking()) {
            case 1:
                drinkType = DrinkType.NONE;
                break;
            case 2:
                drinkType = DrinkType.SOCIALITY;
                break;
            case 3:
                drinkType = DrinkType.OFTEN;
                break;
            default:
                drinkType = DrinkType.NONE;
        }
        subject.setDrink(drinkType);


//        chewing_areca, user_id, unit_id, unit_name
        ChewingArecaType chewingArecaType;
        switch (su.getFrequencyOfDrinking()) {
            case 1:
                chewingArecaType = ChewingArecaType.NONE;
                break;
            case 2:
                chewingArecaType = ChewingArecaType.SOCIALITY;
                break;
            case 3:
                chewingArecaType = ChewingArecaType.OFTEN;
                break;
            default:
                chewingArecaType = ChewingArecaType.NONE;
        }
        su.getFrequencyOfChewingBetelNut();
        subject.setChewingAreca(chewingArecaType);


        subject.setUserId(su.getUserAccount());
        // 舊版身上都是 10014
        subject.setUnitId("100140102310");
        subject.setUnitName("其他");

//        tenant_id, createtime, createby, updatetime
        subject.setTenantId("TTSHB");

        subject.setCreateTime(su.getCreateTime());
        subject.setCreateBy("TTSHB");
        subject.setUpdateTime(su.getCreateTime());

//                    updateby, status
        subject.setUpdateBy("TTSHB");
        subject.setStatus(ModelStatus.ENABLED);
        return subject;
    }

    public Unit getOtherUnit(Unit unit) {

        unit.setId("100140102310");
        unit.setName("其他");
        unit.setTenantId("TTSHB");
        unit.setParentId("1001401");
        unit.setParentName("台東市");
        return unit;
    }
}
