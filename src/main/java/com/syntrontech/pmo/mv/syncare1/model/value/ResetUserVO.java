package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResetUserVO implements Serializable {
	private static final long serialVersionUID = 8090266361172657003L;

	private Integer id;
	
	private String userAccount;
	
	private String userDisplayName;



	public Integer getId() {
		return id;
	}
	
	public String getUserAccount() {
		return userAccount;
	}
	
	public String getUserDisplayName() {
		return userDisplayName;
	}

	

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
}
