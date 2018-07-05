package com.syntrontech.pmo.mv.syncare_service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "subject_unit_log")
public class SubjectUnitLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence")
	private Long sequence;
	
	@Column(name = "subject_id", nullable = false)
	private String subjectId;
	
	@Column(name = "subject_name", nullable = false)
	private String subjectName;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "tenant_id", nullable = false)
	private String tenantId;
	
	@Column(name = "unit_id", nullable = false)
	private String unitId;
	
	@Column(name = "unit_name", nullable = false)
	private String unitName;
	
	@Column(name = "createtime", nullable = false)
	private Date createTime;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
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

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
