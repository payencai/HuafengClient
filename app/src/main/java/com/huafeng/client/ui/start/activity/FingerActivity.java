package com.huafeng.client.ui.start.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FingerActivity extends BaseActivity {
    FingerprintIdentify mFingerprintIdentify;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.lock)
    GestureLockView gestureLockView;
    boolean ishandle;
    boolean isFinger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ishandle = SPUtils.getInstance().getBoolean("ishandle", false);
        isFinger = SPUtils.getInstance().getBoolean("isFinger", false);
        if(isFinger){
            mFingerprintIdentify = new FingerprintIdentify(this);
            if (mFingerprintIdentify.isFingerprintEnable()) {
                mFingerprintIdentify.startIdentify(5, new BaseFingerprint.FingerprintIdentifyListener() {
                    @Override
                    public void onSucceed() {
                        mFingerprintIdentify.cancelIdentify();
                        startActivity(new Intent(FingerActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onNotMatch(int availableTimes) {

                    }

                    @Override
                    public void onFailed(boolean isDeviceLocked) {

                    }

                    @Override
                    public void onStartFailedByDeviceLocked() {

                    }
                });
            }
        }
        setContentView(R.layout.activity_finger);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {


        String head = SPUtils.getInstance().getString("head");
        Glide.with(this).load(head).apply(RequestOptions.circleCropTransform()).into(iv_head);
        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FingerActivity.this, LoginActivity.class);
                intent.putExtra("isFinger", true);
                startActivity(intent);
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
                String value = SPUtils.getInstance().getString("handpwd");
                if (TextUtils.isEmpty(result)) {
                    return;
                } else if (result.equals(value)) {
                    //Toast.makeText(FingerActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    gestureLockView.clearView();
                    startActivity(new Intent(FingerActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(FingerActivity.this, "密码错误o(╯□╰)o~", Toast.LENGTH_SHORT).show();
                    gestureLockView.showErrorStatus(1000);
                }
            }
        });
        String value = SPUtils.getInstance().getString("handpwd");

        if (ishandle && !TextUtils.isEmpty(value)) {
            gestureLockView.setVisibility(View.VISIBLE);
        } else {
            gestureLockView.setVisibility(View.GONE);
        }
    }
}
