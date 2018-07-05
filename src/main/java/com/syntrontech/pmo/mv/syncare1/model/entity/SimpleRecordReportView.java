package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.common.YN;
import com.syntrontech.syncare.model.transfer.SimpleRecordReportViewTO;

/**
 * Created by Tanya on 2017/3/22.
 */
@Entity
@Table(name = "SIMPLE_RECORD_REPORT_VIEW")
public class SimpleRecordReportView implements ObjectConverter<SimpleRecordReportViewTO>, Serializable {
	private static final SimpleDateFormat SDF_DISPLAY = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	@Column(name = "id")
	private int id;
	
	@Column(name = "account_serial")
	private int accountSerial;
	
	@Column(name = "user_account")
	private String account;

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
	private String caseStatusDisplay;
	
	@Column(name = "case_status")
	private int caseStatus;

	@Column(name = "max_blood")
	private String maxBlood;

	@Column(name = "min_blood")
	private String minBlood;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "recluse")
	@Enumerated(EnumType.STRING)
	private YN recluse;
	
	@Column(name = "user_birthday")
	private Date userBirthday;
	
	@Column(name = "age")
	private Integer age;

	@Column(name = "user_register_date")
	private Date userRegisterDate;
	
	
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountSerial() {
		return accountSerial;
	}

	public void setAccountSerial(int accountSerial) {
		this.accountSerial = accountSerial;
	}

	public int getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public YN getRecluse() {
		return recluse;
	}

	public void setRecluse(YN recluse) {
		this.recluse = recluse;
	}

	public Date getUserBrithday() {
		return userBirthday;
	}

	public void setUserBrithday(Date userBrithday) {
		this.userBirthday = userBrithday;
	}

	
	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	@Override
	public SimpleRecordReportViewTO convert(boolean relation) {
		SimpleRecordReportViewTO simpleRecordReportViewTO = new SimpleRecordReportViewTO();
		simpleRecordReportViewTO.setAccount(account);
		simpleRecordReportViewTO.setWithHighBloodPressure(withHighBloodPressure);
		if (null != recordDate)
			simpleRecordReportViewTO.setRecordDate(SDF_DISPLAY.format(recordDate));
		if (null != uploadDate)
			simpleRecordReportViewTO.setRecordDate(SDF_DISPLAY.format(uploadDate));
		simpleRecordReportViewTO.setCaseStatusDisplay(caseStatusDisplay);
		simpleRecordReportViewTO.setMaxBlood(maxBlood);
		simpleRecordReportViewTO.setMinBlood(minBlood);
		simpleRecordReportViewTO.setCity(city);
		simpleRecordReportViewTO.setSex(sex);
		simpleRecordReportViewTO.setUserBrithday(userBirthday);
		simpleRecordReportViewTO.setRecluse(recluse);
		return simpleRecordReportViewTO;
	}

	@Override
	public String toString() {
		return "SimpleRecordReportViewTO [account=" + account + ", withHighBloodPressure=" + withHighBloodPressure
				+ ", recordDate=" + recordDate + ", uploadDate=" + uploadDate + ", caseStatusDisplay="
				+ caseStatusDisplay + ", maxBlood=" + maxBlood + ", minBlood=" + minBlood + ", city=" + city
				+ ", sex=" + sex + ", userBrithday=" + userBirthday +",recluse="+recluse+ "]";
	}
}
