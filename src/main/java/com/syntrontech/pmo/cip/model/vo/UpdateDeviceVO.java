package com.syntrontech.pmo.cip.model.vo;

import com.syntrontech.autoTool.annotation.ParamNotNull;
import com.syntrontech.autoTool.annotation.ParamStringRestrict;

public class UpdateDeviceVO {
	
	@ParamStringRestrict
	private String deviceName;
	
	@ParamNotNull
	@ParamStringRestrict
	private String macAddress;
	
	@ParamStringRestrict
	private String serialNumber;
	
	@ParamNotNull
	@ParamStringRestrict
	private String unitId;

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
	
}
