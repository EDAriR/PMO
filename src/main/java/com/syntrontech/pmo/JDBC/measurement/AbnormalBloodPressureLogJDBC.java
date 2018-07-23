package com.syntrontech.pmo.JDBC.measurement;

import com.syntrontech.pmo.measurement.AbnormalBloodPressureLog;
import com.syntrontech.pmo.measurement.common.BloodPressureCaseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbnormalBloodPressureLogJDBC {

//    private static Logger logger = LoggerFactory.getLogger(AbnormalBloodPressureLogJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM abnormal_blood_pressure ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO abnormal_blood_pressure " +
            "(sequence, abnormal_blood_pressure_squence, case_status, subject_id, subject_name," +
            " case_creator_user_id, case_creator_user_name, case_description, recordtime, tenant_id)"

            + "VALUES (nextval('abnormal_blood_pressure_sequence_seq'), ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?);";
    // abnormal_blood_pressure_squence, case_status, subject_id, subject_name
    // case_creator_user_id, case_creator_user_name, case_description, recordtime, tenant_id

    public static void main(String[] args) {

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();

        AbnormalBloodPressureLogJDBC abnormalBloodPressureLogJDBC = new AbnormalBloodPressureLogJDBC();

        List<AbnormalBloodPressureLog> ss = abnormalBloodPressureLogJDBC.getAllAbnormalBloodPressureLog();

        System.out.println("ss size:" + ss.size());

        abnormalBloodPressureLogJDBC.insertAbnormalBloodPressure(ss.get(0));
    }

    public void insertAbnormalBloodPressure(AbnormalBloodPressureLog abnormalBloodPressureLog){

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);
            // abnormal_blood_pressure_squence, case_status, subject_id, subject_name

            pstmt.setLong(1, abnormalBloodPressureLog.getAbnormalBloodPressureSquence());
            pstmt.setString(2, abnormalBloodPressureLog.getCaseStatus().toString());
            pstmt.setString(3, abnormalBloodPressureLog.getSubjectId());
            pstmt.setString(4, abnormalBloodPressureLog.getSubjectName());
            // case_creator_user_id, case_creator_user_name, case_description, recordtime, tenant_id
            pstmt.setString(5, abnormalBloodPressureLog.getCaseCreatorUserId());
            pstmt.setString(6, abnormalBloodPressureLog.getCaseCreatorUserName());
            pstmt.setString(7, abnormalBloodPressureLog.getCaseDescription());
            pstmt.setTimestamp(8, new Timestamp(abnormalBloodPressureLog.getChangeCaseStatusTime().getTime()));
            pstmt.setString(9, abnormalBloodPressureLog.getTenantId());

            System.out.println("sql => " + pstmt);
            pstmt.executeUpdate();
//            logger.info("create AbnormalBloodPressureLog successful => " + abnormalBloodPressureLog);

        } catch (SQLException e) {
//            logger.debug("create AbnormalBloodPressureLog fail =>" + abnormalBloodPressureLog);

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
    }

    public List<AbnormalBloodPressureLog> getAllAbnormalBloodPressureLog() {

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        List<AbnormalBloodPressureLog> abnormalBloodPressureLogs = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    AbnormalBloodPressureLog abnormalBloodPressureLog = new AbnormalBloodPressureLog();

                    abnormalBloodPressureLog.setSequence(rs.getLong("sequence"));

                    // abnormal_blood_pressure_squence, case_status, subject_id, subject_name
                    abnormalBloodPressureLog.setAbnormalBloodPressureSquence(rs.getLong("abnormal_blood_pressure_squence"));
                    abnormalBloodPressureLog.setCaseStatus(BloodPressureCaseStatus.valueOf(rs.getString("case_status")));
                    abnormalBloodPressureLog.setSubjectId(rs.getString("subject_id"));
                    abnormalBloodPressureLog.setSubjectName(rs.getString("subject_name"));

                    // case_creator_user_id, case_creator_user_name, case_description, recordtime, tenant_id
                    abnormalBloodPressureLog.setCaseCreatorUserId(rs.getString("case_creator_user_id"));
                    abnormalBloodPressureLog.setCaseCreatorUserName(rs.getString("case_creator_user_name"));
                    abnormalBloodPressureLog.setCaseDescription(rs.getString("case_description"));
                    abnormalBloodPressureLog.setChangeCaseStatusTime(rs.getTimestamp("recordtime"));
                    abnormalBloodPressureLog.setTenantId(rs.getString("tenant_id"));

                    abnormalBloodPressureLogs.add(abnormalBloodPressureLog);
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

        return abnormalBloodPressureLogs;
    }

}
