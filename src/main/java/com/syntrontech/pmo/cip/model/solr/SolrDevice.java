package com.syntrontech.pmo.cip.model.solr;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.pmo.cip.model.Device;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.solr.SolrDoc;

public class SolrDevice implements TO<Device>, SolrDoc {
	
	private String id;
	
	private String deviceId_s;
	
	private String deviceName_s;
	
	private String deviceMacAddress_s;
	
	private String deviceSerialNumber_s;
	
	private String deviceUnitId_s;
	
	private String deviceUnitName_s;
	
	private String deviceTenantId_s;
	
	private String deviceCreateTime_dt;
	
	private String deviceUpdateTime_dt;
	
	private static Map<String, String> fieldNameMap;

	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDeviceId_s() {
		return deviceId_s;
	}

	public void setDeviceId_s(String deviceId_s) {
		this.deviceId_s = deviceId_s;
	}

	public String getDeviceName_s() {
		return deviceName_s;
	}

	public void setDeviceName_s(String deviceName_s) {
		this.deviceName_s = deviceName_s;
	}

	public String getDeviceMacAddress_s() {
		return deviceMacAddress_s;
	}

	public void setDeviceMacAddress_s(String deviceMacAddress_s) {
		this.deviceMacAddress_s = deviceMacAddress_s;
	}

	public String getDeviceSerialNumber_s() {
		return deviceSerialNumber_s;
	}

	public void setDeviceSerialNumber_s(String deviceSerialNumber_s) {
		this.deviceSerialNumber_s = deviceSerialNumber_s;
	}

	public String getDeviceUnitId_s() {
		return deviceUnitId_s;
	}

	public void setDeviceUnitId_s(String deviceUnitId_s) {
		this.deviceUnitId_s = deviceUnitId_s;
	}

	public String getDeviceUnitName_s() {
		return deviceUnitName_s;
	}

	public void setDeviceUnitName_s(String deviceUnitName_s) {
		this.deviceUnitName_s = deviceUnitName_s;
	}

	public String getDeviceTenantId_s() {
		return deviceTenantId_s;
	}

	public void setDeviceTenantId_s(String deviceTenantId_s) {
		this.deviceTenantId_s = deviceTenantId_s;
	}

	public String getDeviceCreateTime_dt() {
		return deviceCreateTime_dt;
	}

	public void setDeviceCreateTime_dt(String deviceCreateTime_dt) {
		this.deviceCreateTime_dt = deviceCreateTime_dt;
	}

	public String getDeviceUpdateTime_dt() {
		return deviceUpdateTime_dt;
	}

	public void setDeviceUpdateTime_dt(String deviceUpdateTime_dt) {
		this.deviceUpdateTime_dt = deviceUpdateTime_dt;
	}

	public TO<SolrDevice> convertToTO(TO<SolrDevice> to){
		return to.convertFrom(this);
	}
	
	@Override
	public TO<Device> convertFrom(Device model) {
		this.id = this.getClass().getSimpleName()+model.getSequence();
		this.deviceId_s = model.getId();
		this.deviceName_s = model.getName();
		this.deviceMacAddress_s = model.getMacAddress();
		this.deviceSerialNumber_s = model.getSerialNumber();
		this.deviceUnitId_s = model.getUnitId();
		this.deviceUnitName_s = model.getUnitName();
		this.deviceTenantId_s = model.getTenantId();
		this.deviceCreateTime_dt = Instant.ofEpochMilli(model.getCreateTime().getTime())
										  .toString();
		this.deviceUpdateTime_dt = Instant.ofEpochMilli(model.getUpdateTime().getTime())
										  .toString();
		return this;
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("deviceId", "deviceId_s");
			fieldNameMap.put("deviceName", "deviceName_s");
			fieldNameMap.put("macAddress", "deviceMacAddress_s");
			fieldNameMap.put("serialNumber", "deviceSerialNumber_s");
			fieldNameMap.put("unitId", "deviceUnitId_s");
			fieldNameMap.put("unitName", "deviceUnitName_s");
			fieldNameMap.put("tenantId", "deviceTenantId_s");
			fieldNameMap.put("createTime", "deviceCreateTime_dt");
			fieldNameMap.put("createBy", "deviceCreateBy_s");
			fieldNameMap.put("updateTime", "deviceUpdateTime_dt");
		}
		return fieldNameMap;
	}

}
