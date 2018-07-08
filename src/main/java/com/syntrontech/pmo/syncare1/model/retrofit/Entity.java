package com.syntrontech.pmo.syncare1.model.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Set;

/**
 * Created by Keanu on 2015/3/2.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
	private String id; // entryId
	private String name; // entryName
	private Date updatedDate; // entryUpdatedDate
	private Date createdDate; // entryCreatedDate
	// client side doesn't have to know the status. Ideally, they always get
	// 'Normal' entry.
	// private EntityStatus status; // entryIndexStatus;
	private Set<String> tags; // tags
	// user id should not be exposed to client
	// private String ownerId; // ownerId
	private String ownerEmail;
	private String ownerName;
	private String fullPath;
	private boolean isLink;
	private String sharingUrl;
	private String version;

	private String contentType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public boolean isLink() {
		return isLink;
	}

	public void setLink(boolean isLink) {
		this.isLink = isLink;
	}

	public String getSharingUrl() {
		return sharingUrl;
	}

	public void setSharingUrl(String sharingUrl) {
		this.sharingUrl = sharingUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
