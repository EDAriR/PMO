package com.syntrontech.pmo.syncare1.model.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Simon on 2016/12/15.
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponse {
    private String id;
    private String loginId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
