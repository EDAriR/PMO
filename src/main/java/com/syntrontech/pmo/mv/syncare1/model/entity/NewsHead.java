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
import com.syntrontech.syncare.model.transfer.NewsHeadTO;

/**
 * The persistent class for the newsHead database table.
 * 
 */
@Entity
@Table(name = "news_head")
@NamedQuery(name = "NewsHead.findAll", query = "SELECT l FROM NewsHead l")
public class NewsHead implements ObjectConverter<NewsHeadTO>, Serializable {
	
	public enum NewsHeadStatus {
		PRIVATE, PUBLIC, DELETED
	}
	
	public enum NewsHeadType {
		DEFAULT("不分類");
		
		private String label;
		
		NewsHeadType(String label) {
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
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "content")
	private String content;

	@Column(name = "status")
	private NewsHeadStatus status;
	
	@Column(name = "type")
	private NewsHeadType type;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "chapter")
	private String chapter;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "create_user")
	private String createUser;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@Column(name = "update_user")
	private String updateUser;
	
	@Column(name = "head")
	private int head;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NewsHeadStatus getStatus() {
		return status;
	}

	public void setStatus(NewsHeadStatus status) {
		this.status = status;
	}

	public NewsHeadType getType() {
		return type;
	}

	public void setType(NewsHeadType type) {
		this.type = type;
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

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return "NewsHead [id=" + id + ", title=" + title + ", author=" + author + ", content=" + content + ", status="
				+ status + ", type=" + type + ", image=" + image + ", url=" + url + ", chapter=" + chapter
				+ ", createDate=" + createDate + ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + ", head=" + head + "]";
	}

	@Override
	public NewsHeadTO convert(boolean relation) {
		NewsHeadTO to = new NewsHeadTO();
		to.setId(getId());
		to.setAuthor(getAuthor());
		to.setTitle(getTitle());
		to.setContent(getContent());
		to.setStatus(getStatus().toString());
		to.setType(getType().toString());
		to.setImage(getImage());
		to.setUrl(getUrl());
		to.setChapter(getChapter());
		to.setCreateDate(getCreateDate());
		to.setCreateUser(getCreateUser());
		to.setUpdateDate(getUpdateDate());
		to.setUpdateUser(getUpdateUser());
		to.setHead(getHead());
		
		return to;
	}
}