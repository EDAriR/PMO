package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.Calendar;
import com.syntrontech.pmo.syncare1.model.SynCareQuestionnaireAnswers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SynCareQuestionnaireAnswersJDBC {

    private static Logger logger = LoggerFactory.getLogger(SynCareQuestionnaireAnswersJDBC.class);


    private static final String GET_ALL_STMT = "SELECT * FROM syncare_questionnaire_answers WHERE sync_status = 'N' ORDER BY id;";
    private static final String UPDATE = "UPDATE syncare_questionnaire_answers SET sync_status= 'Y' WHERE id=? ;";


    public static void main(String[] args) {
        SynCareQuestionnaireAnswersJDBC answersJDBC = new SynCareQuestionnaireAnswersJDBC();

        Date start = java.util.Calendar.getInstance().getTime();
        List<SynCareQuestionnaireAnswers> answers = answersJDBC.getAll();

        Date update = java.util.Calendar.getInstance().getTime();

        answers.forEach(a-> answersJDBC.update(a.getId()));

        Date end = java.util.Calendar.getInstance().getTime();

        System.out.println("start =>" + start);
        System.out.println("update =>" + update);
        System.out.println("start =>" + end);
    }
    public List<SynCareQuestionnaireAnswers> getAll() {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        List<SynCareQuestionnaireAnswers> answers = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    SynCareQuestionnaireAnswers answer = new SynCareQuestionnaireAnswers();

                    answer.setId(rs.getInt("id"));
                    answer.setUser(rs.getString("user_id"));
                    answer.setQuestionnaire(rs.getLong("questionnaire_id"));
                    answer.setQuestionnaireTitle(rs.getString("questionnaire_title"));
                    answer.setQuestionnaireQuestionsId(rs.getInt("questionnaire_questions_id"));
                    answer.setQuestionnaireQuestionsTitle(rs.getString("questionnaire_questions_title"));
                    answer.setQuestionnaireAnswersItemId(rs.getInt("questionnaire_answers_item_id"));
                    answer.setQuestionnaireAnswersItemValue(rs.getString("questionnaire_answers_item_value"));
                    answer.setCreateDate(rs.getTimestamp("create_date"));

                    answers.add(answer);
                }
            }

        } catch (SQLException e) {
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
        logger.info("answers: " +  answers);

        return answers;
    }

    public void update(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            logger.info("update SynCareQuestionnaireAnswers :[" + id + "] successful ");


        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }
}
