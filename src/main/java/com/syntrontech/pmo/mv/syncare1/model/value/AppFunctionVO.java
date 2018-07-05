package com.syntrontech.pmo.mv.syncare1.model.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AppFunctionVO {

	private String funcId;

	private String funcName;
	
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
		return "AppFunctionVO [funcId=" + funcId + ", funcName=" + funcName + ", funcOrder=" + funcOrder + "]";
	}
}