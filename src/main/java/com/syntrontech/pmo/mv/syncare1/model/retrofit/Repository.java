package com.syntrontech.pmo.mv.syncare1.model.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Keanu on 2015/3/2.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
	private String id;
	private String name = "My SynDriver - Home";
	private int count;
	private Date updatedDate;
	private String type = "syndriver";
	private List<Folder> folders = new ArrayList<Folder>();
	private List<File> files = new ArrayList<File>();

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
}
