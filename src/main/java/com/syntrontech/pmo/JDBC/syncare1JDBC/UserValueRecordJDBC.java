package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.UserValueRecord;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserValueRecordJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM user_value_record WHERE sync_status = 'N' ORDER BY USER_ID;";
    private static final String conn_str = "jdbc:mysql://localhost:3307/SynCare?"
            + "user=root&password=1qaz2wsx"
            + "&useUnicode=true&characterEncoding=UTF8";

    public static void main(String[] args) throws SQLException {

        UserValueRecordJDBC s = new UserValueRecordJDBC();

        Date star_time = new Date();
        List<UserValueRecord> ss = s.getAllUserValueRecord();
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

    }

    public List<UserValueRecord> getAllUserValueRecord() {

        List<UserValueRecord> userValueRecordlist = new ArrayList<>();
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
                    UserValueRecord userValueRecord = new UserValueRecord();

                    userValueRecord.setBodyValueRecordId(rs.getInt("BODY_VALUE_RECORD_ID"));
                    userValueRecord.setColumnType(rs.getString("COLUMN_TYPE"));
                    userValueRecord.setLocationId(rs.getString("location_id"));
                    userValueRecord.setLocationName(rs.getString("location_name"));
                    userValueRecord.setRecordDate(rs.getDate("RECORD_DATE"));
                    userValueRecord.setUpdateDate(rs.getDate("UPDATE_DATE"));

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
