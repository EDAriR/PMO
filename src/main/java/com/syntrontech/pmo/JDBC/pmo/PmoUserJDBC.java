package com.syntrontech.pmo.JDBC.pmo;

import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.pmo.PmoResult;
import com.syntrontech.pmo.pmo.PmoStatus;
import com.syntrontech.pmo.pmo.PmoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Date;

public class PmoUserJDBC {

    private static Logger logger = LoggerFactory.getLogger(PmoUserJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM pmo_user WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO pmo_user " +
            "(sequence, user_id, pmo_password, status, synctime, tenant_id) "
            + "VALUES (nextval('pmo_user_sequence_seq'), ?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM pmo_user WHERE user_id=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";

    public static void main(String[] args) {


        PmoUserJDBC pmoUserJDBC = new PmoUserJDBC();

        PmoUser pmoUser = pmoUserJDBC.insert(pmoUserJDBC.getTestPmoUser());

        System.out.println(pmoUser);

        PmoUser pmoUser2 = pmoUserJDBC.getPmoUserById(pmoUser.getUserId());

        System.out.println("getPmoUserById");

        System.out.println(pmoUser2);

    }



    public PmoUser insert(PmoUser pmoUser){

        Connection conn = new PMO_GET_CONNECTION().getConn();

        PmoUser old = getPmoUserById(pmoUser.getUserId());

        if (old != null){
            logger.info("getPmoUserById PmoUser = " + old);
            if (old.getUserId() != null && !old.getUserId().equals(""))
                return old;
        }

        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            pstmt.setString(1, pmoUser.getUserId());
            pstmt.setString(2, pmoUser.getPmoPassword());
            pstmt.setString(3, pmoUser.getPmoStatus().toString());
            if(pmoUser.getSynctime() != null)
                pstmt.setTimestamp(4, new Timestamp(pmoUser.getSynctime().getTime()));
            else
                pstmt.setNull(4,Types.TIMESTAMP);
            pstmt.setString(5, pmoUser.getTenantId());

            logger.info(pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.debug("insert  pmoUser fail" + " \n" + pstmt);
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
        logger.info("create pmoUser successful ==> " + pmoUser);

        return pmoUser;
    }

    private PmoUser getPmoUserById(String userId) {


        Connection conn = new PMO_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;
        PmoUser pmoUser = new PmoUser();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, userId);
            logger.info(pstmt.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    // user_id, pmoPassword, pmoStatus, synctime, tenant_id
                    pmoUser.setUserId(rs.getString("user_id"));
                    pmoUser.setPmoPassword(rs.getString("pmo_password"));

                    PmoStatus status =  rs.getString("status") != null ? PmoStatus.valueOf(rs.getString("status")): null;
                    pmoUser.setPmoStatus(status);
                    pmoUser.setSynctime(rs.getTimestamp("synctime"));
                    pmoUser.setTenantId(rs.getString("tenant_id"));
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
        logger.info("getPmoUserById successful ==> " + pmoUser);

        return pmoUser;

    }

    public PmoUser getTestPmoUser() {

        PmoUser testPmoUser = new PmoUser();

        // user_id, pmoPassword, pmoStatus, synctime, tenant_id
        testPmoUser.setUserId("TTSHB");
        testPmoUser.setPmoPassword(null);

        testPmoUser.setPmoStatus(PmoStatus.NotSync);
        testPmoUser.setSynctime(new Date());
        testPmoUser.setTenantId("TTSHB");

        return testPmoUser;
    }

}
