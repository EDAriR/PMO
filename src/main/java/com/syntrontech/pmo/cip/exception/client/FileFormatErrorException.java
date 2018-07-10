package com.syntrontech.pmo.cip.exception.client;

import com.syntrontech.autoTool.exception.client.ClientException;

public class FileFormatErrorException extends ClientException {
	
	private static final long serialVersionUID = 7947593729994622555L;

	private String errorCode = "INVALID_FILE_FORMAT";
	
	private String errorMessage = "the import file format is invalid because => ";
	
	public FileFormatErrorException(String message) {
		this.errorMessage = this.errorMessage + message;
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
