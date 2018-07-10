package com.syntrontech.pmo.cip.exception.server;

import com.syntrontech.autoTool.exception.server.ServerException;

public class POIServiceEcxeption extends ServerException{
	
	private static final long serialVersionUID = 3062414299014443316L;

	private String errorCode = "POI_SERVER_UNSUCCESSFULLY";
	
	private String errorMessage = "POI service create excel unsuccessfully because => ";
	
	public POIServiceEcxeption(String errorMessage){
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
