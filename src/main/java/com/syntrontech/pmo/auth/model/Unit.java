package com.syntrontech.pmo.auth.model;

import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.model.common.ModelStatus;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//store in Postgres
@Entity
@Table(name = "unit")
public class Unit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence", nullable = false)
	private Long sequence;
	
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "parent_id")
	private String parentId;
	
	@Column(name = "parent_name")
	private String parentName;
	
	@Column(name = "tenant_id", nullable = false)
	private String tenantId;
	
	@Column(name = "meta")
	private String meta;
	
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
	private ModelStatus status;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
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

	public ModelStatus getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = status;
	}
	
	public TO<Unit> convertToTO(TO<Unit> to){
		return to.convertFrom(this);
	}
}
