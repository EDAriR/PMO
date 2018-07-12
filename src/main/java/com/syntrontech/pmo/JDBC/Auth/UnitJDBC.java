package com.syntrontech.pmo.JDBC.Auth;

import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.model.common.ModelStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UnitJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM unit WHERE tenant_id='DEFAULT_TENANT' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO unit " +
            "(sequence, id, name, parent_id, parent_name, tenant_id, meta, createtime, createby," +
            "updatetime, updateby, status) "
            + "VALUES (nextval('unit_sequence_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM unit WHERE id=? and tenant_id='DEFAULT_TENANT'" +
            " AND status='ENABLED';";
// sequence,
// id, name, parent_id,
// parent_name, tenant_id,
// meta, createtime, createby,
// updatetime, updateby, status

    public static void main(String[] args) {

        UnitJDBC s = new UnitJDBC();

        Date star_time = new Date(new java.util.Date().getTime());
//        List<Unit> ss = s.getAllUnits();
        Date end_time = new Date(new java.util.Date().getTime());

//        System.out.println("star_time:" + star_time.toInstant());
//        System.out.println("end_time:" + end_time.toInstant());
//        System.out.println("ss size:" + ss.size());

//        s.insertUnit(s.getTestUnit());

        Unit unit = s.getUnitById("xxx");
        System.out.println(unit.getId() == null);
    }

    public Unit getUnitById(String id) {
        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        Unit unit = new Unit();

        try {
            pstmt = conn.prepareStatement(GET_ONE);

            pstmt.setString(1, id);
            System.out.println(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    unit.setId(rs.getString("id"));
                    unit.setName(rs.getString("name"));
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return unit;
    }

    public void insertUnit(Unit unit){

        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // id, name, parent_id,
            pstmt.setString(1, unit.getId());
            pstmt.setString(2, unit.getName());
            pstmt.setString(3, unit.getParentId());
            // parent_name, tenant_id,
            pstmt.setString(4, unit.getParentName());
            pstmt.setString(5, unit.getTenantId());
            // meta, createtime, createby,
            pstmt.setString(6, unit.getMeta());
            pstmt.setDate(7, new java.sql.Date(unit.getCreateTime().getTime()));
            pstmt.setString(8, unit.getCreateBy());
            // updatetime, updateby, status
            pstmt.setDate(9, new java.sql.Date(unit.getUpdateTime().getTime()));
            pstmt.setString(10, unit.getUpdateBy());
            pstmt.setString(11, unit.getStatus().toString());

            System.out.println(pstmt);

            pstmt.executeUpdate();
            System.out.println("create successful ==> " + unit);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }

    public Unit getTestUnit() {

        Unit unit = new Unit();

        unit.setId("JDBCTest");
        unit.setName("JDBCTest");
        unit.setParentId("1001401");
        unit.setParentName("台東市");
        unit.setTenantId("DEFAULT_TENANT");
        unit.setMeta(null);
        unit.setCreateTime(new Date());
        unit.setCreateBy("systemAdmin");
        unit.setUpdateTime(new Date());
        unit.setUpdateBy("systemAdmin");
        unit.setStatus(ModelStatus.ENABLED);

        return unit;
    }

    List<Unit> getAllUnits(){

        Connection conn = new Auth_GET_CONNECTION().getConn();
        List<Unit> units = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Unit unit = new Unit();

                    unit.setSequence(rs.getLong("sequence"));
                    unit.setId(rs.getString("id"));
                    unit.setName(rs.getString("name"));
                    unit.setParentId(rs.getString("parent_id"));
                    unit.setParentName(rs.getString("parent_name"));
                    unit.setTenantId(rs.getString("tenant_id"));
                    unit.setMeta(rs.getString("meta"));
                    unit.setCreateTime(rs.getDate("createtime"));
                    unit.setCreateBy(rs.getString("createby"));
                    unit.setUpdateTime(rs.getDate("updatetime"));
                    unit.setUpdateBy(rs.getString("updateby"));
                    unit.setStatus(ModelStatus.valueOf(rs.getString("status")));

//                    System.out.println("unit:" + unit);
                    units.add(unit);
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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return units;
    }

}
