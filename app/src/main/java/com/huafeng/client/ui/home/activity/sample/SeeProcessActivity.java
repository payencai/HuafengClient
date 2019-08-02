package com.huafeng.client.ui.home.activity.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.model.ModelDetail;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeeProcessActivity extends BaseActivity {
    @BindView(R.id.rv_process)
    RecyclerView rv_process;
    List<SeeProcess> seeProcesses;
    SeeProcessAdapter seeProcessAdapter;
    ModelDetail modelDetail;
    int id;
    String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_process);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        json=getIntent().getStringExtra("json");
        id = getIntent().getIntExtra("id", 0);
        initView();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        seeProcesses = new ArrayList<>();
        seeProcessAdapter = new SeeProcessAdapter(seeProcesses);
        rv_process.setLayoutManager(new LinearLayoutManager(this));
        rv_process.setAdapter(seeProcessAdapter);
        if(TextUtils.isEmpty(json)){
            getProcess();
        }else{
            modelDetail = new Gson().fromJson(json, ModelDetail.class);
            initData();
        }

    }

    private void getProcess() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        modelDetail = new Gson().fromJson(data.toString(), ModelDetail.class);
                        initData();
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


    private void initData() {

        List<SeeProcess.Child> children = new ArrayList<>();
        ModelDetail.ProductProcessListBean old = modelDetail.getProductProcessList().get(0);
        for (int i = 0; i < modelDetail.getProductProcessList().size(); i++) {
            ModelDetail.ProductProcessListBean current = modelDetail.getProductProcessList().get(i);
            if (old.getStageId() == current.getStageId()) {
                SeeProcess.Child child = new SeeProcess.Child(current.getIsOutsourcing(), current.getProcessName(),current.getPrice());
                children.add(child);
            } else {
                List<SeeProcess.Child> temp = new ArrayList<>();
                temp.addAll(children);
                SeeProcess seeProcess = new SeeProcess(old.getStageName(), temp);
                seeProcesses.add(seeProcess);
                children.clear();
                SeeProcess.Child child = new SeeProcess.Child(current.getIsOutsourcing(), current.getProcessName(),current.getPrice());
                children.add(child);

            }
            int position = modelDetail.getProductProcessList().size() - 1;
            if (i == position) {
                SeeProcess seeProcess = new SeeProcess(current.getStageName(), children);
                seeProcesses.add(seeProcess);
            }
            old = current;

        }


        seeProcessAdapter.setNewData(seeProcesses);
    }
}
