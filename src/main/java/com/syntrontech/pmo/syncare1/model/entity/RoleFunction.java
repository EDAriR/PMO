package com.syntrontech.pmo.syncare1.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="role_function")
public class RoleFunction implements FunctionSort {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="function_id")
	private Function function;
	
	@Column(name="sort")
	private int sort;

	public int getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public Function getFunction() {
		return function;
	}

	public int getSort() {
		return sort;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
