package com.syntrontech.pmo.cip.externalService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ExternalServiceApiRequest {
	
	public static <T> T sendGetRequest(String targetUrl, String token, Class<T> to) 
			throws NotOkResponseException{
		Client client = ClientBuilder.newClient();
		
		Response response = client.target(targetUrl)
					 			  .request(MediaType.APPLICATION_JSON)
					 			  .header("Authorization", token)
					 			  .get();
		return getResponseObject(response, to);
	}
	
	public static <T> T sendPostRequest(String targetUrl, String token, Object vo, Class<T> to) 
			throws NotOkResponseException{
		Client client = ClientBuilder.newClient(); 
		
		Response response = client.target(targetUrl)
					 			  .request(MediaType.APPLICATION_JSON)
					 			  .header("Authorization", token)
					 			  .post(Entity.entity(vo, MediaType.APPLICATION_JSON));
		return getResponseObject(response, to);
	}
	
	public static <T> T sendPutRequest(String targetUrl, String token, Object vo, Class<T> to) 
			throws NotOkResponseException{
		Client client = ClientBuilder.newClient(); 
		
		Response response = client.target(targetUrl)
					 			  .request(MediaType.APPLICATION_JSON)
					 			  .header("Authorization", token)
					 			  .put(Entity.entity(vo, MediaType.APPLICATION_JSON));
		return getResponseObject(response, to);
	}
	
	protected static <T> T getResponseObject(Response response, Class<T> to) 
			throws NotOkResponseException{
		if(response.getStatus() == 400){
			ErrorTO error = response.readEntity(ErrorTO.class);
			throw new NotOkResponseException(error);
			
		}else if(response.getStatus() == 401){
			ErrorTO error = new ErrorTO();
			error.setCode("UNAUTHORIZED");
			error.setMessage("Authorization token not exist or expired");
			throw new NotOkResponseException(error);
			
		}else if(response.getStatus() == 403){
			ErrorTO error = new ErrorTO();
			error.setCode("FORBIDDEN");
			error.setMessage("no permission to operate");
			throw new NotOkResponseException(error);
			
		}else if(response.getStatus() == 404){
			ErrorTO error = new ErrorTO();
			error.setCode("NOTFOUND");
			error.setMessage("url not found");
			throw new NotOkResponseException(error);
			
		}
		
		return response.readEntity(to);
	}
}
