package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.Encrypt;
import com.syntrontech.pmo.auth.model.PasswordList;
import com.syntrontech.pmo.auth.model.User;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class PasswordListJDBC {

    private static Logger logger = LoggerFactory.getLogger(PasswordListJDBC.class);

    private static final String INSERT_STMT = "INSERT INTO password_list " +
            "(sequence, password, user_id, password_updatetime) "
            + "VALUES (nextval('password_list_sequence_seq'), ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM password_list WHERE user_id=?";


    public static void main(String[] args) throws SQLException {

        PasswordListJDBC s = new PasswordListJDBC();

//        List<PasswordList> ss = s.getAllPasswordLists();

//        s.insertPassword(new UserJDBC().getTestUser(), "1qaz2wsx");

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

    public String insertPassword(Connection conn, User user, Date birthDay) throws SQLException {

        if(user == null || user.getId() == null || birthDay == null)
            return null;

        PasswordList old = getPasswordListById(user.getId());
        if(old != null && old.getUserId() != null)
            return old.toString();

        // 2018/08/07 密碼全改為預設 規則為 身分證第一個字大寫+西元年生日
        // ex A20180807
        // 設為台灣時區
        String password;
        String userId = user.getId().toUpperCase().trim();

        TimeZone zone = TimeZone.getTimeZone(ZoneId.of("Asia/Taipei"));
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

        sdf.setTimeZone(zone);
        String birthDayStr = sdf.format(birthDay);
        password = userId.charAt(0) + birthDayStr;

        Date update = new Date();

        String pwd = "userId=[" + user.getId() + "], " +
                "password=["+ password +"], " +
                "birthDay=["+ birthDay +"], " +
                "Time=["+ update.getTime() +"], " +
                "UpdateTime=["+ update +"]    ";

//        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        PasswordList passwordList;
        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            passwordList =  insert(userId, password, update);

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

            pwd = pwd + passwordList;
        } catch (SQLException e) {
            logger.debug("insert Password fail " + pstmt);
            throw e;
        }finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("pstmt close fail" + conn);
                e.printStackTrace();
            }
        }
        
        logger.info("create Password successful ==> " + pstmt);

        return pwd;
    }

    PasswordList insert(String userId, String password, Date passwordUpdateTime){

        logger.debug("userId:[" + userId + "], password:[" + password + "], passwordUpdateTime:[" + passwordUpdateTime + "]");

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
