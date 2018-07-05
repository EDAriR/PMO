package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CredentialsVO implements Serializable {
	private static final long serialVersionUID = 8090266361172657003L;

	private String oldCredentials;
	
	private String newCredentials;
	
	private String confirmCredentials;
	
	// 台東新案加入, 公版不需要
	private String token;
	
	private int memberId;
	
	

	public String getOldCredentials() {
		return oldCredentials;
	}

	public String getNewCredentials() {
		return newCredentials;
	}

	public String getConfirmCredentials() {
		return confirmCredentials;
	}
	
	public String getToken() {
		return token;
	}
	
	public int getMemberId() {
		return memberId;
	}
	


	public void setOldCredentials(String oldCredentials) {
		this.oldCredentials = oldCredentials;
	}

	public void setNewCredentials(String newCredentials) {
		this.newCredentials = newCredentials;
	}

	public void setConfirmCredentials(String confirmCredentials) {
		this.confirmCredentials = confirmCredentials;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
