package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.syncare1.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocationJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM location WHERE sync_status = 'N' ORDER BY id;";
    private static final String UPDATE = "UPDATE location SET sync_status= 'Y' WHERE id=? ;";


    public static void main(String[] args) throws SQLException {

        LocationJDBC s = new LocationJDBC();
        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        Date star_time = new Date();
        List<Location> ss = s.getAllLocation(conn);
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        ss.forEach(sss -> s.updateLocation(new Syncare1_GET_CONNECTION().getConn(), sss.getId()));

    }

    public List<Location> getAllLocation(Connection conn) {

        List<Location> locations = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Location location = new Location();

                    location.setId(rs.getString("id"));
                    location.setName(rs.getString("name"));
                    location.setCity(rs.getString("city"));
                    location.setAddress(rs.getString("address"));
                    location.setContact(rs.getString("contact"));
                    location.setPhone(rs.getString("phone"));

//                    System.out.println("LocationJDBC ==>" + location);
                    locations.add(location);
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
        return locations;
    }

    public void updateLocation(Connection conn, String id) {

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
}
