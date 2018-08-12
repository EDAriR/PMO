package com.syntrontech.pmo.JDBC.syncare1JDBC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.syntrontech.pmo.util.YAMLReader.getSetting;

public class Syncare1_GET_CONNECTION {

    private static Logger logger = LoggerFactory.getLogger(Syncare1_GET_CONNECTION.class);


    private static final String DB_PATH = getSetting("syncare1", "db");

    private static final String DRIVER_PATH = getSetting("mysql", "driver");

    public static void main(String[] args) {
        new Syncare1_GET_CONNECTION().getConn();
    }

    public Connection getConn(){

        Connection conn = null;

        try {

            Class.forName(DRIVER_PATH);
            conn = DriverManager.getConnection(DB_PATH);

        }catch (ClassNotFoundException e) {
            logger.warn("com.mysql.cj.jdbc.Driver ClassNotFoundException ");
        } catch (SQLException e) {
            logger.warn("Syncare1_GET_CONNECTION SQLException ");
            e.printStackTrace();

        }

        if (conn != null)
            return conn;
        else
            throw new NullPointerException();

    }

}
