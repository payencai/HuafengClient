package com.huafeng.client.tools;

import android.app.Activity;
import android.util.Log;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;

public class MyEMConnectionListener implements EMConnectionListener {
    Activity activity;

    public MyEMConnectionListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected(int error) {
        Log.e("onDisconnected",error + "");
        if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
            //ToastUtils.showLong("你的账号已经在别处登录");
            LoginUtil.onConnectionConflict(activity);

        }
    }
}
