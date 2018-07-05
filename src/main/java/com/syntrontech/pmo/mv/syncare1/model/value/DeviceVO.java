package com.syntrontech.pmo.mv.syncare1.model.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DeviceVO {
	private String serialNo;
	
	private String name;
	
	private String locationId;

	public String getSerialNo() {
		return serialNo;
	}

	public String getName() {
		return name;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
}
