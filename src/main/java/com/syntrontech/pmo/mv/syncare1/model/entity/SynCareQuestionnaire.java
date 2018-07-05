package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.QuestionnaireQuestionsTO;
import com.syntrontech.syncare.model.transfer.QuestionnaireTO;


/**
 * The persistent class for the questionnaire database table.
 * 
 */
@Entity
@Table(name="syncare_questionnaire")
@NamedQuery(name="Questionnaire.findAll", query="SELECT q FROM SynCareQuestionnaire q")
public class SynCareQuestionnaire implements Serializable, ObjectConverter<QuestionnaireTO> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="name")
	private String name;
	
	@Column(name="display_name")
	private String diaplayName;
	
	@Column(name="title")
	private String title;

	@OneToMany(mappedBy="questionnaire", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OrderBy("id")
	private List<SynCareQuestionnaireQuestions> questionnaireQuestions;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDiaplayName() {
		return diaplayName;
	}

	public List<SynCareQuestionnaireQuestions> getQuestionnaireQuestions() {
		return questionnaireQuestions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setQuestionnaireQuestions(
			List<SynCareQuestionnaireQuestions> questionnaireQuestions) {
		this.questionnaireQuestions = questionnaireQuestions;
	}

	public void setDiaplayName(String diaplayName) {
		this.diaplayName = diaplayName;
	}

	@Override
	public String toString() {
		return "SynCareQuestionnaire [id=" + id + ", name=" + name + ", title="
				+ title + ", questionnaireQuestions=" + questionnaireQuestions
				+ "]";
	}

	@Override
	public QuestionnaireTO convert(boolean relation) {
		QuestionnaireTO questionnaire = new QuestionnaireTO();
		questionnaire.setId(getId());
		questionnaire.setName(getName());
		questionnaire.setDisplayName(getDiaplayName());
		questionnaire.setTitle(getTitle());
		if(relation){
			List<QuestionnaireQuestionsTO> questions = ObjectConverter.utils.transferList(getQuestionnaireQuestions());
			questionnaire.setQuestions(questions);
		}
		return questionnaire;
	}

}