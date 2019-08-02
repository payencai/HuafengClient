package com.huafeng.client.ui.message.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.message.adapter.MyGroupAdapter;
import com.huafeng.client.ui.message.model.MyGroup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyGroupActivity extends BaseActivity {
    @BindView(R.id.rv_group)
    RecyclerView rv_group;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    MyGroupAdapter mMyGroupAdapter;
    List<MyGroup> mMyGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_group);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnMsg(MsgEvent msgEvent){
        if(msgEvent.getmMsg()==800||msgEvent.getmMsg()==701){
            mMyGroups.clear();
            mMyGroupAdapter.setNewData(mMyGroups);
            getData();
        }
    }
    @OnClick({R.id.rl_apply, R.id.tv_add,R.id.rl_search})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:

                break;
            case R.id.tv_add:
                startActivityForResult(new Intent(MyGroupActivity.this, CreateGroupActivity.class), 1);
                break;
            case R.id.rl_apply:
                startActivity(new Intent(MyGroupActivity.this, GroupApplyActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }

    private void initView() {
        mMyGroups = new ArrayList<>();
        mMyGroupAdapter = new MyGroupAdapter(mMyGroups);
        mMyGroupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyGroup myGroup = (MyGroup) adapter.getItem(position);
                Intent intent = new Intent(MyGroupActivity.this, GroupChatActivity.class);
                intent.putExtra("id", myGroup.getId() + "");
                intent.putExtra("name", myGroup.getCrowdName());
                startActivity(intent);
            }
        });
        rv_group.setLayoutManager(new LinearLayoutManager(this));
        rv_group.setAdapter(mMyGroupAdapter);
        getData();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mMyGroups.clear();
                mMyGroupAdapter.setNewData(mMyGroups);
                getData();
            }
        });
    }

    private void getData() {
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getCrowdsList, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                refreshLayout.finishRefresh();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            MyGroup myGroup = new Gson().fromJson(item.toString(), MyGroup.class);
                            //MyUserProvider.getInstance().setUser(myGroup.getId(), myGroup.getCrowdName(), myGroup.getImageUrl(), myGroup.getImage());
                            mMyGroups.add(myGroup);
                        }
                        Collections.sort(mMyGroups, new Comparator<MyGroup>() {
                            @Override
                            public int compare(MyGroup o1, MyGroup o2) {
                                if (o1.getCrowdUserId() == 27 && o2.getCrowdUserId() != 27)
                                    return -1;
                                if (o1.getCrowdUserId() != 27 && o2.getCrowdUserId() == 27) {
                                    return 1;
                                }
                                return o1.getCrowdName().compareTo(o2.getCrowdName());
                            }
                        });
                        mMyGroupAdapter.setNewData(mMyGroups);
                    } else {
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
