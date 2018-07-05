package com.syntrontech.pmo.mv.measurement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.measurement.model.common.BloodPressureCaseStatus;
import com.syntrontech.measurement.restful.to.TO;

@Table
@Entity(name = "abnormal_blood_pressure_log")
public class AbnormalBloodPressureLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sequence;
	
	@Column(name = "abnormal_blood_pressure_squence", nullable = false)
	private Long abnormalBloodPressureSquence;

    @Column(name = "case_status", nullable = false)
    @Enumerated(EnumType.STRING)
	private BloodPressureCaseStatus caseStatus;
	
	@Column(name = "subject_id")
	private String subjectId;
	
	@Column(name = "subject_name")
	private String subjectName;

	@Column(name = "case_creator_user_id")
	private String caseCreatorUserId;

	@Column(name = "case_creator_user_name")
	private String caseCreatorUserName;
	
	@Column(name = "case_description")
	private String caseDescription;

	@Column(name = "recordtime", nullable = false)
	private Date changeCaseStatusTime;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
	
	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public Long getAbnormalBloodPressureSquence() {
		return abnormalBloodPressureSquence;
	}

	public void setAbnormalBloodPressureSquence(Long abnormalBloodPressureSquence) {
		this.abnormalBloodPressureSquence = abnormalBloodPressureSquence;
	}

	public BloodPressureCaseStatus getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(BloodPressureCaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCaseCreatorUserId() {
		return caseCreatorUserId;
	}

	public void setCaseCreatorUserId(String caseCreatorUserId) {
		this.caseCreatorUserId = caseCreatorUserId;
	}

	public String getCaseCreatorUserName() {
		return caseCreatorUserName;
	}

	public void setCaseCreatorUserName(String caseCreatorUserName) {
		this.caseCreatorUserName = caseCreatorUserName;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public Date getChangeCaseStatusTime() {
		return changeCaseStatusTime;
	}

	public void setChangeCaseStatusTime(Date changeCaseStatusTime) {
		this.changeCaseStatusTime = changeCaseStatusTime;
	}
	
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public TO<AbnormalBloodPressureLog> convertToTO(TO<AbnormalBloodPressureLog> to){
		return to.convertFrom(this);
	}
}
