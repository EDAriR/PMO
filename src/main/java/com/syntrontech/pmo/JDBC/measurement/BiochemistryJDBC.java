package com.syntrontech.pmo.JDBC.measurement;

import com.syntrontech.pmo.measurement.Biochemistry;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.BiochemistryMappingsProject;
import com.syntrontech.pmo.model.common.GenderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiochemistryJDBC {

    private static Logger logger = LoggerFactory.getLogger(BiochemistryJDBC.class);

    private static final String FIND_MAX_GROUPID = "SELECT MAX(group_id) FROM biochemistry";

    private static final String GET_ALL_STMT = "SELECT * FROM biochemistry ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO biochemistry " +
            "(sequence, value, group_id, biochemistry_mappings_seq, biochemistry_mappings_project, " +
            "recordtime, latitude, longitude," +
            " status, createtime, createby, tenant_id, device_mac_address, " +
            "subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name," +
            "rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id)"
            + "VALUES (nextval('blood_pressure_heartbeat_sequence_seq'), ?, ?, ?, ?, " +
            "?, ?, ?," +
            "?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?);";
    // value, group_id, biochemistry_mappings_seq, biochemistry_mappings_project


    public static void main(String[] args) {
//        biochemistry
        BiochemistryJDBC biochemistryJDBC = new BiochemistryJDBC();

        System.out.println("getGroupId:" + biochemistryJDBC.getGroupId());

        List<Biochemistry> biochemistrys = biochemistryJDBC.getALl();

        System.out.println(biochemistrys.size());

    }

    public Long getGroupId(){

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        Long groupId = (long)0 ;

        try {

            pstmt = conn.prepareStatement(FIND_MAX_GROUPID);
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    groupId = rs.getLong(1);

                }
            }

            System.out.println("sql => " + pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
//            logger.debug("create getGroupId fail =>" + e.getMessage());

            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);

                e.printStackTrace();
            }

        }
        return groupId + 1 ;
    }


    public Biochemistry insert(Connection conn, Biochemistry biochemistry){

//        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // value, group_id, biochemistry_mappings_seq, biochemistry_mappings_project
            pstmt.setString(1, biochemistry.getValue());
            pstmt.setLong(2, biochemistry.getGroupId());
            pstmt.setLong(3, biochemistry.getMappingsSeq());
            pstmt.setString(4, biochemistry.getMappingsProject().getProject());

            // recordtime, latitude, longitude
            pstmt.setTimestamp(5, new Timestamp(biochemistry.getRecordTime().getTime()));
            pstmt.setString(6, biochemistry.getLatitude());
            pstmt.setString(7, biochemistry.getLongitude());

            // status, createtime, createby, tenant_id, device_mac_address
            pstmt.setString(8, biochemistry.getStatus().toString());
            pstmt.setTimestamp(9, new Timestamp(biochemistry.getCreateTime().getTime()));
            pstmt.setString(10, biochemistry.getCreateBy());
            pstmt.setString(11, biochemistry.getTenantId());
            pstmt.setString(12, biochemistry.getDeviceMacAddress());

            // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
            pstmt.setLong(13, biochemistry.getSubjectSeq());
            pstmt.setString(14, biochemistry.getSubjectId());
            pstmt.setString(15, biochemistry.getSubjectName());
            pstmt.setString(16, biochemistry.getSubjectGender().toString());
            pstmt.setInt(17, biochemistry.getSubjectAge());
            pstmt.setString(18, biochemistry.getSubjectUserId());
            pstmt.setString(19, biochemistry.getSubjectUserName());

            // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
            pstmt.setNull(20, Types.BIGINT);
            pstmt.setNull(21, Types.VARCHAR);
            pstmt.setString(22, biochemistry.getUnitId());
            pstmt.setString(23, biochemistry.getUnitName());
            pstmt.setString(24, biochemistry.getParentUnitId());
            pstmt.setString(25, biochemistry.getParentUnitName());
            pstmt.setString(26, biochemistry.getDeviceId());


            System.out.println("sql => " + pstmt);
            pstmt.executeUpdate();
//            logger.info("create biochemistry successful => " + biochemistry);


            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    biochemistry.setSequence(rs.getLong(1));
                }
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                logger.debug("pstmt close fail" + " || " + pstmt);
                e.printStackTrace();
            }

        }
        return biochemistry;
    }

    public List<Biochemistry> getALl(){

        List<Biochemistry> biochemistrys = new ArrayList<>();

        Connection conn = new MEASUREMENT_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Biochemistry biochemistry = new Biochemistry();

                    biochemistry.setSequence(rs.getLong("sequence"));

                    // value, group_id, biochemistry_mappings_seq, biochemistry_mappings_project
                    biochemistry.setValue(rs.getString("value"));
                    biochemistry.setGroupId(rs.getLong("group_id"));
                    biochemistry.setMappingsSeq(rs.getLong("biochemistry_mappings_seq"));

                    String mappingStr = rs.getString("biochemistry_mappings_project");
                    System.out.println(mappingStr);

                    Map<String, BiochemistryMappingsProject> projects = new HashMap<>();
                    System.out.println("???????????????");
                    for(BiochemistryMappingsProject bmp:BiochemistryMappingsProject.values()){
                        projects.put(bmp.getProject(), bmp);
                    }
                    System.out.println(projects);
                    System.out.println("???????????????");

                    BiochemistryMappingsProject pro = mappingStr != null ?  projects.get(mappingStr) : null;
                    biochemistry.setMappingsProject(pro);

                    // recordtime, latitude, longitude
                    biochemistry.setRecordTime(rs.getTimestamp("recordtime"));
                    biochemistry.setLatitude(rs.getString("latitude"));
                    biochemistry.setLongitude(rs.getString("longitude"));

                    // status, createtime, createby, tenant_id, device_mac_address
                    // private MeasurementStatusType status;
                    biochemistry.setStatus(MeasurementStatusType.valueOf(rs.getString("status")));
                    biochemistry.setCreateTime(rs.getTimestamp("createtime"));
                    biochemistry.setCreateBy(rs.getString("createby"));
                    biochemistry.setTenantId(rs.getString("tenant_id"));
                    biochemistry.setDeviceMacAddress(rs.getString("device_mac_address"));

                    // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
                    biochemistry.setSubjectSeq(rs.getLong("subject_seq"));
                    biochemistry.setSubjectId(rs.getString("subject_id"));
                    biochemistry.setSubjectName(rs.getString("subject_name"));
                    biochemistry.setSubjectGender(GenderType.valueOf(rs.getString("subject_gender")));
                    biochemistry.setSubjectAge(rs.getInt("subject_age"));
                    biochemistry.setSubjectUserId(rs.getString("subject_user_id"));
                    biochemistry.setSubjectUserName(rs.getString("subject_user_name"));

                    // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
                    biochemistry.setRuleSeq(rs.getLong("rule_seq"));
                    biochemistry.setRuleDescription(rs.getString("rule_description"));
                    biochemistry.setUnitId(rs.getString("unit_id"));
                    biochemistry.setUnitName(rs.getString("unit_name"));
                    biochemistry.setParentUnitId(rs.getString("parent_unit_id"));
                    biochemistry.setParentUnitName(rs.getString("parent_unit_name"));
                    biochemistry.setDeviceId(rs.getString("device_id"));

                    biochemistrys.add(biochemistry);
                }
            }
        } catch (SQLException e){
//            logger.debug("get all fail" + conn + " || " + pstmt);

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

        }

        return biochemistrys;
    }


}
