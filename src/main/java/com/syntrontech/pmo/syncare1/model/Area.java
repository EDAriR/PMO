package com.syntrontech.pmo.syncare1.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name="area")
public class Area {
	private static final long serialVersionUID = -3575961371095808601L;

	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	@Column(name="sort")
	private int sort;
	
	@OneToMany(mappedBy="area")
	private List<SystemUser> users;

	public List<SystemUser> getUsers() {
		return users;
	}

	public void setUsers(List<SystemUser> users) {
		this.users = users;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public int getSort() {
		return sort;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Area [code=" + code + ", name=" + name + "]";
	}

}
