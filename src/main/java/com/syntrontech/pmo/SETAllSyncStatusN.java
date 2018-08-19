package com.syntrontech.pmo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static com.syntrontech.pmo.util.YAMLReader.getSetting;

public class SETAllSyncStatusN {

    private static Logger logger = LoggerFactory.getLogger(SETAllSyncStatusN.class);

    private static final String DB_PATH = getSetting("syncare1", "db");

    private static final String DRIVER_PATH = getSetting("mysql", "driver");


    public static void main(String[] args) throws SQLException {


        SETAllSyncStatusN msq = new SETAllSyncStatusN();

        msq.setN();
    }


    public void setN() throws SQLException {

        Connection conn = null;

        try {

            Class.forName(DRIVER_PATH);
            conn = DriverManager.getConnection(DB_PATH);

            System.out.println("Connection MySQL ");

            String sql = "show tables;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {

                    String name = result.getString(1);
                    System.out.println("Table Name : " + name + "\t");

                    if (name.equals("DATABASECHANGELOG") || name.equals("DATABASECHANGELOGLOCK"))
                        continue;

                    try {

                        String UPDATE = "UPDATE " + name +" SET sync_status='N' WHERE sync_status='Y';";

                        PreparedStatement stmt2 = conn.prepareStatement(UPDATE);

                        System.out.println(stmt2);
                        stmt2.executeUpdate();


                    } catch (SQLException e) {
                        System.out.println(">> fail table name: " + name);
                        System.out.println(">>" + e.getMessage());
                        continue;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            logger.warn("com.mysql.cj.jdbc.Driver ClassNotFoundException ");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.commit();
            conn.close();
        }
    }

}
