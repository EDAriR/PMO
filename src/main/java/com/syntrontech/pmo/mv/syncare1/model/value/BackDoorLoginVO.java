package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BackDoorLoginVO implements Serializable {
	private static final long serialVersionUID = 9096581291123937359L;
	
	private int userId;

	private String account;
	
	private String password;

	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	@Override
	public String toString() {
		return "BackDoorLoginVO ["
				+ "userId=" + userId 
				+ "account=" + account 
				+ "password=" + password 
				+ "]";
	}
}
