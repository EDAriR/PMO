package com.syntrontech.pmo.mv.measurement.solr.model;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.measurement.model.BodyTemperature;
import com.syntrontech.measurement.restful.to.TO;

public class SolrBodyTemperature extends SolrMeasurement implements TO<BodyTemperature>, SolrDoc {
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	private String id;
	private Double temperature_d;
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getTemperature_d() {
		return temperature_d;
	}
	public void setTemperature_d(Double temperature_d) {
		this.temperature_d = temperature_d;
	}
	@Override
	public TO<BodyTemperature> convertFrom(BodyTemperature model) {
		this.temperature_d=model.getTemperature();
		
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

	public TO<SolrBodyTemperature> convertToTO(TO<SolrBodyTemperature> to) {
		return to.convertFrom(this);
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if (fieldNameMap.isEmpty()) {
			fieldNameMap.put("bodyTemperature", "temperature_d");
			fieldNameMap.putAll(findCommonFieldNameMap());
		}
		return fieldNameMap;
	}
	
}
