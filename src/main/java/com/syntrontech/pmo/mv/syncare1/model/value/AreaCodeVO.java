package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AreaCodeVO implements Serializable {
	private static final long serialVersionUID = -2624455449816583621L;

	private String code;
	
	private String cityName;
	
	private String cityCode;

	private String downtownName;
	
	private String downtownCode;
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDowntownName() {
		return downtownName;
	}

	public void setDowntownName(String downtownName) {
		this.downtownName = downtownName;
	}

	public String getDowntownCode() {
		return downtownCode;
	}

	public void setDowntownCode(String downtownCode) {
		this.downtownCode = downtownCode;
	}
}
