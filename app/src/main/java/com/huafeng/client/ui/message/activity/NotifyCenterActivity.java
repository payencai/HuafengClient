package com.huafeng.client.ui.message.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
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
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.apply.ApprovalApplyDetailActivity;
import com.huafeng.client.ui.home.activity.design.DesignDetailActivity;
import com.huafeng.client.ui.home.activity.order.OrderDetailActivity;
import com.huafeng.client.ui.home.activity.produce.ProduceDetailActivity;
import com.huafeng.client.ui.message.adapter.NotifyAdapter;
import com.huafeng.client.ui.message.model.Notify;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotifyCenterActivity extends BaseActivity {
    @BindView(R.id.rv_notify)
    RecyclerView rv_notify;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    List<Notify> notifies;
    NotifyAdapter notifyAdapter;
    int page = 1;
    boolean isLoadMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_center);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    private void readMsg(){
        NetUtils.getInstance().post(Api.Push.read,"", MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        notifies.clear();
        page=1;
        getData();
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                notifies.clear();
                getData();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        notifies = new ArrayList<>();
        notifyAdapter = new NotifyAdapter(R.layout.item_notice, notifies);
        notifyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isLoadMore = true;
                page++;
                getData();
            }
        });
        notifyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                Notify notify= (Notify) adapter.getItem(position);
                if(notify.getPush().getIsLinked()==0){
                    return;
                }else{
                    Intent intent=null;
                    switch (notify.getPush().getType()){
                        case 1:
                            intent=new Intent(NotifyCenterActivity.this, DesignDetailActivity.class);
                            intent.putExtra("id",notify.getPush().getServiceId());
                            break;
                        case 2:
                            intent=new Intent(NotifyCenterActivity.this, ProduceDetailActivity.class);
                            intent.putExtra("id",notify.getPush().getServiceId());
                            break;
                        case 3:
                        case 4:
                            intent=new Intent(NotifyCenterActivity.this, ApprovalApplyDetailActivity.class);
                            intent.putExtra("id",notify.getPush().getServiceId());
                            intent.putExtra("flag",2);
                            break;
                        case 5:
                            intent=new Intent(NotifyCenterActivity.this, OrderDetailActivity.class);
                            intent.putExtra("id",notify.getPush().getServiceId());
                            break;
                    }
                    startActivityForResult(intent,1);
                }
            }
        });
        rv_notify.setLayoutManager(new LinearLayoutManager(this));
        rv_notify.setAdapter(notifyAdapter);
        getData();
        readMsg();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        NetUtils.getInstance().get(MyApp.token, Api.Push.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refreshLayout.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<Notify> notifyList = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            Notify notify = new Gson().fromJson(item.toString(), Notify.class);
                            notifyList.add(notify);
                        }
                        notifyAdapter.addData(notifyList);
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() > 0) {
                                notifyAdapter.loadMoreComplete();
                            } else {
                                notifyAdapter.loadMoreEnd(true);
                            }
                        } else {
                            notifyAdapter.loadMoreComplete();
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
