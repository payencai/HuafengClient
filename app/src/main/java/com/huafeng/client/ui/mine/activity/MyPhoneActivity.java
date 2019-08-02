package com.huafeng.client.ui.mine.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPhoneActivity extends BaseActivity {
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_phone);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        tv_phone.setText("你的手机号："+ MyApp.getUserInfo().getUsername());
    }

    @OnClick({R.id.tv_change,R.id.back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_change:
                startActivity(new Intent(MyPhoneActivity.this,BindPhoneActivity.class));
                break;
            case R.id.back:
                finish();
                break;
        }
   }
}
