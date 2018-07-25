package com.syntrontech.pmo.auth.model;

import com.syntrontech.pmo.model.common.MapToUserField;

import javax.persistence.EnumType;


//store in Postgres
public class AccountList {
	
	private String account;
	
	private String userId;
	
	private MapToUserField mapToUserField;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public MapToUserField getMapToUserField() {
		return mapToUserField;
	}

	public void setMapToUserField(MapToUserField mapToUserField) {
		this.mapToUserField = mapToUserField;
	}
	
}
