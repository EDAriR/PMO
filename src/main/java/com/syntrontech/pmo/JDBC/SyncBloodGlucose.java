package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.Auth_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.SubjectJDBC;
import com.syntrontech.pmo.JDBC.measurement.BloodGlucoseJDBC;
import com.syntrontech.pmo.JDBC.measurement.MEASUREMENT_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.pmo.PmoResultJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordMappingJDBC;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.BloodGlucose;
import com.syntrontech.pmo.measurement.common.GlucoseType;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.pmo.MeasurementPMOType;
import com.syntrontech.pmo.pmo.PmoResult;
import com.syntrontech.pmo.pmo.PmoStatus;
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
import java.util.Map;

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
        Map<Integer, List<UserValueRecordMapping>> userValueRecordMap = userValueRecordMappingJDBC.getAllUserValueRecordMapping();

        List<SystemUser> systemUsers = systemUserJDBC.getAllSystemUser();

        List<String> unFindSubjects = new ArrayList<>();

        for (SystemUser su : systemUsers) {
            try {

                String userId = su.getUserAccount().toUpperCase().trim();
                Subject subject = subjectJDBC.getOneSubject(cipconn, userId, userId);

                if (subject == null) {
                    unFindSubjects.add("cannt find subject :" + userId + ", " + su.getUserId());
                    continue;
                }

                // 取出該使用者所有血壓
                List<UserValueRecord> userValueRecords = userValueRecordJDBC.getOneBUserValueRecord(syncare1conn, su.getUserId());

                // 同步新的血糖
                syncBloodGlucose(userValueRecordMappingJDBC, authconn, syncare1conn, measurementconn, su, userValueRecordMap, subject, userValueRecordJDBC);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void syncBloodGlucose(
            UserValueRecordMappingJDBC userValueRecordMappingJDBC,
            Connection authconn, Connection syncare1conn, Connection measurementconn,
            SystemUser su, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, Subject subject, UserValueRecordJDBC userValueRecordJDBC) {

        // sync 136 BG 取出使用者所有血糖
        BloodGlucoseJDBC bloodGlucoseJDBC = new BloodGlucoseJDBC();
        PmoResultJDBC pmoResultJDBC = new PmoResultJDBC();

        List<UserValueRecord> userBGValueRecords = userValueRecordJDBC.getOneBGUserValueRecord(su.getUserId());
        userBGValueRecords.forEach(bg -> {
            try {
                System.out.println("subject " + subject);
                BloodGlucose bloodGlucose = bloodGlucoseJDBC
                        .insert(measurementconn, turnValueRecordToBloodGlucose(authconn, bg, subject, userValueRecordMap));
                userValueRecordJDBC.updateUserValueRecord(syncare1conn, bg.getBodyValueRecordId());
                userValueRecordMappingJDBC.updateUserValueRecordMapping(syncare1conn, userValueRecordMap, bg.getBodyValueRecordId());

                // PMO_result
                System.out.println(bloodGlucose);
                pmoResultJDBC.insert(turnOldRecordsToPmoResult(bg, bloodGlucose.getSubjectId(), bloodGlucose.getSequence(), MeasurementPMOType.BloodGlucose));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private BloodGlucose turnValueRecordToBloodGlucose(Connection authconn, UserValueRecord bg, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap) throws SQLException {

        List<UserValueRecordMapping> values = userValueRecordMap.get(bg.getBodyValueRecordId());
        Integer glucoseValue = 0;
        if (values.size() == 0)
            return null;

        if (values.size() > 1) {
            for (UserValueRecordMapping v : values) {
                if (v.getMapping().getTypeId() == 2033) {
                    // 全部都是飯後
                } else if (v.getMapping().getTypeId() == 136) {
                    try {
                        glucoseValue = Integer.valueOf(v.getRecordValue());
                    } catch (NumberFormatException e) {
                        logger.warn("turnValueRecordToBloodGlucose : " + e.getMessage());
                    }
                }

            }
        }

        BloodGlucose testBloodGlucose = new BloodGlucose();

        testBloodGlucose.setGlucose(glucoseValue);
        // type_id = 2033
        testBloodGlucose.setGlucoseType(GlucoseType.POSTPRANDIAL_BLOOD_GLUCOSE);

        // recordtime, latitude, longitude
        testBloodGlucose.setRecordTime(bg.getRecordDate());
        testBloodGlucose.setLatitude("0");
        testBloodGlucose.setLongitude("0");


        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        testBloodGlucose.setStatus(MeasurementStatusType.EXISTED);
        testBloodGlucose.setCreateTime(bg.getUpdateDate());
        testBloodGlucose.setCreateBy("TTSHB");
        testBloodGlucose.setTenantId("TTSHB");

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
        Unit unit = unitJDBC.getUnitById(authconn, bg.getLocationId());
        if (unit == null || unit.getId() == null) {
            unit = unitJDBC.getOtherUnit(unit);
        }
        testBloodGlucose.setUnitId(unit.getId());
        testBloodGlucose.setUnitName(unit.getName());
        testBloodGlucose.setParentUnitId(unit.getParentId());
        testBloodGlucose.setParentUnitName(unit.getParentName());

        return testBloodGlucose;
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

}
