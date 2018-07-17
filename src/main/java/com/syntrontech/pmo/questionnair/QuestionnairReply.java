package com.syntrontech.pmo.questionnair;

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

import com.syntrontech.pmo.model.common.UnmodifiableDataStatus;
import org.hibernate.annotations.Type;


//store in Postgres
@Entity
@Table(name = "questionnair_reply")
public class QuestionnairReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence", nullable = false)
	private Long sequence;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "tenant_id")
	private String tenantId;
	
	@Column(name = "questionnaire_seq", nullable = false)
	private Long questionnairSeq;
	
	@Column(name = "questionnaire_title", nullable = false)
	private String questionnairTitle;
	
	@Column(name = "questionnaire_question_seq", nullable = false)
	private Long questionnairQuestionSeq;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "questionnaire_question_title", nullable = false)
	private String questionnairQuestionTitle;
	
	@Column(name = "questionnaire_question_option_seq")
	@Type(type = "com.syntrontech.pmo.model.common.LongArrayType")
	private Long[] questionnairQuestionOptionSeq;
	
	@Column(name = "questionnaire_question_option_score")
	@Type(type = "com.syntrontech.pmo.model.common.StringArrayType")
	private String[] questionnairQuestionOptionScore;
	
	@Column(name = "questionnaire_question_answer")
	@Type(type = "com.syntrontech.pmo.model.common.StringArrayType")
	private String[] questionnairQuestionAnswer;
	
	@Column(name = "createtime", nullable = false)
	private Date createTime;
	
	@Column(name = "createby")
	private String createBy;
	
	@Column(name = "updatetime", nullable = false)
	private Date updateTime;
	
	@Column(name = "updateby")
	private String updateBy;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private UnmodifiableDataStatus status;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Long getQuestionnairSeq() {
		return questionnairSeq;
	}

	public void setQuestionnairSeq(Long questionnairSeq) {
		this.questionnairSeq = questionnairSeq;
	}

	public String getQuestionnairTitle() {
		return questionnairTitle;
	}

	public void setQuestionnairTitle(String questionnairTitle) {
		this.questionnairTitle = questionnairTitle;
	}

	public Long getQuestionnairQuestionSeq() {
		return questionnairQuestionSeq;
	}

	public void setQuestionnairQuestionSeq(Long questionnairQuestionSeq) {
		this.questionnairQuestionSeq = questionnairQuestionSeq;
	}

	public String getQuestionnairQuestionTitle() {
		return questionnairQuestionTitle;
	}

	public void setQuestionnairQuestionTitle(String questionnairQuestionTitle) {
		this.questionnairQuestionTitle = questionnairQuestionTitle;
	}

	public Long[] getQuestionnairQuestionOptionSeq() {
		return questionnairQuestionOptionSeq;
	}

	public void setQuestionnairQuestionOptionSeq(Long[] questionnairQuestionOptionSeq) {
		this.questionnairQuestionOptionSeq = questionnairQuestionOptionSeq;
	}

	public String[] getQuestionnairQuestionOptionScore() {
		return questionnairQuestionOptionScore;
	}

	public void setQuestionnairQuestionOptionScore(String[] questionnairQuestionOptionScore) {
		this.questionnairQuestionOptionScore = questionnairQuestionOptionScore;
	}

	public String[] getQuestionnairQuestionAnswer() {
		return questionnairQuestionAnswer;
	}

	public void setQuestionnairQuestionAnswer(String[] questionnairQuestionAnswer) {
		this.questionnairQuestionAnswer = questionnairQuestionAnswer;
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
	
}
