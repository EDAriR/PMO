package com.syntrontech.pmo.auth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//store in Postgres
@Entity
@Table(name = "password_list")
public class PasswordList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence", nullable = false)
	private Long sequence;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "password_updatetime", nullable = false)
	private Date passwordUpdateTime;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "user_id", nullable = false)
	private String userId;

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getPasswordUpdateTime() {
		return passwordUpdateTime;
	}

	public void setPasswordUpdateTime(Date passwordUpdateTime) {
		this.passwordUpdateTime = passwordUpdateTime;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {

		return "PasswordList={password:[" + password +
				"], passwordUpdateTime:[" + passwordUpdateTime +
				"], account:[" + account +
				"], userId:[" + userId + "]}";

	}
}
