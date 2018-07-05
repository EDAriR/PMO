package com.syntrontech.pmo.mv.measurement.solr.model;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.measurement.model.AbnormalBloodPressureLog;
import com.syntrontech.measurement.restful.to.TO;

public class SolrAbnormalBloodPressureLog implements TO<AbnormalBloodPressureLog>, SolrDoc{
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();

	private String id;
	private Long squence_l;
	private Long abnormalBPSquence_l;
	private String caseStatus_s;
	private String subjectId_s;
	private String subjectName_s;
	private String caseCreatorUserId_s;
	private String caseCreatorUserName_s;
	private String caseDescription_s;
	private String changeCaseStatusTime_dt;
	private String tenantId_s;
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Long getSquence_l() {
		return squence_l;
	}

	public void setSquence_l(Long squence_l) {
		this.squence_l = squence_l;
	}

	public Long getAbnormalBPSquence_l() {
		return abnormalBPSquence_l;
	}

	public void setAbnormalBPSquence_l(Long abnormalBPSquence_l) {
		this.abnormalBPSquence_l = abnormalBPSquence_l;
	}

	public String getCaseStatus_s() {
		return caseStatus_s;
	}

	public void setCaseStatus_s(String caseStatus_s) {
		this.caseStatus_s = caseStatus_s;
	}

	public String getSubjectId_s() {
		return subjectId_s;
	}

	public void setSubjectId_s(String subjectId_s) {
		this.subjectId_s = subjectId_s;
	}

	public String getSubjectName_s() {
		return subjectName_s;
	}

	public void setSubjectName_s(String subjectName_s) {
		this.subjectName_s = subjectName_s;
	}

	public String getCaseCreatorUserId_s() {
		return caseCreatorUserId_s;
	}

	public void setCaseCreatorUserId_s(String caseCreatorUserId_s) {
		this.caseCreatorUserId_s = caseCreatorUserId_s;
	}

	public String getCaseCreatorUserName_s() {
		return caseCreatorUserName_s;
	}

	public void setCaseCreatorUserName_s(String caseCreatorUserName_s) {
		this.caseCreatorUserName_s = caseCreatorUserName_s;
	}

	public String getCaseDescription_s() {
		return caseDescription_s;
	}

	public void setCaseDescription_s(String caseDescription_s) {
		this.caseDescription_s = caseDescription_s;
	}

	public String getChangeCaseStatusTime_dt() {
		return changeCaseStatusTime_dt;
	}

	public void setChangeCaseStatusTime_dt(String changeCaseStatusTime_dt) {
		this.changeCaseStatusTime_dt = changeCaseStatusTime_dt;
	}

	public String getTenantId_s() {
		return tenantId_s;
	}
	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}
	
	@Override
	public TO<AbnormalBloodPressureLog> convertFrom(AbnormalBloodPressureLog model) {
		this.id = this.getClass().getSimpleName() + model.getSequence();
		this.squence_l=model.getSequence();
		this.abnormalBPSquence_l = model.getAbnormalBloodPressureSquence();
		this.caseStatus_s = model.getCaseStatus().toString();
		this.subjectId_s = model.getSubjectId();
		this.subjectName_s = model.getSubjectName();
		this.caseCreatorUserId_s = model.getCaseCreatorUserId();
		this.caseCreatorUserName_s = model.getCaseCreatorUserName();
		this.caseDescription_s = model.getCaseDescription();
		this.changeCaseStatusTime_dt = 
				Instant.ofEpochMilli(model.getChangeCaseStatusTime().getTime()).toString();
		this.tenantId_s=model.getTenantId();
		return this;
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if (fieldNameMap.isEmpty()) {
			fieldNameMap.put("logId", "squence_l");
			fieldNameMap.put("caseId", "abnormalBPSquence_l");
			fieldNameMap.put("caseStatus", "caseStatus_s");
			fieldNameMap.put("subjectId", "subjectId_s");
			fieldNameMap.put("subjectName", "subjectName_s");
			fieldNameMap.put("caseCreatorUserId", "caseCreatorUserId_s");
			fieldNameMap.put("caseCreatorUserName", "caseCreatorUserName_s");
			fieldNameMap.put("caseDescription", "caseDescription_s");
			fieldNameMap.put("createTime", "changeCaseStatusTime_dt");
			fieldNameMap.put("tenantId", "tenantId_s");
		}
		return fieldNameMap;
	}
	
	public TO<SolrAbnormalBloodPressureLog> convertToTO(TO<SolrAbnormalBloodPressureLog> to) {
		return to.convertFrom(this);
	}

}
