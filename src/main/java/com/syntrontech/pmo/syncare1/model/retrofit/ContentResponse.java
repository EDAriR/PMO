package com.syntrontech.pmo.syncare1.model.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keanu on 2015/3/2.
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentResponse {
	private String loginToken = "";

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	private List<Repository> repositories = new ArrayList<Repository>();

	public List<Repository> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<Repository> repositories) {
		this.repositories = repositories;
	}
}
