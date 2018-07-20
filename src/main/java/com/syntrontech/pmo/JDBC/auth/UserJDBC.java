package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.model.common.ModelUserStatus;
import com.syntrontech.pmo.model.common.Source;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM users WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO users " +
            "(sequence, id, name, tenant_id, source, meta," +
            "unit_ids, role_ids, emails, mobilephones, cards, permission_ids," +
            "createtime, createby, updatetime, updateby, status) "
            + "VALUES (nextval('users_sequence_seq'), ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM unit WHERE id=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";
// sequence,
//    sequence, id, name, tenant_id, source, meta
//    unit_ids, role_ids, emails, mobilephones, cards, permission_ids
//    createtime, createby, updatetime, updateby, status

    public static void main(String[] args) {

        UserJDBC s = new UserJDBC();

        Date star_time = new Date(new java.util.Date().getTime());
        List<User> ss = s.getAllUsers();
        Date end_time = new Date(new java.util.Date().getTime());

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        System.out.println(ss);
        s.insertUser(s.getTestUser());

//        User user = s.getUserById("xxx");
//        System.out.println(user.getId() == null);
    }

    public User getUserById(String id) {
        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        User user = new User();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
            System.out.println(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    // sequence, id, name, tenant_id, source, meta
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setTenantId(rs.getString("tenant_id"));

                    Source source = rs.getString("source") != null ? Source.valueOf(rs.getString("source")) : null;
                    user.setSource(source);
                    user.setMeta(rs.getString(""));

                    // unit_ids, role_ids, emails, mobilephones, cards, permission_ids

                    String[] unit_ids = (String[])rs.getArray("unit_ids").getArray();
                    user.setUnitIds(unit_ids);

                    String[] roleIds = (String[])rs.getArray("role_ids").getArray();
                    user.setRoleIds(roleIds);

                    String[] emails = (String[])rs.getArray("emails").getArray();
                    user.setEmails(emails);

                    String[] mobilePhones = (String[])rs.getArray("mobilephones").getArray();
                    user.setMobilePhones(mobilePhones);

                    String[] cards = (String[])rs.getArray("mobilephones").getArray();
                    user.setCards(cards);

                    String[] permissionIds = (String[])rs.getArray("permission_ids").getArray();
                    // TODO
                    user.setPermissionIds(permissionIds);

                    // createtime, createby, updatetime, updateby, status
                    user.setCreateTime(rs.getDate("createtime"));
                    user.setCreateBy(rs.getString("createby"));
                    user.setUpdateTime(rs.getTime("updatetime"));
                    user.setUpdateBy(rs.getString("updateby"));

                    ModelUserStatus modelUserStatus = rs.getString("status") != null ? ModelUserStatus.valueOf(rs.getString("status")) : null;
                    user.setStatus(modelUserStatus);
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

        return user;
    }

    public User insertUser(User user){

        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        User old = getUserById(user.getId());
        if (old != null){
            if (old.getId() != null && !old.getId().equals(""))
                return old;
        }


        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            //  id, name, tenant_id, source, meta
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getTenantId());
            pstmt.setString(4, user.getSource().toString());
            pstmt.setString(5, user.getMeta());

            // unit_ids, role_ids, emails, mobilephones, cards, permission_ids
            Array unit_ids = conn.createArrayOf("varchar", user.getUnitIds());
            pstmt.setArray(6, unit_ids);

            Array role_ids = conn.createArrayOf("varchar", user.getRoleIds());
            pstmt.setArray(7, role_ids);

            Array emails = conn.createArrayOf("varchar", user.getEmails());
            pstmt.setArray(8, emails);

            Array mobilephones = conn.createArrayOf("varchar", user.getMobilePhones());
            pstmt.setArray(9, mobilephones);

            Array cards = conn.createArrayOf("varchar", user.getCards());
            pstmt.setArray(10, cards);

            Array permission_ids = conn.createArrayOf("varchar", user.getPermissionIds());
            pstmt.setArray(11, permission_ids);

            pstmt.setTimestamp(12, new java.sql.Timestamp(user.getCreateTime().getTime()));
            pstmt.setString(13, user.getCreateBy());
            pstmt.setTimestamp(14, new java.sql.Timestamp(user.getUpdateTime().getTime()));
            pstmt.setString(15, user.getUpdateBy());
            pstmt.setString(16, user.getStatus().toString());

            System.out.println(pstmt);

            pstmt.executeUpdate();
            System.out.println("create unit successful ==> " + user);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return user;
    }

    public User getTestUser() {

        User user = new User();
        // sequence, id, name, tenant_id, source, meta
        user.setId("userJDBCTest");
        user.setName("userJDBCTest");
        user.setTenantId("TTSHB");
        user.setSource(Source.CREATE);
        user.setMeta("meta");

        // unit_ids, role_ids, emails, mobilephones, cards, permission_ids
        user.setUnitIds(null);
        String[] roleIds = {""};

        user.setRoleIds(roleIds);
        user.setEmails(null);

        String[] mobilePhones = {"6666"};
        user.setMobilePhones(mobilePhones);

        String[] cards = {"123456"};
        user.setCards(cards);

        String[] permissionIds = {"123456"};
        // TODO
        user.setPermissionIds(permissionIds);

        // createtime, createby, updatetime, updateby, status
        user.setCreateTime(new Date());
        user.setCreateBy("systemAdmin");
        user.setUpdateTime(new Date());
        user.setUpdateBy("systemAdmin");
        user.setStatus(ModelUserStatus.ENABLED);
        return user;
    }

    List<User> getAllUsers(){

        Connection conn = new Auth_GET_CONNECTION().getConn();
        List<User> users = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    User user = new User();

                    // sequence, id, name, tenant_id, source, meta
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setTenantId(rs.getString("tenant_id"));

                    Source source = rs.getString("source") != null ? Source.valueOf(rs.getString("source")) : null;
                    user.setSource(source);
                    user.setMeta(rs.getString("meta"));

                    // unit_ids, role_ids, emails, mobilephones, cards, permission_ids

                    String[] unit_ids = (String[])rs.getArray("unit_ids").getArray();
                    user.setUnitIds(unit_ids);

                    String[] roleIds = (String[])rs.getArray("role_ids").getArray();
                    user.setRoleIds(roleIds);

                    String[] emails = (String[])rs.getArray("emails").getArray();
                    user.setEmails(emails);

                    String[] mobilePhones = (String[])rs.getArray("mobilephones").getArray();
                    user.setMobilePhones(mobilePhones);

                    String[] cards = (String[])rs.getArray("mobilephones").getArray();
                    user.setCards(cards);

                    String[] permissionIds = (String[])rs.getArray("permission_ids").getArray();
                    // TODO
                    user.setPermissionIds(permissionIds);

                    // createtime, createby, updatetime, updateby, status
                    user.setCreateTime(rs.getTimestamp("createtime"));
                    user.setCreateBy(rs.getString("createby"));
                    user.setUpdateTime(rs.getTimestamp("updatetime"));
                    user.setUpdateBy(rs.getString("updateby"));

                    ModelUserStatus modelUserStatus = rs.getString("status") != null ? ModelUserStatus.valueOf(rs.getString("status")) : null;
                    user.setStatus(modelUserStatus);
                    users.add(user);
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return users;
    }
}
