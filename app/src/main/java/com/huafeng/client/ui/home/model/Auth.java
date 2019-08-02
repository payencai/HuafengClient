package com.huafeng.client.ui.home.model;

import org.litepal.crud.LitePalSupport;

public class Auth extends LitePalSupport {
    private int authId;

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }
}
