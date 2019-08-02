package com.huafeng.client.clientui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientStorageActivity extends AppCompatActivity {

    @BindView(R.id.rv_model)
    RecyclerView rv_model;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    int page = 1;
    boolean isLoadMore = false;

    ClientStorageAdapter clientModelDataAdapter;
    List<ClientStorage> clientModelDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_storage);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
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
        clientModelDataList = new ArrayList<>();
        clientModelDataAdapter = new ClientStorageAdapter(clientModelDataList);
        clientModelDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ClientModelData clientModelData= (ClientModelData) adapter.getItem(position);
//                Intent intent=new Intent(ClientStorageActivity.this, ClientModelDetailActivity.class);
//                intent.putExtra("id",clientModelData.getId());
//                startActivity(intent);
            }
        });
        rv_model.setLayoutManager(new LinearLayoutManager(this));
        rv_model.setAdapter(clientModelDataAdapter);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();
    }

    private void refresh() {
        page = 1;
        clientModelDataList.clear();
        clientModelDataAdapter.setNewData(clientModelDataList);
        getData();
    }


    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getListForCustomer, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<ClientStorage> modelDesigns = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            ClientStorage modelDesign = new Gson().fromJson(item.toString(), ClientStorage.class);
                            modelDesigns.add(modelDesign);
                        }
                        clientModelDataAdapter.addData(modelDesigns);
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() > 0) {
                                clientModelDataAdapter.loadMoreComplete();
                            } else {
                                clientModelDataAdapter.loadMoreEnd(true);
                            }
                        } else {
                            clientModelDataAdapter.loadMoreComplete();
                        }
                    } else {
                        ToastUtils.showShort("你没有该权限");
                        finish();
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
