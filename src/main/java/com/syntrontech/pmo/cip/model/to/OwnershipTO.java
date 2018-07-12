package com.syntrontech.pmo.cip.model.to;

import com.syntrontech.pmo.cip.model.solr.SolrOwnership;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.model.common.OwnershipStatus;
import com.syntrontech.pmo.model.common.OwnershipType;

public class OwnershipTO implements TO<SolrOwnership> {

	private String ownershipId;
	
	private String userId;
	
	private String userName;

	private OwnershipType type;
	
	private OwnershipStatus status;

	public String getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(String ownershipId) {
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

	@Override
	public TO<SolrOwnership> convertFrom(SolrOwnership model) {
		
		this.ownershipId = model.getOwnershipId_s();
		this.userId = model.getUserId_s();
		this.userName = model.getUserName_s();
		this.type = model.getType_s();
		this.status = model.getOwnershipStatus_s();
		
		return this;
	}
	
}
