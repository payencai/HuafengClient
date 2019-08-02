package com.huafeng.client.ui.home.activity.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.client.ClientContactAdapter;
import com.huafeng.client.ui.home.activity.client.ClientDetailActivity;
import com.huafeng.client.ui.home.model.CustomerData;
import com.huafeng.client.view.sidebar.IndexableAdapter;
import com.huafeng.client.view.sidebar.IndexableLayout;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchClientActivity extends BaseActivity {
    ClientContactAdapter mAdapter;
    List<CustomerData> mDatas;
    int page = 1;
    boolean isEnter = false;
    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;
    @BindView(R.id.et_no)
    EditText et_no;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_client);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void refresh() {
        page = 1;
        mDatas.clear();
        getData();
    }


    private void setList() {
        mAdapter = new ClientContactAdapter(this);
        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CustomerData>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CustomerData entity) {
                CustomerData customerData = mDatas.get(originalPosition);
                Intent intent = new Intent(SearchClientActivity.this, ClientDetailActivity.class);
                intent.putExtra("id", customerData.getId());
                intent.putExtra("clientid", customerData.getClientUserId());
                startActivity(intent);
            }
        });
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        indexableLayout.setOverlayStyle_MaterialDesign(Color.RED);
        indexableLayout.setCompareMode(IndexableLayout.MODE_ALL_LETTERS);
        mAdapter.setDatas(mDatas);
        indexableLayout.setAdapter(mAdapter);
        et_no.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    name = et_no.getText().toString().trim();
                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(SearchClientActivity.this, "请输入搜索的名称", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    refresh();
                    // 搜索功能主体

                    return true;
                }
                return false;
            }
        });
    }


    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        if(!TextUtils.isEmpty(name)){
            httpParams.put("name",name);
        }
        httpParams.put("imageNecessary", 1);
        NetUtils.getInstance().get(MyApp.token, Api.Client.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            CustomerData modelDesign = new Gson().fromJson(item.toString(), CustomerData.class);
                            mDatas.add(modelDesign);
                        }
                        mAdapter.notifyDataSetChanged();
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


    private void initView() {
        mDatas = new ArrayList<>();
        setList();
        //getData();
    }
}
