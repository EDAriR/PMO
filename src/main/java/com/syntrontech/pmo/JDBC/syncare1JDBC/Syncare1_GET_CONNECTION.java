package com.syntrontech.pmo.JDBC.syncare1JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Syncare1_GET_CONNECTION {

    private static final String DB_PATH = "jdbc:mysql://localhost:3307/SynCare"
            + "?user=root&password=1qaz2wsx" +
            "&useUnicode=true&characterEncoding=UTF8";

    private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";

    public Connection getConn(){

        Connection conn = null;

        try {

            Class.forName(DRIVER_PATH);
            System.out.println("Connection MySQL ");
            conn = DriverManager.getConnection(DB_PATH);

        }catch (ClassNotFoundException e) {

            System.out.println("Where is your MySQL JDBC Driver? "
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

}
