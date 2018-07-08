package com.syntrontech.pmo.cip.model.solr;

public class SolrBodyWaist extends SolrMeasurement{
	private String id;
	private Double waist_d;
	

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public Double getWaist_d() {
		return waist_d;
	}

	public void setWaist_d(Double waist_d) {
		this.waist_d = waist_d;
	}
}
