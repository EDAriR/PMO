package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.model.common.ModelUserStatus;
import com.syntrontech.pmo.model.common.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class UserJDBC {

    private static Logger logger = LoggerFactory.getLogger(Auth_GET_CONNECTION.class);

    private static final String GET_ALL_STMT = "SELECT * FROM users WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO users " +
            "(sequence, id, name, tenant_id, source, meta," +
            "unit_ids, role_ids, emails, mobilephones, cards, permission_ids," +
            "createtime, createby, updatetime, updateby, status) "
            + "VALUES (nextval('users_sequence_seq'), ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?);";

    private static final String INSERT_ACCOUNT_STMT = "INSERT INTO account_list " +
            "(account, user_id, map_to_users_field)" +
            "VALUES (?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM users WHERE id=? and tenant_id='TTSHB'" +
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
        User user = s.insertUser(s.getTestUser());
        System.out.println(user);
//        User user = s.getUserById("xxx");
//        logger.info(user.getId() == null);
    }

    public User getUserById(String id) {
        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        User user = new User();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);

//            logger.info(pstmt.toString());
            System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" + pstmt.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    // sequence, id, name, tenant_id, source, meta
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setTenantId(rs.getString("tenant_id"));

                    Source source = rs.getString("source") != null ? Source.valueOf(rs.getString("source")) : null;
                    user.setSource(source);
//                    user.setMeta(rs.getString("meta"));

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
            logger.warn("getUserById fail \n"+ pstmt + "\n user =>" + user);
//            System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" + "getUserById fail =>" + conn + " || " + pstmt + "||" + user);
            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail =>" + pstmt);
//                System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" +"conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
//        logger.info("get user by id successful =>" + user);
        System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" +"get user by id successful =>" + user);
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
            String[] unitIds = {};
            unitIds = user.getUnitIds() != null ? user.getUnitIds(): unitIds;
            Array unit_ids = conn.createArrayOf("varchar", unitIds);
            pstmt.setArray(6, unit_ids);

            String[] roleIds = {};
            roleIds = user.getRoleIds() != null ? user.getRoleIds(): roleIds;
            Array role_ids = conn.createArrayOf("varchar", roleIds);
            pstmt.setArray(7, role_ids);

            String[] emails = {};
            emails = user.getEmails() != null ? user.getEmails(): emails;
            Array emailsArray = conn.createArrayOf("varchar", emails);
            pstmt.setArray(8, emailsArray);

            String[] mobilePhones = {};
            mobilePhones = user.getMobilePhones() != null ? user.getMobilePhones(): mobilePhones;
            Array mobilephonesArray = conn.createArrayOf("varchar", mobilePhones);
            pstmt.setArray(9, mobilephonesArray);

            String[] cards = {};
            cards = user.getCards() != null ? user.getCards(): cards;
            Array cardsArray = conn.createArrayOf("varchar", cards);
            pstmt.setArray(10, cardsArray);

            String[] permissionIds = {};
            permissionIds = user.getPermissionIds() != null ? user.getPermissionIds(): permissionIds;
            Array permission_ids = conn.createArrayOf("varchar", permissionIds);
            pstmt.setArray(11, permission_ids);

            pstmt.setTimestamp(12, new java.sql.Timestamp(user.getCreateTime().getTime()));
            pstmt.setString(13, user.getCreateBy());
            pstmt.setTimestamp(14, new java.sql.Timestamp(user.getUpdateTime().getTime()));
            pstmt.setString(15, user.getUpdateBy());
            pstmt.setString(16, user.getStatus().toString());

//            logger.info(pstmt.toString());
            logger.info("  UserJDBC INSERT:" +pstmt.toString());
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(INSERT_ACCOUNT_STMT);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getId());
            pstmt.setString(3, "ID");

            logger.info("  UserJDBC INSERT ACCOUNT:" +pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.warn("insert user fail =>" + pstmt + "\n" + user);
//            System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" +"getUserById fail =>" + conn + " || " + pstmt + "||" + user);

            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.info("conn or pstmt close fail" + conn + " || " + pstmt);
                System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" +"conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
//        logger.info("create user successful ==> " + user);
        System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" +"create user successful ==> " + user);
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
        String[] roleIds = {"DEFAULT_USER"};

        user.setRoleIds(roleIds);
        user.setEmails(null);

        String[] mobilePhones = {};
        user.setMobilePhones(mobilePhones);

        String[] cards = {};
        user.setCards(cards);

        String[] permissionIds = {};
        // TODO
        user.setPermissionIds(permissionIds);

        // createtime, createby, updatetime, updateby, status
        Date date = new Date();
        user.setCreateTime(date);
        user.setCreateBy("systemAdmin");
        user.setUpdateTime(date);
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
                System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" +"conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return users;
    }
}
