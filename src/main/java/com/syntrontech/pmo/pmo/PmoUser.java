package com.syntrontech.pmo.pmo;

import java.util.Date;

public class PmoUser {

    // TABLE pmo_user
    // sequence
    private Long sequence;
    // user_id
    private String userId;
    // pmo_password
    private String pmoPassword;
    // status
    private PmoStatus pmoStatus;
    // synctime
    private Date synctime;

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPmoPassword() {
        return pmoPassword;
    }

    public void setPmoPassword(String pmoPassword) {
        this.pmoPassword = pmoPassword;
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
}
