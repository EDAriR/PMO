package com.syntrontech.pmo.syncare1.model;

import com.syntrontech.pmo.syncare1.model.common.YN;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Created by Tanya on 2017/3/17.
 */
@Entity
@Table(name = "blood_pressure_record_report")
@NamedQuery(name = "BloodPressureRecordReport.findAll", query = "SELECT r FROM BloodPressureRecordReport r")
public class BloodPressureRecordReport{
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

	@Column(name = "max_blood")
	private String maxBlood;

	@Column(name = "min_blood")
	private String minBlood;

	@Column(name = "token")
	private String token;

	@Column(name = "sex")
	private String sex;

	@Column(name = "recluse")
	@Enumerated(EnumType.STRING)
	private YN recluse;

	@Column(name = "age")
	private int age;

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

	public String getMaxBlood() {
		return maxBlood;
	}

	public void setMaxBlood(String maxBlood) {
		this.maxBlood = maxBlood;
	}

	public String getMinBlood() {
		return minBlood;
	}

	public void setMinBlood(String minBlood) {
		this.minBlood = minBlood;
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

	@Override
	public String toString() {
		return "BloodPressureRecordReport ["
				+ "account=" + account 
				+ ", sex=" + sex 
				+ ", age" + age 
				+ ", city=" + city
				+ ", caseStatusDisplay=" + caseStatusDisplay 
				+ ", maxBlood=" + maxBlood 
				+ ", minBlood=" + minBlood 
				+ ", withHighBloodPressure=" + withHighBloodPressure
				+ ", recluse=" + recluse 
				+ ", recordDate=" + recordDate 
				+ ", uploadDate=" + uploadDate 
				+ ", token=" + token 
				+ "]";
	}
}
