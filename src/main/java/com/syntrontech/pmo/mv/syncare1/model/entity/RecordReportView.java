package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.common.Sex;
import com.syntrontech.syncare.model.common.YN;
import com.syntrontech.syncare.model.transfer.RecordReportViewTO;

@Entity
@Table(name = "RECORD_REPORT_VIEW")
@NamedQuery(name = "RecordReportView.findAll", query = "SELECT r FROM RecordReportView r")
public class RecordReportView implements ObjectConverter<RecordReportViewTO>, Serializable {
	private static final SimpleDateFormat SDF_DISPLAY = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private static final long serialVersionUID = 2648612536607264183L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_account")
	private String account;
	
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_display_name")
	private String username;

	@Column(name = "user_birthday")
	private Date birthday;

	@Column(name = "address")
	private String address;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "phone")
	private String phone;

	@Column(name = "sex")
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@Column(name = "age")
	private Integer age;

	//@Column(name = "withHighBloodPressure")
	//@Enumerated(EnumType.STRING)
	//private YN withHighBloodPressure;
	@Column(name = "alert_notifier_name")
	private String alertNotifierName;
	
	@Column(name = "alert_notifier_mobile_phone")
	private String alertNotifierMobilePhone;
	
	@Column(name = "alert_notifier_email")
	private String alertNotifierEmail;
	
	@Column(name = "alert")
	@Enumerated(EnumType.STRING)
	private YN alert;

	@Column(name = "serial_no")
	private String serialNo;
	
		@Column(name = "syntron_location_id")
	private String syntronLocationId;
	

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "location_name")
	private String locationName;

	@Column(name = "city")
	private String city;

	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "upload_date")
	private Date uploadDate;

	@Column(name = "case_status")
	private Integer caseStatus;

	@Column(name = "case_status_display")
	private Integer caseStatusDisplay;

	@Column(name = "type")
	private String type;

	@Column(name = "height")
	private String height;

	@Column(name = "weight")
	private String weight;

	@Column(name = "bmi")
	private String bmi;

	@Column(name = "max_blood")
	private String maxBlood;

	@Column(name = "min_blood")
	private String minBlood;

	@Column(name = "heart")
	private String heart;

	@Column(name = "blood_glucose")
	private String bloodGlucose;

	@Column(name = "ear_thermometer")
	private String earThermometer;

	@Column(name = "vf_value")
	private String vfValue;

	
	@Column(name = "hbA1c")
	private String hbA1c;
	
	@Column(name = "cholesterol")
	private String cholesterol;
	
	@Column(name = "triglycerides")
	private String triglycerides;
	
	@Column(name = "hdl")
	private String hdl;
	
	@Column(name = "ldl")
	private String ldl;
	
	@Column(name = "got")
	private String got;
	
	@Column(name = "gpt")
	private String gpt;
	
	@Column(name = "creatinine")
	private String creatinine;

	@Column(name = "ethnicity")
	private Integer ethnicity;

	@Column(name = "with_high_blood_pressure")
	@Enumerated(EnumType.STRING)
	private YN withHighBloodPressure;
	
	@Column(name = "with_diabetes_mellitus")
	@Enumerated(EnumType.STRING)
	private YN withDiabetesMellitus;
	
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
	private Integer frequencyOfSmoking;
	
	@Column(name = "frequency_of_drinking")
	private Integer frequencyOfDrinking;
	
	@Column(name = "frequency_of_chewing_betel_nut")
	private Integer frequencyOfChewingBetelNut;

	public int getId() {
		return id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public String getUsername() {
		return username;
	}

	public Sex getSex() {
		return sex;
	}

	public YN getWithHighBloodPressure() {
		return withHighBloodPressure;
	}

	public String getLocationId() {
		return locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getCity() {
		return city;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public String getType() {
		return type;
	}

	public String getHeight() {
		return height;
	}

	public String getWeight() {
		return weight;
	}

	public String getBmi() {
		return bmi;
	}

	public String getMaxBlood() {
		return maxBlood;
	}

	public String getMinBlood() {
		return minBlood;
	}

	public String getHeart() {
		return heart;
	}

	public String getBloodGlucose() {
		return bloodGlucose;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setWithHighBloodPressure(YN withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public void setMaxBlood(String maxBlood) {
		this.maxBlood = maxBlood;
	}

	public void setMinBlood(String minBlood) {
		this.minBlood = minBlood;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public void setBloodGlucose(String bloodGlucose) {
		this.bloodGlucose = bloodGlucose;
	}

	public YN getAlert() {
		return alert;
	}

	public void setAlert(YN alert) {
		this.alert = alert;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getEarThermometer() {
		return earThermometer;
	}

	public void setEarThermometer(String earThermometer) {
		this.earThermometer = earThermometer;
	}

	public String getVfValue() {
		return vfValue;
	}

	public void setVfValue(String vfValue) {
		this.vfValue = vfValue;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getSyntronLocationId() {
		return syntronLocationId;
	}

	public void setSyntronLocationId(String syntronLocationId) {
		this.syntronLocationId = syntronLocationId;
	}

	public Integer getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(Integer caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Integer getCaseStatusDisplay() {
		return caseStatusDisplay;
	}

	public void setCaseStatusDisplay(Integer caseStatusDisplay) {
		this.caseStatusDisplay = caseStatusDisplay;
	}

	public String getHbA1c() {
		return hbA1c;
	}

	public void setHbA1c(String hbA1c) {
		this.hbA1c = hbA1c;
	}

	public String getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getTriglycerides() {
		return triglycerides;
	}

	public void setTriglycerides(String triglycerides) {
		this.triglycerides = triglycerides;
	}

	public String getHdl() {
		return hdl;
	}

	public void setHdl(String hdl) {
		this.hdl = hdl;
	}

	public String getLdl() {
		return ldl;
	}

	public void setLdl(String ldl) {
		this.ldl = ldl;
	}

	public String getGot() {
		return got;
	}

	public void setGot(String got) {
		this.got = got;
	}

	public String getGpt() {
		return gpt;
	}

	public void setGpt(String gpt) {
		this.gpt = gpt;
	}

	public String getCreatinine() {
		return creatinine;
	}

	public void setCreatinine(String creatinine) {
		this.creatinine = creatinine;
	}

	public Integer getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(Integer ethnicity) {
		this.ethnicity = ethnicity;
	}

	public YN getWithDiabetesMellitus() {
		return withDiabetesMellitus;
	}

	public void setWithDiabetesMellitus(YN withDiabetesMellitus) {
		this.withDiabetesMellitus = withDiabetesMellitus;
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

	public Integer getFrequencyOfSmoking() {
		return frequencyOfSmoking;
	}

	public void setFrequencyOfSmoking(Integer frequencyOfSmoking) {
		this.frequencyOfSmoking = frequencyOfSmoking;
	}

	public Integer getFrequencyOfDrinking() {
		return frequencyOfDrinking;
	}

	public void setFrequencyOfDrinking(Integer frequencyOfDrinking) {
		this.frequencyOfDrinking = frequencyOfDrinking;
	}

	public Integer getFrequencyOfChewingBetelNut() {
		return frequencyOfChewingBetelNut;
	}

	public void setFrequencyOfChewingBetelNut(Integer frequencyOfChewingBetelNut) {
		this.frequencyOfChewingBetelNut = frequencyOfChewingBetelNut;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

//	@Override
//	public String toString() {
//		return "RecordReportView [id=" + id + ", account=" + account + ", username=" + username + ", sex=" + sex
//				+ ", age=" + age + ", withHighBloodPressure=" + withHighBloodPressure + ", locationId=" + locationId
//				+ ", locationName=" + locationName + ", city=" + city + ", recordDate=" + recordDate + ", uploadDate="
//				+ uploadDate + ", type=" + type + ", height=" + height + ", weight=" + weight + ", bmi=" + bmi
//				+ ", maxBlood=" + maxBlood + ", minBlood=" + minBlood + ", heart=" + heart + ", bloodGlucose="
//				+ bloodGlucose +", serialNo=" +serialNo + "]";
//	}
//
	
	@Override
	public String toString() {
		return "RecordReportView [id=" + id + ", account=" + account + ", username=" + username + ", sex=" + sex
				+ ", age=" + age + ", withHighBloodPressure=" + withHighBloodPressure + ", withDiabetesMellitus=" 
				+ withDiabetesMellitus + ", alertNotifierName=" 
				+ alertNotifierName + ", alertNotifierMobilePhone=" + alertNotifierMobilePhone + ", alertNotifierEmail=" 
				+ alertNotifierEmail + ", locationId=" + locationId + ", locationName=" + locationName + ", city=" + city 
				+ ", recordDate=" + recordDate + ", uploadDate=" + uploadDate + ", type=" + type + ", height=" + height 
				+ ", weight=" + weight + ", bmi=" + bmi + ", maxBlood=" + maxBlood + ", minBlood=" + minBlood 
				+ ", heart=" + heart + ", bloodGlucose=" + bloodGlucose + ", caseStatus=" + caseStatus + ", caseStatusDisplay=" 
				+ caseStatusDisplay + "]";
	}

	@Override
	public RecordReportViewTO convert(boolean relation) {
		RecordReportViewTO reportViewTO = new RecordReportViewTO();

		reportViewTO.setAccount(account);
		reportViewTO.setUserId(userId);
		reportViewTO.setCaseStatus(caseStatus);
		reportViewTO.setCaseStatusDisplay(caseStatusDisplay);
		reportViewTO.setHeart(heart);
		reportViewTO.setId(id);
		reportViewTO.setMaxBlood(maxBlood);
		reportViewTO.setMinBlood(minBlood);
		if (null != recordDate) {
			reportViewTO.setRecordDate(SDF_DISPLAY.format(recordDate));
		}
		reportViewTO.setUsername(username);

		return reportViewTO;
	}

}
