package com.syntrontech.pmo.JDBC.measurement;

import com.syntrontech.pmo.measurement.AbnormalBloodPressure;
import com.syntrontech.pmo.measurement.common.BloodPressureCaseStatus;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.GenderType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbnormalBloodPressureJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM abnormal_blood_pressure ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO abnormal_blood_pressure " +
            "(sequence, id, name, mac_address, serial_number, unit_id, " +
            "unit_name, tenant_id, status, createtime, createby, updatetime, updateby) "

            + "VALUES (nextval('device_sequence_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//                    blood_pressure_seq, subject_seq, subject_id, subject_name, subject_gender
//                    subject_age, subject_user_id, subject_user_name, systolic_pressure, diastolic_pressure, heart_rate
//                    recordtime, createby, case_status, last_change_case_status_time, unit_id
//                    tenant_id, device_mac_address, unit_name, status, rule_description
//                    parent_unit_id, parent_unit_name, device_id

    public static void main(String[] args) {

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();

        AbnormalBloodPressureJDBC abnormalBloodPressureJDBC = new AbnormalBloodPressureJDBC();

        List<AbnormalBloodPressure> ss = abnormalBloodPressureJDBC.getAllAbnormalBloodPressure();

        System.out.println("ss size:" + ss.size());
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

//                    blood_pressure_seq, subject_seq, subject_id, subject_name, subject_gender
                    abnormalBloodPressure.setBloodPressureSeq(rs.getLong("blood_pressure_seq"));
                    abnormalBloodPressure.setSubjectSeq(rs.getLong("subject_seq"));
                    abnormalBloodPressure.setSubjectId(rs.getString("subject_id"));
                    abnormalBloodPressure.setSubjectName(rs.getString("subject_name"));

                    abnormalBloodPressure.setSubjectGender(GenderType.valueOf(rs.getString("subject_gender")));

//                    subject_age, subject_user_id, subject_user_name, systolic_pressure, diastolic_pressure, heart_rate
                    abnormalBloodPressure.setSubjectAge(rs.getInt("subject_age"));
                    abnormalBloodPressure.setSubjectUserId(rs.getString("subject_user_id"));
                    abnormalBloodPressure.setSubjectUserName(rs.getString("subject_user_name"));
                    abnormalBloodPressure.setSystolicPressure(rs.getInt("systolic_pressure"));
                    abnormalBloodPressure.setDiastolicPressure(rs.getInt("diastolic_pressure"));
                    abnormalBloodPressure.setHeartRate(rs.getInt("heart_rate"));

//                    recordtime, createby, case_status, last_change_case_status_time, unit_id

                    abnormalBloodPressure.setRecordtime(rs.getTimestamp("recordtime"));
                    abnormalBloodPressure.setCreateBy(rs.getString("createby"));
                    abnormalBloodPressure.setCaseStatus(BloodPressureCaseStatus.valueOf(rs.getString("case_status")));
                    abnormalBloodPressure.setLastChangeCaseStatusTime(rs.getTimestamp("last_change_case_status_time"));
                    abnormalBloodPressure.setUnitId(rs.getString("unit_id"));

//                    tenant_id, device_mac_address, unit_name, status, rule_description
                    abnormalBloodPressure.setTenantId(rs.getString("tenant_id"));
                    abnormalBloodPressure.setDeviceMacAddress(rs.getString("device_mac_address"));
                    abnormalBloodPressure.setUnitName(rs.getString("unit_name"));
//                private MeasurementStatusType status;
                    abnormalBloodPressure.setStatus(MeasurementStatusType.valueOf(rs.getString("status")));
                    abnormalBloodPressure.setRuleDescription(rs.getString(""));

//                    parent_unit_id, parent_unit_name, device_id
                    abnormalBloodPressure.setParentUnitId(rs.getString("parent_unit_id"));
                    abnormalBloodPressure.setParentUnitName(rs.getString("parent_unit_name"));
                    abnormalBloodPressure.setDeviceId(rs.getString("device_id"));

                    abnormalBloodPressures.add(abnormalBloodPressure);
                }
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        } finally

        {

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


}
