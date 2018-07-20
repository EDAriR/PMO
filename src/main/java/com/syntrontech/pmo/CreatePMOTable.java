package com.syntrontech.pmo;

import com.syntrontech.pmo.JDBC.pmo.PMO_GET_CONNECTION;

import java.sql.*;

public class CreatePMOTable {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try{

            conn = new PMO_GET_CONNECTION().getConn();

            String pmoUserSeqSql = "CREATE SEQUENCE pmo_user_sequence_seq";

            stmt = conn.prepareStatement(pmoUserSeqSql);

            stmt.executeQuery();

            System.out.println(stmt);

            String pmoUserSql = "CREATE TABLE pmo_user " +
                    "(sequence bigint PRIMARY KEY DEFAULT nextval('pmo_user_sequence_seq'), " +
                    " user_id VARCHAR(255)  not NULL, " +
                    " pmo_password VARCHAR(255), " +
                    " status VARCHAR(255), " +
                    " synctime timestamp)";

            stmt = conn.prepareStatement(pmoUserSql);

            stmt.executeQuery();

            System.out.println(stmt);

            System.out.println("Created table  pmo_user in pmodb ...");

            String pmoResultSeqSql = "CREATE SEQUENCE pmo_result_sequence_seq";

            stmt = conn.prepareStatement(pmoResultSeqSql);

            stmt.executeQuery();

            System.out.println(stmt);

            String pmoResultSql = "CREATE TABLE pmo_result " +
                    "(sequence bigint PRIMARY KEY DEFAULT nextval('pmo_result_sequence_seq'), " +
                    " user_id VARCHAR(255)  not NULL, " +
                    " result VARCHAR(255), " +
                    " measurement_type VARCHAR(255), " +
                    " status VARCHAR(255), " +
                    " synctime timestamp)";


            stmt = conn.prepareStatement(pmoResultSql);

            stmt.executeQuery();

            System.out.println(stmt);

            System.out.println("Created table  pmo_user in pmodb...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}
