package com.syntrontech.pmo.cip.exception.server;

import com.syntrontech.autoTool.exception.server.ServerException;

public class PushServerException extends ServerException {
	
	private static final long serialVersionUID = 3062414299014443316L;

	private String errorCode = "PUSH_MESSAGE_TO_PUSH_SERVER_UNSUCCESSFULLY";
	
	private String errorMessage = "can't push message to push server successfully because => ";
	
	public PushServerException(String errorMessage){
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
