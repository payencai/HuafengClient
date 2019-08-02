package com.huafeng.client.ui.start.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.huafeng.client.R;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.WebviewActivity;
import com.huafeng.client.ui.start.bean.Splash;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {
    Splash splash;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_time)
    TextView tv_time;
    int count = 3;
    CountDownTimer mCountDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        startMain();
    }
    @OnClick({R.id.tv_time,R.id.iv_logo})
    void OnClick(View view ){
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()){
            case R.id.tv_time:
                mCountDownTimer.cancel();
                startMain();
                break;
            case R.id.iv_logo:
                if (TextUtils.isEmpty(splash.getHyperlink())){
                    return;
                }
                mCountDownTimer.cancel();
                Intent intent=new Intent(GuideActivity.this, WebviewActivity.class);
                intent.putExtra("img",splash.getHyperlink());
                startActivityForResult(intent,1);
                break;
        }
    }
    private void initView() {
        splash= (Splash) getIntent().getSerializableExtra("data");
        Glide.with(this).load(splash.getImageUrl()).into(ivLogo);
        startCount(count*1000);
    }
    private void startCount(int time) {
        mCountDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setText("跳过(" + count + "s)");
                count--;
            }
            @Override
            public void onFinish() {
                tv_time.setText("跳过(" + count + "s)");
                startMain();
                //倒计时结束时回调该函数
            }
        }.start();
    }

    private void startMain(){
        String user = SPUtils.getInstance().getString("user");
        String pwd = SPUtils.getInstance().getString("pwd");
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pwd)) {
            boolean ishandle = SPUtils.getInstance().getBoolean("ishandle", false);
            boolean isfinger = SPUtils.getInstance().getBoolean("isFinger", false);
            if (!isfinger && !ishandle) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(GuideActivity.this, FingerActivity.class));
                finish();
            }

        } else {
            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
            finish();
        }
    }
}
