package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.Auth_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.SubjectJDBC;
import com.syntrontech.pmo.JDBC.measurement.AbnormalBloodPressureJDBC;
import com.syntrontech.pmo.JDBC.measurement.AbnormalBloodPressureLogJDBC;
import com.syntrontech.pmo.JDBC.measurement.BloodPressureHeartBeatJDBC;
import com.syntrontech.pmo.JDBC.measurement.MEASUREMENT_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.pmo.PmoResultJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordMappingJDBC;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.AbnormalBloodPressure;
import com.syntrontech.pmo.measurement.AbnormalBloodPressureLog;
import com.syntrontech.pmo.measurement.BloodPressureHeartBeat;
import com.syntrontech.pmo.measurement.common.BloodPressureCaseStatus;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SyncBloodPressureHeartBeat {

    private static Logger logger = LoggerFactory.getLogger(SyncBloodPressureHeartBeat.class);


    // 同步至 新的血壓心跳 異常追蹤 異常追蹤log
    void sync() {
        Connection authconn = new Auth_GET_CONNECTION().getConn();
        Connection cipconn = new CIP_GET_CONNECTION().getConn();
        Connection measurementconn = new MEASUREMENT_GET_CONNECTION().getConn();
        Connection syncare1conn = new Syncare1_GET_CONNECTION().getConn();

        UserValueRecordJDBC userValueRecordJDBC = new UserValueRecordJDBC();
        SubjectJDBC subjectJDBC = new SubjectJDBC();
        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();

        // 取出所有未同步量測紀錄數值 <body_value_record_id, UserValueRecordMappings>
        Map<Integer, List<UserValueRecordMapping>> userValueRecordMap = new UserValueRecordMappingJDBC()
                .getAllUserValueRecordMapping();

        List<SystemUser> systemUsers = systemUserJDBC.getAllSystemUser();

        List<String> unFindSubjects = new ArrayList<>();

        for (SystemUser su : systemUsers) {
            try {

                String userId = su.getUserAccount().toUpperCase().trim();
                Subject  subject = subjectJDBC.getOneSubject(cipconn, userId, userId);

                if (subject == null) {
                    unFindSubjects.add("cannt find subject :" + userId + ", " + su.getUserId());
                    continue;
                }

                // 取出該使用者所有血壓
                List<UserValueRecord> userValueRecords = userValueRecordJDBC.getOneBUserValueRecord(syncare1conn, su.getUserId());

                syncMeasurementBloodPressureHeartBeat(authconn, syncare1conn, measurementconn, userValueRecords, userValueRecordMap, subject, su, userValueRecordJDBC);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void syncMeasurementBloodPressureHeartBeat(
            Connection authconn,
            Connection syncare1conn,
            Connection conn,
            List<UserValueRecord> userValueRecords,
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

            try {
                BloodPressureHeartBeat bloodPressureHeartBeat = syncBloodPressureHeartBeat(authconn, syncare1conn, conn, userValueRecordMap, old, subject);

                if (bloodPressureHeartBeat != null) {
                    // 更新舊 UserValueRecord 資料狀態
                    userValueRecordJDBC.updateUserValueRecord(syncare1conn, old.getBodyValueRecordId());

                    // 連續兩筆紀錄為異常 做異常紀錄
                    if (bloodPressureHeartBeats.size() > 0) {
                        BloodPressureHeartBeat oldBloodPressureHeartBeat = bloodPressureHeartBeats.get(bloodPressureHeartBeats.size() - 1);

                        // 判斷是否為異常
                        if (isBloodPressureAbnormal(oldBloodPressureHeartBeat) && isBloodPressureAbnormal(bloodPressureHeartBeat)) {
                            AbnormalBloodPressure abnormalBloodPressure = abnormalBloodPressureJDBC
                                    .insertAbnormalBloodPressure(conn, turnNoarmalToAbnormal(bloodPressureHeartBeat, su));

                            // 尚未處理不需存log
                            if (su.getCaseStatus() != 0 || su.getCaseNote() != null)
                                abnormalBloodPressureLogJDBC
                                        .insert(conn, turnBloodPressureAbnormalToLog(abnormalBloodPressure, su));
                        }

                    }
                    pmoResultJDBC.insert(turnOldRecordsToPmoResult(old, bloodPressureHeartBeat.getSubjectId(), bloodPressureHeartBeat.getSequence(), MeasurementPMOType.BloodPressure));
                    updateUserValueRecordMapping(syncare1conn, userValueRecordMap, old.getBodyValueRecordId());
                    bloodPressureHeartBeats.add(bloodPressureHeartBeat);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }


    private BloodPressureHeartBeat syncBloodPressureHeartBeat(
            Connection authconn, Connection syncare1conn, Connection conn,
            Map<Integer, List<UserValueRecordMapping>> userValueRecordMap,
            UserValueRecord old, Subject subject) throws SQLException {

        // 取出該筆紀錄的值
        List<UserValueRecordMapping> recordValues = userValueRecordMap.get(old.getBodyValueRecordId());
        // 7 收縮壓 8 舒張壓 9 心跳
        Map<Integer, List<UserValueRecordMapping>> values = recordValues.stream()
                .collect(Collectors.groupingBy(s -> s.getMapping().getTypeId()));

        BloodPressureHeartBeatJDBC bloodPressureHeartBeatJDBC = new BloodPressureHeartBeatJDBC();

        System.out.println("UserValueRecord sync => " + old);
        if (values.keySet().size() < 3) {
            return null;
        }
        BloodPressureHeartBeat bph = turnOldRecordsToBloodPressureHeartBeat(authconn, old, values, subject);
        BloodPressureHeartBeat bloodPressureHeartBeat = bloodPressureHeartBeatJDBC
                .insertBloodPressureHeartBeat(conn, bph);

        // 更新舊 UserValueRecordMapping 資料狀態
        recordValues.forEach(mapping -> new UserValueRecordMappingJDBC()
                .updateUserValueRecordMapping(syncare1conn, mapping.getUserValueRecordMappingId())
        );
        return bloodPressureHeartBeat;
    }

    private BloodPressureHeartBeat turnOldRecordsToBloodPressureHeartBeat(
            Connection authconn,
            UserValueRecord old, Map<Integer, List<UserValueRecordMapping>> values, Subject subject) throws SQLException {

        BloodPressureHeartBeat bloodPressureHeartBeat = new BloodPressureHeartBeat();

        System.out.println("UserValueRecordMapping sync => " + values);

        // 7 收縮壓 8 舒張壓 9 心跳
        // systolic_pressure, diastolic_pressure, heart_rate
        List<UserValueRecordMapping> systolicPressure = values.get(7);
        Integer systolicvalue = 0;
        if (systolicPressure.size() > 0) {
            String systolicPressureValue = systolicPressure.get(0).getRecordValue();
            try {
                systolicvalue = Double.valueOf(systolicPressureValue).intValue();
            } catch (NumberFormatException e) {
                logger.warn("systolicPressure NumberFormatException" + e.getMessage());
            }
        }
        bloodPressureHeartBeat.setSystolicPressure(systolicvalue);

        List<UserValueRecordMapping> diastolicPressures = values.get(8);
        Integer diastolic = 0;
        if (diastolicPressures.size() > 0) {
            String diastolicStr = diastolicPressures.get(0).getRecordValue();
            try {
                diastolic = Double.valueOf(diastolicStr).intValue();
            } catch (NumberFormatException e) {
                logger.warn("diastolicPressures NumberFormatException" + e.getMessage());
            }
        }
        bloodPressureHeartBeat.setDiastolicPressure(diastolic);
        // constraints:nullable: false
        List<UserValueRecordMapping> heartRates = values.get(9);
        int hearBeat = 0;
        if (heartRates.size() > 0) {
            String heartRate = heartRates.get(0).getRecordValue();
            try {
                hearBeat = Double.valueOf(heartRate).intValue();
            } catch (NumberFormatException e) {
                logger.warn("diastolicPressures NumberFormatException" + e.getMessage());
            }
        }

        bloodPressureHeartBeat.setHeartRate(hearBeat);

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
        Unit unit = unitJDBC.getUnitById(authconn, old.getLocationId());
        if (unit == null || unit.getId() == null) {
            unit = getOtherUnit(unit);
        }
        bloodPressureHeartBeat.setUnitId(unit.getId());
        bloodPressureHeartBeat.setUnitName(unit.getName());
        bloodPressureHeartBeat.setParentUnitId(unit.getParentId());
        bloodPressureHeartBeat.setParentUnitName(unit.getParentName());

        return bloodPressureHeartBeat;
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
        if (systemUser.getCaseUpdateDate() != null)
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

    private AbnormalBloodPressureLog turnBloodPressureAbnormalToLog(AbnormalBloodPressure abnormalBloodPressure, SystemUser su) {
        AbnormalBloodPressureLog abnormalBloodPressureLog = new AbnormalBloodPressureLog();

        // abnormal_blood_pressure_squence, case_status, subject_id, subject_name
        abnormalBloodPressureLog.setAbnormalBloodPressureSquence(abnormalBloodPressure.getBloodPressureSeq());
        abnormalBloodPressureLog.setCaseStatus(abnormalBloodPressure.getCaseStatus());
        abnormalBloodPressureLog.setSubjectId(abnormalBloodPressure.getSubjectId());
        abnormalBloodPressureLog.setSubjectName(abnormalBloodPressure.getSubjectName());

        // case_creator_user_id, case_creator_user_name, case_description, recordtime, tenant_id
        abnormalBloodPressureLog.setCaseCreatorUserId("TTSHB");
        abnormalBloodPressureLog.setCaseCreatorUserName("TTSHB");
        abnormalBloodPressureLog.setCaseDescription(su.getCaseNote());
        System.out.println(su);

        if (su.getCaseUpdateDate() == null)
            abnormalBloodPressureLog.setChangeCaseStatusTime(su.getCaseUpdateDate());
        else
            abnormalBloodPressureLog.setChangeCaseStatusTime(abnormalBloodPressure.getLastChangeCaseStatusTime());

        abnormalBloodPressureLog.setTenantId(abnormalBloodPressure.getTenantId());

        if (abnormalBloodPressureLog.getChangeCaseStatusTime() == null)
            abnormalBloodPressureLog.setChangeCaseStatusTime(new Date());

        System.out.println(abnormalBloodPressureLog);
        return abnormalBloodPressureLog;

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

    public Unit getOtherUnit(Unit unit) {

        unit.setId("100140102310");
        unit.setName("其他");
        unit.setTenantId("TTSHB");
        unit.setParentId("1001401");
        unit.setParentName("台東市");
        return unit;
    }

    private void updateUserValueRecordMapping(
            Connection syncare1conn,
            Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, int bodyValueRecordId) {

        List<UserValueRecordMapping> values = userValueRecordMap.get(bodyValueRecordId);
        values.forEach(v -> new UserValueRecordMappingJDBC().updateUserValueRecordMapping(syncare1conn, v.getUserValueRecordMappingId()));

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
        if (systolic > 150 || systolic == 150 || (age < 80 && systolic > 140 || systolic == 140)) {
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


}
