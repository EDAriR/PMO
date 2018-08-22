package com.syntrontech.pmo.JDBC.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

import static com.syntrontech.pmo.util.YAMLReader.getSetting;

public class Auth_GET_CONNECTION {

    private static Logger logger = LoggerFactory.getLogger(Auth_GET_CONNECTION.class);

    private static final String DRIVER_PATH = getSetting("postgresql", "driver");
    private static final String DB_PATH = getSetting("auth", "db");
    private static final String DB_USER = getSetting("postgresql", "user");
    private static final String DB_PASSWORD = getSetting("postgresql", "password");

    public static void main(String[] args) {

        new Auth_GET_CONNECTION().getConn();
    }

    public Connection getConn(){

        Connection conn = null;
        System.out.println("connection path " + DB_PATH);

        try {

            Class.forName(DRIVER_PATH);
            conn = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWORD);

        }catch (ClassNotFoundException e) {
            logger.debug("JDBC ClassNotFoundException");
//            System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" +"JDBC ClassNotFoundException");

        } catch (SQLException e) {
            logger.debug("Auth_GET_CONNECTION Failed!");
//            System.out.println(Calendar.getInstance().getTime() + "  UserJDBC:" + "Auth_GET_CONNECTION Failed!");
        }

        if (conn != null)
            return conn;
        else
            throw new NullPointerException();

    }
}
