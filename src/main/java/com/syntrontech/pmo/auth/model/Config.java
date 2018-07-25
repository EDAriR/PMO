package com.syntrontech.pmo.auth.model;

import com.syntrontech.pmo.model.TO.TO;

import javax.persistence.GenerationType;


//store in Postgres
public class Config {
	
	private Long sequence;
	
	private String id;
	
	private String value;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public TO<Config> convertToTO(TO<Config> to){
		return to.convertFrom(this);
	}
}
