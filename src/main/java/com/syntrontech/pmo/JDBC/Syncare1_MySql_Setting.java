package com.syntrontech.pmo.JDBC;

public class Syncare1_MySql_Setting {

    public String getConn_str(){
        String conn_str = "jdbc:mysql://localhost:3307/SynCare"
                + "?user=root&password=1qaz2wsx" +
                "&useUnicode=true&characterEncoding=UTF8";
        return conn_str;
    }

    public String getDriver(){
        String driver = "com.mysql.cj.jdbc.Driver";
        return driver;
    }

}
