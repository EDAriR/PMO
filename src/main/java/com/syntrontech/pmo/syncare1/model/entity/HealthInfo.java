package com.syntrontech.pmo.syncare1.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the news database table.
 * 
 */
@Entity
@Table(name = "health_info")
@NamedQuery(name = "HealthInfo.findAll", query = "SELECT l FROM HealthInfo l")
public class HealthInfo {
	
	public enum HealthInfoStatus {
		PRIVATE, PUBLIC, DELETED
	}
	
	public enum HealthInfoType {
		HEALTH("健康資訊"), SPORT("運動資訊"), VIDEO("影片");
		
		private String label;
		
		HealthInfoType(String label) {
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
	private HealthInfoStatus status;
	
	@Column(name = "type")
	private HealthInfoType type;
	
	@Column(name = "image")
	private String image;
	
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public HealthInfoStatus getStatus() {
		return status;
	}

	public void setStatus(HealthInfoStatus status) {
		this.status = status;
	}

	public HealthInfoType getType() {
		return type;
	}

	public void setType(HealthInfoType type) {
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
		return "HealthInfo [id=" + id + ", title=" + title + ", author=" + author + ", content=" + content + ", status="
				+ status + ", type=" + type + ", image=" + image + ", chapter=" + chapter + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser + "]";
	}

}