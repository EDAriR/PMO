package com.syntrontech.pmo.JDBC.cip;

import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.model.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

public class SubjectJDBC {

    private static Logger logger = LoggerFactory.getLogger(SubjectJDBC.class);

    private static final String GET_ALL_STMT = "SELECT * FROM subject ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO subject " +
            "(sequence, id, name, gender, birthday, home_phone, address, ethnicity, " +
            "personal_history, family_history, smoke, drink, chewing_areca, user_id, unit_id, unit_name," +
            "tenant_id, createtime, createby, updatetime, updateby, status) "
            + "VALUES (nextval('subject_sequence_seq'), ?, ?, ?, ?, ?, ?, ?," +
            " ?, ?, ?, ?, ?, ?, ?, ?," +
            " ?, ?, ?, ?, ?, ?);";

//    sequence, id, name, gender, birthday, home_phone, address, ethnicity
//    personal_history, family_history, smoke, drink, chewing_areca, user_id, unit_id, unit_name
//    tenant_id, createtime, createby, updatetime, updateby, status

    private static final String GET_ONE = "SELECT * FROM subject WHERE id=? AND user_id=? AND tenant_id='TTSHB' AND status='ENABLED';";

    public static void main(String[] args) throws SQLException {


        SubjectJDBC s = new SubjectJDBC();
//        Date star_time = new Date();
//        List<Subject> ss = s.getAllSubjects();
//        Date end_time = new Date();

//        System.out.println("star_time:" + star_time.toInstant());
//        System.out.println("end_time:" + end_time.toInstant());
//        System.out.println("ss size:" + ss.size());

        Subject subject = s.getSubject();
        Subject sb = s.insertSubject(subject);

        System.out.println(sb);

        Subject ssb = s.getOneSubject(sb.getId(), sb.getUserId());

        System.out.println(ssb);

    }

    public Subject getOneSubject(String id, String user_id) throws SQLException {

        if(id == null || user_id == null)
            return null;

        id = id.toUpperCase().trim();
        user_id = user_id.toUpperCase().trim();

        Connection conn = new CIP_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;
        ResultSet rs;

        Subject subject = null;
        try {
            pstmt = conn.prepareStatement(GET_ONE);
            pstmt.setString(1, id);
            pstmt.setString(2, user_id);
            rs = pstmt.executeQuery();


            if (rs != null) {
                while (rs.next()) {

                    subject = new Subject();
//                    sequence, id, name, gender
                    subject.setSequence(rs.getLong("sequence"));
                    subject.setId(rs.getString("id"));
                    subject.setName(rs.getString("name"));
                    GenderType gender = rs.getString("gender") != null ? GenderType.valueOf(rs.getString("gender")) : null;
                    subject.setGender(gender);

//                    birthday, home_phone, address, ethnicity
                    subject.setBirthday(rs.getTimestamp("birthday"));
                    subject.setHomePhone(rs.getString("home_phone"));
                    subject.setAddress(rs.getString("address"));

//                    EthnicityType ethnicityType = rs.getString("gender") != null ? EthnicityType.valueOf(rs.getString("gender")) : null;
//                    subject.setEthnicity(ethnicityType);

//                    personal_history, family_history, smoke, drink

                    List<PersonalHistoryType> personal_history = Arrays.asList((String[]) rs.getArray("personal_history").getArray())
                            .stream()
                            .map(ph -> PersonalHistoryType.valueOf(ph))
                            .collect(Collectors.toList());

                    subject.setPersonalHistory(personal_history);

                    List<FamilyHistoryType> family_history = Arrays.asList((String[]) rs.getArray("family_history").getArray())
                            .stream()
                            .map(ph -> FamilyHistoryType.valueOf(ph))
                            .collect(Collectors.toList());
                    subject.setFamilyHistory(family_history);

                    SmokeType smoke = rs.getString("smoke") != null ? SmokeType.valueOf(rs.getString("smoke")) : null;
                    subject.setSmoke(smoke);

                    DrinkType drink = rs.getString("drink") != null ? DrinkType.valueOf(rs.getString("drink")) : null;
                    subject.setDrink(drink);

//                    chewing_areca, user_id, unit_id, unit_name
                    ChewingArecaType chewingAreca = rs.getString("chewing_areca") != null ?
                            ChewingArecaType.valueOf(rs.getString("chewing_areca")) : null;
                    subject.setChewingAreca(chewingAreca);

                    subject.setUserId(rs.getString("user_id"));
                    subject.setUnitId(rs.getString("unit_id"));
                    subject.setUnitName(rs.getString("unit_name"));

//                    tenant_id, createtime, createby, updatetime
                    subject.setTenantId(rs.getString("tenant_id"));
                    subject.setCreateTime(rs.getTimestamp("createtime"));
                    subject.setCreateBy(rs.getString("createby"));
                    subject.setUpdateTime(rs.getTimestamp("updatetime"));

//                    updateby, status
                    subject.setUpdateBy(rs.getString("updateby"));
                    ModelStatus status = rs.getString("status") != null ? ModelStatus.valueOf(rs.getString("status")) : null;
                    subject.setStatus(status);

                }
            }
        } catch (SQLException e) {
            logger.error("getOneSubject fail " + pstmt);
            throw e;
//            System.out.println(Calendar.getInstance().getTime() + "  SubjectJDBC:" + "getOneSubject fail " + conn + " || " + pstmt);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("conn or pstmt close fail" + pstmt);
                e.printStackTrace();
            }
        }

//        logger.info("getOneSubject successful ==> " + subject);
        logger.info("SubjectJDBC get One Subject successful ==> " + subject);

