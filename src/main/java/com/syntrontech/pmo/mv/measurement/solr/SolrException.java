package com.syntrontech.pmo.mv.measurement.solr;

import com.syntrontech.autoTool.exception.server.ServerException;

public class SolrException extends ServerException {
	
	private static final long serialVersionUID = -1536290598397825533L;

	private String errorCode = "SOLR_SERVER_ERROR";
	
	private String errorMessage = "solr server error =>";
	
	public SolrException(String errorMessage){
		this.errorMessage = this.errorMessage + errorMessage;
	}
	
	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

}
