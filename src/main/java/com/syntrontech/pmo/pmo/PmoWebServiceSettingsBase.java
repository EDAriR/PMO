package com.syntrontech.pmo.pmo;

public class PmoWebServiceSettingsBase implements PmoWebServiceSettings {
	private String url;
	
	private String pmoAccount;
	
	private String pmoCredentials;
	
	private String areaCode;
	
	private String trustStore;
	
	private String trustStorePassword;
	
	private String trustStoreType;

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getPmoAccount() {
		return pmoAccount;
	}

	@Override
	public String getPmoCredentials() {
		return pmoCredentials;
	}
	
	@Override
	public String getAreaCode() {
		return areaCode;
	}

	@Override
	public String getTrustStore() {
		return trustStore;
	}

	@Override
	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	@Override
	public String getTrustStoreType() {
		return trustStoreType;
	}

	public void setTrustStore(String trustStore) {
		this.trustStore = trustStore;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public void setTrustStoreType(String trustStoreType) {
		this.trustStoreType = trustStoreType;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPmoAccount(String pmoAccount) {
		this.pmoAccount = pmoAccount;
	}

	public void setPmoCredentials(String pmoCredentials) {
		this.pmoCredentials = pmoCredentials;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return "PmoWebServiceSettingsBase [url=" + url + ", pmoAccount="
				+ pmoAccount + ", pmoCredentials=" + pmoCredentials
				+ ", areaCode=" + areaCode + ", trustStore=" + trustStore
				+ ", trustStorePassword=" + trustStorePassword
				+ ", trustStoreType=" + trustStoreType + "]";
	}
}
