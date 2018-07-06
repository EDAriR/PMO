package com.syntrontech.pmo.auth.model.solr;

import com.syntrontech.pmo.cip.solr.SolrDoc;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.solr.InternalServiceException;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class SolrConfig implements SolrDoc {
	
	private String id;
	
	private String configId_s;
	
	private String configValue_s;
	
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

	public String getConfigId_s() {
		return configId_s;
	}

	public void setConfigId_s(String configId_s) {
		this.configId_s = configId_s;
	}

	public String getConfigValue_s() {
		return configValue_s;
	}

	public void setConfigValue_s(String configValue_s) {
		this.configValue_s = configValue_s;
	}
	
	public TO<SolrConfig> convertToTO(TO<SolrConfig> to){
		return to.convertFrom(this);
	}

	protected void createFieldNameMapIfNotExist(){
		if(Objects.isNull(fieldNameMap)){
			fieldNameMap = new ConcurrentHashMap<>();
			fieldNameMap.put("configId", "configId_s");
			fieldNameMap.put("value", "configValue_s");
		}
	}

	public String getFieldNameTransformTOFieldName(String fieldName) throws InternalServiceException {
		createFieldNameMapIfNotExist();
		if(!fieldNameMap.containsKey(fieldName)){
			throw new InternalServiceException("can't find mapping solr doc field name from ["+fieldName+"]");
		}
		return fieldNameMap.get(fieldName);
	}

	public Set<String> findTOFieldNames() {
		createFieldNameMapIfNotExist();
		return fieldNameMap.keySet();
	}

}
