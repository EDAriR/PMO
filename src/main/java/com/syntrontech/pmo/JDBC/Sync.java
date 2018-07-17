package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.RoleJDBC;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.auth.UserJDBC;
import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.DeviceJDBC;
import com.syntrontech.pmo.JDBC.cip.SubjectJDBC;
import com.syntrontech.pmo.JDBC.cip.UnitMetaJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.DeviceSyncare1JDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.LocationJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserRoleJDBC;
import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.*;
import com.syntrontech.pmo.syncare1.model.Device;
import com.syntrontech.pmo.syncare1.model.Location;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

public class Sync {

    public static void main(String[] args) {

        Sync sync = new Sync();

        // TODO syncare_questionnair_answer
        // TODO blood_pressure_record_report
        // TODO abnormal_blood_pressure_record

//        sync.syncLocationToUnit();
//        sync.syncDevice();
    }

    public void syncSystemUserToUserAndSubject(){

        List<String> userIds = new UserRoleJDBC().getAllUserRoles();

        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();
        SubjectJDBC subjectJDBC = new SubjectJDBC();
        UserJDBC userJDBC = new UserJDBC();

//        DEFAULT_TENANT_ADMIN
//        TTABO
        Role newrole = new RoleJDBC().getRoleById("DEFAULT_TENANT_ADMIN");

        userIds.stream()
                .map(id -> systemUserJDBC.getSystemUserById(id))
                .forEach(su -> {
                    // TODO subjectJDBC 驗證是否存在
                    userJDBC.insertUser(syncSystemUserToUser(su, newrole));
                    subjectJDBC.insertSubject(syncSystemUserToSubject(su));
                    // TODO 緊急聯絡人
                    // TODO 異常

                });
    }

    private User syncSystemUserToUser(SystemUser su, Role newrole) {

        User user = new User();
        // sequence, id, name, tenant_id, source, meta

        // TODO 密碼

        user.setId(su.getUserAccount());
        user.setName(su.getUserDisplayName());
        user.setTenantId("TTABO");
        user.setSource(Source.CREATE);

        // unit_ids, role_ids, emails, mobilephones, cards, permission_ids
//        user.setUnitIds(null);

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

//        種族註記，0：未填寫，1：漢族，2：客家，3：原住民，4：外籍

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
        YN brainAttack = su.getWithBrainAttack();
        if(su.getWithBrainAttack() == YN.Y)
            personalHistoryTypes.add(PersonalHistoryType.STROKE);
        YN diabetesMellitus = su.getWithDiabetesMellitus();
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
        subject.setTenantId("TTABO");

        subject.setCreateTime(su.getCreateTime());
        subject.setCreateBy("TTABO");
        subject.setUpdateTime(su.getCreateTime());

//                    updateby, status
        subject.setUpdateBy("TTABO");
        subject.setStatus(ModelStatus.ENABLED);
        return subject;
    }

    public void syncLocationToUnit(){

        LocationJDBC locationJDBC = new LocationJDBC();
        UnitJDBC unitJDBC = new UnitJDBC();
        UnitMetaJDBC unitMetaJDBC = new UnitMetaJDBC();

        List<Location> locations = locationJDBC.getAllLocation(new Syncare1_GET_CONNECTION().getConn());

        System.out.println(locations.size());
        locations.forEach(l -> {
            System.out.println(l);
            if (l.getId().equals("") || l.getId() == null){

            }else{

                if(unitJDBC.getUnitById(l.getId()).getId() == null)
                    unitJDBC.insertUnit(convertLocationToUnit(l));
                if(unitMetaJDBC.getUnitMetaById(l.getId()).getUnitId() == null)
                    unitMetaJDBC.insertUnitMeta(convertLocationToUnitMeta(l));

                locationJDBC.updateLocation(new Syncare1_GET_CONNECTION().getConn(), l.getId());
            }
        });

        System.out.println("sync unit : " + locations.size() + " successful");

    }

