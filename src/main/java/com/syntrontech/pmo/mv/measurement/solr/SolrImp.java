package com.syntrontech.pmo.mv.measurement.solr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;
import com.syntrontech.measurement.restful.vo.SearchVO;

public class SolrImp implements Solr {

	private static final Logger logger = LoggerFactory.getLogger(SolrImp.class);

	private String solrUrl;
	
	public SolrImp(String solrUrl){
		this.solrUrl = solrUrl;
	}

	@Override
	public void saveJsonDoc(Object doc) throws SolrException, ParamFormatErrorException {
		String updateJsonDocUrl = solrUrl + "/update/json/docs?commit=true&optimize=true";
		
		connectSolr_post(updateJsonDocUrl, doc);
		logger.trace("update document to solr successfully");
	}

	@Override
	public void deleteJsonDoc(String solrIds) throws SolrException, ParamFormatErrorException {
		SolrDeleteDoc deleteDoc = new SolrDeleteDoc();
		deleteDoc.setDelete(solrIds);
		String deleteJsonDocUrl = solrUrl + "/update?commit=true";
		
		connectSolr_post(deleteJsonDocUrl, deleteDoc);
		logger.trace("delete document to solr successfully");
	}
	@Override
	public <T> T findOneJsonDocBySolrFieldValue(String filter, Class<T> solrDocument) throws SolrException, ParamFormatErrorException{
		List<String> filters =new ArrayList<String>(1);
		filters.add(filter);
		return findOneJsonDocBySolrFieldValue(filters,solrDocument);
	}

	@Override
	public <T> T findOneJsonDocBySolrFieldValue(List<String> filters, Class<T> solrDocument)
			throws SolrException, ParamFormatErrorException {
		
		List<T> docs = searchJsonDoc("*", null, null, null, null, filters, solrDocument).getContent();
		if (docs.size() > 0) {
			return docs.get(0);
		}
		return null;
	}

	
	@Override
	public <T> SolrSearchModel<T> searchJsonDoc(SearchVO searchFactor, Class<T> solrDocument)
			throws SolrException, ParamFormatErrorException {
		
		return searchJsonDoc(searchFactor.getKeyword(), searchFactor.getIndex(), 
				searchFactor.getRows(), searchFactor.getOrderBy(), searchFactor.getOrder(),
				searchFactor.getFilters(), solrDocument);
	}
	@Override
	public <T> SolrSearchModel<T> searchJsonDoc(String keyword,Integer index, Integer rows, String orderBy,
			String order, List<String> filters, Class<T> solrDocument) 
					throws SolrException, ParamFormatErrorException {
		keyword = Optional.ofNullable(keyword).orElse("*");
		
		StringBuffer queryJsonDocUrl = new StringBuffer(solrUrl);
		try{
			queryJsonDocUrl.append("/select?wt=json")
							.append("&q=" + URLEncoder.encode(keyword, "utf-8"))
							.append("&fl=");
			
			List<Field> sonfields = Arrays.asList(solrDocument.getDeclaredFields());
			List<Field> superfields =Arrays.asList(solrDocument.getSuperclass().getDeclaredFields());
			List<Field> fields = new ArrayList<Field>(sonfields);
			fields.addAll(superfields);
			
			for (Field field : fields) {
				queryJsonDocUrl.append(field.getName() + "+");
			}
			if(Objects.nonNull(index) && index > 0 && Objects.nonNull(rows) && rows > 0){
				queryJsonDocUrl.append("&start=" + (index-1)*rows + "&rows=" + rows);
			}
			if(Objects.nonNull(orderBy) && Objects.nonNull(order)){
				queryJsonDocUrl.append("&sort=" + orderBy + "+" + order);
			}
			queryJsonDocUrl.append("&fq=id:" + solrDocument.getSimpleName() + "*");
			if(Objects.nonNull(filters)){
				for(String filter : filters){
					queryJsonDocUrl.append("&fq=" + URLEncoder.encode(filter, "utf-8"));
				}
			}
		}catch(UnsupportedEncodingException ex){
			
		}
			
		String response = connectSolr_get(queryJsonDocUrl.toString());
		
		return convertSolrResult(response, solrDocument);
	}

