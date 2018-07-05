package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginVO implements Serializable {
	private static final long serialVersionUID = 4933383559855488769L;

	private String account;
	
	private String credentials;

	public String getAccount() {
		return account;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		return "LoginVO [account=" + account + ", credentials=" + credentials
				+ "]";
	}
}
