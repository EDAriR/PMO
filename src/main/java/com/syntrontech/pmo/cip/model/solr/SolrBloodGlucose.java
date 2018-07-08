package com.syntrontech.pmo.cip.model.solr;

public class SolrBloodGlucose extends SolrMeasurement{
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

	public String getId() {
		return id;
	}
}
