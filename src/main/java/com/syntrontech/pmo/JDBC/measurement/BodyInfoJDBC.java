package com.syntrontech.pmo.JDBC.measurement;

import com.syntrontech.pmo.measurement.BodyInfo;
import com.syntrontech.pmo.measurement.BodyInfo;
import com.syntrontech.pmo.measurement.common.GlucoseType;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.GenderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BodyInfoJDBC {

    private static Logger logger = LoggerFactory.getLogger(BodyInfoJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM body_info ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO body_info " +
            "(sequence, height, weight, bmi, bfp," +
            "recordtime, latitude, longitude, status, createtime, createby," +
            "tenant_id, device_mac_address, " +
            " subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name," +
            "rule_seq, rule_description, unit_id, unit_name," +
            "parent_unit_id, parent_unit_name, device_id)"

            + "VALUES (nextval('body_info_sequence_seq'), ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, " +
            "?, ?, " +
            "?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?," +
            "?, ?, ?);";

    public static void main(String[] args) {

        BodyInfoJDBC bodyInfoJDBC = new BodyInfoJDBC();

        List<BodyInfo> bodyInfos = bodyInfoJDBC.getALl();
        System.out.println(bodyInfos.size());

        BodyInfo b = bodyInfoJDBC.insert(bodyInfoJDBC.gettestBodyInfo());
        System.out.println(b);
    }

//    BodyInfo
public BodyInfo insert(BodyInfo bodyInfo){

    Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
    PreparedStatement pstmt = null;

    try {
        pstmt = conn.prepareStatement(INSERT_STMT);

        // height, weight, bmi, bfp
        pstmt.setDouble(1, bodyInfo.getHeight());
        pstmt.setDouble(2, bodyInfo.getWeight());
        if(bodyInfo.getBmi() != null)
            pstmt.setDouble(3, bodyInfo.getBmi());
        else
            pstmt.setNull(3, Types.DECIMAL);

        if (bodyInfo.getBfp() != null)
            pstmt.setDouble(4, bodyInfo.getBfp());
        else
            pstmt.setNull(4, Types.DECIMAL);

        // recordtime, latitude, longitude
        pstmt.setTimestamp(5, new Timestamp(bodyInfo.getRecordTime().getTime()));
        pstmt.setString(6, bodyInfo.getLatitude());
        pstmt.setString(7, bodyInfo.getLongitude());

        // status, createtime, createby, tenant_id, device_mac_address
        pstmt.setString(8, bodyInfo.getStatus().toString());
        pstmt.setTimestamp(9, new Timestamp(bodyInfo.getCreateTime().getTime()));
        pstmt.setString(10, bodyInfo.getCreateBy());
        pstmt.setString(11, bodyInfo.getTenantId());
        pstmt.setString(12, bodyInfo.getDeviceMacAddress());

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        pstmt.setLong(13, bodyInfo.getSubjectSeq());
        pstmt.setString(14, bodyInfo.getSubjectId());
        pstmt.setString(15, bodyInfo.getSubjectName());
        pstmt.setString(16, bodyInfo.getSubjectGender().toString());
        pstmt.setInt(17, bodyInfo.getSubjectAge());
        pstmt.setString(18, bodyInfo.getSubjectUserId());
        pstmt.setString(19, bodyInfo.getSubjectUserName());

        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id

        if(bodyInfo.getRuleSeq() != null)
            pstmt.setLong(20, bodyInfo.getRuleSeq());
        else
            pstmt.setNull(20, Types.BIGINT);

        if(bodyInfo.getRuleDescription() != null)
            pstmt.setString(21, bodyInfo.getRuleDescription());
        else
            pstmt.setNull(21, Types.VARCHAR);

        pstmt.setString(22, bodyInfo.getUnitId());
        pstmt.setString(23, bodyInfo.getUnitName());
        pstmt.setString(24, bodyInfo.getParentUnitId());
        pstmt.setString(25, bodyInfo.getParentUnitName());
        pstmt.setString(26, bodyInfo.getDeviceId());


        logger.info("sql => " + pstmt);
        pstmt.executeUpdate();
//        logger.info("create bodyInfo successful => " + bodyInfo);


        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                bodyInfo.setSequence(rs.getLong(0));
            }
            rs.close();
        }

    } catch (SQLException e) {
//        logger.debug("create bodyInfo fail =>" + bodyInfo);

        e.printStackTrace();
    } finally {

        try {
            if(pstmt != null)
                pstmt.close();
            conn.close();
        } catch (SQLException e) {
//            logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);

            e.printStackTrace();
        }

    }

    return bodyInfo;
}
    

    public List<BodyInfo> getALl() {

        List<BodyInfo> bodyInfos = new ArrayList<>();

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    BodyInfo bodyInfo = new BodyInfo();

                    bodyInfo.setSequence(rs.getLong("sequence"));

                    bodyInfo.setHeight(rs.getDouble("height"));
                    bodyInfo.setWeight(rs.getDouble("weight"));
                    bodyInfo.setBmi(rs.getDouble("bmi"));
                    bodyInfo.setBfp(rs.getDouble("bfp"));

                    // recordtime, latitude, longitude
                    bodyInfo.setRecordTime(rs.getTimestamp("recordtime"));
                    bodyInfo.setLatitude(rs.getString("latitude"));
                    bodyInfo.setLongitude(rs.getString("longitude"));


                    // status, createtime, createby, tenant_id, device_mac_address
                    // private MeasurementStatusType status;
                    bodyInfo.setStatus(MeasurementStatusType.valueOf(rs.getString("status")));
                    bodyInfo.setCreateTime(rs.getTimestamp("createtime"));
                    bodyInfo.setCreateBy(rs.getString("createby"));
                    bodyInfo.setTenantId(rs.getString("tenant_id"));
                    bodyInfo.setDeviceMacAddress(rs.getString("device_mac_address"));

                    // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
                    bodyInfo.setSubjectSeq(rs.getLong("subject_seq"));
                    bodyInfo.setSubjectId(rs.getString("subject_id"));
                    bodyInfo.setSubjectName(rs.getString("subject_name"));
                    bodyInfo.setSubjectGender(GenderType.valueOf(rs.getString("subject_gender")));
                    bodyInfo.setSubjectAge(rs.getInt("subject_age"));
                    bodyInfo.setSubjectUserId(rs.getString("subject_user_id"));
                    bodyInfo.setSubjectUserName(rs.getString("subject_user_name"));

                    // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
                    bodyInfo.setRuleSeq(rs.getLong("rule_seq"));
                    bodyInfo.setRuleDescription(rs.getString("rule_description"));
                    bodyInfo.setUnitId(rs.getString("unit_id"));
                    bodyInfo.setUnitName(rs.getString("unit_name"));
                    bodyInfo.setParentUnitId(rs.getString("parent_unit_id"));
                    bodyInfo.setParentUnitName(rs.getString("parent_unit_name"));
                    bodyInfo.setDeviceId(rs.getString("device_id"));

                    bodyInfos.add(bodyInfo);
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
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

            return bodyInfos;

        }
    }

    public BodyInfo gettestBodyInfo() {

        BodyInfo testBodyInfo = new BodyInfo();

        testBodyInfo.setHeight(100.0);
        testBodyInfo.setWeight(100.0);

        // recordtime, latitude, longitude
        testBodyInfo.setRecordTime(new java.util.Date());
        testBodyInfo.setLatitude("0");
        testBodyInfo.setLongitude("0");


        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        testBodyInfo.setStatus(MeasurementStatusType.EXISTED);
        testBodyInfo.setCreateTime(new Date());
        testBodyInfo.setCreateBy("systemAdmin");
        testBodyInfo.setTenantId("DEFAULT_TENANT");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        testBodyInfo.setSubjectSeq((long)1);
        testBodyInfo.setSubjectId("systemAdmin");
        testBodyInfo.setSubjectName("systemAdmin");
        testBodyInfo.setSubjectGender(GenderType.MALE);
        testBodyInfo.setSubjectAge(0);
        testBodyInfo.setSubjectUserId("systemAdmin");
        testBodyInfo.setSubjectUserName("systemAdmin");

        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        testBodyInfo.setUnitId("100140102310");
        testBodyInfo.setUnitName("");
        testBodyInfo.setParentUnitId("1001401");
        testBodyInfo.setParentUnitName("");

        return testBodyInfo;
    }
}
