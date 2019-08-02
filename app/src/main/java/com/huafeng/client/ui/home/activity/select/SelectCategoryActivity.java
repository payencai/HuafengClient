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
import com.huafeng.client.ui.home.adapter.RawCategoryAdapter;
import com.huafeng.client.ui.home.model.RawCategory;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCategoryActivity extends BaseActivity {

    @BindView(R.id.lv_first)
    ListView lv_first;
    @BindView(R.id.lv_sec)
    ListView lv_sec;


    RawCategoryAdapter mFristAdapter;
    RawCategoryAdapter mSecAdapter;

    List<RawCategory> mFirstCategory;
    List<RawCategory> mSecCategory;
    RawCategory rawCategory1;
    int currentLevel=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
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
                if(currentLevel==1){
                    finish();
                }else if(currentLevel==2){
                    currentLevel=1;
                    lv_first.setVisibility(View.VISIBLE);
                    lv_sec.setVisibility(View.GONE);

                }
                break;
        }
    }



    private void initView() {

        mFirstCategory=new ArrayList<>();
        mSecCategory=new ArrayList<>();

        mFristAdapter=new RawCategoryAdapter(this,mFirstCategory);
        mSecAdapter=new RawCategoryAdapter(this,mSecCategory);

        lv_first.setAdapter(mFristAdapter);
        lv_sec.setAdapter(mSecAdapter);

        lv_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentLevel=2;
                rawCategory1=mFirstCategory.get(position);
                lv_first.setVisibility(View.GONE);
                lv_sec.setVisibility(View.VISIBLE);
                mSecCategory.clear();
                getSecCategory(rawCategory1.getId());
            }
        });

        lv_sec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RawCategory rawCategory=mSecCategory.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", rawCategory);
                intent.putExtra("data1", rawCategory1);
                setResult(0, intent);
                finish();
            }
        });

        getFirstCategory();
    }
    private void getFirstCategory() {

        NetUtils.getInstance().get(MyApp.token, Api.Matrerial.getFirstCategorys, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            RawCategory firstType=new Gson().fromJson(item.toString(),RawCategory.class);
                            mFirstCategory.add(firstType);
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
    private void getSecCategory(int id) {
        final HttpParams params = new HttpParams();
        params.put("firstCategoryId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Matrerial.getSecondCategorysByFirst, params,new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            RawCategory firstType=new Gson().fromJson(item.toString(),RawCategory.class);
                            mSecCategory.add(firstType);
                        }
                        mSecAdapter.notifyDataSetChanged();
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
