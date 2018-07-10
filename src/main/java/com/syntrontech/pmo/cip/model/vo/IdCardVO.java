package com.syntrontech.pmo.cip.model.vo;

import com.syntrontech.autoTool.annotation.ParamEnumRequired;
import com.syntrontech.autoTool.annotation.ParamNotNull;
import com.syntrontech.autoTool.annotation.ParamStringRestrict;
import com.syntrontech.pmo.cip.model.common.UserAgent;

public class IdCardVO {
	
	@ParamNotNull
	@ParamStringRestrict
	private String cardId;
	
	@ParamNotNull
	@ParamEnumRequired(UserAgent.class)
	private String userAgent;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
