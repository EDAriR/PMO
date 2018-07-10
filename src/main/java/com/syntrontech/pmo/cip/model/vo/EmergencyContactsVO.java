package com.syntrontech.pmo.cip.model.vo;

import com.syntrontech.autoTool.annotation.ParamNotNull;
import com.syntrontech.autoTool.annotation.ParamStringRestrict;

public class EmergencyContactsVO {
	
	@ParamNotNull
	private String emergencyContactName; 
	
	@ParamNotNull
	@ParamStringRestrict(pattern="[0-9]{0,15}")
	private String emergencyContactMobilePhone;
	
	@ParamStringRestrict(pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$")
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
	
}
