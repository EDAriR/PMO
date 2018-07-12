package com.syntrontech.pmo.questionnair.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.syntrontech.pmo.model.common.QuestionType;
import com.syntrontech.pmo.questionnair.model.common.RequiredType;
import com.syntrontech.pmo.questionnair.model.common.UnmodifiableDataStatus;
import org.hibernate.annotations.Type;


//store in Postgres
@Entity
@Table(name = "questionnaire_question")
public class QuestionnairQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence", nullable = false)
	private Long sequence;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "position_sequence")
	private Long positionSequence;
	
	@Column(name = "required", nullable = false)
	@Enumerated(EnumType.STRING)
	private RequiredType required;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	
	@Column(name = "validation_expression")
	private String validationExpression;
	
	@Column(name = "createtime", nullable = false)
	private Date createTime;
	
	@Column(name = "createby", nullable = false)
	private String createBy;
	
	@Column(name = "updatetime", nullable = false)
	private Date updateTime;
	
	@Column(name = "updateby", nullable = false)
	private String updateBy;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private UnmodifiableDataStatus status;
	
	@Column(name = "questionnaire_seq", nullable = false)
	private Long questionnairSeq;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPositionSequence() {
		return positionSequence;
	}

	public void setPositionSequence(Long positionSequence) {
		this.positionSequence = positionSequence;
	}

	public RequiredType getRequired() {
		return required;
	}

	public void setRequired(RequiredType required) {
		this.required = required;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public String getValidationExpression() {
		return validationExpression;
	}

	public void setValidationExpression(String validationExpression) {
		this.validationExpression = validationExpression;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public UnmodifiableDataStatus getStatus() {
		return status;
	}

	public void setStatus(UnmodifiableDataStatus status) {
		this.status = status;
	}

	public Long getQuestionnairSeq() {
		return questionnairSeq;
	}

	public void setQuestionnairSeq(Long questionnairSeq) {
		this.questionnairSeq = questionnairSeq;
	}
	
}
