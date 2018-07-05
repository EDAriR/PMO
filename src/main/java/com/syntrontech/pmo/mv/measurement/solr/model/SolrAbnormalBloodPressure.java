package com.syntrontech.pmo.mv.measurement.solr.model;

import com.syntrontech.pmo.mv.measurement.TO;
import com.syntrontech.pmo.mv.measurement.model.AbnormalBloodPressure;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SolrAbnormalBloodPressure extends SolrMeasurement implements TO<AbnormalBloodPressure>, SolrDoc{
	private static Map<String, String> fieldNameMap = new ConcurrentHashMap<>();
	private String id;
	private Long bloodPressureSeq_l;
	private Integer systolicPressure_i;
	private Integer diastolicPressure_i;
	private Integer heartRate_i;
	
	private String caseStatus_s;
	private String lastChangeCaseStatusTime_dt;
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Long getBloodPressureSeq_l() {
		return bloodPressureSeq_l;
	}
	public void setBloodPressureSeq_l(Long bloodPressureSeq_l) {
		this.bloodPressureSeq_l = bloodPressureSeq_l;
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
	public String getCaseStatus_s() {
		return caseStatus_s;
	}
	public void setCaseStatus_s(String caseStatus_s) {
		this.caseStatus_s = caseStatus_s;
	}
	public String getLastChangeCaseStatusTime_dt() {
		return lastChangeCaseStatusTime_dt;
	}
	public void setLastChangeCaseStatusTime_dt(String lastChangeCaseStatusTime_dt) {
		this.lastChangeCaseStatusTime_dt = lastChangeCaseStatusTime_dt;
	}
	
	@Override
	public TO<AbnormalBloodPressure> convertFrom(AbnormalBloodPressure model) {
		this.id = this.getClass().getSimpleName() + model.getSequence();
		this.sequence_l = model.getSequence();
		this.bloodPressureSeq_l = model.getBloodPressureSeq();
		this.subjectSeq_l = model.getSubjectSeq();
		this.subjectId_s = model.getSubjectId();
		this.subjectName_s = model.getSubjectName();
//		this.subjectGender_s=model.getSubjectGender().toString();
		this.subjectUserId_s = model.getSubjectUserId();
		this.systolicPressure_i = model.getSystolicPressure();
		this.diastolicPressure_i = model.getDiastolicPressure();
		this.heartRate_i = model.getHeartRate();
		this.recordTime_dt = Instant.ofEpochMilli(model.getRecordtime().getTime()).toString();
//		this.caseStatus_s = model.getCaseStatus().toString();
		this.unitId_s = model.getUnitId();
		this.tenantId_s = model.getTenantId();
		this.subjectAge_l=model.getSubjectAge().longValue();
		this.unitName_s=model.getUnitName();
		this.subjectUserName_s=model.getSubjectUserName();
		this.lastChangeCaseStatusTime_dt=Instant.ofEpochMilli(model.getLastChangeCaseStatusTime().getTime()).toString();
		this.parentUnitId_s=model.getParentUnitId();
		this.parentUnitName_s=model.getParentUnitName();
		this.deviceId_s=model.getDeviceId();
		
		return this;
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if (fieldNameMap.isEmpty()) {
			fieldNameMap.put("bloodPressureSeq", "bloodPressureSeq_l");
			fieldNameMap.put("caseId", "sequence_l");
			fieldNameMap.put("systolic", "systolicPressure_i");
			fieldNameMap.put("diastolic", "diastolicPressure_i");
			fieldNameMap.put("heartRate", "heartRate_i");
			fieldNameMap.put("caseStatus", "caseStatus_s");
			fieldNameMap.put("caseDescription", "caseDescription_s");
			fieldNameMap.put("changeCaseStatusTime", "changeCaseStatusTime_dt");
			fieldNameMap.put("caseCreatorUserId", "caseCreatorUserId_s");
			fieldNameMap.put("caseCreatorUserName", "caseCreatorUserName_s");
			fieldNameMap.put("lastChangeCaseStatusTime", "lastChangeCaseStatusTime_dt");
			fieldNameMap.putAll(findCommonFieldNameMap());
		}
		return fieldNameMap;
	}
	
	public TO<SolrAbnormalBloodPressure> convertToTO(TO<SolrAbnormalBloodPressure> to) {
		return to.convertFrom(this);
	}
	
}
