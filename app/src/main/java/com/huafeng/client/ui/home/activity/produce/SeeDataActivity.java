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

public class SeeDataActivity extends BaseActivity {
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
    @BindView(R.id.tv_item7)
    TextView tv_item7;
    @BindView(R.id.tv_item8)
    TextView tv_item8;
    int id;
    ProduceCal produceCal;
    String value;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_data);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        value = getIntent().getStringExtra("total");
        total= Integer.parseInt(value.split(",")[0]);
        value=value.split(",")[1];
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCount();
    }

    private void initData() {
        tv_item5.setText(produceCal.getClothConsume());
        tv_item3.setText(produceCal.getMaterialName());
        tv_item2.setText(total + "");
        tv_item1.setText(value);
        tv_item8.setText(produceCal.getRemarks());
        String tvSize = "";
        double count = 0;
        String sizes[] = produceCal.getClothConsume().split(",");
        for (int i = 0; i < sizes.length; i++) {
            String sizenum = sizes[i];
            String sn[] = sizenum.split(":");
            String size = sn[0];
            String number = sn[1];
            count = count + Double.parseDouble(number);
            tvSize = tvSize + " " + size;

        }
        tv_item4.setText(tvSize);
        tv_item6.setText(count + "");
        tv_item7.setText((count/total)+"");
    }

    private void getCount() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("productionOrderFlowId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Production.getMterialConsumeAccount, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        produceCal = new Gson().fromJson(data.toString(), ProduceCal.class);
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
