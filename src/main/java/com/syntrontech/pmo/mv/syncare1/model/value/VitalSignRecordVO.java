package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VitalSignRecordVO implements Serializable {
	private static final long serialVersionUID = 1237851555786286434L;

	private Integer userId;
	
	private String type;
	
	private String serialNo;
	
	private Date date;
	
	private RecordVO record;

	// 台東新案加入, 公版不需要
	private String token;
	
	private int memberId;

	
	
	public Integer getUserId() {
		return userId;
	}

	public String getType() {
		return type;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public Date getDate() {
		return date;
	}
	
	public RecordVO getRecord() {
		return record;
	}

	public String getToken() {
		return token;
	}
	
	public int getMemberId() {
		return memberId;
	}

	

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setRecord(RecordVO record) {
		this.record = record;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	
	
	@Override
	public String toString() {
		return "VitalSignRecordVO ["
				+ "userId=" + userId 
				+ ", type=" + type
				+ ", serialNo=" + serialNo 
				+ ", date=" + date 
				+ ", token=" + token 
				+ ", memberId=" + memberId 
				+ ", record=" + record 
				+ "]";
	}
}
