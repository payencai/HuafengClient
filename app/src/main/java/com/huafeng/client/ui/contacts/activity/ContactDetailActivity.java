package com.huafeng.client.ui.contacts.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.contacts.model.UserDetail;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.view.CircleImageView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.utils.MyUserProvider;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;
import com.suke.widget.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactDetailActivity extends BaseActivity {
    int hxUserId;
    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.switch1)
    SwitchButton sb_top;
    @BindView(R.id.switch2)
    SwitchButton sb_notice;
    @BindView(R.id.switch3)
    SwitchButton sb_force;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.iv_nick)
    ImageView iv_nick;
    int isTopChat = 0;
    int isNotice = 0;
    int isRemind = 0;
    String nickname;
    UserDetail userDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        hxUserId = getIntent().getIntExtra("id", 0);
        getDetail();
        showLoading(500);
        initView();

    }

    KProgressHUD kProgressHUD;

    private void showLoading(long time) {
        kProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(1000)
                .setCancellable(true)
                .setDimAmount(0.5f)
                .show();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, time);//3秒后执行TimeTask的run方法
    }

    private void update() {
        HttpParams params = new HttpParams();
        params.put("id", userDetail.getFriends().getId());
        params.put("isNotice", isNotice);
        params.put("isTopChat", isTopChat);
        params.put("isRemind", isRemind);
        if (!TextUtils.isEmpty(nickname)) {
            params.put("nickname", nickname);
        }
        NetUtils.getInstance().post(Api.Huanxin.updateFriendsById, MyApp.token, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        //ToastUtils.showShort("修改成功");
                    } else {
                        String msg = jsonObject.getString("message");
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

    private void initView() {
        sb_top.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(hxUserId + "");
                if (isChecked) {
                    isTopChat = 1;

                    if (conversation != null) {
                        conversation.setExtField("top");
                    }
                } else {
                    isTopChat = 0;
                    if (conversation != null) {
                        conversation.setExtField("false");
                    }
                }
                update();
            }
        });
        iv_nick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateNickDialog();
            }
        });
        sb_notice.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    isNotice = 1;
                } else {
                    isNotice = 0;
                }
                update();
            }
        });
        sb_force.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    isRemind = 1;
                } else {
                    isRemind = 0;
                }
                update();
            }
        });
    }

    private void updateUserInfo() {
        Map<String, Object> params = new HashMap<>();
        if (!TextUtils.isEmpty(nickname))
            params.put("nickname", nickname);
        params.put("id", MyApp.getUserInfo().getId());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.EmployeeUser.edit, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                ToastUtils.showShort("修改成功");
                LogUtils.e(response);

            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void showUpdateNickDialog() {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_updatenick, null);
        final EditText et_season = (EditText) view.findViewById(R.id.et_season);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                nickname = et_season.getEditableText().toString();
                if (TextUtils.isEmpty(nickname)) {
                    ToastUtils.showShort("昵称不能为空！");
                    return;
                }
                update();
                tv_name.setText(nickname);
                MyUserProvider.getInstance().getUser(userDetail.getFriends().getFriendId() + "").setNickname(nickname);
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

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @OnClick({R.id.back, R.id.rl_delete, R.id.iv_phone})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_phone:
                callPhone(userDetail.getTelephone());
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_delete:
                delFriend();
                break;
        }
    }

    private void delFriend() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("friendId", hxUserId);
        NetUtils.getInstance().post(Api.Huanxin.deleteMyFriend, MyApp.token, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        EMClient.getInstance().chatManager().deleteConversation(hxUserId + "", true);
                        ToastUtils.showShort("删除成功");
                        EventBus.getDefault().post(new MsgEvent(900));
                        finish();
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

    private void getDetail() {
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
                        if (userDetail.getFriends() != null) {
                            isNotice = userDetail.getFriends().getIsNotice();
                            isTopChat = userDetail.getFriends().getIsTopChat();
                            isRemind = userDetail.getFriends().getIsRemind();
                            tv_name.setText(userDetail.getFriends().getNickname());
                        }

                        tv_company.setText(userDetail.getFactoryName());
                        tv_type.setText(userDetail.getDepartmentName() + userDetail.getPostName());
                        if (isTopChat == 1) {
                            sb_top.setChecked(true);
                        }
                        if (isNotice == 1) {
                            sb_notice.setChecked(true);
                        }
                        if (isRemind == 1) {
                            sb_force.setChecked(true);
                        }
                        Glide.with(ContactDetailActivity.this)
                                .load(userDetail.getHeadPortraitUrl())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(iv_head);
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
