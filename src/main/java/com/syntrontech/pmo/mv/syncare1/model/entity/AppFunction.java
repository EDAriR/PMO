package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.AppFunctionTO;

/**
 * The persistent class for the app_function database table.
 * 
 */
@Entity
@Table(name = "app_function")
@NamedQuery(name = "AppFunction.findAll", query = "SELECT l FROM AppFunction l")
public class AppFunction implements ObjectConverter<AppFunctionTO>, Serializable {
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

	@Override
	public AppFunctionTO convert(boolean relation) {
		AppFunctionTO to = new AppFunctionTO();
		to.setFuncId(getFuncId());
		to.setFuncName(getFuncName());
		to.setFuncOrder(getFuncOrder());
		return to;
	}
}