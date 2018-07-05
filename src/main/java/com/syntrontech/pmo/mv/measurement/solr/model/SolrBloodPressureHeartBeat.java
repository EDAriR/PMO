package com.syntrontech.pmo.mv.measurement.solr.model;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.measurement.model.BloodPressureHeartBeat;
import com.syntrontech.measurement.restful.to.TO;

public class SolrBloodPressureHeartBeat extends SolrMeasurement implements TO<BloodPressureHeartBeat>, SolrDoc {
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	private String id;
	private Integer systolicPressure_i;
	private Integer diastolicPressure_i;
	private Integer heartRate_i;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSystolicPressure_i() {
		return systolicPressure_i;
	}

	public void setSystolicPressure_i(Integer systolicPressure_i) {
		this.systolicPressure_i = systolicPressure_i;
	}

	public Integer getDiastolicPressure_i() {
		return diastolicPressure_i;
	}

	public void setDiastolicPressure_i(Integer diastolicPressure_i) {
		this.diastolicPressure_i = diastolicPressure_i;
	}

	public Integer getHeartRate_i() {
		return heartRate_i;
	}

	public void setHeartRate_i(Integer heartRate_i) {
		this.heartRate_i = heartRate_i;
	}

	@Override
	public TO<BloodPressureHeartBeat> convertFrom(BloodPressureHeartBeat model) {
		this.id = this.getClass().getSimpleName() + model.getSequence();
		this.systolicPressure_i = model.getSystolicPressure();
		this.diastolicPressure_i = model.getDiastolicPressure();
		this.heartRate_i = model.getHeartRate();
		
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
			fieldNameMap.put("systolic", "systolicPressure_i");
			fieldNameMap.put("diastolic", "diastolicPressure_i");
			fieldNameMap.put("heartRate", "heartRate_i");
			fieldNameMap.putAll(findCommonFieldNameMap());
		}
		return fieldNameMap;
	}

	public TO<SolrBloodPressureHeartBeat> convertToTO(TO<SolrBloodPressureHeartBeat> to) {
		return to.convertFrom(this);
	}
}
