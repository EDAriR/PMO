package com.syntrontech.pmo.JDBC.syncare1JDBC;

public class A_DB_conn_str_setting {

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
