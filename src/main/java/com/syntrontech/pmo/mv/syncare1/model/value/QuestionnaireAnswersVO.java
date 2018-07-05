package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestionnaireAnswersVO {
	private int userId;
	
	private List<QuestionnaireAnswerVO> answers;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public List<QuestionnaireAnswerVO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionnaireAnswerVO> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "QuestionnaireAnswersVO [userId=" + userId + ", answers="
				+ answers + "]";
	}
}
