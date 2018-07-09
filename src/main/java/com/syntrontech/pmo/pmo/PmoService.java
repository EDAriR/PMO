package com.syntrontech.pmo.pmo;

public class PmoService {
	public static <T> PmoServiceBuilder<T> service(Class<T> serviceEndpointInterface){
		return new PmoServiceBuilder<T>(serviceEndpointInterface);
	}
}
