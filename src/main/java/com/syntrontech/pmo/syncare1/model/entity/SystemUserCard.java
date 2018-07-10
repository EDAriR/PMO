package com.syntrontech.pmo.syncare1.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="system_user_card")
public class SystemUserCard{
	private static final long serialVersionUID = -3598572192581951657L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="card_id")
	private String cardId;
	
	@Column(name="card_name")
	private String cardName;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private SystemUser systemUser;

	public int getId() {
		return id;
	}

	public String getCardId() {
		return cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	@Override
	public String toString() {
		return "SystemUserCard [id=" + id + ", cardId=" + cardId
				+ ", cardName=" + cardName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemUserCard other = (SystemUserCard) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		return true;
	}
	
}
