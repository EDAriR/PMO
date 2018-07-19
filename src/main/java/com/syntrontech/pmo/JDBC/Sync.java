package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.PasswordListJDBC;
import com.syntrontech.pmo.JDBC.auth.RoleJDBC;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.auth.UserJDBC;
import com.syntrontech.pmo.JDBC.cip.*;
import com.syntrontech.pmo.JDBC.measurement.AbnormalBloodPressureJDBC;
import com.syntrontech.pmo.JDBC.measurement.AbnormalBloodPressureLogJDBC;
import com.syntrontech.pmo.JDBC.measurement.BloodPressureHeartBeatJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.*;
import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.AbnormalBloodPressure;
import com.syntrontech.pmo.measurement.BloodPressureHeartBeat;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.*;
import com.syntrontech.pmo.syncare1.model.*;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;
import com.syntrontech.pmo.util.CalendarUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Sync {

    public static void main(String[] args) {

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

        PasswordListJDBC passwordListJDBC = new PasswordListJDBC();

        // 取出新的role 只要user 的  DEFAULT_TENANT_ADMIN TTABO
        Role newrole = new RoleJDBC().getRoleById("DEFAULT_TENANT_ADMIN");

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
                                abnormalBloodPressureJDBC.insertAbnormalBloodPressure(turnNoarmalToAbnormal(bloodPressureHeartBeat));
//                                abnormalBloodPressureLogJDBC.
                            }

                        }

                        bloodPressureHeartBeats.add(bloodPressureHeartBeat);
                    });


                    // TODO PMO
                    // ↓  不需要
                    // ALBUM_NAME	varchar(45) NULL
                    // ALBUM_TYPE	int(11) unsigned [0]	使用者mapping的相本為 -> 1;picasa, 2:無名 .....
                    // ADVERTISMENT_STATUS 好康報報對於使用者的狀態_1: 此使用者尚未收到"新廣告通知了"(包含修改),2:此使用者已經收到"新廣告通知了

                });
    }

    private AbnormalBloodPressure turnNoarmalToAbnormal(BloodPressureHeartBeat bloodPressureHeartBeat) {

        AbnormalBloodPressure abnormalBloodPressure = new AbnormalBloodPressure();

        private Long subjectSeq;

        abnormalBloodPressure.setSubjectSeq();

        private String subjectId;
        abnormalBloodPressure.setSubjectId();

        private String subjectName;
        abnormalBloodPressure.setSubjectName();
        private GenderType subjectGender;
        abnormalBloodPressure.setSubjectGender();
        private Integer subjectAge;
        abnormalBloodPressure.setSubjectAge();
        private String subjectUserId;
        abnormalBloodPressure.setSubjectUserId();
        private String subjectUserName;
        abnormalBloodPressure.setSubjectUserName();
        private Integer systolicPressure;
        abnormalBloodPressure.setSystolicPressure();
        private Integer diastolicPressure;
        abnormalBloodPressure.setDiastolicPressure();
        private Integer heartRate;
        abnormalBloodPressure.setHeartRate();
        private Date recordtime;
        abnormalBloodPressure.setRecordtime();
        private String createBy;
        abnormalBloodPressure.setCreateBy();
        private BloodPressureCaseStatus caseStatus;
        abnormalBloodPressure.setCaseStatus();
        @Column(name = "last_change_case_status_time", nullable = false)
        private Date lastChangeCaseStatusTime;

        @Column(name = "unit_id")
        private String unitId;

        @Column(name = "tenant_id", nullable = false)
        private String tenantId;

        @Column(name = "device_mac_address")
        private String deviceMacAddress;

        @Column(name = "unit_name")
        private String unitName;

        @Column(name = "status", nullable = false)
        @Enumerated(EnumType.STRING)
        private MeasurementStatusType status;

        @Column(name = "rule_description")
        private String ruleDescription;

        @Column(name = "parent_unit_id")
        private String parentUnitId;

        @Column(name = "parent_unit_name")
        private String parentUnitName;

        @Column(name = "device_id")
        private String deviceId;
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
        bloodPressureHeartBeat.setTenantId("DEAFULT_TENTANT");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        bloodPressureHeartBeat.setSubjectSeq(subject.getSequence());
        bloodPressureHeartBeat.setSubjectId(subject.getId());
        bloodPressureHeartBeat.setSubjectName(subject.getName());
        bloodPressureHeartBeat.setSubjectGender(subject.getGender());
        bloodPressureHeartBeat.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), old.getRecordDate()));
        bloodPressureHeartBeat.setSubjectUserId(subject.getUserId());
        bloodPressureHeartBeat.setSubjectUserName(subject.getName());


        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        // TODO
//        bloodPressureHeartBeat.setRuleSeq(rs.getLong("rule_seq"));
//        bloodPressureHeartBeat.setRuleDescription(rs.getString("rule_description"));

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
        emergencyContact.setTenantId("DEFAULT_TENANT_ADMIN");
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
        user.setTenantId("TTABO");
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
        user.setCreateBy("TTABO");
        user.setUpdateTime(su.getCreateTime());
        user.setUpdateBy("TTABO");
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
        // TODO
        subject.setTenantId("TTABO");

        subject.setCreateTime(su.getCreateTime());
        subject.setCreateBy("TTABO");
        subject.setUpdateTime(su.getCreateTime());

//                    updateby, status
        subject.setUpdateBy("TTABO");
        subject.setStatus(ModelStatus.ENABLED);
        return subject;
    }

}
