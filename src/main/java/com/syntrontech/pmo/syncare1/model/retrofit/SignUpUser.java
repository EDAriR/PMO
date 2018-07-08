package com.syntrontech.pmo.syncare1.model.retrofit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpUser {

	private String loginId;
	@JsonProperty("username")
	private String userName;
	private String email;
	private String password;

	private long quota;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getQuota() {
		return quota;
	}

	public void setQuota(long quota) {
		this.quota = quota;
	}
}
