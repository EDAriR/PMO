package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SubjectJDBC {


    private static final String GET_ALL_STMT = "SELECT * FROM subject ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO subject " +
            // TODO
            "(id, name, mac_address, serial_number, unit_id, unit_name, tenant_id, status, " +
            "createtime, createby, updatetime, updateby) "

            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";




    public static void main(String[] args) {

        Connection conn = new CIP_GET_CONNECTION().getConn();

        SubjectJDBC s = new SubjectJDBC();
        Date star_time = new Date();
        List<HashMap<String, String>> ss = s.getAllSubjects(conn);
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        Subject subject = new Subject();

        s.insertSubject(conn, subject);

    }


    List<HashMap<String, String>> getAllSubjects(Connection conn){

        List<HashMap<String, String>> devices = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    HashMap<String, String> device = new HashMap<>();
                    device.put("sequence", rs.getString("sequence"));

                    device.put("id" ,rs.getString("id"));
                    device.put("name" , rs.getString("name"));
                    device.put("mac_address" , rs.getString("mac_address"));

                    device.put("serial_number" , rs.getString("serial_number"));
                    device.put("unit_id" , rs.getString("unit_id"));
                    device.put("unit_name" , rs.getString("unit_name"));
                    device.put("tenant_id" , rs.getString("tenant_id"));
                    device.put("createtime" , rs.getString("createtime"));
                    device.put("createby" , rs.getString("createby"));
                    device.put("updatetime" , rs.getString("updatetime"));
                    device.put("updateby" , rs.getString("updateby"));
                    device.put("status" , rs.getString("status"));

//                    System.out.println("device:" + device);
                    devices.add(device);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return devices;
    }


    public void insertSubject(Connection conn, Subject subject){

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            Statement stgfid = conn.prepareStatement("SELECT last_value FROM forums_id_seq");
            ResultSet rsgfid = stgfid.executeQuery("");
            rsgfid.next();
            int forumId = rsgfid.getInt(1);
            rsgfid.close();
            stgfid.close();

            pstmt.setLong(1, forumId);


            pstmt.executeUpdate();
            System.out.println("create successful ==> " + subject);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
