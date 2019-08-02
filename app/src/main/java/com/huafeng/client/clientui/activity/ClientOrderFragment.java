package com.huafeng.client.clientui.activity;


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
import com.huafeng.client.clientui.ClientOrder;
import com.huafeng.client.clientui.ClientOrderAdapter;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
public class ClientOrderFragment extends Fragment {


    public ClientOrderFragment() {
        // Required empty public constructor
    }
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv_design)
    RecyclerView rv_design;
    ClientOrderAdapter mOrderAdapter;
    List<ClientOrder> mOrders;
    int state;

    int page=1;
    boolean isLoadMore=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_order, container, false);
        state=getArguments().getInt("state");
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);
        initView();
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnMsg(MsgEvent msgEvent){
        if(msgEvent.getmMsg()==100){
            refresh();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static ClientOrderFragment newInstance(int state) {
        ClientOrderFragment orderListFragment = new ClientOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("state", state);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }
    private void refresh(){
        page=1;
        mOrders.clear();
        mOrderAdapter.setNewData(mOrders);
        getData();
    }
    private void initView() {
        mOrders=new ArrayList<>();
        mOrderAdapter=new ClientOrderAdapter(R.layout.item_home_order,mOrders);
        mOrderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                isLoadMore=true;
                getData();
            }
        },rv_design);
        mOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ClientOrder order= (ClientOrder) adapter.getItem(position);
//                Intent intent=new Intent(getContext(), OrderDetailActivity.class);
//                intent.putExtra("id",order.getId());
//                intent.putExtra("state",order.getStatus());
//                startActivityForResult(intent,1);

            }
        });
        rv_design.setLayoutManager(new LinearLayoutManager(getContext()));
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        rv_design.setAdapter(mOrderAdapter);
        getData();
    }
    private  void getData(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("page",page);

        if(state>0)
            httpParams.put("status",state);
        NetUtils.getInstance().get(MyApp.token, Api.Order.getListForCustomer, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        List<ClientOrder> orderList=new ArrayList<>();
                        JSONArray data=jsonObject.getJSONArray("beanList");
                        for (int i = 0; i <data.length() ; i++) {
                            JSONObject item=data.getJSONObject(i);
                            ClientOrder clientOrder=new Gson().fromJson(item.toString(), ClientOrder.class);
                            orderList.add(clientOrder);
                            mOrders.add(clientOrder);
                        }
                        if(isLoadMore){
                            isLoadMore=false;
                            if(data.length()>0){
                                mOrderAdapter.addData(orderList);
                                mOrderAdapter.loadMoreComplete();
                            }else{
                                mOrderAdapter.loadMoreEnd(true);
                            }
                        }else{
                            mOrderAdapter.setNewData(mOrders);
                        }

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
