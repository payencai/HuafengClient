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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.produce.ProduceDetailActivity;
import com.huafeng.client.ui.home.adapter.ProduceManaAdapter;
import com.huafeng.client.ui.home.model.ProduceMana;
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
public class ProduceFragment extends Fragment {

    int state;

    int page=1;
    boolean isLoadMore=false;
    public ProduceFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv_design)
    RecyclerView rv_design;
    ProduceManaAdapter mModelDesignAdapter;
    List<ProduceMana> mModelDesigns;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_produce, container, false);
        state=getArguments().getInt("type");
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    public static ProduceFragment newInstance(int state) {
        ProduceFragment orderListFragment = new ProduceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", state);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }
    private void initView() {
        mModelDesigns=new ArrayList<>();

        mModelDesignAdapter=new ProduceManaAdapter(R.layout.item_new_produce,mModelDesigns);
        mModelDesignAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                ProduceMana order= (ProduceMana) adapter.getItem(position);
                Intent intent=new Intent(getContext(), ProduceDetailActivity.class);
                intent.putExtra("id",order.getId());
                startActivityForResult(intent,1);
            }
        });
        mModelDesignAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                isLoadMore=true;
                getData();
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        rv_design.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_design.setAdapter(mModelDesignAdapter);
        getData();
    }
    private void refresh(){
        page=1;
        mModelDesigns.clear();
        mModelDesignAdapter.setNewData(mModelDesigns);
        getData();
    }
    private  void getData(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("page",page);
        httpParams.put("imageNecessary",1);
        if(state>0)
            httpParams.put("currentStatus",state);
        NetUtils.getInstance().get(MyApp.token, Api.Production.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        jsonObject=jsonObject.getJSONObject("data");
                        List<ProduceMana> orderList=new ArrayList<>();
                        JSONArray data=jsonObject.getJSONArray("beanList");
                        for (int i = 0; i <data.length() ; i++) {
                            JSONObject item=data.getJSONObject(i);
                            ProduceMana modelDesign=new Gson().fromJson(item.toString(),ProduceMana.class);
                            orderList.add(modelDesign);
                            mModelDesigns.add(modelDesign);
                        }
                        if(isLoadMore){
                            isLoadMore=false;
                            if(data.length()>0){
                                mModelDesignAdapter.addData(orderList);
                                mModelDesignAdapter.loadMoreComplete();
                            }else{
                                mModelDesignAdapter.loadMoreEnd(true);
                            }
                        }else{
                            mModelDesignAdapter.setNewData(mModelDesigns);
                        }

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
