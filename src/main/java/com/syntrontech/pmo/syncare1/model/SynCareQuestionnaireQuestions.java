package com.syntrontech.pmo.syncare1.model;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the questionnaire_questions database table.
 * 
 */
@Entity
@Table(name="syncare_questionnaire_questions")
@NamedQuery(name="QuestionnaireQuestions.findAll", query="SELECT q FROM SynCareQuestionnaireQuestions q")
public class SynCareQuestionnaireQuestions{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="answer_type")
	private String answerType;

	@ManyToOne
	@JoinColumn(name="questionnaire_id")
	private SynCareQuestionnaire questionnaire;

	@OneToMany(mappedBy="questionnaireQuestions", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<SynCareQuestionnaireAnswersItem> questionnaireAnswersItems;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAnswerType() {
		return answerType;
	}

	public SynCareQuestionnaire getQuestionnaire() {
		return questionnaire;
	}

	public List<SynCareQuestionnaireAnswersItem> getQuestionnaireAnswersItems() {
		return questionnaireAnswersItems;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public void setQuestionnaire(SynCareQuestionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public void setQuestionnaireAnswersItems(
			List<SynCareQuestionnaireAnswersItem> questionnaireAnswersItems) {
		this.questionnaireAnswersItems = questionnaireAnswersItems;
	}

	@Override
	public String toString() {
		return "QuestionnaireQuestions [id=" + id + ", title=" + title
				+ ", answerType=" + answerType + ", questionnaireAnswersItems="
				+ questionnaireAnswersItems + "]";
	}

}