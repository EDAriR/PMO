package com.syntrontech.pmo.pmo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties
public class VitalRecord {
	public enum Type{
		/**
		 * 血壓
		 */
		BP, 
		/**
		 * 血糖
		 */
		BG
	}
	
	public enum Mark{
		/**
		 * 飯前血糖
		 */
		AC, 
		/**
		 * 飯後血糖
		 */
		PC, 
		/**
		 * 隨機血糖
		 */
		NM
	}
	
	public enum InputType{
		/**
		 * 儀器輸入
		 */
		Device, 
		/**
		 * 手動輸入
		 */
		Manual
	}
	
	@JsonProperty("IDNo")
	private String idNo = "";
	
	@JsonProperty("IDENTIFIER")
	private String identifier = "";
	
	@JsonProperty("LocationID")
	private String locationId = "";
	
	@JsonProperty("Type")
	private Type type;
	
	@JsonProperty("MTime")
	private String mTime = "";
	
	@JsonProperty("Mark")
	private Optional<Mark> mark = Optional.ofNullable(null);
	
	@JsonProperty("InputType")
	private InputType inputType = InputType.Device;
	
	@JsonProperty("Values")
	private List<Number> values = new ArrayList<>();
	
	@JsonProperty("Remark")
	private String remark = "";
	
	@JsonProperty("DeviceModel")
	private String deviceModel = "";
	
	@JsonProperty("DeviceSN")
	private String deviceSn = "";

	public VitalRecord() {
	}
	
	@JsonCreator
	public VitalRecord(
			@JsonProperty("IDNo") String idNo,
			@JsonProperty("IDENTIFIER") String identifier,
			@JsonProperty("LocationID") String locationId,
			@JsonProperty("Type") Type type,
			@JsonProperty("MTime") String mTime,
			@JsonProperty("Mark") String mark,
			@JsonProperty("InputType") InputType inputType,
			@JsonProperty("Values") List<Number> values,
			@JsonProperty("Remark") String remark,
			@JsonProperty("DeviceModel") String deviceModel,
			@JsonProperty("DeviceSN") String deviceSn) {
		this.idNo = idNo;
		this.identifier = identifier;
		this.locationId = locationId;
		this.type = type;
		this.mTime = mTime;
		setMark(mark);
		this.inputType = inputType;
		this.values = values;
		this.remark = remark;
		this.deviceModel = deviceModel;
		this.deviceSn = deviceSn;
	}

	public String getIdNo() {
		return idNo;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getLocationId() {
		return locationId;
	}

	public Type getType() {
		return type;
	}

	public String getmTime() {
		return mTime;
	}

	public String getMark() {
		return mark.isPresent()?mark.get().name():"";
	}

	public InputType getInputType() {
		return inputType;
	}

	public List<Number> getValues() {
		return values;
	}

	public String getRemark() {
		return remark;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	public void setMark(String mark) {
		this.mark = StringUtils.isNotBlank(mark)?
				Optional.ofNullable(Mark.valueOf(mark)):
					Optional.ofNullable(null);
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public void setValues(List<Number> values) {
		this.values = values;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	@Override
	public String toString() {
		return "VitalRecord [idNo=" + idNo + ", identifier=" + identifier
				+ ", locationId=" + locationId + ", Type=" + type + ", mTime="
				+ mTime + ", mark=" + getMark() + ", inputType=" + inputType
				+ ", values=" + values + ", remark=" + remark
				+ ", deviceModel=" + deviceModel + ", deviceSn=" + deviceSn
				+ "]";
	}
}
