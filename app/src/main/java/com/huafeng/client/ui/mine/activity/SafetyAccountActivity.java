package com.huafeng.client.ui.mine.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.start.activity.UpdatePaswordActivity;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafetyAccountActivity extends BaseActivity {
    @BindView(R.id.rl_phone)
    RelativeLayout rl_phone;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.rl_hand)
    RelativeLayout rlHand;
    @BindView(R.id.switch2)
    SwitchButton switch2;
    @BindView(R.id.switch1)
    SwitchButton switchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_account);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        tv_phone.setText(MyApp.getUserInfo().getUsername());
        boolean ishandle=SPUtils.getInstance().getBoolean("ishandle");
        boolean isFinger=SPUtils.getInstance().getBoolean("isFinger");
        String pwd=SPUtils.getInstance().getString("handpwd");
        if(ishandle){
            rlHand.setVisibility(View.VISIBLE);
        }
        switchButton.setChecked(ishandle);
        switch2.setChecked(isFinger);
        switch2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.getInstance().put("isFinger",isChecked);
            }
        });
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                   if(isChecked){
                       rlHand.setVisibility(View.VISIBLE);
                       if(TextUtils.isEmpty(pwd)){
                           startActivity(new Intent(SafetyAccountActivity.this,SetHandPasswordActivity.class));
                       }else{
                           SPUtils.getInstance().put("ishandle",isChecked);
                       }
                   }else{
                       SPUtils.getInstance().put("ishandle",isChecked);
                       rlHand.setVisibility(View.GONE);
                   }

            }
        });

    }
    @OnClick({R.id.rl_phone,R.id.rl_pwd,R.id.back,R.id.rl_hand})
    void onClick(View view){
        switch (view.getId()){
            case R.id.rl_hand:
                startActivity(new Intent(SafetyAccountActivity.this,SetHandPasswordActivity.class));
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_phone:
                startActivity(new Intent(SafetyAccountActivity.this,MyPhoneActivity.class));
                break;
            case R.id.rl_pwd:
                startActivity(new Intent(SafetyAccountActivity.this,UpdatePaswordActivity.class));
                break;

        }
    }
}
