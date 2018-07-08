package com.syntrontech.pmo.syncare1.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SIMPLE_ABNORMAL_VIEW")
@NamedQuery(name = "SimpleAbnormalView.findAll", query = "SELECT s FROM SimpleAbnormalView s")
public class SimpleAbnormalView {
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	private int id;

	@Column(name = "user_account")
	private String account;

	@Column(name = "name")
	private String username;

	@Column(name = "account_serial")
	private int accountSerial;

	@Column(name = "with_high_blood_pressure")
	private String withHighBloodPressure;

	@Column(name = "city")
	private String city;

	@Id
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "upload_date")
	private Date uploadDate;

	@Column(name = "case_status_display")
	private int caseStatusDisplay;

	@Column(name = "case_status")
	private int caseStatus;

	@Column(name = "sex")
	private String sex;

	@Column(name = "recluse")
	private String recluse;

	@Column(name = "user_birthday")
	private Date birthday;

	@Column(name = "age")
	private Integer age;

	@Column(name = "max_blood")
	private String maxBlood;

	@Column(name = "min_blood")
	private String minBlood;

	@Column(name = "heart")
	private String heart;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountSerial() {
		return accountSerial;
	}

	public void setAccountSerial(int accountSerial) {
		this.accountSerial = accountSerial;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRecluse() {
		return recluse;
	}

	public void setRecluse(String recluse) {
		this.recluse = recluse;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}


}
