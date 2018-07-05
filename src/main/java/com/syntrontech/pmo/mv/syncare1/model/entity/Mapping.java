package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.MappingTO;


/**
 * The persistent class for the mapping database table.
 * 
 */
@Entity
@Table(name="mapping")
@NamedQuery(name="Mapping.findAll", query="SELECT m FROM Mapping m")
public class Mapping implements ObjectConverter<MappingTO>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TYPE_ID")
	private int typeId;

	@Column(name="COLUMN_TYPE")
	private String columnType;

	@Column(name="MEMO")
	private String memo;

	@Column(name="TYPE_NAME")
	private String typeName;

	/*
	//bi-directional many-to-one association to HealthInformation
	@OneToMany(mappedBy="mapping1")
	private List<HealthInformation> healthInformations1;

	//bi-directional many-to-one association to HealthInformation
	@OneToMany(mappedBy="mapping2")
	private List<HealthInformation> healthInformations2;
	*/

	//bi-directional many-to-one association to UserValueRecordMapping
	@OneToMany(mappedBy="mapping")
	private List<UserValueRecordMapping> userValueRecordMappings;

	public Mapping() {
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getColumnType() {
		return this.columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/*
	public List<HealthInformation> getHealthInformations1() {
		return this.healthInformations1;
	}

	public void setHealthInformations1(List<HealthInformation> healthInformations1) {
		this.healthInformations1 = healthInformations1;
	}

	public HealthInformation addHealthInformations1(HealthInformation healthInformations1) {
		getHealthInformations1().add(healthInformations1);
		healthInformations1.setMapping1(this);

		return healthInformations1;
	}

	public HealthInformation removeHealthInformations1(HealthInformation healthInformations1) {
		getHealthInformations1().remove(healthInformations1);
		healthInformations1.setMapping1(null);

		return healthInformations1;
	}

	public List<HealthInformation> getHealthInformations2() {
		return this.healthInformations2;
	}

	public void setHealthInformations2(List<HealthInformation> healthInformations2) {
		this.healthInformations2 = healthInformations2;
	}

	public HealthInformation addHealthInformations2(HealthInformation healthInformations2) {
		getHealthInformations2().add(healthInformations2);
		healthInformations2.setMapping2(this);

		return healthInformations2;
	}

	public HealthInformation removeHealthInformations2(HealthInformation healthInformations2) {
		getHealthInformations2().remove(healthInformations2);
		healthInformations2.setMapping2(null);

		return healthInformations2;
	}
	 */

	public List<UserValueRecordMapping> getUserValueRecordMappings() {
		return this.userValueRecordMappings;
	}

	public void setUserValueRecordMappings(List<UserValueRecordMapping> userValueRecordMappings) {
		this.userValueRecordMappings = userValueRecordMappings;
	}

	public UserValueRecordMapping addUserValueRecordMapping(UserValueRecordMapping userValueRecordMapping) {
		getUserValueRecordMappings().add(userValueRecordMapping);
		userValueRecordMapping.setMapping(this);

		return userValueRecordMapping;
	}

	public UserValueRecordMapping removeUserValueRecordMapping(UserValueRecordMapping userValueRecordMapping) {
		getUserValueRecordMappings().remove(userValueRecordMapping);
		userValueRecordMapping.setMapping(null);

		return userValueRecordMapping;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + typeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mapping other = (Mapping) obj;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mapping [typeId=" + typeId + ", columnType=" + columnType
				+ ", memo=" + memo + ", typeName=" + typeName + "]";
	}

	@Override
	public MappingTO convert(boolean relation) {
		MappingTO to = new MappingTO();
		to.setColumnType(getColumnType());
		to.setMemo(getMemo());
		to.setTypeId(getTypeId());
		to.setTypeName(getTypeName());
		
		return to;
	}

}