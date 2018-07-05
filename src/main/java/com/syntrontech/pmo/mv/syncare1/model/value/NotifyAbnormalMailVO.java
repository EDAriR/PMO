package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NotifyAbnormalMailVO implements Serializable {
	private static final long serialVersionUID = -2624455449816583621L;

	private String userId;
	
	private String userAccount;
	
	private String userName;

	private String userAge;
	
	private String userMaxBP;
	
	private String userMinBP;
	
	private String userHeartRate;
	
	private String area;
	
	private Date recordDate;

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserMaxBP() {
		return userMaxBP;
	}

	public void setUserMaxBP(String userMaxBP) {
		this.userMaxBP = userMaxBP;
	}

	public String getUserMinBP() {
		return userMinBP;
	}

	public void setUserMinBP(String userMinBP) {
		this.userMinBP = userMinBP;
	}

	public String getUserHeartRate() {
		return userHeartRate;
	}

	public void setUserHeartRate(String userHeartRate) {
		this.userHeartRate = userHeartRate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
}
