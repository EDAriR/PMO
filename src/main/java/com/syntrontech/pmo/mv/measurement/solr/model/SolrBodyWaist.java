package com.syntrontech.pmo.mv.measurement.solr.model;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.measurement.model.BodyWaist;
import com.syntrontech.measurement.restful.to.TO;

public class SolrBodyWaist extends SolrMeasurement implements TO<BodyWaist>, SolrDoc{
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	private String id;
	private Double waist_d;
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getWaist_d() {
		return waist_d;
	}

	public void setWaist_d(Double waist_d) {
		this.waist_d = waist_d;
	}

	@Override
	public TO<BodyWaist> convertFrom(BodyWaist model) {
		this.waist_d=model.getWaist();
		
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
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if (fieldNameMap.isEmpty()) {
			fieldNameMap.put("waist", "waist_d");
			fieldNameMap.putAll(findCommonFieldNameMap());
		}
		return fieldNameMap;
	}

	public TO<SolrBodyWaist> convertToTO(TO<SolrBodyWaist> to) {
		return to.convertFrom(this);
	}

}
