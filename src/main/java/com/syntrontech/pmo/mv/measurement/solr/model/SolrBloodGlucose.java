package com.syntrontech.pmo.mv.measurement.solr.model;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.measurement.model.BloodGlucose;
import com.syntrontech.measurement.restful.to.TO;

public class SolrBloodGlucose extends SolrMeasurement implements TO<BloodGlucose>, SolrDoc{
	
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	private String id;
	private Long glucose_l;
	private String glucoseType_s;

	public Long getGlucose_l() {
		return glucose_l;
	}

	public void setGlucose_l(Long glucose_l) {
		this.glucose_l = glucose_l;
	}

	public String getGlucoseType_s() {
		return glucoseType_s;
	}

	public void setGlucoseType_s(String glucoseType_s) {
		this.glucoseType_s = glucoseType_s;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if (fieldNameMap.isEmpty()) {
			fieldNameMap.put("bloodGlucose", "glucose_l");
			fieldNameMap.put("type", "glucoseType_s");
			fieldNameMap.putAll(findCommonFieldNameMap());
		}
		return fieldNameMap;
	}
	
	@Override
	public TO<BloodGlucose> convertFrom(BloodGlucose model) {
		
		this.glucose_l=model.getGlucose().longValue();
		this.glucoseType_s=model.getGlucoseType().toString();
		
		this.id = this.getClass().getSimpleName() + model.getSequence();
		this.sequence_l = model.getSequence();
		this.recordTime_dt = Instant.ofEpochMilli(model.getRecordTime().getTime()).toString();
		this.createBy_s = model.getCreateBy();
		this.tenantId_s = model.getTenantId();
		this.subjectId_s=model.getSubjectId();
		this.subjectName_s=model.getSubjectName();
		this.subjectAge_l = model.getSubjectAge().longValue();
		this.subjectGender_s = model.getSubjectGender().toString();
		this.ruleDescription_s = model.getRuleDescription();
		this.unitId_s = model.getUnitId();
		this.status_s = model.getStatus().toString();
		this.subjectSeq_l = model.getSubjectSeq();
		this.subjectUserId_s = model.getSubjectUserId();
		this.unitName_s=model.getUnitName();
		this.macAddress_s=model.getDeviceMacAddress();
		this.parentUnitId_s=model.getParentUnitId();
		this.parentUnitName_s=model.getParentUnitName();
		this.deviceId_s=model.getDeviceId();
		return this;
	}
	
	public TO<SolrBloodGlucose> convertToTO(TO<SolrBloodGlucose> to) {
		return to.convertFrom(this);
	}
	
}
