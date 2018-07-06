package com.syntrontech.pmo.cip.solr;

public class SolrBodyTemperature extends SolrMeasurement {
	private String id;
	private Double temperature_d;

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
}
