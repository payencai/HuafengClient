package com.huafeng.client.clientui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;

import com.huafeng.client.clientui.AddSupplierActivity;
import com.huafeng.client.clientui.SupplierApply;
import com.huafeng.client.clientui.SupplierApplyAdapter;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;

import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewSupplierActivity extends AppCompatActivity {
    SupplierApplyAdapter supplierApplyAdapter;
    List<SupplierApply> suppliers;
    @BindView(R.id.rv_supplier)
    RecyclerView rv_supplier;
    @BindView(R.id.et_search)
    EditText et_search;
    int page = 1;
    boolean isLoadMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_supplier);
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
        suppliers.clear();
        supplierApplyAdapter.setNewData(suppliers);
        getData();
    }

    private void initView() {
        suppliers = new ArrayList<>();
        supplierApplyAdapter = new SupplierApplyAdapter(suppliers);
        supplierApplyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                isLoadMore = true;
                getData();
            }
        }, rv_supplier);
        supplierApplyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (CheckDoubleClick.isFastDoubleClick()) {
                    return;
                }
                SupplierApply supplierApply = (SupplierApply) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.tv_refuse:
                        refuse(supplierApply);
                        break;
                    case R.id.tv_agree:
                        agreee(supplierApply);
                        break;
                }
            }
        });
        rv_supplier.setLayoutManager(new LinearLayoutManager(this));
        rv_supplier.setAdapter(supplierApplyAdapter);
        getData();
    }

    private void refuse(SupplierApply supplierApply) {
        Map<String,Object> params=new HashMap<>();
        params.put("factoryId",supplierApply.getFactoryId());
        params.put("id",supplierApply.getClientInvitation().getId());
        String json=new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Client.refuse, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");

                    if (code == 0) {
                        ToastUtils.showShort("已拒绝");
                        refresh();
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
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
    @OnClick({R.id.iv_back, R.id.tv_search})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_search:
                String data=et_search.getEditableText().toString();
                Intent intent=new Intent(NewSupplierActivity.this, AddSupplierActivity.class);
                intent.putExtra("data",data);
                startActivityForResult(intent,1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void agreee(SupplierApply supplierApply) {
        Map<String,Object> params=new HashMap<>();
        params.put("factoryId",supplierApply.getClientInvitation().getFactoryId());
        params.put("id",supplierApply.getClientInvitation().getId());
        String json=new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Client.agree, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");

                    if (code == 0) {
                        ToastUtils.showShort("已同意");
                        refresh();
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
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

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        NetUtils.getInstance().get(MyApp.token, Api.Client.getApplicationsAndInvitations, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");

                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        List<SupplierApply> supplierApplyList = new ArrayList<>();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            SupplierApply supplier = new Gson().fromJson(item.toString(), SupplierApply.class);
                            supplierApplyList.add(supplier);
                        }
                        supplierApplyAdapter.addData(supplierApplyList);
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() == 0) {
                                supplierApplyAdapter.loadMoreEnd(true);
                            } else {
                                supplierApplyAdapter.loadMoreComplete();
                            }
                        } else {
                            supplierApplyAdapter.loadMoreComplete();
                        }


                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
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
