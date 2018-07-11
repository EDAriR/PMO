package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SystemUserJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM system_user ORDER BY USER_ID;";
    private static final String conn_str = "jdbc:mysql://localhost:3307/SynCare?"
            + "user=root&password=1qaz2wsx"
            + "&useUnicode=true&characterEncoding=UTF8";

    public static void main( String[] args ) throws SQLException
    {

        SystemUserJDBC s = new SystemUserJDBC();

        List<SystemUser> ss = s.getAllSystemUser();

        System.out.println("ss size:" + ss.size());

    }

    public List<SystemUser> getAllSystemUser(){

        List<SystemUser> systemUserslist = new ArrayList<>();
        Connection conn = null;

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection MySQL ");

            conn = DriverManager.getConnection(conn_str);

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    SystemUser systemUser = new SystemUser();

                    systemUser.setUserId(rs.getInt("USER_ID"));
                    systemUser.setStateId(rs.getString("STATE_ID"));
                    systemUser.setUserAccount(rs.getString("USER_ACCOUNT"));
                    systemUser.setUserPassword(rs.getString("USER_PASSWORD"));
                    systemUser.setCardId(rs.getString("CARD_ID"));
                    systemUser.setUserGender(rs.getString("USER_GENDER"));
                    systemUser.setUserBirthday(rs.getTimestamp("USER_BIRTHDAY"));
                    systemUser.setAge(rs.getInt("age"));
                    systemUser.setUserAddress(rs.getString("USER_ADDRESS"));
                    systemUser.setUserEmail(rs.getString("USER_EMAIL"));
                    systemUser.setUserPhone(rs.getString("USER_PHONE"));
                    systemUser.setUserMobile(rs.getString("USER_MOBILE"));
                    systemUser.setUserDisplayName(rs.getString("USER_DISPLAY_NAME"));
                    systemUser.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                    systemUser.setMemo(rs.getString("MEMO"));
                    systemUser.setPicasaAccount(rs.getString("PICASA_ACCOUNT"));
                    systemUser.setAlbumName(rs.getString("ALBUM_NAME"));
                    systemUser.setAlbumType(rs.getInt("ALBUM_TYPE"));
                    systemUser.setAdvertismentStatus(rs.getInt("ADVERTISMENT_STATUS"));
                    systemUser.setPmoStatus(SystemUser.SystemUserPmoStatus.valueOf(rs.getString("pmo_status")));
                    systemUser.setSex(Sex.valueOf(rs.getString("sex")));
                    systemUser.setAlert(getYN(rs,"alert"));

//                    @JoinColumn(name = "area")
//                    private Area area;

                    systemUser.setWithHighBloodPressure(getYN(rs,"with_high_blood_pressure"));
                    systemUser.setPmoPassword(rs.getString("pmo_PASSWORD"));
                    systemUser.setCaseStatus(rs.getInt("case_status"));
                    systemUser.setCaseStatusDisplay(rs.getInt("case_status_display"));
                    systemUser.setCaseNote(rs.getString("case_note"));
                    systemUser.setCaseUpdateDate(rs.getTimestamp("case_update_date"));
                    systemUser.setAlertNotifierName(rs.getString("alert_notifier_name"));
                    systemUser.setAlertNotifierMobilePhone(rs.getString("alert_notifier_mobile_phone"));
                    systemUser.setAlertNotifierEmail(rs.getString("alert_notifier_email"));
                    systemUser.setWithDiabetesMellitus(getYN(rs,"with_diabetes_mellitus"));

                    /* 2017/03 台東新案新增  start*/
                    systemUser.setEthnicity(rs.getInt("ethnicity"));

                    systemUser.setWithDiabetesMellitus(getYN(rs, "with_diabetes_mellitus"));

                    systemUser.setWithBrainAttack(getYN(rs,"with_brain_attack"));
                    systemUser.setWithHeartAttack(getYN(rs,"with_heart_attack"));
                    systemUser.setFamilyWithHeartAttack(getYN(rs,"family_with_heart_attack"));
                    systemUser.setFamilyWithBrainAttack(getYN(rs,"family_with_brain_attack"));
                    systemUser.setFamilyWithHighBloodPressure(getYN(rs,"family_with_high_blood_pressure"));
                    systemUser.setFamilyWithDiabetesMellitus(getYN(rs,"family_with_diabetes_mellitus"));
                    systemUser.setHealthInformationNotify(getYN(rs,"health_information_notify"));
                    systemUser.setFrequencyOfSmoking(rs.getInt("frequency_of_smoking"));
                    systemUser.setFrequencyOfDrinking(rs.getInt("frequency_of_drinking"));
                    systemUser.setFrequencyOfChewingBetelNut(rs.getInt("frequency_of_chewing_betel_nut"));
                    systemUser.setWithDiabetesMellitus(getYN(rs,"health_information_notify"));
                    systemUser.setWithDiabetesMellitus(getYN(rs, "recluse"));
                    systemUser.setWithDiabetesMellitus(getYN(rs,"iHealth_service"));
                    systemUser.setRecluse(getYN(rs,"recluse"));
                    systemUser.setIHealthService(getYN(rs,"iHealth_service"));

                    System.out.println(systemUser);
                    System.out.println("area ===>" + rs.getString("area"));
                    systemUserslist.add(systemUser);

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

        return systemUserslist;

    }

    private YN getYN(ResultSet rs, String columnLabel) throws SQLException {
        return rs.getString(columnLabel) != null?
                YN.valueOf(rs.getString(columnLabel))
                : null;
    }

}
