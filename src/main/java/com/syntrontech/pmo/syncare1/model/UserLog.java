package com.syntrontech.pmo.syncare1.model;

import com.syntrontech.pmo.syncare1.model.common.UserLogType;

import java.io.Serializable;
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
 * The persistent class for the news database table.
 * 
 */
@Entity
@Table(name = "log")
@NamedQuery(name = "UserLog.findAll", query = "SELECT l FROM UserLog l")
public class UserLog {
	private static final long serialVersionUID = 1L;

	public enum LOG_INFO {
		INFO("INFO"), WARN("WARN"), ERROR("ERROR");

		private String label;

		LOG_INFO(String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}
	}

	@Id
	@Column(name = "log_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "log_info")
	@Enumerated(EnumType.STRING)
	private LOG_INFO logInfo;

	@Column(name = "log_type")
	@Enumerated(EnumType.STRING)
	private UserLogType logType;

	@Column(name = "log_datetime")
	private Date logDateTime;

	@Column(name = "log_app_version")
	private String appVersion;

	@Column(name = "log_memo")
	private String logMemo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LOG_INFO getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LOG_INFO logInfo) {
		this.logInfo = logInfo;
	}

	public UserLogType getLogType() {
		return logType;
	}

	public void setLogType(UserLogType logType) {
		this.logType = logType;
	}

	public Date getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getLogMemo() {
		return logMemo;
	}

	public void setLogMemo(String logMemo) {
		this.logMemo = logMemo;
	}

}