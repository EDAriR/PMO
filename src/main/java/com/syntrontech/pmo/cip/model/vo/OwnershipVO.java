package com.syntrontech.pmo.cip.model.vo;


import com.syntrontech.autoTool.annotation.ParamEnumRequired;
import com.syntrontech.autoTool.annotation.ParamNotNull;
import com.syntrontech.autoTool.annotation.ParamStringRestrict;
import com.syntrontech.pmo.model.common.OwnershipStatus;
import com.syntrontech.pmo.model.common.OwnershipType;

public class OwnershipVO {
	
	private Long ownershipId;
	
	@ParamNotNull
	@ParamStringRestrict
	private String userId;
	
	@ParamNotNull
	@ParamStringRestrict
	private String userName;

	@ParamNotNull
	@ParamEnumRequired(OwnershipType.class)
	private OwnershipType type;
	
	@ParamNotNull
	@ParamEnumRequired(OwnershipStatus.class)
	private OwnershipStatus status;

	public Long getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(Long ownershipId) {
		this.ownershipId = ownershipId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public OwnershipType getType() {
		return type;
	}

	public void setType(OwnershipType type) {
		this.type = type;
	}

	public OwnershipStatus getStatus() {
		return status;
	}

	public void setStatus(OwnershipStatus status) {
		this.status = status;
	}

}
