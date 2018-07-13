package com.syntrontech.pmo.JDBC.questionnair;

import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.UnitMetaJDBC;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.ModelMgmtStatus;
import com.syntrontech.pmo.questionnair.model.QuestionnairReply;
import com.syntrontech.pmo.questionnair.model.common.UnmodifiableDataStatus;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class QuestionnairReplyJDBC {

    private static final String GET_ALL_STMT = "select * from questionnair_reply WHERE status='EXISTED' order by sequence;";
    private static final String INSERT_STMT = "INSERT INTO questionnair_reply " +
            "(sequence, user_id, tenant_id, questionnaire_seq, " +
            "unit_parent_name, unit_status, tenant_id, category, " +
            "contact, address, home_phone, mobile_phone," +
            " comment, createtime, createby, updatetime, updateby) "
            + "VALUES (nextval('questionnair_reply_sequence_seq'), ?, ?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, ?, ?, ?);";
//        sequence, user_id, tenant_id, questionnaire_seq
//        questionnaire_title, questionnaire_question_seq, questionnaire_question_title, questionnaire_question_option_seq
//        questionnaire_question_option_score, questionnaire_question_answer
//        createtime, createby, updatetime, updateby, status

    private static final String GET_ONE = "SELECT * FROM unit_meta WHERE unit_id=? and tenant_id='DEFAULT_TENANT' AND unit_status='ENABLED';";

    public static void main(String[] args) {

        QuestionnairReplyJDBC s = new QuestionnairReplyJDBC();

        Date star_time = new Date(new java.util.Date().getTime());
        List<QuestionnairReply> ss = s.getAllQuestionnairReplys();
        Date end_time = new Date(new java.util.Date().getTime());

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());
//
//        s.insertUnitMeta(s.getTestQuestionnairReply());
//
//        UnitMeta unit = s.getUnitMetaById("1001401");

    }

    public QuestionnairReply getTestQuestionnairReply() {

        QuestionnairReply questionnairReply = new QuestionnairReply();
//        sequence, user_id, tenant_id, questionnaire_seq
//        questionnaire_question_option_score, questionnaire_question_answer
//        createtime, createby, updatetime, updateby, status

        questionnairReply.setUserId("systemAdmin");
        questionnairReply.setTenantId("DEFAULT_TENANT");
        questionnairReply.setQuestionnairSeq((long)1);

        // questionnaire_title, questionnaire_question_seq, questionnaire_question_title, questionnaire_question_option_seq
        questionnairReply.setQuestionnairTitle("setQuestionnairTitle");
        questionnairReply.setQuestionnairQuestionSeq((long)111);
        questionnairReply.setQuestionnairQuestionTitle("setQuestionnairQuestionTitle");
        // TODO
        Long[] longArray = new Long[]{(long) 1};
        questionnairReply.setQuestionnairQuestionOptionSeq(longArray);


        Date date = new Date();
        questionnairReply.setCreateTime(date); // Date
        questionnairReply.setCreateBy("systemAdmin");
        questionnairReply.setUpdateTime(date); // Date
        questionnairReply.setUpdateBy("systemAdmin");
        questionnairReply.setStatus(UnmodifiableDataStatus.EXISTED);

        return questionnairReply;
    }

    List<QuestionnairReply> getAllQuestionnairReplys(){

        Connection conn = new QUESTIONNAIR_GET_CONNECTION().getConn();
        List<QuestionnairReply> questionnairReplys = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    QuestionnairReply questionnairReply = new QuestionnairReply();

//                    sequence, user_id, tenant_id, questionnaire_seq
                    questionnairReply.setSequence(rs.getLong("sequence"));
                    questionnairReply.setUserId(rs.getString("user_id"));
                    questionnairReply.setTenantId(rs.getString("tenant_id"));
                    questionnairReply.setQuestionnairSeq(rs.getLong("questionnaire_seq"));

//                    questionnaire_title, questionnaire_question_seq, questionnaire_question_title, questionnaire_question_option_seq
                    questionnairReply.setQuestionnairTitle(rs.getString("questionnaire_title"));
                    questionnairReply.setQuestionnairQuestionSeq(rs.getLong("questionnaire_question_seq"));
                    questionnairReply.setQuestionnairQuestionTitle(rs.getString("questionnaire_question_title"));
//                    questionnairReply.setQuestionnairQuestionOptionSeq(rs.getArray("questionnaire_question_option_seq"));
                    System.out.println(rs.getArray("questionnaire_question_option_seq"));
//                    questionnaire_question_option_score, questionnaire_question_answer
                    System.out.println(rs.getArray("questionnaire_question_option_score"));
                    Array array = rs.getArray("questionnaire_question_answer");
                    System.out.println(rs.getArray("questionnaire_question_answer"));


//                    createtime, createby, updatetime, updateby, status
                    questionnairReply.setCreateTime(rs.getDate("createtime"));
                    questionnairReply.setCreateBy(rs.getString("createby"));
                    questionnairReply.setUpdateTime(rs.getDate("updatetime"));
                    questionnairReply.setUpdateBy(rs.getString("updateby"));
                    questionnairReply.setStatus(UnmodifiableDataStatus.valueOf(rs.getString("status")));

//                    System.out.println("unit:" + unit);
                    questionnairReplys.add(questionnairReply);
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
        return questionnairReplys;
    }
}
