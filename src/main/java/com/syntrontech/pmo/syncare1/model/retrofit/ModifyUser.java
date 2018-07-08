package com.syntrontech.pmo.syncare1.model.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Simon on 2016/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyUser {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}