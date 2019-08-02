package com.huafeng.client.ui.start.fragment;


import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.TimeUtil;
import com.huafeng.client.ui.contacts.model.Contacts;
import com.huafeng.client.ui.message.model.MyGroup;
import com.huafeng.client.ui.message.model.Notify;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.utils.MyUserProvider;
import com.lzy.okgo.model.HttpParams;
import com.nanchen.wavesidebar.FirstLetterUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends EaseConversationListFragment {

    @Override
    protected void initView() {
        super.initView();
        hideTitleBar();
        getMyFriend();
        //getNotice();

    }
    @Override
    public void onResume() {
        super.onResume();
        getUnReadCount();

    }
    Notify notify;
    private void getNotice() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", 1);
        NetUtils.getInstance().get(MyApp.token, Api.Push.getList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<Notify> notifyList = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            notify = new Gson().fromJson(item.toString(), Notify.class);
                            if(i==0){
                                tvContent.setText(notify.getPush().getContent());
                                String time= TimeUtil.getTimeFormatText(notify.getPush().getGmtCreate());
                                tvTime.setText(time);
                                break;
                            }
                        }


                    } else {
                        ToastUtils.showShort("你没有该权限");

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
    private void getMyFriend(){

        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getMyFriendList , new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    String msg=jsonObject.getString("message");
                    if(code==0){
                        JSONArray data=jsonObject.getJSONArray("data");
                        for (int i = 0; i <data.length() ; i++)
                        {
                            JSONObject item=data.getJSONObject(i);
                            Contacts contacts=new Gson().fromJson(item.toString(),Contacts.class);
                            MyUserProvider.getInstance().setUser(contacts.getUserId()+"",contacts.getNickname(),contacts.getHeadPortraitUrl(),contacts.getHeadPortrait());

                        }
                        getMyGroup();
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
    private void getMyGroup(){
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getCrowdsList , new OnMessageReceived() {
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
                            MyGroup contacts=new Gson().fromJson(item.toString(),MyGroup.class);
                            String head="http://icons.iconarchive.com/icons/blackvariant/button-ui-system-folders-drives/1024/Group-icon.png";
                            if(!TextUtils.isEmpty(contacts.getImageUrl())){
                                head=contacts.getImageUrl();
                            }
                            MyUserProvider.getInstance().setUser(contacts.getId(),contacts.getCrowdName(),head,contacts.getImage());
                        }
                        refresh();
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
    private void getUnReadCount(){
        NetUtils.getInstance().get(MyApp.token, Api.Push.getUnReadNum, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        int count=jsonObject.getInt("data");
                        if(count>0){
                            tvCount.setText(count+"");
                            tvCount.setVisibility(View.VISIBLE);
                            int number = EMClient.getInstance().chatManager().getUnreadMessageCount();
                            ShortcutBadger.applyCount(getContext(), number); //for 1.1.4+
                        }else{
                            tvCount.setVisibility(View.GONE);
                        }
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
