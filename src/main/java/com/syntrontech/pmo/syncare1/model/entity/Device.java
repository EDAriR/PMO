package com.syntrontech.pmo.syncare1.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the device database table.
 * 
 */
@Entity
@Table(name = "device")
@NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
public class Device{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "serial_no")
	private String serialNo;

	private String name;

	// bi-directional many-to-one association to Location
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "syntron_location_id")
	private String syntronLocationId;

	// bi-directional many-to-one association to UserValueRecord
	@OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
	private List<UserValueRecord> userValueRecords;

	public Device() {
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getSyntronLocationId() {
		return syntronLocationId;
	}

	public void setSyntronLocationId(String syntronLocationId) {
		this.syntronLocationId = syntronLocationId;
	}

	public List<UserValueRecord> getUserValueRecords() {
		return this.userValueRecords;
	}

	public void setUserValueRecords(List<UserValueRecord> userValueRecords) {
		this.userValueRecords = userValueRecords;
	}

	public UserValueRecord addUserValueRecord(UserValueRecord userValueRecord) {
		getUserValueRecords().add(userValueRecord);
		userValueRecord.setDevice(this);

		return userValueRecord;
	}

	public UserValueRecord removeUserValueRecord(UserValueRecord userValueRecord) {
		getUserValueRecords().remove(userValueRecord);
		userValueRecord.setDevice(null);

		return userValueRecord;
	}


	@Override
	public String toString() {
		return "Device [serialNo=" + serialNo + ", name=" + name + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serialNo == null) ? 0 : serialNo.hashCode());
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
		Device other = (Device) obj;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		return true;
	}

}