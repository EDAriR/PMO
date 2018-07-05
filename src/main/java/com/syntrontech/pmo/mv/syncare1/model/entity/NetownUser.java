package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.syntrontech.syncare.annotation.Unique;
import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.common.Sex;
import com.syntrontech.syncare.model.common.YN;

/**
 * The persistent class for the system_user database table.
 * 
 */
@Entity
@Table(name = "netown_user")
@NamedQuery(name = "NetownUser.findAll", query = "SELECT s FROM NetownUser s")
public class NetownUser implements Serializable, ObjectConverter<SystemUser> {
	private static final String AREA_CODE = "10018";

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime = new Timestamp(new Date().getTime());

	@Column(name = "USER_ACCOUNT")
	@Unique
	private String userAccount;

	@Column(name = "USER_ADDRESS")
	private String userAddress;

	@Column(name = "USER_BIRTHDAY")
	private Timestamp userBirthday;

	@Column(name = "USER_DISPLAY_NAME")
	private String userDisplayName;

	@Column(name = "USER_GENDER")
	private String userGender = "M";

	@Column(name = "USER_PHONE")
	private String userPhone;

	public NetownUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Timestamp getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Timestamp userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserDisplayName() {
		return this.userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender.substring(0, 1);
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "NetownUser [userId=" + userId + ", createTime=" + createTime + ", userAccount=" + userAccount
				+ ", userAddress=" + userAddress + ", userBirthday=" + userBirthday + ", userDisplayName="
				+ userDisplayName + ", userPhone=" + userPhone + ", userGender=" + userGender + "]";
	}

	@Override
	public SystemUser convert(boolean relation) {
		SystemUser user = new SystemUser();
		user.setUserAccount(getUserAccount());
		user.setUserDisplayName(getUserDisplayName());
		if ("男".equals(getUserGender())) {
			user.setSex(Sex.male);
		} else {
			user.setSex(Sex.female);
		}
		user.setUserAddress(getUserAddress());
		if (null != getUserBirthday()) {
			user.setUserBirthday(new Timestamp(getUserBirthday().getTime()));
		} else {
			user.setUserBirthday(new Timestamp(new Date().getTime()));
		}
		user.setUserPhone(getUserPhone());
		user.setUserEmail("");
		user.setUserMobile(getUserPhone());
		user.setAlert(YN.Y);
		user.setCreateTime(new Timestamp(new Date().getTime()));
		user.setWithHighBloodPressure(YN.N);

		Area area = new Area();
		area.setCode(AREA_CODE);
		user.setArea(area);
		return user;
	}
}