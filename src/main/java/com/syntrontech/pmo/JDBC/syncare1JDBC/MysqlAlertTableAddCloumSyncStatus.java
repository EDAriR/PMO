package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.JDBC.Syncare1_MySql_Setting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlAlertTableAddCloumSyncStatus {

    public static void main( String[] args ) throws SQLException
    {
        Connection conn = null;
        String sql;

        Syncare1_MySql_Setting conn_setting = new Syncare1_MySql_Setting();
        String conn_str = conn_setting.getConn_str();
        try {
            Class.forName(conn_setting.getDriver());

System.out.println("Connection MySQL ");

            conn = DriverManager.getConnection(conn_str);
            Statement stmt = conn.createStatement();
            sql = "show tables;";
            ResultSet result = stmt.executeQuery(sql);

            List<String> tableNames = new ArrayList<>();
            if (result != null) {
                while (result.next()) {

                    String name = result.getString(1);
                    System.out.println("Table Name : " + name + "\t");

                    if (name.equals("DATABASECHANGELOG") || name.equals("DATABASECHANGELOGLOCK"))
                        continue;

                    sql = "ALTER TABLE " + name +  " ADD sync_status varchar(2) DEFAULT 'N'";
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

        } catch (SQLException e) {
System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
