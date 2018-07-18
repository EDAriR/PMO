package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.syncare1.model.Mapping;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import com.syntrontech.pmo.syncare1.model.UserValueRecordMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class UserValueRecordMappingJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM user_value_record_mapping WHERE sync_status = 'N' ORDER BY USER_VALUE_RECORD_ID;";
    private static final String UPDATE = "UPDATE user_value_record_mapping SET sync_status= 'Y' WHERE USER_VALUE_RECORD_MAPPING_ID=? ;";

    public static void main(String[] args) throws SQLException {

//        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        UserValueRecordMappingJDBC s = new UserValueRecordMappingJDBC();

        Date star_time = new Date();
        Map<Integer, List<UserValueRecordMapping>> ss = s.getAllUserValueRecordMapping();
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.keySet().size());

        // 3身高  4體重  5 BMI
        // 7 收縮壓 8 舒張壓  9  心跳
        // 136 血糖
        // 13 GH 糖化血色素 14 CHOL 膽固醇 15 TG 三酸甘油脂
        // 16 HLD 高密度脂蛋白 17 LDL 低密度脂蛋白 18 LF 肝功能GOT 19 LF 肝功能GPT  20 RF 肌酸酐

    }

    public Map<Integer, List<UserValueRecordMapping>> getAllUserValueRecordMapping() {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        Map<Integer, List<UserValueRecordMapping>> map  = new HashMap<>();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UserValueRecordMapping userValueRecordMapping = new UserValueRecordMapping();

//                    USER_VALUE_RECORD_MAPPING_ID	TYPE_ID	RECORD_VALUE	USER_VALUE_RECORD_ID
                    userValueRecordMapping.setUserValueRecordMappingId(rs.getInt("USER_VALUE_RECORD_MAPPING_ID"));

                    Mapping m = new Mapping();
                    m.setTypeId(rs.getInt("TYPE_ID"));
                    userValueRecordMapping.setMapping(m);
                    userValueRecordMapping.setRecordValue(rs.getString("RECORD_VALUE"));

                    UserValueRecord userValueRecord = new UserValueRecord();
                    userValueRecord.setBodyValueRecordId(rs.getInt("USER_VALUE_RECORD_ID"));
                    userValueRecordMapping.setUserValueRecord(userValueRecord);


                    System.out.println(userValueRecordMapping);
                    int bodyValueRecordId = userValueRecordMapping.getUserValueRecord().getBodyValueRecordId();

                    List<UserValueRecordMapping> userValueRecordMappinglist = map.get(bodyValueRecordId);
                    if (userValueRecordMappinglist == null || userValueRecordMappinglist.size() == 0){
                        userValueRecordMappinglist = new ArrayList<>();
                        userValueRecordMappinglist.add(userValueRecordMapping);
                        map.put(bodyValueRecordId, userValueRecordMappinglist);
                    }else{
                        userValueRecordMappinglist.add(userValueRecordMapping);
                        map.put(bodyValueRecordId, userValueRecordMappinglist);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || "+ pstmt);
                e.printStackTrace();
            }

        }
        return map;
    }

    public void updateUserValueRecordMapping(String id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, id);
            pstmt.executeUpdate();

            System.out.println("update " + id + " successful ==============");


        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }
}
