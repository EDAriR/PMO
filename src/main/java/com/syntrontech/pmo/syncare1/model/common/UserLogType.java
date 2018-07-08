package com.syntrontech.pmo.syncare1.model.common;

public enum UserLogType {
	USER_LOGIN("USER_LOGIN"), IC_LOGIN("IC_LOGIN"), RFID_LOGIN("RFID_LOGIN"), USER_REGISTER(
			"USER_REGISTER"), VITAL_SAVE("VITAL_SAVE"), USER_LOGOUT("USER_LOGOUT");

	private String label;

	UserLogType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
