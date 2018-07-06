package com.syntrontech.pmo.cip.solr;

public class SolrBiochemistry extends SolrMeasurement {

	private String id;
	private String value_s;
	private Long groupId_l;
	private Long mappingsSeq_l;
	private String mappingsProject_s;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue_s() {
		return value_s;
	}

	public void setValue_s(String value_s) {
		this.value_s = value_s;
	}

	public Long getGroupId_l() {
		return groupId_l;
	}

	public void setGroupId_l(Long groupId_l) {
		this.groupId_l = groupId_l;
	}

	public Long getMappingsSeq_l() {
		return mappingsSeq_l;
	}

	public void setMappingsSeq_l(Long mappingsSeq_l) {
		this.mappingsSeq_l = mappingsSeq_l;
	}

	public String getMappingsProject_s() {
		return mappingsProject_s;
	}

	public void setMappingsProject_s(String mappingsProject_s) {
		this.mappingsProject_s = mappingsProject_s;
	}

}
