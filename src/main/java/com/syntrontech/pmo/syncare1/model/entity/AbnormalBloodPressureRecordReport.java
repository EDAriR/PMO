package com.syntrontech.pmo.syncare1.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "abnormal_blood_pressure_record_report")
@NamedQuery(name = "AbnormalBloodPressureRecordReport.findAll", query = "SELECT a FROM AbnormalBloodPressureRecordReport a")
public class AbnormalBloodPressureRecordReport{

	private static final SimpleDateFormat SDF_DISPLAY = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_account")
	private String account;

	@Column(name = "with_high_blood_pressure")
	private String withHighBloodPressure;

	@Column(name = "city")
	private String city;

	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "upload_date")
	private Date uploadDate;

	@Column(name = "case_status_display")
	private String caseStatusDisplay;

	@Column(name = "token")
	private String token;

	@Column(name = "sex")
	private String sex;

	@Column(name = "age")
	private int age;

	@Column(name = "diff")
	private int diff;

	@Column(name = "start_range")
	private int startRange;

	@Column(name = "end_range")
	private int endRange;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getWithHighBloodPressure() {
		return withHighBloodPressure;
	}

	public void setWithHighBloodPressure(String withHighBloodPressure) {
		this.withHighBloodPressure = withHighBloodPressure;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getCaseStatusDisplay() {
		return caseStatusDisplay;
	}

	public void setCaseStatusDisplay(String caseStatusDisplay) {
		this.caseStatusDisplay = caseStatusDisplay;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public int getStartRange() {
		return startRange;
	}

	public void setStartRange(int startRange) {
		this.startRange = startRange;
	}

	public int getEndRange() {
		return endRange;
	}

	public void setEndRange(int endRange) {
		this.endRange = endRange;
	}


	@Override
	public String toString() {
		return "AbnormalBloodPressureRecordReport ["
				+ "account=" + account 
				+ ", age" + age 
				+ ", sex=" + sex 
				+ ", city=" + city 
				+ ", diff=" + diff 
				+ ", caseStatusDisplay=" + caseStatusDisplay 
				+ ", recordDate=" + recordDate 
				+ ", uploadDate=" + uploadDate 
				+ ", withHighBloodPressure=" + withHighBloodPressure
				+ ", startRange=" + startRange 
				+ ", endRange=" + endRange 
				+ ", token=" + token 
				+ "]";
	}

}
