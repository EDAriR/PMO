package com.syntrontech.pmo.JDBC.cip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CIP_GET_CONNECTION {

    private static final String DRIVER_PATH = "org.postgresql.Driver";
    private static final String DB_PATH = "jdbc:postgresql://127.0.0.1:5432/cipdb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1qaz2wsx";

    public Connection getConn(){

        Connection conn = null;

        try {

            Class.forName(DRIVER_PATH);
            System.out.println("Connection postgresql ");
            conn = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWORD);

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
}
