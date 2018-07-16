package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.model.common.ModelUserStatus;
import com.syntrontech.pmo.model.common.Source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RoleJDBC {

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

        RoleJDBC s = new RoleJDBC();

        Date star_time = new Date(new java.util.Date().getTime());
//        List<Role> ss = s.getAllRoles();
        Date end_time = new Date(new java.util.Date().getTime());

//        System.out.println("star_time:" + star_time.toInstant());
//        System.out.println("end_time:" + end_time.toInstant());
//        System.out.println("ss size:" + ss.size());

//        System.out.println(ss);
//        s.insertUser(s.getTestUser());

        Role role = s.getRoleById("DEFAULT_TENANT_ADMIN");

        System.out.println(role);
    }

    public Role getRoleById(String id) {

//        DEFAULT_TENANT_ADMIN
//        DEFAULT_USER
        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        Role role = new Role();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
            System.out.println(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    // sequence, id, name, tenant_id, source, meta
                    role.setId(rs.getString("id"));
                    role.setName(rs.getString("name"));
                    role.setTenantId(rs.getString("tenant_id"));


                    String[] permissionIds = (String[])rs.getArray("permission_ids").getArray();
                    // TODO
                    role.setPermissionIds(permissionIds);

                    // createtime, createby, updatetime, updateby, status
                    role.setCreateTime(rs.getDate("createtime"));
                    role.setCreateBy(rs.getString("createby"));
                    role.setUpdateTime(rs.getDate("updatetime"));
                    role.setUpdateBy(rs.getString("updateby"));

                    ModelStatus modelStatus = rs.getString("status") != null ? ModelStatus.valueOf(rs.getString("status")) : null;
                    role.setStatus(modelStatus);
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

        return role;
    }
}