package com.huafeng.client.clientui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.clientui.ClientModelDetail;
import com.huafeng.client.clientui.ClientOrderSizeAdapter;
import com.huafeng.client.clientui.ClientOrderSubmit;
import com.huafeng.client.clientui.SelectSample;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.view.MathUtils;
import com.huafeng.client.view.MyGridView;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientCreateOrderActivity extends AppCompatActivity {
    @BindView(R.id.gv_size)
    MyGridView gv_size;
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.tv_no)
    TextView tv_no;
    @BindView(R.id.et_price)
    EditText et_price;
    @BindView(R.id.et_num)
    EditText et_num;
    @BindView(R.id.et_remark)
    EditText et_remark;
    int id;
    ClientModelDetail clientModelDetail;
    ClientOrderSizeAdapter clientOrderSizeAdapter;
    ClientOrderSubmit clientOrderSubmit;
    SelectSample selectSample;
    List<ClientOrderSubmit.SizeQuantityListBean> sizeQuantityListBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_create_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        clientOrderSubmit = new ClientOrderSubmit();
        initView();
    }

    private void initView() {
        sizeQuantityListBeans = new ArrayList<>();
        clientOrderSizeAdapter = new ClientOrderSizeAdapter(this, sizeQuantityListBeans);
        gv_size.setAdapter(clientOrderSizeAdapter);
        if (id > 0) {
            getDetail();
            tv_no.setEnabled(false);
        }
    }

    private boolean checkInput() {
        boolean isOk = true;
        String num = et_num.getEditableText().toString();
        String price = et_price.getEditableText().toString();
        String address = et_address.getEditableText().toString();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.showLong("地址输入不能为空");
            isOk = false;
            return isOk;
        }
        if (TextUtils.isEmpty(num)) {
            ToastUtils.showLong("数量输入不能为空");
            isOk = false;
            return isOk;
        }
        if (TextUtils.isEmpty(price)) {
            ToastUtils.showLong("价格输入不能为空");
            isOk = false;
            return isOk;
        }
        if (id == 0) {
            ToastUtils.showLong("请选择款号");
            isOk = false;
            return isOk;
        }
        if (!MathUtils.isNumber(price)) {
            ToastUtils.showLong("价格只能是数字");
            isOk = false;
            return isOk;
        }
        if (!MathUtils.isInteger(num)) {
            ToastUtils.showLong("数量只能是数字");
            isOk = false;
            return isOk;
        }
        clientOrderSubmit.setRemarks(et_remark.getEditableText().toString());
        clientOrderSubmit.setQuantity(Integer.parseInt(num));
        clientOrderSubmit.setSampleId(id);
        clientOrderSubmit.setUnitPrice(Double.parseDouble(price));
        clientOrderSubmit.setShippingAddress(address);
        List<ClientOrderSubmit.SizeQuantityListBean> quantityListBeans = new ArrayList<>();
        for (int i = 0; i < sizeQuantityListBeans.size(); i++) {
            ClientOrderSubmit.SizeQuantityListBean sizeQuantityListBean = new ClientOrderSubmit.SizeQuantityListBean();
            sizeQuantityListBean.setQuantity(sizeQuantityListBeans.get(i).getQuantity());
            sizeQuantityListBean.setSizeId(sizeQuantityListBeans.get(i).getSizeId());
            sizeQuantityListBean.setSizeName(sizeQuantityListBeans.get(i).getSizeName());
            quantityListBeans.add(sizeQuantityListBean);
        }
        clientOrderSubmit.setSizeQuantityList(quantityListBeans);
        return isOk;
    }

    public void addOrder() {
        String json = new Gson().toJson(clientOrderSubmit);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Sample.create, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showLong("提交成功");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && data != null) {
            selectSample = (SelectSample) data.getSerializableExtra("data");
            id = selectSample.getId();
            sizeQuantityListBeans.clear();
            getDetail();
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.tv_no})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.tv_no:
                startActivityForResult(new Intent(ClientCreateOrderActivity.this, SelectClientSampleActivity.class), 1);
                break;
            case R.id.tv_submit:
                if (checkInput())
                    addOrder();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getForCustomer, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        clientModelDetail = new Gson().fromJson(data.toString(), ClientModelDetail.class);
                        initData();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                LogUtils.e(error);
            }
        });
    }

    private void initData() {
        tv_no.setText(clientModelDetail.getSampleNo());
        for (int i = 0; i < clientModelDetail.getSizeList().size(); i++) {
            ClientOrderSubmit.SizeQuantityListBean sizeQuantityListBean = new ClientOrderSubmit.SizeQuantityListBean();
            sizeQuantityListBean.setQuantity(0);
            sizeQuantityListBean.setSizeId(clientModelDetail.getSizeList().get(i).getSizeId());
            sizeQuantityListBean.setSizeName(clientModelDetail.getSizeList().get(i).getSizeName());
            sizeQuantityListBeans.add(sizeQuantityListBean);
        }
        clientOrderSizeAdapter.notifyDataSetChanged();
    }
}
