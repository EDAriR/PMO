package com.syntrontech.pmo.JDBC.measurement;

import com.syntrontech.pmo.measurement.AbnormalBloodPressure;
import com.syntrontech.pmo.measurement.BloodPressureHeartBeat;
import com.syntrontech.pmo.measurement.common.BloodPressureCaseStatus;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.GenderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BloodPressureHeartBeatJDBC {

//    private static Logger logger = LoggerFactory.getLogger(BloodPressureHeartBeatJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM blood_pressure_heartbeat ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO blood_pressure_heartbeat " +
            "(sequence, systolic_pressure, diastolic_pressure, heart_rate, recordtime, latitude, longitude," +
            " status, createtime, createby, tenant_id, device_mac_address, " +
            "subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name," +
            "rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id)"

            + "VALUES (nextval('blood_pressure_heartbeat_sequence_seq'), ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?);";

    // systolic_pressure, diastolic_pressure, heart_rate
    // recordtime, latitude, longitude
    // status, createtime, createby, tenant_id, device_mac_address
    // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
    // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id

    private static final String GET_ONE = "SELECT * FROM blood_pressure_heartbeat WHERE sequence=? and tenant_id='DEFAULT_TENANT'" +
            " AND status='ENABLED';";

    public static void main(String[] args) {

        BloodPressureHeartBeatJDBC bloodPressureHeartBeatJDBC = new BloodPressureHeartBeatJDBC();

        List<BloodPressureHeartBeat> ss = bloodPressureHeartBeatJDBC.getAllBloodPressureHeartBeat();

        System.out.println("ss size:" + ss.size());

        System.out.println(bloodPressureHeartBeatJDBC.insertBloodPressureHeartBeat(bloodPressureHeartBeatJDBC.getTestBloodPressureHeartBeat()));

    }

    public BloodPressureHeartBeat insertBloodPressureHeartBeat(BloodPressureHeartBeat bloodPressureHeartBeat){

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // systolic_pressure, diastolic_pressure, heart_rate
            pstmt.setInt(1, bloodPressureHeartBeat.getSystolicPressure());
            pstmt.setInt(2, bloodPressureHeartBeat.getDiastolicPressure());
            pstmt.setInt(3, bloodPressureHeartBeat.getHeartRate());

            // recordtime, latitude, longitude
            pstmt.setTimestamp(4, new Timestamp(bloodPressureHeartBeat.getRecordTime().getTime()));
            pstmt.setString(5, bloodPressureHeartBeat.getLatitude());
            pstmt.setString(6, bloodPressureHeartBeat.getLongitude());

            // status, createtime, createby, tenant_id, device_mac_address
            pstmt.setString(7, bloodPressureHeartBeat.getStatus().toString());
            pstmt.setTimestamp(8, new Timestamp(bloodPressureHeartBeat.getCreateTime().getTime()));
            pstmt.setString(9, bloodPressureHeartBeat.getCreateBy());
            pstmt.setString(10, bloodPressureHeartBeat.getTenantId());
            pstmt.setString(11, bloodPressureHeartBeat.getDeviceMacAddress());

            // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
            pstmt.setLong(12, bloodPressureHeartBeat.getSubjectSeq());
            pstmt.setString(13, bloodPressureHeartBeat.getSubjectId());
            pstmt.setString(14, bloodPressureHeartBeat.getSubjectName());
            pstmt.setString(15, bloodPressureHeartBeat.getSubjectGender().toString());
            pstmt.setInt(16, bloodPressureHeartBeat.getSubjectAge());
            pstmt.setString(17, bloodPressureHeartBeat.getSubjectUserId());
            pstmt.setString(18, bloodPressureHeartBeat.getSubjectUserName());

            // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
            if(bloodPressureHeartBeat.getRuleSeq() != null){
                pstmt.setLong(19, bloodPressureHeartBeat.getRuleSeq());
            }else {
                pstmt.setNull(19, Types.BIGINT);
            }
            if(bloodPressureHeartBeat.getRuleDescription() != null){
                pstmt.setString(20, bloodPressureHeartBeat.getRuleDescription());
            }else {
                pstmt.setNull(20, Types.VARCHAR);
            }
            pstmt.setString(21, bloodPressureHeartBeat.getUnitId());
            pstmt.setString(22, bloodPressureHeartBeat.getUnitName());
            pstmt.setString(23, bloodPressureHeartBeat.getParentUnitId());
            pstmt.setString(24, bloodPressureHeartBeat.getParentUnitName());
            pstmt.setString(25, bloodPressureHeartBeat.getDeviceId());

//            logger.info("sql => " + pstmt);
            pstmt.executeUpdate();
//            logger.info("create bloodPressureHeartBeat successful => " + bloodPressureHeartBeat);


            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bloodPressureHeartBeat.setSequence(rs.getLong(1));
                }
                rs.close();
            }

        } catch (SQLException e) {
//            logger.debug("create bloodPressureHeartBeat fail =>" + bloodPressureHeartBeat);
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

        return bloodPressureHeartBeat;
    }


    public List<BloodPressureHeartBeat> getAllBloodPressureHeartBeat() {

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        List<BloodPressureHeartBeat> bloodPressureHeartBeats = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    BloodPressureHeartBeat bloodPressureHeartBeat = new BloodPressureHeartBeat();

                    bloodPressureHeartBeat.setSequence(rs.getLong("sequence"));

                    // systolic_pressure, diastolic_pressure, heart_rate
                    bloodPressureHeartBeat.setSystolicPressure(rs.getInt("systolic_pressure"));
                    bloodPressureHeartBeat.setDiastolicPressure(rs.getInt("diastolic_pressure"));
                    bloodPressureHeartBeat.setHeartRate(rs.getInt("heart_rate"));


                    // recordtime, latitude, longitude
                    bloodPressureHeartBeat.setRecordTime(rs.getTimestamp("recordtime"));
                    bloodPressureHeartBeat.setLatitude(rs.getString("latitude"));
                    bloodPressureHeartBeat.setLongitude(rs.getString("longitude"));


                    // status, createtime, createby, tenant_id, device_mac_address
                    // private MeasurementStatusType status;
                    bloodPressureHeartBeat.setStatus(MeasurementStatusType.valueOf(rs.getString("status")));
                    bloodPressureHeartBeat.setCreateTime(rs.getTimestamp("createtime"));
                    bloodPressureHeartBeat.setCreateBy(rs.getString("createby"));
                    bloodPressureHeartBeat.setTenantId(rs.getString("tenant_id"));
                    bloodPressureHeartBeat.setDeviceMacAddress(rs.getString("device_mac_address"));

                    // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
                    bloodPressureHeartBeat.setSubjectSeq(rs.getLong("subject_seq"));
                    bloodPressureHeartBeat.setSubjectId(rs.getString("subject_id"));
                    bloodPressureHeartBeat.setSubjectName(rs.getString("subject_name"));
                    bloodPressureHeartBeat.setSubjectGender(GenderType.valueOf(rs.getString("subject_gender")));
                    bloodPressureHeartBeat.setSubjectAge(rs.getInt("subject_age"));
                    bloodPressureHeartBeat.setSubjectUserId(rs.getString("subject_user_id"));
                    bloodPressureHeartBeat.setSubjectUserName(rs.getString("subject_user_name"));

                    // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
                    bloodPressureHeartBeat.setRuleSeq(rs.getLong("rule_seq"));
                    bloodPressureHeartBeat.setRuleDescription(rs.getString("rule_description"));
                    bloodPressureHeartBeat.setUnitId(rs.getString("unit_id"));
                    bloodPressureHeartBeat.setUnitName(rs.getString("unit_name"));
                    bloodPressureHeartBeat.setParentUnitId(rs.getString("parent_unit_id"));
                    bloodPressureHeartBeat.setParentUnitName(rs.getString("parent_unit_name"));
                    bloodPressureHeartBeat.setDeviceId(rs.getString("device_id"));

                    bloodPressureHeartBeats.add(bloodPressureHeartBeat);
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
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return bloodPressureHeartBeats;
    }

    BloodPressureHeartBeat getTestBloodPressureHeartBeat(){

        BloodPressureHeartBeat bloodPressureHeartBeat = new BloodPressureHeartBeat();

        // systolic_pressure, diastolic_pressure, heart_rate
        bloodPressureHeartBeat.setSystolicPressure(100);
        bloodPressureHeartBeat.setDiastolicPressure(100);
        bloodPressureHeartBeat.setHeartRate(100);


        // recordtime, latitude, longitude
        bloodPressureHeartBeat.setRecordTime(new Date());
        bloodPressureHeartBeat.setLatitude("0");
        bloodPressureHeartBeat.setLongitude("0");


        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        bloodPressureHeartBeat.setStatus(MeasurementStatusType.EXISTED);
        bloodPressureHeartBeat.setCreateTime(new Date());
        bloodPressureHeartBeat.setCreateBy("TTSHB");
        bloodPressureHeartBeat.setTenantId("TTSHB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        bloodPressureHeartBeat.setSubjectSeq((long)1);
        bloodPressureHeartBeat.setSubjectId("userJDBCTest");
        bloodPressureHeartBeat.setSubjectName("userJDBCTest");
        bloodPressureHeartBeat.setSubjectGender(GenderType.MALE);
        bloodPressureHeartBeat.setSubjectAge(0);
        bloodPressureHeartBeat.setSubjectUserId("userJDBCTest");
        bloodPressureHeartBeat.setSubjectUserName("userJDBCTest");

        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        bloodPressureHeartBeat.setUnitId("100140102310");
        bloodPressureHeartBeat.setUnitName("");
        bloodPressureHeartBeat.setParentUnitId("1001401");
        bloodPressureHeartBeat.setParentUnitName("");

        return bloodPressureHeartBeat;

    }
}
