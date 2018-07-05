package com.syntrontech.pmo.mv.syncare1.model.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {

	private String authenticationIdentifier;
	private String authenticationToken;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthenticationIdentifier() {
		return authenticationIdentifier;
	}

	public void setAuthenticationIdentifier(String authenticationIdentifier) {
		this.authenticationIdentifier = authenticationIdentifier;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
}
