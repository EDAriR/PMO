package com.syntrontech.pmo.syncare1.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the todo database table.
 * 
 */
@Entity
@Table(name = "todo")
@NamedQuery(name = "Todo.findAll", query = "SELECT l FROM Todo l")
public class Todo {
	
	public enum TodoStatus {
		PRIVATE, PUBLIC, DELETED
	}
	
	public enum TodoType {
		LOW("低"), NORMAL("中"), HIGH("高"), URGENT("緊急"); 
		
		private String label;
		
		TodoType(String label) {
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
	private TodoStatus status;
	
	@Column(name = "type")
	private TodoType type;
	
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
	
	@Column(name = "assign_to_user")
	private String assignToUser;
	
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

	public TodoStatus getStatus() {
		return status;
	}

	public void setStatus(TodoStatus status) {
		this.status = status;
	}

	public TodoType getType() {
		return type;
	}

	public void setType(TodoType type) {
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

	public String getAssignToUser() {
		return assignToUser;
	}

	public void setAssignToUser(String assignToUser) {
		this.assignToUser = assignToUser;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", author=" + author + ", content=" + content + ", status="
				+ status + ", type=" + type + ", image=" + image + ", chapter=" + chapter + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser
				+ ", assignToUser=" + assignToUser + "]";
	}
}