package com.syntrontech.pmo.cip.model.to;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.syntrontech.pmo.cip.model.solr.SolrSubject;
import com.syntrontech.pmo.model.common.*;

public class MeasuredSubjectTO extends SubjectTO{
	
	private String userId;
	private String userName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public MeasuredSubjectTO setMeasuredSubjectTO(SolrSubject model) {
		this.setUserId(model.getSubjectUserId_s());
		this.setSubjectId(model.getSubjectId_s());
		this.setSubjectName(model.getSubjectName_s());
		this.setGender(GenderType.valueOf(model.getSubjectGender_s()));
		String dt = model.getSubjectBirthday_dt();
		
		ISO8601DateFormat df = new ISO8601DateFormat();
		try {
			this.setBirthday(df.parse(dt));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.setHomePhone(model.getSubjectHomePhone_s());
		this.setAddress(model.getSubjectAddress_s());
		this.setEthnicity(EthnicityType.valueOf(model.getSubjectEthnicity_s()));
		this.setPersonalHistory(model.getSubjectPersonalHistory_ss());
		this.setFamilyHistory(model.getSubjectFamilyHistory_ss());
		this.setSmoke(SmokeType.valueOf(model.getSubjectSmoke_s()));
		this.setDrink(DrinkType.valueOf(model.getSubjectDrink_s()));
		this.setChewingAreca(ChewingArecaType.valueOf(model.getSubjectChewingAreca_s()));
		this.setUnitId(model.getSubjectUnitId_s());
		this.setUnitName(model.getSubjectUnitName_s());
		
		return this;
	}
	
	public MeasuredSubjectTO setMeasuredSubjectTO(SolrSubject model, List<EmergencyContactTO> emergencyContacts) {
		this.setUserId(model.getSubjectUserId_s());
		this.setSubjectId(model.getSubjectId_s());
		this.setSubjectName(model.getSubjectName_s());
		this.setGender(GenderType.valueOf(model.getSubjectGender_s()));
		String dt = model.getSubjectBirthday_dt();
		
		ISO8601DateFormat df = new ISO8601DateFormat();
		try {
			this.setBirthday(df.parse(dt));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.setHomePhone(model.getSubjectHomePhone_s());
		this.setAddress(model.getSubjectAddress_s());
		this.setEthnicity(EthnicityType.valueOf(model.getSubjectEthnicity_s()));
		this.setPersonalHistory(model.getSubjectPersonalHistory_ss());
		this.setFamilyHistory(model.getSubjectFamilyHistory_ss());
		this.setSmoke(SmokeType.valueOf(model.getSubjectSmoke_s()));
		this.setDrink(DrinkType.valueOf(model.getSubjectDrink_s()));
		this.setChewingAreca(ChewingArecaType.valueOf(model.getSubjectChewingAreca_s()));
		this.setUnitId(model.getSubjectUnitId_s());
		this.setUnitName(model.getSubjectUnitName_s());
		
		this.setEmergencyContacts(emergencyContacts);
		
		return this;
	}

}
