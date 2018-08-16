package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.questionnair.QuestionnairReplyJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SynCareQuestionnaireAnswersJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.model.common.UnmodifiableDataStatus;
import com.syntrontech.pmo.questionnair.QuestionnairReply;
import com.syntrontech.pmo.syncare1.model.SynCareQuestionnaireAnswers;
import com.syntrontech.pmo.syncare1.model.SystemUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncAnswers {

    private static Logger logger = LoggerFactory.getLogger(SyncAnswers.class);

    public static void main(String[] args) {
        new SyncAnswers().syncAnswers();
    }

    public void syncAnswers() {

        SynCareQuestionnaireAnswersJDBC answersJDBC = new SynCareQuestionnaireAnswersJDBC();

        Connection syncare1conn = new Syncare1_GET_CONNECTION().getConn();
        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();
        QuestionnairReplyJDBC replyJDBC = new QuestionnairReplyJDBC();

        try {

            List<SynCareQuestionnaireAnswers> answers = answersJDBC.getAll();

//            Collections.reverse(answers);

            Map<String, List<QuestionnairReply>> replyMap = answers.stream()
                    .map(a -> turnAnswerToReply(syncare1conn, a, systemUserJDBC))
                    .collect(Collectors.groupingBy(QuestionnairReply::getUserId));

            for(String id:replyMap.keySet()){

                List<QuestionnairReply> replys = replyMap.get(id);
                replys.sort((o1, o2) -> o1.getQuestionnairQuestionSeq() > o2.getQuestionnairQuestionSeq() ? -1 :
                        (o1.getQuestionnairQuestionSeq() < o2.getQuestionnairQuestionSeq()) ? 1 : 0);

                replys.forEach(r -> replyJDBC.insert(r));
            }

            answers.forEach(a -> answersJDBC.update(a.getId()));

        }finally{

            try {
                syncare1conn.close();
            } catch (SQLException e) {
                logger.debug("Connection close fail :" + syncare1conn);
                e.printStackTrace();
            }
        }
    }

//    private void syncToQuestionnairReply(Connection conn, SynCareQuestionnaireAnswers answers, SystemUserJDBC systemUserJDBC) {
//
//
//
//
//
//        if (reply != null)
//            reply = ;
//
//        System.out.println("old  ==>>>" + answers + "<<<");
//        System.out.println("new  ==>>>" + reply + "<<<");
//        System.out.println("old  ==>>>" + answers.getId() + "<<<");
//        System.out.println("new  ==>>>" + reply.getSequence() + "<<<");
//
//    }

    private QuestionnairReply turnAnswerToReply(Connection conn, SynCareQuestionnaireAnswers answers, SystemUserJDBC systemUserJDBC) {

        Map<Long, String> scopeMap = getScope();
        Map<Long, String> answerMap = getAnswers();

        QuestionnairReply questionnairReply = new QuestionnairReply();
//        sequence, user_id, tenant_id, questionnaire_seq
//        questionnaire_question_option_score, questionnaire_question_answer
//        createtime, createby, updatetime, updateby, status

        SystemUser su = systemUserJDBC.getSystemUserById(conn, answers.getUser());
        String userId = su.getUserAccount() == null ? "" : su.getUserAccount().toUpperCase();
        questionnairReply.setUserId(userId.toUpperCase());
        questionnairReply.setTenantId("TTSHB");
        questionnairReply.setQuestionnairSeq(answers.getQuestionnaire());

        // questionnaire_title, questionnaire_question_seq, questionnaire_question_title, questionnaire_question_option_seq
        questionnairReply.setQuestionnairTitle(answers.getQuestionnaireTitle());

        int id = answers.getQuestionnaireQuestionsId();
        Long seq = (long) id;

        if (id == 6)
            seq = 7L;
        if (id == 7)
            seq = 6L;

        questionnairReply.setQuestionnairQuestionSeq(seq);
        questionnairReply.setQuestionnairQuestionTitle(answers.getQuestionnaireQuestionsTitle());

        long optionseq = (long) answers.getQuestionnaireAnswersItemId();
        Long[] longArray = {optionseq};
        if (optionseq != 0)
            questionnairReply.setQuestionnairQuestionOptionSeq(longArray);
        else
            questionnairReply.setQuestionnairQuestionOptionSeq(null);

        String scope = scopeMap.get(optionseq);
        if(scope != null){
            String[] scoreArr = {scope};
            questionnairReply.setQuestionnairQuestionOptionScore(scoreArr);
        }else{
            questionnairReply.setQuestionnairQuestionOptionScore(null);
        }

        try{

            Long answerseq = Long.valueOf(answers.getQuestionnaireAnswersItemValue());
            String[] strArr = {answerMap.get(answerseq)};
            questionnairReply.setQuestionnairQuestionAnswer(strArr);

        }catch (NumberFormatException e){
            String[] strArr = {answers.getQuestionnaireAnswersItemValue()};
            questionnairReply.setQuestionnairQuestionAnswer(strArr);
        }

        questionnairReply.setCreateTime(answers.getCreateDate()); // Date
        questionnairReply.setCreateBy(userId);
        questionnairReply.setUpdateTime(answers.getCreateDate()); // Date
        questionnairReply.setUpdateBy(userId);
        questionnairReply.setStatus(UnmodifiableDataStatus.EXISTED);

        return questionnairReply;

    }

    public Map<Long, String> getScope() {

        Map<Long, String> scope = new HashMap<>();
        scope.put(1L, "5");
        scope.put(2L, "4");
        scope.put(3L, "3");
        scope.put(4L, "2");
        scope.put(5L, "1");

        scope.put(6L, "5");
        scope.put(7L, "4");
        scope.put(8L, "3");
        scope.put(9L, "2");
        scope.put(10L, "1");

        scope.put(11L, "5");
        scope.put(12L, "4");
        scope.put(13L, "3");
        scope.put(14L, "2");
        scope.put(15L, "1");

        scope.put(16L, "5");
        scope.put(17L, "4");
        scope.put(18L, "3");
        scope.put(19L, "2");
        scope.put(20L, "1");

        scope.put(21L, "5");
        scope.put(22L, "4");
        scope.put(23L, "3");
        scope.put(24L, "2");
        scope.put(25L, "1");

        scope.put(26L, "5");
        scope.put(27L, "4");
        scope.put(28L, "3");
        scope.put(29L, "2");
        scope.put(30L, "1");

        return scope;
    }

    public Map<Long, String> getAnswers() {
        Map<Long, String> answers = new HashMap<>();

        answers.put(1L, "好很多");
        answers.put(2L, "好一點");
        answers.put(3L, "差不多");
        answers.put(4L, "差一些");
        answers.put(5L, "差很多");

        answers.put(6L, "減少很多");
        answers.put(7L, "減少一點");
        answers.put(8L, "差不多");
        answers.put(9L, "增加一些");
        answers.put(10L, "增加很多");

        answers.put(11L, "減少很多");
        answers.put(12L, "減少一點");
        answers.put(13L, "差不多");
        answers.put(14L, "增加一些");
        answers.put(15L, "增加很多");

        answers.put(16L, "減少很多");
        answers.put(17L, "減少一點");
        answers.put(18L, "差不多");
        answers.put(19L, "增加一些");
        answers.put(20L, "增加很多");

        answers.put(21L, "好很多");
        answers.put(22L, "好一點");
        answers.put(23L, "差不多");
        answers.put(24L, "差一些");
        answers.put(25L, "差很多");

        answers.put(26L, "很支持");
        answers.put(27L, "支持");
        answers.put(28L, "沒意見");
        answers.put(29L, "不支持");
        answers.put(30L, "很不支持");


        return answers;
    }
}
