package com.syntrontech.pmo.JDBC.measurement;

import com.syntrontech.pmo.measurement.AbnormalBloodPressure;
import com.syntrontech.pmo.measurement.common.BloodPressureCaseStatus;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.GenderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbnormalBloodPressureJDBC {

    private static Logger logger = LoggerFactory.getLogger(AbnormalBloodPressureJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM abnormal_blood_pressure ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO abnormal_blood_pressure " +
            "(sequence, blood_pressure_seq, subject_seq, subject_id, subject_name, subject_gender," +
            " subject_age, subject_user_id, subject_user_name, systolic_pressure, diastolic_pressure, heart_rate," +
            "recordtime, createby, case_status, last_change_case_status_time, unit_id, " +
            "tenant_id, device_mac_address, unit_name, status, rule_description," +
            "parent_unit_id, parent_unit_name, device_id)"

            + "VALUES (nextval('abnormal_blood_pressure_sequence_seq'), ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, " +
            "?, ?, ?);";


    private static final String GET_ONE = "SELECT * FROM abnormal_blood_pressure WHERE id=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";

    public static void main(String[] args) {

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();

        AbnormalBloodPressureJDBC abnormalBloodPressureJDBC = new AbnormalBloodPressureJDBC();

        List<AbnormalBloodPressure> ss = abnormalBloodPressureJDBC.getAllAbnormalBloodPressure();

        System.out.println("ss size:" + ss.size());

        System.out.println(abnormalBloodPressureJDBC.insertAbnormalBloodPressure(abnormalBloodPressureJDBC.getTestAbnormalBloodPressure()));
    }

    AbnormalBloodPressure getTestAbnormalBloodPressure(){

        AbnormalBloodPressure abnormalBloodPressure = new AbnormalBloodPressure();

        // blood_pressure_seq, subject_seq, subject_id, subject_name, subject_gender
        abnormalBloodPressure.setBloodPressureSeq(58L);
        abnormalBloodPressure.setSubjectSeq(1L);
        abnormalBloodPressure.setSubjectId("userJDBCTest");
        abnormalBloodPressure.setSubjectName("userJDBCTest");

        abnormalBloodPressure.setSubjectGender(GenderType.MALE);

        // subject_age, subject_user_id, subject_user_name, systolic_pressure, diastolic_pressure, heart_rate
        abnormalBloodPressure.setSubjectAge(0);
        abnormalBloodPressure.setSubjectUserId("userJDBCTest");
        abnormalBloodPressure.setSubjectUserName("userJDBCTest");
        abnormalBloodPressure.setSystolicPressure(100);
        abnormalBloodPressure.setDiastolicPressure(100);
        abnormalBloodPressure.setHeartRate(100);

        // recordtime, createby, case_status, last_change_case_status_time, unit_id
        abnormalBloodPressure.setRecordtime(new Date());
        abnormalBloodPressure.setCreateBy("userJDBCTest");
        abnormalBloodPressure.setCaseStatus(BloodPressureCaseStatus.NOT_YET);
        abnormalBloodPressure.setLastChangeCaseStatusTime(new Date());
        abnormalBloodPressure.setUnitId("100140102310");

        // tenant_id, device_mac_address, unit_name, status, rule_description
        abnormalBloodPressure.setTenantId("TTSHB");
        abnormalBloodPressure.setDeviceMacAddress("70:8b:cd:9c:36:c4");
        abnormalBloodPressure.setUnitName("");


        // private MeasurementStatusType status;
        abnormalBloodPressure.setStatus(MeasurementStatusType.EXISTED);
        abnormalBloodPressure.setRuleDescription(null);

        // parent_unit_id, parent_unit_name, device_id
        abnormalBloodPressure.setParentUnitId("1001401");
        abnormalBloodPressure.setParentUnitName("台東市");
        abnormalBloodPressure.setDeviceId("87878");

        return abnormalBloodPressure;
    }


    public AbnormalBloodPressure insertAbnormalBloodPressure(AbnormalBloodPressure abnormalBloodPressure){

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // blood_pressure_seq, subject_seq, subject_id, subject_name, subject_gender
            pstmt.setLong(1, abnormalBloodPressure.getBloodPressureSeq());
            pstmt.setLong(2, abnormalBloodPressure.getSubjectSeq());
            pstmt.setString(3, abnormalBloodPressure.getSubjectId());
            pstmt.setString(4, abnormalBloodPressure.getSubjectName());
            pstmt.setString(5, abnormalBloodPressure.getSubjectGender().toString());
            // subject_age, subject_user_id, subject_user_name, systolic_pressure, diastolic_pressure, heart_rate
            pstmt.setLong(6, abnormalBloodPressure.getSubjectAge());
            pstmt.setString(7, abnormalBloodPressure.getSubjectUserId());
            pstmt.setString(8, abnormalBloodPressure.getSubjectUserName());
            pstmt.setInt(9, abnormalBloodPressure.getSystolicPressure());
            pstmt.setInt(10, abnormalBloodPressure.getDiastolicPressure());
            pstmt.setInt(11, abnormalBloodPressure.getHeartRate());

            // recordtime, createby, case_status, last_change_case_status_time, unit_id
            pstmt.setTimestamp(12, new java.sql.Timestamp(abnormalBloodPressure.getRecordtime().getTime()));
            pstmt.setString(13, abnormalBloodPressure.getCreateBy());
            pstmt.setString(14, abnormalBloodPressure.getCaseStatus().toString());
            pstmt.setTimestamp(15, new java.sql.Timestamp(abnormalBloodPressure.getLastChangeCaseStatusTime().getTime()));
            pstmt.setString(16, abnormalBloodPressure.getUnitId());

            // tenant_id, device_mac_address, unit_name, status, rule_description
            pstmt.setString(17, abnormalBloodPressure.getTenantId());

            String macAddress = abnormalBloodPressure.getDeviceMacAddress();
            if(macAddress != null)
                pstmt.setString(18, abnormalBloodPressure.getDeviceMacAddress());
            else
                pstmt.setNull(18, Types.VARCHAR);

            pstmt.setString(19, abnormalBloodPressure.getUnitName());
            pstmt.setString(20, abnormalBloodPressure.getStatus().toString());

            String ruleDescription = abnormalBloodPressure.getRuleDescription();
            if(ruleDescription != null)
                pstmt.setString(21, abnormalBloodPressure.getRuleDescription());
            else
                pstmt.setNull(21, Types.VARCHAR);

            // parent_unit_id, parent_unit_name, device_id
            pstmt.setString(22, abnormalBloodPressure.getParentUnitId());
            pstmt.setString(23, abnormalBloodPressure.getParentUnitName());
            pstmt.setString(24, abnormalBloodPressure.getDeviceId());

            System.out.println("sql => " + pstmt);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    abnormalBloodPressure.setSequence(rs.getLong(1));
                }
                rs.close();
            }

//            logger.info("create AbnormalBloodPressure successful => " + abnormalBloodPressure);

        } catch (SQLException e) {
            logger.debug("create AbnormalBloodPressure fail =>" + abnormalBloodPressure);
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
//                e.printStackTrace();
            }

        }

        return abnormalBloodPressure;
    }


    public List<AbnormalBloodPressure> getAllAbnormalBloodPressure() {

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        List<AbnormalBloodPressure> abnormalBloodPressures = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    AbnormalBloodPressure abnormalBloodPressure = new AbnormalBloodPressure();

                    abnormalBloodPressure.setSequence(rs.getLong("sequence"));

                    // blood_pressure_seq, subject_seq, subject_id, subject_name, subject_gender
                    abnormalBloodPressure.setBloodPressureSeq(rs.getLong("blood_pressure_seq"));
                    abnormalBloodPressure.setSubjectSeq(rs.getLong("subject_seq"));
                    abnormalBloodPressure.setSubjectId(rs.getString("subject_id"));
                    abnormalBloodPressure.setSubjectName(rs.getString("subject_name"));

                    abnormalBloodPressure.setSubjectGender(GenderType.valueOf(rs.getString("subject_gender")));

                    // subject_age, subject_user_id, subject_user_name, systolic_pressure, diastolic_pressure, heart_rate
                    abnormalBloodPressure.setSubjectAge(rs.getInt("subject_age"));
                    abnormalBloodPressure.setSubjectUserId(rs.getString("subject_user_id"));
                    abnormalBloodPressure.setSubjectUserName(rs.getString("subject_user_name"));
                    abnormalBloodPressure.setSystolicPressure(rs.getInt("systolic_pressure"));
                    abnormalBloodPressure.setDiastolicPressure(rs.getInt("diastolic_pressure"));
                    abnormalBloodPressure.setHeartRate(rs.getInt("heart_rate"));

                    // recordtime, createby, case_status, last_change_case_status_time, unit_id
                    abnormalBloodPressure.setRecordtime(rs.getTimestamp("recordtime"));
                    abnormalBloodPressure.setCreateBy(rs.getString("createby"));
                    abnormalBloodPressure.setCaseStatus(BloodPressureCaseStatus.valueOf(rs.getString("case_status")));
                    abnormalBloodPressure.setLastChangeCaseStatusTime(rs.getTimestamp("last_change_case_status_time"));
                    abnormalBloodPressure.setUnitId(rs.getString("unit_id"));

                    // tenant_id, device_mac_address, unit_name, status, rule_description
                    abnormalBloodPressure.setTenantId(rs.getString("tenant_id"));
                    abnormalBloodPressure.setDeviceMacAddress(rs.getString("device_mac_address"));
                    abnormalBloodPressure.setUnitName(rs.getString("unit_name"));


                    // private MeasurementStatusType status;
                    abnormalBloodPressure.setStatus(MeasurementStatusType.valueOf(rs.getString("status")));
                    abnormalBloodPressure.setRuleDescription(rs.getString("rule_description"));

                    // parent_unit_id, parent_unit_name, device_id
                    abnormalBloodPressure.setParentUnitId(rs.getString("parent_unit_id"));
                    abnormalBloodPressure.setParentUnitName(rs.getString("parent_unit_name"));
                    abnormalBloodPressure.setDeviceId(rs.getString("device_id"));

                    abnormalBloodPressures.add(abnormalBloodPressure);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return abnormalBloodPressures;
    }

    public AbnormalBloodPressure getAbnormalBloodPressureById(String id) {
        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        AbnormalBloodPressure abnormalBloodPressure = new AbnormalBloodPressure();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
            System.out.println(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    abnormalBloodPressure.setSequence(rs.getLong("sequence"));

                    // blood_pressure_seq, subject_seq, subject_id, subject_name, subject_gender
                    abnormalBloodPressure.setBloodPressureSeq(rs.getLong("blood_pressure_seq"));
                    abnormalBloodPressure.setSubjectSeq(rs.getLong("subject_seq"));
                    abnormalBloodPressure.setSubjectId(rs.getString("subject_id"));
                    abnormalBloodPressure.setSubjectName(rs.getString("subject_name"));

                    abnormalBloodPressure.setSubjectGender(GenderType.valueOf(rs.getString("subject_gender")));

                    // subject_age, subject_user_id, subject_user_name, systolic_pressure, diastolic_pressure, heart_rate
                    abnormalBloodPressure.setSubjectAge(rs.getInt("subject_age"));
                    abnormalBloodPressure.setSubjectUserId(rs.getString("subject_user_id"));
                    abnormalBloodPressure.setSubjectUserName(rs.getString("subject_user_name"));
                    abnormalBloodPressure.setSystolicPressure(rs.getInt("systolic_pressure"));
                    abnormalBloodPressure.setDiastolicPressure(rs.getInt("diastolic_pressure"));
                    abnormalBloodPressure.setHeartRate(rs.getInt("heart_rate"));

                    // recordtime, createby, case_status, last_change_case_status_time, unit_id
                    abnormalBloodPressure.setRecordtime(rs.getTimestamp("recordtime"));
                    abnormalBloodPressure.setCreateBy(rs.getString("createby"));
                    abnormalBloodPressure.setCaseStatus(BloodPressureCaseStatus.valueOf(rs.getString("case_status")));
                    abnormalBloodPressure.setLastChangeCaseStatusTime(rs.getTimestamp("last_change_case_status_time"));
                    abnormalBloodPressure.setUnitId(rs.getString("unit_id"));

                    // tenant_id, device_mac_address, unit_name, status, rule_description
                    abnormalBloodPressure.setTenantId(rs.getString("tenant_id"));
                    abnormalBloodPressure.setDeviceMacAddress(rs.getString("device_mac_address"));
                    abnormalBloodPressure.setUnitName(rs.getString("unit_name"));


                    // private MeasurementStatusType status;
                    abnormalBloodPressure.setStatus(MeasurementStatusType.valueOf(rs.getString("status")));
                    abnormalBloodPressure.setRuleDescription(rs.getString("rule_description"));

                    // parent_unit_id, parent_unit_name, device_id
                    abnormalBloodPressure.setParentUnitId(rs.getString("parent_unit_id"));
                    abnormalBloodPressure.setParentUnitName(rs.getString("parent_unit_name"));
                    abnormalBloodPressure.setDeviceId(rs.getString("device_id"));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return abnormalBloodPressure;
    }


}
