package com.syntrontech.pmo.mv.syncare1.model.value;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NewsVO {
	private int id;

	private String title;
	
	private String author;
	
	private String content;

	private String status;
	
	private String type;
	
	private String image;
	
	private String url;
	
	private String chapter;
	
	private Date createDate;
	
	private String createUser;
	
	private Date updateDate;
	
	private String updateUser;
	
	private int head;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return "NewsVO [id=" + id + ", title=" + title + ", author=" + author + ", content=" + content + ", status="
				+ status + ", type=" + type + ", image=" + image + ", url=" + url + ", chapter=" + chapter
				+ ", createDate=" + createDate + ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + ", head=" + head + "]";
	}
}
