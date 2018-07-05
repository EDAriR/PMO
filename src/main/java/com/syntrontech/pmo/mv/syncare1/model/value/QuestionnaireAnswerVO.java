package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestionnaireAnswerVO {
	private int item;
	
	private List<String> answer;

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "QuestionnaireAnswerVO [item=" + item + ", answer=" + answer
				+ "]";
	}
}
