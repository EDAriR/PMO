package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.PasswordListJDBC;
import com.syntrontech.pmo.JDBC.auth.RoleJDBC;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.auth.UserJDBC;
import com.syntrontech.pmo.JDBC.cip.*;
import com.syntrontech.pmo.JDBC.measurement.AbnormalBloodPressureJDBC;
import com.syntrontech.pmo.JDBC.measurement.AbnormalBloodPressureLogJDBC;
import com.syntrontech.pmo.JDBC.measurement.BloodPressureHeartBeatJDBC;
import com.syntrontech.pmo.JDBC.pmo.PmoResultJDBC;
import com.syntrontech.pmo.JDBC.pmo.PmoUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.*;
import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.AbnormalBloodPressure;
import com.syntrontech.pmo.measurement.AbnormalBloodPressureLog;
import com.syntrontech.pmo.measurement.BloodPressureHeartBeat;
import com.syntrontech.pmo.measurement.common.BloodPressureCaseStatus;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.*;
import com.syntrontech.pmo.pmo.PmoResult;
import com.syntrontech.pmo.pmo.PmoUser;
import com.syntrontech.pmo.pmo.PmoStatus;
import com.syntrontech.pmo.syncare1.model.*;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;
import com.syntrontech.pmo.util.CalendarUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Sync {

    public static void main(String[] args) {

        // TODO 台東 預設 系統管理員 TENANT ==> TTSHB
        // 測試流程 ==> 新建TTSHB  TENANT 給 default user 權限後測試
        Sync sync = new Sync();

        // TODO syncare_questionnair_answer

//        new SyncUnit().syncLocationToUnit();
//        new SyncDevice().syncDevice();
        sync.syncSystemUserToUserAndSubject();
    }

    public void syncSystemUserToUserAndSubject(){

        // 取出所有 未同步 且 角色為使用者的使用者
        List<String> userIds = new UserRoleJDBC().getAllUserRoles();

        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();
        UserJDBC userJDBC = new UserJDBC();
        SubjectJDBC subjectJDBC = new SubjectJDBC();
        EmergencyContactJDBC emergencyContactJDBC = new EmergencyContactJDBC();

        AbnormalBloodPressureJDBC abnormalBloodPressureJDBC = new AbnormalBloodPressureJDBC();
        AbnormalBloodPressureLogJDBC abnormalBloodPressureLogJDBC = new AbnormalBloodPressureLogJDBC();
        UserValueRecordJDBC userValueRecordJDBC = new UserValueRecordJDBC();

        PmoResultJDBC pmoResultJDBC =  new PmoResultJDBC();
        PmoUserJDBC pmoUserJDBC = new PmoUserJDBC();

        PasswordListJDBC passwordListJDBC = new PasswordListJDBC();

        // 取出新的role 只要user 的  DEFAULT_TENANT_ADMIN  DEFAULT_USER  TTSHB
        Role newrole = new RoleJDBC().getRoleById("DEFAULT_USER");

        // 取出所有未同步量測紀錄數值 <body_value_record_id, UserValueRecordMappings>
        Map<Integer, List<UserValueRecordMapping>> userValueRecordMap = new UserValueRecordMappingJDBC()
                .getAllUserValueRecordMapping();

        userIds.stream()   // 找出未同步systemuser
                .map(id -> systemUserJDBC.getSystemUserById(id))
                .forEach(su -> {

                    // 新增 user
                    User user = userJDBC.insertUser(syncSystemUserToUser(su, newrole));
                    // 密碼
                    passwordListJDBC.insertPassword(user, su.getUserPassword());
                    // 新增 subject
                    Subject subject = subjectJDBC.insertSubject(syncSystemUserToSubject(su));
                    // 新增 緊急聯絡人 Alert = Y 為接受緊急通知
                    if (su.getAlert() == YN.Y)
                        emergencyContactJDBC.insertEmergencyContact(syncSystemUserToEmergencyContact(su));

                    // 取出該使用者所有血壓重算異常
                    // 根據使用者身上異常追蹤狀態 例如為 2就醫確診異常
                    // 重算後新增的全部異常log 處理狀態為舊資料狀態 2就醫確診異常
                    List<UserValueRecord> userValueRecords = userValueRecordJDBC.getOneBUserValueRecord(su.getUserId());

                    // 同步至 新的血壓心跳
                    List<BloodPressureHeartBeat>  bloodPressureHeartBeats = new ArrayList<>();
                    userValueRecords.forEach(old ->{

                        BloodPressureHeartBeat bloodPressureHeartBeat = syncBloodPressureHeartBeat(userValueRecordMap, old, subject);

                        // 更新舊 UserValueRecord 資料狀態
                        userValueRecordJDBC.updateUserValueRecord(old.getBodyValueRecordId());

                        // 連續兩筆紀錄為異常 做異常紀錄
                        BloodPressureHeartBeat oldBloodPressureHeartBeat = null;
                        if(bloodPressureHeartBeats.size() > 0 ){
                            oldBloodPressureHeartBeat = bloodPressureHeartBeats.get(bloodPressureHeartBeats.size() - 1 );
                        }
                        if (oldBloodPressureHeartBeat != null){
                            // 判斷是否為異常
                            // TODO 異常
                            if(isBloodPressureAbnormal(oldBloodPressureHeartBeat) && isBloodPressureAbnormal(bloodPressureHeartBeat)){
                                AbnormalBloodPressure abnormalBloodPressure = abnormalBloodPressureJDBC
                                        .insertAbnormalBloodPressure(
                                                turnNoarmalToAbnormal(bloodPressureHeartBeat, su));
                                // 尚未處理不需存log
                                if(su.getCaseStatus() != 0 && su.getCaseNote() != null)
                                    abnormalBloodPressureLogJDBC
                                            .insertAbnormalBloodPressure(
                                                    turnBloodPressureAbnormalToLog(abnormalBloodPressure, su));
                            }

                        }

                        pmoResultJDBC.insert(turnOldRecordsToPmoResult(old, bloodPressureHeartBeat));

                        userValueRecordJDBC.updateUserValueRecord(old.getBodyValueRecordId());
                        bloodPressureHeartBeats.add(bloodPressureHeartBeat);
                    });


                    // TODO PMO
                    // ↓  不需要
                    // ALBUM_NAME	varchar(45) NULL
                    // ALBUM_TYPE	int(11) unsigned [0]	使用者mapping的相本為 -> 1;picasa, 2:無名 .....
                    // ADVERTISMENT_STATUS 好康報報對於使用者的狀態_1: 此使用者尚未收到"新廣告通知了"(包含修改),2:此使用者已經收到"新廣告通知了

                    // TODO sync 136 BG
                    // TODO PMO
                    // TODO su update sync status
                });
    }

    private PmoResult turnOldRecordsToPmoResult(UserValueRecord old, BloodPressureHeartBeat bloodPressureHeartBeat) {

        PmoResult pmoResult = new PmoResult();

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
        abnormalBloodPressureLog.setChangeCaseStatusTime(su.getCaseUpdateDate());
        abnormalBloodPressureLog.setTenantId(abnormalBloodPressure.getTenantId());

        return abnormalBloodPressureLog;

    }

    private AbnormalBloodPressure turnNoarmalToAbnormal(BloodPressureHeartBeat bloodPressureHeartBeat, SystemUser systemUser) {

        AbnormalBloodPressure abnormalBloodPressure = new AbnormalBloodPressure();

        abnormalBloodPressure.setSubjectSeq(bloodPressureHeartBeat.getSubjectSeq());

        abnormalBloodPressure.setSubjectId(bloodPressureHeartBeat.getSubjectId());

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
        switch(systemUser.getCaseStatus()){
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
        abnormalBloodPressure.setLastChangeCaseStatusTime(systemUser.getCaseUpdateDate());

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
        Integer systolic=record.getSystolicPressure();
        Integer diastolic=record.getDiastolicPressure();

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
        if(systolicPressure.size() > 0 )
            bloodPressureHeartBeat.setSystolicPressure(Integer.valueOf(systolicPressure.get(0).getRecordValue()));
        List<UserValueRecordMapping> diastolicPressure = values.get(8);
        if(diastolicPressure.size() > 0 )
            bloodPressureHeartBeat.setDiastolicPressure(Integer.valueOf(diastolicPressure.get(0).getRecordValue()));
        // constraints:nullable: false
        List<UserValueRecordMapping> heartRate = values.get(9);
        if(heartRate.size() > 0 )
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
        Unit unit = unitJDBC.getUnitById(old.getLocationId());
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
        // TODO
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

        switch (suEthnicity){
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

        ArrayList<PersonalHistoryType>  personalHistoryTypes = new ArrayList();
        if(su.getWithHighBloodPressure() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.HYPERTENSION);
        if(su.getWithBrainAttack() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.STROKE);
        if(su.getWithDiabetesMellitus() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.DIABETES_MELLITUS);
        if(su.getWithHeartAttack() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.HEART_DISEASE);
        subject.setPersonalHistory(personalHistoryTypes);


        ArrayList<FamilyHistoryType>  familyHistoryTypes = new ArrayList();
        if(su.getFamilyWithBrainAttack() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.STROKE);
        if(su.getFamilyWithDiabetesMellitus() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.DIABETES_MELLITUS);
        if(su.getFamilyWithHeartAttack() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.HEART_DISEASE);
        if(su.getFamilyWithHighBloodPressure() == YN.Y)
            familyHistoryTypes.add(FamilyHistoryType.HYPERTENSION);
        subject.setFamilyHistory(familyHistoryTypes);

        SmokeType smokeType;
        switch (su.getFrequencyOfSmoking()){
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
        switch(su.getFrequencyOfDrinking()){
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
        switch(su.getFrequencyOfDrinking()) {
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

}
