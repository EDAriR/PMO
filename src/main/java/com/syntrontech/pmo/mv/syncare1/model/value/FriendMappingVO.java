package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.Date;

public class FriendMappingVO {
	private String inviter;

	private String invitee;

	private String calendarView;

	private String calendarEdit;

	private String healthinfoView;

	private String healthinfoEdit;

	private String inviterMessage;

	private String inviteeMessage;

	private String status;

	private Date createDate;

	private String createUser;

	private String albumView;

	private String notifyView;

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getInvitee() {
		return invitee;
	}

	public void setInvitee(String invitee) {
		this.invitee = invitee;
	}

	public String getCalendarView() {
		return calendarView;
	}

	public void setCalendarView(String calendarView) {
		this.calendarView = calendarView;
	}

	public String getCalendarEdit() {
		return calendarEdit;
	}

	public void setCalendarEdit(String calendarEdit) {
		this.calendarEdit = calendarEdit;
	}

	public String getHealthinfoView() {
		return healthinfoView;
	}

	public void setHealthinfoView(String healthinfoView) {
		this.healthinfoView = healthinfoView;
	}

	public String getHealthinfoEdit() {
		return healthinfoEdit;
	}

	public void setHealthinfoEdit(String healthinfoEdit) {
		this.healthinfoEdit = healthinfoEdit;
	}

	public String getInviterMessage() {
		return inviterMessage;
	}

	public void setInviterMessage(String inviterMessage) {
		this.inviterMessage = inviterMessage;
	}

	public String getInviteeMessage() {
		return inviteeMessage;
	}

	public void setInviteeMessage(String inviteeMessage) {
		this.inviteeMessage = inviteeMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getAlbumView() {
		return albumView;
	}

	public void setAlbumView(String albumView) {
		this.albumView = albumView;
	}

	public String getNotifyView() {
		return notifyView;
	}

	public void setNotifyView(String notifyView) {
		this.notifyView = notifyView;
	}

	@Override
	public String toString() {
		return "FriendMappingVO [inviter=" + inviter + ", invitee=" + invitee + ", calendarView=" + calendarView
				+ ", calendarEdit=" + calendarEdit + ", healthinfoView=" + healthinfoView + ", healthinfoEdit="
				+ healthinfoEdit + ", inviterMessage=" + inviterMessage + ", inviteeMessage=" + inviteeMessage
				+ ", status=" + status + ", createDate=" + createDate + ", createUser=" + createUser + "]";
	}
}
