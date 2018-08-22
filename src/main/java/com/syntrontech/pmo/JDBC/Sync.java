package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.Auth_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.auth.PasswordListJDBC;
import com.syntrontech.pmo.JDBC.auth.RoleJDBC;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.auth.UserJDBC;
import com.syntrontech.pmo.JDBC.cip.*;
import com.syntrontech.pmo.JDBC.measurement.*;
import com.syntrontech.pmo.JDBC.pmo.PmoUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.*;
import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.*;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.*;
import com.syntrontech.pmo.syncare1.model.*;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;
import com.syntrontech.pmo.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Sync {

    private static Logger logger = LoggerFactory.getLogger(Sync.class);

    public static void main(String[] args) {

        // TODO 台東 預設 系統管理員 TENANT ==> TTSHB
        // 測試流程 ==> 新建TTSHB  TENANT 給 default user 權限後測試
        Sync sync = new Sync();
        sync.syncSystemUserToUserAndSubject();
    }

    public List<String> syncSystemUserToUserAndSubject() {

        Connection authconn = new Auth_GET_CONNECTION().getConn();
        Connection cipconn = new CIP_GET_CONNECTION().getConn();
        Connection measurementconn = new MEASUREMENT_GET_CONNECTION().getConn();
        Connection syncare1conn = new Syncare1_GET_CONNECTION().getConn();


        // 取出所有 未同步 且 角色為使用者的使用者
        List<String> userIds = new UserRoleJDBC().getAllUserRoles();

        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();
        UserJDBC userJDBC = new UserJDBC();
        SubjectJDBC subjectJDBC = new SubjectJDBC();
        EmergencyContactJDBC emergencyContactJDBC = new EmergencyContactJDBC();

        UserValueRecordJDBC userValueRecordJDBC = new UserValueRecordJDBC();

//        PmoUserJDBC pmoUserJDBC = new PmoUserJDBC();

        PasswordListJDBC passwordListJDBC = new PasswordListJDBC();

        // 取出新的role 只要user 的  DEFAULT_TENANT_ADMIN  DEFAULT_USER  TTSHB
        Role newrole = new RoleJDBC().getRoleById("DEFAULT_USER");

        // 取出所有未同步量測紀錄數值 <body_value_record_id, UserValueRecordMappings>
        Map<Integer, List<UserValueRecordMapping>> userValueRecordMap = new UserValueRecordMappingJDBC()
                .getAllUserValueRecordMapping();

        List<String> pwds = new ArrayList<>();
        try {

            for (String id : userIds) {

                // 找出未同步systemuser 
                SystemUser su = systemUserJDBC.getSystemUserById(syncare1conn, id);
                System.out.println("unsync SystemUser" + su);

                logger.info("sync system user :" + su);
                if (su.getUserAccount() == null) {
                    continue;
                }

                // 新增 user
                User user = userJDBC.insertUser(authconn, syncSystemUserToUser(su, newrole));

                logger.info("user in new db : " + user);
                // 密碼
                String pwd = passwordListJDBC.insertPassword(authconn, user, su.getUserBirthday());
                // 新增 subject cipconn
                Subject subject = subjectJDBC.insertSubject(cipconn, syncSystemUserToSubject(su));

                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println("user account = " + user.getId());
                System.out.println("old user account = " + su.getUserAccount());
                System.out.println("user birthday = " + su.getUserBirthday());
                System.out.println(pwd);
                pwds.add("user birthday =" + su.getUserBirthday() + ", " + pwd);
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");


                // 新增 緊急聯絡人 Alert = Y 為接受緊急通知
                if (su.getAlert() == YN.Y && su.getAlertNotifierName() != null)
                    emergencyContactJDBC.insertEmergencyContact(cipconn, syncSystemUserToEmergencyContact(su));

                // 取出該使用者所有血壓  拉出
//                List<UserValueRecord> userValueRecords = userValueRecordJDBC.getOneBUserValueRecord(syncare1conn, su.getUserId());

                // 同步至 新的血壓心跳 異常追蹤 異常追蹤log  拉出
//                syncMeasurementBloodPressureHeartBeat(authconn, syncare1conn, measurementconn, userValueRecords, userValueRecordMap, subject, su, userValueRecordJDBC);

                // 同步新的血糖 拉出
//                syncBloodGlucose(authconn, syncare1conn, measurementconn, su, userValueRecordMap, subject, userValueRecordJDBC);

                // BodyInfo 更新至新的身高體重
                synBodyInfo(authconn, syncare1conn, measurementconn, userValueRecordJDBC, su, subject, userValueRecordMap);

                // Biochemistry 先不同步
//                syncBiochemistry(authconn, syncare1conn, measurementconn, su, subject, userValueRecordMap, userValueRecordJDBC);

                // PMO USER RESULT
//                pmoUserJDBC.insert(turnSystemUserToPmoUser(su));
                // update systemUser sync status
                systemUserJDBC.updateSystemUser(syncare1conn, su.getUserId());

                new UserRoleJDBC().updateUserRoles(su.getUserId());


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                authconn.close();
                cipconn.close();
                measurementconn.close();
                syncare1conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pwds;
    }


    private void synBodyInfo(
            Connection authconn, Connection syncare1conn, Connection measurementconn,
            UserValueRecordJDBC userValueRecordJDBC, SystemUser su, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap) {

        BodyInfoJDBC bodyInfoJDBC = new BodyInfoJDBC();
        List<UserValueRecord> userBodyInfoValueRecords = userValueRecordJDBC.getOneUserAValueRecord(su.getUserId());
        userBodyInfoValueRecords.forEach(record -> {

            try {
                bodyInfoJDBC.insert(turnValueRecordToBodyInfo(authconn, record, subject, userValueRecordMap));
                // update mapping / records
                updateUserValueRecordMapping(syncare1conn, userValueRecordMap, record.getBodyValueRecordId());
                userValueRecordJDBC.updateUserValueRecord(syncare1conn, record.getBodyValueRecordId());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private BodyInfo turnValueRecordToBodyInfo(
            Connection authconn,
            UserValueRecord record, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap) throws SQLException {

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
        } catch (Exception e) {

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
        Unit unit = unitJDBC.getUnitById(authconn, record.getLocationId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
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
            if (record != null && record.size() > 0) {
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


    private void updateUserValueRecordMapping(
            Connection syncare1conn,
            Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, int bodyValueRecordId) {

        List<UserValueRecordMapping> values = userValueRecordMap.get(bodyValueRecordId);
        values.forEach(v -> new UserValueRecordMappingJDBC().updateUserValueRecordMapping(syncare1conn, v.getUserValueRecordMappingId()));

    }


//    private PmoUser turnSystemUserToPmoUser(SystemUser su) {
//        PmoUser pmoUser = new PmoUser();
//
//        // user_id
//        pmoUser.setUserId(su.getUserAccount());
//        // pmo_password
//        pmoUser.setPmoPassword(su.getPmoPassword());
//        // status
//
//        PmoStatus pmoStatus = PmoStatus.NotSync;
//        SystemUser.SystemUserPmoStatus status = su.getPmoStatus();
//        if (status == SystemUser.SystemUserPmoStatus.Sync)
//            pmoStatus = PmoStatus.Sync;
//        if (status == SystemUser.SystemUserPmoStatus.NotSync)
//            pmoStatus = PmoStatus.NotSync;
//        if (status == SystemUser.SystemUserPmoStatus.Error)
//            pmoStatus = PmoStatus.Error;
//        pmoUser.setPmoStatus(pmoStatus);
//
//        pmoUser.setTenantId("TTSHB");
//
//        return pmoUser;
//
//    }

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
        user.setId(su.getUserAccount().toUpperCase().trim());
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
        subject.setId(su.getUserAccount().toUpperCase());
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


        ArrayList<FamilyHistoryType> familyHistoryTypes = new ArrayList<>();
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
