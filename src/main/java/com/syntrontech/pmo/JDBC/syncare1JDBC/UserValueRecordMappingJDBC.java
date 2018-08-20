package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.Mapping;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import com.syntrontech.pmo.syncare1.model.UserValueRecordMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class UserValueRecordMappingJDBC {

    private static Logger logger = LoggerFactory.getLogger(UserValueRecordMappingJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM user_value_record_mapping WHERE sync_status = 'N' ORDER BY USER_VALUE_RECORD_ID;";
    private static final String UPDATE = "UPDATE user_value_record_mapping SET sync_status= 'Y' WHERE USER_VALUE_RECORD_MAPPING_ID=? ;";
    private static final String GET_BY_RECORD_ID_STMT = "SELECT * FROM user_value_record_mapping WHERE USER_VALUE_RECORD_ID = ? AND sync_status = 'N';";

    public static void main(String[] args) throws SQLException {

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

    public void updateUserValueRecordMapping(
            Connection syncare1conn,
            Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, int bodyValueRecordId) {

        List<UserValueRecordMapping> values = userValueRecordMap.get(bodyValueRecordId);
        values.forEach(v -> updateUserValueRecordMapping(syncare1conn, v.getUserValueRecordMappingId()));

    }

    public List<UserValueRecordMapping> getUserValueRecordMappingByRecordId(int id) throws SQLException {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        List<UserValueRecordMapping> values  = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_BY_RECORD_ID_STMT);
            pstmt.setInt(1, id);
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

                    values.add(userValueRecordMapping);
                }
            }

        } catch (SQLException e) {
            logger.debug("get getUserValueRecordMappingByRecordId fail " + pstmt);
            e.printStackTrace();
           throw e;
        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || "+ pstmt);
                e.printStackTrace();
            }

        }
        return values;
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
            logger.debug("get all fail " + pstmt);
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

    public void updateUserValueRecordMapping(Connection conn, int id) {

//        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("update UserValueRecordMapping USER_VALUE_RECORD_MAPPING_ID=[" + id + "] successful");


        } catch (SQLException e) {
            logger.debug("update User ValueRecordMapping fail " + pstmt);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }
}
