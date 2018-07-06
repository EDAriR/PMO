package com.syntrontech.pmo.cip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.syntrontech.cip.model.common.ModelStatus;

@Table
@Entity(name = "elder_living_alone")
public class ElderLivingAloneModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence")
	private Long sequence;
	
	@Column(name = "id", nullable = false)
	private String ID;

	@Column(name = "name")
	private String name;

	@Column(name = "unit_id")
	private String unitId;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "status")
	private ModelStatus status;
	
	@Column(name = "tenant_id")
	private String tenantId;
	
	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public ModelStatus getModelStatus() {
		return status;
	}

	public void setModelStatus(ModelStatus modelStatus) {
		this.status = modelStatus;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString(){
		return "ElderLivingAlone["
				+"ID="+ID+", "
				+"city="+city+", "
				+"name="+name+", "
				+"unitId="+unitId
				+"tenantId="+tenantId+", "
				+"modelStatus="+status+", "
				+"]";
	}

}
