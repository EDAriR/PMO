package com.syntrontech.pmo.auth.model;

import com.syntrontech.pmo.model.TO.TO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//store in Postgres
@Entity
@Table(name = "config")
public class Config {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence", nullable = false)
	private Long sequence;
	
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "value", nullable = false)
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
