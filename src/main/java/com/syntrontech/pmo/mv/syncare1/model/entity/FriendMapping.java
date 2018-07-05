package com.syntrontech.pmo.mv.syncare1.model.entity;

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

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.common.YN;
import com.syntrontech.syncare.model.transfer.FriendMappingTO;

/**
 * The persistent class for the friend_mapping database table.
 * 
 */
@Entity
@Table(name = "friend_mapping")
@NamedQuery(name = "FriendMapping.findAll", query = "SELECT l FROM FriendMapping l")
public class FriendMapping implements ObjectConverter<FriendMappingTO>, Serializable {
	private static final long serialVersionUID = 1L;

	public enum FriendMappingStatus {
		connected, waiting;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="inviter")
	private String inviter;
	
	@Column(name="invitee")
	private String invitee;

	@Column(name = "calendar_view")
	@Enumerated(EnumType.STRING)
	private YN calendarView;

	@Column(name = "calendar_edit")
	@Enumerated(EnumType.STRING)
	private YN calendarEdit;

	@Column(name = "healthinfo_view")
	@Enumerated(EnumType.STRING)
	private YN healthinfoView;

	@Column(name = "healthinfo_edit")
	@Enumerated(EnumType.STRING)
	private YN healthinfoEdit;

	@Column(name = "inviter_message")
	private String inviterMessage;

	@Column(name = "invitee_message")
	private String inviteeMessage;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private FriendMappingStatus status;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "create_user")
	private String createUser;

	@Column(name = "album_view")
	@Enumerated(EnumType.STRING)
	private YN albumView;

	@Column(name = "notify_view")
	@Enumerated(EnumType.STRING)
	private YN notifyView;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public YN getCalendarView() {
		return calendarView;
	}

	public void setCalendarView(YN calendarView) {
		this.calendarView = calendarView;
	}

	public YN getCalendarEdit() {
		return calendarEdit;
	}

	public void setCalendarEdit(YN calendarEdit) {
		this.calendarEdit = calendarEdit;
	}

	public YN getHealthinfoView() {
		return healthinfoView;
	}

	public void setHealthinfoView(YN healthinfoView) {
		this.healthinfoView = healthinfoView;
	}

	public YN getHealthinfoEdit() {
		return healthinfoEdit;
	}

	public void setHealthinfoEdit(YN healthinfoEdit) {
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

	public FriendMappingStatus getStatus() {
		return status;
	}

	public void setStatus(FriendMappingStatus status) {
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

	public YN getAlbumView() {
		return albumView;
	}

	public void setAlbumView(YN albumView) {
		this.albumView = albumView;
	}

	public YN getNotifyView() {
		return notifyView;
	}

	public void setNotifyView(YN notifyView) {
		this.notifyView = notifyView;
	}

	@Override
	public String toString() {
		return "FriendMapping [id=" + id + ", calendarView=" + calendarView + ", calendarEdit=" + calendarEdit
				+ ", healthinfoView=" + healthinfoView + ", healthinfoEdit=" + healthinfoEdit + ", inviterMessage="
				+ inviterMessage + ", inviteeMessage=" + inviteeMessage + ", status=" + status + ", createDate="
				+ createDate + ", createUser=" + createUser + "]";
	}

	@Override
	public FriendMappingTO convert(boolean relation) {
		FriendMappingTO to = new FriendMappingTO();
		to.setInviter(getInviter());
		to.setInvitee(getInvitee());
		to.setCalendarView(getCalendarView().toString());
		to.setCalendarEdit(getCalendarEdit().toString());
		to.setHealthinfoView(getHealthinfoView().toString());
		to.setHealthinfoEdit(getHealthinfoEdit().toString());
		to.setInviterMessage(getInviterMessage());
		to.setInviteeMessage(getInviteeMessage());
		to.setAlbumView(getAlbumView().toString());
		to.setNotifyView(getNotifyView().toString());
		to.setStatus(getStatus().toString());
		to.setCreateDate(getCreateDate());
		to.setCreateUser(getCreateUser());

		return to;
	}
}
