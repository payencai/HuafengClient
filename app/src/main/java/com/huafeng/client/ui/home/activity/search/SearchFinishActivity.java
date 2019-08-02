package com.huafeng.client.ui.home.activity.search;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.huafeng.client.ui.home.activity.origin.OriginFinishDetailActivity;
import com.huafeng.client.ui.home.adapter.FinishStorageAdapter;
import com.huafeng.client.ui.home.model.FinishStorage;
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

public class SearchFinishActivity extends BaseActivity {
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    @BindView(R.id.et_no)
    EditText et_no;
    int page = 1;
    boolean isLoadMore = false;
    String orderNumber;
    FinishStorageAdapter finishStorageAdapter;
    List<FinishStorage> finishStorages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_finish);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    private void initView() {
        et_no.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    orderNumber = et_no.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNumber)) {
                        Toast.makeText(SearchFinishActivity.this, "请输入搜索的名字", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    refresh();
                    // 搜索功能主体

                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        finishStorages = new ArrayList<>();

        finishStorageAdapter = new FinishStorageAdapter(R.layout.item_finished_product, finishStorages);
        finishStorageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FinishStorage finishStorage = (FinishStorage) adapter.getItem(position);
                Intent intent = new Intent(SearchFinishActivity.this, OriginFinishDetailActivity.class);
                intent.putExtra("id", finishStorage.getSampleId());
                startActivityForResult(intent, 1);
            }
        });
        finishStorageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isLoadMore = true;
                page++;
                getData();
            }
        }, rv_order);
        rv_order.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        rv_order.setAdapter(finishStorageAdapter);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        getData();
    }
    private void refresh() {
        page = 1;
        finishStorages.clear();
        finishStorageAdapter.setNewData(finishStorages);
        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        httpParams.put("imageNecessary",1);
        if (!TextUtils.isEmpty(orderNumber)) {
            httpParams.put("sampleNo", orderNumber);
        }

        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getSampleList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<FinishStorage> finishStorageList = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            FinishStorage modelDesign = new Gson().fromJson(item.toString(), FinishStorage.class);
                            finishStorageList.add(modelDesign);
                        }
                        finishStorageAdapter.addData(finishStorageList);
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() > 0) {
                                finishStorageAdapter.loadMoreComplete();
                            } else {
                                finishStorageAdapter.loadMoreEnd(true);
                            }
                        } else {
                            finishStorageAdapter.loadMoreComplete();
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
