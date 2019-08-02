package com.huafeng.client.ui.home.activity.order;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.OrderConfirmAdapter;
import com.huafeng.client.ui.home.model.Materials;
import com.huafeng.client.ui.home.model.OrderCreate;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.lv_order)
    ListView lv_order;
    OrderConfirmAdapter orderConfirmAdapter;
    List<Materials> materials;
    int id;
    OrderCreate orderCreate;
    int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        orderId = getIntent().getIntExtra("orderId", 0);
        orderCreate = (OrderCreate) getIntent().getSerializableExtra("data");
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        materials = new ArrayList<>();
        orderConfirmAdapter = new OrderConfirmAdapter(this, materials, orderCreate.getQuantity());
        orderConfirmAdapter.setOnItemChangeListener(new OrderConfirmAdapter.OnItemChangeListener() {
            @Override
            public void onChange(int pos, int type) {
                Materials material = materials.get(pos);
                if (!TextUtils.isEmpty(material.getInput())) {
                    double total = Double.parseDouble(material.getInput());
                    if (type == 1) {
                        if (total > 1) {
                            total--;
                            materials.get(pos).setInput(total + "");
                            orderConfirmAdapter.notifyDataSetChanged();
                        }
                    } else {
                        total++;
                        materials.get(pos).setInput(total + "");
                        orderConfirmAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
        lv_order.setAdapter(orderConfirmAdapter);
        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                List<OrderCreate.PurchaseAssignmentListBean> purchaseAssignmentListBeans = new ArrayList<>();
                for (int i = 0; i < materials.size(); i++) {

                    OrderCreate.PurchaseAssignmentListBean purchaseAssignmentListBean = new OrderCreate.PurchaseAssignmentListBean();
                    purchaseAssignmentListBean.setType(materials.get(i).getPurchaseType());
                    if (TextUtils.isEmpty(materials.get(i).getInput())) {
                        purchaseAssignmentListBean.setQuantity(0);
                    } else {
                        purchaseAssignmentListBean.setQuantity(Double.parseDouble(materials.get(i).getInput()));
                    }
                    purchaseAssignmentListBean.setRawMaterialId(materials.get(i).getRawMaterialId());
                    purchaseAssignmentListBeans.add(purchaseAssignmentListBean);

                }
                orderCreate.setPurchaseAssignmentList(purchaseAssignmentListBeans);
                if (orderCreate.getOrderId() == 0)
                    addOrder();
                else {
                    confirmOrder();
                }
            }
        });
        getList();
    }

    public void addOrder() {
        String json = new Gson().toJson(orderCreate);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Order.create, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("创建成功");
                        EventBus.getDefault().post(new MsgEvent(100));
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
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

    public void confirmOrder() {
        String json = new Gson().toJson(orderCreate);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Order.confirm, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        EventBus.getDefault().post(new MsgEvent(100));
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
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

    private void getList() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getMaterials, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            Materials firstType = new Gson().fromJson(item.toString(), Materials.class);
                            firstType.setInput(orderCreate.getQuantity() * firstType.getQuantity() + "");
                            materials.add(firstType);
                        }
                        orderConfirmAdapter.notifyDataSetChanged();
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
