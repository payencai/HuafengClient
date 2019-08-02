package com.huafeng.client.ui.start.activity;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordActivity extends BaseActivity {
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
    int count=60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
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
            count=60;
            tv_send.setText("重新获取");
            tv_send.setEnabled(true);
            tv_send.setTextColor(getResources().getColor(R.color.color_333));
            //倒计时结束时回调该函数
        }
    }
    @OnClick({R.id.tv_send,R.id.tv_submit,R.id.iv_back})
    void onClick(View view){
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()){
            case R.id.tv_send:
                String phone=et_phone.getEditableText().toString();
                if(!TextUtils.isEmpty(phone)){
                    getCodeByType(phone);
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if(checkInput())
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
                        Toast.makeText(FindPasswordActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_LONG).show();
                        //注册,并且登录
                    } else {
                        Toast.makeText(FindPasswordActivity.this, "获取验证码异常", Toast.LENGTH_LONG).show();
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
    private  boolean checkInput(){
        boolean isOk=true;
        phone=et_phone.getEditableText().toString();
        code=et_code.getEditableText().toString();
        pwd=et_pwd.getEditableText().toString();
        if(TextUtils.isEmpty(phone)){
            ToastUtils.showShort("手机号不能为空");
            isOk=false;
            return  isOk;
        }
        if(TextUtils.isEmpty(code)){
            isOk=false;
            ToastUtils.showShort("验证码不能为空");
            return  isOk;
        }
        if(TextUtils.isEmpty(pwd)){
            isOk=false;
            ToastUtils.showShort("密码不能为空");
            return  isOk;
        }
        return isOk;
    }
    //注册
    private void resetPwd(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("telephone",phone);
        httpParams.put("code",code);
        httpParams.put("password",pwd);
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
    private void handleHttp(String result){
        try {
            JSONObject jsonObject=new JSONObject(result);
            int resultCode=jsonObject.getInt("resultCode");
            String message=jsonObject.getString("message");
            if(resultCode==0){
                finish();
                ToastUtils.showShort("重置成功");
            }else{
                ToastUtils.showShort(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
