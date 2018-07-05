package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syntrontech.syncare.model.common.Sex;

@JsonIgnoreProperties(ignoreUnknown=true)
public class IcLoginVO implements Serializable {
	private static final long serialVersionUID = -5523184751089014659L;

	private String cardId;
	
	private String name;
	
	private String id;
	
	private Date birthday;
	
	private Sex sex;
	
	private String email;
	
	private String phone;
	
	private String tel;

	public String getCardId() {
		return cardId;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getTel() {
		return tel;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "IcLoginVO [cardId=" + cardId + ", name=" + name + ", id=" + id
				+ ", birthday=" + birthday + ", sex=" + sex + ", email="
				+ email + ", phone=" + phone + ", tel=" + tel + "]";
	}
}
