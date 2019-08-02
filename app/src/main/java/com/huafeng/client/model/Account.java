package com.huafeng.client.model;

import org.litepal.crud.LitePalSupport;

public class Account extends LitePalSupport  {
    private String password;


    private String username;
    private boolean isCurrent;

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
