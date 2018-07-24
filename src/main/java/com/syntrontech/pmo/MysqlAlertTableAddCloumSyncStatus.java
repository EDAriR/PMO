package com.syntrontech.pmo;

import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlAlertTableAddCloumSyncStatus {

    private static Logger logger = LoggerFactory.getLogger(MysqlAlertTableAddCloumSyncStatus.class);

    private static final String DB_PATH = "jdbc:mysql://localhost:3307/SynCare"
            + "?user=root&password=1qaz2wsx" +
            "&useUnicode=true&characterEncoding=UTF8";

    private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) throws SQLException {


        MysqlAlertTableAddCloumSyncStatus msq = new MysqlAlertTableAddCloumSyncStatus();

        msq.alertTable();
    }

    public void alertTable() throws SQLException {

        Connection conn = null;

        try {

            Class.forName(DRIVER_PATH);
            conn = DriverManager.getConnection(DB_PATH);

            System.out.println("Connection MySQL ");

            Statement stmt = conn.createStatement();
            String sql = "show tables;";
            ResultSet result = stmt.executeQuery(sql);

            if (result != null) {
                while (result.next()) {

                    String name = result.getString(1);
                    System.out.println("Table Name : " + name + "\t");

                    if (name.equals("DATABASECHANGELOG") || name.equals("DATABASECHANGELOGLOCK"))
                        continue;

                    sql = "ALTER TABLE " + name + " ADD sync_status varchar(2) DEFAULT 'N'";
//                    sql = "ALTER TABLE " + name +  " DROP sync_status";

                    try {
                        PreparedStatement stmt2 = conn.prepareStatement(sql);
                        int rs = stmt2.executeUpdate(sql);
                        System.out.println(rs);

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
            conn.close();
        }
    }

}
