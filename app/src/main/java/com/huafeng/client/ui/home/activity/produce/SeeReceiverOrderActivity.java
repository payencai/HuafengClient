package com.huafeng.client.ui.home.activity.produce;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
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

public class SeeReceiverOrderActivity extends BaseActivity {
    @BindView(R.id.tv_item1)
    TextView tv_item1;
    @BindView(R.id.tv_item2)
    TextView tv_item2;
    @BindView(R.id.tv_item3)
    TextView tv_item3;
    @BindView(R.id.tv_item4)
    TextView tv_item4;
    @BindView(R.id.tv_item5)
    TextView tv_item5;
    @BindView(R.id.tv_item6)
    TextView tv_item6;
    int id;
    GetOrder sendOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_receiver_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCount();
    }
    private void initData() {
        String flow="";
        for (int i = 0; i <sendOrder.getProcessNameList().size() ; i++) {
            flow=sendOrder.getProcessNameList().get(i)+" "+flow;
        }
        tv_item4.setText(flow);
        tv_item3.setText(sendOrder.getQuantity()+"");
        tv_item2.setText(sendOrder.getConfirmTime());
        tv_item1.setText(sendOrder.getNoteNo());
        tv_item5.setText(sendOrder.getSupplierName()+"");
        tv_item6.setText(sendOrder.getRemarks());

    }

    private void getCount() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Production.getPickupNote, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        sendOrder = new Gson().fromJson(data.toString(), GetOrder.class);
                        initData();
                    }else{
                        ToastUtils.showShort("你没有权限");
                        finish();
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
}
