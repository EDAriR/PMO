package com.syntrontech.pmo.syncare1.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="syncare_questionnaire_answers")
public class SynCareQuestionnaireAnswers implements Serializable{
	private static final long serialVersionUID = -3042687621758050427L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private SystemUser user;
	
	@ManyToOne
	@JoinColumn(name="questionnaire_id")
	private SynCareQuestionnaire questionnaire;
	
	@Column(name="questionnaire_title")
	private String questionnaireTitle;
	
	@Column(name="questionnaire_questions_id")
	private int questionnaireQuestionsId;
	
	@Column(name="questionnaire_questions_title")
	private String questionnaireQuestionsTitle;
	
	@Column(name="questionnaire_answers_item_id")
	private int questionnaireAnswersItemId;
	
	@Column(name="questionnaire_answers_item_value")
	private String questionnaireAnswersItemValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	public int getId() {
		return id;
	}

	public SystemUser getUser() {
		return user;
	}

	public SynCareQuestionnaire getQuestionnaire() {
		return questionnaire;
	}

	public String getQuestionnaireTitle() {
		return questionnaireTitle;
	}

	public int getQuestionnaireQuestionsId() {
		return questionnaireQuestionsId;
	}

	public String getQuestionnaireQuestionsTitle() {
		return questionnaireQuestionsTitle;
	}

	public int getQuestionnaireAnswersItemId() {
		return questionnaireAnswersItemId;
	}

	public String getQuestionnaireAnswersItemValue() {
		return questionnaireAnswersItemValue;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public void setQuestionnaire(SynCareQuestionnaire questionnaireId) {
		this.questionnaire = questionnaireId;
	}

	public void setQuestionnaireTitle(String questionnaireTitle) {
		this.questionnaireTitle = questionnaireTitle;
	}

	public void setQuestionnaireQuestionsId(int questionnaireQuestionsId) {
		this.questionnaireQuestionsId = questionnaireQuestionsId;
	}

	public void setQuestionnaireQuestionsTitle(String questionnaireQuestionsTitle) {
		this.questionnaireQuestionsTitle = questionnaireQuestionsTitle;
	}

	public void setQuestionnaireAnswersItemId(int questionnaireAnswersItemId) {
		this.questionnaireAnswersItemId = questionnaireAnswersItemId;
	}

	public void setQuestionnaireAnswersItemValue(
			String questionnaireAnswersItemValue) {
		this.questionnaireAnswersItemValue = questionnaireAnswersItemValue;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SynCareQuestionnaireAnswers other = (SynCareQuestionnaireAnswers) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SynCareQuestionnaireAnswers [id=" + id + ", user=" + user
				+ ", questionnaireId=" + questionnaire.getId()
				+ ", questionnaireTitle=" + questionnaireTitle
				+ ", questionnaireQuestionsId=" + questionnaireQuestionsId
				+ ", questionnaireQuestionsTitle="
				+ questionnaireQuestionsTitle + ", questionnaireAnswersItemId="
				+ questionnaireAnswersItemId
				+ ", questionnaireAnswersItemValue="
				+ questionnaireAnswersItemValue + ", createDate=" + createDate
				+ "]";
	}

}
