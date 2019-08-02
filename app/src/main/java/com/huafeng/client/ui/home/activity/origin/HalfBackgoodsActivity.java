package com.huafeng.client.ui.home.activity.origin;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.select.SelectClientActivity;
import com.huafeng.client.ui.home.model.Client;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalfBackgoodsActivity extends BaseActivity {
    BackgoodsAdapter backgoodsAdapter;
    List<Backgoods> backgoodsList;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_client)
    TextView tv_client;
    Client client;
    BackSubmit backSubmit;
    List<BackSubmit.EntryListBean> entryListBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_half_backgoods);
        ButterKnife.bind(this);
        initView();
    }

    private boolean checkInput() {
        boolean isOk = true;
        if(client==null){
            ToastUtils.showShort("请先选择客户");
            isOk=false;
            return  isOk;
        }
        for (int i = 0; i < backgoodsAdapter.getData().size(); i++) {

            if (backgoodsAdapter.getData().get(i).getQuantity() == null||backgoodsAdapter.getData().get(i).getQuantity().intValue()==0) {
                ToastUtils.showShort("请输入数量");
                isOk = false;
                break;
            }
        }
        return isOk;
    }

    private void submit() {
        for (int i = 0; i < backgoodsAdapter.getData().size(); i++) {
            BackSubmit.EntryListBean entryListBean = new BackSubmit.EntryListBean();
            entryListBean.setQuantity(backgoodsAdapter.getData().get(i).getQuantity());
            entryListBean.setSampleId(backgoodsAdapter.getData().get(i).getSampleId());
            entryListBean.setRemarks(backgoodsAdapter.getData().get(i).getRemarks());
            entryListBean.setClothQuantity(backgoodsAdapter.getData().get(i).getClothQuantity());
            entryListBean.setRepairQuantity(backgoodsAdapter.getData().get(i).getRepairQuantity());
            entryListBean.setSewQuantity(backgoodsAdapter.getData().get(i).getSewQuantity());
            entryListBean.setWashQuantity(backgoodsAdapter.getData().get(i).getWashQuantity());
            entryListBeans.add(entryListBean);
        }
        backSubmit.setEntryList(entryListBeans);
        String json = new Gson().toJson(backSubmit);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.halfEntry, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
                        finish();
                    } else {
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

    @OnClick({R.id.iv_back, R.id.rl_client, R.id.tv_submit})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkInput()) {
                    submit();
                }
                break;
            case R.id.rl_client:
                startActivityForResult(new Intent(HalfBackgoodsActivity.this, SelectClientActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            client = (Client) data.getSerializableExtra("data");
            if (client != null)
                tv_client.setText(client.getName());
            backgoodsList.clear();
            getData();
        }
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("clientRecordId", client.getId());
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getSampleDispatched, httpParams, new OnMessageReceived() {
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
                            Backgoods backgoods = new Gson().fromJson(item.toString(), Backgoods.class);
                            backgoodsList.add(backgoods);
                        }
                        backgoodsAdapter.setNewData(backgoodsList);
                    } else {

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
        backSubmit = new BackSubmit();
        backgoodsList = new ArrayList<>();
        backgoodsAdapter = new BackgoodsAdapter(backgoodsList);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(backgoodsAdapter);
    }
}
