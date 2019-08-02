package com.huafeng.client.ui.home.activity.origin;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.select.SelectSupplierActivity;
import com.huafeng.client.ui.home.model.Supplier;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.MathUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryOriginDataActivity extends BaseActivity {
    Supplier supplier;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.et_price)
    CursorEditText et_price;
    @BindView(R.id.et_num)
    CursorEditText et_num;
    @BindView(R.id.et_cloth)
    CursorEditText et_cloth;
    @BindView(R.id.rl_clothes)
    RelativeLayout rl_clothes;
    @BindView(R.id.et_remark)
    EditText et_remark;
    boolean isShow;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_origin_data);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        isShow = getIntent().getBooleanExtra("isShow", false);
        id = getIntent().getIntExtra("id", 0);
        if (isShow) {
            rl_clothes.setVisibility(View.VISIBLE);
        }
    }

    private void submit() {
        Map<String, Object> params = new HashMap<>();
        params.put("materialId", id);
        params.put("supplierId", supplier.getId());
        params.put("unitPrice", Double.valueOf(et_price.getEditableText().toString()));
        params.put("normalQuantity", Double.valueOf(et_num.getEditableText().toString()));

        params.put("remarks", et_remark.getEditableText().toString());
        if (isShow) {
            params.put("clothQuantity", et_cloth.getEditableText().toString());
        }
        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.entry, json, MyApp.token, new OnMessageReceived() {
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

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.rl_shop})
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
            case R.id.rl_shop:
                Intent intent = new Intent(EntryOriginDataActivity.this, SelectSupplierActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null && requestCode == 1) {
            supplier = (Supplier) data.getSerializableExtra("data");
            tv_name.setText(supplier.getName());
        }

    }

    private double calTotal(ArrayList<String> cloths) {
        double total = 0;
        for (int i = 0; i < cloths.size(); i++) {
            LogUtils.e(cloths.get(i));
            if (MathUtils.isNumber(cloths.get(i))) {
                total = total + Double.parseDouble(cloths.get(i));
            }
        }
        return total;
    }

    private boolean checkInput() {
        boolean isOk = true;

        if (TextUtils.isEmpty(et_price.getEditableText().toString().trim())) {
            isOk = false;
            ToastUtils.showShort("请输入单价");
            return isOk;
        }
        if (TextUtils.isEmpty(et_num.getEditableText().toString().trim())) {
            isOk = false;
            ToastUtils.showShort("请输入数量");
            return isOk;
        }



        if (supplier == null) {
            isOk = false;
            ToastUtils.showShort("请选择供应商");
            return isOk;
        }
        if (isShow) {
            String num = et_num.getEditableText().toString().trim();
            if (TextUtils.isEmpty(et_cloth.getEditableText().toString().trim())) {
                isOk = false;
                ToastUtils.showShort("请输入布料码数");
                return isOk;
            } else {
                String cloth = et_cloth.getEditableText().toString().trim();
                double value = calTotal(StringUtils.StringToArrayList(cloth, ","));
                Log.e("value",value+"");
                if (value != Double.parseDouble(num)) {
                    ToastUtils.showShort("输入的数量要等于布料之和");
                    isOk = false;
                    return isOk;
                }

            }

        }
        return isOk;
    }
}
