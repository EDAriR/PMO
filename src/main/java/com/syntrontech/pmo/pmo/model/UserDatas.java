package com.syntrontech.pmo.pmo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public class UserDatas {
	@JsonProperty("UserList")
	private List<UserData> users = new ArrayList<>();

	public UserDatas() {
	}
	
	@JsonCreator
	public UserDatas(
			@JsonProperty("UserList") List<UserData> users) {
		this.users = users;
	}
	
	public List<UserData> getUsers() {
		return users;
	}

	public void setUsers(List<UserData> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserDatas [users=" + users + "]";
	}
}
