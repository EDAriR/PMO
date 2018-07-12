package com.syntrontech.pmo.measurement.model;

import com.syntrontech.pmo.measurement.model.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.GenderType;

import java.util.Date;

public interface Measurement {

    Long getSequence();

    void setSequence(Long sequence);

    Date getRecordTime();

    void setRecordTime(Date recordTime);

    String getLatitude();

    void setLatitude(String latitude);

    String getLongitude();

    void setLongitude(String longitude);

    MeasurementStatusType getStatus();

    void setStatus(MeasurementStatusType status);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    String getCreateBy();

    void setCreateBy(String createBy);

    String getTenantId();

    void setTenantId(String tenantId);

    String getDeviceMacAddress();

    void setDeviceMacAddress(String deviceMacAddress);

    Long getSubjectSeq();

    void setSubjectSeq(Long subjectSeq);

    String getSubjectId();

    void setSubjectId(String subjectId);

    String getSubjectName();

    void setSubjectName(String subjectName);

    Integer getSubjectAge();

    void setSubjectAge(Integer subjectAge);

    GenderType getSubjectGender();

    void setSubjectGender(GenderType subjectGender);

    String getSubjectUserId();

    void setSubjectUserId(String subjectUserId);

    String getSubjectUserName();

    void setSubjectUserName(String subjectUserName);

    Long getRuleSeq();

    void setRuleSeq(Long ruleSeq);

    String getRuleDescription();

    void setRuleDescription(String ruleDescription);

    String getUnitId();

    void setUnitId(String unitId);

    String getUnitName();

    void setUnitName(String deviceId);

    String getParentUnitId();

    void setParentUnitId(String parentUnitId);

    String getParentUnitName();

    void setParentUnitName(String parentUnitId);

    String getDeviceId();

    void setDeviceId(String deviceId);
}
