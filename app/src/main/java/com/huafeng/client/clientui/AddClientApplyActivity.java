package com.huafeng.client.clientui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.view.CursorEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddClientApplyActivity extends AppCompatActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_no)
    TextView tv_no;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.et_name)
    CursorEditText et_name;
    @BindView(R.id.et_phone)
    CursorEditText et_phone;
    @BindView(R.id.et_remark)
    EditText et_remark;
    Supplier supplier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_apply);
        ButterKnife.bind(this);
        supplier= (Supplier) getIntent().getSerializableExtra("data");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @OnClick({R.id.iv_back, R.id.tv_submit})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                String name=et_name.getEditableText().toString();
                String phone=et_phone.getEditableText().toString();
                if(TextUtils.isEmpty(name)){
                    ToastUtils.showShort("请输入姓名");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    ToastUtils.showShort("请输入联系方式");
                    return;
                }
                submit();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void submit(){
        Map<String,Object> params=new HashMap<>();
        params.put("factoryId",supplier.getId());
        params.put("contactNumber",et_phone.getEditableText().toString());
        params.put("name",et_name.getEditableText().toString());
        params.put("remarks",et_remark.getEditableText().toString());
        String json=new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Client.apply, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
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
    private void initView() {
        if(supplier!=null){
            tv_name.setText(supplier.getName());
            tv_no.setText(supplier.getSystemId());
            tv_address.setText(supplier.getAddress());
            if(!TextUtils.isEmpty(supplier.getImageUrl()))
                Glide.with(this).load(supplier.getImageUrl()).into(iv_img);
        }
    }
}
