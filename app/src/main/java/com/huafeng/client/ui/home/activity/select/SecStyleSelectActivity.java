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
import com.huafeng.client.ui.home.adapter.FirstTypeAdapter;
import com.huafeng.client.ui.home.model.FirstType;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecStyleSelectActivity extends BaseActivity {
    FirstTypeAdapter mFirstTypeAdapter;
    List<FirstType> mFirstTypes;
    @BindView(R.id.lv_sec)
    ListView lv_sec;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_style_select);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        initView();
    }

    private void initView() {
        mFirstTypes = new ArrayList<>();
        mFirstTypeAdapter = new FirstTypeAdapter(this, mFirstTypes);
        mFirstTypeAdapter.setShow(false);
        lv_sec.setAdapter(mFirstTypeAdapter);
        lv_sec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FirstType firstType = mFirstTypes.get(position);
                Intent intent = new Intent();
                intent.putExtra("data", firstType);
                setResult(0, intent);
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSecCategory(id);
    }

    private void getSecCategory(int id) {

        final HttpParams params = new HttpParams();
        params.put("firstCategoryId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Product.getSecondCategorysByFirst, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            FirstType firstType = new Gson().fromJson(item.toString(), FirstType.class);
                            mFirstTypes.add(firstType);
                        }
                        mFirstTypeAdapter.notifyDataSetChanged();

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
