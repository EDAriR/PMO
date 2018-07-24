package com.syntrontech.pmo.syncare1.model;

import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the system_user database table.
 * 
 */
@Entity
@Table(name = "system_user")
@NamedQuery(name = "SystemUserJDBC.findAll", query = "SELECT s FROM SystemUserJDBC s")
public class SystemUser{
	public enum SystemUserPmoStatus {
		NotSync, Sync, Error, Internal
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "STATE_ID")
	private String stateId = "A";
	
	@Column(name = "USER_ACCOUNT")
	private String userAccount;
	
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	
	@Column(name = "CARD_ID")
	private String cardId;
	
	@Column(name = "USER_GENDER")
	private String userGender = "M";

	@Column(name = "USER_BIRTHDAY")
	private Timestamp userBirthday;
	
	@Column(name = "age")
	private int age;

	@Column(name = "USER_ADDRESS")
	private String userAddress;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "USER_PHONE")
	private String userPhone;
	
	@Column(name = "USER_MOBILE")
	private String userMobile;

	@Column(name = "USER_DISPLAY_NAME")
	private String userDisplayName;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime = new Timestamp(new Date().getTime());

	@Column(name = "MEMO")
	private String memo;
	
	@Column(name = "PICASA_ACCOUNT")
	private String picasaAccount;
	
	@Column(name = "ALBUM_NAME")
	private String albumName;

	@Column(name = "ALBUM_TYPE")
	private int albumType;
	
	@Column(name = "ADVERTISMENT_STATUS")
	private int advertismentStatus;
	
	@Column(name = "pmo_status")
	@Enumerated(EnumType.STRING)
	private SystemUserPmoStatus pmoStatus = SystemUserPmoStatus.Internal;

	@Column(name = "sex")
	@Enumerated(EnumType.STRING)
	private Sex sex = Sex.female;

	@Column(name = "alert")
	@Enumerated(EnumType.STRING)
	private YN alert = YN.N;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area")
	private Area area;

	@Column(name = "with_high_blood_pressure")
	@Enumerated(EnumType.STRING)
	private YN withHighBloodPressure = YN.N;

	@Column(name = "pmo_PASSWORD")
	private String pmoPassword;

	@Column(name = "case_status")
	private int caseStatus;

	@Column(name = "case_status_display")
	private int caseStatusDisplay;

	@Column(name = "case_note")
	private String caseNote;

	@Column(name = "case_update_date")
	private Timestamp caseUpdateDate = new Timestamp(new Date().getTime());
	
	@Column(name = "alert_notifier_name")
	private String alertNotifierName;
	
	@Column(name = "alert_notifier_mobile_phone")
	private String alertNotifierMobilePhone;
	
	@Column(name = "alert_notifier_email")
	private String alertNotifierEmail;

	@Column(name = "with_diabetes_mellitus")
	@Enumerated(EnumType.STRING)
	private YN withDiabetesMellitus = YN.N;
	
	/* 2017/03 台東新案新增  start*/
	@Column(name = "ethnicity")
	private int ethnicity;
	
	@Column(name = "with_heart_attack")
	@Enumerated(EnumType.STRING)
	private YN withHeartAttack = YN.N;
	
	@Column(name = "with_brain_attack")
	@Enumerated(EnumType.STRING)
	private YN withBrainAttack = YN.N;
	
	@Column(name = "family_with_heart_attack")
	@Enumerated(EnumType.STRING)
	private YN familyWithHeartAttack = YN.N;
	
	@Column(name = "family_with_brain_attack")
	@Enumerated(EnumType.STRING)
	private YN familyWithBrainAttack = YN.N;
	
	@Column(name = "family_with_high_blood_pressure")
	@Enumerated(EnumType.STRING)
	private YN familyWithHighBloodPressure = YN.N;
	
	@Column(name = "family_with_diabetes_mellitus")
	@Enumerated(EnumType.STRING)
	private YN familyWithDiabetesMellitus = YN.N;

	@Column(name = "frequency_of_smoking")
	private int frequencyOfSmoking;
	
	@Column(name = "frequency_of_drinking")
	private int frequencyOfDrinking;
	
	@Column(name = "frequency_of_chewing_betel_nut")
	private int frequencyOfChewingBetelNut;
	
	@Column(name = "health_information_notify")
	@Enumerated(EnumType.STRING)
	private YN healthInformationNotify = YN.N;
	
	@Column(name = "recluse")
	@Enumerated(EnumType.STRING)
	private YN recluse = YN.N;
	
	@Column(name = "iHealth_service")
	@Enumerated(EnumType.STRING)
	private YN iHealthService = YN.N;
	/* 2017/03 台東新案新增  end*/
	
	
	
	// bi-directional many-to-one association to UserValueRecord
	@OneToMany(mappedBy = "systemUser", fetch = FetchType.LAZY)
	private List<UserValueRecord> userValueRecords = new ArrayList<>();

	@OneToMany(mappedBy = "systemUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SystemUserCard> cards = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<SynCareQuestionnaireAnswers> questionnaireAnswers = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserRole> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserFunction> functions = new ArrayList<>();

	
	
	public SystemUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStateId() {
		return this.stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender.substring(0, 1);
	}
	
	public Timestamp getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Timestamp userBirthday) {
		this.userBirthday = userBirthday;
	}
	
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getUserAddress() {
		return this.userAddress;
	}
	
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	public String getUserDisplayName() {
		return this.userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getPicasaAccount() {
		return this.picasaAccount;
	}

	public void setPicasaAccount(String picasaAccount) {
		this.picasaAccount = picasaAccount;
	}
	
	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public int getAlbumType() {
		return this.albumType;
	}

	public void setAlbumType(int albumType) {
		this.albumType = albumType;
	}
	
	public int getAdvertismentStatus() {
		return this.advertismentStatus;
	}

	public void setAdvertismentStatus(int advertismentStatus) {
		this.advertismentStatus = advertismentStatus;
	}
	
	public SystemUserPmoStatus getPmoStatus() {
		return pmoStatus;
	}

	public void setPmoStatus(SystemUserPmoStatus pmoStatus) {
		this.pmoStatus = pmoStatus;
	}
	
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public YN getAlert() {
		return alert;
	}
	
	public void setAlert(YN alert) {
		this.alert = alert;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public YN getWithHighBloodPressure() {
		return withHighBloodPressure;
	}
	
	public void setWithHighBloodPressure(YN withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}
	
	public String getPmoPassword() {
		return pmoPassword;
	}

	public void setPmoPassword(String pmoPassword) {
		this.pmoPassword = pmoPassword;
	}
	
	public int getCaseStatusDisplay() {
		return caseStatusDisplay;
	}

	public void setCaseStatusDisplay(int caseStatusDisplay) {
		this.caseStatusDisplay = caseStatusDisplay;
	}

	public int getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	public String getCaseNote() {
		return caseNote;
	}

	public void setCaseNote(String caseNote) {
		this.caseNote = caseNote;
	}

	public Timestamp getCaseUpdateDate() {
		return caseUpdateDate;
	}

	public void setCaseUpdateDate(Timestamp caseUpdateDate) {
		this.caseUpdateDate = caseUpdateDate;
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

	public YN getWithDiabetesMellitus() {
		return withDiabetesMellitus;
	}
	
	public void setWithDiabetesMellitus(YN withDiabetesMellitus) {
		this.withDiabetesMellitus = withDiabetesMellitus;
	}

	public int getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(int ethnicity) {
		this.ethnicity = ethnicity;
	}

	public YN getWithHeartAttack() {
		return withHeartAttack;
	}

	public void setWithHeartAttack(YN withHeartAttack) {
		this.withHeartAttack = withHeartAttack;
	}

	public YN getWithBrainAttack() {
		return withBrainAttack;
	}

	public void setWithBrainAttack(YN withBrainAttack) {
		this.withBrainAttack = withBrainAttack;
	}

	public YN getFamilyWithHeartAttack() {
		return familyWithHeartAttack;
	}

	public void setFamilyWithHeartAttack(YN familyWithHeartAttack) {
		this.familyWithHeartAttack = familyWithHeartAttack;
	}

	public YN getFamilyWithBrainAttack() {
		return familyWithBrainAttack;
	}

	public void setFamilyWithBrainAttack(YN familyWithBrainAttack) {
		this.familyWithBrainAttack = familyWithBrainAttack;
	}

	public YN getFamilyWithHighBloodPressure() {
		return familyWithHighBloodPressure;
	}

	public void setFamilyWithHighBloodPressure(YN familyWithHighBloodPressure) {
		this.familyWithHighBloodPressure = familyWithHighBloodPressure;
	}

	public YN getFamilyWithDiabetesMellitus() {
		return familyWithDiabetesMellitus;
	}

	public void setFamilyWithDiabetesMellitus(YN familyWithDiabetesMellitus) {
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

	public YN getHealthInformationNotify() {
		return healthInformationNotify;
	}

	public void setHealthInformationNotify(YN healthInformationNotify) {
		this.healthInformationNotify = healthInformationNotify;
	}
	
	public YN getRecluse() {
		return recluse;
	}

	public void setRecluse(YN recluse) {
		this.recluse = recluse;
	}
	
	
	public YN getIHealthService() {
		return iHealthService;
	}
	
	public void setIHealthService(YN iHealthService) {
		this.iHealthService = iHealthService;
	}
	
	public List<UserValueRecord> getUserValueRecords() {
		return this.userValueRecords;
	}

	public void setUserValueRecords(List<UserValueRecord> userValueRecords) {
		this.userValueRecords = userValueRecords;
	}

	public UserValueRecord addUserValueRecord(UserValueRecord userValueRecord) {
		getUserValueRecords().add(userValueRecord);
		userValueRecord.setSystemUser(this);

		return userValueRecord;
	}

	public UserValueRecord removeUserValueRecord(UserValueRecord userValueRecord) {
		getUserValueRecords().remove(userValueRecord);
		userValueRecord.setSystemUser(null);

		return userValueRecord;
	}

	public List<SystemUserCard> getCards() {
		return cards;
	}

	public void setCards(List<SystemUserCard> cards) {
		this.cards = cards;
	}

	public SystemUserCard addCard(SystemUserCard card) {
		getCards().add(card);
		card.setSystemUser(this);
		return card;
	}

	public SystemUserCard removeCard(SystemUserCard card) {
		getCards().remove(card);
		card.setSystemUser(null);
		return card;
	}

	public List<SynCareQuestionnaireAnswers> getQuestionnaireAnswers() {
		return questionnaireAnswers;
	}

	public void setQuestionnaireAnswers(List<SynCareQuestionnaireAnswers> questionnaireAnswers) {
		this.questionnaireAnswers = questionnaireAnswers;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public List<UserFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(List<UserFunction> functions) {
		this.functions = functions;
	}


	@Override
	public String toString() {
		return "SystemUserJDBC ["
				+ "userId=" + userId 
				+ ", createTime=" + createTime 
				+ ", caseUpdateDate=" + caseUpdateDate
				+ ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword
				+ ", userAddress=" + userAddress
				+ ", userBirthday=" + userBirthday
				+ ", age=" + age 
				+ ", userDisplayName=" + userDisplayName 
				+ ", userEmail=" + userEmail 
				+ ", sex=" + sex 
				+ ", userMobile=" + userMobile
				+ ", userPhone=" + userPhone 
				+ ", pmoStatus=" + pmoStatus 
				+ ", alert=" + alert 
				+ ", area=" + area 
				+ ", withHighBloodPressure=" + withHighBloodPressure 
				+ ", withDiabetesMellitus=" + withDiabetesMellitus 
				+ ", alertNotifierName=" + alertNotifierName 
				
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
				+ ", recluse=" + recluse
				+ ", iHealthService=" + iHealthService
				+ "]";
	}


}