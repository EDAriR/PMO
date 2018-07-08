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
@Table(name="user_function")
public class UserFunction implements FunctionSort {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private SystemUser user;

	@ManyToOne
	@JoinColumn(name="function_id")
	private Function function;

	@Column(name="sort")
	private int sort;

	public int getId() {
		return id;
	}

	public SystemUser getUser() {
		return user;
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

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
