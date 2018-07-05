package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.syntrontech.syncare.model.common.Sex;
import com.syntrontech.syncare.model.common.SexTPCU;
import com.syntrontech.syncare.model.common.UserType;

/*
 * 介接城市科大人員資料
 */
@Entity
@Table(name = "view_syntrontech_usrinfo")
@NamedQuery(name = "UserInfoTPCUView.findAll", query = "SELECT r FROM UserInfoTPCUView r")
public class UserInfoTPCUView implements Serializable {
	private static final long serialVersionUID = 2648612536607264183L;

	@Id
	@Column(name = "std_id")
	private int id;

	@Column(name = "usr_type")
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column(name = "std_idno")
	private String idno;

	@Column(name = "std_name")
	private String userName;

	@Column(name = "std_sex")
	@Enumerated(EnumType.STRING)
	private SexTPCU sex;

	@Column(name = "std_birthday")
	private String birthday;

	@Column(name = "std_cntt_addr")
	private String address;

	@Column(name = "std_mobile")
	private String mobile;

	@Column(name = "std_tel")
	private String phone;

	@Column(name = "std_email")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SexTPCU getSex() {
		return sex;
	}

	public void setSex(SexTPCU sex) {
		this.sex = sex;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
