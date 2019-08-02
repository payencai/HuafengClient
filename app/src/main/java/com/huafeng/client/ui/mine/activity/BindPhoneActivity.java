package com.huafeng.client.ui.mine.activity;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
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
import butterknife.OnClick;

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_send1)
    TextView tv_send1;
    @BindView(R.id.tv_send2)
    TextView tv_send2;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code1)
    EditText et_code1;
    @BindView(R.id.et_code2)
    EditText et_code2;
    TimeCount1 mTimeCount1;
    TimeCount2 mTimeCount2;
    int count1=60;
    int count2=60;
    String code1;
    String code2;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        mTimeCount1 = new TimeCount1(60000, 1000);
        mTimeCount2 = new TimeCount2(60000, 1000);
        tv_phone.setText(MyApp.getUserInfo().getUsername());
    }

    class TimeCount1 extends CountDownTimer {

        public TimeCount1(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_send1.setEnabled(false);
            tv_send1.setTextColor(getResources().getColor(R.color.color_999));
            count1--;
            //倒计时的过程中回调该函数
            tv_send1.setText(count1 + "s");
        }

        @Override
        public void onFinish() {
            count1=60;
            tv_send1.setText("重新获取");
            tv_send1.setEnabled(true);
            tv_send1.setTextColor(getResources().getColor(R.color.color_333));
            //倒计时结束时回调该函数
        }
    }
    class TimeCount2 extends CountDownTimer {

        public TimeCount2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_send2.setEnabled(false);
            tv_send2.setTextColor(getResources().getColor(R.color.color_999));
            count2--;
            //倒计时的过程中回调该函数
            tv_send2.setText(count2 + "s");
        }

        @Override
        public void onFinish() {
            count2=60;
            tv_send2.setText("重新获取");
            tv_send2.setEnabled(true);
            tv_send2.setTextColor(getResources().getColor(R.color.color_333));
            //倒计时结束时回调该函数
        }
    }
    private void getCodeByType1(String phone) {
        HttpParams params = new HttpParams();
        params.put("telephone", phone);
        params.put("code", "3");
        NetUtils.getInstance().get(MyApp.token, Api.EmployeeUser.getVerificationCode, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject object = new JSONObject(response);
                    int code = object.getInt("resultCode");
                    if (code == 0) {
                        mTimeCount1.start();
                        Toast.makeText(BindPhoneActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_LONG).show();
                        //注册,Upda
                    } else {
                        Toast.makeText(BindPhoneActivity.this, "获取验证码异常", Toast.LENGTH_LONG).show();
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
    private void getCodeByType2(String phone) {
        HttpParams params = new HttpParams();
        params.put("telephone", phone);
        params.put("code", "3");
        NetUtils.getInstance().get(MyApp.token, Api.EmployeeUser.getVerificationCode, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject object = new JSONObject(response);
                    int code = object.getInt("resultCode");
                    if (code == 0) {
                        mTimeCount2.start();
                        Toast.makeText(BindPhoneActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_LONG).show();
                        //注册,Upda
                    } else {
                        Toast.makeText(BindPhoneActivity.this, "获取验证码异常", Toast.LENGTH_LONG).show();
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
    @OnClick({R.id.tv_send1, R.id.tv_submit, R.id.back, R.id.tv_send2})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send2:
                String phone=et_phone.getEditableText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    getCodeByType2(phone);
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tv_send1:

                getCodeByType1(MyApp.getUserInfo().getUsername());
                break;
            case R.id.tv_submit:
                code1=et_code1.getEditableText().toString();
                code2=et_code2.getEditableText().toString();
                phone=et_phone.getEditableText().toString();
                if(TextUtils.isEmpty(phone)){
                    ToastUtils.showShort("请输入新手机号");
                    return;
                }
                if(TextUtils.isEmpty(code1)||TextUtils.isEmpty(code2)){
                    ToastUtils.showShort("请输入验证码");
                    return;
                }
                submit();
                //showUpdateNickDialog();
                break;
        }
    }
    private void submit(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("telephone",MyApp.getUserInfo().getUsername());
        httpParams.put("code",code1);
        httpParams.put("newTelephone",phone);
        httpParams.put("newCode",code2);
        NetUtils.getInstance().post(Api.EmployeeUser.exchangeTelephone, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int resultCode = jsonObject.getInt("resultCode");
                    String message = jsonObject.getString("message");
                    if (resultCode == 0) {
                        ToastUtils.showShort("换绑成功");
                        finish();
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

}
