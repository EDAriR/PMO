package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.AreaTO;

@Entity
@Table(name="area")
public class Area implements ObjectConverter<AreaTO>, Serializable {
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

	@Override
	public AreaTO convert(boolean relation) {
		AreaTO to = new AreaTO();
		to.setCode(getCode());
		to.setName(getName());
		return to;
	}
}
