package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.common.Sex;
import com.syntrontech.syncare.model.common.YN;
import com.syntrontech.syncare.model.entity.Area;
import com.syntrontech.syncare.model.entity.SystemUser;
import com.syntrontech.syncare.model.entity.SystemUserCard;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RegisterVO implements ObjectConverter<SystemUser>, Serializable {
	private static final long serialVersionUID = -3868552241538061915L;

	private List<String> cardId = new ArrayList<>();
	
	private String id;
	
	private String name;
	
	private String credentials;
	
	private Sex sex;
	
	private String address;
	
	private Date birthday;
	
	private String tel;
	
	private String email;
	
	private String phone;
	
	private boolean alert = false;
	
	private String areaCode;
	
	private boolean withHighBloodPressure = false;
	
	private boolean withDiabetesMellitus = false;
	
	private String alertNotifierName;
	
	private String alertNotifierMobilePhone;
	
	private String alertNotifierEmail;
	
	private int ethnicity;
	
	private boolean withHeartAttack;
	
	private boolean withBrainAttack;
	
	private boolean familyWithHeartAttack;
	
	private boolean familyWithBrainAttack;
	
	private boolean familyWithHighBloodPressure;
	
	private boolean familyWithDiabetesMellitus;

	private int frequencyOfSmoking;
	
	private int frequencyOfDrinking;
	
	private int frequencyOfChewingBetelNut;
	
	private boolean healthInformationNotify;

	
	
	public List<String> getCardId() {
		return cardId;
	}

	public String getId() {
		return id.toUpperCase();
	}

	public String getName() {
		return name;
	}
	
	public String getCredentials() {
		return credentials;
	}

	public Sex getSex() {
		return sex;
	}

	public String getAddress() {
		return address;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isAlert() {
		return alert;
	}

	public String getAreaCode() {
		return areaCode;
	}
	
	public boolean isWithHighBloodPressure() {
		return withHighBloodPressure;
	}
	
	public boolean isWithDiabetesMellitus() {
		return withDiabetesMellitus;
	}
	
	public String getAlertNotifierName() {
		return alertNotifierName;
	}
	
	public String getAlertNotifierMobilePhone() {
		return alertNotifierMobilePhone;
	}
	
	public String getAlertNotifierEmail() {
		return alertNotifierEmail;
	}
	
	public int getEthnicity() {
		return ethnicity;
	}

	public boolean isWithHeartAttack() {
		return withHeartAttack;
	}

	public boolean isWithBrainAttack() {
		return withBrainAttack;
	}

	public boolean isFamilyWithHeartAttack() {
		return familyWithHeartAttack;
	}

	public boolean isFamilyWithBrainAttack() {
		return familyWithBrainAttack;
	}

	public boolean isFamilyWithHighBloodPressure() {
		return familyWithHighBloodPressure;
	}

	public boolean isFamilyWithDiabetesMellitus() {
		return familyWithDiabetesMellitus;
	}

	public int getFrequencyOfSmoking() {
		return frequencyOfSmoking;
	}

	public int getFrequencyOfDrinking() {
		return frequencyOfDrinking;
	}

	public int getFrequencyOfChewingBetelNut() {
		return frequencyOfChewingBetelNut;
	}

	public boolean isHealthInformationNotify() {
		return healthInformationNotify;
	}

	
	
	public void setCardId(List<String> cardId) {
		this.cardId = cardId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public void setWithHighBloodPressure(boolean withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}
	
	public void setWithDiabetesMellitus(boolean withDiabetesMellitus) {
		this.withDiabetesMellitus = withDiabetesMellitus;
	}
	
	public void setAlertNotifierName(String alertNotifierName) {
		this.alertNotifierName = alertNotifierName;
	}
	
	public void setAlertNotifierMobilePhone(String alertNotifierMobilePhone) {
		this.alertNotifierMobilePhone = alertNotifierMobilePhone;
	}
	
	public void setAlertNotifierEmail(String alertNotifierEmail) {
		this.alertNotifierEmail = alertNotifierEmail;
	}
	
	public void setEthnicity(int ethnicity) {
		this.ethnicity = ethnicity;
	}
	
	public void setWithHeartAttack(boolean withHeartAttack) {
		this.withHeartAttack = withHeartAttack;
	}
	
	public void setWithBrainAttack(boolean withBrainAttack) {
		this.withBrainAttack = withBrainAttack;
	}
	
	public void setFamilyWithHeartAttack(boolean familyWithHeartAttack) {
		this.familyWithHeartAttack = familyWithHeartAttack;
	}
	
	public void setFamilyWithBrainAttack(boolean familyWithBrainAttack) {
		this.familyWithBrainAttack = familyWithBrainAttack;
	}
	
	public void setFamilyWithHighBloodPressure(boolean familyWithHighBloodPressure) {
		this.familyWithHighBloodPressure = familyWithHighBloodPressure;
	}
	
	public void setFamilyWithDiabetesMellitus(boolean familyWithDiabetesMellitus) {
		this.familyWithDiabetesMellitus = familyWithDiabetesMellitus;
	}
	
	public void setFrequencyOfSmoking(int frequencyOfSmoking) {
		this.frequencyOfSmoking = frequencyOfSmoking;
	}
	
	public void setFrequencyOfDrinking(int frequencyOfDrinking) {
		this.frequencyOfDrinking = frequencyOfDrinking;
	}
	
	public void setFrequencyOfChewingBetelNut(int frequencyOfChewingBetelNut) {
		this.frequencyOfChewingBetelNut = frequencyOfChewingBetelNut;
	}
	
	public void setHealthInformationNotify(boolean healthInformationNotify) {
		this.healthInformationNotify = healthInformationNotify;
	}
	
	
	
	@Override
	public String toString() {
		return "RegisterVO ["
				+ "cardId=" + cardId 
				+ ", id=" + id 
				+ ", name=" + name
				+ ", sex=" + sex 
				+ ", address=" + address 
				+ ", birthday=" + birthday 
				+ ", tel=" + tel 
				+ ", email=" + email 
				+ ", phone=" + phone 
				+ ", alert=" + alert 
				
				+ ", ethnicity=" + ethnicity 
				+ ", withHeartAttack=" + withHeartAttack 
				+ ", withBrainAttack=" + withBrainAttack 
				+ ", familyWithHeartAttack=" + familyWithHeartAttack 
				+ ", familyWithBrainAttack=" + familyWithBrainAttack 
				+ ", familyWithHighBloodPressure=" + familyWithHighBloodPressure 
				+ ", familyWithDiabetesMellitus=" + familyWithDiabetesMellitus
				+ ", frequencyOfSmoking=" + frequencyOfSmoking 
				+ ", frequencyOfDrinking=" + frequencyOfDrinking 
				+ ", frequencyOfChewingBetelNut=" + frequencyOfChewingBetelNut 
				+ ", healthInformationNotify=" + healthInformationNotify 
				+ "]";
	}

	@Override
	public SystemUser convert(boolean relation) {
		SystemUser user = new SystemUser();
		user.setUserAccount(getId());
		user.setUserDisplayName(getName());
		user.setSex(getSex());
		user.setUserAddress(getAddress());
		user.setUserBirthday(new Timestamp(getBirthday().getTime()));
		user.setAge(computeAge(getBirthday()));
		user.setUserPhone(getTel());
		user.setUserEmail(getEmail());
		user.setUserMobile(getPhone());
		user.setAlert(isAlert() ? YN.Y : YN.N);
		user.setCreateTime(new Timestamp(new Date().getTime()));
		user.setWithHighBloodPressure(isWithHighBloodPressure() ? YN.Y : YN.N);
		user.setWithDiabetesMellitus(isWithDiabetesMellitus() ? YN.Y : YN.N);
		user.setAlertNotifierName(StringUtils.isBlank(getAlertNotifierName()) ? "" : getAlertNotifierName());
		user.setAlertNotifierMobilePhone(StringUtils.isBlank(getAlertNotifierMobilePhone()) ? "" : getAlertNotifierMobilePhone());
		user.setAlertNotifierEmail(StringUtils.isBlank(getAlertNotifierEmail()) ? "" : getAlertNotifierEmail());
		
		user.setEthnicity(getEthnicity());
		user.setWithHeartAttack(isWithHeartAttack() ? YN.Y : YN.N);
		user.setWithBrainAttack(isWithBrainAttack() ? YN.Y : YN.N);
		user.setFamilyWithHeartAttack(isFamilyWithHeartAttack() ? YN.Y : YN.N);
		user.setFamilyWithBrainAttack(isFamilyWithBrainAttack() ? YN.Y : YN.N);
		user.setFamilyWithHighBloodPressure(isFamilyWithHighBloodPressure() ? YN.Y : YN.N);
		user.setFamilyWithDiabetesMellitus(isFamilyWithDiabetesMellitus() ? YN.Y : YN.N);
		user.setFrequencyOfSmoking(getFrequencyOfSmoking());
		user.setFrequencyOfDrinking(getFrequencyOfDrinking());
		user.setFrequencyOfChewingBetelNut(getFrequencyOfChewingBetelNut());
		user.setHealthInformationNotify(isHealthInformationNotify() ? YN.Y : YN.N);
		
		Area area = new Area();
		area.setCode(getAreaCode());
		user.setArea(area);
		
		getCardId().stream()
			.filter(card -> StringUtils.isNotBlank(card))
			.map(card -> setUserCardId(card))
			.forEach(card -> user.addCard(card));
		return user;
	}
	
	private int computeAge(Date userBirthday) {
		Date now = new Date();
		Date age = new Date(now.getTime() - userBirthday.getTime());
		Calendar instance = Calendar.getInstance();
		instance.setTime(age);
		instance.add(Calendar.YEAR, -1970);
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        return Integer.parseInt(sdf.format(instance.getTime()));
	}
	
	private SystemUserCard setUserCardId(String cardId){
		SystemUserCard card = new SystemUserCard();
		card.setCardId(cardId);
		return card;
	}

}
