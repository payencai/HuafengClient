package com.huafeng.client.ui.start.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
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
import com.huafeng.client.ui.mine.activity.AboutUsActivity;
import com.huafeng.client.ui.mine.activity.ComplainActivity;
import com.huafeng.client.ui.mine.activity.MyAccountActivity;
import com.huafeng.client.ui.mine.activity.SafetyAccountActivity;
import com.huafeng.client.ui.mine.activity.UserInfoActivity;
import com.huafeng.client.ui.mine.model.UserMsg;
import com.huafeng.client.ui.start.activity.LoginActivity;
import com.huafeng.client.ui.start.activity.MyQrCodeActivity;
import com.huafeng.client.view.CircleImageView;
import com.hyphenate.chat.EMClient;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    @BindView(R.id.tv_nick)
    TextView tv_nick;
    @BindView(R.id.iv_qrcode)
    ImageView iv_qrcode;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnMsg(MsgEvent msgEvent){
        if(msgEvent.getmMsg()==900){
            getDetail();
        }
    }
    private void initView() {


        //SPUtil.get(this,"user",);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getDetail();
            }
        });

        String qrcodeUrl = "华枫制衣好友:" + MyApp.getUserInfo().getId();
        Bitmap logo =ImageUtils.getBitmap(R.drawable.ic_deault_head);
        Bitmap bitmap = (Bitmap) CodeUtils.createImage(qrcodeUrl, 200, 200, logo);
        iv_qrcode.setImageBitmap(bitmap);
        getDetail();


    }

    private void getDetail() {

        NetUtils.getInstance().get(MyApp.token, Api.EmployeeUser.getInformation, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refreshLayout.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        UserMsg userMsg = new Gson().fromJson(data.toString(), UserMsg.class);
                        if (!TextUtils.isEmpty(userMsg.getHeadPortraitUrl())) {
                            Glide.with(getContext())
                                    .load(userMsg.getHeadPortraitUrl())
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(iv_head);
                        } else {
                            Glide.with(getContext())
                                    .load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png")
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(iv_head);

                        }
                        tv_nick.setText(userMsg.getNickname());
                        tv_number.setText("工号："+userMsg.getEmployeeNumber());
                        tv_type.setText(userMsg.getDepartmentName() + " " + userMsg.getPostName());
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



    @OnClick({R.id.iv_qrcode, R.id.ll_top, R.id.rl_safe, R.id.rl_complain, R.id.rl_about, R.id.tv_exit, R.id.tv_change})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_qrcode:
                startActivity(new Intent(getContext(), MyQrCodeActivity.class));
                break;
            case R.id.tv_change:
                startActivity(new Intent(getContext(), MyAccountActivity.class));
                break;
            case R.id.tv_exit:
                EMClient.getInstance().logout(true);
                MyApp.isLogin = false;
                MyApp.token = "";
                MyApp.setUserInfo(null);
                SPUtils.getInstance().put("phone", "");
                SPUtils.getInstance().put("pwd", "");
                SPUtils.getInstance().put("token", "");
                SPUtils.getInstance().put("user", "");
                ActivityUtils.finishAllActivities();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.rl_about:
                startActivity(new Intent(getContext(), AboutUsActivity.class));
                break;
            case R.id.rl_complain:
                startActivity(new Intent(getContext(), ComplainActivity.class));
                break;
            case R.id.ll_top:
                startActivity(new Intent(getContext(), UserInfoActivity.class));
                break;
            case R.id.rl_safe:
                startActivity(new Intent(getContext(), SafetyAccountActivity.class));
                break;

        }
    }
}
