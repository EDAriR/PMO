package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.model.PasswordList;
import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.model.common.ModelStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PasswordListJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM users WHERE tenant_id='DEFAULT_TENANT' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO users " +
            "(sequence, id, name, tenant_id, source, meta," +
            "unit_ids, role_ids, emails, mobilephones, cards, permission_ids," +
            "createtime, createby, updatetime, updateby, status) "
            + "VALUES (nextval('users_sequence_seq'), ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM role WHERE id=? and tenant_id='DEFAULT_TENANT'" +
            " AND status='ENABLED';";


    public static void main(String[] args) {

        PasswordListJDBC s = new PasswordListJDBC();

        Date star_time = new Date(new java.util.Date().getTime());
//        List<PasswordList> ss = s.getAllPasswordLists();
        Date end_time = new Date(new java.util.Date().getTime());

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
//        System.out.println("ss size:" + ss.size());

//        s.insertPasswordList(s.getTestPasswordList());

        PasswordList passwordList = s.getPasswordListById("DEFAULT_TENANT_ADMIN");

        System.out.println(passwordList);
    }

    public PasswordList getPasswordListById(String id) {

//        DEFAULT_TENANT_ADMIN
//        DEFAULT_USER
        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        PasswordList passwordList = new PasswordList();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
            System.out.println(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    // sequence, id, name, tenant_id, source, meta
                    passwordList.setSequence(rs.getLong("sequence"));
                    passwordList.setPassword(rs.getString("password"));
                    passwordList.setUserId(rs.getString("user_id"));
                    passwordList.setAccount(rs.getString("account"));

                    passwordList.setPasswordUpdateTime(rs.getDate("password_updatetime"));

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

        return passwordList;
    }
}
