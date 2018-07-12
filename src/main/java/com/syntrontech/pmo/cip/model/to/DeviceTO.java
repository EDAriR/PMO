package com.syntrontech.pmo.cip.model.to;


import com.syntrontech.pmo.cip.model.solr.SolrDevice;
import com.syntrontech.pmo.model.TO.TO;

public class DeviceTO implements TO<SolrDevice> {
	
	private String deviceId;
	
	private String deviceName;
	
	private String macAddress;
	
	private String serialNumber;
	
	private String unitId;
	
	private String unitName;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Override
	public TO<SolrDevice> convertFrom(SolrDevice model) {
		this.deviceId = model.getDeviceId_s();
		this.deviceName = model.getDeviceName_s();
		this.macAddress = model.getDeviceMacAddress_s();
		this.serialNumber = model.getDeviceSerialNumber_s();
		this.unitId = model.getDeviceUnitId_s();
		this.unitName = model.getDeviceUnitName_s();
		return this;
	}
	
}
