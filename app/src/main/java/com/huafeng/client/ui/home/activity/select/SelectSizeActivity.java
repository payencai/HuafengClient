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
import com.huafeng.client.ui.home.adapter.FirstSizeAdapter;
import com.huafeng.client.ui.home.adapter.SecSizeAdapter;
import com.huafeng.client.ui.home.model.FirstSize;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectSizeActivity extends BaseActivity {
    @BindView(R.id.lv_first)
    ListView lv_first;
    @BindView(R.id.lv_sec)
    ListView lv_sec;
    FirstSizeAdapter mFristAdapter;
    SecSizeAdapter mSecSizeAdapter;
    List<FirstSize> mFirstSizes;
    List<FirstSize.SizesBean> mSizesBeans;
    FirstSize firstSize1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @OnClick({R.id.iv_back})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                if(lv_sec.getVisibility()==View.VISIBLE){
                    lv_sec.setVisibility(View.GONE);
                    lv_first.setVisibility(View.VISIBLE);
                }else{
                    finish();
                }
                break;
        }
    }
    private void initView() {
        mFirstSizes=new ArrayList<>();
        mSizesBeans=new ArrayList<>();
        mFristAdapter=new FirstSizeAdapter(this,mFirstSizes);
        mSecSizeAdapter=new SecSizeAdapter(this,mSizesBeans);
        lv_first.setAdapter(mFristAdapter);
        lv_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 firstSize1=mFirstSizes.get(position);
                 lv_first.setVisibility(View.GONE);
                 lv_sec.setVisibility(View.VISIBLE);
                 mSizesBeans.clear();
                 mSizesBeans.addAll(firstSize1.getSizes());
                 mSecSizeAdapter.notifyDataSetChanged();
            }
        });
        lv_sec.setAdapter(mSecSizeAdapter);
        lv_sec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FirstSize.SizesBean sizesBean=mSizesBeans.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", sizesBean);
                intent.putExtra("name", firstSize1.getName());
                setResult(0, intent);
                finish();
            }
        });
        getSize();
    }

    private void getSize() {

        NetUtils.getInstance().get(MyApp.token, Api.Size.getHierarchy, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            FirstSize firstType=new Gson().fromJson(item.toString(),FirstSize.class);
                            mFirstSizes.add(firstType);
                        }
                        mFristAdapter.notifyDataSetChanged();
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
