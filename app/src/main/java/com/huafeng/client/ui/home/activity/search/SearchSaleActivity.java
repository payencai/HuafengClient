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
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.order.OrderDetailActivity;
import com.huafeng.client.ui.home.adapter.OrderAdapter;
import com.huafeng.client.ui.home.model.Order;
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

public class SearchSaleActivity extends BaseActivity {
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    @BindView(R.id.et_no)
    EditText et_no;
    OrderAdapter mOrderAdapter;
    List<Order> mOrders;
    int state;
    int page=1;
    boolean isLoadMore=false;
    String orderNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sale);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    private void refresh(){
        page=1;
        mOrders.clear();
        mOrderAdapter.setNewData(mOrders);
        getData();
    }
    private void initView() {
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mOrders=new ArrayList<>();
        mOrderAdapter=new OrderAdapter(R.layout.item_home_order,mOrders);
        mOrderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                isLoadMore=true;
                getData();
            }
        },rv_order);
        mOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Order order= (Order) adapter.getItem(position);
                Intent intent=new Intent(SearchSaleActivity.this, OrderDetailActivity.class);
                intent.putExtra("id",order.getId());
                intent.putExtra("state",order.getStatus());
                startActivityForResult(intent,1);

            }
        });
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        rv_order.setAdapter(mOrderAdapter);
        et_no.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    orderNumber = et_no.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNumber)) {
                        Toast.makeText(SearchSaleActivity.this, "请输入搜索的订单号", Toast.LENGTH_SHORT).show();
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
    private  void getData(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("page",page);
        if(!TextUtils.isEmpty(orderNumber)){
            httpParams.put("sampleNo",orderNumber);
        }
        httpParams.put("imageNecessary",1);
        NetUtils.getInstance().get(MyApp.token, Api.Order.getOrderList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        List<Order> orderList=new ArrayList<>();
                        JSONArray data=jsonObject.getJSONArray("beanList");
                        for (int i = 0; i <data.length() ; i++) {
                            JSONObject item=data.getJSONObject(i);
                            Order modelDesign=new Gson().fromJson(item.toString(),Order.class);
                            orderList.add(modelDesign);
                        }
                        mOrderAdapter.addData(orderList);
                        if(isLoadMore){
                            isLoadMore=false;
                            if(data.length()>0){
                                mOrderAdapter.loadMoreComplete();
                            }else{
                                mOrderAdapter.loadMoreEnd(true);
                            }
                        }else{
                            mOrderAdapter.loadMoreComplete();
                        }

                    }else{
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
