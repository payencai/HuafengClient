package com.huafeng.client.ui.start.activity;

import android.os.Build;
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
import com.huafeng.client.model.UserInfo;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePaswordActivity extends BaseActivity {
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.iv_eye)
    ImageView iv_eye;
    String phone;
    String code;
    String pwd;
    TimeCount mTimeCount;
    int count = 60;
    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pasword);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        tv_phone.setText(MyApp.getUserInfo().getUsername());
        phone = MyApp.getUserInfo().getUsername();
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
                String phone = MyApp.getUserInfo().getUsername();
                if (!TextUtils.isEmpty(phone)) {
                    getCodeByType(phone);
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkInput())
                    resetPwd();
                break;
        }
    }

    private void getCodeByType(String phone) {
        HttpParams params = new HttpParams();
        params.put("telephone", phone);
        params.put("code", "2");
        NetUtils.getInstance().get(MyApp.token, Api.EmployeeUser.getVerificationCode, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject object = new JSONObject(response);
                    int code = object.getInt("resultCode");
                    if (code == 0) {
                        mTimeCount.start();
                        Toast.makeText(UpdatePaswordActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_LONG).show();
                        //注册,Upda
                    } else {
                        Toast.makeText(UpdatePaswordActivity.this, "获取验证码异常", Toast.LENGTH_LONG).show();
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

    private boolean checkInput() {
        boolean isOk = true;

        code = et_code.getEditableText().toString();
        pwd = et_pwd.getEditableText().toString();

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

    //注册
    private void resetPwd() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("telephone", phone);
        httpParams.put("code", code);
        httpParams.put("password", pwd);
        NetUtils.getInstance().post(Api.EmployeeUser.resetPassword, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                handleHttp(response);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void handleHttp(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("resultCode");
            String message = jsonObject.getString("message");
            if (resultCode == 0) {

                ToastUtils.showShort("重置成功");
                login(pwd);

            } else {
                ToastUtils.showShort(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //自动登录
    private void login(String pwd) {
        Map<String, Object> params = new HashMap<>();

        params.put("password", pwd);
        params.put("username", phone);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.EmployeeUser.login, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                handleHttp(response, 0);
                LogUtils.e(response);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void loginChat(String userName, String password, String id) {

        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();

                    }
                });

                Log.e("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.e("main", "登录聊天服务器失败！");
            }
        });
    }

    private void handleHttp(String result, int code) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("resultCode");
            String message = jsonObject.getString("message");
            if (resultCode == 0) {
                switch (code) {
                    case 0:
                        JSONObject data = jsonObject.getJSONObject("data");
                        UserInfo userInfo = new Gson().fromJson(data.toString(), UserInfo.class);
                        SPUtils.getInstance().put("user", data.toString());
                        SPUtils.getInstance().put("token", userInfo.getToken());
                        MyApp.token = userInfo.getToken();
                        MyApp.setUserInfo(userInfo);
                        loginChat(MyApp.getUserInfo().getHxUsername(), MyApp.getUserInfo().getHxPassword(), MyApp.getUserInfo().getHxUsername());


                        break;
                }
            } else {
                ToastUtils.showShort(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
