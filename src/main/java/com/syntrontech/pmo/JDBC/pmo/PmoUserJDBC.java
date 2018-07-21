package com.syntrontech.pmo.JDBC.pmo;

import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.pmo.PmoResult;
import com.syntrontech.pmo.pmo.PmoStatus;
import com.syntrontech.pmo.pmo.PmoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class PmoUserJDBC {

    private static Logger logger = LoggerFactory.getLogger(PmoUserJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM pmo_user WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO pmo_user " +
            "(sequence, user_id, pmoPassword, pmoStatus, synctime, tenant_id) "
            + "VALUES (nextval('pmo_user_sequence_seq'), ?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM pmo_user WHERE id=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";

    public static void main(String[] args) {

    }

    public PmoUser insert(PmoUser pmoUser){

        Connection conn = new PMO_GET_CONNECTION().getConn();

        PmoUser old = getPmoUserById(pmoUser.getUserId());
        if (old != null){
            logger.info("UnitMeta = " + old);
            if (old.getUserId() != null && !old.getUserId().equals(""))
                return old;
        }

        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // sequence, unit_id, unit_name, unit_parent_id,
            pstmt.setString(1, pmoUser.getUserId());
            pstmt.setString(2, pmoUser.getPmoPassword());
            pstmt.setString(3, pmoUser.getPmoStatus().toString());
            // unit_parent_name, unit_status, tenant_id, category,
            pstmt.setTimestamp(4, new Timestamp(pmoUser.getSynctime().getTime()));

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
        logger.info("create unitMeta successful ==> " + pmoUser);

        return pmoUser;
    }

}
