package com.syntrontech.pmo.pmo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntrontech.syncare.model.common.YN;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@JsonIgnoreProperties
public class UserData {
	@JsonProperty("IDNo")
	private String idno;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("IDENTIFIER")
	private String identifier;
	
	//
	@JsonProperty("IDENTIFIER2")
	private String identifier2;
	
	//
	@JsonProperty("IDENTIFIER3")
	private String identifier3;
	
	@JsonProperty("eMail")
	private String email;
	
	@JsonProperty("Sex")
	private String sex;
	
	@JsonProperty("Tel")
	private String tel;
	
	@JsonProperty("AreaCode")
	private String areaCode;
	
	@JsonProperty("Birth")
	private String birth;
	
	//
	@JsonProperty("Name")
	private String name;
	
	//
	@JsonProperty("Address")
	private String address;
	
	//
	@JsonProperty("Type")
	private String type;
	
	//
	@JsonProperty("Group")
	private String group;

	//
	@JsonProperty("MobilePhone")
	private String mobilePhone;
	
	//
	@JsonProperty("isHTN")
	@Enumerated(EnumType.STRING)
	private YN isHTN = YN.N;
	
	//
	@JsonProperty("isDM")
	@Enumerated(EnumType.STRING)
	private YN isDM = YN.N;
	
	//
	@JsonProperty("isAlert")
	@Enumerated(EnumType.STRING)
	private YN isAlert = YN.N;
	
	//
	@JsonProperty("AlertNotifierName")
	private String alertNotifierName;
	
	//
	@JsonProperty("AlertNotifierMobilePhone")
	private String alertNotifierMobilePhone;
	
	//
	@JsonProperty("AlertNotifierEmail")
	private String alertNotifierEmail;
	
	public UserData() {
	}

	@JsonCreator
	public UserData(
			@JsonProperty("IDNo") String idno,
			@JsonProperty("Password") String password,
			@JsonProperty("IDENTIFIER") String identifier,
			@JsonProperty("IDENTIFIER2") String identifier2,
			@JsonProperty("IDENTIFIER3") String identifier3,
			@JsonProperty("eMail") String email,
			@JsonProperty("Sex") String sex,
			@JsonProperty("Tel") String tel,
			@JsonProperty("AreaCode") String areaCode,
			@JsonProperty("Birth") String birth,
			@JsonProperty("Name") String name,
			@JsonProperty("Address") String address,
			@JsonProperty("Type") String type,
			@JsonProperty("Group") String group,
			@JsonProperty("MobilePhone") String mobilePhone,
			@JsonProperty("isHTN") YN isHTM,
			@JsonProperty("isDM") YN isDM,
			@JsonProperty("isAlert") YN isAlert,
			@JsonProperty("AlertNotifierName") String alertNotifierName,
			@JsonProperty("AlertNotifierMobilePhone") String alertNotifierMobilePhone,
			@JsonProperty("AlertNotifierEmail") String alertNotifierEmail) {
		this.idno = idno;
		this.password = password;
		this.identifier = identifier;
		this.identifier2 = identifier2;
		this.identifier3 = identifier3;
		this.email = email;
		this.sex = sex;
		this.tel = tel;
		this.areaCode = areaCode;
		this.birth = birth;
		this.name = name;
		this.address = address;
		this.type = type;
		this.group = group;
		this.mobilePhone = mobilePhone;
		this.isHTN = isHTM;
		this.isDM = isDM;
		this.isAlert = isAlert;
		this.alertNotifierName = alertNotifierName;
		this.alertNotifierMobilePhone = alertNotifierMobilePhone;
		this.alertNotifierEmail = alertNotifierEmail;
	}

	public String getIdno() {
		return idno;
	}

	public String getPassword() {
		return password;
	}

	public String getIdentifier() {
		return identifier;
	}
	
	public String getIdentifier2() {
		return identifier2;
	}
	
	public String getIdentifier3() {
		return identifier3;
	}

	public String getEmail() {
		return email;
	}

	public String getSex() {
		return sex;
	}

	public String getTel() {
		return tel;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getBirth() {
		return birth;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getType() {
		return type;
	}
	
	public String getGroup() {
		return group;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public YN isHTN() {
		return isHTN;
	}
	
	public YN isDM() {
		return isDM;
	}
	
	public YN isAlert() {
		return isAlert;
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
	
	public void setIdno(String idno) {
		this.idno = idno;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public void setIdentifier2(String identifier2) {
		this.identifier2 = identifier2;
	}
	
	public void setIdentifier3(String identifier3) {
		this.identifier3 = identifier3;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public void setIsHTM(YN isHTM) {
		this.isHTN = isHTM;
	}
	
	public void setIsDM(YN isDM) {
		this.isDM = isDM;
	}
	
	public void setIsAlert(YN isAlert) {
		this.isAlert = isAlert;
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
	
	@Override
	public String toString() {
		return "UserData [idno=" + idno + ", password=" + password
				+ ", identifier=" + identifier + ", identifier2=" + identifier2 + ", identifier3=" + identifier3
				+ ", email=" + email + ", sex=" + sex + ", tel=" + tel + ", areaCode=" + areaCode + ", birth="
				+ birth + ", name=" + name + ", address=" + address + ", type=" + type + ", group=" + group
				+ ", mobilePhone=" + mobilePhone + ", isHTM=" + isHTN + ", isDM=" + isDM + ", isAlert=" 
				+ isAlert + ", alertNotifierName=" + alertNotifierName + ", alertNotifierMobilePhone=" 
				+ alertNotifierMobilePhone + ", alertNotifierEmail=" + alertNotifierEmail + "]";
	}
}
