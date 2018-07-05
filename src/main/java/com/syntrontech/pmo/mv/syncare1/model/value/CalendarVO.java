package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CalendarVO	 {

	private int id;

	private String title;
	
	private Date startDate;
	
	private Date endDate;
	
	private String type;
	
	private String message;
	
	private String vmessage;
	
	private String attachment;
	
	private String sender;
	
	private String receiver;

	private String status;

	private Date createDate;
	
	private String createUser;
	
	private Date updateDate;
	
	private String updateUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVmessage() {
		return vmessage;
	}

	public void setVmessage(String vmessage) {
		this.vmessage = vmessage;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "CalendarVO [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", type=" + type + ", message=" + message + ", vmessage=" + vmessage + ", attachment=" + attachment
				+ ", sender=" + sender + ", receiver=" + receiver + ", status=" + status + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser + "]";
	}
}