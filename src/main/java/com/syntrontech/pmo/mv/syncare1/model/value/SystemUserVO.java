package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syntrontech.syncare.model.common.Sex;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SystemUserVO implements Serializable {
	private static final long serialVersionUID = -2624455449816583621L;

	private List<SystemUserCardVO> card = new ArrayList<>();
	
	private String email;
	
	private String name;
	
	private String address;
	
	private Date birthday;
	
	private Sex sex;
	
	private String tel;
	
	private String phone;
	
	private boolean alert;
	
	private boolean withHighBloodPressure;
	
	private String picasaAccount;

	private String photo;
	
	private boolean withDiabetesMellitus;
	
	private String alertNotifierName;
	
	private String alertNotifierMobilePhone;
	
	private String alertNotifierEmail;
	
	private int ethnicity ;
	
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
	
	private boolean recluse;
	
	private boolean iHealthService;
	
	// 台東新案加入, 公版不需要
	private String token;
	
	private int memberId;

	public List<SystemUserCardVO> getCard() {
		return card;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public String getTel() {
		return tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setCard(List<SystemUserCardVO> card) {
		this.card = card;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	public boolean isWithHighBloodPressure() {
		return withHighBloodPressure;
	}

	public void setWithHighBloodPressure(boolean withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}

	public String getPicasaAccount() {
		return picasaAccount;
	}

	public void setPicasaAccount(String picasaAccount) {
		this.picasaAccount = picasaAccount;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isWithDiabetesMellitus() {
		return withDiabetesMellitus;
	}

	public void setWithDiabetesMellitus(boolean withDiabetesMellitus) {
		this.withDiabetesMellitus = withDiabetesMellitus;
	}

	public String getAlertNotifierName() {
		return alertNotifierName;
	}

	public void setAlertNotifierName(String alertNotifierName) {
		this.alertNotifierName = alertNotifierName;
	}

	public String getAlertNotifierMobilePhone() {
		return alertNotifierMobilePhone;
	}

	public void setAlertNotifierMobilePhone(String alertNotifierMobilePhone) {
		this.alertNotifierMobilePhone = alertNotifierMobilePhone;
	}

	public String getAlertNotifierEmail() {
		return alertNotifierEmail;
	}

	public void setAlertNotifierEmail(String alertNotifierEmail) {
		this.alertNotifierEmail = alertNotifierEmail;
	}

	public int getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(int ethnicity) {
		this.ethnicity = ethnicity;
	}

	public boolean isWithHeartAttack() {
		return withHeartAttack;
	}

	public void setWithHeartAttack(boolean withHeartAttack) {
		this.withHeartAttack = withHeartAttack;
	}

	public boolean isWithBrainAttack() {
		return withBrainAttack;
	}

	public void setWithBrainAttack(boolean withBrainAttack) {
		this.withBrainAttack = withBrainAttack;
	}

	public boolean isFamilyWithHeartAttack() {
		return familyWithHeartAttack;
	}

	public void setFamilyWithHeartAttack(boolean familyWithHeartAttack) {
		this.familyWithHeartAttack = familyWithHeartAttack;
	}

	public boolean isFamilyWithBrainAttack() {
		return familyWithBrainAttack;
	}

	public void setFamilyWithBrainAttack(boolean familyWithBrainAttack) {
		this.familyWithBrainAttack = familyWithBrainAttack;
	}

	public boolean isFamilyWithHighBloodPressure() {
		return familyWithHighBloodPressure;
	}

	public void setFamilyWithHighBloodPressure(boolean familyWithHighBloodPressure) {
		this.familyWithHighBloodPressure = familyWithHighBloodPressure;
	}

	public boolean isFamilyWithDiabetesMellitus() {
		return familyWithDiabetesMellitus;
	}

	public void setFamilyWithDiabetesMellitus(boolean familyWithDiabetesMellitus) {
		this.familyWithDiabetesMellitus = familyWithDiabetesMellitus;
	}

	public int getFrequencyOfSmoking() {
		return frequencyOfSmoking;
	}

	public void setFrequencyOfSmoking(int frequencyOfSmoking) {
		this.frequencyOfSmoking = frequencyOfSmoking;
	}

	public int getFrequencyOfDrinking() {
		return frequencyOfDrinking;
	}

	public void setFrequencyOfDrinking(int frequencyOfDrinking) {
		this.frequencyOfDrinking = frequencyOfDrinking;
	}

	public int getFrequencyOfChewingBetelNut() {
		return frequencyOfChewingBetelNut;
	}

	public void setFrequencyOfChewingBetelNut(int frequencyOfChewingBetelNut) {
		this.frequencyOfChewingBetelNut = frequencyOfChewingBetelNut;
	}

	public boolean isHealthInformationNotify() {
		return healthInformationNotify;
	}

	public void setHealthInformationNotify(boolean healthInformationNotify) {
		this.healthInformationNotify = healthInformationNotify;
	}

	public boolean isRecluse() {
		return recluse;
	}

	public void setRecluse(boolean recluse) {
		this.recluse = recluse;
	}

	public boolean isiHealthService() {
		return iHealthService;
	}

	public void setiHealthService(boolean iHealthService) {
		this.iHealthService = iHealthService;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

//	@Override
//	public String toString() {
//		return "SystemUserVO [card=" + card + ", email=" + email + ", name=" + name + ", address=" + address
//				+ ", birthday=" + birthday + ", sex=" + sex + ", tel=" + tel + ", phone=" + phone + ", alert=" + alert 
//				+ ", withHighBloodPressure=" + withHighBloodPressure + ", picasaAccount=" + picasaAccount + ", photo="
//				+ photo + "]";
//	}
}
