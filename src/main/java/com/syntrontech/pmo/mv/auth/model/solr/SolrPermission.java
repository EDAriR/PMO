package com.syntrontech.pmo.mv.auth.model.solr;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.auth.exception.server.InternalServiceException;
import com.syntrontech.auth.model.Permission;
import com.syntrontech.auth.restful.to.TO;

public class SolrPermission implements SolrDoc<Permission>{
	
	private String id;
	
	private String permissionId_s;
	
	private static Map<String, String> fieldNameMap;
	
	@Override
	public String getId() {
		return id;
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
	
	@Override
	public TO<Permission> convertFrom(Permission model) {
		this.id = this.getClass().getSimpleName()+model.getSequence();
		this.permissionId_s = model.getId();
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
