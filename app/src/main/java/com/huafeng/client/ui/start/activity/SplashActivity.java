package com.huafeng.client.ui.start.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.start.bean.Splash;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot() && getIntent() != null) {
            String action = getIntent().getAction();
            if (getIntent().hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getPic();
        }


    }

    private void startMain() {
        String user = SPUtils.getInstance().getString("user");
        String pwd = SPUtils.getInstance().getString("pwd");
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pwd)) {
            boolean ishandle = SPUtils.getInstance().getBoolean("ishandle", false);
            boolean isfinger = SPUtils.getInstance().getBoolean("isFinger", false);
            if (!isfinger && !ishandle) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, FingerActivity.class));
                finish();
            }

        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void getPic() {
        NetUtils.getInstance().get(Api.Guide.get, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int resultCode = jsonObject.getInt("resultCode");
                    if (resultCode == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        Splash splash = GsonUtils.fromJson(data.toString(), Splash.class);
                        if (splash != null) {
                            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                            intent.putExtra("data", splash);
                            startActivity(intent);
                            finish();
                        } else {
                            LogUtils.e("tst");
                            startMain();
                        }
                    } else {

                        String message = jsonObject.getString("message");
                        ToastUtils.showShort(message);
                        startMain();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onError(String error) {
                startMain();
                LogUtils.e(error);
            }
        });
    }
}
