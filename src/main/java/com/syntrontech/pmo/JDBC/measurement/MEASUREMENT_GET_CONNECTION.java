package com.syntrontech.pmo.JDBC.measurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.syntrontech.pmo.util.YAMLReader.getSetting;

public class MEASUREMENT_GET_CONNECTION {

    private static Logger logger = LoggerFactory.getLogger(MEASUREMENT_GET_CONNECTION.class);

    private static final String DRIVER_PATH = getSetting("postgresql", "driver");
    private static final String DB_PATH = getSetting("measurement", "db");
    private static final String DB_USER = getSetting("postgresql", "user");
    private static final String DB_PASSWORD = getSetting("postgresql", "password");

    public Connection getConn() {

        Connection conn = null;
        System.out.println("connection path " + DB_PATH);

        try {

            Class.forName(DRIVER_PATH);
            conn = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWORD);

        } catch (ClassNotFoundException e) {
            logger.debug("JDBC ClassNotFoundException");
        } catch (SQLException e) {
            logger.debug("MEASUREMENT_GET_CONNECTION Failed!");

            e.printStackTrace();

        }

        if (conn != null)
            return conn;
        else
            throw new NullPointerException();
    }
}
