package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.Date;

public class AttachmentVO {

	private int id;
	
	private String appType;
	
	private String ofileName;
	
	private String nfileName;
	
	private String filePath;
	
	private String fileExt;
	
	private long fileSize;
	
	private String contentType;
	
	private String status;

	private Date createDate;
	
	private String createUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
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

	@Override
	public String toString() {
		return "AttachmentVO [id=" + id + ", appType=" + appType + ", ofileName=" + ofileName + ", nfileName="
				+ nfileName + ", filePath=" + filePath + ", fileExt=" + fileExt + ", fileSize=" + fileSize
				+ ", contentType=" + contentType + ", status=" + status + ", createDate=" + createDate + ", createUser="
				+ createUser + "]";
	}
}