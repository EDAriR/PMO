package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.SynCareQuestionnaireAnswersItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SynCareQuestionnaireAnswersItemJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM syncare_questionnaire_answers_item WHERE sync_status = 'N' ORDER BY id;";
    private static final String UPDATE = "UPDATE syncare_questionnaire_answers_item SET sync_status= 'Y' WHERE id=? ;";

    public static void main(String[] args) {
        SynCareQuestionnaireAnswersItemJDBC itemJDBC = new SynCareQuestionnaireAnswersItemJDBC();

        List<SynCareQuestionnaireAnswersItem> items = itemJDBC.getAll();

        items.forEach(item -> itemJDBC.update(item.getId()));
    }

    public List<SynCareQuestionnaireAnswersItem> getAll() {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        List<SynCareQuestionnaireAnswersItem> items = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    SynCareQuestionnaireAnswersItem item = new SynCareQuestionnaireAnswersItem();

                    item.setId(rs.getInt("id"));
                    item.setLabel(rs.getString("label"));
                    item.setValue(rs.getString("value"));
                    item.setQuestionnaireQuestions(rs.getInt("questionnaire_questions_id"));

//                    System.out.println("LocationJDBC ==>" + location);
                    items.add(item);
                }
            }

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
                System.out.println("conn or pstmt close fail" + conn + " || "+ pstmt);
                e.printStackTrace();
            }

        }
        return items;
    }

    public void update(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();
        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("update " + id + " successful ==============");


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
