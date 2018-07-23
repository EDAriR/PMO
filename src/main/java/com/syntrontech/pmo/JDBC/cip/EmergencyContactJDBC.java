package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.ModelMgmtStatus;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmergencyContactJDBC {

//    private static Logger logger = LoggerFactory.getLogger(EmergencyContactJDBC.class);

    private static final String GET_ALL_STMT = "select * from emergency_contacts order by sequence;";
    private static final String INSERT_STMT = "INSERT INTO emergency_contacts " +
            "(sequence, subject_id, user_id, tenant_id, name, phone, email, status) "
            + "VALUES (nextval('emergency_contacts_sequence_seq'), ?, ?, ?, ?, ?, ?, ?);";
    // sequence, subject_id, user_id, tenant_id, name, phone, email, status

    private static final String GET_ONE = "SELECT * FROM emergency_contacts WHERE name=? and tenant_id='TTSHB' AND status='ENABLED';";

    public static void main(String[] args) {

        EmergencyContactJDBC s = new EmergencyContactJDBC();

//        List<EmergencyContact> ss = s.getAllEmergencyContacts();
        EmergencyContact emt = s.insertEmergencyContact(s.getTestEmergencyContact());
        System.out.println(emt);
    }

    public EmergencyContact insertEmergencyContact(EmergencyContact emergencyContact){

        Connection conn = new CIP_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // subject_id, user_id, tenant_id, name, phone, email, status
            pstmt.setString(1, emergencyContact.getSubjectId());
            pstmt.setString(2, emergencyContact.getUserId());
            pstmt.setString(3, emergencyContact.getTenantId());
            pstmt.setString(4, emergencyContact.getName());
            pstmt.setString(5, emergencyContact.getPhone());
            pstmt.setString(6, emergencyContact.getEmail());
            pstmt.setString(7, emergencyContact.getStatus().toString());

//            logger.info(pstmt.toString());
            System.out.println(Calendar.getInstance().getTime() + "  EmergencyContactJDBC:" + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException e) {
//            logger.debug("insertEmergencyContact fail" + conn + " || " + pstmt);
            System.out.println(Calendar.getInstance().getTime() + "  EmergencyContactJDBC:" + "insertEmergencyContact fail" + conn + " || " + pstmt);
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                System.out.println(Calendar.getInstance().getTime() + "  EmergencyContactJDBC:" + "conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
//        logger.info("create emergencyContact successful ==> " + emergencyContact);
        System.out.println(Calendar.getInstance().getTime() + "  EmergencyContactJDBC:" + "create emergencyContact successful ==> " + emergencyContact);

        return emergencyContact;
    }

    public List<EmergencyContact> getAllEmergencyContacts() {

        Connection conn = new CIP_GET_CONNECTION().getConn();
        List<EmergencyContact> emergencyContacts = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    EmergencyContact emergencyContact = new EmergencyContact();
                    // sequence, subject_id, user_id, tenant_id, name, phone, email, status
                    emergencyContact.setSequence(rs.getLong("sequence"));
                    emergencyContact.setSubjectId(rs.getString("subject_id"));
                    emergencyContact.setUserId(rs.getString("user_id"));
                    emergencyContact.setTenantId(rs.getString("tenant_id"));
                    emergencyContact.setName(rs.getString("name"));
                    emergencyContact.setPhone(rs.getString("phone"));
                    emergencyContact.setEmail(rs.getString("email"));
                    emergencyContact.setStatus(ModelStatus.valueOf(rs.getString("status")));
//                    logger.info("unit:" + unit);
                    emergencyContacts.add(emergencyContact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                System.out.println(Calendar.getInstance().getTime() + "  EmergencyContactJDBC:" + "conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
        return emergencyContacts;
    }

    private EmergencyContact getTestEmergencyContact(){

        EmergencyContact emergencyContact = new EmergencyContact();

        emergencyContact.setSubjectId("userJDBCTest");
        emergencyContact.setUserId("userJDBCTest");
        emergencyContact.setTenantId("TTSHB");
        emergencyContact.setName("userJDBCTest");
        emergencyContact.setPhone("666");
        emergencyContact.setEmail("666");
        emergencyContact.setStatus(ModelStatus.ENABLED);

        return emergencyContact;
    }
}
