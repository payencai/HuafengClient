package com.huafeng.client.ui.contacts.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

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
import com.huafeng.client.ui.contacts.adapter.FriendAskAdapter;
import com.huafeng.client.ui.contacts.model.FriendRequest;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.message.activity.SingleChatActivity;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewFriendsActivity extends BaseActivity {
    @BindView(R.id.rl_ask)
    RecyclerView rv_ask;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    FriendAskAdapter mFriendAskAdapter;
    List<FriendRequest> mFriendRequests;
    String rejectReason;
    int id;
    int state=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        mFriendRequests=new ArrayList<>();
        mFriendAskAdapter=new FriendAskAdapter(R.layout.item_new_friend,mFriendRequests);
        mFriendAskAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FriendRequest friendRequest= (FriendRequest) adapter.getItem(position);
                id=friendRequest.getId();
                switch (view.getId()){
                    case R.id.tv_agree:
                        state=1;
                        handleApply(friendRequest);
                        break;
                    case R.id.tv_refuse:
                        state=2;
                        showRefuseDialog();
                        break;
                }
            }
        });
        mFriendAskAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FriendRequest friendRequest= (FriendRequest) adapter.getItem(position);
                if(friendRequest.getState()==1){
                    Intent intent = new Intent(NewFriendsActivity.this, SingleChatActivity.class);
                    intent.putExtra("id", friendRequest.getUserId()+"");
                    intent.putExtra("name", friendRequest.getNickname());
                    startActivity(intent);
                }
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                updateData();
            }
        });
        rv_ask.setLayoutManager(new LinearLayoutManager(this));
        rv_ask.setAdapter(mFriendAskAdapter);
        getApplyFriend();
    }
    @OnClick({R.id.tv_add,R.id.back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_add:
                startActivity(new Intent(NewFriendsActivity.this,SearchStrangerActivity.class));
                break;
            case R.id.back:
                finish();
                break;
        }
    }
    private void updateData(){
        mFriendRequests.clear();
        mFriendAskAdapter.setNewData(mFriendRequests);
        getApplyFriend();
    }
    private void getApplyFriend(){
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getFriendApplyList , new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refreshLayout.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    String msg=jsonObject.getString("message");
                    if(code==0){
                        JSONArray data=jsonObject.getJSONArray("data");
                        for (int i = 0; i <data.length() ; i++) {
                            JSONObject item=data.getJSONObject(i);
                            FriendRequest contacts=new Gson().fromJson(item.toString(),FriendRequest.class);
                            mFriendRequests.add(contacts);
                        }
                        mFriendAskAdapter.setNewData(mFriendRequests);
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
    private void handleApply(FriendRequest friendRequest){
        HttpParams httpParams=new HttpParams();
        httpParams.put("id",id);
        httpParams.put("state",state);
        if(state==2)
           httpParams.put("rejectReason",rejectReason);
        NetUtils.getInstance().post(Api.Huanxin.updateFriendApply, MyApp.token, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        String data=jsonObject.getString("data");
                        ToastUtils.showShort(data);
                        EventBus.getDefault().post(new MsgEvent(700));
                        updateData();
                        if(friendRequest!=null){
                            String nickName = friendRequest.getNickname();
                            String avatar = friendRequest.getHeadPortraitUrl();
                            String userId=friendRequest.getUserId()+"";
                            LogUtils.e(nickName+"---"+avatar);
                            EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
                                @Override
                                public EaseUser getUser(String username) {
                                    EaseUser easeUser = new EaseUser(userId);
                                    Log.e("receiver", nickName + avatar);
                                    easeUser.setAvatar(avatar);
                                    easeUser.setNickname(nickName);
                                    return easeUser;
                                }
                            });
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
    private void showRefuseDialog() {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_friend, null);
        final EditText et_season = (EditText) view.findViewById(R.id.et_season);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        tv_name.setText("拒绝申请");
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                rejectReason = et_season.getEditableText().toString();
                if(TextUtils.isEmpty(rejectReason)){
                    ToastUtils.showShort("理由不能为空！");
                    return;
                }
                handleApply(null);

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }
}
