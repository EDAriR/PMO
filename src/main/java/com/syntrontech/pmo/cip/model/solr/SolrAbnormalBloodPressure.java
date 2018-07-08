package com.syntrontech.pmo.cip.model.solr;

public class SolrAbnormalBloodPressure extends SolrMeasurement  {

	private String id;
	private Long bloodPressureSeq_l;
	private Integer systolicPressure_i;
	private Integer diastolicPressure_i;
	private Integer heartRate_i;
	
	private String caseStatus_s;
	private String lastChangeCaseStatusTime_dt;
	
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
	
}
