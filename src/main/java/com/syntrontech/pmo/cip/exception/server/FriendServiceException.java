package com.syntrontech.pmo.cip.exception.server;

import com.syntrontech.autoTool.exception.server.ServerException;

public class FriendServiceException  extends ServerException {

	private static final long serialVersionUID = 7851947583257104271L;

	private String errorCode = "FRIEND_SERVER_ERROR";
	
	private String errorMessage = "friend server error :";
	
	public FriendServiceException(String errorMessage){
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
