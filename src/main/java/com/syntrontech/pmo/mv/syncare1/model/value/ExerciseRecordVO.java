package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syntrontech.syncare.model.entity.ExerciseRecord;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ExerciseRecordVO {
	
	private String columnType;
	
	private String createUser;
	
	private Date createDate;
	
	private List<ExerciseRecord> records;

	public List<ExerciseRecord> getRecords() {
		return records;
	}

	public void setRecords(List<ExerciseRecord> records) {
		this.records = records;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ExerciseRecordVO [columnType=" + columnType + ", createUser=" + createUser + ", createDate="
				+ createDate + ", records=" + records + "]";
	}
}