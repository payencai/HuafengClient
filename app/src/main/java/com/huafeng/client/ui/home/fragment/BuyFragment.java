package com.huafeng.client.ui.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.huafeng.client.ui.home.activity.buy.WaitBuy;
import com.huafeng.client.ui.home.activity.buy.WaitBuyAdapter;
import com.huafeng.client.ui.home.activity.buy.WaitBuyDetailActivity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyFragment extends Fragment {
    @BindView(R.id.rv_design)
    RecyclerView  rv_design;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    WaitBuyAdapter mBuyOrderAdapter;
    List<WaitBuy> mBuyOrders;
    int type;
    int page = 1;
    boolean isLoadMore = false;
    public BuyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_design, container, false);
        type=getArguments().getInt("type");
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    public static BuyFragment newInstance(int state) {
        BuyFragment orderListFragment = new BuyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", state);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }
    private void refresh() {
        page = 1;
        mBuyOrders.clear();
        mBuyOrderAdapter.setNewData(mBuyOrders);
        getData();
    }
    private void initView() {
        mBuyOrders=new ArrayList<>();
        mBuyOrderAdapter=new WaitBuyAdapter(R.layout.item_wait_order,mBuyOrders);
        mBuyOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                WaitBuy waitBuy= (WaitBuy) adapter.getItem(position);
                Intent intent=new Intent(getContext(), WaitBuyDetailActivity.class);
                intent.putExtra("id",waitBuy.getId());
                startActivityForResult(intent,1);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        rv_design.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_design.setAdapter(mBuyOrderAdapter);
        getData();
    }
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        if (type > 0) {
            httpParams.put("type", type);
        }
        httpParams.put("imageNecessary",1);
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.getWaitList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refreshLayout.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<WaitBuy> buyOrders = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            WaitBuy buyOrder = new Gson().fromJson(item.toString(), WaitBuy.class);
                            mBuyOrders.add(buyOrder);
                            buyOrders.add(buyOrder);
                        }
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() > 0) {
                                mBuyOrderAdapter.addData(buyOrders);
                                mBuyOrderAdapter.loadMoreComplete();
                            } else {
                                mBuyOrderAdapter.loadMoreEnd(true);
                            }
                        } else {
                            mBuyOrderAdapter.setNewData(mBuyOrders);
                        }

                    }else{
                        ToastUtils.showShort("你没有权限！");
                        getActivity().finish();
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
