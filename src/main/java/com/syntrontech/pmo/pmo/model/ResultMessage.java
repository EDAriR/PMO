package com.syntrontech.pmo.pmo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class ResultMessage {
	@JsonProperty("MessageCode")
	private String code;
	
	@JsonProperty("MessageDescription")
	private String message;

	
	public ResultMessage(
			@JsonProperty("MessageCode") String code,
			@JsonProperty("MessageDescription") String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultMessage [code=" + code + ", message=" + message + "]";
	}

}
