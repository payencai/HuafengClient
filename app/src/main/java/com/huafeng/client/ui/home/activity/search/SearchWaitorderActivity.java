package com.huafeng.client.ui.home.activity.search;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.huafeng.client.ui.home.activity.buy.WaitBuy;
import com.huafeng.client.ui.home.activity.buy.WaitBuyDetailActivity;
import com.huafeng.client.ui.home.activity.buy.WaitBuyAdapter;

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

public class SearchWaitorderActivity extends BaseActivity {

    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    @BindView(R.id.et_no)
    EditText et_no;
    int page = 1;
    boolean isLoadMore = false;
    WaitBuyAdapter mWaitBuyAdapter;
    List<WaitBuy> mWaitBuys;
    String orderNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_waitorder);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    private void refresh() {
        page = 1;
        mWaitBuys.clear();
        mWaitBuyAdapter.setNewData(mWaitBuys);
        getData();
    }
    private void initView() {
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        mWaitBuys = new ArrayList<>();
        mWaitBuyAdapter = new WaitBuyAdapter(R.layout.item_wait_order, mWaitBuys);
        mWaitBuyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                WaitBuy waitBuy= (WaitBuy) adapter.getItem(position);
                Intent intent=new Intent(SearchWaitorderActivity.this, WaitBuyDetailActivity.class);
                intent.putExtra("id",waitBuy.getId());
                startActivityForResult(intent,1);
            }
        });
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        rv_order.setAdapter(mWaitBuyAdapter);
        et_no.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    orderNumber = et_no.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNumber)) {
                        Toast.makeText(SearchWaitorderActivity.this, "请输入搜索的订单号", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    refresh();
                    // 搜索功能主体

                    return true;
                }
                return false;
            }
        });
    }
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        httpParams.put("contactNum",orderNumber);
        httpParams.put("imageNecessary",1);
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.getWaitList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<WaitBuy> WaitBuys = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            WaitBuy WaitBuy = new Gson().fromJson(item.toString(), WaitBuy.class);
                            mWaitBuys.add(WaitBuy);
                            WaitBuys.add(WaitBuy);
                        }
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() > 0) {
                                mWaitBuyAdapter.addData(WaitBuys);
                                mWaitBuyAdapter.loadMoreComplete();
                            } else {
                                mWaitBuyAdapter.loadMoreEnd(true);
                            }
                        } else {
                            mWaitBuyAdapter.setNewData(mWaitBuys);
                        }

                    }else{
                        ToastUtils.showShort("你没有权限！");
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
