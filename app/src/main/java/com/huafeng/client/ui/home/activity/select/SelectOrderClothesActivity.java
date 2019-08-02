package com.huafeng.client.ui.home.activity.select;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.order.OrderClothes;
import com.huafeng.client.ui.home.adapter.SelectOrderClothesAdapter;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectOrderClothesActivity extends BaseActivity {


    SelectOrderClothesAdapter mWashProcessAdapter;
    List<OrderClothes> mWashProcesses;
    @BindView(R.id.lv_first)
    ListView lv_first;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_order_clothes);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id=getIntent().getIntExtra("id",0);
        initView();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWashProcesses=new ArrayList<>();
        mWashProcessAdapter=new SelectOrderClothesAdapter(this,mWashProcesses);
        lv_first.setAdapter(mWashProcessAdapter);
        lv_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderClothes firstType=mWashProcesses.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", firstType);
                setResult(0, intent);
                finish();
            }
        });
        getFirstCategory();
    }
    private void getFirstCategory() {
        HttpParams httpParams=new HttpParams();
        httpParams.put("orderId",id);
        NetUtils.getInstance().get(MyApp.token, Api.Production.getProductionOrderClothMaterial,httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            OrderClothes firstType=new Gson().fromJson(item.toString(),OrderClothes.class);
                            mWashProcesses.add(firstType);
                        }
                        mWashProcessAdapter.notifyDataSetChanged();
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
