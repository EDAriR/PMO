package com.syntrontech.pmo.syncare1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="area_code")
public class AreaCode {
	private static final long serialVersionUID = -3575961371095808601L;

	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="city_code")
	private String cityCode;
	
	@Column(name="downtown_name")
	private String downtownName;
	
	@Column(name="downtown_code")
	private String downtownCode;
	
	@Column(name="village_name")
	private String villageName;
	
	@Column(name="village_code")
	private String villageCode;



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

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}



	@Override
	public String toString() {
		return "AreaCode ["
				+ "code=" + code 
				+ ", cityName=" + cityName 
				+ ", cityCode=" + cityCode 
				+ ", downtownName=" + downtownName 
				+ ", downtownCode=" + downtownCode 
				+ ", villageName=" + villageName 
				+ ", villageCode=" + villageCode 
				+ "]";
	}

}
