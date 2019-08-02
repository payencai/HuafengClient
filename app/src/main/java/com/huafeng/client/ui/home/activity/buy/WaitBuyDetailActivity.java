package com.huafeng.client.ui.home.activity.buy;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaitBuyDetailActivity extends BaseActivity {
    @BindView(R.id.rv_wait)
    RecyclerView rv_wait;
    List<WaitBuyDetail> waitBuyDetails;
    WaitBuyDetailAdapter waitBuyDetailAdapter;
    int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_buy_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        groupId = getIntent().getIntExtra("id", 0);
        showLoading();
        initView();
    }
    KProgressHUD kProgressHUD;
    private void showLoading(){
        kProgressHUD= KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(1000)
                .setCancellable(true)
                .setDimAmount(0.5f)
                .show();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 500);//3秒后执行TimeTask的run方法
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitBuyDetailActivity.this,CreateAskActivity.class);
                intent.putExtra("id",groupId);
                startActivityForResult(intent,3);
            }
        });
        waitBuyDetails = new ArrayList<>();
        waitBuyDetailAdapter = new WaitBuyDetailAdapter(R.layout.item_wait_detail, waitBuyDetails);
        rv_wait.setLayoutManager(new LinearLayoutManager(this));
        rv_wait.setAdapter(waitBuyDetailAdapter);
        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("groupId", groupId);
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.getDetailList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            WaitBuyDetail waitBuyDetail = new Gson().fromJson(item.toString(), WaitBuyDetail.class);
                            waitBuyDetails.add(waitBuyDetail);
                        }
                        waitBuyDetailAdapter.setNewData(waitBuyDetails);

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
        waitBuyDetails.clear();
        getData();
    }
}
