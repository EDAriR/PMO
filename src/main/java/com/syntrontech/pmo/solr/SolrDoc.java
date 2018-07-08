package com.syntrontech.pmo.solr;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;

public interface SolrDoc{
	
	String getId();
	
	default String getFieldNameformTO(String fieldName) throws ParamFormatErrorException {
		Optional<String> fieldValue = Optional.ofNullable(createFieldNameMap().get(fieldName));
		if(!fieldValue.isPresent()) {
			Logger logger = LoggerFactory.getLogger(this.getClass());
			logger.error("SolrModel can't find mapping [" + fieldName + "]");
			throw new ParamFormatErrorException("Search");
		}
		return fieldValue.get();
	}

	default Set<String> findFieldNames() {
		return createFieldNameMap().keySet();
	}
	
	public Map<String, String> createFieldNameMap();
	
}
