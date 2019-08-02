package com.huafeng.client.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetHandPasswordActivity extends BaseActivity {
    @BindView(R.id.lock)
    GestureLockView gestureLockView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_hand_password);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    int count=0;
    String first="";
    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gestureLockView.setUseVibrate(true);
        gestureLockView.setUseAnim(true);
        gestureLockView.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                if(count==0){
                    first=result;
                    count++;
                    gestureLockView.clearView();
                    ToastUtils.showShort("请输入确认手势");
                }else{
                    if(first.equals(result)){
                        SPUtils.getInstance().put("ishandle",true);
                        SPUtils.getInstance().put("handpwd",result);
                        gestureLockView.clearView();
                        Toast.makeText(SetHandPasswordActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        ToastUtils.showShort("2次密码不一致，请重新设置");
                        gestureLockView.clearView();
                        count=0;
                        first="";
                    }
                }

            }
        });
    }

}
