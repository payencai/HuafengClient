package com.huafeng.client.ui.home.activity.design;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;


import com.huafeng.client.ui.home.activity.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeeSizeModuleActivity extends BaseActivity {
    List<SizeModule> sizeModules;
    SizeModuleAdapter sizeModuleAdapter;
    @BindView(R.id.rv_size)
    RecyclerView rv_size;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_size_model);
        ButterKnife.bind(this);
        id=getIntent().getIntExtra("id",0);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sizeModules=new ArrayList<>();
        sizeModuleAdapter=new SizeModuleAdapter(sizeModules);
        rv_size.setLayoutManager(new LinearLayoutManager(this));
        rv_size.setAdapter(sizeModuleAdapter);
        getModel(id);
    }

    private void getModel(int id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("moduleId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getSizeModule, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONArray array=data.getJSONArray("sizeList");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject item=array.getJSONObject(i);
                            SizeModule sizeModule=new Gson().fromJson(item.toString(),SizeModule.class);
                            sizeModules.add(sizeModule);
                        }
                        sizeModuleAdapter.setNewData(sizeModules);
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
}
