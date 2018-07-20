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
import java.util.List;

public class BloodPressureHeartBeatJDBC {

    private static Logger logger = LoggerFactory.getLogger(BloodPressureHeartBeatJDBC.class);

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

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();

        BloodPressureHeartBeatJDBC abnormalBloodPressureJDBC = new BloodPressureHeartBeatJDBC();

        List<BloodPressureHeartBeat> ss = abnormalBloodPressureJDBC.getAllBloodPressureHeartBeat();

        System.out.println("ss size:" + ss.size());
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
            pstmt.setTimestamp(7, new Timestamp(bloodPressureHeartBeat.getCreateTime().getTime()));
            pstmt.setString(8, bloodPressureHeartBeat.getCreateBy());
            pstmt.setString(9, bloodPressureHeartBeat.getTenantId());
            pstmt.setString(10, bloodPressureHeartBeat.getDeviceMacAddress());

            // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
            pstmt.setLong(11, bloodPressureHeartBeat.getSubjectSeq());
            pstmt.setString(12, bloodPressureHeartBeat.getSubjectId());
            pstmt.setString(13, bloodPressureHeartBeat.getSubjectName());
            pstmt.setString(14, bloodPressureHeartBeat.getSubjectGender().toString());
            pstmt.setInt(15, bloodPressureHeartBeat.getSubjectAge());
            pstmt.setString(16, bloodPressureHeartBeat.getSubjectUserId());
            pstmt.setString(17, bloodPressureHeartBeat.getSubjectUserName());

            // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
            pstmt.setLong(18, bloodPressureHeartBeat.getRuleSeq());
            pstmt.setString(19, bloodPressureHeartBeat.getRuleDescription());
            pstmt.setString(20, bloodPressureHeartBeat.getUnitId());
            pstmt.setString(21, bloodPressureHeartBeat.getUnitName());
            pstmt.setString(22, bloodPressureHeartBeat.getParentUnitId());
            pstmt.setString(23, bloodPressureHeartBeat.getParentUnitName());
            pstmt.setString(24, bloodPressureHeartBeat.getDeviceId());


            logger.info("sql => " + pstmt);
            pstmt.executeUpdate();
            logger.info("create bloodPressureHeartBeat successful => " + bloodPressureHeartBeat);


            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bloodPressureHeartBeat.setSequence(rs.getLong(1));
                }
                rs.close();
            }

        } catch (SQLException e) {
            logger.debug("create bloodPressureHeartBeat fail =>" + bloodPressureHeartBeat);
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);

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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return bloodPressureHeartBeats;
    }
}
