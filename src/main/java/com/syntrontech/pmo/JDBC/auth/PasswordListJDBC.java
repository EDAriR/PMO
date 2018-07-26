package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.Encrypt;
import com.syntrontech.pmo.auth.model.PasswordList;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PasswordListJDBC {

    private static Logger logger = LoggerFactory.getLogger(Auth_GET_CONNECTION.class);

    private static final String INSERT_STMT = "INSERT INTO password_list " +
            "(sequence, password, user_id, password_updatetime) "
            + "VALUES (nextval('password_list_sequence_seq'), ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM password_list WHERE user_id=?";


    public static void main(String[] args) throws SQLException {

        PasswordListJDBC s = new PasswordListJDBC();

//        List<PasswordList> ss = s.getAllPasswordLists();

        s.insertPassword(new UserJDBC().getTestUser(), "1qaz2wsx");

        PasswordList passwordList = s.getPasswordListById(new UserJDBC().getTestUser().getId());

        System.out.println(passwordList);
    }

    public PasswordList getPasswordListById(String id) throws SQLException {

        if(id == null)
            return null;

        id = id.toUpperCase().trim();

//        DEFAULT_USER
        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        PasswordList passwordList = null;

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
            System.out.println(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                passwordList = new PasswordList();
                while (rs.next()) {
                    // sequence, id, name, tenant_id, source, meta
                    passwordList.setSequence(rs.getLong("sequence"));
                    passwordList.setPassword(rs.getString("password"));
                    passwordList.setUserId(rs.getString("user_id"));
                    passwordList.setAccount(rs.getString("account"));

                    passwordList.setPasswordUpdateTime(rs.getTimestamp("password_updatetime"));

                }
            }

        } catch (SQLException e) {
            logger.error("getPassword ListById fail " + pstmt);
            throw e;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.warn("conn or pstmt close fail "+ pstmt);
                e.printStackTrace();
            }

        }

        return passwordList;
    }

    public PasswordList insertPassword(User user, String password) throws SQLException {

        if(user == null || user.getId() == null || password == null)
            return null;
//        password = password.toUpperCase().trim();

        PasswordList old = getPasswordListById(user.getId());
        if(old != null && old.getUserId() != null)
            return old;

        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        PasswordList passwordList = null;
        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            passwordList =  insert(user.getId(), password, user.getUpdateTime());

            pstmt.setString(1, passwordList.getPassword());
            pstmt.setString(2, passwordList.getUserId());
            pstmt.setTimestamp(3, new Timestamp(passwordList.getPasswordUpdateTime().getTime()));

            logger.info(pstmt.toString());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    passwordList.setSequence(rs.getLong(1));
                }
                rs.close();
            }

        } catch (SQLException e) {
            logger.debug("insert Password fail " + pstmt);
            throw e;
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail " + pstmt);
            }

        }
        logger.info("create Password successful ==> " + pstmt);
        return passwordList;
    }

    PasswordList insert(String userId, String password, Date passwordUpdateTime){

        userId = userId.toUpperCase().trim();

        String encryptPassword = getEncryptPassword(password, passwordUpdateTime);

        PasswordList passwordList = new PasswordList();
        passwordList.setPassword(encryptPassword);
        passwordList.setPasswordUpdateTime(passwordUpdateTime);
        passwordList.setUserId(userId);

        return passwordList;
    }

    protected String getEncryptPassword(String password, Date passwordUpdateTime){
        Encrypt encrypt = new Encrypt();
        return encrypt.encrypt_sha512(password+passwordUpdateTime.getTime());
    }
}
