package com.syntrontech.pmo.cip.model.solr;


public class SolrAbnormalBloodPressureLog extends SolrMeasurement{

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
}
