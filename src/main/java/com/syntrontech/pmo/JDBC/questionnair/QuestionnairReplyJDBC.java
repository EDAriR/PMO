package com.syntrontech.pmo.JDBC.questionnair;

import com.syntrontech.pmo.questionnair.QuestionnairReply;
import com.syntrontech.pmo.model.common.UnmodifiableDataStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionnairReplyJDBC {

    private static final String GET_ALL_STMT = "select * from questionnair_reply WHERE status='EXISTED' order by sequence;";
    private static final String INSERT_STMT = "INSERT INTO questionnair_reply " +
            "(sequence, user_id, tenant_id, questionnaire_seq, " +
            "questionnaire_title, questionnaire_question_seq, questionnaire_question_title, questionnaire_question_option_seq, " +
            "questionnaire_question_option_score, questionnaire_question_answer," +
            " createtime, createby, updatetime, updateby, status) "
            + "VALUES (nextval('questionnair_reply_sequence_seq'), ?, ?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, " +
            "?, ?, ?, ?, ?);";
//        sequence, user_id, tenant_id, questionnaire_seq
//        questionnaire_title, questionnaire_question_seq, questionnaire_question_title, questionnaire_question_option_seq
//        questionnaire_question_option_score, questionnaire_question_answer
//        createtime, createby, updatetime, updateby, status

    private static final String GET_ONE = "SELECT * FROM questionnair_reply WHERE questionnaire_seq=? AND questionnaire_question_seq=? AND tenant_id='TTSHB' ;";

    public static void main(String[] args) {

        QuestionnairReplyJDBC s = new QuestionnairReplyJDBC();

        Date star_time = new Date(new java.util.Date().getTime());
        List<QuestionnairReply> ss = s.getAllQuestionnairReplys();
        Date end_time = new Date(new java.util.Date().getTime());


        QuestionnairReply qq = s.getTestQuestionnairReply();
        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
        System.out.println("ss size:" + ss.size());

        QuestionnairReply gg = s.insert(qq);
        System.out.println("=====");
        System.out.println(gg);
//        s.insertUnitMeta(s.getTestQuestionnairReply());
//
//        UnitMeta unit = s.getUnitMetaById("1001401");

    }

    public QuestionnairReply insert(QuestionnairReply questionnairReply){

        Connection conn = new QUESTIONNAIR_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

//        createtime, createby, updatetime, updateby, status


            //  user_id, tenant_id, questionnaire_seq
            pstmt.setString(1, questionnairReply.getUserId());
            pstmt.setString(2, questionnairReply.getTenantId());
            pstmt.setLong(3, questionnairReply.getQuestionnairQuestionSeq());

            // questionnaire_title, questionnaire_question_seq,
            // questionnaire_question_title, questionnaire_question_option_seq
            pstmt.setString(4, questionnairReply.getQuestionnairTitle());
            pstmt.setLong(5, questionnairReply.getQuestionnairQuestionSeq());
            pstmt.setString(6, questionnairReply.getQuestionnairQuestionTitle());


            Long[] questionnairQuestionOptionSeq = {};
            questionnairQuestionOptionSeq = questionnairReply.getQuestionnairQuestionOptionSeq() != null ?
                    questionnairReply.getQuestionnairQuestionOptionSeq() : questionnairQuestionOptionSeq;
            Array opSeq = conn.createArrayOf("BigInt", questionnairQuestionOptionSeq);
            pstmt.setArray(7, opSeq);


            String[] questionnairQuestionOptionScore = {};
            questionnairQuestionOptionScore = questionnairReply.getQuestionnairQuestionOptionScore() != null ?
                    questionnairReply.getQuestionnairQuestionOptionScore() : questionnairQuestionOptionScore;
            Array score = conn.createArrayOf("varchar", questionnairQuestionOptionScore);
            //        questionnaire_question_option_score, questionnaire_question_answer
            pstmt.setArray(8, score);


            String[] questionnairQuestionAnswer = {};
            questionnairQuestionAnswer = questionnairReply.getQuestionnairQuestionAnswer() != null ?
                    questionnairReply.getQuestionnairQuestionAnswer() : questionnairQuestionAnswer;
            Array answer = conn.createArrayOf("varchar", questionnairQuestionAnswer);
            pstmt.setArray(9, answer);


            pstmt.setTimestamp(10, new Timestamp(questionnairReply.getCreateTime().getTime()));
            pstmt.setString(11, questionnairReply.getCreateBy());
            pstmt.setTimestamp(12, new Timestamp(questionnairReply.getUpdateTime().getTime()));
            pstmt.setString(13, questionnairReply.getUpdateBy());

            pstmt.setString(14, questionnairReply.getStatus().toString());

//            logger.info("sql => " + pstmt);
            pstmt.executeUpdate();
//            logger.info("create questionnairReply successful => " + questionnairReply);


            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    questionnairReply.setSequence(rs.getLong(1));
                }
                rs.close();
            }

        } catch (SQLException e) {
//            logger.debug("create questionnairReply fail =>" + questionnairReply);
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

        return questionnairReply;
    }

    public QuestionnairReply getTestQuestionnairReply() {

        QuestionnairReply questionnairReply = new QuestionnairReply();
//        sequence, user_id, tenant_id, questionnaire_seq
//        questionnaire_question_option_score, questionnaire_question_answer
//        createtime, createby, updatetime, updateby, status

        questionnairReply.setUserId("userJDBCTest");
        questionnairReply.setTenantId("TTSHB");
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
        questionnairReply.setCreateBy("userJDBCTest");
        questionnairReply.setUpdateTime(date); // Date
        questionnairReply.setUpdateBy("userJDBCTest");
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
