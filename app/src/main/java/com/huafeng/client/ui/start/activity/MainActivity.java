package com.huafeng.client.ui.start.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.JPush.JpushConfig;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.model.UserInfo;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.DistanceUtil;
import com.huafeng.client.tools.MyEMConnectionListener;
import com.huafeng.client.tools.TimeUtil;
import com.huafeng.client.ui.contacts.activity.StrangerDetailActivity;
import com.huafeng.client.ui.contacts.model.UserDetail;
import com.huafeng.client.ui.home.activity.design.DesignDetailActivity;
import com.huafeng.client.ui.home.activity.produce.ProduceDetailActivity;
import com.huafeng.client.ui.home.model.MyLocation;
import com.huafeng.client.ui.home.model.Record;
import com.huafeng.client.ui.message.activity.GroupChatActivity;
import com.huafeng.client.ui.message.activity.NotifyCenterActivity;
import com.huafeng.client.ui.message.activity.SingleChatActivity;
import com.huafeng.client.ui.message.activity.StrongNoticeActivity;
import com.huafeng.client.ui.mine.model.UserMsg;
import com.huafeng.client.ui.start.fragment.ConversationFragment;
import com.huafeng.client.ui.start.fragment.HomeFragment;
import com.huafeng.client.ui.start.fragment.MineFragment;
import com.huafeng.client.ui.start.fragment.NewContactsFragment;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.utils.MyUserProvider;
import com.lzy.okgo.model.HttpParams;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.ycbjie.notificationlib.NotificationUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leolin.shortcutbadger.ShortcutBadger;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class MainActivity extends AppCompatActivity implements AMapLocationListener {
    @BindView(R.id.fr_container)
    FrameLayout fr_container;

    @BindView(R.id.iv_tab_home)
    ImageView iv_tab_home;
    @BindView(R.id.iv_tab_msg)
    ImageView iv_tab_msg;
    @BindView(R.id.iv_tab_contacts)
    ImageView iv_tab_contacts;
    @BindView(R.id.iv_tab_mine)
    ImageView iv_tab_mine;

    @BindView(R.id.tv_tab_home)
    TextView tv_tab_home;
    @BindView(R.id.tv_tab_msg)
    TextView tv_tab_msg;
    @BindView(R.id.tv_tab_contacts)
    TextView tv_tab_contacts;
    @BindView(R.id.tv_tab_mine)
    TextView tv_tab_mine;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_tab_home)
    LinearLayout ll_tab_home;
    @BindView(R.id.ll_tab_msg)
    LinearLayout ll_tab_msg;
    @BindView(R.id.ll_tab_contacts)
    LinearLayout ll_tab_contacts;
    @BindView(R.id.ll_tab_mine)
    LinearLayout ll_tab_mine;
    @BindView(R.id.tv_count)
    TextView tv_count;
    FragmentManager fm;
    List<Fragment> fragments;
    HomeFragment mHomeFragment;
    ConversationFragment mConversationFragment;
    NewContactsFragment mContactsFragment;
    MineFragment mMineFragment;
    UserDetail userDetail;
    MyLocation mMyLocation;
    MyEMConnectionListener myEMConnectionListener;
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    // 是否退出程序
    private static Boolean isExit = false;
    // 定时触发器
    private static Timer tExit = null;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().removeConnectionListener(myEMConnectionListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initLocation() {
        mlocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mlocationClient.setLocationListener(this);
        if (mlocationClient != null) {
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.stopLocation();

        }

    }

    private void hideAllFragment() {
        for (Fragment fragment : fragments) {
            if (fragment != null)
                fm.beginTransaction().hide(fragment).commit();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent;
        if (requestCode == 166 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }

            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                Log.e("qrcode", result);
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                if (result.contains("华枫制衣好友")) {
                    String values[] = result.split(":");
                    intent = new Intent(this, StrangerDetailActivity.class);
                    intent.putExtra("id", values[1]);
                    startActivity(intent);
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String marker = jsonObject.getString("marker");
                        int id = jsonObject.getInt("id");
                        if ("productionOrder".equals(marker)) {
                            intent = new Intent(this, ProduceDetailActivity.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        } else {
                            intent = new Intent(this, DesignDetailActivity.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int count = EMClient.getInstance().chatManager().getUnreadMessageCount();
        if (count > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(count + "");
        } else {
            tv_count.setVisibility(View.GONE);
        }

    }

    private void getDetail(String hxUserId, EMMessage message) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("hxUserId", hxUserId);
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getHxUserDetails, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        userDetail = new Gson().fromJson(data.toString(), UserDetail.class);
                        if (userDetail.getFriends().getIsRemind() == 1) {
                            Intent intent = new Intent(MainActivity.this, StrongNoticeActivity.class);
                            intent.putExtra("id", hxUserId);
                            intent.putExtra("name", userDetail.getNickname());
                            intent.putExtra("head", userDetail.getHeadPortraitUrl());
                            startActivityForResult(intent, 1);
                        }
                        if (userDetail.getFriends().getIsNotice() == 0) {
                            //EaseUI.getInstance().getNotifier().notify(message);
                            //EaseUI.getInstance().getNotifier().vibrateAndPlayTone(message);
                            NotificationUtils notificationUtils = new NotificationUtils(MainActivity.this);
                            notificationUtils.sendNotification(1,"消息提醒","你有一条新的消息",R.mipmap.ic_app_logo);
                        }
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

    private void showFragment(Fragment fragment) {
        fm.beginTransaction().show(fragment).commit();
    }

    UserMsg userMsg;

    private void getUserInfo() {

        NetUtils.getInstance().get(MyApp.token, Api.EmployeeUser.getInformation, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        userMsg = new Gson().fromJson(data.toString(), UserMsg.class);
                        tv_title.setText(userMsg.getFactoryName());
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

    private void initFragment() {
        showHome();
        initLocation();
    }

    private void showHome() {

        fragments = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        fragments.add(mHomeFragment);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fr_container, mHomeFragment).commit();
        showFragment(mHomeFragment);
    }

    private void initView() {
        myEMConnectionListener=new MyEMConnectionListener(MainActivity.this);
        initFragment();
        if (TextUtils.isEmpty(MyApp.token)) {
            login();
        } else {
            getUserInfo();
            mlocationClient.startLocation();
            EMClient.getInstance().addConnectionListener(myEMConnectionListener);
        }

    }


    //自动登录
    private void login() {
        Map<String, Object> params = new HashMap<>();
        String phone = SPUtils.getInstance().getString("phone");
        String pwd = SPUtils.getInstance().getString("pwd");
        params.put("password", pwd);
        params.put("username", phone);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.EmployeeUser.login, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                handleHttp(response, 0);
                //LogUtils.e(response);
            }

            @Override
            public void onError(String error) {
                ToastUtils.showLong("登录失败！");
                finish();
            }
        });
    }



    private void loginChat(String userName, String password, String id) {

        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                MyUserProvider.getInstance().setUser(MyApp.getUserInfo().getId() + "", MyApp.getUserInfo().getNickname(), MyApp.getUserInfo().getHeadPortraitUrl(), MyApp.getUserInfo().getHeadPortrait());
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                EMClient.getInstance().addConnectionListener(myEMConnectionListener);
                int count = EMClient.getInstance().chatManager().getUnreadMessageCount();
                ShortcutBadger.applyCount(MainActivity.this, count); //for 1.1.4+
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                ToastUtils.showLong("登录聊天服务器失败！");
                ActivityUtils.finishAllActivities();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.e("main", "登录聊天服务器失败！");
            }
        });
    }




    private void handleHttp(String result, int code) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("resultCode");
            String message = jsonObject.getString("message");
            if (resultCode == 0) {
                switch (code) {
                    case 0:
                        JSONObject data = jsonObject.getJSONObject("data");
                        UserInfo userInfo = new Gson().fromJson(data.toString(), UserInfo.class);
                        JpushConfig.getInstance().setAlias(userInfo.getEmployeeRecordId() + "");
                        JpushConfig.getInstance().setTag(userInfo.getEmployeeRecordId() + "");
                        SPUtils.getInstance().put("user", data.toString());
                        SPUtils.getInstance().put("token", userInfo.getToken());
                        MyApp.token = userInfo.getToken();
                        MyApp.setUserInfo(userInfo);
                        mlocationClient.startLocation();
                        getUserInfo();
                        EventBus.getDefault().post(new MsgEvent(111));
                        loginChat(MyApp.getUserInfo().getHxUsername(), MyApp.getUserInfo().getHxPassword(), MyApp.getUserInfo().getHxUsername());
                        break;
                }
            } else {
                ToastUtils.showShort(message);
                SPUtils.getInstance().put("user", "");
                ActivityUtils.finishAllActivities();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initEaseuiMsg() {


        mConversationFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                if (conversation != null) {
                    if (conversation.getType() == EMConversation.EMConversationType.GroupChat) {
                        Intent intent = new Intent(MainActivity.this, GroupChatActivity.class);
                        intent.putExtra("id", conversation.conversationId());
                        intent.putExtra("name", conversation.conversationId());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, SingleChatActivity.class);
                        intent.putExtra("id", conversation.conversationId());
                        intent.putExtra("name", conversation.conversationId());
                        startActivity(intent);
                    }

                }

            }
        });
        mConversationFragment.setOnNotifyClickListener(new EaseConversationListFragment.OnNotifyClickListener() {
            @Override
            public void onClick() {
                startActivity(new Intent(MainActivity.this, NotifyCenterActivity.class));
            }
        });
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {

                int count = EMClient.getInstance().chatManager().getUnreadMessageCount();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count > 0) {
                            tv_count.setVisibility(View.VISIBLE);
                            tv_count.setText(count + "");
                            ShortcutBadger.applyCount(MainActivity.this, count); //for 1.1.4+
                        } else {
                            tv_count.setVisibility(View.GONE);
                        }
                    }
                });

                for (EMMessage message : list) {
                    String userId = message.getFrom();
                    String nickName = message.getStringAttribute("nickName", "");
                    String avatar = message.getStringAttribute("avatar", "");
                    String avatarKey = message.getStringAttribute("avatarKey", "");
                    getDetail(userId, message);
                    MyUserProvider.getInstance().setUser(userId, nickName, avatar, avatarKey);
                }
                if (mConversationFragment != null) {
                    mConversationFragment.refresh();
                }

            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageRead(List<EMMessage> list) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {

            }


            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        });

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                if (tExit != null) {
                    tExit.cancel(); // 将原任务从队列中移除
                }
                // 重新实例一个定时器
                tExit = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                };
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 延时两秒触发task任务
                tExit.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void clearTagbarState() {
        tv_tab_home.setTextColor(ContextCompat.getColor(this, R.color.color_999));
        iv_tab_home.setImageResource(R.mipmap.ic_unselect_home);
        tv_tab_msg.setTextColor(ContextCompat.getColor(this, R.color.color_999));
        iv_tab_msg.setImageResource(R.mipmap.ic_unselect_msg);
        tv_tab_contacts.setTextColor(ContextCompat.getColor(this, R.color.color_999));
        iv_tab_contacts.setImageResource(R.mipmap.ic_unselect_contacts);
        tv_tab_mine.setTextColor(ContextCompat.getColor(this, R.color.color_999));
        iv_tab_mine.setImageResource(R.mipmap.ic_unselect_mine);
    }

    @OnClick({R.id.ll_tab_home, R.id.ll_tab_msg, R.id.ll_tab_contacts, R.id.ll_tab_mine, R.id.iv_code})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_code:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, 166);
                break;
            case R.id.ll_tab_home:
                if (userMsg != null) {
                    tv_title.setText(userMsg.getFactoryName());
                }
                clearTagbarState();
                hideAllFragment();
                tv_tab_home.setTextColor(ContextCompat.getColor(this, R.color.color_blue));
                iv_tab_home.setImageResource(R.mipmap.ic_select_home);
                showFragment(mHomeFragment);
                break;
            case R.id.ll_tab_msg:
                tv_title.setText("消息");
                if (mConversationFragment == null) {
                    mConversationFragment = new ConversationFragment();
                    fragments.add(mConversationFragment);
                    fm.beginTransaction().add(R.id.fr_container, mConversationFragment).commit();
                    initEaseuiMsg();
                }
                clearTagbarState();
                hideAllFragment();
                tv_tab_msg.setTextColor(ContextCompat.getColor(this, R.color.color_blue));
                iv_tab_msg.setImageResource(R.mipmap.ic_select_msg);
                showFragment(mConversationFragment);
                break;
            case R.id.ll_tab_contacts:
                tv_title.setText("通讯录");
                if (mContactsFragment == null) {
                    mContactsFragment = new NewContactsFragment();
                    fragments.add(mContactsFragment);
                    fm.beginTransaction().add(R.id.fr_container, mContactsFragment).commit();
                }
                clearTagbarState();
                hideAllFragment();
                tv_tab_contacts.setTextColor(ContextCompat.getColor(this, R.color.color_blue));
                iv_tab_contacts.setImageResource(R.mipmap.ic_select_contacts);
                showFragment(mContactsFragment);
                break;
            case R.id.ll_tab_mine:
                tv_title.setText("我的");
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    fragments.add(mMineFragment);
                    fm.beginTransaction().add(R.id.fr_container, mMineFragment).commit();
                }
                clearTagbarState();
                hideAllFragment();
                tv_tab_mine.setTextColor(ContextCompat.getColor(this, R.color.color_blue));
                iv_tab_mine.setImageResource(R.mipmap.ic_select_mine);
                showFragment(mMineFragment);
                break;
        }
    }






    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        LogUtils.e(aMapLocation.getAddress());
        MyApp.setaMapLocation(aMapLocation);

    }
}
