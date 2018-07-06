package com.syntrontech.pmo.cip.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.cip.model.common.ModelMgmtStatus;
import com.syntrontech.cip.restful.to.TO;

@Table
@Entity(name = "unit_meta")
public class UnitMeta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence")
	private Long sequence;
	
	@Column(name = "unit_id", nullable = false)
	private String unitId;
	
	@Column(name = "unit_name", nullable = false)
	private String unitName;
	
	@Column(name = "unit_parent_id")
	private String unitParentId;
	
	@Column(name = "unit_parent_name")
	private String unitParentName;
	
	@Column(name = "unit_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ModelMgmtStatus unitStatus;
	
	@Column(name = "tenant_id", nullable = false)
	private String tenantId;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "home_phone")
	private String homePhone;
	
	@Column(name = "mobile_phone")
	private String mobilePhone;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "createtime", nullable = false)
	private Date createTime;
	
	@Column(name = "createby", nullable = false)
	private String createBy;
	
	@Column(name = "updatetime", nullable = false)
	private Date updateTime;
	
	@Column(name = "updateby", nullable = false)
	private String updateBy;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

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

	public String getUnitParentId() {
		return unitParentId;
	}

	public void setUnitParentId(String unitParentId) {
		this.unitParentId = unitParentId;
	}

	public String getUnitParentName() {
		return unitParentName;
	}

	public void setUnitParentName(String unitParentName) {
		this.unitParentName = unitParentName;
	}

	public ModelMgmtStatus getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(ModelMgmtStatus unitStatus) {
		this.unitStatus = unitStatus;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public TO<UnitMeta> convertToTO(TO<UnitMeta> to){
		return to.convertFrom(this);
	}
	
	@Override
	public String toString(){
		return "UnitMeta {sequence="+sequence+", unitId="+unitId+", unitName="+unitName+", unitParentId="+unitParentId
				+", unitParentName="+unitParentName+", unitStatus="+unitStatus+", tenantId="+tenantId
				+", category="+category+", contact="+contact+", address="+address+", homePhone="+homePhone
				+", mobilePhone="+mobilePhone+", comment="+comment+", createTime="+createTime
				+", createBy="+createBy+", updateTime="+updateTime+", updateBy="+updateBy+"}";
	}
}
