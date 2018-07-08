package com.syntrontech.pmo.syncare1.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="syncare_questionnaire_answers_item")
public class SynCareQuestionnaireAnswersItem {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="label")
	private String label;
	
	@Column(name="value")
	private String value;
	
	@ManyToOne
	@JoinColumn(name="questionnaire_questions_id")
	private SynCareQuestionnaireQuestions questionnaireQuestions;

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

	public SynCareQuestionnaireQuestions getQuestionnaireQuestions() {
		return questionnaireQuestions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setQuestionnaireQuestions(
			SynCareQuestionnaireQuestions questionnaireQuestions) {
		this.questionnaireQuestions = questionnaireQuestions;
	}

	@Override
	public String toString() {
		return "QuestionnaireAnswersItem [id=" + id + ", label=" + label
				+ ", value=" + value + "]";
	}
}
