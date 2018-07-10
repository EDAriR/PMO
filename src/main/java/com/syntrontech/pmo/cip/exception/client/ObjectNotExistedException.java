package com.syntrontech.pmo.cip.exception.client;

import com.syntrontech.autoTool.exception.client.ClientException;

public class ObjectNotExistedException extends ClientException {

	
	private static final long serialVersionUID = -8223693285696570532L;
	
	private String errorCode = "OBJECT_DOES_NOT_EXIST";
	
	private String errorMessage = "object doesn't exist";
	
	public ObjectNotExistedException(String objectName){
		errorCode = errorCode.replace("OBJECT", objectName.toUpperCase());
		errorMessage = errorMessage.replace("object", objectName.toLowerCase());
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
