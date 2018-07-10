package com.syntrontech.pmo.cip.externalService.auth.to;

public class UnitTO {
	
	private String unitId;
	
	private String unitName;
	
	private String parentUnitId;
	
	private String parentUnitName;
	
	private String status;
	
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getParentUnitId() {
		return parentUnitId;
	}

	public void setParentUnitId(String parentUnitId) {
		this.parentUnitId = parentUnitId;
	}

	public String getParentUnitName() {
		return parentUnitName;
	}

	public void setParentUnitName(String parentUnitName) {
		this.parentUnitName = parentUnitName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString(){
		return "UnitTO["
				+"unitId="+unitId+", "
				+"unitName="+unitName+", "
				+"parentUnitId="+parentUnitId+", "
				+"parentUnitName="+parentUnitName+", "
				+"status="+status
				+"]";
	}
}
