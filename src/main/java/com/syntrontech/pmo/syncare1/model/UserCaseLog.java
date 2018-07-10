package com.syntrontech.pmo.syncare1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_case_log")
public class UserCaseLog {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;

	@Column(name = "user_id")
	private int userId;
	@Column(name = "case_status")
	private int caseStatus;
	@Column(name = "case_status_display")
	private int caseStatusDisplay;
	@Column(name = "case_note")
	private String caseNote;
	@Column(name = "update_date")
	private Date upateDate;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}

	public int getCaseStatusDisplay() {
		return caseStatusDisplay;
	}

	public void setCaseStatusDisplay(int caseStatusDisplay) {
		this.caseStatusDisplay = caseStatusDisplay;
	}

	public String getCaseNote() {
		return caseNote;
	}

	public void setCaseNote(String caseNote) {
		this.caseNote = caseNote;
	}

	public Date getUpateDate() {
		return upateDate;
	}

	public void setUpateDate(Date upateDate) {
		this.upateDate = upateDate;
	}

	@Override
	public String toString() {
		return "UserCaseLog [logId=" + logId + ", userId=" + userId + ", caseStatus=" + caseStatus
				+ ", caseStatusDisplay=" + caseStatusDisplay + ", upateDate=" + upateDate.getTime() + "]";
	}

}
