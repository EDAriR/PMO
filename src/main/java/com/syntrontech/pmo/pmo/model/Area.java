package com.syntrontech.pmo.pmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Area {
	@JsonProperty("Code")
	private String code;
	
	@JsonProperty("Name")
	private String name;

	public Area() {
	}

	public Area(
			@JsonProperty("Code") String code,
			@JsonProperty("Name") String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Area [code=" + code + ", name=" + name + "]";
	}
}
