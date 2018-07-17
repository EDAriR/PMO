package com.syntrontech.pmo.measurement;

import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
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
@Entity(name = "body_info")
public class BodyInfo implements Measurement{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sequence;
	
	@Column(name = "height")
	private Double height;
	
	@Column(name = "weight", nullable = false)
	private Double weight;
	
	@Column(name = "bmi")
	private Double bmi;
	
	@Column(name = "bfp")
	private Double bfp;
	
	@Column(name = "recordtime", nullable = false)
	private Date recordTime;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeasurementStatusType status;
	
	@Column(name = "createtime", nullable = false)
	private Date createTime;
	
    @Column(name = "createby", nullable = false)
    private String createBy;
    
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
    
    @Column(name = "device_mac_address")
    private String deviceMacAddress;
    
    @Column(name = "subject_seq", nullable = false)
    private Long subjectSeq;
    
    @Column(name = "subject_id", nullable = false)
    private String subjectId;
    
    @Column(name="subject_name")
    private String subjectName;
    
    @Column(name = "subject_age", nullable = false)
    private Integer subjectAge;
    
    @Column(name = "subject_gender", nullable = false)
    @Enumerated(EnumType.STRING)
	private GenderType subjectGender;
    
    @Column(name = "subject_user_id", nullable = false)
    private String subjectUserId;
    
    @Column(name = "subject_user_name", nullable = false)
    private String subjectUserName;
	
    @Column(name = "rule_seq")
    private Long ruleSeq;
    
    @Column(name = "rule_description")
    private String ruleDescription;

    @Column(name = "unit_id", nullable = false)
    private String unitId;

    @Column(name = "unit_name")
    private String unitName;
    
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

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Double getBfp() {
		return bfp;
	}

	public void setBfp(Double bfp) {
		this.bfp = bfp;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public MeasurementStatusType getStatus() {
		return status;
	}

	public void setStatus(MeasurementStatusType status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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

	public Long getSubjectSeq() {
		return subjectSeq;
	}

	public void setSubjectSeq(Long subjectSeq) {
		this.subjectSeq = subjectSeq;
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

	public Integer getSubjectAge() {
		return subjectAge;
	}

	public void setSubjectAge(Integer subjectAge) {
		this.subjectAge = subjectAge;
	}

	public GenderType getSubjectGender() {
		return subjectGender;
	}

	public void setSubjectGender(GenderType subjectGender) {
		this.subjectGender = subjectGender;
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

	public Long getRuleSeq() {
		return ruleSeq;
	}

	public void setRuleSeq(Long ruleSeq) {
		this.ruleSeq = ruleSeq;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
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

	public TO<BodyInfo> convertToTO(TO<BodyInfo> to){
		return to.convertFrom(this);
	}
}
