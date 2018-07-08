package com.syntrontech.pmo.auth.model.solr;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.pmo.solr.SolrDoc;

public class SolrPermission implements SolrDoc {
	
	private String id;
	
	private String permissionId_s;
	
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

	public String getPermissionId_s() {
		return permissionId_s;
	}

	public void setPermissionId_s(String permissionId_s) {
		this.permissionId_s = permissionId_s;
	}

	protected void createFieldNameMapIfNotExist(){
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("permissionId", "permissionId_s");
		}
	}

}
