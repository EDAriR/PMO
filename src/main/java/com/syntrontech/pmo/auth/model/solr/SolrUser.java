package com.syntrontech.pmo.auth.model.solr;

import com.syntrontech.pmo.cip.solr.SolrDoc;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class SolrUser implements SolrDoc {
	
	private String id;
	
	private String userId_s;
	
	private String userName_s;
	
	private String tenantId_s;
	
	private String[] emails_ss;
	
	private String[] mobilePhones_ss;
	
	private String[] unitIds_ss;
	
	private String[] roleIds_ss;
	
	private String[] cards_ss;
	
	private String[] permissionIds_ss;
	
	private String userMeta_s;
	
	private String userStatus_s;
	
	private String userCreateTime_dt;
	
	private String userUpdateTime_dt;
	
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

	public String getTenantId_s() {
		return tenantId_s;
	}

	public void setTenantId_s(String tenantId_s) {
		this.tenantId_s = tenantId_s;
	}
	
	public String[] getEmails_ss() {
		return emails_ss;
	}

	public void setEmails_ss(String[] emails_ss) {
		this.emails_ss = emails_ss;
	}

	public String[] getMobilePhones_ss() {
		return mobilePhones_ss;
	}

	public void setMobilePhones_ss(String[] mobilePhones_ss) {
		this.mobilePhones_ss = mobilePhones_ss;
	}

	public String[] getCards_ss() {
		return cards_ss;
	}

	public void setCards_ss(String[] cards_ss) {
		this.cards_ss = cards_ss;
	}

	public String[] getUnitIds_ss() {
		return unitIds_ss;
	}

	public void setUnitIds_ss(String[] unitIds_ss) {
		this.unitIds_ss = unitIds_ss;
	}

	public String[] getRoleIds_ss() {
		return roleIds_ss;
	}

	public void setRoleIds_ss(String[] roleIds_ss) {
		this.roleIds_ss = roleIds_ss;
	}

	public String[] getPermissionIds_ss() {
		return permissionIds_ss;
	}

	public void setPermissionIds_ss(String[] permissionIds_ss) {
		this.permissionIds_ss = permissionIds_ss;
	}

	public String getUserMeta_s() {
		return userMeta_s;
	}

	public void setUserMeta_s(String userMeta_s) {
		this.userMeta_s = userMeta_s;
	}

	public String getUserStatus_s() {
		return userStatus_s;
	}

	public void setUserStatus_s(String userStatus_s) {
		this.userStatus_s = userStatus_s;
	}

	public String getUserCreateTime_dt() {
		return userCreateTime_dt;
	}

	public void setUserCreateTime_dt(String userCreateTime_dt) {
		this.userCreateTime_dt = userCreateTime_dt;
	}

	public String getUserUpdateTime_dt() {
		return userUpdateTime_dt;
	}

	public void setUserUpdateTime_dt(String userUpdateTime_dt) {
		this.userUpdateTime_dt = userUpdateTime_dt;
	}

	protected void createFieldNameMapIfNotExist(){
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("userId", "userId_s");
			fieldNameMap.put("userName", "userName_s");
			fieldNameMap.put("tenantId", "tenantId_s");
			fieldNameMap.put("email", "emails_ss");
			fieldNameMap.put("mobilePhone", "mobilePhones_ss");
			fieldNameMap.put("unitIds", "unitIds_ss");
			fieldNameMap.put("roleIds", "roleIds_ss");
			fieldNameMap.put("cardIds", "cards_ss");
			fieldNameMap.put("permissionIds", "permissionIds_ss");
			fieldNameMap.put("status", "userStatus_s");
			fieldNameMap.put("createTime", "userCreateTime_dt");
			fieldNameMap.put("updateTime", "userUpdateTime_dt");
		}
	}

}
