package com.syntrontech.pmo.cip.model.solr;

public class SolrBloodPressureHeartBeat extends SolrMeasurement{
	private String id;
	private Integer systolicPressure_i;
	private Integer diastolicPressure_i;
	private Integer heartRate_i;

	
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


}
