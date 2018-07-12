package com.syntrontech.pmo.questionnair.model;

import java.util.ArrayList;
import java.util.List;

public class ComposedQuestion {

	private QuestionnairQuestion question;
	
	private List<QuestionnairQuestionOption> options = new ArrayList<>();

	public QuestionnairQuestion getQuestion() {
		return question;
	}

	public void setQuestion(QuestionnairQuestion question) {
		this.question = question;
	}
	
	public List<QuestionnairQuestionOption> getOptions() {
		return options;
	}

	public void addQuestionOption(QuestionnairQuestionOption option) {
		options.add(option);
	}
}
