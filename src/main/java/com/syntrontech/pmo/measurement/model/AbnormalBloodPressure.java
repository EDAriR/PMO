package com.syntrontech.pmo.measurement.model;

import com.syntrontech.pmo.measurement.model.common.BloodPressureCaseStatus;
import com.syntrontech.pmo.measurement.model.common.MeasurementStatusType;
import com.syntrontech.pmo.model.TO.TO;
import com.syntrontech.pmo.model.common.GenderType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "abnormal_blood_pressure")
public class AbnormalBloodPressure {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sequence;
	
	@Column(name = "blood_pressure_seq")
	private Long bloodPressureSeq;
	
    @Column(name = "subject_seq", nullable = false)
    private Long subjectSeq;
	
	@Column(name = "subject_id")
	private String subjectId;
	
	@Column(name = "subject_name")
	private String subjectName;
	
    @Column(name = "subject_gender", nullable = false)
    @Enumerated(EnumType.STRING)
	private GenderType subjectGender;
    
    @Column(name = "subject_age", nullable = false)
    private Integer subjectAge;
	
    @Column(name = "subject_user_id", nullable = false)
    private String subjectUserId;
    
    @Column(name = "subject_user_name", nullable = false)
    private String subjectUserName;
	
	@Column(name = "systolic_pressure")
	private Integer systolicPressure;
	
	@Column(name = "diastolic_pressure")
	private Integer diastolicPressure;
	
	@Column(name = "heart_rate", nullable = false)
	private Integer heartRate;
	
	@Column(name = "recordtime", nullable = false)
	private Date recordtime;
	
    @Column(name = "createby", nullable = false)
    private String createBy;
	
    @Column(name = "case_status", nullable = false)
    @Enumerated(EnumType.STRING)
	private BloodPressureCaseStatus caseStatus;
    
	@Column(name = "last_change_case_status_time", nullable = false)
	private Date lastChangeCaseStatusTime;
    
    @Column(name = "unit_id")
    private String unitId;
    
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
    
    @Column(name = "device_mac_address")
    private String deviceMacAddress;
    
    @Column(name = "unit_name")
    private String unitName;
    
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeasurementStatusType status;
	
    @Column(name = "rule_description")
    private String ruleDescription;
    
    @Column(name = "parent_unit_id")
    private String parentUnitId;

    @Column(name = "parent_unit_name")
    private String parentUnitName;

    @Column(name = "device_id")
    private String deviceId;


	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public Long getBloodPressureSeq() {
		return bloodPressureSeq;
	}

	public void setBloodPressureSeq(Long bloodPressureSeq) {
		this.bloodPressureSeq = bloodPressureSeq;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public GenderType getSubjectGender() {
		return subjectGender;
	}

	public void setSubjectGender(GenderType subjectGender) {
		this.subjectGender = subjectGender;
	}

	public Integer getSubjectAge() {
		return subjectAge;
	}

	public void setSubjectAge(Integer subjectAge) {
		this.subjectAge = subjectAge;
	}

	public Integer getSystolicPressure() {
		return systolicPressure;
	}

	public void setSystolicPressure(Integer systolicPressure) {
		this.systolicPressure = systolicPressure;
	}

	public Integer getDiastolicPressure() {
		return diastolicPressure;
	}

	public void setDiastolicPressure(Integer diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}

	public Integer getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}

	public Date getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
	}

	public BloodPressureCaseStatus getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(BloodPressureCaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	public Date getLastChangeCaseStatusTime() {
		return lastChangeCaseStatusTime;
	}

	public void setLastChangeCaseStatusTime(Date lastChangeCaseStatusTime) {
		this.lastChangeCaseStatusTime = lastChangeCaseStatusTime;
	}

	public Long getSubjectSeq() {
		return subjectSeq;
	}

	public void setSubjectSeq(Long subjectSeq) {
		this.subjectSeq = subjectSeq;
	}

	public String getSubjectUserId() {
		return subjectUserId;
	}

	public void setSubjectUserId(String subjectUserId) {
		this.subjectUserId = subjectUserId;
	}

	public String getSubjectUserName() {
		return subjectUserName;
	}

	public void setSubjectUserName(String subjectUserName) {
		this.subjectUserName = subjectUserName;
	}

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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public String getDeviceMacAddress() {
		return deviceMacAddress;
	}

	public void setDeviceMacAddress(String deviceMacAddress) {
		this.deviceMacAddress = deviceMacAddress;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public MeasurementStatusType getStatus() {
		return status;
	}

	public void setStatus(MeasurementStatusType status) {
		this.status = status;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	
	public String getParentUnitId() {
		return parentUnitId;
	}

	public void setParentUnitId(String parantUnitId) {
		this.parentUnitId = parantUnitId;
	}

	public String getParentUnitName() {
		return parentUnitName;
	}

	public void setParentUnitName(String parentUnitName) {
		this.parentUnitName = parentUnitName;
	}
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public AbnormalBloodPressure convertFromBloodPressureHeartBeat(BloodPressureHeartBeat bloodPressureHeartBeat){
		this.bloodPressureSeq = bloodPressureHeartBeat.getSequence();
		this.subjectSeq = bloodPressureHeartBeat.getSubjectSeq();
		this.subjectId = bloodPressureHeartBeat.getSubjectId();
		this.subjectUserId = bloodPressureHeartBeat.getSubjectUserId();
		this.subjectUserName=bloodPressureHeartBeat.getSubjectUserName();
		this.systolicPressure = bloodPressureHeartBeat.getSystolicPressure();
		this.diastolicPressure = bloodPressureHeartBeat.getDiastolicPressure();
		this.heartRate = bloodPressureHeartBeat.getHeartRate();
		this.recordtime = bloodPressureHeartBeat.getRecordTime();
		this.subjectName = bloodPressureHeartBeat.getSubjectName();
		this.caseStatus = BloodPressureCaseStatus.NOT_YET;
		this.unitId = bloodPressureHeartBeat.getUnitId();
		this.tenantId = bloodPressureHeartBeat.getTenantId();
		this.subjectGender = bloodPressureHeartBeat.getSubjectGender();
		this.subjectAge = bloodPressureHeartBeat.getSubjectAge();
		this.unitName = bloodPressureHeartBeat.getUnitName();
		this.deviceMacAddress=bloodPressureHeartBeat.getDeviceMacAddress();
		this.status=bloodPressureHeartBeat.getStatus();
		this.createBy=bloodPressureHeartBeat.getCreateBy();
		this.ruleDescription=bloodPressureHeartBeat.getRuleDescription();
		//第一次存lastChangeCaseStatusTime=recordtime
		this.lastChangeCaseStatusTime = bloodPressureHeartBeat.getRecordTime();
		this.deviceId = bloodPressureHeartBeat.getDeviceId();
		return this;
	}
	
	public TO<AbnormalBloodPressure> convertToTO(TO<AbnormalBloodPressure> to){
		return to.convertFrom(this);
	}
    

}
