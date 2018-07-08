package com.syntrontech.pmo.cip.model.solr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SolrElderlyLivingAlone {
	
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	private String unitId_s;
	private String unitName_s;
	private String subjectId_s;
	private String tenantId_s;
	private String subjectName_s;
	private Long subjectAge_l;

	public String getUnitId_s() {
		return unitId_s;
	}

	public void setUnitId_s(String unitId_s) {
		this.unitId_s = unitId_s;
	}

	public String getSubjectId_s() {
		return subjectId_s;
	}

	public String getUnitName_s() {
		return unitName_s;
	}

	public void setUnitName_s(String unitName_s) {
		this.unitName_s = unitName_s;
	}

	public void setSubjectId_s(String subjectId_s) {
		this.subjectId_s = subjectId_s;
	}

	public String getTenantId_s() {
		return tenantId_s;
	}

	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}

	public String getSubjectName_s() {
		return subjectName_s;
	}

	public void setSubjectName_s(String subjectName_s) {
		this.subjectName_s = subjectName_s;
	}

	public Long getSubjectAge_l() {
		return subjectAge_l;
	}

	public void setSubjectAge_l(Long subjectAge_l) {
		this.subjectAge_l = subjectAge_l;
	}

}
