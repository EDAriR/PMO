package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.Auth_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.SubjectJDBC;
import com.syntrontech.pmo.JDBC.measurement.BloodGlucoseJDBC;
import com.syntrontech.pmo.JDBC.measurement.MEASUREMENT_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordMappingJDBC;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.BloodGlucose;
import com.syntrontech.pmo.measurement.common.GlucoseType;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import com.syntrontech.pmo.syncare1.model.UserValueRecordMapping;
import com.syntrontech.pmo.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SyncBloodGlucose {

    private static Logger logger = LoggerFactory.getLogger(SyncBloodGlucose.class);


    public void sync() {

        Connection authconn = new Auth_GET_CONNECTION().getConn();
        Connection cipconn = new CIP_GET_CONNECTION().getConn();
        Connection measurementconn = new MEASUREMENT_GET_CONNECTION().getConn();
        Connection syncare1conn = new Syncare1_GET_CONNECTION().getConn();

        UserValueRecordJDBC userValueRecordJDBC = new UserValueRecordJDBC();
        SubjectJDBC subjectJDBC = new SubjectJDBC();
        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();

        // 取出所有未同步量測紀錄數值 <body_value_record_id, UserValueRecordMappings>
        UserValueRecordMappingJDBC userValueRecordMappingJDBC = new UserValueRecordMappingJDBC();
        BloodGlucoseJDBC bloodGlucoseJDBC = new BloodGlucoseJDBC();

        List<String> unFindSubjects = new ArrayList<>();
        List<UserValueRecord> userValueRecords= userValueRecordJDBC.getAllBGUserValueRecord();

        for (UserValueRecord record:userValueRecords){
            try {
                SystemUser su = systemUserJDBC.getSystemUserById(syncare1conn, String.valueOf(record.getSystemUser().getUserId()));
                if(su == null || su.getUserAccount() == null)
                    continue;

                String userId = su.getUserAccount().toUpperCase().trim();
                Subject subject = subjectJDBC.getOneSubject(cipconn, userId, userId);

                if (subject == null || subject.getId() == null){
                    unFindSubjects.add("system user id=["+ su.getUserId() +
                            "], account=[" + su.getUserAccount() + "]," +
                    " name=[" + su.getUserDisplayName() + "]");
                    continue;
                }

                UserValueRecordMapping userValueRecordMaping = userValueRecordMappingJDBC
                        .getBGUserValueRecordMappingByRecordId(syncare1conn, record.getBodyValueRecordId());

                BloodGlucose bloodGlucose = bloodGlucoseJDBC
                        .insert(measurementconn, turnValueRecordToBloodGlucose(authconn, record, subject, userValueRecordMaping));
                userValueRecordJDBC.updateUserValueRecord(syncare1conn, record.getBodyValueRecordId());
                userValueRecordMappingJDBC.updateUserValueRecordMapping(syncare1conn, userValueRecordMaping.getUserValueRecordMappingId());

                    System.out.println("****************************************************");
                    System.out.println(record);
                    System.out.println(userValueRecordMaping);

                System.out.println("new bloodGlucose : " + bloodGlucose);
                    System.out.println("****************************************************");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
        authconn.close();
        cipconn.close();
        measurementconn.close();
        syncare1conn.close();
                unFindSubjects.forEach(s -> System.out.println(s));
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }

    private BloodGlucose turnValueRecordToBloodGlucose(Connection authconn, UserValueRecord bg, Subject subject,
                                                       UserValueRecordMapping userValueRecordMap)
            throws SQLException {

        Integer glucoseValue = Integer.valueOf(userValueRecordMap.getRecordValue());

        BloodGlucose bloodGlucose = new BloodGlucose();

        bloodGlucose.setGlucose(glucoseValue);
        // type_id = 2033
        bloodGlucose.setGlucoseType(GlucoseType.POSTPRANDIAL_BLOOD_GLUCOSE);

        // recordtime, latitude, longitude
        bloodGlucose.setRecordTime(bg.getRecordDate());
        bloodGlucose.setLatitude("0");
        bloodGlucose.setLongitude("0");


        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        bloodGlucose.setStatus(MeasurementStatusType.EXISTED);
        bloodGlucose.setCreateTime(bg.getUpdateDate());
        bloodGlucose.setCreateBy("TTSHB");
        bloodGlucose.setTenantId("TTSHB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        bloodGlucose.setSubjectSeq(subject.getSequence());
        bloodGlucose.setSubjectId(subject.getId());
        bloodGlucose.setSubjectName(subject.getName());
        bloodGlucose.setSubjectGender(subject.getGender());
        bloodGlucose.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), bg.getRecordDate()));
        bloodGlucose.setSubjectUserId(subject.getUserId());
        bloodGlucose.setSubjectUserName(subject.getName());

        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(authconn, bg.getLocationId());
        if (unit == null || unit.getId() == null) {
            unit = unitJDBC.getOtherUnit(unit);
        }
        bloodGlucose.setUnitId(unit.getId());
        bloodGlucose.setUnitName(unit.getName());
        bloodGlucose.setParentUnitId(unit.getParentId());
        bloodGlucose.setParentUnitName(unit.getParentName());

        return bloodGlucose;
    }

//    private PmoResult turnOldRecordsToPmoResult(UserValueRecord old, String userId, long sequence, MeasurementPMOType type) {
//
//        PmoResult pmoResult = new PmoResult();
//
//        pmoResult.setUserId(userId);
//        // measurement_type
//        pmoResult.setMeasurementType(type);
//        // record_id
//        pmoResult.setRecordId(sequence);
//        // result
//        pmoResult.setResult(old.getPmoResult());
//        // status
//        PmoStatus pmoStatus = PmoStatus.NotSync;
//        UserValueRecord.RecordPmoStatus ss = old.getPmoStatus();
//        if (ss == UserValueRecord.RecordPmoStatus.Sync)
//            pmoStatus = PmoStatus.Sync;
//        if (ss == UserValueRecord.RecordPmoStatus.NotSync)
//            pmoStatus = PmoStatus.NotSync;
//        if (ss == UserValueRecord.RecordPmoStatus.Error)
//            pmoStatus = PmoStatus.Error;
//        pmoResult.setPmoStatus(pmoStatus);
//        // synctime
//        pmoResult.setSynctime(old.getUpdateDate());
//
//        return pmoResult;
//
//    }

}
