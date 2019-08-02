package com.huafeng.client.ui.message.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.message.adapter.DelGroupUserAdapter;
import com.huafeng.client.ui.message.model.GroupDetail;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupUserDeleteActivity extends BaseActivity {
    DelGroupUserAdapter delGroupUserAdapter;

    @BindView(R.id.lv_contact)
    ListView lv_contact;
    GroupDetail groupDetail;
    List<String> userIdList;
    String groupId;
    List<GroupDetail.CrowdUserListBean> crowdUserListBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_user_delete);
        ButterKnife.bind(this);
        groupId=getIntent().getStringExtra("id");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @OnClick({R.id.tv_finish, R.id.iv_back})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish:
                if(userIdList.size()==0){
                    ToastUtils.showShort("请选择删除人员");
                    return;
                }
                addToGroup(groupId);
                break;


        }
    }
    private void initView() {
        userIdList=new ArrayList<>();
        crowdUserListBeans=new ArrayList<>();
        delGroupUserAdapter=new DelGroupUserAdapter(this,crowdUserListBeans);
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroupDetail.CrowdUserListBean contacts = crowdUserListBeans.get(position);
                if (contacts.isCheck()) {
                    crowdUserListBeans.get(position).setCheck(false);
                    userIdList.remove(contacts.getUserId()+"");
                } else {
                    crowdUserListBeans.get(position).setCheck(true);
                    userIdList.add(contacts.getUserId()+"");
                }

                delGroupUserAdapter.notifyDataSetChanged();
            }
        });
        lv_contact.setAdapter(delGroupUserAdapter);
        getDetail();
    }
    private void addToGroup(String groupId) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("crowdId", groupId);
        //userIdList.add(0, MyApp.getUserInfo().getHxUsername());
        String ids= StringUtils.listToString2(userIdList, ',');
        httpParams.put("userIds",ids );

        Log.e("userIds",ids);
        NetUtils.getInstance().post(Api.Huanxin.deleteCrowdByUserIds, MyApp.token, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("删除成功");
                        finish();
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
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("crowdId", groupId);
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getCrowdDetailsByCrowdId, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        groupDetail = new Gson().fromJson(data.toString(), GroupDetail.class);
                        crowdUserListBeans.addAll(groupDetail.getCrowdUserList());
                        for (int i = 0; i <crowdUserListBeans.size() ; i++) {
                            if(MyApp.getUserInfo().getHxUsername().equals(crowdUserListBeans.get(i).getUserId()+"")){
                                crowdUserListBeans.remove(i);
                            }
                        }
                        delGroupUserAdapter.notifyDataSetChanged();
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
