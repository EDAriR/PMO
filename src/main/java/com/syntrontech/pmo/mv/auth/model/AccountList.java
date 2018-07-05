package com.syntrontech.pmo.mv.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.auth.model.common.MapToUserField;

//store in Postgres
@Entity
@Table(name = "account_list")
public class AccountList {
	
	@Id
	@Column(name = "account", nullable = false)
	private String account;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "map_to_users_field")
	@Enumerated(EnumType.STRING)
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
