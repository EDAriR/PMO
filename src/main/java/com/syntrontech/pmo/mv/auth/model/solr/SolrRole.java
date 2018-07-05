package com.syntrontech.pmo.mv.auth.model.solr;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.auth.exception.server.InternalServiceException;
import com.syntrontech.auth.model.Role;
import com.syntrontech.auth.restful.to.TO;

public class SolrRole implements SolrDoc<Role>{
	
	private String id;
	
	private String roleId_s;
	
	private String roleName_s;
	
	private String tenantId_s;
	
	private String[] permissionIds_ss;
	
	private String roleMeta_s;
	
	private String roleStatus_s;
	
	private String roleCreateTime_dt;
	
	private String roleUpdateTime_dt;
	
	private static Map<String, String> fieldNameMap;

	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRoleId_s() {
		return roleId_s;
	}

	public void setRoleId_s(String roleId_s) {
		this.roleId_s = roleId_s;
	}

	public String getRoleName_s() {
		return roleName_s;
	}

	public void setRoleName_s(String roleName_s) {
		this.roleName_s = roleName_s;
	}

	public String getTenantId_s() {
		return tenantId_s;
	}

	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}

	public String[] getPermissionIds_ss() {
		return permissionIds_ss;
	}

	public void setPermissionIds_ss(String[] permissionIds_ss) {
		this.permissionIds_ss = permissionIds_ss;
	}

	public String getRoleMeta_s() {
		return roleMeta_s;
	}

	public void setRoleMeta_s(String roleMeta_s) {
		this.roleMeta_s = roleMeta_s;
	}

	public String getRoleStatus_s() {
		return roleStatus_s;
	}

	public void setRoleStatus_s(String roleStatus_s) {
		this.roleStatus_s = roleStatus_s;
	}
	
	public String getRoleCreateTime_dt() {
		return roleCreateTime_dt;
	}

	public void setRoleCreateTime_dt(String roleCreateTime_dt) {
		this.roleCreateTime_dt = roleCreateTime_dt;
	}

	public String getRoleUpdateTime_dt() {
		return roleUpdateTime_dt;
	}

	public void setRoleUpdateTime_dt(String roleUpdateTime_dt) {
		this.roleUpdateTime_dt = roleUpdateTime_dt;
	}

	public TO<SolrRole> convertToTO(TO<SolrRole> to){
		return to.convertFrom(this);
	}
	
	protected void createFieldNameMapIfNotExist(){
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("roleId", "roleId_s");
			fieldNameMap.put("roleName", "roleName_s");
			fieldNameMap.put("tenantId", "tenantId_s");
			fieldNameMap.put("permissionIds", "permissionIds_ss");
			fieldNameMap.put("status", "roleStatus_s");
			fieldNameMap.put("createTime", "roleCreateTime_dt");
			fieldNameMap.put("updateTime", "roleUpdateTime_dt");
		}
	}
	
	@Override
	public TO<Role> convertFrom(Role model) {
		this.id = this.getClass().getSimpleName()+model.getSequence();
		this.roleId_s = model.getId();
		this.roleName_s = model.getName();
		this.tenantId_s = model.getTenantId();
		this.permissionIds_ss = model.getPermissionIds();
		this.roleMeta_s = model.getMeta();
		this.roleStatus_s = model.getStatus().name();
		this.roleCreateTime_dt = Instant.ofEpochMilli(model.getCreateTime().getTime()).toString();
		this.roleUpdateTime_dt = Instant.ofEpochMilli(model.getUpdateTime().getTime()).toString();
		return this;
	}

	@Override
	public String getFieldNameTransformTOFieldName(String fieldName) throws InternalServiceException {
		createFieldNameMapIfNotExist();
		if(!fieldNameMap.containsKey(fieldName)){
			throw new InternalServiceException("can't find mapping solr doc field name from ["+fieldName+"]");
		}
		return fieldNameMap.get(fieldName);
	}

	@Override
	public Set<String> findTOFieldNames() {
		createFieldNameMapIfNotExist();
		return fieldNameMap.keySet();
	}

}
