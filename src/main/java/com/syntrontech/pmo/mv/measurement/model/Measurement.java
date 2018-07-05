package com.syntrontech.pmo.mv.measurement.model;

import java.util.Date;

import com.syntrontech.measurement.model.common.GenderType;
import com.syntrontech.measurement.model.common.MeasurementStatusType;

public interface Measurement {

	public Long getSequence();
	public void setSequence(Long sequence);
	
	public Date getRecordTime();
	public void setRecordTime(Date recordTime);
	
	public String getLatitude();
	public void setLatitude(String latitude);
	
	public String getLongitude();
	public void setLongitude(String longitude);

	public MeasurementStatusType getStatus() ;
	public void setStatus(MeasurementStatusType status);
	
	public Date getCreateTime();
	public void setCreateTime(Date createTime) ;
	
	public String getCreateBy();
	public void setCreateBy(String createBy);

	public String getTenantId();
	public void setTenantId(String tenantId);
	
	public String getDeviceMacAddress() ;
	public void setDeviceMacAddress(String deviceMacAddress);
	
	public Long getSubjectSeq() ;
	public void setSubjectSeq(Long subjectSeq);

	public String getSubjectId();
	public void setSubjectId(String subjectId);
	
	public String getSubjectName();
	public void setSubjectName(String subjectName);
	
	public Integer getSubjectAge();
	public void setSubjectAge(Integer subjectAge);
	
	public GenderType getSubjectGender();
	public void setSubjectGender(GenderType subjectGender);

	public String getSubjectUserId();
	public void setSubjectUserId(String subjectUserId);
	
	public String getSubjectUserName();
	public void setSubjectUserName(String subjectUserName);
	
	public Long getRuleSeq();
	public void setRuleSeq(Long ruleSeq) ;
	
	public String getRuleDescription();
	public void setRuleDescription(String ruleDescription);
	
	public String getUnitId();
	public void setUnitId(String unitId);

	public String getUnitName();
	public void setUnitName(String deviceId);
	
	public String getParentUnitId();
	public void setParentUnitId(String parentUnitId);
	
	public String getParentUnitName();
	public void setParentUnitName(String parentUnitId);
	
	public String getDeviceId();
	public void setDeviceId(String deviceId);
}
