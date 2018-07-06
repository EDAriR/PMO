package com.syntrontech.pmo.auth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.syntrontech.auth.model.common.ModelUserStatus;
import com.syntrontech.auth.model.common.Source;
import com.syntrontech.auth.restful.to.TO;

//store in Postgres
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence", nullable = false)
	private Long sequence;

	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "tenant_id", nullable = false)
	private String tenantId;
	
	@Column(name = "source", nullable = false)
	@Enumerated(EnumType.STRING)
	private Source source;
	
	@Column(name = "meta")
	private String meta;
	
	@Column(name = "unit_ids")
	@Type(type = "com.syntrontech.auth.model.common.StringArrayType")
	private String[] unitIds;
	
	@Column(name = "role_ids")
	@Type(type = "com.syntrontech.auth.model.common.StringArrayType")
	private String[] roleIds;
	
	@Column(name = "emails")
	@Type(type = "com.syntrontech.auth.model.common.StringArrayType")
	private String[] emails;
	
	@Column(name = "mobilephones")
	@Type(type = "com.syntrontech.auth.model.common.StringArrayType")
	private String[] mobilePhones;
	
	@Column(name = "cards")
	@Type(type = "com.syntrontech.auth.model.common.StringArrayType")
	private String[] cards;
	
	@Column(name = "permission_ids")
	@Type(type = "com.syntrontech.auth.model.common.StringArrayType")
	private String[] permissionIds;
	
	@Column(name = "createtime", nullable = false)
	private Date createTime;
	
	@Column(name = "createby", nullable = false)
	private String createBy;
	
	@Column(name = "updatetime", nullable = false)
	private Date updateTime;
	
	@Column(name = "updateby", nullable = false)
	private String updateBy;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ModelUserStatus status;
	
	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String[] getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(String[] unitIds) {
		this.unitIds = unitIds;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	public String[] getMobilePhones() {
		return mobilePhones;
	}

	public void setMobilePhones(String[] mobilePhones) {
		this.mobilePhones = mobilePhones;
	}

	public String[] getCards() {
		return cards;
	}

	public void setCards(String[] cards) {
		this.cards = cards;
	}

	public String[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
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

	public ModelUserStatus getStatus() {
		return status;
	}

	public void setStatus(ModelUserStatus status) {
		this.status = status;
	}

	public TO<User> convertToTO(TO<User> to){
		return to.convertFrom(this);
	}
}
