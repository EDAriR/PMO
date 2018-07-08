package com.syntrontech.pmo.syncare1.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="function")
public class Function {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int no;
	
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="url_hash")
	private String urlHash;
	
	@Column(name="icon_class")
	private String iconClass;
	
	@Column(name="type")
	private String type;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent", insertable=false, updatable=false)
	private Function parent;
	
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY)
	private List<Function> functions;

	public int getNo() {
		return no;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrlHash() {
		return urlHash;
	}

	public String getIconClass() {
		return iconClass;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public Function getParent() {
		return parent;
	}
	
	public List<Function> getFunctions() {
		return functions;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrlHash(String urlHash) {
		this.urlHash = urlHash;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setParent(Function parent) {
		this.parent = parent;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

}
