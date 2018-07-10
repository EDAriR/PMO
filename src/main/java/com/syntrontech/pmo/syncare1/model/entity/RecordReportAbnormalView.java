package com.syntrontech.pmo.syncare1.model.entity;

import com.syntrontech.pmo.syncare1.model.common.Sex;
import com.syntrontech.pmo.syncare1.model.common.YN;

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


@Entity
@Table(name = "RECORD_REPORT_ABNORMAL_VIEW")
@NamedQuery(name = "RecordReportAbnormalView.findAll", query = "SELECT r FROM RecordReportAbnormalView r")
public class RecordReportAbnormalView {
	private static final SimpleDateFormat SDF_DISPLAY = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private static final long serialVersionUID = -704834825969610057L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "user_account")
	private String account;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_display_name")
	private String username;

	@Column(name = "user_birthday")
	private String birthday;

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
	private int age;

	@Column(name = "with_high_blood_pressure")
	@Enumerated(EnumType.STRING)
	private YN withHighBloodPressure;

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
	private int caseStatus;

	@Column(name = "case_status_display")
	private int caseStatusDisplay;

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

	@Column(name = "bloodGlucose")
	private String bloodGlucose;

	public int getId() {
		return id;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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

	public int getAge() {
		return age;
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

	public void setAge(int age) {
		this.age = age;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSyntronLocationId() {
		return syntronLocationId;
	}

	public void setSyntronLocationId(String syntronLocationId) {
		this.syntronLocationId = syntronLocationId;
	}

	@Override
	public String toString() {
		return "RecordReportView [id=" + id + ", account=" + account + ", username=" + username + ", sex=" + sex
				+ ", age=" + age + ", withHighBloodPressure=" + withHighBloodPressure + ", locationId=" + locationId
				+ ", locationName=" + locationName + ", city=" + city + ", recordDate=" + recordDate + ", uploadDate="
				+ uploadDate + ", type=" + type + ", height=" + height + ", weight=" + weight + ", bmi=" + bmi
				+ ", maxBlood=" + maxBlood + ", minBlood=" + minBlood + ", heart=" + heart + ", bloodGlucose="
				+ bloodGlucose + ", caseStatus=" + caseStatus + ", caseStatusDisplay=" + caseStatusDisplay + "]";
	}

}
