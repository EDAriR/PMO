package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class UnitJDBC {

    private static Logger logger = LoggerFactory.getLogger(Auth_GET_CONNECTION.class);

    private static final String GET_ALL_STMT = "SELECT * FROM unit WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO unit " +
            "(sequence, id, name, parent_id, parent_name, tenant_id, meta, createtime, createby," +
            "updatetime, updateby, status) "
            + "VALUES (nextval('unit_sequence_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM unit WHERE id=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";

    private static final String GET_ONE_BY_NAME = "SELECT * FROM unit WHERE name=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";
// sequence,
// id, name, parent_id,
// parent_name, tenant_id,
// meta, createtime, createby,
// updatetime, updateby, status

    public static void main(String[] args) throws SQLException {

        UnitJDBC s = new UnitJDBC();

        List<Unit> ss = s.getAllUnits();

        System.out.println("ss size:" + ss.size());

        Unit insertTest = s.insertUnit(s.getTestUnit());

        System.out.println("insert");
        System.out.println(insertTest);
        Unit unit = s.getUnitById(insertTest.getId());
        System.out.println("get one");
        System.out.println(unit);

    }

    public Unit getUnitByName(String name) throws SQLException {

        if(name == null)
            return null;
        name = name.trim();

        Connection conn = new Auth_GET_CONNECTION().getConn();

        PreparedStatement pstmt = conn.prepareStatement(GET_ONE_BY_NAME);

        return getOne(conn, pstmt, name);

    }

    public Unit getUnitById(String id) throws SQLException {

        if(id == null)
            return null;
        id = id.trim();

        Connection conn = new Auth_GET_CONNECTION().getConn();

        PreparedStatement pstmt = conn.prepareStatement(GET_ONE);

        return getOne(conn, pstmt, id);
    }

    private Unit getOne(Connection conn, PreparedStatement pstmt, String id) throws SQLException {

        Unit unit = new Unit();

        try {

            pstmt.setString(1, id);
            logger.info(pstmt.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    unit.setSequence(rs.getLong("sequence"));
                    unit.setId(rs.getString("id"));
                    unit.setName(rs.getString("name"));
                    unit.setParentId(rs.getString("parent_id"));
                    unit.setParentName(rs.getString("parent_name"));
                    unit.setTenantId(rs.getString("tenant_id"));
                }
            }

        } catch (SQLException e) {
            logger.debug("get Unit  fail " + pstmt);
            throw e;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.info("conn or pstmt close fail " + pstmt);
                e.printStackTrace();
            }

        }
        logger.info("getUnitById successful => " + unit);
        return unit;
    }



    public Unit insertUnit(Unit unit) throws SQLException {

        if(unit == null || unit.getId() == null)
            return null;

        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        Unit old = getUnitById(unit.getId());
        if (old != null){
            if (old.getId() != null && !old.getId().equals(""))
                return old;
        }


        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // id, name, parent_id,
            pstmt.setString(1, unit.getId().trim());
            pstmt.setString(2, unit.getName().trim());
            pstmt.setString(3, unit.getParentId());
            // parent_name, tenant_id,
            pstmt.setString(4, unit.getParentName());
            pstmt.setString(5, unit.getTenantId());
            // meta, createtime, createby,
            pstmt.setString(6, unit.getMeta());
            pstmt.setTimestamp(7, new java.sql.Timestamp(unit.getCreateTime().getTime()));
            pstmt.setString(8, unit.getCreateBy());
            // updatetime, updateby, status
            pstmt.setTimestamp(9, new java.sql.Timestamp(unit.getUpdateTime().getTime()));
            pstmt.setString(10, unit.getUpdateBy());
            pstmt.setString(11, unit.getStatus().toString());

//            logger.info(pstmt.toString());
            logger.info(" UnitJDBC: " + pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.debug("insertUnit fail " + pstmt + "||" + conn);
            throw e;
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.info("conn or pstmt close fail " + pstmt);
                e.printStackTrace();
            }

        }
        logger.info("create unit successful ==> " + unit);
//        System.out.println(Calendar.getInstance().getTime() +" UnitJDBC: " + "create unit successful ==> " + unit);
        return unit;
    }

    public Unit getTestUnit() {

        Unit unit = new Unit();

        unit.setId("JDBCTest66");
        unit.setName("JDBCTest");
        unit.setParentId("1001401");
        unit.setParentName("台東市");
        unit.setTenantId("TTSHB");
        unit.setMeta(null);
        unit.setCreateTime(new Date());
        unit.setCreateBy("TTSHB");
        unit.setUpdateTime(new Date());
        unit.setUpdateBy("TTSHB");
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
                    unit.setUpdateTime(rs.getTimestamp("updatetime"));
                    unit.setUpdateBy(rs.getString("updateby"));
                    unit.setStatus(ModelStatus.valueOf(rs.getString("status")));

//                    logger.info("unit:" + unit);
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
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                System.out.println(Calendar.getInstance().getTime() +" UnitJDBC: " + "conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return units;
    }

}
