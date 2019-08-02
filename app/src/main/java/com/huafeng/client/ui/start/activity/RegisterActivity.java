package com.huafeng.client.ui.start.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.Account;
import com.huafeng.client.model.UserInfo;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.DbUtil;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.MyUserProvider;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.iv_eye)
    ImageView iv_eye;
    int status = 0;
    String phone;
    String code;
    String pwd;
    TimeCount mTimeCount;
    int count = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_send.setEnabled(false);
            tv_send.setTextColor(getResources().getColor(R.color.color_999));
            count--;
            //倒计时的过程中回调该函数
            tv_send.setText(count + "s");
        }

        @Override
        public void onFinish() {
            count = 60;
            tv_send.setText("重新获取");
            tv_send.setEnabled(true);
            tv_send.setTextColor(getResources().getColor(R.color.color_333));
            //倒计时结束时回调该函数
        }
    }

    @OnClick({R.id.tv_send, R.id.tv_submit, R.id.iv_back})
    void onClick(View view) {
        if (CheckDoubleClick.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_send:
                String phone = et_phone.getEditableText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    getCodeByType(phone);
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkInput())
                    register();
                break;
        }
    }

    private boolean checkInput() {
        boolean isOk = true;
        phone = et_phone.getEditableText().toString();
        code = et_code.getEditableText().toString();
        pwd = et_pwd.getEditableText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("手机号不能为空");
            isOk = false;
            return isOk;
        }
        if (TextUtils.isEmpty(code)) {
            isOk = false;
            ToastUtils.showShort("验证码不能为空");
            return isOk;
        }
        if (TextUtils.isEmpty(pwd)) {
            isOk = false;
            ToastUtils.showShort("密码不能为空");
            return isOk;
        }
        return isOk;
    }

    private void initView() {
        mTimeCount = new TimeCount(60000, 1000);
        iv_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == 0) {
                    status = 1;
                    iv_eye.setImageResource(R.mipmap.ic_eye);

                    et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); //密码可见
                } else {
                    status = 0;
                    iv_eye.setImageResource(R.mipmap.ic_uneye);

                    et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());//密码不可见
                }
            }
        });
    }

    private void getCodeByType(String phone) {
        HttpParams params = new HttpParams();
        params.put("telephone", phone);
        params.put("code", "1");
        NetUtils.getInstance().get(MyApp.token, Api.EmployeeUser.getVerificationCode, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject object = new JSONObject(response);
                    int code = object.getInt("resultCode");
                    if (code == 0) {
                        mTimeCount.start();
                        Toast.makeText(RegisterActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_LONG).show();
                        //注册,并且登录
                    } else {
                        Toast.makeText(RegisterActivity.this, "获取验证码异常", Toast.LENGTH_LONG).show();
                        //登录
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

    //注册
    private void register() {

        HttpParams httpParams = new HttpParams();
        httpParams.put("telephone", phone);
        httpParams.put("code", code);
        httpParams.put("password", pwd);
        NetUtils.getInstance().post(Api.EmployeeUser.register, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int resultCode = jsonObject.getInt("resultCode");
                    if (resultCode == 0) {
                        ToastUtils.showLong("注册成功");
                        login();
                    } else {
                        String message = jsonObject.getString("message");
                        ToastUtils.showShort(message);
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

    //密码登录
    private void login() {
        Map<String, Object> params = new HashMap<>();
        params.put("password", pwd);
        params.put("username", phone);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.EmployeeUser.login, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int resultCode = jsonObject.getInt("resultCode");
                    String message = jsonObject.getString("message");
                    if (resultCode == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        UserInfo userInfo = new Gson().fromJson(data.toString(), UserInfo.class);
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
                        if (MyApp.getUserInfo().getFactoryId() > 0) {
                            loginChat(userInfo.getHxUsername(), userInfo.getHxPassword());
                        } else {
                            startActivity(new Intent(RegisterActivity.this, ApplyFactoryActivity.class));
                            finish();
                        }
                    } else {
                        ToastUtils.showShort(message);
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

    private void loginChat(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {


                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                MyUserProvider.getInstance().setUser(MyApp.getUserInfo().getId() + "", MyApp.getUserInfo().getNickname(), MyApp.getUserInfo().getHeadPortraitUrl(), MyApp.getUserInfo().getHeadPortrait());
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
                Log.e("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

                ToastUtils.showLong("登录聊天服务器失败!");
                Log.e("main", "登录聊天服务器失败！");
                finish();
            }
        });
    }

}
