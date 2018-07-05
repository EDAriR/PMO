package com.syntrontech.pmo.mv.auth.model.solr;

import java.util.Set;

import com.syntrontech.auth.exception.server.InternalServiceException;
import com.syntrontech.auth.restful.to.TO;

public interface SolrDoc<T> extends TO<T>{
	
	String getId();
	
	String getFieldNameTransformTOFieldName(String fieldName) throws InternalServiceException;
	
	Set<String> findTOFieldNames();
}
