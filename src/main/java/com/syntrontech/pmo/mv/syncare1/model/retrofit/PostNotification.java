package com.syntrontech.pmo.mv.syncare1.model.retrofit;

public class PostNotification {

	private String to;
	private NotificationBody data;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public NotificationBody getData() {
		return data;
	}

	public void setData(NotificationBody data) {
		this.data = data;
	}
}
