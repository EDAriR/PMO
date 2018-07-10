package com.syntrontech.pmo.cip.model.vo;

import java.util.Set;

public class AaaCardGuestVO {
	
	private String userId;
	
	private String userName;
	
	private String password;
	
	private String confirmPassword;
	
	private String email;
	
	private String mobilePhone;
	
	private Set<String> cardIds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Set<String> getCardIds() {
		return cardIds;
	}

	public void setCardIds(Set<String> cardIds) {
		this.cardIds = cardIds;
	}
	
}
