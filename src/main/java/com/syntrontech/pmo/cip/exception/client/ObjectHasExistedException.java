package com.syntrontech.pmo.cip.exception.client;

import com.syntrontech.autoTool.exception.client.ClientException;

public class ObjectHasExistedException extends ClientException {

	private static final long serialVersionUID = 1L;

	private String errorCode = "OBJECT_ALREADY_EXISTS";
	
	private String errorMessage = "object already existed";
	
	public ObjectHasExistedException(String paramName){
		errorCode = errorCode.replace("OBJECT", paramName.toUpperCase());
		errorMessage = errorMessage.replace("object", paramName.toLowerCase());
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
