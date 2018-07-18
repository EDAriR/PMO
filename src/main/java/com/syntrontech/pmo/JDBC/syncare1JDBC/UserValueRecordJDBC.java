package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserValueRecordJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM user_value_record WHERE sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";
    private static final String GET_B_STMT = "SELECT * FROM user_value_record WHERE COLUMN_TYPE='B' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";
    private static final String GET_A_STMT = "SELECT * FROM user_value_record WHERE COLUMN_TYPE='A' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";
    private static final String GET_BG_STMT = "SELECT * FROM user_value_record WHERE COLUMN_TYPE='BG' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";

    private static final String UPDATE = "UPDATE user_value_record SET sync_status= 'Y' WHERE BODY_VALUE_RECORD_ID=? ;";

    public static void main(String[] args) throws SQLException {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        UserValueRecordJDBC s = new UserValueRecordJDBC();

        Date star_time = new Date();
        List<UserValueRecord> ss = s.getAllBGUserValueRecord();
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

    }

    public void updateUserValueRecord(String id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, id);
            pstmt.executeUpdate();

            System.out.println("update " + id + " successful ==============");


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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }

    public List<UserValueRecord> getAllBUserValueRecord() {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        List<UserValueRecord> userValueRecordlist = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_B_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();

                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    System.out.println("Device >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ?UserValueRecord.RecordPmoStatus.valueOf(pmo_status): null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    System.out.println("USER_ID :" + rs.getString("USER_ID"));

                    System.out.println(userValueRecord);

                    userValueRecordlist.add(userValueRecord);
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
        return userValueRecordlist;
    }

    public List<UserValueRecord> getAllAUserValueRecord() {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        List<UserValueRecord> userValueRecordlist = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_A_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();

                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    System.out.println("Device >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ?UserValueRecord.RecordPmoStatus.valueOf(pmo_status): null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    System.out.println("USER_ID :" + rs.getString("USER_ID"));

                    System.out.println(userValueRecord);

                    userValueRecordlist.add(userValueRecord);
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
        return userValueRecordlist;
    }

    public List<UserValueRecord> getAllBGUserValueRecord() {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        List<UserValueRecord> userValueRecordlist = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_BG_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();

                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    System.out.println("Device >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ?UserValueRecord.RecordPmoStatus.valueOf(pmo_status): null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    System.out.println("USER_ID :" + rs.getString("USER_ID"));

                    System.out.println(userValueRecord);

                    userValueRecordlist.add(userValueRecord);
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
        return userValueRecordlist;
    }


    public List<UserValueRecord> getAllUserValueRecord(Connection conn) {

        List<UserValueRecord> userValueRecordlist = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;


        try {


            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();

                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    System.out.println("Device >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ?UserValueRecord.RecordPmoStatus.valueOf(pmo_status): null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    System.out.println("USER_ID :" + rs.getString("USER_ID"));

                    System.out.println(userValueRecord);

                    userValueRecordlist.add(userValueRecord);
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
        return userValueRecordlist;
    }

}
