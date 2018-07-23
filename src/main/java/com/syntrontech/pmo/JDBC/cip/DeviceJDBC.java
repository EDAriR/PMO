package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.Device;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DeviceJDBC {

//    private static Logger logger = LoggerFactory.getLogger(DeviceJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM device ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO device " +
            "(sequence, id, name, mac_address, serial_number, unit_id, " +
            "unit_name, tenant_id, status, createtime, createby, updatetime, updateby) "

            + "VALUES (nextval('device_sequence_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM device WHERE id=? and tenant_id='TTSHB' AND status='ENABLED';";

    public static void main(String[] args) {

        Connection conn = new CIP_GET_CONNECTION().getConn();
        DeviceJDBC s = new DeviceJDBC();


        Date star_time = new Date(new java.util.Date().getTime());
        List<HashMap<String, String>> ss = s.getAllDevice(conn);
        Date end_time = new Date(new java.util.Date().getTime());

//        System.out.println("star_time:" + star_time.toInstant());
//        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        Device d = s.insertDevice(s.getTestDevice());

        System.out.println(d);

        Device dev = s.getOne(d.getId());

        System.out.println(dev);

    }

    private Device getTestDevice(){

        Device device = new Device();
        device.setId("87878");
        device.setName("4687");
        device.setSerialNumber("8877");
        device.setUnitId("JDBCTest66");
        device.setUnitName("JDBCTest66");
        device.setTenantId("TTSHB");
        java.util.Date date = new java.util.Date();
        device.setCreateTime(new java.util.Date());
        device.setCreateBy("TTSHB");
        device.setUpdateTime(date);
        device.setUpdateBy("TTSHB");
        device.setStatus(ModelStatus.ENABLED);
        return device;
    }

    public Device getOne(String id){

        Connection conn = new CIP_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        Device device = new Device();
        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
//            logger.info(pstmt.toString());
            System.out.println(Calendar.getInstance().getTime() + "  UnitMetaJDBC:" + pstmt.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    device.setId(rs.getString("id"));
                    device.setSerialNumber(rs.getString("serial_number"));
                    device.setMacAddress(rs.getString("mac_address"));
                    device.setTenantId(rs.getString("tenant_id"));
                    device.setUnitId(rs.getString("unit_id"));
                    device.setUnitName(rs.getString("unit_name"));
                    device.setCreateBy(rs.getString("createby"));
                }
            }

        } catch (SQLException e) {
            System.out.println(Calendar.getInstance().getTime() + "  UnitMetaJDBC:" + "getOneSubject fail " + conn + " || " + pstmt);

            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.info("conn or pstmt close fail" + conn + " || " + pstmt);
                System.out.println(Calendar.getInstance().getTime() + "  UnitMetaJDBC:" + "conn or pstmt close fail" + conn + " || " + pstmt);
            }
        }

        return device;
    }

    public Device insertDevice(Device device){

        Connection conn = new CIP_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            pstmt.setString(1, device.getId());
            pstmt.setString(2, device.getName());
            String mac_address = device.getMacAddress() != null ? device.getMacAddress() : null;
            pstmt.setString(3, mac_address);
            pstmt.setString(4, device.getSerialNumber());
            pstmt.setString(5, device.getUnitId());

            pstmt.setString(6, device.getUnitName());
            pstmt.setString(7, device.getTenantId());
            pstmt.setString(8, device.getStatus().toString());
            pstmt.setTimestamp(9, new Timestamp(device.getCreateTime().getTime()));
            pstmt.setString(10, device.getCreateBy());

            pstmt.setTimestamp(11, new Timestamp(device.getUpdateTime().getTime()));
            pstmt.setString(12, device.getUpdateBy());

//            logger.info(pstmt.toString());
            System.out.println(Calendar.getInstance().getTime() + "  DeviceJDBC:" + pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.info("conn or pstmt close fail" + conn + " || " + pstmt);
                System.out.println(Calendar.getInstance().getTime() + "  DeviceJDBC:" + "conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
//        logger.info("create successful ==> " + device);
        System.out.println(Calendar.getInstance().getTime() + "  DeviceJDBC:" + "create successful ==> " + device);
        return device;
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

//                    logger.info("device:" + device);
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
                System.out.println(Calendar.getInstance().getTime() + "  DeviceJDBC:" + "conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return devices;
    }

}
