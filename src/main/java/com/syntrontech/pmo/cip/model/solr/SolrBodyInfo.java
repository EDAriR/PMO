package com.syntrontech.pmo.cip.model.solr;

public class SolrBodyInfo extends SolrMeasurement {
	private String id;
	private Double height_d;
	private Double weight_d;
	private Double bmi_d;
	private Double bfp_d;
	
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
}
