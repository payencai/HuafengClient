package com.huafeng.client.ui.message.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.message.adapter.GroupApplyAdapter;
import com.huafeng.client.ui.message.model.GroupApply;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupApplyActivity extends BaseActivity {
   @BindView(R.id.rv_apply)
    RecyclerView rv_apply;
    GroupApplyAdapter mGroupApplyAdapter;
    List<GroupApply> mGroupApplies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_apply);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        mGroupApplies=new ArrayList<>();
        mGroupApplyAdapter=new GroupApplyAdapter(R.layout.item_new_friend,mGroupApplies);
        rv_apply.setLayoutManager(new LinearLayoutManager(this));
        rv_apply.setAdapter(mGroupApplyAdapter);
        getData();
    }
    private void getData(){
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getCrowdApplyList , new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    String msg=jsonObject.getString("message");
                    if(code==0){
                        JSONArray data=jsonObject.getJSONArray("data");
                        for (int i = 0; i <data.length() ; i++) {
                            JSONObject item=data.getJSONObject(i);
                            GroupApply contacts=new Gson().fromJson(item.toString(),GroupApply.class);
                            mGroupApplies.add(contacts);
                        }
                        mGroupApplyAdapter.setNewData(mGroupApplies);
                    }else{
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
