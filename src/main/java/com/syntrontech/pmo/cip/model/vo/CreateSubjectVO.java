package com.syntrontech.pmo.cip.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import com.syntrontech.autoTool.annotation.ParamEnumRequired;
import com.syntrontech.autoTool.annotation.ParamNotNull;
import com.syntrontech.autoTool.annotation.ParamStringRestrict;
import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;
import com.syntrontech.pmo.model.common.*;

public class CreateSubjectVO {

	@ParamNotNull
	@ParamStringRestrict
	private String subjectId;
	
	@ParamNotNull
	@ParamStringRestrict
	private String subjectName;
	
	@ParamNotNull
	@ParamEnumRequired(GenderType.class)
	private String gender;
	
	@ParamNotNull
	@ParamStringRestrict(pattern = "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$")
	private String birthday;
	
	@ParamStringRestrict(pattern="[0-9]{0,10}")
	private String homePhone;
	
	@ParamStringRestrict
	private String address;
	
	@ParamNotNull
	@ParamEnumRequired(EthnicityType.class)
	private String ethnicity;
	
	@ParamNotNull
	@ParamEnumRequired(PersonalHistoryType.class)
	private List<String> personalHistory;
	
	@ParamNotNull
	@ParamEnumRequired(FamilyHistoryType.class)
	private List<String> familyHistory;
	
	@ParamNotNull
	@ParamEnumRequired(SmokeType.class)
	private String smoke;
	
	@ParamNotNull
	@ParamEnumRequired(DrinkType.class)
	private String drink;
	
	@ParamNotNull
	@ParamEnumRequired(ChewingArecaType.class)
	private String chewingAreca;
	
	@ParamStringRestrict
	private String unitId;
	
	private ArrayList<EmergencyContactsVO> emergencyContacts;
	
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
		return GenderType.valueOf(gender);
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() throws ParamFormatErrorException {
		Date date = null;
		try {
			date = DatatypeConverter.parseDate(birthday).getTime();
		} catch (IllegalArgumentException e) {
			throw new ParamFormatErrorException("birthday");
		}
		return date;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
		return EthnicityType.valueOf(ethnicity);
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public List<PersonalHistoryType> getPersonalHistory() {
		return personalHistory.stream()
							  .map(p -> PersonalHistoryType.valueOf(p))
							  .collect(Collectors.toList());
	}

	public void setPersonalHistory(List<String> personalHistory) {
		this.personalHistory = personalHistory;
	}

	public List<FamilyHistoryType> getFamilyHistory() {
		return familyHistory.stream()
							.map(f -> FamilyHistoryType.valueOf(f))
							.collect(Collectors.toList());
	}

	public void setFamilyHistory(List<String> familyHistory) {
		this.familyHistory = familyHistory;
	}

	public SmokeType getSmoke() {
		return SmokeType.valueOf(smoke);
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public DrinkType getDrink() {
		return DrinkType.valueOf(drink);
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public ChewingArecaType getChewingAreca() {
		return ChewingArecaType.valueOf(chewingAreca);
	}

	public void setChewingAreca(String chewingAreca) {
		this.chewingAreca = chewingAreca;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public ArrayList<EmergencyContactsVO> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(ArrayList<EmergencyContactsVO> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	
}
