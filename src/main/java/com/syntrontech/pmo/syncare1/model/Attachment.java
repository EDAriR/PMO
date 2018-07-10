package com.syntrontech.pmo.syncare1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the attachment database table.
 * 
 */
@Entity
@Table(name = "attachment")
@NamedQuery(name = "Attachment.findAll", query = "SELECT l FROM Attachment l")
public class Attachment{
	
	public enum AttachmentStatus {
		PRIVATE, PUBLIC, DELETED
	}
	
	public enum AppType {
		NONE("暫存"),
		PROFILE("個人檔案"),
		NEWS("最新消息"),
		HEALTHINFO("健康資訊"),
		TODO("待辦事項"),
		CALENDAR("行事曆");
		
		private String label;
		
		AppType(String label) {
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
	
	@Column(name = "app_type")
	private AppType appType;
	
	@Column(name = "ofile_name")
	private String ofileName;
	
	@Column(name = "nfile_name")
	private String nfileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "file_ext")
	private String fileExt;
	
	@Column(name = "file_size")
	private long fileSize;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Column(name = "status")
	private AttachmentStatus status;

	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "create_user")
	private String createUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AppType getAppType() {
		return appType;
	}

	public void setAppType(AppType appType) {
		this.appType = appType;
	}

	public String getOfileName() {
		return ofileName;
	}

	public void setOfileName(String ofileName) {
		this.ofileName = ofileName;
	}

	public String getNfileName() {
		return nfileName;
	}

	public void setNfileName(String nfileName) {
		this.nfileName = nfileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public AttachmentStatus getStatus() {
		return status;
	}

	public void setStatus(AttachmentStatus status) {
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

}