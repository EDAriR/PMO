package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HealthInfoVO {
	private int id;

	private String title;
	
	private String author;
	
	private String image;
	
	private String chapter;
	
	private String content;
	
	private String status;
	
	private String type;
	
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	@Override
	public String toString() {
		return "HealthInfoVO [id=" + id + ", title=" + title + ", author=" + author + ", image=" + image + ", chapter="
				+ chapter + ", content=" + content + ", status=" + status + ", type=" + type + ", createDate="
				+ createDate + ", createUser=" + createUser + ", updateDate=" + updateDate + ", updateUser="
				+ updateUser + "]";
	}
}