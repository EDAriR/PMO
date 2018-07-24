package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.Device;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserValueRecordJDBC {

    private static Logger logger = LoggerFactory.getLogger(UserValueRecordJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM user_value_record WHERE sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";
    private static final String GET_B_STMT = "SELECT * FROM user_value_record WHERE COLUMN_TYPE='B' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";
    private static final String GET_A_STMT = "SELECT * FROM user_value_record WHERE COLUMN_TYPE='A' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";
    private static final String GET_BG_STMT = "SELECT * FROM user_value_record WHERE COLUMN_TYPE='BG' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";

    // body info
    private static final String GET_ONE_USER_A_STMT = "SELECT * FROM user_value_record WHERE USER_ID=? AND COLUMN_TYPE='A' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";
    // 血壓
    private static final String GET_ONE_USER_B_STMT = "SELECT * FROM user_value_record WHERE USER_ID=? AND COLUMN_TYPE='B' AND sync_status = 'N' ORDER BY RECORD_DATE;";
    // 血糖
    private static final String GET_ONE_USER_BG_STMT = "SELECT * FROM user_value_record WHERE USER_ID=? AND COLUMN_TYPE='BG' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";

    // 其他
    private static final String GET_ONE_USER_OTHER_STMT = "SELECT * FROM user_value_record WHERE COLUMN_TYPE != 'B' AND COLUMN_TYPE != 'BG' AND COLUMN_TYPE != 'A' AND sync_status = 'N' ORDER BY BODY_VALUE_RECORD_ID;";

    private static final String GET_ONE_STMT = "SELECT * FROM user_value_record WHERE BODY_VALUE_RECORD_ID=? ;";

    private static final String UPDATE = "UPDATE user_value_record SET sync_status= 'Y' WHERE BODY_VALUE_RECORD_ID=? ;";

    public static void main(String[] args) throws SQLException {

//        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        UserValueRecordJDBC s = new UserValueRecordJDBC();

        List<UserValueRecord> getByUserId = s.getOneBUserValueRecord(1);
        System.out.println("get one by id : " + getByUserId.size());

        getByUserId.forEach(b -> System.out.println(b));

        List<UserValueRecord> other = s.getOneUserOtherValueRecord(1);
        System.out.println(other);
        List<UserValueRecord> aType = s.getOneUserAValueRecord(1);
        System.out.println(aType);

        List<UserValueRecord> btype = s.getOneBUserValueRecord(1);
        System.out.println(btype);
    }

    public List<UserValueRecord> getOneUserOtherValueRecord(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        List<UserValueRecord> userValueRecords = new ArrayList<>();
        try {

            pstmt = conn.prepareStatement(GET_ONE_USER_OTHER_STMT);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();
                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    Device device = new Device();
                    device.setSerialNo(rs.getString("serial_no"));
                    userValueRecord.setDevice(device);
                    System.out.println("serial_no >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    userValueRecords.add(userValueRecord);
                }
            }

        } catch (SQLException e) {
            logger.debug("get A type user value record fail" + " || " + pstmt);

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

        return userValueRecords;
    }

    public List<UserValueRecord> getOneUserAValueRecord(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        List<UserValueRecord> userValueRecords = new ArrayList<>();
        try {

            pstmt = conn.prepareStatement(GET_ONE_USER_A_STMT);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();
                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    Device device = new Device();
                    device.setSerialNo(rs.getString("serial_no"));
                    userValueRecord.setDevice(device);
                    System.out.println("serial_no >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    userValueRecords.add(userValueRecord);
                }
            }

        } catch (SQLException e) {
            logger.debug("get A type user value record fail" + conn + " || " + pstmt);

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

        return userValueRecords;
    }

    public UserValueRecord getOneUserValueRecord(String id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;
        UserValueRecord userValueRecord = null;

        try {

            pstmt = conn.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    userValueRecord = new UserValueRecord();
                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    Device device = new Device();
                    device.setSerialNo(rs.getString("serial_no"));
                    userValueRecord.setDevice(device);
                    System.out.println("serial_no >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    System.out.println("USER_ID :" + rs.getString("USER_ID"));

                    System.out.println(userValueRecord);
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return userValueRecord;
    }

    public List<UserValueRecord> getOneBUserValueRecord(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        List<UserValueRecord> userValueRecords = new ArrayList<>();
        try {

            pstmt = conn.prepareStatement(GET_ONE_USER_B_STMT);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();
                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    Device device = new Device();
                    device.setSerialNo(rs.getString("serial_no"));
                    userValueRecord.setDevice(device);
                    System.out.println("serial_no >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    System.out.println("USER_ID :" + rs.getString("USER_ID"));

                    System.out.println(userValueRecord);

                    userValueRecords.add(userValueRecord);
                }
            }

        } catch (SQLException e) {
            logger.debug("get B type user value record fail" + conn + " || " + pstmt);

        } catch (Exception e) {

            logger.debug("get B type user value record fail" + conn + " || " + pstmt);

        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return userValueRecords;
    }

    public List<UserValueRecord> getOneBGUserValueRecord(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        List<UserValueRecord> userValueRecords = new ArrayList<>();
        try {

            pstmt = conn.prepareStatement(GET_ONE_USER_BG_STMT);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    UserValueRecord userValueRecord = new UserValueRecord();
                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getTimestamp("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));

                    Device device = new Device();
                    device.setSerialNo(rs.getString("serial_no"));
                    userValueRecord.setDevice(device);
                    System.out.println("serial_no >>" + rs.getString("serial_no") + "<<");

                    String pmo_status = rs.getString("pmo_status");
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
                    userValueRecord.setPmoStatus(recordPmoStatus);

                    userValueRecord.setPmoResult(rs.getString("pmo_result"));

                    String synCare2StatusString = rs.getString("syncare2_status");
                    UserValueRecord.RecordSynCare2Status synCare2Status = synCare2StatusString != null ? UserValueRecord.RecordSynCare2Status.valueOf(synCare2StatusString) : null;
                    userValueRecord.setSynCare2Status(synCare2Status);

                    userValueRecord.setUserAccountSerial(rs.getLong("user_account_serial"));

                    userValueRecords.add(userValueRecord);
                }
            }

        } catch (SQLException e) {
            logger.debug("get BG type user value record fail" + conn + " || " + pstmt);

        } catch (Exception e) {
            logger.debug("get BG type user value record fail" + conn + " || " + pstmt);

        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
        return userValueRecords;
    }

    public void updateUserValueRecord(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("update " + id + " successful ==============");


        } catch (SQLException e) {
            logger.debug("update user value record fail" + conn + " || " + pstmt);

        } catch (Exception e) {
            logger.debug("update user value record fail" + conn + " || " + pstmt);

        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
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
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
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
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
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
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
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
                    UserValueRecord.RecordPmoStatus recordPmoStatus = pmo_status != null ? UserValueRecord.RecordPmoStatus.valueOf(pmo_status) : null;
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
        return userValueRecordlist;
    }

}
