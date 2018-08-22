package com.syntrontech.pmo.JDBC.questionnair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.syntrontech.pmo.util.YAMLReader.getSetting;

public class QUESTIONNAIR_GET_CONNECTION {

    private static final String DRIVER_PATH = getSetting("postgresql", "driver");
    private static final String DB_PATH = getSetting("questionnaire", "db");
    private static final String DB_USER = getSetting("postgresql", "user");
    private static final String DB_PASSWORD = getSetting("postgresql", "password");

    public static void main(String[] args) {
        new QUESTIONNAIR_GET_CONNECTION().getConn();
    }

    public Connection getConn(){

        Connection conn = null;
        System.out.println("connection path " + DB_PATH);

        try {

            Class.forName(DRIVER_PATH);
            conn = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWORD);

        }catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver?");
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
