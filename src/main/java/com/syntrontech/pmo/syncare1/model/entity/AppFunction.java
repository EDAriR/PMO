package com.syntrontech.pmo.syncare1.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the app_function database table.
 * 
 */
@Entity
@Table(name = "app_function")
@NamedQuery(name = "AppFunction.findAll", query = "SELECT l FROM AppFunction l")
public class AppFunction {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "func_id")
	private String funcId;

	@Column(name = "func_name")
	private String funcName;
	
	@Column(name = "func_order")
	private Integer funcOrder;

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public Integer getFuncOrder() {
		return funcOrder;
	}

	public void setFuncOrder(Integer funcOrder) {
		this.funcOrder = funcOrder;
	}

	@Override
	public String toString() {
		return "AppFunction [funcId=" + funcId + ", funcName=" + funcName + ", funcOrder=" + funcOrder + "]";
	}

}