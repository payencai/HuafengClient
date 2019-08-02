package com.huafeng.client.ui.message.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.ui.contacts.activity.ContactDetailActivity;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.utils.MyUserProvider;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleChatActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;


    String userId;
    EaseChatFragment mEaseChatFragment;
    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getStringExtra("id");
        if (userId.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnMsg(MsgEvent msgEvent){
        if(msgEvent.getmMsg()==900){
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        EventBus.getDefault().register(this);
        userId=getIntent().getStringExtra("id");
        mEaseChatFragment=new EaseChatFragment();
        Bundle bundle=new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID,userId);
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
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
                if(EaseUserUtils.getUserInfo(message.getTo())!=null){
                    message.setAttribute("toNickName",EaseUserUtils.getUserInfo(message.getTo()).getNickname());
                    message.setAttribute("toAvatar", EaseUserUtils.getUserInfo(message.getTo()).getAvatar());
                    message.setAttribute("toAvatarKey", EaseUserUtils.getUserInfo(message.getTo()).getKey());
                }

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MyUserProvider.getInstance().getUser(userId)!=null){
            tv_name.setText(MyUserProvider.getInstance().getUser(userId).getNickname());
        }
    }

    @OnClick({R.id.iv_back,R.id.iv_more})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:
                Intent intent=new Intent(SingleChatActivity.this, ContactDetailActivity.class);
                intent.putExtra("id",Integer.parseInt(userId));
                startActivityForResult(intent,1);
                break;

        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if(data!=null&&requestCode==6){
//
//        }
//
//    }
}
