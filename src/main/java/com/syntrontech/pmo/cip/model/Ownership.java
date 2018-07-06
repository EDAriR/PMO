package com.syntrontech.pmo.cip.model;


import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.model.common.OwnershipStatus;
import com.syntrontech.pmo.model.common.OwnershipType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Table
@Entity(name = "ownership")
public class Ownership {
	
	@Id
	@SequenceGenerator(name = "OWNERSHIP_SEQ", allocationSize = 1, sequenceName = "ownership_sequence_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OWNERSHIP_SEQ")
	private Long sequence;

	@Column(name = "ownership_id", nullable = false)
	private String ownershipId;
	
	@Column(name = "my_id", nullable = false)
	private String myId;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "type", nullable = false)
	private OwnershipType type;
	
	@Column(name = "ownership_status")
	private OwnershipStatus ownershipStatus;
	
	@Column(name = "model_status")
	private ModelStatus modelStatus;
	
	@Column(name = "tenant_id")
	private String tenantId;

	public String getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(String ownershipId) {
		this.ownershipId = ownershipId;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
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

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public OwnershipStatus getOwnershipStatus() {
		return ownershipStatus;
	}

	public void setOwnershipStatus(OwnershipStatus ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}

	public ModelStatus getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(ModelStatus modelStatus) {
		this.modelStatus = modelStatus;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

//	public SolrOwnership convertToTO(SolrOwnership solrOwnership) {
//
//		solrOwnership.setId(solrOwnership.getClass().getSimpleName()+this.getOwnershipId());
//		solrOwnership.setOwnershipId_s(ownershipId);
//		solrOwnership.setUserId_s(userId);
//		solrOwnership.setUserName_s(userName);
//		solrOwnership.setType_s(type);
//		solrOwnership.setOwnershipStatus_s(ownershipStatus);
//		solrOwnership.setModelStatus_s(modelStatus);
//		solrOwnership.setTenantId_s(tenantId);
//		solrOwnership.setMyId_s(myId);
//
//		return solrOwnership;
//	}
//
//	public OwnershipTO convertToTO(Ownership ownership) {
//
//		OwnershipTO ownershipTO = new OwnershipTO();
//		ownershipTO.setOwnershipId(ownershipId);
//		ownershipTO.setUserId(userId);
//		ownershipTO.setUserName(userName);
//		ownershipTO.setType(type);
//		ownershipTO.setStatus(ownershipStatus);
//
//		return ownershipTO;
//	}
	
}