	protected <T> SolrSearchModel<T> convertSolrResult(String result, Class<T> target) throws SolrException {
		try {
			
			ObjectMapper objMapper = new ObjectMapper();
			Map<String, Object> resultMap = objMapper.readValue(result, HashMap.class);
			Map<String, Object> responseMap = (Map<String, Object>) resultMap.get("response");
			
			SolrSearchModel<T> solrSearchModel = new SolrSearchModel<>();
			solrSearchModel.setTotalnumFound((Integer) responseMap.get("numFound"));
			String docsStr = objMapper.writeValueAsString(responseMap.get("docs"));
			List<T> docs = objMapper.readValue(docsStr,TypeFactory.defaultInstance().constructCollectionType(List.class, target));
			solrSearchModel.setContent(docs);
			
			return solrSearchModel;
			
		} catch (IOException ex) {
			String errorMessage = "convert solr document's type json to object error =>" + ex.getMessage();
			logger.error(errorMessage);
			ex.printStackTrace();
			throw new SolrException(errorMessage);
		}
	}
	
	protected String connectSolr_post(String solrUrl, Object postEntity) throws SolrException, ParamFormatErrorException{
		
		Client client = ClientBuilder.newClient();
		
		try {
			
			String jsonString = new ObjectMapper().writeValueAsString(postEntity);
			
			Response response = client.target(solrUrl)
						 				.request(MediaType.APPLICATION_JSON)
						 				.post(Entity.entity(jsonString, MediaType.APPLICATION_JSON));
			
			logger.trace("post document[{}] to solr successfully", jsonString);
			
			String entity = response.readEntity(String.class);


			if (response.getStatus() == 400) {
				
				int status = response.getStatus();
				String errorMessage = "request to solr successfully, but get response status[" + status
						+ "], and the error code is [" + entity + "]";
				
				logger.error(errorMessage);
				throw new ParamFormatErrorException("Search");
				
			}else if(response.getStatus() != 200) {
				int status = response.getStatus();
				String errorMessage = "request to solr successfully, but get response status[" + status
						+ "], and the error code is [" + entity + "]";
				
				logger.error(errorMessage);
				throw new SolrException(errorMessage);
			}
			
			logger.trace("response is =>" + entity);
			
			return entity;
			
		} catch (Exception ex) {
			
			if (ex instanceof ParamFormatErrorException) {
				throw (ParamFormatErrorException) ex;
			}else if (ex instanceof SolrException) {
				throw (SolrException) ex;
			} else {
				String errorMessage = "can't request to solr, and the error message is [" + ex.getMessage() + "]";
				logger.error(errorMessage);
				ex.printStackTrace();
				throw new SolrException(errorMessage);
			}
		}
	}
	
	protected String connectSolr_get(String solrUrl) throws SolrException, ParamFormatErrorException{
		
		Client client = ClientBuilder.newClient();
		
		try {
			Response response = client.target(solrUrl)
					 					.request(MediaType.TEXT_PLAIN_TYPE)
					 					.get();
			logger.trace("query document from solr successfully");
			
			String entity = response.readEntity(String.class);

			if (response.getStatus() == 400) {
				
				int status = response.getStatus();
				String errorMessage = "request to solr successfully, but get response status[" + status
						+ "], and the error code is [" + entity + "]";
				
				logger.error(errorMessage);
				throw new ParamFormatErrorException("Search error");
				
			}else if(response.getStatus() != 200) {
				int status = response.getStatus();
				String errorMessage = "request to solr successfully, but get response status[" + status
						+ "], and the error code is [" + entity + "]";
				
				logger.error(errorMessage);
				throw new SolrException(errorMessage);
			}
			
			logger.trace("response is =>" + entity);
			
			return entity;
			
		} catch (Exception ex) {
			
			if (ex instanceof ParamFormatErrorException) {
				
				throw (ParamFormatErrorException) ex;
			}else if (ex instanceof SolrException) {
				
				throw (SolrException) ex;
			} else {
				
				String errorMessage = "can't request to solr, and the error message is [" + ex.getMessage() + "]";
				logger.error(errorMessage);
				ex.printStackTrace();
				throw new SolrException(errorMessage);
			}
		}
	}


}