        return subject;
    }


    List<Subject> getAllSubjects() {

        List<Subject> subjects = new ArrayList<>();
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
                    subject.setSequence(rs.getLong("sequence"));
                    subject.setId(rs.getString("id"));
                    subject.setName(rs.getString("name"));
                    GenderType gender = rs.getString("gender") == null ? GenderType.valueOf(rs.getString("gender")) : null;
                    subject.setGender(gender);

//                    birthday, home_phone, address, ethnicity
                    subject.setBirthday(rs.getTimestamp("birthday"));
                    subject.setHomePhone(rs.getString("home_phone"));
                    subject.setAddress(rs.getString("address"));

                    EthnicityType ethnicityType = rs.getString("gender") == null ? EthnicityType.valueOf(rs.getString("gender")) : null;
                    subject.setEthnicity(ethnicityType);

//                    personal_history, family_history, smoke, drink

                    List<PersonalHistoryType> personal_history = Arrays.asList((String[]) rs.getArray("personal_history").getArray())
                            .stream()
                            .map(ph -> PersonalHistoryType.valueOf(ph))
                            .collect(Collectors.toList());

                    subject.setPersonalHistory(personal_history);

                    List<FamilyHistoryType> family_history = Arrays.asList((String[]) rs.getArray("family_history").getArray())
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
                    subject.setCreateTime(rs.getTimestamp("createtime"));
                    subject.setCreateBy(rs.getString("createby"));
                    subject.setUpdateTime(rs.getTimestamp("updatetime"));

//                    updateby, status
                    subject.setUpdateBy(rs.getString("updateby"));
                    ModelStatus status = rs.getString("status") == null ? ModelStatus.valueOf(rs.getString("status")) : null;
                    subject.setStatus(status);

//                    logger.info("subject:" + subject);
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
//            logger.debug("getAllSubjects fail" + conn + " || " + pstmt);
            System.out.println(Calendar.getInstance().getTime() + "  SubjectJDBC:" + "getAllSubjects fail" + conn + " || " + pstmt);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }
        }

        return subjects;
    }


    public Subject insertSubject(Subject subject) throws SQLException {

        if(subject.getId() == null || subject.getUserId() == null)
            return null;

        Subject old = getOneSubject(subject.getId(), subject.getUserId());

        if(Objects.nonNull(old) && old.getId() != null && old.getSequence() != null)
            return old;

        Connection conn = new CIP_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            //    sequence, id, name, gender, birthday,
            pstmt.setString(1, subject.getId().toUpperCase().trim());
            pstmt.setString(2, subject.getName());
            pstmt.setString(3, subject.getGender().toString());
            pstmt.setTimestamp(4, new java.sql.Timestamp(subject.getBirthday().getTime()));

            //    home_phone, address, ethnicity
            pstmt.setString(5, subject.getHomePhone());
            pstmt.setString(6, subject.getAddress());
            pstmt.setString(7, subject.getEthnicity().toString());

            //    personal_history, family_history, smoke, drink,
            Array personalHistory = conn.createArrayOf("varchar", subject.getPersonalHistory());
            pstmt.setArray(8, personalHistory);

            Array familyHistory = conn.createArrayOf("varchar", subject.getFamilyHistory());
            pstmt.setArray(9, familyHistory);
            pstmt.setString(10, subject.getSmoke().toString());
            pstmt.setString(11, subject.getDrink().toString());

            // chewing_areca, user_id, unit_id, unit_name
            pstmt.setString(12, subject.getChewingAreca().toString());
            pstmt.setString(13, subject.getUserId().toUpperCase().trim());
            pstmt.setString(14, subject.getUnitId());
            pstmt.setString(15, subject.getUnitName());

            //    tenant_id, createtime, createby, updatetime, updateby, status
            pstmt.setString(16, subject.getTenantId());

            pstmt.setTimestamp(17, new java.sql.Timestamp(subject.getCreateTime().getTime()));
            pstmt.setString(18, subject.getCreateBy());
            pstmt.setTimestamp(19, new java.sql.Timestamp(subject.getUpdateTime().getTime()));
            pstmt.setString(20, subject.getUpdateBy());
            pstmt.setString(21, subject.getStatus().toString());

            logger.info(pstmt.toString());
//            System.out.println(Calendar.getInstance().getTime() + "  SubjectJDBC:" + pstmt.toString());

            pstmt.executeUpdate();
            logger.info("insert Subject " + pstmt);

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                long seq = rs.getLong(1);
                subject.setSequence(seq );
            }else{
                subject = getOneSubject(subject.getId(), subject.getUserId());
            }
            if(subject.getSequence() == null)
                subject = getOneSubject(subject.getId(), subject.getUserId());

            rs.close();

