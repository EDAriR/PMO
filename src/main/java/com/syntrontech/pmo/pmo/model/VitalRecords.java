package com.syntrontech.pmo.pmo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public class VitalRecords {
	@JsonProperty("VitalRecordList")
	private List<VitalRecord> records = new ArrayList<>();

	public VitalRecords() {
	}
	
	@JsonCreator
	public VitalRecords(
			@JsonProperty("VitalRecordList") List<VitalRecord> records) {
		this.records = records;
	}

	public List<VitalRecord> getRecords() {
		return records;
	}

	public void setRecords(List<VitalRecord> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "VitalRecords [records=" + records + "]";
	}
}
