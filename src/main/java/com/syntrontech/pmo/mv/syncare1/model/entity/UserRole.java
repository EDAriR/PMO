package com.syntrontech.pmo.mv.syncare1.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private SystemUser user;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	public int getId() {
		return id;
	}

	public SystemUser getUser() {
		return user;
	}

	public Role getRole() {
		return role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
