package com.syntrontech.pmo.cip.model.to;


import com.syntrontech.pmo.cip.model.solr.SolrMetaUnit;

public class UnitMetaTO implements TO<SolrMetaUnit> {
	
	private String unitId;
	
	private String unitName;
	
	private String parentUnitId;
	
	private String parentUnitName;
	
	private String status;
	
	private String category;
	
	private String contact;
	
	private String address;
	
	private String homePhone;
	
	private String mobilePhone;
	
	private String comment;
	
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getParentUnitId() {
		return parentUnitId;
	}

	public void setParentUnitId(String parentUnitId) {
		this.parentUnitId = parentUnitId;
	}

	public String getParentUnitName() {
		return parentUnitName;
	}

	public void setParentUnitName(String parentUnitName) {
		this.parentUnitName = parentUnitName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public TO<SolrMetaUnit> convertFrom(SolrMetaUnit model) {
		this.unitId = model.getUnitId_s();
		this.unitName = model.getUnitName_s();
		this.parentUnitId = model.getUnitParentId_s();
		this.parentUnitName = model.getUnitParentName_s();
		this.status = model.getUnitStatus_s();
		this.category = model.getCategory_s();
		this.contact = model.getContact_s();
		this.address = model.getAddress_s();
		this.homePhone = model.getHomePhone_s();
		this.mobilePhone = model.getMobilePhone_s();
		this.comment = model.getComment_s();
		return this;
	}
	
	@Override
	public String toString(){
		return "UnitMetaTO["
				+"unitId="+unitId+", "
				+"unitName="+unitName+", "
				+"parentUnitId="+parentUnitId+", "
				+"parentUnitName="+parentUnitName+", "
				+"status="+status+", "
				+"category="+category+", "
				+"contact="+contact+", "
				+"address="+address+", "
				+"homePhone="+homePhone+", "
				+"mobilePhone="+mobilePhone+", "
				+"comment="+comment
				+"]";
	}

}
