package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.ModelMgmtStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class UnitMetaJDBC {

    private static Logger logger = LoggerFactory.getLogger(UnitMetaJDBC.class);

    private static final String GET_ALL_STMT = "select * from unit_meta WHERE tenant_id='DEFAULT_TENANT' AND unit_status='ENABLED' order by sequence;";
    private static final String INSERT_STMT = "INSERT INTO unit_meta " +
            "(sequence, unit_id, unit_name, unit_parent_id, " +
            "unit_parent_name, unit_status, tenant_id, category, " +
            "contact, address, home_phone, mobile_phone," +
            " comment, createtime, createby, updatetime, updateby) "
            + "VALUES (nextval('unit_meta_sequence_seq'), ?, ?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, ?, ?, ?);";
//        sequence, unit_id, unit_name, unit_parent_id,
//        unit_parent_name, unit_status, tenant_id, category,
//        contact, address, home_phone, mobile_phone
//        comment, createtime, createby, updatetime
//        updateby

    private static final String GET_ONE = "SELECT * FROM unit_meta WHERE unit_id=? and tenant_id='DEFAULT_TENANT' AND unit_status='ENABLED';";

    public static void main(String[] args) {

        UnitMetaJDBC s = new UnitMetaJDBC();

        Date star_time = new Date(new java.util.Date().getTime());
        List<UnitMeta> ss = s.getAllUnitMetas();
        Date end_time = new Date(new java.util.Date().getTime());
        Map<String, List<UnitMeta>> map = ss.stream()
                .collect(Collectors.groupingBy(UnitMeta::getCategory));


        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        s.insertUnitMeta(s.getTestUnitMeta());

        UnitMeta unit = s.getUnitMetaById("1001401");

        for (String key:map.keySet()) {
            System.out.println(key + " : " + map.get(key)
                    .stream()
                    .map(um -> um.getUnitParentName())
                    .collect(toSet()));
        }

        for (String key:map.keySet()) {
            System.out.println(key + " : " + map.get(key)
                    .stream()
                    .map(um -> um.getUnitParentId())
                    .collect(toSet()));
        }

    }

    public UnitMeta getUnitMetaById(String id) {
        Connection conn = new CIP_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        UnitMeta unit = new UnitMeta();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
            logger.info(pstmt.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    unit.setUnitId(rs.getString("unit_id"));
                    unit.setUnitName(rs.getString("unit_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.info("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }
        }

        return unit;
    }

    public UnitMeta insertUnitMeta(UnitMeta unitMeta){

        UnitMeta old = getUnitMetaById(unitMeta.getUnitId());
        if (old != null){
            logger.info("UnitMeta = " + old);
            if (old.getUnitId() != null && !old.getUnitId().equals(""))
                return old;
        }

        Connection conn = new CIP_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // sequence, unit_id, unit_name, unit_parent_id,
            pstmt.setString(1, unitMeta.getUnitId());
            pstmt.setString(2, unitMeta.getUnitName());
            pstmt.setString(3, unitMeta.getUnitParentId());
            // unit_parent_name, unit_status, tenant_id, category,
            pstmt.setString(4, unitMeta.getUnitParentName());
            pstmt.setString(5, unitMeta.getUnitStatus().toString());
            pstmt.setString(6, unitMeta.getTenantId());
            pstmt.setString(7, unitMeta.getCategory());
            // contact, address, home_phone, mobile_phone
            pstmt.setString(8, unitMeta.getContact());
            pstmt.setString(9, unitMeta.getAddress());
            pstmt.setString(10, unitMeta.getHomePhone());
            pstmt.setString(11, unitMeta.getMobilePhone());

            // comment, createtime, createby, updatetime, updateby
            pstmt.setString(12, unitMeta.getComment());
            pstmt.setTimestamp(13, new java.sql.Timestamp(unitMeta.getCreateTime().getTime()));
            pstmt.setString(14, unitMeta.getCreateBy());
            pstmt.setTimestamp(15, new java.sql.Timestamp(unitMeta.getUpdateTime().getTime()));
            pstmt.setString(16, unitMeta.getUpdateBy());

            logger.info(pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.debug("insertUnitMeta fail" + conn + " || " + pstmt);
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
        logger.info("create unitMeta successful ==> " + unitMeta);
        return unitMeta;
    }

    public UnitMeta getTestUnitMeta() {

        UnitMeta unitMeta = new UnitMeta();
//        sequence, unit_id, unit_name, unit_parent_id
//        unit_parent_name, unit_status, tenant_id, category
//        contact, address, home_phone, mobile_phone
//        comment, createtime, createby, updatetime
//        updateby

        unitMeta.setUnitId("00001" + Math.random());
        unitMeta.setUnitName("JDBCTest");
        unitMeta.setUnitParentId("1001401");
        unitMeta.setUnitParentName("台東市");
        unitMeta.setUnitStatus(ModelMgmtStatus.ENABLED); // ModelMgmtStatus
        unitMeta.setTenantId("DEFAULT_TENANT");
        unitMeta.setCategory("台東市");
        unitMeta.setContact("systemAdmin");
        unitMeta.setAddress("=---=三");
        unitMeta.setHomePhone("66666");
        unitMeta.setMobilePhone("6666");
        unitMeta.setComment("");
        Date date = new Date();
        unitMeta.setCreateTime(date); // Date
        unitMeta.setCreateBy("systemAdmin");
        unitMeta.setUpdateTime(date); // Date
        unitMeta.setUpdateBy("systemAdmin");

        return unitMeta;
    }

    List<UnitMeta> getAllUnitMetas(){

        Connection conn = new CIP_GET_CONNECTION().getConn();
        List<UnitMeta> units = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    UnitMeta unitMeta = new UnitMeta();

                    // sequence, unit_id, unit_name, unit_parent_id,
                    unitMeta.setSequence(rs.getLong("sequence"));
                    unitMeta.setUnitId(rs.getString("unit_id"));
                    unitMeta.setUnitName(rs.getString("unit_name"));
                    unitMeta.setUnitParentId(rs.getString("unit_parent_id"));
                    // unit_parent_name, unit_status, tenant_id, category,
                    unitMeta.setUnitParentName(rs.getString("unit_parent_name"));
                    unitMeta.setUnitStatus(ModelMgmtStatus.valueOf(rs.getString("unit_status")));
                    unitMeta.setTenantId(rs.getString("tenant_id"));
                    unitMeta.setCategory(rs.getString("category"));
                    // contact, address, home_phone, mobile_phone
                    unitMeta.setContact(rs.getString("contact"));
                    unitMeta.setAddress(rs.getString("address"));
                    unitMeta.setHomePhone(rs.getString("home_phone"));
                    unitMeta.setMobilePhone(rs.getString("mobile_phone"));
                    // comment, createtime, createby, updatetime, updateby
                    unitMeta.setCreateTime(rs.getTimestamp("createtime"));
                    unitMeta.setCreateBy(rs.getString("createby"));
                    unitMeta.setUpdateTime(rs.getTimestamp("updatetime"));
                    unitMeta.setUpdateBy(rs.getString("updateby"));

                    units.add(unitMeta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
        return units;
    }
}
