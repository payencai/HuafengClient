package com.huafeng.client.ui.home.activity.select;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.SizeTypeAdapter;
import com.huafeng.client.ui.home.model.SizeType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectSizeTypeActivity extends BaseActivity {
    SizeTypeAdapter mSizeTypeAdapter;
    List<SizeType> mSizeTypes;
    @BindView(R.id.rv_size)
    RecyclerView rv_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_type);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSizeTypes=new ArrayList<>();
        mSizeTypeAdapter=new SizeTypeAdapter(mSizeTypes);
        mSizeTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SizeType sizeType= (SizeType) adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("data", sizeType);
                setResult(0, intent);
                finish();
            }
        });
        rv_size.setLayoutManager(new LinearLayoutManager(this));
        rv_size.setAdapter(mSizeTypeAdapter);
        getSize();
    }
    private void getSize() {

        NetUtils.getInstance().get(MyApp.token, Api.Size.getSizeType, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            SizeType firstType=new Gson().fromJson(item.toString(),SizeType.class);
                            mSizeTypes.add(firstType);
                        }
                        mSizeTypeAdapter.setNewData(mSizeTypes);
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
