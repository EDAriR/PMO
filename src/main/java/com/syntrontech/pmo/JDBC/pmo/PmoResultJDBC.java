package com.syntrontech.pmo.JDBC.pmo;

import com.syntrontech.pmo.pmo.MeasurementPMOType;
import com.syntrontech.pmo.pmo.PmoResult;
import com.syntrontech.pmo.pmo.PmoStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Date;

public class PmoResultJDBC {

    private static Logger logger = LoggerFactory.getLogger(PmoUserJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM pmo_result WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO pmo_result " +
            "(sequence, user_id, measurement_type, record_id, result, status, synctime, tenant_id) "
            + "VALUES (nextval('pmo_user_sequence_seq'), ?, ?, ?, ?, ?. ?, ?);";

    private static final String GET_ONE = "SELECT * FROM pmo_result WHERE user_id=? and record_id=? and measurement_type = ? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";


    public static void main(String[] args) {

        PmoResultJDBC pmoResultJDBC = new PmoResultJDBC();

        PmoResult pmoResult = pmoResultJDBC.insert(pmoResultJDBC.getTestPmoResult());

        System.out.println(pmoResult);

        PmoResult pmoResult2 = pmoResultJDBC.getPmoResultByUserIdAndRecordIdAndTenantId(pmoResult.getUserId(), pmoResult.getRecordId(), pmoResult.getMeasurementType());

        System.out.println("getPmoResultByUserIdAndRecordIdAndTenantId");
        System.out.println(pmoResult2);

    }

    private PmoResult getPmoResultByUserIdAndRecordIdAndTenantId(String userId, Long recordId, MeasurementPMOType type) {

        Connection conn = new PMO_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;
        PmoResult pmoResult = new PmoResult();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, userId);
            pstmt.setLong(2, recordId);
            pstmt.setString(3, type.toString());
            logger.info(pstmt.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    pmoResult.setUserId(rs.getString("user_id"));

                    MeasurementPMOType pmoType = rs.getString("measurement_type") == null ? null : MeasurementPMOType.valueOf(rs.getString("measurement_type"));
                    pmoResult.setMeasurementType(pmoType);
                    pmoResult.setRecordId(rs.getLong("record_id"));
                    pmoResult.setResult(rs.getString("result"));

                    PmoStatus status =  rs.getString("status") != null ? PmoStatus.valueOf(rs.getString("status")): null;
                    pmoResult.setPmoStatus(status);
                    pmoResult.setSynctime(rs.getTimestamp("synctime"));
                    pmoResult.setTenantId(rs.getString("tenant_id"));
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
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }
        }
        logger.info("getPmoUserById successful ==> " + pmoResult);

        return pmoResult;

    }

    public PmoResult insert(PmoResult pmoResult){

        Connection conn = new PMO_GET_CONNECTION().getConn();

        PmoResult old = getPmoResultByUserIdAndRecordIdAndTenantId(pmoResult.getUserId(), pmoResult.getRecordId(), pmoResult.getMeasurementType());

        if (old != null){
            logger.info("getPmoUserById PmoUser = " + old);
            if (old.getUserId() != null && !old.getUserId().equals(""))
                return old;
        }

        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // sequence, user_id, measurement_type, record_id, result, status, synctime, tenant_id
            pstmt.setString(1, pmoResult.getUserId());
            pstmt.setString(2, pmoResult.getMeasurementType().toString());
            pstmt.setLong(3, pmoResult.getRecordId());
            pstmt.setString(4, pmoResult.getResult());
            pstmt.setString(5, pmoResult.getPmoStatus().toString());
            pstmt.setTimestamp(6, new Timestamp(pmoResult.getSynctime().getTime()));
            pstmt.setString(7, pmoResult.getTenantId());

            logger.info(pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.debug("insert  pmoUser fail" + conn + " || " + pstmt);
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
        logger.info("create pmoResult successful ==> " + pmoResult);

        return pmoResult;
    }

    public PmoResult getTestPmoResult() {

        PmoResult testPmoResult = new PmoResult();

        // sequence, user_id, measurement_type, record_id, result, status, synctime, tenant_id
        testPmoResult.setUserId("TTSHB");
        testPmoResult.setMeasurementType(MeasurementPMOType.BloodPressure);
        testPmoResult.setRecordId((long)1);
        testPmoResult.setResult("666");

        testPmoResult.setPmoStatus(PmoStatus.NotSync);
        testPmoResult.setSynctime(new Date());
        testPmoResult.setTenantId("TTSHB");

        return testPmoResult;
    }
}