    private UnitMeta convertLocationToUnitMeta(Location location) {

        UnitJDBC unitJDBC = new UnitJDBC();
        UnitMeta unitMeta = new UnitMeta();


        String unitId = location.getId();
        unitMeta.setUnitId(unitId);
        unitMeta.setUnitName(location.getName());

        if(unitId.length() > 7) {
            Unit parentUnit = unitJDBC.getUnitById(unitId.substring(0, 7));
            unitMeta.setUnitParentId(parentUnit.getId());
            unitMeta.setUnitParentName(parentUnit.getName());
        }

        unitMeta.setUnitStatus(ModelMgmtStatus.ENABLED); // ModelMgmtStatus
        unitMeta.setTenantId("DEFAULT_TENANT");

        if (unitId.length() > 6 )
            unitMeta.setCategory(getCategory(unitId.substring(0, 7)));
        else
            unitMeta.setCategory("台東市");
        unitMeta.setContact(location.getContact());
        unitMeta.setAddress(location.getAddress());
        unitMeta.setHomePhone(location.getPhone());
        unitMeta.setMobilePhone(location.getPhone());
        Date date = new Date();
        unitMeta.setCreateTime(date); // Date
        unitMeta.setCreateBy("systemAdmin");
        unitMeta.setUpdateTime(date); // Date
        unitMeta.setUpdateBy("systemAdmin");
        return unitMeta;
    }

    String getCategory(String category){

        Map<String, String> map = new HashMap<>();
        //        離島 : [1001411, 1001416]
        //        離島 : [綠島鄉, 蘭嶼鄉]
        map.put("1001411","離島");
        map.put("1001416","離島");

        //        台東市 : [1001401, 1001404]
        //        台東市 : [卑南鄉, 台東市]
        map.put("1001401","台東市");

        //        南迴線 : [太麻里鄉, 卑南鄉, 金峰鄉, 大武鄉, 達仁鄉]
        //        南迴線 : [1001414, 1001410, 1001409, 1001415, 1001404]
        map.put("1001404","南迴線");
        map.put("1001410","南迴線");
        map.put("1001409","南迴線");
        map.put("1001415","南迴線");
        map.put("1001404","南迴線");

        //        海線 : [長濱鄉, 東河鄉, 成功鎮]
        //        海線 : [1001402, 1001408, 1001407]
        map.put("1001402","海線");
        map.put("1001408","海線");
        map.put("1001407","海線");

        //        縱谷線 : [關山鎮, 海端鄉, 延平鄉, 鹿野鄉, 池上鄉]
        //        縱谷線 : [1001412, 1001403, 1001413, 1001405, 1001406]
        map.put("1001412","縱谷線");
        map.put("1001403","縱谷線");
        map.put("1001413","縱谷線");
        map.put("1001405","縱谷線");
        map.put("1001406","縱谷線");

        return  map.get(category);

    }

    private Unit convertLocationToUnit(Location location) {

        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = new Unit();

        String unitId = location.getId();
        unit.setId(unitId);
        unit.setName(location.getName());
        if(unitId.length() > 7){

            Unit parentUnit = unitJDBC.getUnitById(unitId.substring(0, 7));
            unit.setParentId(parentUnit.getId());
            unit.setParentName(parentUnit.getName());

        }
        unit.setTenantId("DEFAULT_TENANT");
        unit.setMeta(null);
        unit.setCreateTime(new Date());
        unit.setCreateBy("systemAdmin");
        unit.setUpdateTime(new Date());
        unit.setUpdateBy("systemAdmin");
        unit.setStatus(ModelStatus.ENABLED);

        return unit;
    }

    public void syncDevice(){

        DeviceSyncare1JDBC syncare1DeviceJDBC = new DeviceSyncare1JDBC();

        List<Device> syncare1Devices = syncare1DeviceJDBC.getAllDevice(new Syncare1_GET_CONNECTION().getConn());

        DeviceJDBC cipDeviceJDBC = new DeviceJDBC();

        syncare1Devices.forEach(sd -> {
            insertCIPDevice(cipDeviceJDBC, new CIP_GET_CONNECTION().getConn(), sd);
            syncare1DeviceJDBC.updateDevice(new Syncare1_GET_CONNECTION().getConn(), sd.getSerialNo());
        });

        System.out.println("sync devices total :" + syncare1Devices.size() + " successful");

    }

    private void insertCIPDevice(DeviceJDBC cipDeviceJDBC, Connection cip_conn, Device sd) {

        com.syntrontech.pmo.cip.model.Device device = new com.syntrontech.pmo.cip.model.Device();

        String serialNo = sd.getSerialNo();
        device.setId(serialNo);
        device.setName(sd.getName());
        device.setSerialNumber(sd.getSerialNo());
        device.setUnitId(sd.getLocation());
        device.setUnitName(sd.getSyntronLocationId());
        device.setTenantId("DEFAULT_TENANT");
        Date date = new Date();
        device.setCreateTime(new Date());
        device.setCreateBy("systemAdmin");
        device.setUpdateTime(date);
        device.setUpdateBy("systemAdmin");
        device.setStatus(ModelStatus.ENABLED);

        cipDeviceJDBC.insertDevice(cip_conn, device);

    }
}
