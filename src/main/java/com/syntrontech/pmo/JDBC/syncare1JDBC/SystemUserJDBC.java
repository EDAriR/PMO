package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.Area;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystemUserJDBC {

    private static Logger logger = LoggerFactory.getLogger(SystemUserJDBC.class);


    private static final String GET_ALL_STMT = "SELECT * FROM system_user WHERE recluse='Y' AND sync_status = 'N' ORDER BY USER_ID;";

    private static final String GET_ONE = "SELECT * FROM system_user WHERE USER_ID=?";
    private static final String UPDATE = "UPDATE system_user SET sync_status= 'Y' WHERE USER_ID=? ;";

    public static void main( String[] args ) throws SQLException
    {

        SystemUserJDBC s = new SystemUserJDBC();

        List<SystemUser> ss = s.getAllSystemUser();

        System.out.println("ss size:" + ss.size());

        Map<String, List<SystemUser>> aaa = ss.stream()
                .collect(Collectors.groupingBy(SystemUser::getUserGender));

//        SystemUser sss = s.getSystemUserById("4");

//        System.out.println(sss);
    }

    public void updateSystemUser(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            logger.info("update SystemUser :[" + id + "] successful ");


        } catch (SQLException e) {
            logger.warn("Syncare1_GET_CONNECTION SQLException ");

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }

    public List<SystemUser> getAllSystemUser(){

        List<SystemUser> systemUserslist = new ArrayList<>();

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {


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

//                    種族註記，0：未填寫，1：漢族，2：客家，3：原住民，4：外籍
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

                    Area area = new Area();
                    area.setCode(rs.getString("area"));
                    systemUser.setArea(area);
                    System.out.println(systemUser);
                    System.out.println("area ===>" + area);
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

    public SystemUser getSystemUserById(String id){

        SystemUser systemUser = new SystemUser();

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {


            pstmt = conn.prepareStatement(GET_ONE);
            pstmt.setString(1, id);

            System.out.println(pstmt);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

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

//                    種族註記，0：未填寫，1：漢族，2：客家，3：原住民，4：外籍
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

                    Area area = new Area();
                    area.setCode(rs.getString("area"));
                    systemUser.setArea(area);
                    System.out.println(systemUser);


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

        return systemUser;
    }


    private YN getYN(ResultSet rs, String columnLabel) throws SQLException {
        return rs.getString(columnLabel) != null?
                YN.valueOf(rs.getString(columnLabel))
                : null;
    }

}
