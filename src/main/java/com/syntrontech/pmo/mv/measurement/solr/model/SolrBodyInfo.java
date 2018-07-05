package com.syntrontech.pmo.mv.measurement.solr.model;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.measurement.model.BodyInfo;
import com.syntrontech.measurement.restful.to.TO;

public class SolrBodyInfo extends SolrMeasurement implements TO<BodyInfo>, SolrDoc{
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	private String id;
	private Double height_d;
	private Double weight_d;
	private Double bmi_d;
	private Double bfp_d;
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getHeight_d() {
		return height_d;
	}
	public void setHeight_d(Double height_d) {
		this.height_d = height_d;
	}
	public Double getWeight_d() {
		return weight_d;
	}
	public void setWeight_d(Double weight_d) {
		this.weight_d = weight_d;
	}
	public Double getBmi_d() {
		return bmi_d;
	}
	public void setBmi_d(Double bmi_d) {
		this.bmi_d = bmi_d;
	}
	public Double getBfp_d() {
		return bfp_d;
	}
	public void setBfp_d(Double bfp_d) {
		this.bfp_d = bfp_d;
	}
	@Override
	public TO<BodyInfo> convertFrom(BodyInfo model) {
		this.height_d=model.getHeight();
		this.weight_d=model.getWeight();
		this.bmi_d=model.getBmi();
		this.bfp_d=model.getBfp();
		
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

	public TO<SolrBodyInfo> convertToTO(TO<SolrBodyInfo> to) {
		return to.convertFrom(this);
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if (fieldNameMap.isEmpty()) {
			fieldNameMap.put("bodyHeight", "height_d");
			fieldNameMap.put("bodyWeight", "weight_d");
			fieldNameMap.put("bmi", "bmi_d");
			fieldNameMap.put("bodyFatPercentage", "bfp_d");
			fieldNameMap.putAll(findCommonFieldNameMap());
		}
		return fieldNameMap;
	}
}
