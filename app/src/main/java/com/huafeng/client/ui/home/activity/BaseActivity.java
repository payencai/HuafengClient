package com.huafeng.client.ui.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.huafeng.client.MyApp;
import com.huafeng.client.tools.MyEMConnectionListener;
import com.hyphenate.chat.EMClient;

public class BaseActivity extends AppCompatActivity {
    MyEMConnectionListener myEMConnectionListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyApp.isLogin){
            myEMConnectionListener=new MyEMConnectionListener(BaseActivity.this);
            EMClient.getInstance().addConnectionListener(myEMConnectionListener);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().removeConnectionListener(myEMConnectionListener);
    }
}
