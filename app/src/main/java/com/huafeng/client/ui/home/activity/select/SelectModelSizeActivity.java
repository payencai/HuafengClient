package com.huafeng.client.ui.home.activity.select;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.SelectModelSizeAdapter;
import com.huafeng.client.ui.home.model.BigModel;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectModelSizeActivity extends BaseActivity {
    SelectModelSizeAdapter modelSizeAdapter;
    List<BigModel> bigModels;
    @BindView(R.id.lv_select)
    ListView lv_select;
    int clientId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_model_size);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        clientId=getIntent().getIntExtra("id",0);
        initView();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bigModels=new ArrayList<>();
        modelSizeAdapter=new SelectModelSizeAdapter(this,bigModels);
        lv_select.setAdapter(modelSizeAdapter);
        lv_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BigModel bigModel=bigModels.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", bigModel);
                setResult(0, intent);
                finish();
            }
        });
        getList();
    }
    private void getList() {
        HttpParams httpParams=new HttpParams();
        httpParams.put("clientRecordId",clientId);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getListByClientId,httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            BigModel firstType=new Gson().fromJson(item.toString(),BigModel.class);
                            bigModels.add(firstType);
                        }
                        modelSizeAdapter.notifyDataSetChanged();
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
