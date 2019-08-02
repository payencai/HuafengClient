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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
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

public class SearchProduceActivity extends BaseActivity {
    int state;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    @BindView(R.id.et_no)
    EditText et_no;
    @BindView(R.id.rv_design)
    RecyclerView rv_design;
    ProduceManaAdapter mModelDesignAdapter;
    List<ProduceMana> mModelDesigns;
    int page=1;
    boolean isLoadMore=false;
    String sampleNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_produce);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }
    private void initView() {
        mModelDesigns=new ArrayList<>();

        mModelDesignAdapter=new ProduceManaAdapter(R.layout.item_home_produce,mModelDesigns);
        mModelDesignAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProduceMana order= (ProduceMana) adapter.getItem(position);
                Intent intent=new Intent(SearchProduceActivity.this, ProduceDetailActivity.class);
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
        rv_design.setLayoutManager(new LinearLayoutManager(this));
        rv_design.setAdapter(mModelDesignAdapter);
        et_no.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    sampleNo = et_no.getText().toString().trim();
                    if (TextUtils.isEmpty(sampleNo)) {
                        Toast.makeText(SearchProduceActivity.this, "请输入搜索的生产单号", Toast.LENGTH_SHORT).show();
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
        if(!TextUtils.isEmpty(sampleNo)){
            httpParams.put("sampleNo",sampleNo);
        }
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
