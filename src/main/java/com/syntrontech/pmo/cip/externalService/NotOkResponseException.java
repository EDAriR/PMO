package com.syntrontech.pmo.cip.externalService;

public class NotOkResponseException extends Exception {

	private static final long serialVersionUID = -2886701021867771336L;
	
	private ErrorTO error;
	
	public NotOkResponseException(ErrorTO error){
		this.error = error;
	}
	
	public ErrorTO getErrorTO(){
		return error;
	}
}
