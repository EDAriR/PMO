package com.syntrontech.pmo.cip.model.solr;

import com.syntrontech.pmo.cip.model.Ownership;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.model.common.OwnershipStatus;
import com.syntrontech.pmo.model.common.OwnershipType;
import com.syntrontech.pmo.solr.SolrDoc;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class SolrOwnership implements TO<Ownership>,SolrDoc {

	private String id;
	
	private String ownershipId_s;
	
	private String myId_s;
	
	private String userId_s;
	
	private String userName_s;

	private OwnershipType type_s;
	
	private OwnershipStatus ownershipStatus_s;
	
	private ModelStatus modelStatus_s;
	
	private String tenantId_s;
	
	private static Map<String, String> fieldNameMap;

	public String getOwnershipId_s() {
		return ownershipId_s;
	}

	public void setOwnershipId_s(String ownershipId_s) {
		this.ownershipId_s = ownershipId_s;
	}
	
	public String getMyId_s() {
		return myId_s;
	}

	public void setMyId_s(String myId_s) {
		this.myId_s = myId_s;
	}

	public String getUserId_s() {
		return userId_s;
	}

	public void setUserId_s(String userId_s) {
		this.userId_s = userId_s;
	}

	public String getUserName_s() {
		return userName_s;
	}

	public void setUserName_s(String userName_s) {
		this.userName_s = userName_s;
	}

	public OwnershipType getType_s() {
		return type_s;
	}

	public void setType_s(OwnershipType type_s) {
		this.type_s = type_s;
	}

	
	public OwnershipStatus getOwnershipStatus_s() {
		return ownershipStatus_s;
	}
		
	public void setOwnershipStatus_s(OwnershipStatus ownershipStatus_s) {
		this.ownershipStatus_s = ownershipStatus_s;
	}
		
	public void setModelStatus_s(ModelStatus modelStatus_s) {
		this.modelStatus_s = modelStatus_s;
	}
	
	public ModelStatus getModelStatus_s() {
		return modelStatus_s;
	}

	public String getTenantId_s() {
		return tenantId_s;
	}
	
	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	@Override
	public TO<Ownership> convertFrom(Ownership model) {

		SolrOwnership s = new SolrOwnership();
		this.id = s.getClass().getSimpleName()+model.getOwnershipId();
		this.ownershipId_s = model.getOwnershipId();
		this.myId_s = model.getMyId();
		this.userId_s = model.getUserId();
		this.userName_s = model.getUserName();
		this.type_s = model.getType();
		this.ownershipStatus_s = model.getOwnershipStatus();
		this.modelStatus_s = model.getModelStatus();
		this.tenantId_s = model.getTenantId();

		return this;
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("ownershipId", "ownershipId_s");
			fieldNameMap.put("myId", "myId_s");
			fieldNameMap.put("userId", "userId_s");
			fieldNameMap.put("userName", "userName_s");
			fieldNameMap.put("type", "type_s");
			fieldNameMap.put("status", "ownershipStatus_s");
			fieldNameMap.put("updateTime", "ownershipId_s");
			fieldNameMap.put("tenantId", "tenantId_s");
			
		}
		return fieldNameMap;
	}
	
	public TO<SolrOwnership> convertToTO(TO<SolrOwnership> to){
		return to.convertFrom(this);
	}

}
