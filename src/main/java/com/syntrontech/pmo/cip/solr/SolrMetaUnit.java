package com.syntrontech.pmo.cip.solr;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.cip.model.UnitMeta;
import com.syntrontech.cip.restful.to.TO;

public class SolrMetaUnit implements TO<UnitMeta>, SolrDoc {
	
	private String id;
	
	private String unitId_s;
	
	private String unitName_s;
	
	private String unitParentId_s;
	
	private String unitParentName_s;
	
	private String unitStatus_s;
	
	private String tenantId_s;
	
	private String category_s;
	
	private String contact_s;
	
	private String address_s;
	
	private String homePhone_s;
	
	private String mobilePhone_s;
	
	private String comment_s;
	
	private String createTime_dt;
	
	private String updateTime_dt;
	
	private static Map<String, String> fieldNameMap;
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUnitId_s() {
		return unitId_s;
	}

	public void setUnitId_s(String unitId_s) {
		this.unitId_s = unitId_s;
	}

	public String getUnitName_s() {
		return unitName_s;
	}

	public void setUnitName_s(String unitName_s) {
		this.unitName_s = unitName_s;
	}

	public String getUnitParentId_s() {
		return unitParentId_s;
	}

	public void setUnitParentId_s(String unitParentId_s) {
		this.unitParentId_s = unitParentId_s;
	}

	public String getUnitParentName_s() {
		return unitParentName_s;
	}

	public void setUnitParentName_s(String unitParentName_s) {
		this.unitParentName_s = unitParentName_s;
	}

	public String getUnitStatus_s() {
		return unitStatus_s;
	}

	public void setUnitStatus_s(String unitStatus_s) {
		this.unitStatus_s = unitStatus_s;
	}

	public String getTenantId_s() {
		return tenantId_s;
	}

	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}

	public String getCategory_s() {
		return category_s;
	}

	public void setCategory_s(String category_s) {
		this.category_s = category_s;
	}

	public String getContact_s() {
		return contact_s;
	}

	public void setContact_s(String contact_s) {
		this.contact_s = contact_s;
	}

	public String getAddress_s() {
		return address_s;
	}

	public void setAddress_s(String address_s) {
		this.address_s = address_s;
	}

	public String getHomePhone_s() {
		return homePhone_s;
	}

	public void setHomePhone_s(String homePhone_s) {
		this.homePhone_s = homePhone_s;
	}

	public String getMobilePhone_s() {
		return mobilePhone_s;
	}

	public void setMobilePhone_s(String mobilePhone_s) {
		this.mobilePhone_s = mobilePhone_s;
	}

	public String getComment_s() {
		return comment_s;
	}

	public void setComment_s(String comment_s) {
		this.comment_s = comment_s;
	}

	public String getCreateTime_dt() {
		return createTime_dt;
	}

	public void setCreateTime_dt(String createTime_dt) {
		this.createTime_dt = createTime_dt;
	}

	public String getUpdateTime_dt() {
		return updateTime_dt;
	}

	public void setUpdateTime_dt(String updateTime_dt) {
		this.updateTime_dt = updateTime_dt;
	}

	public TO<SolrMetaUnit> convertToTO(TO<SolrMetaUnit> to){
		return to.convertFrom(this);
	}
	
	@Override
	public TO<UnitMeta> convertFrom(UnitMeta model) {
		this.id = this.getClass().getSimpleName()+model.getSequence();
		this.unitId_s = model.getUnitId();
		this.unitName_s = model.getUnitName();
		this.unitParentId_s = model.getUnitParentId();
		this.unitParentName_s = model.getUnitParentName();
		this.unitStatus_s = model.getUnitStatus().name();
		this.tenantId_s = model.getTenantId();
		this.category_s = model.getCategory();
		this.contact_s = model.getContact();
		this.address_s = model.getAddress();
		this.homePhone_s = model.getHomePhone();
		this.mobilePhone_s = model.getMobilePhone();
		this.comment_s = model.getComment();
		this.createTime_dt = Instant.ofEpochMilli(model.getCreateTime().getTime())
									.toString();
		this.updateTime_dt = Instant.ofEpochMilli(model.getUpdateTime().getTime())
									.toString();
		return this;
	}
	
	@Override
	public Map<String, String> createFieldNameMap() {
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("unitId", "unitId_s");
			fieldNameMap.put("unitName", "unitName_s");
			fieldNameMap.put("parentUnitId", "unitParentId_s");
			fieldNameMap.put("parentUnitName", "unitParentName_s");
			fieldNameMap.put("status", "unitStatus_s");
			fieldNameMap.put("tenantId", "tenantId_s");
			fieldNameMap.put("category", "category_s");
			fieldNameMap.put("contact", "contact_s");
			fieldNameMap.put("address", "address_s");
			fieldNameMap.put("homePhone", "homePhone_s");
			fieldNameMap.put("mobilePhone", "mobilePhone_s");
			fieldNameMap.put("comment", "comment_s");
			fieldNameMap.put("createTime", "createTime_dt");
			fieldNameMap.put("updateTime", "updateTime_dt");
		}
		return fieldNameMap;
	}

}
