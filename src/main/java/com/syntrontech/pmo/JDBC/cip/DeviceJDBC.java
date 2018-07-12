package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.Device;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class DeviceJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM device ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO device " +
            "(sequence, id, name, mac_address, serial_number, unit_id, " +
            "unit_name, tenant_id, status, createtime, createby, updatetime, updateby) "

            + "VALUES (nextval('device_sequence_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";




    public static void main(String[] args) {

        Connection conn = new CIP_GET_CONNECTION().getConn();
        DeviceJDBC s = new DeviceJDBC();


        Date star_time = new Date(new java.util.Date().getTime());
        List<HashMap<String, String>> ss = s.getAllDevice(conn);
        Date end_time = new Date(new java.util.Date().getTime());

//        System.out.println("star_time:" + star_time.toInstant());
//        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        HashMap<String, String> device = new HashMap<>();

        device.put("id" ,"id");
        device.put("name" , "systemAdmin");
        device.put("mac_address" , "mac_address");

        device.put("serial_number" , "serial_number");
        device.put("unit_id" , "100140102310");
        device.put("unit_name" , "其他");
        device.put("tenant_id" , "DEFAULT_TENANT");
        device.put("createtime" ,  String.valueOf(new Date(new java.util.Date().getTime()).getTime()));
        device.put("createby" , "systemAdmin");
        device.put("updatetime" , String.valueOf(new Date(new java.util.Date().getTime()).getTime()));
        device.put("updateby" , "systemAdmin");
        device.put("status" , "ENABLED");
        System.out.println(device);
        s.insertDevice(conn, device);

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
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return devices;
    }


    public void insertDevice(Connection conn, HashMap<String, String> device){

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            pstmt.setString(1, device.get("id"));
            pstmt.setString(2, device.get("name"));
            String mac_address = device.get("mac_address") != null ? device.get("mac_address") : "";
            System.out.println("mac_address==>" + mac_address);
            pstmt.setString(3,  mac_address);
            pstmt.setString(4, device.get("serial_number"));
            pstmt.setString(5, device.get("unit_id"));

            pstmt.setString(6, device.get("unit_name"));
            pstmt.setString(7, device.get("tenant_id"));
            pstmt.setString(8, device.get("status"));
            java.util.Date date = new java.util.Date();
            System.out.println("date :" + date);
            pstmt.setDate(9, new Date(date.getTime()));
            pstmt.setString(10, device.get("createby"));

            java.util.Date utilDate=new java.util.Date();
            java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());

            System.out.println(sqlDate);
            pstmt.setDate(11, sqlDate);
            pstmt.setString(12, device.get("updateby"));

            System.out.println(pstmt);
            pstmt.executeUpdate();
            System.out.println("create successful ==> " + device);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }

    public void insertDevice(Connection conn, Device device){

        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            pstmt.setString(1, device.getId());
            pstmt.setString(2, device.getName());
            String mac_address = device.getMacAddress() != null ? device.getMacAddress() : "";
            pstmt.setString(3, mac_address);
            pstmt.setString(4, device.getSerialNumber());
            pstmt.setString(5, device.getUnitId());

            pstmt.setString(6, device.getUnitName());
            pstmt.setString(7, device.getTenantId());
            pstmt.setString(8, device.getStatus().toString());
            pstmt.setDate(9, new Date(device.getCreateTime().getTime()));
            pstmt.setString(10, device.getCreateBy());

            pstmt.setDate(11, new Date(device.getUpdateTime().getTime()));
            pstmt.setString(12, device.getUpdateBy());

            System.out.println(pstmt);

            pstmt.executeUpdate();
            System.out.println("create successful ==> " + device);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }


}
