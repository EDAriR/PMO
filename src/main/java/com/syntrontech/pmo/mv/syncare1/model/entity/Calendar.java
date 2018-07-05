package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.CalendarTO;

/**
 * The persistent class for the calendar database table.
 * 
 */
@Entity
@Table(name = "calendar")
@NamedQuery(name = "Calendar", query = "SELECT l FROM Calendar l")
public class Calendar implements ObjectConverter<CalendarTO>, Serializable {
	
	public enum CalendarStatus {
		PRIVATE, PUBLIC, DELETED
	}
	
	public enum CalendarType {
		DEFAULT		("未知"), 
		OUT			("外出"), 
		SCHOOL		("上課"), 
		DOCTOR		("看醫生"), 
		MEDICINE	("吃藥"), 
		TRAVEL		("出遊"), 
		PARTY		("聚餐"),
		WAKEUP		("起床"),
		GATHERING	("聚會");
		
		private String label;
		
		CalendarType(String label) {
			this.label = label;
		}
		
		private String getLabel() {
			return this.label;
		}
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "type")
	private CalendarType type;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "vmessage")
	private String vmessage;
	
	@Column(name = "attachment")
	private String attachment;
	
	@Column(name = "sender")
	private String sender;
	
	@Column(name = "receiver")
	private String receiver;

	@Column(name = "status")
	private CalendarStatus status;

	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "create_user")
	private String createUser;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@Column(name = "update_user")
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

	public CalendarType getType() {
		return type;
	}

	public void setType(CalendarType type) {
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

	public CalendarStatus getStatus() {
		return status;
	}

	public void setStatus(CalendarStatus status) {
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
		return "Calendar [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", type=" + type + ", message=" + message + ", vmessage=" + vmessage + ", attachment=" + attachment
				+ ", sender=" + sender + ", receiver=" + receiver + ", status=" + status + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser + "]";
	}

	@Override
	public CalendarTO convert(boolean relation) {
		CalendarTO to = new CalendarTO();
		to.setId(getId());
		to.setTitle(getTitle());
		to.setStartDate(getStartDate());
		to.setEndDate(getEndDate());
		to.setType(getType().toString());
		to.setMessage(getMessage());
		to.setVmessage(getVmessage());
		to.setAttachment(getAttachment());
		to.setSender(getSender());
		to.setReceiver(getReceiver());
		to.setStatus(getStatus().toString());
		to.setCreateDate(getCreateDate());
		to.setCreateUser(getCreateUser());
		to.setUpdateDate(getUpdateDate());
		to.setUpdateUser(getUpdateUser());
		return to;
	}
}