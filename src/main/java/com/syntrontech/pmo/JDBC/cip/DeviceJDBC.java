package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.Device;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DeviceJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM device ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO device " +
            "(id, name, mac_address, serial_number, unit_id, unit_name, tenant_id, status, " +
            "createtime, createby, updatetime, updateby) "

            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";




    public static void main(String[] args) {

        Connection conn = new CIP_GET_CONNECTION().getConn();
        DeviceJDBC s = new DeviceJDBC();


        Date star_time = new Date();
        List<HashMap<String, String>> ss = s.getAllDevice(conn);
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        HashMap<String, String> device = new HashMap<>();

        device.put("id" ,"id");
        device.put("name" , "systemAdmin");
        device.put("mac_address" , "mac_address");

        device.put("serial_number" , "serial_number");
        device.put("unit_id" , "100140102310");
        device.put("unit_name" , "其他");
        device.put("tenant_id" , "DEFAULT_TENANT");
        device.put("createtime" , new Date().toInstant().toString());
        device.put("createby" , "systemAdmin");
        device.put("updatetime" , new Date().toInstant().toString());
        device.put("updateby" , "systemAdmin");
        device.put("status" , "ENABLED");
        s.insertDevice(conn, device);

    }

    private Connection getConn(){

        Connection conn = null;

        try {

            Class.forName("org.postgresql.Driver");
            System.out.println("Connection MySQL ");
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/cipdb",
                    "postgres",
                    "1qaz2wsx");

        }catch (ClassNotFoundException e) {

                System.out.println("Where is your PostgreSQL JDBC Driver? "
                        + "Include in your library path!");
                e.printStackTrace();
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

        }

        if (conn != null)
            return conn;
        else
            throw new NullPointerException();

    }

    List<HashMap<String, String>> getAllDevice(Connection conn){

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


    public void insertDevice(Connection conn, HashMap<String, String> device){

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
            pstmt.setString(2, device.get("id"));
            pstmt.setString(3, device.get("name"));
            pstmt.setString(4, device.get("serial_number"));
            pstmt.setString(5, device.get("unit_id"));
            pstmt.setString(6, device.get("unit_name"));
            pstmt.setString(7, device.get("tenant_id"));
            pstmt.setString(8, device.get("createtime"));
            pstmt.setString(9, device.get("createby"));
            pstmt.setString(10, device.get("updatetime"));
            pstmt.setString(11, device.get("updateby"));
            pstmt.setString(12, device.get("status"));

            pstmt.executeUpdate();
            System.out.println("create successful ==> " + device);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDevice(Connection conn, Device device){

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
            System.out.println("create successful ==> " + device);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
