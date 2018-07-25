package com.syntrontech.pmo.cip.model;

import com.syntrontech.pmo.model.common.ModelStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class EmergencyContact {
	
	private Long sequence;
	
	private String subjectId;
	
	private String userId;
	
	private String tenantId;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private ModelStatus status;

	@Override
	public String toString() {
		return "EmergencyContact:[" +
				"subjectId=" + subjectId + ", " +
				"tenantId=" + tenantId + ", " +
				"userId=" + userId + ", " +
				"name=" + name + " " +
				"]";
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ModelStatus getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = status;
	}
	
}
