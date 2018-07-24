package com.syntrontech.pmo.JDBC.measurement;

import com.syntrontech.pmo.measurement.BloodGlucose;
import com.syntrontech.pmo.measurement.common.GlucoseType;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.GenderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BloodGlucoseJDBC {

    private static Logger logger = LoggerFactory.getLogger(BloodGlucoseJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM blood_glucose ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO blood_glucose " +
            "(sequence, glucose, glucose_type, recordtime, latitude, longitude," +
            " status, createtime, createby, tenant_id, device_mac_address, " +
            "subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name," +
            "rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id)"
            + "VALUES (nextval('blood_pressure_heartbeat_sequence_seq'), ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?);";

    private static final String GET_SEQUENCE = "SELECT MAX(sequence) FROM blood_glucose;";

    public static void main(String[] args) {
//        BloodGlucose
        BloodGlucoseJDBC bloodGlucoseJDBC = new BloodGlucoseJDBC();

//        List<BloodGlucose> bloodGlucoses = bloodGlucoseJDBC.getALl();

//        System.out.println(bloodGlucoses.size());

        bloodGlucoseJDBC.insert(bloodGlucoseJDBC.getTestBloodGlucose());
    }

    public BloodGlucose getTestBloodGlucose() {

        BloodGlucose testBloodGlucose= new BloodGlucose();

        testBloodGlucose.setGlucose(10);
        testBloodGlucose.setGlucoseType(GlucoseType.POSTPRANDIAL_BLOOD_GLUCOSE);

        // recordtime, latitude, longitude
        testBloodGlucose.setRecordTime(new java.util.Date());
        testBloodGlucose.setLatitude("0");
        testBloodGlucose.setLongitude("0");


        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        testBloodGlucose.setStatus(MeasurementStatusType.EXISTED);
        testBloodGlucose.setCreateTime(new Date());
        testBloodGlucose.setCreateBy("userJDBCTest");
        testBloodGlucose.setTenantId("TTSHB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        testBloodGlucose.setSubjectSeq((long)25);
        testBloodGlucose.setSubjectId("userJDBCTest");
        testBloodGlucose.setSubjectName("userJDBCTest");
        testBloodGlucose.setSubjectGender(GenderType.MALE);
        testBloodGlucose.setSubjectAge(0);
        testBloodGlucose.setSubjectUserId("userJDBCTest");
        testBloodGlucose.setSubjectUserName("userJDBCTest");

        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        testBloodGlucose.setUnitId("100140102310");
        testBloodGlucose.setUnitName("其他");
        testBloodGlucose.setParentUnitId("1001401");
        testBloodGlucose.setParentUnitName("台東市");

        return testBloodGlucose;
    }

    public BloodGlucose insert(BloodGlucose bloodGlucose){

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // systolic_pressure, diastolic_pressure, heart_rate
            pstmt.setInt(1, bloodGlucose.getGlucose());
            pstmt.setString(2, bloodGlucose.getGlucoseType().toString());

            // recordtime, latitude, longitude
            pstmt.setTimestamp(3, new Timestamp(bloodGlucose.getRecordTime().getTime()));
            pstmt.setString(4, bloodGlucose.getLatitude());
            pstmt.setString(5, bloodGlucose.getLongitude());

            // status, createtime, createby, tenant_id, device_mac_address
            pstmt.setString(6, bloodGlucose.getStatus().toString());
            pstmt.setTimestamp(7, new Timestamp(bloodGlucose.getCreateTime().getTime()));
            pstmt.setString(8, bloodGlucose.getCreateBy());
            pstmt.setString(9, bloodGlucose.getTenantId());
            pstmt.setString(10, bloodGlucose.getDeviceMacAddress());

            // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
            pstmt.setLong(11, bloodGlucose.getSubjectSeq());
            pstmt.setString(12, bloodGlucose.getSubjectId());
            pstmt.setString(13, bloodGlucose.getSubjectName());
            pstmt.setString(14, bloodGlucose.getSubjectGender().toString());
            pstmt.setInt(15, bloodGlucose.getSubjectAge());
            pstmt.setString(16, bloodGlucose.getSubjectUserId());
            pstmt.setString(17, bloodGlucose.getSubjectUserName());

            // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
            if(bloodGlucose.getRuleSeq() != null){
                pstmt.setLong(18, bloodGlucose.getRuleSeq());
            }else {
                pstmt.setNull(18, Types.BIGINT);
            }
            if(bloodGlucose.getRuleDescription() != null){
                pstmt.setString(19, bloodGlucose.getRuleDescription());
            }else {
                pstmt.setNull(19, Types.VARCHAR);
            }

            pstmt.setString(20, bloodGlucose.getUnitId());
            pstmt.setString(21, bloodGlucose.getUnitName());
            pstmt.setString(22, bloodGlucose.getParentUnitId());
            pstmt.setString(23, bloodGlucose.getParentUnitName());
            pstmt.setString(24, bloodGlucose.getDeviceId());

//            System.out.println("sql => " + pstmt);
            logger.info("sql => " + pstmt);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bloodGlucose.setSequence(rs.getLong(1));
                }
                rs.close();
            }

            if(bloodGlucose.getSequence() == null){

                pstmt = conn.prepareStatement(GET_SEQUENCE);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        bloodGlucose.setSequence(rs.getLong(1));
                    }
                }
            }


        } catch (SQLException e) {
            logger.warn("create bloodGlucose fail =>" + bloodGlucose);

            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);

                e.printStackTrace();
            }

        }
        logger.info(bloodGlucose.toString());
        return bloodGlucose;
    }

    public List<BloodGlucose> getALl(){

        List<BloodGlucose> bloodGlucoses = new ArrayList<>();

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    BloodGlucose bloodGlucose = new BloodGlucose();

                    bloodGlucose.setSequence(rs.getLong("sequence"));

                    // systolic_pressure, diastolic_pressure, heart_rate
                    bloodGlucose.setGlucose(rs.getInt("glucose"));

                    String glucose_type = rs.getString("glucose_type");
                    GlucoseType glucoseType = glucose_type != null ?  GlucoseType.valueOf(glucose_type) : null;
                    bloodGlucose.setGlucoseType(glucoseType);

                    // recordtime, latitude, longitude
                    bloodGlucose.setRecordTime(rs.getTimestamp("recordtime"));
                    bloodGlucose.setLatitude(rs.getString("latitude"));
                    bloodGlucose.setLongitude(rs.getString("longitude"));


                    // status, createtime, createby, tenant_id, device_mac_address
                    // private MeasurementStatusType status;
                    bloodGlucose.setStatus(MeasurementStatusType.valueOf(rs.getString("status")));
                    bloodGlucose.setCreateTime(rs.getTimestamp("createtime"));
                    bloodGlucose.setCreateBy(rs.getString("createby"));
                    bloodGlucose.setTenantId(rs.getString("tenant_id"));
                    bloodGlucose.setDeviceMacAddress(rs.getString("device_mac_address"));

                    // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
                    bloodGlucose.setSubjectSeq(rs.getLong("subject_seq"));
                    bloodGlucose.setSubjectId(rs.getString("subject_id"));
                    bloodGlucose.setSubjectName(rs.getString("subject_name"));
                    bloodGlucose.setSubjectGender(GenderType.valueOf(rs.getString("subject_gender")));
                    bloodGlucose.setSubjectAge(rs.getInt("subject_age"));
                    bloodGlucose.setSubjectUserId(rs.getString("subject_user_id"));
                    bloodGlucose.setSubjectUserName(rs.getString("subject_user_name"));

                    // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
                    bloodGlucose.setRuleSeq(rs.getLong("rule_seq"));
                    bloodGlucose.setRuleDescription(rs.getString("rule_description"));
                    bloodGlucose.setUnitId(rs.getString("unit_id"));
                    bloodGlucose.setUnitName(rs.getString("unit_name"));
                    bloodGlucose.setParentUnitId(rs.getString("parent_unit_id"));
                    bloodGlucose.setParentUnitName(rs.getString("parent_unit_name"));
                    bloodGlucose.setDeviceId(rs.getString("device_id"));

                    bloodGlucoses.add(bloodGlucose);
                }
            }
        } catch (SQLException e){
//            logger.debug("get all fail" + conn + " || " + pstmt);

            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }


        return bloodGlucoses;
    }
}
