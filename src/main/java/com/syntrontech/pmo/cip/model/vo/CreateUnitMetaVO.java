package com.syntrontech.pmo.cip.model.vo;

import com.syntrontech.autoTool.annotation.ParamNotNull;
import com.syntrontech.autoTool.annotation.ParamStringRestrict;

public class CreateUnitMetaVO {
	
	@ParamNotNull
	@ParamStringRestrict
	private String unitId;
	
	@ParamStringRestrict
	private String category;
	
	@ParamStringRestrict
	private String contact;
	
	@ParamStringRestrict
	private String address;
	
	@ParamStringRestrict
	private String homePhone;
	
	@ParamStringRestrict
	private String mobilePhone;
	
	@ParamStringRestrict
	private String comment;

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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

}
