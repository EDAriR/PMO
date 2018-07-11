package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.syncare1.model.Device;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceSyncare1JDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM device WHERE sync_status = 'N' ORDER BY serial_no;";
    private static final String UPDATE = "UPDATE device SET sync_status= 'Y' WHERE serial_no=? ;";


    public static void main(String[] args) throws SQLException {

        DeviceSyncare1JDBC s = new DeviceSyncare1JDBC();
        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        Date star_time = new Date();
        List<Device> ss = s.getAllDevice(conn);
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        ss.forEach(sss -> s.updateDevice(conn, sss));

    }

    public List<Device> getAllDevice(Connection conn) {

        List<Device> devices = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Device device = new Device();

                    device.setSerialNo(rs.getString("serial_no"));
                    device.setName(rs.getString("name"));
                    String location_id = rs.getString("location_id");
                    System.out.println("location_id :" + location_id);
                    device.setLocation(location_id);
                    device.setSyntronLocationId(rs.getString("syntron_location_id"));

                    System.out.println(device);
                    devices.add(device);
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
        return devices;
    }

    public void updateDevice(Connection conn, Device device) {

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, device.getSerialNo());
            pstmt.executeUpdate();

            System.out.println("update successful ==============");


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

}
