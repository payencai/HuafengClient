package com.huafeng.client.clientui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;

import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSupplierActivity extends AppCompatActivity {

    SearchSupplierAdapter mySupplierAdapter;
    List<Supplier> suppliers;
    @BindView(R.id.rv_supplier)
    RecyclerView rv_supplier;
    @BindView(R.id.et_search)
    EditText et_search;
    String searchCondition = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        searchCondition = getIntent().getStringExtra("data");
        initView();
    }

    @OnClick({R.id.tv_cancel})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
        }
    }

    private void initView() {
        suppliers = new ArrayList<>();
        mySupplierAdapter = new SearchSupplierAdapter(suppliers);
        mySupplierAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Supplier supplier = (Supplier) adapter.getItem(position);
                if (CheckDoubleClick.isFastDoubleClick()) {
                    return;
                }
                if (view.getId() == R.id.tv_do) {
                    Intent intent = new Intent(AddSupplierActivity.this, AddClientApplyActivity.class);
                    intent.putExtra("data", supplier);
                    startActivityForResult(intent, 1);
                }
            }
        });
        rv_supplier.setLayoutManager(new LinearLayoutManager(this));
        rv_supplier.setAdapter(mySupplierAdapter);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    searchCondition = et_search.getText().toString().trim();
                    suppliers.clear();
                    getData();
                    return true;
                }
                return false;
            }
        });

        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();

        httpParams.put("searchCondition", searchCondition);
        NetUtils.getInstance().get(MyApp.token, Api.Client.getFactoryList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        if (data != null && data.length() > 0) {
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject item = data.getJSONObject(i);
                                Supplier supplier = new Gson().fromJson(item.toString(), Supplier.class);
                                suppliers.add(supplier);
                            }
                            mySupplierAdapter.setNewData(suppliers);
                        }
                    } else {
                        String msg = jsonObject.getString("message");
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
