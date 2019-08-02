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
import com.huafeng.client.ui.home.activity.sample.ProcesModel;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeeRawModuleActivity extends BaseActivity {
    ProcesModel procesModel;
    int id;
    RawModelAdapter rawModelAdapter;
    @BindView(R.id.rv_raw)
    RecyclerView rv_raw;
    List<ProcesModel.MaterialListBean> materialListBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_raw_module);
        id=getIntent().getIntExtra("id",0);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rawModelAdapter=new RawModelAdapter(materialListBeans);
        rv_raw.setLayoutManager(new LinearLayoutManager(this));
        rv_raw.setAdapter(rawModelAdapter);
        getModel();
    }

    private void getModel() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("moduleId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getMaterialModule, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        procesModel = new Gson().fromJson(data.toString(), ProcesModel.class);
                        rawModelAdapter.setNewData(procesModel.getMaterialList());
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
