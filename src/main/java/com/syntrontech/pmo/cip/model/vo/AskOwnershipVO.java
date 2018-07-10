package com.syntrontech.pmo.cip.model.vo;

import javax.ws.rs.DefaultValue;

import com.syntrontech.autoTool.annotation.ParamEnumRequired;
import com.syntrontech.autoTool.annotation.ParamNotNull;
import com.syntrontech.autoTool.annotation.ParamStringRestrict;
import com.syntrontech.pmo.model.common.OwnershipType;

public class AskOwnershipVO {
	
	@ParamNotNull
	@ParamStringRestrict
	private String userId;
	
	@ParamEnumRequired(OwnershipType.class)
	@DefaultValue("VITALS")
	private String type;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
