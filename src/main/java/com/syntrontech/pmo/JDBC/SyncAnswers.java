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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncAnswers {

	private static Logger logger = LoggerFactory.getLogger(SyncAnswers.class);

    public static void main(String[] args) {
        new SyncAnswers().syncAnswers();
    }

    public void syncAnswers(){

        SynCareQuestionnaireAnswersJDBC answersJDBC = new SynCareQuestionnaireAnswersJDBC();
        
        Connection syncare1conn = new Syncare1_GET_CONNECTION().getConn();
        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();
        
        try {
        	List<SynCareQuestionnaireAnswers> answers = answersJDBC.getAll();
            answers.forEach(a-> {

                syncToQuestionnairReply(syncare1conn, a, systemUserJDBC);
                answersJDBC.update(a.getId());
            });
        }finally{
        	
        	try {
        		syncare1conn.close();
        	}catch(SQLException e){
        		 logger.debug("Connection close fail :" + syncare1conn );
                 e.printStackTrace();
        	}
        }
        

    }

    private void syncToQuestionnairReply(Connection conn, SynCareQuestionnaireAnswers answers, SystemUserJDBC systemUserJDBC) {

        QuestionnairReplyJDBC replyJDBC = new QuestionnairReplyJDBC();

        QuestionnairReply reply = turnAnswerToReply(conn, answers, systemUserJDBC);
        System.out.println("old  ==>>>" + answers  + "<<<");
        System.out.println("new  ==>>>" + reply  + "<<<");
        if(reply != null)
            replyJDBC.insert(reply);

    }

    private QuestionnairReply turnAnswerToReply(Connection conn, SynCareQuestionnaireAnswers answers, SystemUserJDBC systemUserJDBC){

        Map<Long, String> scopeMap = getScope();
        QuestionnairReply questionnairReply = new QuestionnairReply();
//        sequence, user_id, tenant_id, questionnaire_seq
//        questionnaire_question_option_score, questionnaire_question_answer
//        createtime, createby, updatetime, updateby, status

        SystemUser su = systemUserJDBC.getSystemUserById(conn, answers.getUser());
        String userId = su.getUserAccount() == null? "":su.getUserAccount().toUpperCase();
        questionnairReply.setUserId(userId.toUpperCase());
        questionnairReply.setTenantId("TTSHB");
        questionnairReply.setQuestionnairSeq(answers.getQuestionnaire());

        // questionnaire_title, questionnaire_question_seq, questionnaire_question_title, questionnaire_question_option_seq
        questionnairReply.setQuestionnairTitle(answers.getQuestionnaireTitle());

        int id = answers.getQuestionnaireQuestionsId();
        Long seq =  (long)id;

        if(id == 6)
            seq = 7L;
        if(id == 7)
            seq = 6L;

        questionnairReply.setQuestionnairQuestionSeq(seq);
        questionnairReply.setQuestionnairQuestionTitle(answers.getQuestionnaireQuestionsTitle());

        long optionseq = (long) answers.getQuestionnaireAnswersItemId();
        Long[] longArray = {optionseq};
        questionnairReply.setQuestionnairQuestionOptionSeq(longArray);

        String[] strArr = {answers.getQuestionnaireAnswersItemValue()};
        questionnairReply.setQuestionnairQuestionAnswer(strArr);

        String scope = scopeMap.get(optionseq) == null? "": scopeMap.get(optionseq);
        String[] scoreArr = {scope};
        questionnairReply.setQuestionnairQuestionOptionScore(scoreArr);

        questionnairReply.setCreateTime(answers.getCreateDate()); // Date
        questionnairReply.setCreateBy(userId);
        questionnairReply.setUpdateTime(answers.getCreateDate()); // Date
        questionnairReply.setUpdateBy(userId);
        questionnairReply.setStatus(UnmodifiableDataStatus.EXISTED);
        
        return questionnairReply;

    }

    public Map<Long,String> getScope() {

        Map<Long, String> scope = new HashMap<>();
        scope.put(1L,"5");
        scope.put(2L,"4");
        scope.put(3L,"3");
        scope.put(4L,"2");
        scope.put(5L,"1");

        scope.put(6L,"5");
        scope.put(7L,"4");
        scope.put(8L,"3");
        scope.put(9L,"2");
        scope.put(10L,"1");

        scope.put(11L,"5");
        scope.put(12L,"4");
        scope.put(13L,"3");
        scope.put(14L,"2");
        scope.put(15L,"1");

        scope.put(16L,"5");
        scope.put(17L,"4");
        scope.put(18L,"3");
        scope.put(19L,"2");
        scope.put(20L,"1");

        scope.put(21L,"5");
        scope.put(22L,"4");
        scope.put(23L,"3");
        scope.put(24L,"2");
        scope.put(25L,"1");

        scope.put(26L,"5");
        scope.put(27L,"4");
        scope.put(28L,"3");
        scope.put(29L,"2");
        scope.put(30L,"1");

        return scope;
    }
}
