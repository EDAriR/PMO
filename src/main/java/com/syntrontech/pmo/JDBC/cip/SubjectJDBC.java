package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.model.common.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

public class SubjectJDBC {


    private static final String GET_ALL_STMT = "SELECT * FROM subject ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO subject " +
            "(sequence, id, name, gender, birthday, home_phone, address, ethnicity, " +
            "personal_history, family_history, smoke, drink, chewing_areca, user_id, unit_id, unit_name," +
            "tenant_id, createtime, createby, updatetime, updateby, status) "
            + "VALUES (nextval('subject_sequence_seq'), ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?);";

//    sequence, id, name, gender, birthday, home_phone, address, ethnicity
//    personal_history, family_history, smoke, drink, chewing_areca, user_id, unit_id, unit_name
//    tenant_id, createtime, createby, updatetime, updateby, status




    public static void main(String[] args) {


        SubjectJDBC s = new SubjectJDBC();
        Date star_time = new Date();
        List<Subject> ss = s.getAllSubjects();
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        Subject subject = s.getSubject();

        System.out.println("unit_id");
        System.out.println("unit_id");
        System.out.println("unit_id");
        System.out.println("unit_id");
        System.out.println("unit_id");

//        s.insertSubject(conn, subject);

    }


    List<Subject> getAllSubjects(){

        List<Subject> devices = new ArrayList<>();
        Connection conn = new CIP_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Subject subject = new Subject();

//                    sequence, id, name, gender
                    subject.setSequence(rs.getLong(""));
                    subject.setId(rs.getString("id"));
                    subject.setName(rs.getString("name"));
                    GenderType gender = rs.getString("gender") == null ? GenderType.valueOf(rs.getString("gender")) : null;
                    subject.setGender(gender);

//                    birthday, home_phone, address, ethnicity
                    subject.setBirthday(rs.getDate("birthday"));
                    subject.setHomePhone(rs.getString("home_phone"));
                    subject.setAddress(rs.getString("address"));

                    EthnicityType ethnicityType = rs.getString("gender") == null ? EthnicityType.valueOf(rs.getString("gender")) : null;
                    subject.setEthnicity(ethnicityType);

//                    personal_history, family_history, smoke, drink

                    List<PersonalHistoryType> personal_history = Arrays.asList((String[])rs.getArray("personal_history").getArray())
                            .stream()
                            .map(ph -> PersonalHistoryType.valueOf(ph))
                            .collect(Collectors.toList());

                    subject.setPersonalHistory(personal_history);

                    List<FamilyHistoryType> family_history = Arrays.asList((String[])rs.getArray("family_history").getArray())
                            .stream()
                            .map(ph -> FamilyHistoryType.valueOf(ph))
                            .collect(Collectors.toList());
                    subject.setFamilyHistory(family_history);

                    SmokeType smoke = rs.getString("smoke") == null ? SmokeType.valueOf(rs.getString("smoke")) : null;
                    subject.setSmoke(smoke);

                    DrinkType drink = rs.getString("drink") == null ? DrinkType.valueOf(rs.getString("drink")) : null;
                    subject.setDrink(drink);

//                    chewing_areca, user_id, unit_id, unit_name
                    ChewingArecaType chewingAreca = rs.getString("chewing_areca") == null ?
                            ChewingArecaType.valueOf(rs.getString("chewing_areca")) : null;
                    subject.setChewingAreca(chewingAreca);

                    subject.setUserId(rs.getString("user_id"));
                    subject.setUnitId(rs.getString("unit_id"));
                    subject.setUnitName(rs.getString("unit_name"));

//                    tenant_id, createtime, createby, updatetime
                    subject.setTenantId(rs.getString("tenant_id"));
                    subject.setCreateTime(rs.getDate("createtime"));
                    subject.setCreateBy(rs.getString("createby"));
                    subject.setUpdateTime(rs.getDate("updatetime"));

//                    updateby, status
                    subject.setUpdateBy(rs.getString("updateby"));
                    ModelStatus status = rs.getString("status") == null ? ModelStatus.valueOf(rs.getString("status")) : null;
                    subject.setStatus(status);

                    System.out.println("subject:" + subject);
                    devices.add(subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return devices;
    }


    public void insertSubject(Subject subject){

        Connection conn = new CIP_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            //    sequence, id, name, gender, birthday,
            pstmt.setString(1, subject.getId());
            pstmt.setString(2,subject.getName());
            pstmt.setString(3, subject.getGender().toString());
            pstmt.setDate(4,new java.sql.Date(subject.getBirthday().getTime()));

            //    home_phone, address, ethnicity
            pstmt.setString(5, subject.getHomePhone());
            pstmt.setString(6, subject.getAddress());
            pstmt.setString(7, subject.getEthnicity().toString());

            //    personal_history, family_history, smoke, drink,
            Array personalHistory = conn.createArrayOf("varchar", subject.getPersonalHistory());
            pstmt.setArray(8, personalHistory);

            Array familyHistory = conn.createArrayOf("varchar", subject.getFamilyHistory() );
            pstmt.setArray(9, familyHistory);
            pstmt.setString(10, subject.getSmoke().toString());
            pstmt.setString(11, subject.getDrink().toString());

            // chewing_areca, user_id, unit_id, unit_name
            pstmt.setString(12, subject.getChewingAreca().toString());
            pstmt.setString(13, subject.getUserId());
            pstmt.setString(14, subject.getUnitId());
            pstmt.setString(15, subject.getUnitName());

            //    tenant_id, createtime, createby, updatetime, updateby, status
            pstmt.setString(16, subject.getTenantId());
            pstmt.setDate(17, new java.sql.Date(subject.getCreateTime().getTime()));
            pstmt.setString(18, subject.getCreateBy());
            pstmt.setDate(19, new java.sql.Date(subject.getUpdateTime().getTime()));
            pstmt.setString(20, subject.getUpdateBy());
            pstmt.setString(21, subject.getStatus().toString());

            pstmt.executeUpdate();
            System.out.println("create successful ==> " + subject);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Subject getSubject() {

        Subject subject = new Subject();




//        sequence, id, name, gender
        subject.setId("SubjectJDBCTest");
        subject.setName("SubjectJDBCTest");
        subject.setGender(GenderType.FEMALE);

//        birthday, home_phone, address, ethnicity
        subject.setBirthday(new Date(666));
        subject.setHomePhone("home_phone");
        subject.setAddress("address");

        subject.setEthnicity(EthnicityType.HAN);

//        personal_history, family_history, smoke, drink
        subject.setPersonalHistory(Arrays.asList(PersonalHistoryType.HYPERTENSION));
        subject.setFamilyHistory(Arrays.asList(FamilyHistoryType.HYPERTENSION));
        subject.setSmoke(SmokeType.SOCIALITY);
        subject.setDrink(DrinkType.OFTEN);

//        chewing_areca, user_id, unit_id, unit_name
        subject.setChewingAreca(ChewingArecaType.OFTEN);

        subject.setUserId("systemAdmin");
        subject.setUnitId("unit_id");
        subject.setUnitName("unit_name");

//        tenant_id, createtime, createby, updatetime
        subject.setTenantId("tenant_id");
        subject.setCreateTime(new Date());
        subject.setCreateBy("systemAdmin");
        subject.setUpdateTime(new Date());

//                    updateby, status
        subject.setUpdateBy("systemAdmin");
        subject.setStatus(ModelStatus.ENABLED);
        return subject;
    }

}
