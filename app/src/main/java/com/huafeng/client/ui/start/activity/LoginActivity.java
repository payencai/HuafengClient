package com.huafeng.client.ui.start.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.JPush.JpushConfig;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.Account;
import com.huafeng.client.model.UserInfo;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.DbUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.MyUserProvider;
import com.jaeger.library.StatusBarUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leolin.shortcutbadger.ShortcutBadger;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.tv_login)
    TextView tv_login;
    String phone;
    String pwd;
    boolean isFinger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        isFinger = getIntent().getBooleanExtra("isFinger", false);
//        if (!isFinger) {
//
//        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.color_status));
        initView();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }




    private void initView() {
        askPermission();
        String phone=SPUtils.getInstance().getString("phone", "");
        String pwd=SPUtils.getInstance().getString("pwd", "");
        if(!TextUtils.isEmpty(phone)){
            et_phone.setText(phone);
        }
        if(!TextUtils.isEmpty(pwd)){
            et_pwd.setText(pwd);
        }
    }

    private void askPermission() {
        final RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance
        // Must be done during an initialization phase like onCreate
        rxPermissions
                .request(Manifest.permission.RECORD_AUDIO, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now

                    } else {
                        // Oups permission denied
                    }
                });

    }

    KProgressHUD kProgressHUD;

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.tv_forget})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if (checkInput()) {
                    kProgressHUD = KProgressHUD.create(this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setLabel("加载中")
                            .setCancellable(true)
                            .show();
                    login(phone, pwd);
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                break;
            case R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));

                break;

        }
    }

    private boolean checkInput() {
        boolean isOk = true;
        phone = et_phone.getEditableText().toString();
        pwd = et_pwd.getEditableText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("手机号不能为空");
            isOk = false;
            return isOk;
        }

        if (TextUtils.isEmpty(pwd)) {
            isOk = false;
            ToastUtils.showShort("密码不能为空");
            return isOk;
        }
        return isOk;
    }

    //密码登录
    private void login(String phone, String pwd) {

        Map<String, Object> params = new HashMap<>();
        params.put("password", pwd);
        params.put("username", phone);
        String json = new Gson().toJson(params);
        //ToastUtils.showLong("开始登录");
        NetUtils.getInstance().post(Api.EmployeeUser.login, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);

                handleHttp(response);


            }

            @Override
            public void onError(String error) {
                ToastUtils.showLong("登录失败");
                finish();
            }
        });
    }


    private void handleHttp(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("resultCode");
            if (resultCode == 0) {
                JSONObject data = jsonObject.getJSONObject("data");
                UserInfo userInfo = new Gson().fromJson(data.toString(), UserInfo.class);
                JpushConfig.getInstance().setAlias(userInfo.getEmployeeRecordId()+"");
                JpushConfig.getInstance().setTag(userInfo.getEmployeeRecordId()+"");
                SPUtils.getInstance().put("phone", phone);
                SPUtils.getInstance().put("pwd", pwd);
                SPUtils.getInstance().put("head", userInfo.getHeadPortraitUrl());
                Account account = new Account();
                account.setUsername(phone);
                account.setPassword(pwd);
                account.setCurrent(true);
                if (DbUtil.isShouldAddToDb(account)) {//如果没有账号，保存
                    account.save();
                } else {
                    ContentValues values = new ContentValues();//更新
                    values.put("password", pwd);
                    LitePal.updateAll(Account.class, values, "username = ? ", phone);
                }
                SPUtils.getInstance().put("token", userInfo.getToken());
                SPUtils.getInstance().put("user", data.toString());
                MyApp.token = userInfo.getToken();
                MyApp.isLogin = true;
                MyApp.setUserInfo(userInfo);
                MyUserProvider.getInstance().setUser(userInfo.getHxUsername()+"",userInfo.getNickname(),userInfo.getHeadPortraitUrl(),userInfo.getHeadPortrait());
                loginChat(userInfo.getHxUsername(), userInfo.getHxPassword());

            } else {
                if (kProgressHUD != null && kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
                String message = jsonObject.getString("message");
                ToastUtils.showShort(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (kProgressHUD != null && kProgressHUD.isShowing()) {
                kProgressHUD.dismiss();
            }
        }

    }

    private void loginChat(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                if (kProgressHUD != null && kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                MyUserProvider.getInstance().setUser(MyApp.getUserInfo().getId() + "", MyApp.getUserInfo().getNickname(), MyApp.getUserInfo().getHeadPortraitUrl(), MyApp.getUserInfo().getHeadPortrait());
                int count = EMClient.getInstance().chatManager().getUnreadMessageCount();
                ShortcutBadger.applyCount(LoginActivity.this, count); //for 1.1.4+
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                Log.e("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                if (kProgressHUD != null && kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
                ToastUtils.showLong("登录聊天服务器失败!");
                Log.e("main", "登录聊天服务器失败！");

            }
        });
    }
}
