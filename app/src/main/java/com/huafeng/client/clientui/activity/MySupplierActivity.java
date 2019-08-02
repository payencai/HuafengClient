package com.huafeng.client.clientui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;

import com.huafeng.client.clientui.MySupplierAdapter;
import com.huafeng.client.clientui.Supplier;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySupplierActivity extends AppCompatActivity {
    MySupplierAdapter mySupplierAdapter;
    List<Supplier> suppliers;
    @BindView(R.id.rv_supplier)
    RecyclerView rv_supplier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_supplier);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @OnClick({R.id.iv_back, R.id.tv_new})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_new:
                startActivityForResult(new Intent(MySupplierActivity.this,NewSupplierActivity.class),1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void initView() {
        suppliers=new ArrayList<>();
        mySupplierAdapter=new MySupplierAdapter(suppliers);
        rv_supplier.setLayoutManager(new LinearLayoutManager(this));
        rv_supplier.setAdapter(mySupplierAdapter);
        getData();
    }
    private void getData(){
        NetUtils.getInstance().get(MyApp.token, Api.Client.getMyFactory, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        JSONArray data=jsonObject.getJSONArray("data");
                        if(data!=null&&data.length()>0){
                            for (int i = 0; i <data.length() ; i++) {
                                JSONObject item=data.getJSONObject(i);
                                Supplier supplier=new Gson().fromJson(item.toString(),Supplier.class);
                                suppliers.add(supplier);
                            }
                            mySupplierAdapter.setNewData(suppliers);
                        }
                    }else{
                        String msg=jsonObject.getString("message");
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
