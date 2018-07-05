package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RfidCardVO implements Serializable {
	private static final long serialVersionUID = 9096581291123937359L;
	
	private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	@Override
	public String toString() {
		return "RfidCardVO [cardId=" + cardId + "]";
	}
}
