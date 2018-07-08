package com.syntrontech.pmo.cip.model.solr;

import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.solr.SolrDoc;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class SolrEmergencyContacts implements TO<EmergencyContact>, SolrDoc {
	
	private String id;
	
	private Long emergencyContactSequence_l;
	
	private String subjectId_s;
	
	private String userId_s;
	
	private String tenantId_s;
	
	private String name_s;
	
	private String phone_s;
	
	private String email_s;
	
	private String status_s;
	
	private static Map<String, String> fieldNameMap;
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("subjectId", "subjectId_s");
			fieldNameMap.put("userId", "userId_s");
			fieldNameMap.put("tenantId", "tenantId_s");
			fieldNameMap.put("name", "name_s");
			fieldNameMap.put("phone", "phone_s");
			fieldNameMap.put("email", "email_s");
			fieldNameMap.put("status", "status_s");
		}
		return fieldNameMap;
	}
	
	@Override
	public TO<EmergencyContact> convertFrom(EmergencyContact model){
		
		this.id = this.getClass().getSimpleName()+model.getSequence();
		this.emergencyContactSequence_l = model.getSequence();
		this.subjectId_s = model.getSubjectId();
		this.userId_s = model.getUserId();
		this.tenantId_s = model.getTenantId();
		this.name_s = model.getName();
		this.phone_s = model.getPhone();
		this.email_s = model.getEmail();
		this.status_s = model.getEmail();
		
		return this;
		
	}

	@Override
	public String getId() {
		return id;
	}

	public String getSubjectId_s() {
		return subjectId_s;
	}
	
	public Long getEmergencyContactSequence_l() {
		return emergencyContactSequence_l;
	}

	public void setEmergencyContactSequence_l(Long emergencyContactSequence_l) {
		this.emergencyContactSequence_l = emergencyContactSequence_l;
	}

	public void setSubjectId_s(String subjectId_s) {
		this.subjectId_s = subjectId_s;
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

	public String getName_s() {
		return name_s;
	}

	public void setName_s(String name_s) {
		this.name_s = name_s;
	}

	public String getPhone_s() {
		return phone_s;
	}

	public void setPhone_s(String phone_s) {
		this.phone_s = phone_s;
	}

	public String getEmail_s() {
		return email_s;
	}

	public void setEmail_s(String email_s) {
		this.email_s = email_s;
	}

	public String getStatus_s() {
		return status_s;
	}

	public void setStatus_s(String status_s) {
		this.status_s = status_s;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
