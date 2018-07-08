package com.syntrontech.pmo.cip.model.solr;

import com.syntrontech.pmo.solr.SolrDoc;
import com.syntrontech.pmo.util.ISODateTime;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class SolrQuestionnairReply implements SolrDoc {

	private static Map<String, String> fieldNameMap;
	
	private String id;
	
	private Long sequence_l;
	
	private String userId_s;
	
	private String tenantId_s;
	
	private Long questionnairSeq_l;
	
	private String questionnairTitle_s;
	
	private Long questionnairQuestionSeq_l;
	
	private String questionnairQuestionTitle_s;
	
	private Long[] questionnairQuestionOptionSeq_ls;
	
	private String[] questionnairQuestionOptionScore_ss;
	
	private String[] questionnairQuestionAnswer_ss;
	
	private String createTime_dt;
	
	private String createBy_s;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Long getSequence_l() {
		return sequence_l;
	}

	public void setSequence_l(Long sequence_l) {
		this.sequence_l = sequence_l;
	}

	public String getUserId_s() {
		return userId_s;
	}

	public void setUserId_s(String userId_s) {
		this.userId_s = userId_s;
	}

	public String getTenantId_s() {
		return tenantId_s;
	}

	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}

	public Long getQuestionnairSeq_l() {
		return questionnairSeq_l;
	}

	public void setQuestionnairSeq_l(Long questionnairSeq_l) {
		this.questionnairSeq_l = questionnairSeq_l;
	}

	public String getQuestionnairTitle_s() {
		return questionnairTitle_s;
	}

	public void setQuestionnairTitle_s(String questionnairTitle_s) {
		this.questionnairTitle_s = questionnairTitle_s;
	}

	public Long getQuestionnairQuestionSeq_l() {
		return questionnairQuestionSeq_l;
	}

	public void setQuestionnairQuestionSeq_l(Long questionnairQuestionSeq_l) {
		this.questionnairQuestionSeq_l = questionnairQuestionSeq_l;
	}

	public String getQuestionnairQuestionTitle_s() {
		return questionnairQuestionTitle_s;
	}

	public void setQuestionnairQuestionTitle_s(String questionnairQuestionTitle_s) {
		this.questionnairQuestionTitle_s = questionnairQuestionTitle_s;
	}

	public Long[] getQuestionnairQuestionOptionSeq_ls() {
		return questionnairQuestionOptionSeq_ls;
	}

	public void setQuestionnairQuestionOptionSeq_ls(Long[] questionnairQuestionOptionSeq_ls) {
		this.questionnairQuestionOptionSeq_ls = questionnairQuestionOptionSeq_ls;
	}

	public String[] getQuestionnairQuestionOptionScore_ss() {
		return questionnairQuestionOptionScore_ss;
	}

	public void setQuestionnairQuestionOptionScore_ss(String[] questionnairQuestionOptionScore_ss) {
		this.questionnairQuestionOptionScore_ss = questionnairQuestionOptionScore_ss;
	}

	public String[] getQuestionnairQuestionAnswer_ss() {
		return questionnairQuestionAnswer_ss;
	}

	public void setQuestionnairQuestionAnswer_ss(String[] questionnairQuestionAnswer_ss) {
		this.questionnairQuestionAnswer_ss = questionnairQuestionAnswer_ss;
	}

	public Date getCreateTime_dt() {
		return ISODateTime.parseISOString(createTime_dt);
	}

	public void setCreateTime_dt(String createTime_dt) {
		this.createTime_dt = createTime_dt;
	}

	public String getCreateBy_s() {
		return createBy_s;
	}

	public void setCreateBy_s(String createBy_s) {
		this.createBy_s = createBy_s;
	}

	@Override
	public Map<String, String> createFieldNameMap() {

		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("sequence", "sequence_l");
			fieldNameMap.put("userId", "userId_s");
			fieldNameMap.put("tenantId", "tenantId_s");
			fieldNameMap.put("questionnairSeq", "questionnairSeq_l");
			fieldNameMap.put("questionnairTitle", "questionnairTitle_s");
			fieldNameMap.put("questionnairQuestionSeq", "questionnairQuestionSeq_l");
			fieldNameMap.put("questionnairQuestionTitle", "questionnairQuestionTitle_s");
			fieldNameMap.put("questionnairQuestionOptionSeq", "questionnairQuestionOptionSeq_ls");
			fieldNameMap.put("questionnairQuestionOptionScore", "questionnairQuestionOptionScore_ss");
			fieldNameMap.put("questionnairQuestionAnswer", "questionnairQuestionAnswer_ss");
			fieldNameMap.put("createTime", "createTime_dt");
			fieldNameMap.put("createBy", "createBy_s");
		}
		return fieldNameMap;
	}

}
