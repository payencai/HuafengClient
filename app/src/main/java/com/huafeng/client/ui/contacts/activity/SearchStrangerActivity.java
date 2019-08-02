package com.huafeng.client.ui.contacts.activity;

import android.app.Dialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.huafeng.client.ui.contacts.adapter.StrangerAdapter;
import com.huafeng.client.ui.contacts.model.Stranger;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchStrangerActivity extends BaseActivity {
    @BindView(R.id.rv_design)
    RecyclerView rv_design;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    StrangerAdapter mStrangerAdapter;
    List<Stranger> mStrangers;
    String keyWord = "";
    int friendId;
    String applyReason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stranger);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @OnClick({R.id.back})
    void OnClick(View view ){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
    private void applyForFriend(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("friendId",friendId);
        httpParams.put("applyReason",applyReason);
        NetUtils.getInstance().post(Api.Huanxin.addFriendApply, MyApp.token, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        ToastUtils.showShort("提交成功，请耐心等候对方处理");
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
    private void showApplyDialog() {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_friend, null);
        final EditText et_season = (EditText) view.findViewById(R.id.et_season);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        tv_name.setText("添加好友申请");
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                applyReason = et_season.getEditableText().toString();
                if(TextUtils.isEmpty(applyReason)){
                    ToastUtils.showShort("理由不能为空！");
                    return;
                }
                applyForFriend();

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
    private void initView() {
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mStrangers.clear();
                    mStrangerAdapter.setNewData(mStrangers);
                    keyWord = et_search.getEditableText().toString();
                    if (TextUtils.isEmpty(keyWord)) {
                       getAllStrangers();
                    }else{
                        getSearchStangers();
                    }
                    return true;
                }
                return false;
            }
        });
        mStrangers = new ArrayList<>();
        mStrangerAdapter = new StrangerAdapter(R.layout.item_search_friend, mStrangers);
        mStrangerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Stranger stranger= (Stranger) adapter.getItem(position);
                friendId=stranger.getId();
                if(view.getId()==R.id.tv_agree){
                    showApplyDialog();
                }
            }
        });
        rv_design.setLayoutManager(new LinearLayoutManager(this));
        rv_design.setAdapter(mStrangerAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mStrangers.clear();
                mStrangerAdapter.setNewData(mStrangers);
                if (TextUtils.isEmpty(keyWord)) {
                    getAllStrangers();
                }else{
                    getSearchStangers();
                }
            }
        });
        //getAllStrangers();
    }

    private void getSearchStangers() {
        HttpParams params = new HttpParams();
        if (!TextUtils.isEmpty(keyWord)) {
            params.put("keyWord", keyWord);
        }
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getHxUserListForAdd, params, new OnMessageReceived() {
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
                            Stranger contacts = new Gson().fromJson(item.toString(), Stranger.class);
                            mStrangers.add(contacts);
                        }
                        mStrangerAdapter.setNewData(mStrangers);
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

    private void getAllStrangers() {
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getHxUserListForAdd, new OnMessageReceived() {
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
                            Stranger contacts = new Gson().fromJson(item.toString(), Stranger.class);
                            mStrangers.add(contacts);
                        }
                        mStrangerAdapter.setNewData(mStrangers);
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
