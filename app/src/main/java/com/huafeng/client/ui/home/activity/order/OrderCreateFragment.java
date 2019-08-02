package com.huafeng.client.ui.home.activity.order;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.huafeng.client.ui.home.activity.produce.ProduceDetailActivity;
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
public class OrderCreateFragment extends Fragment {

    @BindView(R.id.rv_create)
    RecyclerView rv_create;
    @BindView(R.id.tv_num1)
    TextView tv_num1;
    @BindView(R.id.tv_num2)
    TextView tv_num2;
    @BindView(R.id.tv_num3)
    TextView tv_num3;
    @BindView(R.id.tv_create)
    TextView tv_create;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    OrderCreateAdapter orderCreateAdapter;
    List<OrderCreate> orderCreates;
    OrderDetail orderDetail;
    public OrderCreateFragment() {
        // Required empty public constructor
    }
    OrderDetailActivity activity;
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_order_create, container, false);
        ButterKnife.bind(this,view);
        activity= (OrderDetailActivity) getActivity();
        id=activity.getId();
        initView();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        orderCreates.clear();
        orderCreateAdapter.setNewData(orderCreates);
        getCreateOrder();
        getNum();
    }

    private void initView() {
        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                Intent intent=new Intent(getContext(),CreateOrderActivity.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,1);
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                orderCreates.clear();
                orderCreateAdapter.setNewData(orderCreates);
                getCreateOrder();
                getNum();
            }
        });
        orderCreates=new ArrayList<>();
        orderCreateAdapter=new OrderCreateAdapter(orderCreates);
        orderCreateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                OrderCreate create= (OrderCreate) adapter.getItem(position);
                Intent intent=new Intent(getContext(), ProduceDetailActivity.class);
                intent.putExtra("id",create.getId());
                startActivityForResult(intent,1);
            }
        });
        rv_create.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_create.setAdapter(orderCreateAdapter);
        getCreateOrder();
        getNum();
        getDetail();
    }
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Order.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        orderDetail = new Gson().fromJson(data.toString(), OrderDetail.class);
                        getAuth();
                    }else{
                        ToastUtils.showShort("你没有该权限！");
                        getActivity().finish();
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
    private void getAuth() {
        // LitePal.deleteAll(Auth.class);
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
                            if(authId==1021||authId==1023){
                                if(orderDetail.getOrders().getStatus()==2){
                                    tv_create.setVisibility(View.VISIBLE);
                                }
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
    private void getNum() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("orderId", id);

        NetUtils.getInstance().get(MyApp.token, Api.Order.getProductionOrderStatistics, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject  data = jsonObject.getJSONObject("data");
                        tv_num1.setText(data.getInt("quantityOrder")+"");
                        tv_num2.setText(data.getString("quantityAssignment")+"");
                        tv_num3.setText(data.getString("quantityProducted")+"");
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
    private void getCreateOrder() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("orderId", id);
        httpParams.put("imageNecessary",1);
        NetUtils.getInstance().get(MyApp.token, Api.Order.getProductionOrderList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            OrderCreate orderCreate = new Gson().fromJson(item.toString(), OrderCreate.class);
                            orderCreates.add(orderCreate);
                        }
                        orderCreateAdapter.setNewData(orderCreates);

                    }else{
                        ToastUtils.showShort("你没有该权限");
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
