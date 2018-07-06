package com.syntrontech.pmo.solr;

import java.util.List;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;

public interface Solr {

	void saveJsonDoc(Object doc) throws SolrException, ParamFormatErrorException;
	
	void saveJsonDocs(List<?> docs) throws SolrException, ParamFormatErrorException;

	void deleteJsonDoc(String docIds) throws SolrException, ParamFormatErrorException;
	
	void deleteDocByQuery(String query) throws SolrException, ParamFormatErrorException;
	
	void deleteAllDoc(Class<?> doc) throws SolrException, ParamFormatErrorException;

	<T> T findOneJsonDocBySolrFieldValue(List<String> filters, Class<T> solrDocument)
			throws SolrException, ParamFormatErrorException;

	<T> SolrSearchModel<T> searchJsonDoc(String keyword, Integer index, Integer rows, String orderBy,
                                         String order, List<String> filters, Class<T> solrDocument) throws SolrException, ParamFormatErrorException;

	<T> SolrSearchModel<T> searchJsonDoc(String keyword, Integer index, Integer rows, String orderBy,
                                         String order, String filters, Class<T> solrDocument) throws SolrException, ParamFormatErrorException;
}
