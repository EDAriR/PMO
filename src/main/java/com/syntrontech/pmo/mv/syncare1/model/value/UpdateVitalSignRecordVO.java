package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateVitalSignRecordVO implements Serializable {
	private static final long serialVersionUID = 1237851555786286434L;

	private Integer userId;
	
	private Integer bodyValueRecordId;
	
	private Map<String, String> modifyDataMap;



	public Integer getUserId() {
		return userId;
	}

	public Integer getBodyValueRecordId() {
		return bodyValueRecordId;
	}

	public Map<String, String> getModifyDataMap() {
		return modifyDataMap;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setBodyValueRecordId(Integer bodyValueRecordId) {
		this.bodyValueRecordId = bodyValueRecordId;
	}

	public void setModifyDataMap(Map<String, String> modifyDataMap) {
		this.modifyDataMap = modifyDataMap;
	}
	

	
	@Override
	public String toString() {
		return "VitalSignRecordVO ["
				+ "userId=" + userId 
				+ ", bodyValueRecordId=" + bodyValueRecordId 
				+ ", modifyDataMap=" + modifyDataMap 
				+ "]";
	}
}
