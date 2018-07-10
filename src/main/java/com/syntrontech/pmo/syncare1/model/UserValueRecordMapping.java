package com.syntrontech.pmo.syncare1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the user_value_record_mapping database table.
 * 
 */
@Entity
@Table(name="user_value_record_mapping")
@NamedQuery(name="UserValueRecordMapping.findAll", query="SELECT u FROM UserValueRecordMapping u")
public class UserValueRecordMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_VALUE_RECORD_MAPPING_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userValueRecordMappingId;

	@Column(name="RECORD_VALUE")
	private String recordValue;

	//bi-directional many-to-one association to Mapping
	@ManyToOne
	@JoinColumn(name="TYPE_ID")
	private Mapping mapping;

	//bi-directional many-to-one association to UserValueRecord
	@ManyToOne
	@JoinColumn(name="USER_VALUE_RECORD_ID")
	private UserValueRecord userValueRecord;
	
	

	public UserValueRecordMapping() {
	}

	public int getUserValueRecordMappingId() {
		return this.userValueRecordMappingId;
	}

	public void setUserValueRecordMappingId(int userValueRecordMappingId) {
		this.userValueRecordMappingId = userValueRecordMappingId;
	}

	public String getRecordValue() {
		return this.recordValue;
	}

	public void setRecordValue(String recordValue) {
		this.recordValue = recordValue;
	}

	public Mapping getMapping() {
		return this.mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	public UserValueRecord getUserValueRecord() {
		return this.userValueRecord;
	}

	public void setUserValueRecord(UserValueRecord userValueRecord) {
		this.userValueRecord = userValueRecord;
	}
	
	
	
	@Override
	public String toString() {
		return "UserValueRecordMapping ["
				+ "userValueRecordMappingId=" + userValueRecordMappingId 
				+ ", recordValue=" + recordValue
				+ ", mapping=" + mapping 
				+ ", userValueRecord=" + userValueRecord 
				+ "]";
	}

}