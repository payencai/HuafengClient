package com.huafeng.client.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplainActivity extends BaseActivity {
    @BindView(R.id.et_content)
    EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.iv_back, R.id.tv_submit})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                String content = et_content.getEditableText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showLong("请输入内容");
                    return;
                }
                addReback(content);
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }

    private void addReback(String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("content", content);
        String json = new Gson().toJson(params);

        NetUtils.getInstance().post(Api.EmployeeUser.addReback, json,MyApp.token,  new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
                        finish();
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
}
