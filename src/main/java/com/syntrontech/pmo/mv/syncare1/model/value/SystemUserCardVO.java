package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.entity.SystemUserCard;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SystemUserCardVO implements ObjectConverter<SystemUserCard>, Serializable {
	private static final long serialVersionUID = -326401679978531245L;

	private String cardId;
	
	private String name;

	public String getCardId() {
		return cardId;
	}

	public String getName() {
		return name;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public SystemUserCard convert(boolean relation) {
		SystemUserCard card = new SystemUserCard();
		card.setCardId(getCardId());
		card.setCardName(getName());
		return card;
	}
}
