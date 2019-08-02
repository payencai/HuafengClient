package com.huafeng.client.ui.home.activity.order;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.huafeng.client.ui.home.activity.select.SelectClientActivity;
import com.huafeng.client.ui.home.activity.select.SelectModelSizeActivity;
import com.huafeng.client.ui.home.adapter.OrderSelectSizeAdapter;
import com.huafeng.client.ui.home.model.BigModel;
import com.huafeng.client.ui.home.model.Client;
import com.huafeng.client.ui.home.model.ModelDetail;
import com.huafeng.client.ui.home.model.OrderCreate;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.MyGridView;
import com.lzy.okgo.model.HttpParams;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSaleOrderActivity extends BaseActivity {
    Client client;
    BigModel bigModel;
    @BindView(R.id.tv_client)
    TextView tv_client;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_product)
    TextView tv_product;
    @BindView(R.id.et_price)
    CursorEditText et_price;
    @BindView(R.id.et_number)
    CursorEditText et_number;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.gv_size)
    MyGridView gv_size;
    String city;
    String province;
    OrderSelectSizeAdapter orderSelectSizeAdapter;
    List<ModelDetail.SizeListBean> sizeListBeans;
    String remarks;
    int clientRecordId = 0;
    double unitPrice;
    String shippingAddress;
    int sampleId;
    int quantity;
    String address;
    ModelDetail modelDetail;
    List<String> addressList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        sizeListBeans = new ArrayList<>();
        orderSelectSizeAdapter = new OrderSelectSizeAdapter(this, sizeListBeans);
        gv_size.setAdapter(orderSelectSizeAdapter);
    }

    @OnClick({R.id.rl_client, R.id.rl_addr, R.id.iv_back, R.id.tv_add, R.id.rl_product})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.rl_product:
                if (client == null) {
                    ToastUtils.showShort("请先选择客户！");
                    return;
                }
                Intent intent = new Intent(AddSaleOrderActivity.this, SelectModelSizeActivity.class);
                intent.putExtra("id", client.getId());
                startActivityForResult(intent, 2);
                break;
            case R.id.rl_client:
                startActivityForResult(new Intent(AddSaleOrderActivity.this, SelectClientActivity.class), 1);
                break;
            case R.id.rl_addr:
                if (client == null) {
                    ToastUtils.showShort("请先选择客户！");
                    return;
                }
                if(!TextUtils.isEmpty(client.getReceivingAddress()))
                   showEmployDialog();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                if (checkInput()) {
                    OrderCreate orderCreate = new OrderCreate();
                    orderCreate.setSampleId(bigModel.getId());
                    orderCreate.setClientRecordId(client.getId());
                    int number = Integer.parseInt(et_number.getEditableText().toString());
                    double price = Double.parseDouble(et_price.getEditableText().toString());
                    orderCreate.setQuantity(number);
                    orderCreate.setUnitPrice(price);
                    orderCreate.setShippingAddress(address);
                    orderCreate.setRemarks(remarks);
                    List<OrderCreate.SizeQuantityListBean> sizeQuantityListBeanList = new ArrayList<>();
                    for (int i = 0; i < sizeListBeans.size(); i++) {
                        ModelDetail.SizeListBean sizeListBean = sizeListBeans.get(i);
                        if (!TextUtils.isEmpty(sizeListBean.getInput())) {
                            OrderCreate.SizeQuantityListBean sizeQuantityListBean = new OrderCreate.SizeQuantityListBean();
                            sizeQuantityListBean.setSizeId(sizeListBean.getSizeId());
                            sizeQuantityListBean.setQuantity(Integer.parseInt(sizeListBean.getInput()));
                            sizeQuantityListBeanList.add(sizeQuantityListBean);
                        }
                    }
                    orderCreate.setSizeQuantityList(sizeQuantityListBeanList);
                    String json = new Gson().toJson(orderCreate);
                    Intent intent1 = new Intent(AddSaleOrderActivity.this, ConfirmOrderActivity.class);
                    intent1.putExtra("id", bigModel.getId());
                    intent1.putExtra("data", orderCreate);
                    startActivityForResult(intent1, 3);
                    Log.e("json", json);
                }
                break;
        }
    }

    private void showEmployDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_type, null);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        LoopView loopView = (LoopView) view.findViewById(R.id.loopView);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                tv_address.setText(addressList.get(loopView.getSelectedItem()));
            }
        });


        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                address = addressList.get(index);
            }
        });
        // 设置原始数据
        loopView.setItems(addressList);
        loopView.setNotLoop();
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 2:
                    bigModel = (BigModel) data.getSerializableExtra("data");
                    tv_product.setText(bigModel.getSampleNo());
                    sizeListBeans.clear();
                    getDetail();
                    break;
                case 1:
                    client = (Client) data.getSerializableExtra("data");
                    bigModel = null;
                    sizeListBeans.clear();
                    orderSelectSizeAdapter.notifyDataSetChanged();
                    tv_product.setText("请选择");
                    tv_client.setText(client.getName());

                    if (!TextUtils.isEmpty(client.getReceivingAddress())) {
                        addressList = StringUtils.StringToArrayList(client.getReceivingAddress(), ",");
                        address = addressList.get(0);
                        tv_address.setText(address);
                    }else{
                        tv_address.setText("改客户暂时没有地址");
                    }

                    break;
                case 3:
                    if (resultCode == RESULT_OK)
                        finish();
                    break;
            }
        }
    }

    private void getDetail() {

        HttpParams httpParams = new HttpParams();
        httpParams.put("id", bigModel.getId());
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        modelDetail = new Gson().fromJson(data.toString(), ModelDetail.class);
                        sizeListBeans.addAll(modelDetail.getSizeList());
                        orderSelectSizeAdapter.notifyDataSetChanged();
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

    private boolean checkInput() {
        boolean isOk = true;
        remarks = et_remark.getEditableText().toString();
        String unitPrice = et_price.getEditableText().toString();
        String number = et_number.getEditableText().toString();
        if (client == null) {
            isOk = false;
            ToastUtils.showShort("请选择客户");
            return isOk;
        }
        if (bigModel == null) {
            isOk = false;
            ToastUtils.showShort("请选择产品");
            return isOk;
        }
        if (TextUtils.isEmpty(unitPrice)) {
            isOk = false;
            ToastUtils.showShort("请输入单价");
            return isOk;
        }
        if (TextUtils.isEmpty(number)) {
            isOk = false;
            ToastUtils.showShort("请输入数量");
            return isOk;
        }
        return isOk;
    }

}
