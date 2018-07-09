package com.syntrontech.pmo.pmo;

import org.apache.commons.lang.StringUtils;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

class PmoServiceBuilder<T> {
	private String url;
	
	private String serviceName;
	
	private final Class<T> serviceEndpointInterface;
	
	private String trustStore;
	
	private String trustStorePassword;
	
	private String trustStoreType;
	
	public PmoServiceBuilder(Class<T> serviceEndpointInterface){
		this.serviceEndpointInterface = serviceEndpointInterface;
	}

	public String getUrl() {
		return url;
	}

	public String getServiceName() {
		return serviceName;
	}

	public Class<T> getServiceEndpointInterface() {
		return serviceEndpointInterface;
	}

	public String getTrustStore() {
		return trustStore;
	}

	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	public String getTrustStoreType() {
		return trustStoreType;
	}

	public PmoServiceBuilder<T> serviceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}
	
	public PmoServiceBuilder<T> url(String url){
		this.url = url;
		return this;
	}
	
	public PmoServiceBuilder<T> trustStore(String store){
		this.trustStore = store;
		return this;
	}
	
	public PmoServiceBuilder<T> trustStorePassword(String storePassword){
		this.trustStorePassword = storePassword;
		return this;
	}
	
	public PmoServiceBuilder<T> trustStoreType(String storeType){
		this.trustStoreType = storeType;
		return this;
	}

	public T build(){
		if(StringUtils.isNotBlank(trustStore)){
			setSSL();
		}
		return buildService().getPort(buildQName(serviceName+"Soap"), serviceEndpointInterface);
	}
	
	private void setSSL(){
		setProperty("javax.net.ssl.trustStore", trustStore);
		setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
		setProperty("javax.net.ssl.trustStoreType", trustStoreType);
	}
	
	private void setProperty(String key, String value){
		System.setProperty(key, value);
	}
	
	private Service buildService(){
		return Service.create(buildURL(), buildQName(serviceName));
	}
	
	private URL buildURL(){
		try {
			return new URL(_buildURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String _buildURL() {
		return new StringBuilder()
			.append(url)
			.append(serviceName)
			.append(".asmx?wsdl")
			.toString();
	}
	
	private QName buildQName(String serviceName){
		return new QName("http://tempuri.org/", serviceName);
	}
}
