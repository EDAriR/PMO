package com.syntrontech.pmo.mv.syncare1.model.entity;

import com.syntrontech.pmo.mv.syncare1.model.common.Sex;
import com.syntrontech.pmo.mv.syncare1.model.common.YN;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
@Table(name="system_user")
@NamedQuery(name="SystemUser.findAll", query="SELECT s FROM SystemUser s")
public class SystemUser implements Serializable {
	public enum SystemUserPmoStatus{
		NotSync, Sync, Error, Internal
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;

	@Column(name="ADVERTISMENT_STATUS")
	private int advertismentStatus;

	@Column(name="ALBUM_NAME")
	private String albumName;

	@Column(name="ALBUM_TYPE")
	private int albumType;

	@Column(name="CARD_ID")
	private String cardId;

	@Column(name="CREATE_TIME")
	private Timestamp createTime = new Timestamp(new Date().getTime());

	@Column(name="MEMO")
	private String memo;

	@Column(name="PICASA_ACCOUNT")
	private String picasaAccount;

	@Column(name="STATE_ID")
	private String stateId = "A";

	@Column(name="USER_ACCOUNT")
	private String userAccount;

	@Column(name="USER_ADDRESS")
	private String userAddress;

	@Column(name="USER_BIRTHDAY")
	private Timestamp userBirthday;

	@Column(name = "age")
	private int age;

	@Column(name="USER_DISPLAY_NAME")
	private String userDisplayName;
	
	@Column(name="USER_PHOTO")
	private String userPhoto;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_GENDER")
	private String userGender = "M";
	
	@Column(name="sex")
	@Enumerated(EnumType.STRING)
	private Sex sex = Sex.female;

	@Column(name="USER_MOBILE")
	private String userMobile;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	@Column(name="USER_PHONE")
	private String userPhone;
	
	@Column(name="pmo_status")
	@Enumerated(EnumType.STRING)
	private SystemUserPmoStatus pmoStatus = SystemUserPmoStatus.Internal;
	
	@Column(name="pmo_PASSWORD")
	private String pmoPassword;
	
	@Column(name="alert")
	@Enumerated(EnumType.STRING)
	private YN alert = YN.N;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="area")
	private Area area;
	
	@Column(name = "with_high_blood_pressure")
	@Enumerated(EnumType.STRING)
	private YN withHighBloodPressure = YN.N;

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
	
	@Column(name = "i_health_service")
	@Enumerated(EnumType.STRING)
	private YN iHealthService = YN.N;
	/* 2017/03 台東新案新增  end*/

	//bi-directional many-to-one association to UserValueRecord
	@OneToMany(mappedBy="systemUser", fetch=FetchType.LAZY)
	private List<UserValueRecord> userValueRecords = new ArrayList<>();
	
