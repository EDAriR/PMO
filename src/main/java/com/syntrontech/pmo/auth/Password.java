package com.syntrontech.pmo.auth;

import java.util.Date;

public class Password {
	
	private final static Encrypt encrypt = new Encrypt();
	
	public static String get(String password, Date passwordUpdateTime){
		return encrypt.encrypt_sha512(password+passwordUpdateTime.getTime());
	}
}
