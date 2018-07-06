package com.syntrontech.pmo.solr;

import com.syntrontech.autoTool.exception.server.ServerException;

public class InternalServiceException extends ServerException {
	
	private static final long serialVersionUID = 4314830620177226994L;

	private String errorCode = "INTERNAL_SERVICE_ERROR";
	
	private String errorMessage = "internal service error =>";
	
	public InternalServiceException(String errorMessage){
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
