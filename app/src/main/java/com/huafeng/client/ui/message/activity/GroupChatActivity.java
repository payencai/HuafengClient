package com.huafeng.client.ui.message.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.message.fragment.ChatFragment;
import com.huafeng.client.ui.message.model.GroupDetail;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.utils.MyUserProvider;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupChatActivity extends BaseActivity {


    @BindView(R.id.tv_name)
    TextView tv_name;

    String name;
    String userId;
    ChatFragment mEaseChatFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        name=getIntent().getStringExtra("name");
        userId=getIntent().getStringExtra("id");
        EventBus.getDefault().register(this);
        initView();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnMsg(MsgEvent msgEvent){
        if(msgEvent.getmMsg()==800){
            finish();
        }
        if(msgEvent.getmMsg()==701){
            getDetail();
        }
    }
    private void initView(){
        mEaseChatFragment=new ChatFragment();
        Bundle bundle=new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID,userId);
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        mEaseChatFragment.setArguments(bundle);
        mEaseChatFragment.setChatFragmentHelper(new EaseChatFragment.EaseChatFragmentHelper() {
            @Override
            public void onSetMessageAttributes(EMMessage message) {
                String nickName = MyApp.getUserInfo().getNickname();
                String avatar = MyApp.getUserInfo().getHeadPortraitUrl();
                String avatarKey= MyApp.getUserInfo().getHeadPortrait();
                message.setAttribute("nickName", nickName);
                message.setAttribute("avatar", avatar);
                message.setAttribute("avatarKey", avatarKey);
                message.setAttribute("toNickName", EaseUserUtils.getUserInfo(message.getTo()).getNickname());
                message.setAttribute("toAvatar", EaseUserUtils.getUserInfo(message.getTo()).getAvatar());
                message.setAttribute("toAvatarKey", EaseUserUtils.getUserInfo(message.getTo()).getKey());
            }

            @Override
            public void onEnterToChatDetails() {

            }

            @Override
            public void onAvatarClick(String username) {

            }

            @Override
            public void onAvatarLongClick(String username) {

            }

            @Override
            public boolean onMessageBubbleClick(EMMessage message) {
                return false;
            }

            @Override
            public void onMessageBubbleLongClick(EMMessage message) {

            }

            @Override
            public boolean onExtendMenuItemClick(int itemId, View view) {
                return false;
            }

            @Override
            public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
                return null;
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.fr_container,mEaseChatFragment).commit();
        getDetail();
    }
    @OnClick({R.id.iv_back,R.id.iv_more})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:
                Intent intent=new Intent(GroupChatActivity.this, GroupDetailActivity.class);
                intent.putExtra("id",userId);
                startActivity(intent);
                break;

        }
    }
    private void getDetail(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("crowdId",userId);
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getCrowdDetailsByCrowdId, httpParams,new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    String msg=jsonObject.getString("message");
                    if(code==0){
                        JSONObject data=jsonObject.getJSONObject("data");
                        GroupDetail groupDetail=new Gson().fromJson(data.toString(),GroupDetail.class);
                        for (int i = 0; i <groupDetail.getCrowdUserList().size() ; i++) {
                             GroupDetail.CrowdUserListBean crowdUserListBean=groupDetail.getCrowdUserList().get(i);
                             MyUserProvider.getInstance().setUser(crowdUserListBean.getUserId()+"",crowdUserListBean.getNickname(),crowdUserListBean.getHeadPortraitUrl(),crowdUserListBean.getHeadPortrait());
                        }
                        tv_name.setText(groupDetail.getCrowdName());
                        MyUserProvider.getInstance().setUser(groupDetail.getId()+"",groupDetail.getCrowdName(),groupDetail.getImageUrl(),groupDetail.getImage());
                    }else{
                        ToastUtils.showShort(msg);
                        EMClient.getInstance().chatManager().deleteConversation(userId,true);
                        finish();
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
