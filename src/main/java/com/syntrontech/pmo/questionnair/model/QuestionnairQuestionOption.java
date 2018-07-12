package com.syntrontech.pmo.questionnair.model;

import com.syntrontech.pmo.questionnair.model.common.UnmodifiableDataStatus;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//store in Postgres
@Entity
@Table(name = "questionnaire_question_option")
public class QuestionnairQuestionOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence", nullable = false)
	private Long sequence;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "score")
	private String score;
	
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
	
	@Column(name = "questionnaire_question_seq", nullable = false)
	private Long questionnairQuestionSeq;

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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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

	public Long getQuestionnairQuestionSeq() {
		return questionnairQuestionSeq;
	}

	public void setQuestionnairQuestionSeq(Long questionnairQuestionSeq) {
		this.questionnairQuestionSeq = questionnairQuestionSeq;
	}

}
