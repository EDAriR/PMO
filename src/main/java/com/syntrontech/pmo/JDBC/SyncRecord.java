package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.Auth_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.cip.SubjectJDBC;
import com.syntrontech.pmo.JDBC.measurement.BloodPressureHeartBeatJDBC;
import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.SubjectJDBC;
import com.syntrontech.pmo.JDBC.measurement.BloodPressureHeartBeatJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordMappingJDBC;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.BloodPressureHeartBeat;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.*;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import com.syntrontech.pmo.syncare1.model.UserValueRecordMapping;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;
import com.syntrontech.pmo.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.syntrontech.pmo.syncare1.model.common.Service.*;
import static com.syntrontech.pmo.syncare1.model.common.Service.RecordType.*;

public class SyncRecord {

    private static Logger logger = LoggerFactory.getLogger(SyncRecord.class);

    public void sync() {

        UserValueRecordJDBC valueRecordJDBC = new UserValueRecordJDBC();

        UserValueRecordMappingJDBC valueMappingJDBC = new UserValueRecordMappingJDBC();

        List<UserValueRecord> allValueRecords = valueRecordJDBC.getAllUserValueRecord();
        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();
        Connection syncare1conn = new Syncare1_GET_CONNECTION().getConn();
        Connection cipconn = new CIP_GET_CONNECTION().getConn();
        Connection authconn = new Auth_GET_CONNECTION().getConn();
        
        try {
        	for (UserValueRecord record : allValueRecords) {
        		SystemUser systemUser = systemUserJDBC.getSystemUserById(syncare1conn, String.valueOf(record.getSystemUser().getUserId()));
        		RecordType type = valueOf(record.getColumnType());
        		
        		switch (type) {
        			case A:
        				break;
        			case B:
        				insertBP(cipconn, authconn, syncare1conn, cipconn, record, systemUser, valueMappingJDBC);
        				break;
        			case BG:
        				break;
                }
            	valueRecordJDBC.updateUserValueRecord(syncare1conn, record.getBodyValueRecordId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
        	
        } finally {

            try {
            	cipconn.close();
            	syncare1conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + syncare1conn);
                e.printStackTrace();
            }

        }

    }

    private BloodPressureHeartBeat insertBP(Connection cipconn, Connection authconn, Connection syncare1conn, Connection conn, UserValueRecord record, SystemUser systemUser, UserValueRecordMappingJDBC valueMappingJDBC) throws SQLException {

        BloodPressureHeartBeatJDBC jdbc = new BloodPressureHeartBeatJDBC();

        List<UserValueRecordMapping> values = valueMappingJDBC.getUserValueRecordMappingByRecordId(record.getBodyValueRecordId());

        BloodPressureHeartBeat bloodPressureHeartBeat = getBloodPressureHeartBeat(cipconn, authconn, conn, record, systemUser, values);
        if(bloodPressureHeartBeat == null )
            return null;
        bloodPressureHeartBeat = jdbc.insertBloodPressureHeartBeat(conn, bloodPressureHeartBeat);

        values.forEach(v -> valueMappingJDBC.updateUserValueRecordMapping(syncare1conn, v.getUserValueRecordMappingId()));

        return bloodPressureHeartBeat;
    }

    private BloodPressureHeartBeat getBloodPressureHeartBeat(Connection cipconn, Connection authconn, Connection conn, UserValueRecord record, SystemUser systemUser, List<UserValueRecordMapping> values) throws SQLException {

        String account = systemUser.getUserAccount();
        SubjectJDBC subjectJDBC = new SubjectJDBC();
        Subject subject = subjectJDBC.getOneSubject(cipconn, account, account);
        if (subject == null || subject.getId() == null) {
            // TODO
            subject = getSubject(systemUser);
            subjectJDBC.insertSubject(conn, subject);
        }

        if(subject.getSequence() == null){
            return null;
        }

        BloodPressureHeartBeat bloodPressureHeartBeat = new BloodPressureHeartBeat();

        System.out.println("UserValueRecordMapping sync => " + values);

        // 7 收縮壓 8 舒張壓 9 心跳
        // systolic_pressure, diastolic_pressure, heart_rate
        Integer systolicvalue = 0;
        Integer diastolic = 0;
        // constraints:nullable: false
        Integer hearBeat = 0;

        for (UserValueRecordMapping value : values) {
            try {
                switch (value.getMapping().getTypeId()) {
                    case 7:
                        systolicvalue = (int) Double.parseDouble(value.getRecordValue());
                        break;
                    case 9:
                        diastolic = (int) Double.parseDouble(value.getRecordValue());
                        break;
                    case 8:
                        hearBeat = (int) Double.parseDouble(value.getRecordValue());
                        break;
                }

            } catch (NumberFormatException e) {
                logger.warn("hearBeat NumberFormatException" + e.getMessage());
            }
        }

        bloodPressureHeartBeat.setSystolicPressure(systolicvalue);
        bloodPressureHeartBeat.setDiastolicPressure(diastolic);
        bloodPressureHeartBeat.setHeartRate(hearBeat);

        // recordtime, latitude, longitude
        bloodPressureHeartBeat.setRecordTime(record.getRecordDate());

        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        bloodPressureHeartBeat.setStatus(MeasurementStatusType.EXISTED);
        bloodPressureHeartBeat.setCreateTime(record.getUpdateDate());
        bloodPressureHeartBeat.setCreateBy(systemUser.getUserAccount());
        bloodPressureHeartBeat.setTenantId("TTSHB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        bloodPressureHeartBeat.setSubjectSeq(subject.getSequence());
        bloodPressureHeartBeat.setSubjectId(subject.getId());
        bloodPressureHeartBeat.setSubjectName(subject.getName());
        bloodPressureHeartBeat.setSubjectGender(subject.getGender());
        bloodPressureHeartBeat.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), record.getRecordDate()));
        bloodPressureHeartBeat.setSubjectUserId(subject.getUserId());
        bloodPressureHeartBeat.setSubjectUserName(subject.getName());


        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(authconn, subject.getUnitId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
            bloodPressureHeartBeat.setUnitId(record.getLocationId());
            bloodPressureHeartBeat.setUnitName(record.getLocationName());
        }
        bloodPressureHeartBeat.setUnitId(unit.getId());
        bloodPressureHeartBeat.setUnitName(unit.getName());
        bloodPressureHeartBeat.setParentUnitId(unit.getParentId());
        bloodPressureHeartBeat.setParentUnitName(unit.getParentName());

        return bloodPressureHeartBeat;

    }

    private Subject getSubject(SystemUser su) {

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
