package com.syntrontech.pmo.auth.model.solr;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import com.syntrontech.pmo.cip.solr.SolrDoc;

public class SolrTenant implements SolrDoc {
	
	private String id;
	
	private String tenantId_s;
	
	private String tenantName_s;
	
	private Long tenantLimit_l;
	
	private String tenantParentId_s;
	
	private String[] permissionIds_ss;
	
	private String tenantMeta_s;
	
	private String tenantStatus_s;
	
	private String tenantCreateTime_dt;
	
	private String tenantUpdateTime_dt;
	
	private static Map<String, String> fieldNameMap;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Map<String, String> createFieldNameMap() {
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTenantId_s() {
		return tenantId_s;
	}
	
	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}
	
	public String getTenantName_s() {
		return tenantName_s;
	}
	
	public void setTenantName_s(String tenantName_s) {
		this.tenantName_s = tenantName_s;
	}
	
	public Long getTenantLimit_l() {
		return tenantLimit_l;
	}
	
	public void setTenantLimit_l(Long tenantLimit_l) {
		this.tenantLimit_l = tenantLimit_l;
	}
	
	public String getTenantParentId_s() {
		return tenantParentId_s;
	}
	
	public void setTenantParentId_s(String tenantParentId_s) {
		this.tenantParentId_s = tenantParentId_s;
	}

	public String[] getPermissionIds_ss() {
		return permissionIds_ss;
	}

	public void setPermissionIds_ss(String[] permissionIds_ss) {
		this.permissionIds_ss = permissionIds_ss;
	}

	public String getTenantMeta_s() {
		return tenantMeta_s;
	}
	
	public void setTenantMeta_s(String tenantMeta_s) {
		this.tenantMeta_s = tenantMeta_s;
	}
	
	public String getTenantStatus_s() {
		return tenantStatus_s;
	}
	
	public void setTenantStatus_s(String tenantStatus_s) {
		this.tenantStatus_s = tenantStatus_s;
	}
	
	public String getTenantCreateTime_dt() {
		return tenantCreateTime_dt;
	}

	public void setTenantCreateTime_dt(String tenantCreateTime_dt) {
		this.tenantCreateTime_dt = tenantCreateTime_dt;
	}

	public String getTenantUpdateTime_dt() {
		return tenantUpdateTime_dt;
	}

	public void setTenantUpdateTime_dt(String tenantUpdateTime_dt) {
		this.tenantUpdateTime_dt = tenantUpdateTime_dt;
	}


	protected void createFieldNameMapIfNotExist(){
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("tenantId", "tenantId_s");
			fieldNameMap.put("tenantName", "tenantName_s");
			fieldNameMap.put("limit", "tenantLimit_l");
			fieldNameMap.put("parentTenantId", "tenantParentId_s");
			fieldNameMap.put("permissionIds", "permissionIds_ss");
			fieldNameMap.put("status", "tenantStatus_s");
			fieldNameMap.put("createTime", "tenantCreateTime_dt");
			fieldNameMap.put("updateTime", "tenantUpdateTime_dt");
		}
	}
}
