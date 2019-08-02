package com.huafeng.client.ui.home.activity.client;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

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
import com.huafeng.client.ui.home.activity.search.SearchClientActivity;
import com.huafeng.client.ui.home.model.CustomerData;
import com.huafeng.client.view.sidebar.IndexableAdapter;
import com.huafeng.client.view.sidebar.IndexableLayout;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomManaActivity extends BaseActivity {


    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    ClientContactAdapter mAdapter;
    List<CustomerData> mDatas;
    int page = 1;
    boolean isEnter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_mana);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        EventBus.getDefault().register(this);
        showLoading();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    KProgressHUD kProgressHUD;
    private void showLoading(){
        kProgressHUD= KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(1000)
                .setCancellable(false)
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
    @OnClick({R.id.iv_add, R.id.iv_back,R.id.iv_search})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                startActivityForResult(new Intent(CustomManaActivity.this, SearchClientActivity.class), 1);
                break;
            case R.id.iv_add:
                startActivityForResult(new Intent(CustomManaActivity.this, AddCustomerActivity.class), 1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onNotice(MsgEvent  msg){
        if(msg.getmMsg()==400){
            refresh();
        }
    }
    private void refresh(){
        page = 1;
        mDatas.clear();
        getData();
    }
    private void getAuth() {

        NetUtils.getInstance().get(MyApp.token, Api.Authority.getMyAuthority, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            int authId = data.getInt(i);
                            if (authId == 1071) {
                                isEnter = true;
                                iv_add.setVisibility(View.VISIBLE);
                                break;
                            }

                        }
                    } else {

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

    private void setList() {
        mAdapter = new ClientContactAdapter(this);
        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CustomerData>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CustomerData entity) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                if (isEnter = true) {
                    CustomerData customerData = mDatas.get(originalPosition);
                    Intent intent = new Intent(CustomManaActivity.this, ClientDetailActivity.class);
                    intent.putExtra("id", customerData.getId());
                    intent.putExtra("clientid", customerData.getClientUserId());
                    startActivity(intent);
                } else {
                    ToastUtils.showShort("你没有权限");
                }

            }
        });

        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        indexableLayout.setOverlayStyle_MaterialDesign(Color.RED);
        indexableLayout.setCompareMode(IndexableLayout.MODE_ALL_LETTERS);
        mAdapter.setDatas(mDatas);
        indexableLayout.setAdapter(mAdapter);

    }


    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        httpParams.put("imageNecessary",1);
        NetUtils.getInstance().get(MyApp.token, Api.Client.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            CustomerData modelDesign = new Gson().fromJson(item.toString(), CustomerData.class);
                            mDatas.add(modelDesign);
                        }
                        mAdapter.notifyDataSetChanged();
                        refresh.finishLoadMore();
                        refresh.finishRefresh();
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
        refresh();
    }

    private void initView() {
        mDatas = new ArrayList<>();
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        refresh.setEnableRefresh(false);
        getAuth();
        setList();
        getData();
    }
}