//            System.out.println(Calendar.getInstance().getTime() + "  SubjectJDBC:" + "create successful ==> " + subject);

        } catch (SQLException e) {
            logger.error("create subject fail " + pstmt);
//            System.out.println(Calendar.getInstance().getTime() + "  SubjectJDBC:" + "create subject fail" + conn + " || " + pstmt);
            e.printStackTrace();
            throw e;
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.error("conn or pstmt close fail " + pstmt);
                e.printStackTrace();
            }
        }
        logger.info("create successful ==> " + subject);
        return subject;
    }

    Subject getSubject() {

        Subject subject = new Subject();

//        sequence, id, name, gender
        subject.setId("userJDBCTest");
        subject.setName("SubjectJDBCTest");
        subject.setGender(GenderType.FEMALE);

//        birthday, home_phone, address, ethnicity
        subject.setBirthday(new Date(66666666));
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

        subject.setUserId("userJDBCTest");
        subject.setUnitId("JDBCTest66");
        subject.setUnitName("JDBCTest");

//        tenant_id, createtime, createby, updatetime
        subject.setTenantId("TTSHB");
        subject.setCreateTime(new Date());
        subject.setCreateBy("userJDBCTest");
        subject.setUpdateTime(new Date());

//                    updateby, status
        subject.setUpdateBy("userJDBCTest");
        subject.setStatus(ModelStatus.ENABLED);
        return subject;
    }

}
