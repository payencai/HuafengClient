package com.huafeng.client.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.Account;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.model.UserInfo;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.mine.adapter.AccountAdapter;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAccountActivity extends BaseActivity {
    @BindView(R.id.lv_account)
    ListView lv_account;
    AccountAdapter accountAdapter;
    List<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        accounts = new ArrayList<>();
        int pos = 0;
        List<Account> accountList = LitePal.findAll(Account.class);
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            if (account.getUsername().equals(MyApp.getUserInfo().getUsername())) {
                account.setCurrent(true);
                pos = i;
            }else{
                account.setCurrent(false);
            }
            accounts.add(account);
        }
        accountAdapter = new AccountAdapter(this, accounts);
        accountAdapter.setOnItemClickListener(new AccountAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Account account = accounts.get(position);
                if (account.isCurrent()) {
                    ToastUtils.showLong("你登录的正是这个账号，不能重复登录！");
                    return;
                }
                accountAdapter.setPos(position);
                accountAdapter.notifyDataSetChanged();
                changeAccount(account);
            }

            @Override
            public void onDel(int position) {
                Account delAccount = accounts.get(position);
                accounts.remove(position);
                accountAdapter.notifyDataSetChanged();
                LitePal.deleteAll(Account.class, "username = ?", delAccount.getUsername());

            }
        });
        accountAdapter.setPos(pos);
        lv_account.setAdapter(accountAdapter);

    }

    private void changeAccount(Account account) {
        EMClient.getInstance().logout(true);
        MyApp.isLogin = false;
        MyApp.token = "";
        MyApp.setUserInfo(null);
        SPUtils.getInstance().put("phone", "");
        SPUtils.getInstance().put("pwd", "");
        SPUtils.getInstance().put("token", "");
        SPUtils.getInstance().put("user", "");
        login(account.getUsername(), account.getPassword());
    }

    //密码登录
    private void login(String phone, String pwd) {
        Map<String, Object> params = new HashMap<>();
        params.put("password", pwd);
        params.put("username", phone);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.EmployeeUser.login, json, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                handleHttp(response, 0, phone, pwd);
                LogUtils.e(response);
            }

            @Override
            public void onError(String error) {

            }
        });
    }


    private void handleHttp(String result, int code, String phone, String pwd) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("resultCode");
            String message = jsonObject.getString("message");
            if (resultCode == 0) {
                switch (code) {
                    case 0:
                        JSONObject data = jsonObject.getJSONObject("data");
                        UserInfo userInfo = new Gson().fromJson(data.toString(), UserInfo.class);
                        SPUtils.getInstance().put("phone", phone);
                        SPUtils.getInstance().put("pwd", pwd);
                        SPUtils.getInstance().put("token", userInfo.getToken());
                        SPUtils.getInstance().put("user", data.toString());
                        MyApp.token = userInfo.getToken();
                        MyApp.isLogin = true;
                        MyApp.setUserInfo(userInfo);
                        loginChat(userInfo.getHxUsername(), userInfo.getHxPassword());
                        break;
                }
            } else {
                ToastUtils.showShort(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loginChat(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                EventBus.getDefault().post(new MsgEvent(900));
               // EventBus.getDefault().post(new MsgEvent(900));
                Log.e("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.e("main", "登录聊天服务器失败！");
            }
        });
    }
}
