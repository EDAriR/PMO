package com.syntrontech.pmo.mv.syncare1.model.value;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.entity.UserCaseLog;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCaseLogVO implements ObjectConverter<UserCaseLog>, Serializable {
	private static final long serialVersionUID = 1L;

	private int userId;
	private int updateCaseStatus;
	private boolean withEnforce;
	private String caseNote;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUpdateCaseStatus() {
		return updateCaseStatus;
	}

	public void setUpdateCaseStatus(int updateCaseStatus) {
		this.updateCaseStatus = updateCaseStatus;
	}

	public boolean isWithEnforce() {
		return withEnforce;
	}

	public void setWithEnforce(boolean withEnforce) {
		this.withEnforce = withEnforce;
	}

	public String getCaseNote() {
		return caseNote;
	}

	public void setCaseNote(String caseNote) {
		this.caseNote = caseNote;
	}

	@Override
	public UserCaseLog convert(boolean relation) {
		UserCaseLog userCaseLog = new UserCaseLog();

		userCaseLog.setUserId(userId);
		userCaseLog.setCaseStatus(updateCaseStatus);
		userCaseLog.setCaseStatusDisplay(updateCaseStatus);
		userCaseLog.setUpateDate(new Date());
		userCaseLog.setCaseNote(caseNote);

		return userCaseLog;
	}
}
