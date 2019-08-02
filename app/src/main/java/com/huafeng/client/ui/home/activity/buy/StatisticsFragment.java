package com.huafeng.client.ui.home.activity.buy;


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
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
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
public class StatisticsFragment extends Fragment {


    @BindView(R.id.rv_statis)
    RecyclerView rv_design;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    StatisticeAdapter mBuyOrderAdapter;
    List<StatisticsOrigin> mBuyOrders;

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_statistics, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void refresh() {

        mBuyOrders.clear();
        mBuyOrderAdapter.setNewData(mBuyOrders);
        getData();
    }
    private void initView() {
        mBuyOrders=new ArrayList<>();
        mBuyOrderAdapter=new StatisticeAdapter(mBuyOrders);
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
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.materialCount, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refreshLayout.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            StatisticsOrigin buyOrder = new Gson().fromJson(item.toString(), StatisticsOrigin.class);
                            mBuyOrders.add(buyOrder);
                        }
                        mBuyOrderAdapter.setNewData(mBuyOrders);

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
