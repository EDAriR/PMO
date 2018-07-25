package com.syntrontech.pmo.cip.model;

import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.model.common.ModelStatus;

import java.util.Date;


public class Device {
	private Long sequence;
	
	private String id;
	
	private String name;
	
	private String macAddress;
	
	private String serialNumber;
	
	private String unitId;
	
	private String unitName;
	
	private String tenantId;
	
	private Date createTime;
	
	private String createBy;
	
	private Date updateTime;
	
	private String updateBy;
	
	private ModelStatus status;

	@Override
	public String toString() {
		return "Device:[" +
				"id=" + id + ", " +
				"serialNumber=" + serialNumber + ", " +
				"unitId=" + unitId + ", " +
				"createBy=" + createBy + ", " +
				"tenantId=" + tenantId + " " +
				"]";
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public ModelStatus getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = status;
	}
	
	public TO<Device> convertToTO(TO<Device> to){
		return to.convertFrom(this);
	}
}
