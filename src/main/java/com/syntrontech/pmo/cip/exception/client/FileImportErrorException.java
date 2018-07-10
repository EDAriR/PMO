package com.syntrontech.pmo.cip.exception.client;

import com.syntrontech.autoTool.exception.client.ClientException;
import com.syntrontech.pmo.cip.externalService.ErrorTO;

public class FileImportErrorException extends ClientException {

	private static final long serialVersionUID = 2956177913379451404L;
	
	private String errorCode = "FILE_IMPORT_ERROR";
	
	private String errorMessage;
	
	public FileImportErrorException(int rowNum, ErrorTO error) {
		
		String jsonError = "{"+"\"code\":"+"\""+error.getCode()+"\","
							  +"\"message\":"+"\""+error.getMessage()+"\"}";
		errorMessage = "file row ["+rowNum+"] error => "+jsonError;
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
