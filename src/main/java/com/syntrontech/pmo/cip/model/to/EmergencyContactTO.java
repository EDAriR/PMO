package com.syntrontech.pmo.cip.model.to;


import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.solr.SolrEmergencyContacts;
import com.syntrontech.pmo.model.TO.TO;

public class EmergencyContactTO implements TO<EmergencyContact> {
	
	private String emergencyContactName;
	private String emergencyContactMobilePhone;
	private String emergencyContactEmail;
	
	
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactMobilePhone() {
		return emergencyContactMobilePhone;
	}
	public void setEmergencyContactMobilePhone(String emergencyContactMobilePhone) {
		this.emergencyContactMobilePhone = emergencyContactMobilePhone;
	}
	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}
	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}
	
	@Override
	public TO<EmergencyContact> convertFrom(EmergencyContact model) {
		
		this.emergencyContactName = model.getName();
		this.emergencyContactMobilePhone = model.getPhone();
		this.emergencyContactEmail = model.getEmail();
		return this;
	}

	public TO<EmergencyContact> convertFrom(SolrEmergencyContacts model) {
		
		this.emergencyContactName = model.getName_s();
		this.emergencyContactMobilePhone = model.getPhone_s();
		this.emergencyContactEmail = model.getEmail_s();
		return this;
	}

	
}
