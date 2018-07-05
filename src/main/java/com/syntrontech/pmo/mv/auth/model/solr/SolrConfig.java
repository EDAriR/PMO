package com.syntrontech.pmo.mv.auth.model.solr;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.syntrontech.auth.exception.server.InternalServiceException;
import com.syntrontech.auth.model.Config;
import com.syntrontech.auth.restful.to.TO;

public class SolrConfig implements SolrDoc<Config>{
	
	private String id;
	
	private String configId_s;
	
	private String configValue_s;
	
	private static Map<String, String> fieldNameMap;
	
	@Override
	public String getId() {
		return id;
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
	
	@Override
	public TO<Config> convertFrom(Config model) {
		this.id = this.getClass().getSimpleName()+model.getSequence();
		this.configId_s = model.getId();
		this.configValue_s = model.getValue();
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
