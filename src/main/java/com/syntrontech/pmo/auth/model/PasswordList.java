package com.syntrontech.pmo.auth.model;

import java.util.Date;


//store in Postgres
public class PasswordList {
	
	private Long sequence;
	
	private String password;
	
	private Date passwordUpdateTime;
	
	private String account;
	
	private String userId;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getPasswordUpdateTime() {
		return passwordUpdateTime;
	}

	public void setPasswordUpdateTime(Date passwordUpdateTime) {
		this.passwordUpdateTime = passwordUpdateTime;
	}
	
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

	@Override
	public String toString() {

		return "PasswordList={password:[" + password +
				"], passwordUpdateTime:[" + passwordUpdateTime +
				"], account:[" + account +
				"], userId:[" + userId + "]}";

	}
}
