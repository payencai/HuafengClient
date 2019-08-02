package com.huafeng.client.ui.message.activity;

import android.app.Service;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StrongNoticeActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_know)
    TextView tv_know;
    String name;
    String head;
    String id;

    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_strong_notice);
        ButterKnife.bind(this);
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        head=getIntent().getStringExtra("head");
        vibrator=(Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(new long[]{100L, 1000L, 1000L, 1000L, 1000L}, 0));
        } else {
            vibrator.vibrate(new long[]{100L, 2000L, 1000L, 1000L, 3000L}, 0);
        }
        tv_name.setText(name);
        Glide.with(this).load(head).into(ivHead);
        tv_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
                finish();
                update();
            }
        });
    }
    private void update() {
        HttpParams params = new HttpParams();
        params.put("id",id);
        params.put("isRemind", 0);
        NetUtils.getInstance().post(Api.Huanxin.updateFriendsById, MyApp.token, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        //ToastUtils.showShort("修改成功");
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        vibrator.cancel();
        finish();
        update();
    }
}
