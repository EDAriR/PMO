package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.questionnair.QuestionnairReplyJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SynCareQuestionnaireAnswersJDBC;
import com.syntrontech.pmo.model.common.UnmodifiableDataStatus;
import com.syntrontech.pmo.questionnair.QuestionnairReply;
import com.syntrontech.pmo.syncare1.model.SynCareQuestionnaireAnswers;

import java.util.Date;
import java.util.List;

public class SyncAnswers {

    public static void main(String[] args) {
        new SyncAnswers().syncAnswers();
    }

    public void syncAnswers(){

        SynCareQuestionnaireAnswersJDBC answersJDBC = new SynCareQuestionnaireAnswersJDBC();

        List<SynCareQuestionnaireAnswers> answers = answersJDBC.getAll();
        answers.forEach(a-> {

            syncToQuestionnairReply(a);
            answersJDBC.update(a.getId());
        });

    }

    private void syncToQuestionnairReply(SynCareQuestionnaireAnswers answers) {

        QuestionnairReplyJDBC replyJDBC = new QuestionnairReplyJDBC();

        QuestionnairReply reply = turnAnswerToReply(answers);
        System.out.println(reply);
        if(reply != null)
            replyJDBC.insert(reply);

    }

    private QuestionnairReply turnAnswerToReply(SynCareQuestionnaireAnswers answers) {

        QuestionnairReply questionnairReply = new QuestionnairReply();
//        sequence, user_id, tenant_id, questionnaire_seq
//        questionnaire_question_option_score, questionnaire_question_answer
//        createtime, createby, updatetime, updateby, status

        questionnairReply.setUserId(answers.getUser());
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

        Long[] longArray = {(long)answers.getQuestionnaireAnswersItemId()};;
        questionnairReply.setQuestionnairQuestionOptionSeq(longArray);


        Date date = new Date();
        questionnairReply.setCreateTime(answers.getCreateDate()); // Date
        questionnairReply.setCreateBy(answers.getUser());
        questionnairReply.setUpdateTime(answers.getCreateDate()); // Date
        questionnairReply.setUpdateBy(answers.getUser());
        questionnairReply.setStatus(UnmodifiableDataStatus.EXISTED);

        return questionnairReply;

    }
}
