package com.syntrontech.pmo.cip.model.to;

import com.syntrontech.pmo.cip.model.solr.SolrSubject;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.model.common.*;
import com.syntrontech.pmo.util.ISODateTime;

import java.util.Date;
import java.util.List;


public class SubjectTO implements TO<SolrSubject> {
	
	private String subjectId;
	
	private String subjectName;
	
	private GenderType gender;
	
	private String birthday;
	
	private String homePhone;
	
	private String address;
	
	private EthnicityType ethnicity;
	
	private String[] personalHistory;
	
	private String[] familyHistory;
	
	private SmokeType smoke;
	
	private DrinkType drink;
	
	private ChewingArecaType chewingAreca;
	
	private String unitId;
	
	private String unitName;
	
	private List<EmergencyContactTO> emergencyContact;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = ISODateTime.formatISOString(birthday);
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EthnicityType getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(EthnicityType ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String[] getPersonalHistory() {
		return personalHistory;
	}

	public void setPersonalHistory(String[] personalHistory) {
		this.personalHistory = personalHistory;
	}

	public String[] getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String[] familyHistory) {
		this.familyHistory = familyHistory;
	}

	public SmokeType getSmoke() {
		return smoke;
	}

	public void setSmoke(SmokeType smoke) {
		this.smoke = smoke;
	}

	public DrinkType getDrink() {
		return drink;
	}

	public void setDrink(DrinkType drink) {
		this.drink = drink;
	}

	public ChewingArecaType getChewingAreca() {
		return chewingAreca;
	}

	public void setChewingAreca(ChewingArecaType chewingAreca) {
		this.chewingAreca = chewingAreca;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public List<EmergencyContactTO> getEmergencyContacts() {
		return emergencyContact;
	}

	public void setEmergencyContacts(List<EmergencyContactTO> emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	@Override
	public TO<SolrSubject> convertFrom(SolrSubject model) {
		this.subjectId = model.getSubjectId_s();
		this.subjectName = model.getSubjectName_s();
		this.gender = GenderType.valueOf(model.getSubjectGender_s());
		this.birthday = model.getSubjectBirthday_dt();
		this.homePhone = model.getSubjectHomePhone_s();
		this.address = model.getSubjectAddress_s();
		this.ethnicity = EthnicityType.valueOf(model.getSubjectEthnicity_s());
		this.personalHistory = model.getSubjectPersonalHistory_ss();
		this.familyHistory = model.getSubjectFamilyHistory_ss();
		this.smoke = SmokeType.valueOf(model.getSubjectSmoke_s());
		this.drink = DrinkType.valueOf(model.getSubjectDrink_s());
		this.chewingAreca = ChewingArecaType.valueOf(model.getSubjectChewingAreca_s());
		this.unitId = model.getSubjectUnitId_s();
		this.unitName = model.getSubjectUnitName_s();
		return this;
	}

	

}
