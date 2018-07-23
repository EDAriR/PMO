package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.questionnair.QuestionnairReplyJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SynCareQuestionnaireAnswersJDBC;
import com.syntrontech.pmo.syncare1.model.SynCareQuestionnaireAnswers;

import java.util.List;

public class SyncAnswers {

    public void syncAnswers(){

        SynCareQuestionnaireAnswersJDBC answersJDBC = new SynCareQuestionnaireAnswersJDBC();

        List<SynCareQuestionnaireAnswers> answers = answersJDBC.getAll();
        answers.forEach(a-> {

            syncToQuestionnairReply(a);
            answersJDBC.update(a.getId());
        });

    }

    private void syncToQuestionnairReply(SynCareQuestionnaireAnswers a) {

        QuestionnairReplyJDBC replyJDBC = new QuestionnairReplyJDBC();



    }
}
