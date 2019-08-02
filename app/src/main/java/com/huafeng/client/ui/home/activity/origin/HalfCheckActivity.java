package com.huafeng.client.ui.home.activity.origin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.start.activity.Result;
import com.huafeng.client.view.CursorEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalfCheckActivity extends AppCompatActivity {
    int type=1;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_item1)
    CursorEditText et_item1;
    @BindView(R.id.et_item2)
    CursorEditText et_item2;
    @BindView(R.id.et_item3)
    CursorEditText et_item3;
    @BindView(R.id.et_item4)
    CursorEditText et_item4;
    @BindView(R.id.et_item5)
    CursorEditText et_item5;
    @BindView(R.id.et_des)
    EditText etDes;
    String url;
    int id=0;
    HalfCheckSubmit halfCheckSubmit=new HalfCheckSubmit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_half_check);
        ButterKnife.bind(this);
        type=getIntent().getIntExtra("type",0);
        id=getIntent().getIntExtra("id",0);
        initView();
    }

    private void initView() {
        if(type==1){
            url= Api.Inventory.halfCheck;
            tv_title.setText("盘点");
        }else{
            url=Api.Inventory.halfOut;
            tv_title.setText("出库");
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_submit})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkInput()) {
                    submit();
                }
                break;

        }
    }

    private void submit() {
        halfCheckSubmit.setSampleId(id);
        halfCheckSubmit.setQuantity(Integer.parseInt(et_item1.getEditableText().toString().trim()));
        halfCheckSubmit.setRemarks((etDes.getEditableText().toString().trim()));
        halfCheckSubmit.setClothQuantity(Integer.parseInt(et_item5.getEditableText().toString().trim()));
        halfCheckSubmit.setSewQuantity(Integer.parseInt(et_item4.getEditableText().toString().trim()));
        halfCheckSubmit.setRepairQuantity(Integer.parseInt(et_item3.getEditableText().toString().trim()));
        halfCheckSubmit.setWashQuantity(Integer.parseInt(et_item2.getEditableText().toString().trim()));
        String json=new Gson().toJson(halfCheckSubmit);
        NetUtils.getInstance().post(Api.Inventory.halfCheck, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                Result result= GsonUtils.fromJson(response,Result.class);
                if(result.getResultCode()==0){
                    ToastUtils.showShort("提交成功！");
                    finish();
                }else{
                    ToastUtils.showShort(result.getMessage());
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private boolean checkInput() {
        boolean isOk=true;
        if(TextUtils.isEmpty(et_item1.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        if(TextUtils.isEmpty(et_item2.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        if(TextUtils.isEmpty(et_item3.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        if(TextUtils.isEmpty(et_item4.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        if(TextUtils.isEmpty(et_item5.getEditableText().toString().trim())){
            isOk=false;
            ToastUtils.showShort("数量不能为空！");
            return isOk;
        }
        return isOk;
    }
}
