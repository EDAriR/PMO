package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.syncare1.model.UserRole;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRoleJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM user_role  WHERE role_id = '1' AND sync_status = 'N' ORDER BY sequence;";

    public static void main(String[] args) throws SQLException {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        UserRoleJDBC s = new UserRoleJDBC();

        Date star_time = new Date();
        List<String> ss = s.getAllUserRoles();
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());
        System.out.println(ss);

    }

    public List<String> getAllUserRoles() {


        List<String> userRolelist = new ArrayList<>();

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {


            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    userRolelist.add(String.valueOf(rs.getInt("user_id")));
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || "+ pstmt);
                e.printStackTrace();
            }
        }

        return userRolelist;
    }
}
