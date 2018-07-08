package com.syntrontech.pmo.syncare1.model.entity;

import javax.persistence.*;

/*
 * 介接城市科大門禁資料
 */
/**
 * Created by Simon on 2016/7/26.
 */
@Entity
@Table(name = "view_door_account")
@NamedQuery(name = "DoorAccountTPCView.findAll", query = "SELECT r FROM DoorAccountTPCView r")
public class DoorAccountTPCView {

    @Id
    @Column(name = "ac_account")
    public String ac_account;

    @Column(name = "ac_status")
    public String ac_status;

    @Column(name = "card_code")
    public String card_code;

    public String getAc_account() {
        return ac_account;
    }

    public void setAc_account(String ac_account) {
        this.ac_account = ac_account;
    }

    public String getAc_status() {
        return ac_status;
    }

    public void setAc_status(String ac_status) {
        this.ac_status = ac_status;
    }

    public String getCard_code() {
        return card_code;
    }

    public void setCard_code(String card_code) {
        this.card_code = card_code;
    }
}
