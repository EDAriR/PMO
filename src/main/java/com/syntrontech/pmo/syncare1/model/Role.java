package com.syntrontech.pmo.syncare1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY)
	private List<RoleFunction> functions;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<RoleFunction> getFunctions() {
		return functions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFunctions(List<RoleFunction> functions) {
		this.functions = functions;
	}

}
