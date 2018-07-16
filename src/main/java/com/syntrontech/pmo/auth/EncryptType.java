package com.syntrontech.pmo.auth;

public enum EncryptType {
	SHA512("SHA-512"), MD5("MD5");
	
	private String value;
	
	private EncryptType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