	@OneToMany(mappedBy="systemUser", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<SystemUserCard> cards = new ArrayList<>();
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<SynCareQuestionnaireAnswers> questionnaireAnswers = new ArrayList<>();
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<UserRole> roles = new ArrayList<>();
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<UserFunction> functions = new ArrayList<>();

	public SystemUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAdvertismentStatus() {
		return this.advertismentStatus;
	}

	public void setAdvertismentStatus(int advertismentStatus) {
		this.advertismentStatus = advertismentStatus;
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

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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

	public String getUserAddress() {
		return this.userAddress;
	}
	
	public YN getWithHighBloodPressure() {
		return withHighBloodPressure;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Timestamp getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Timestamp userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserDisplayName() {
		return this.userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender.substring(0, 1);
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public SystemUserPmoStatus getPmoStatus() {
		return pmoStatus;
	}

	public void setPmoStatus(SystemUserPmoStatus pmoStatus) {
		this.pmoStatus = pmoStatus;
	}
	
	public String getPmoPassword() {
		return pmoPassword;
	}

	public void setPmoPassword(String pmoPassword) {
		this.pmoPassword = pmoPassword;
	}

	public YN getAlert() {
		return alert;
	}

	public void setAlert(YN alert) {
		this.alert = alert;
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
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public void setWithHighBloodPressure(YN withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}

	public SystemUserCard addCard(SystemUserCard card){
		getCards().add(card);
		card.setSystemUser(this);
		return card;
	}
	
	public SystemUserCard removeCard(SystemUserCard card){
		getCards().remove(card);
		card.setSystemUser(null);
		return card;
	}

	public List<SynCareQuestionnaireAnswers> getQuestionnaireAnswers() {
		return questionnaireAnswers;
	}

	public void setQuestionnaireAnswers(
			List<SynCareQuestionnaireAnswers> questionnaireAnswers) {
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
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}

	public int getCaseStatusDisplay() {
		return caseStatusDisplay;
	}

	public void setCaseStatusDisplay(int caseStatusDisplay) {
		this.caseStatusDisplay = caseStatusDisplay;
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

	public YN getiHealthService() {
		return iHealthService;
	}

	public void setiHealthService(YN iHealthService) {
		this.iHealthService = iHealthService;
	}

//	@Override
//	public String toString() {
//		return "SystemUser [userId=" + userId + ", createTime=" + createTime
//				+ ", userAccount=" + userAccount + ", userAddress="
//				+ userAddress + ", userBirthday=" + userBirthday
//				+ ", userDisplayName=" + userDisplayName + ", userEmail="
//				+ userEmail + ", sex=" + sex + ", userMobile=" + userMobile
//				+ ", userPhone=" + userPhone + ", pmoStatus=" + pmoStatus
//				+ ", alert=" + alert + ", area=" + area
//				+ ", withHighBloodPressure=" + withHighBloodPressure
//				+ ", cards=" + cards + "]";
//	}

//	@Override
//	public SystemUserTO convert(boolean relation) {
//		SystemUserTO to = new SystemUserTO();
//		to.setId(getUserId());
//		to.setCreateTime(new Date(getCreateTime().getTime()));
//		to.setAccount(getUserAccount());
//		to.setAddress(getUserAddress());
//		to.setBirthday(new Date(getUserBirthday().getTime()));
//		to.setName(getUserDisplayName());
//		to.setEmail(getUserEmail());
//		to.setSex(getSex().name());
//		to.setTel(getUserPhone());
//		to.setPhone(getUserMobile());
//		to.setPmoStatus(getPmoStatus().name());
//		to.setAlert(YN.Y.equals(getAlert()));
//		to.setWithHighBloodPressure(YN.Y.equals(getWithHighBloodPressure()));
//		to.setPicasaAccount(getPicasaAccount());
//		to.setPhoto(getUserPhoto());
//		
//		if(relation){
//			List<SystemUserCardTO> cards = utils.transferList(getCards());
//			to.setCard(cards);
//			to.setArea(getArea().convert(true));
//			List<String> filledQuestionnaires = getQuestionnaireAnswers().stream()
//				.map(answer -> answer.getQuestionnaire())
//				.collect(Collectors.toSet()).stream()
//					.map(questionnaire -> questionnaire.getName())
//					.collect(Collectors.toList());
//			to.setFilledQuestionnaire(filledQuestionnaires);
//			
//			List<FunctionSort> sort = new ArrayList<>();
//			sort.addAll(getFunctions());
//			sort.addAll(getRoles().stream()
//				.flatMap(role -> role.getRole().getFunctions().stream())
//				.collect(Collectors.toList()));
//			List<FunctionTO> functions = sort.stream()
//				.sorted((f1,f2) -> f1.getSort()>f2.getSort()?1:-1)
//				.map(fnSort -> fnSort.getFunction())
//				.filter(function -> "Enable".equals(function.getStatus()))
//				.distinct()
//				.map(function -> function.convert(true))
//				.collect(Collectors.toList());
//			to.setFunctionList(functions);
//			
//			List<RoleTO> roles = getRoles().stream()
//				.map(role -> role.getRole().convert(false))
//				.collect(Collectors.toList());
//			to.setRoles(roles);
//		}
//		return to;
//	}
	
	@Override
	public String toString() {
		return "SystemUser ["
				+ "userId=" + userId 
				+ ", createTime=" + createTime 
				+ ", userAccount=" + userAccount
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

//	@Override
//	public SystemUserTO convert(boolean relation) {
//		SystemUserTO to = new SystemUserTO();
//		to.setId(getUserId());
//		to.setCreateTime(new Date(getCreateTime().getTime()));
//		to.setAccount(getUserAccount());
//		to.setAddress(getUserAddress());
//		to.setBirthday(new Date(getUserBirthday().getTime()));
//		to.setAge(getAge());
//		to.setName(getUserDisplayName());
//		to.setEmail(getUserEmail());
//		to.setSex(getSex().name());
//		to.setTel(getUserPhone());
//		to.setPhone(getUserMobile());
//		to.setPmoStatus(getPmoStatus().name());
//		to.setAlert(YN.Y.equals(getAlert()));
//		to.setWithHighBloodPressure(YN.Y.equals(getWithHighBloodPressure()));
//		to.setWithDiabetesMellitus(YN.Y.equals(getWithDiabetesMellitus()));
//		to.setAlertNotifierName(getAlertNotifierName());;
//		to.setAlertNotifierMobilePhone(getAlertNotifierMobilePhone());
//		to.setAlertNotifierEmail(getAlertNotifierEmail());
//
//		/* 2017/03 台東新案新增 */
//		to.setEthnicity(getEthnicity());
//		to.setWithHeartAttack(YN.Y.equals(getWithHeartAttack()));
//		to.setWithBrainAttack(YN.Y.equals(getWithBrainAttack()));
//		to.setFamilyWithHeartAttack(YN.Y.equals(getFamilyWithHeartAttack()));
//		to.setFamilyWithBrainAttack(YN.Y.equals(getFamilyWithBrainAttack()));
//		to.setFamilyWithHighBloodPressure(YN.Y.equals(getFamilyWithHighBloodPressure()));
//		to.setFamilyWithDiabetesMellitus(YN.Y.equals(getFamilyWithDiabetesMellitus()));
//		to.setFrequencyOfSmoking(getFrequencyOfSmoking());
//		to.setFrequencyOfDrinking(getFrequencyOfDrinking());
//		to.setFrequencyOfChewingBetelNut(getFrequencyOfChewingBetelNut());
//		to.setHealthInformationNotify(YN.Y.equals(getHealthInformationNotify()));
//		to.setRecluse(YN.Y.equals(getRecluse()));
//		to.setiHealthService(YN.Y.equals(getiHealthService()));
//		/* 2017/03 台東新案新增 */
//
//		if (relation) {
//			List<SystemUserCardTO> cards = utils.transferList(getCards());
//			to.setCard(cards);
//			to.setArea(getArea().convert(true));
//			List<String> filledQuestionnaires = getQuestionnaireAnswers().stream()
//					.map(answer -> answer.getQuestionnaire()).collect(Collectors.toSet()).stream()
//					.map(questionnaire -> questionnaire.getName()).collect(Collectors.toList());
//			to.setFilledQuestionnaire(filledQuestionnaires);
//
//			List<FunctionSort> sort = new ArrayList<>();
//			sort.addAll(getFunctions());
//			sort.addAll(getRoles().stream().flatMap(role -> role.getRole().getFunctions().stream())
//					.collect(Collectors.toList()));
//			List<FunctionTO> functions = sort.stream().sorted((f1, f2) -> f1.getSort() > f2.getSort() ? 1 : -1)
//					.map(fnSort -> fnSort.getFunction()).filter(function -> "Enable".equals(function.getStatus()))
//					.distinct().map(function -> function.convert(true)).collect(Collectors.toList());
//			to.setFunctionList(functions);
//
//			List<RoleTO> roles = getRoles().stream().map(role -> role.getRole().convert(false))
//					.collect(Collectors.toList());
//			to.setRoles(roles);
//		}
//		return to;
//	}

}