package com.syntrontech.pmo.mv.measurement.solr;

import java.util.List;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;
import com.syntrontech.measurement.restful.vo.SearchVO;

public interface Solr {

	void saveJsonDoc(Object doc) throws SolrException, ParamFormatErrorException;

	void deleteJsonDoc(String docIds) throws SolrException, ParamFormatErrorException;

	<T> T findOneJsonDocBySolrFieldValue(String filter, Class<T> solrDocument) throws SolrException, ParamFormatErrorException;
	
	<T> T findOneJsonDocBySolrFieldValue(List<String> filters, Class<T> solrDocument)
			throws SolrException, ParamFormatErrorException;

	<T> SolrSearchModel<T> searchJsonDoc(String keyword, Integer index, Integer rows, String orderBy,
                                         String order, List<String> filters, Class<T> solrDocument) throws SolrException, ParamFormatErrorException;
	
	<T> SolrSearchModel<T> searchJsonDoc(SearchVO searchFactor, Class<T> solrDocument) throws SolrException, ParamFormatErrorException;

	

}
