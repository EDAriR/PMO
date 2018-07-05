package com.syntrontech.pmo.mv.syncare1.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.syntrontech.syncare.model.ObjectConverter;
import com.syntrontech.syncare.model.transfer.RecordTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The persistent class for the user_value_record database table.
 * 
 */
@Entity
@Table(name = "user_value_record")
@NamedQuery(name = "UserValueRecord.findAll", query = "SELECT u FROM UserValueRecord u")
public class UserValueRecord implements ObjectConverter<RecordTO>, Serializable {
	public enum RecordPmoStatus {
		Sync, NotSync, Error
	}

	public enum RecordSynCare2Status {
		Sync, NotSync, Error
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BODY_VALUE_RECORD_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bodyValueRecordId;

	@Column(name = "COLUMN_TYPE")
	private String columnType;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "location_name")
	private String locationName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECORD_DATE")
	private Date recordDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	// bi-directional many-to-one association to Device
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serial_no")
	private Device device;

	@Column(name = "pmo_status")
	@Enumerated(EnumType.STRING)
	private RecordPmoStatus pmoStatus = RecordPmoStatus.NotSync;

	@Column(name = "pmo_result")
	private String pmoResult;

	@Column(name = "syncare2_status")
	@Enumerated(EnumType.STRING)
	private RecordSynCare2Status synCare2Status = RecordSynCare2Status.NotSync;

	@Column(name = "user_account_serial")
	private long userAccountSerial;

	// bi-directional many-to-one association to SystemUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private SystemUser systemUser;

	// bi-directional many-to-one association to UserValueRecordMapping
	@OneToMany(mappedBy = "userValueRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserValueRecordMapping> userValueRecordMappings = new ArrayList<>();

	public UserValueRecord() {
	}

	public int getBodyValueRecordId() {
		return this.bodyValueRecordId;
	}

	public void setBodyValueRecordId(int bodyValueRecordId) {
		this.bodyValueRecordId = bodyValueRecordId;
	}

	public String getColumnType() {
		return this.columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public RecordPmoStatus getPmoStatus() {
		return pmoStatus;
	}

	public void setPmoStatus(RecordPmoStatus pmoStatus) {
		this.pmoStatus = pmoStatus;
	}

	public String getPmoResult() {
		return pmoResult;
	}

	public void setPmoResult(String pmoResult) {
		this.pmoResult = pmoResult;
	}

	public RecordSynCare2Status getSynCare2Status() {
		return synCare2Status;
	}

	public void setSynCare2Status(RecordSynCare2Status synCare2Status) {
		this.synCare2Status = synCare2Status;
	}

	public long getUserAccountSerial() {
		return userAccountSerial;
	}

	public void setUserAccountSerial(long userAccountSerial) {
		this.userAccountSerial = userAccountSerial;
	}

	public SystemUser getSystemUser() {
		return this.systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public List<UserValueRecordMapping> getUserValueRecordMappings() {
		return this.userValueRecordMappings;
	}

	public void setUserValueRecordMappings(List<UserValueRecordMapping> userValueRecordMappings) {
		this.userValueRecordMappings = userValueRecordMappings;
	}

	public UserValueRecordMapping addUserValueRecordMapping(UserValueRecordMapping userValueRecordMapping) {
		getUserValueRecordMappings().add(userValueRecordMapping);
		userValueRecordMapping.setUserValueRecord(this);

		return userValueRecordMapping;
	}

	public UserValueRecordMapping removeUserValueRecordMapping(UserValueRecordMapping userValueRecordMapping) {
		getUserValueRecordMappings().remove(userValueRecordMapping);
		userValueRecordMapping.setUserValueRecord(null);

		return userValueRecordMapping;
	}

	@Override
	public RecordTO convert(boolean relation) {
		RecordTO to = new RecordTO();
		to.setId(getBodyValueRecordId());
		to.setRecordDate(getRecordDate());
		to.setType(getColumnType());

		if (relation) {
			Map<String, Number> values = getUserValueRecordMappings().stream().collect(Collectors
					.toMap(key -> key.getMapping().getTypeName(), value -> Double.parseDouble(value.getRecordValue())));
			to.setValue(values);
		}
		return to;
	}

	@Override
	public String toString() {
		return "UserValueRecord [" + "bodyValueRecordId=" + bodyValueRecordId + ", columnType=" + columnType
				+ ", locationId=" + locationId + ", locationName=" + locationName + ", recordDate=" + recordDate
				+ ", updateDate=" + updateDate + ", device=" + device + ", pmoStatus=" + pmoStatus + ", pmoResult="
				+ pmoResult + ", synCare2Status=" + synCare2Status + ", systemUser=" + systemUser
				+ ", userValueRecordMappings=" + userValueRecordMappings + "]";
	}

}