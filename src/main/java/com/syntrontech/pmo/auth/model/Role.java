package com.syntrontech.pmo.auth.model;

import com.syntrontech.pmo.model.common.ModelStatus;

import java.util.Arrays;
import java.util.Date;



//store in Postgres
public class Role {
	
	private Long sequence;
	
	private String id;
	
	private String name;
	
	private String tenantId;
	
	private String meta;
	
	private String[] permissionIds;
	
	private Date createTime;
	
	private String createBy;
	
	private Date updateTime;
	
	private String updateBy;
	
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

	public ModelStatus getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString(){
		return "Role {sequence="+sequence+", id="+id+", name="+name+", tenantId="+tenantId
				+", meta="+meta+", permissionIds="+Arrays.asList(permissionIds)+", createTime="+createTime
				+", createBy="+createBy+", updateTime="+updateTime+", updateBy="+updateBy
				+", status="+status+"}";
	}
}
