package com.syntrontech.pmo.JDBC.measurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MEASUREMENT_GET_CONNECTION {

    private static Logger logger = LoggerFactory.getLogger(MEASUREMENT_GET_CONNECTION.class);

    private static final String DRIVER_PATH = "org.postgresql.Driver";
    private static final String DB_PATH = "jdbc:postgresql://127.0.0.1:5432/measurementdb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1qaz2wsx";

    public Connection getConn(){

        Connection conn = null;

        try {

            Class.forName(DRIVER_PATH);
            conn = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWORD);

        }catch (ClassNotFoundException e) {
            logger.debug("JDBC not found");
        } catch (SQLException e) {
            logger.debug("Connection Failed!");

            e.printStackTrace();

        }

        if (conn != null)
            return conn;
        else
            throw new NullPointerException();

    }
}
