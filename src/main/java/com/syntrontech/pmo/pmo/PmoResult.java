package com.syntrontech.pmo.pmo;

import java.util.Date;

public class PmoResult {
    // TABLE pmo_result

    // sequence
    private Long sequence;
    // user_id
    private String userId;
    // measurement_type
    private MeasurementPMOType measurementType;
    // record_id
    private Long recordId;
    // result
    private String result;
    // status
    private PmoStatus pmoStatus;
    // synctime
    private Date synctime;
    // tenant_id
    private String tenantId;

    @Override
    public String toString() {
        return "PmoResult:[" +
                "userId=" + userId + ", " +
                "measurementType=" + measurementType + ", " +
                "recordId=" + recordId + ", " +
                "result=" + result + ", " +
                "tenantId=" + tenantId + ", " +
                "]";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MeasurementPMOType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementPMOType measurementType) {
        this.measurementType = measurementType;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public PmoStatus getPmoStatus() {
        return pmoStatus;
    }

    public void setPmoStatus(PmoStatus pmoStatus) {
        this.pmoStatus = pmoStatus;
    }

    public Date getSynctime() {
        return synctime;
    }

    public void setSynctime(Date synctime) {
        this.synctime = synctime;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
