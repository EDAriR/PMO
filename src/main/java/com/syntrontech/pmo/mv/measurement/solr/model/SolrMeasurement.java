package com.syntrontech.pmo.mv.measurement.solr.model;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SolrMeasurement {
	protected Long sequence_l;
	protected String recordTime_dt;
	protected String status_s;
	protected String createBy_s;
	protected String tenantId_s;
	protected Long subjectSeq_l;
	protected String subjectId_s;
	protected String subjectName_s;
	protected Long subjectAge_l;
	protected String subjectGender_s;
	protected String subjectUserId_s;
	protected String subjectUserName_s;
	protected String ruleDescription_s;
	protected String unitId_s;
	protected String unitName_s;;
	protected String macAddress_s;
	protected String parentUnitId_s;
	protected String parentUnitName_s;
	protected String deviceId_s;
	
	private static Map<String, String> commonFieldNameMap;
	
	public Long getSequence_l() {
		return sequence_l;
	}
	public void setSequence_l(Long sequence_l) {
		this.sequence_l = sequence_l;
	}
	public String getStatus_s() {
		return status_s;
	}
	public void setStatus_s(String status_s) {
		this.status_s = status_s;
	}
	public String getRecordTime_dt() {
		return recordTime_dt;
	}
	public void setRecordTime_dt(String recordTime_dt) {
		this.recordTime_dt = recordTime_dt;
	}
	public String getCreateBy_s() {
		return createBy_s;
	}
	public void setCreateBy_s(String createBy_s) {
		this.createBy_s = createBy_s;
	}
	public String getTenantId_s() {
		return tenantId_s;
	}
	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}
	public Long getSubjectSeq_l() {
		return subjectSeq_l;
	}
	public void setSubjectSeq_l(Long subjectSeq_l) {
		this.subjectSeq_l = subjectSeq_l;
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
	public Long getSubjectAge_l() {
		return subjectAge_l;
	}
	public void setSubjectAge_l(Long subjectAge_l) {
		this.subjectAge_l = subjectAge_l;
	}
	public String getSubjectGender_s() {
		return subjectGender_s;
	}
	public void setSubjectGender_s(String subjectGender_s) {
		this.subjectGender_s = subjectGender_s;
	}
	public String getSubjectUserId_s() {
		return subjectUserId_s;
	}
	public void setSubjectUserId_s(String subjectUserId_s) {
		this.subjectUserId_s = subjectUserId_s;
	}
	public String getSubjectUserName_s() {
		return subjectUserName_s;
	}
	public void setSubjectUserName_s(String subjectUserName_s) {
		this.subjectUserName_s = subjectUserName_s;
	}
	public String getRuleDescription_s() {
		return ruleDescription_s;
	}
	public void setRuleDescription_s(String ruleDescription_s) {
		this.ruleDescription_s = ruleDescription_s;
	}
	public String getUnitId_s() {
		return unitId_s;
	}
	public void setUnitId_s(String unitId_s) {
		this.unitId_s = unitId_s;
	}
	public String getUnitName_s() {
		return unitName_s;
	}
	public void setUnitName_s(String unitName_s) {
		this.unitName_s = unitName_s;
	}
	public String getMacAddress_s() {
		return macAddress_s;
	}
	public void setMacAddress_s(String macAddress_s) {
		this.macAddress_s = macAddress_s;
	}
	
	public String getParentUnitId_s() {
		return parentUnitId_s;
	}

	public void setParentUnitId_s(String parentUnitId_s) {
		this.parentUnitId_s = parentUnitId_s;
	}
	
	public String getParentUnitName_s() {
		return parentUnitName_s;
	}

	public void setParentUnitName_s(String parentUnitName_s) {
		this.parentUnitName_s = parentUnitName_s;
	}
	
	public String getDeviceId_s() {
		return deviceId_s;
	}

	public void setDeviceId_s(String deviceId_s) {
		this.deviceId_s = deviceId_s;
	}
	
	public static Map<String, String> findCommonFieldNameMap() {
		if (Objects.isNull(commonFieldNameMap)) {
			commonFieldNameMap = new ConcurrentHashMap<>();
			
			commonFieldNameMap.put("sequence", "sequence_l");
			commonFieldNameMap.put("createTime", "recordTime_dt");
			commonFieldNameMap.put("createBy", "createBy_s");
			commonFieldNameMap.put("tenantId", "tenantId_s");
			commonFieldNameMap.put("subjectId", "subjectId_s");
			commonFieldNameMap.put("subjectName", "subjectName_s");
			commonFieldNameMap.put("subjectAge", "subjectAge_l");
			commonFieldNameMap.put("subjectGender", "subjectGender_s");
			commonFieldNameMap.put("ruleDescription", "ruleDescription_s");
			commonFieldNameMap.put("unitId", "unitId_s");
			commonFieldNameMap.put("unitName", "unitName_s");
			commonFieldNameMap.put("status", "status_s");
			commonFieldNameMap.put("subjectSeq", "subjectSeq_l");
			commonFieldNameMap.put("userId", "subjectUserId_s");
			commonFieldNameMap.put("userName", "subjectUserName_s");
			commonFieldNameMap.put("macAddress", "macAddress_s");
			commonFieldNameMap.put("parentUnitId", "parentUnitId_s");
			commonFieldNameMap.put("parentUnitName", "parentUnitName_s");
			commonFieldNameMap.put("deviceId", "deviceId_s");
		}
		return commonFieldNameMap;
	}
	
	
	
}
