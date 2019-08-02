package com.huafeng.client.ui.contacts.activity;

import android.app.Dialog;
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
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.contacts.model.UserDetail;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StrangerDetailActivity extends BaseActivity {
    String id;
    UserDetail userDetail;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_depart)
    TextView tv_depart;
    @BindView(R.id.tv_factory)
    TextView tv_factory;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    String applyReason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stranger_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id=getIntent().getStringExtra("id");

        initView();
    }
    @OnClick({R.id.tv_add,R.id.back})
    public void OnClick(View view){
       switch (view.getId()){
           case R.id.tv_add:
               showApplyDialog();
               break;
           case R.id.back:
               finish();
               break;
       }
    }
    private void applyForFriend(){
        HttpParams httpParams=new HttpParams();
        httpParams.put("friendId",userDetail.getId());
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
        getDetail();
    }
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("hxUserId", id);
        LogUtils.e("start"+id);
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
                        setData();
                    } else {
                        ToastUtils.showShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                LogUtils.e(error);
            }
        });
    }
    private void setData(){
        tv_name.setText(userDetail.getNickname());
        tv_depart.setText(userDetail.getDepartmentName()+" "+userDetail.getPostName());
        tv_factory.setText(userDetail.getFactoryName());
        if(TextUtils.isEmpty(userDetail.getHeadPortraitUrl())){
            Glide.with(this)
                    .load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png")
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_head);
        }else{
            Glide.with(this).load(userDetail.getHeadPortraitUrl()).apply(RequestOptions.circleCropTransform()).into(iv_head);
        }
        if(userDetail.getIsFriend()==1){
            tvAdd.setVisibility(View.GONE);
        }
    }
}
