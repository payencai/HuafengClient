package com.huafeng.client.ui.home.activity.apply;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.adapter.PhotoAdapter;
import com.huafeng.client.ui.home.model.ApplyDetail;
import com.huafeng.client.view.GridViewForScrollView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApprovalApplyDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_refuse)
    TextView tv_refuse;
    @BindView(R.id.tv_agree)
    TextView tv_agree;
    @BindView(R.id.gv_photo)
    GridViewForScrollView gv_photo;
    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;
    PhotoAdapter photoAdapter;
    ArrayList<String> images=new ArrayList<>();
    int id;
    int flag;
    ApplyDetail applyDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_apply_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id=getIntent().getIntExtra("id",0);
        flag=getIntent().getIntExtra("flag",0);
        showLoading(500);
        initView();

    }
    KProgressHUD kProgressHUD;
    private void showLoading(long time){
        kProgressHUD= KProgressHUD.create(this)
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void approve(int status) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Approval.approve, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("已处理");
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
    private void initView() {

        getDetail();
    }
    private void showConfirmDialog(int type) {
        final Dialog dialog = new Dialog(this, R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_stop, null);

        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_confirm);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                switch (type) {
                    case 1:
                        approve(1);
                        break;
                    case 2:
                        approve(2);
                        break;

                }

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
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (display.getWidth() * 0.7);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }
    @OnClick ({R.id.tv_agree,R.id.tv_refuse,R.id.back})
    void OnClick(View view){
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()){
            case R.id.tv_agree:
                showConfirmDialog(1);
                break;
            case R.id.tv_refuse:
                showConfirmDialog(2);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Approval.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        applyDetail = new Gson().fromJson(data.toString(), ApplyDetail.class);
                        initData();
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

    private void initData() {
        String imgs=applyDetail.getImagesUrl();
        if(!TextUtils.isEmpty(imgs)){
            images.addAll(StringUtils.StringToArrayList(imgs,","));
        }
        photoAdapter=new PhotoAdapter(this,images);
        gv_photo.setAdapter(photoAdapter);
        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoUtil.seeBigPhoto(ApprovalApplyDetailActivity.this,position,images,view);
            }
        });
        tv_name.setText(applyDetail.getApproveByName());
        tv_content.setText(applyDetail.getContent());
        if(applyDetail.getType()==1){
            tv_type.setText("人事审批");
        }else{
            tv_type.setText("报销审批");
        }
        tv_title.setText(applyDetail.getTitle());
        if(applyDetail.getStatus()==1){
            tv_status.setText("已通过");
            rl_bottom.setVisibility(View.GONE);
        }else if(applyDetail.getStatus()==2){
            tv_status.setText("已拒绝");
            rl_bottom.setVisibility(View.GONE);
        }else{
            rl_bottom.setVisibility(View.VISIBLE);
            tv_status.setText("审批中");
        }
        if(flag==1){
            rl_bottom.setVisibility(View.GONE);
        }else{
            rl_bottom.setVisibility(View.VISIBLE);
        }
    }
}
