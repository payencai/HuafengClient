package com.huafeng.client.ui.home.activity.buy;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.model.Auth;
import com.huafeng.client.view.MyListView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AskDetailActivity extends BaseActivity {
    @BindView(R.id.tv_reqest)
    TextView tv_reqest;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.tv_produce)
    TextView tv_produce;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_pay)
    TextView tv_pay;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.tv_refuse)
    TextView tv_refuse;
    @BindView(R.id.tv_agree)
    TextView tv_agree;
    @BindView(R.id.lv_ask)
    MyListView lv_ask;
    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;
    int id;
    AskDetail askDetail;
    AskDetailAdapter askDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        showLoading();
        initView();
    }
    KProgressHUD kProgressHUD;
    private void showLoading(){
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
        timer.schedule(task, 500);//3秒后执行TimeTask的run方法
    }

    @OnClick({R.id.iv_back, R.id.tv_agree, R.id.tv_refuse})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_agree:
                showConfirmDialog(1);
                break;
            case R.id.tv_refuse:
                showConfirmDialog(2);
                break;
        }
    }

    private void initView() {

        getDetail();
    }

    private void agree() {
        Map<String, Object> params = new HashMap<>();
        params.put("purchaseRequirementGroupId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Purchase.agree, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("已通过");
                        finish();
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
        LogUtils.e(json);
    }

    private void refuse() {
        Map<String, Object> params = new HashMap<>();
        params.put("purchaseRequirementGroupId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Purchase.refuse, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("已拒绝");
                        finish();
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
        LogUtils.e(json);
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
                        agree();
                        break;
                    case 2:
                        refuse();
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

    private void initData() {
        tv_reqest.setText(askDetail.getRequisitionNumber());
        tv_name.setText(askDetail.getCreateByName());
        tv_number.setText(askDetail.getSampleNo());
        tv_order.setText(askDetail.getOrderNumber());
        tv_produce.setText(askDetail.getProductionOrderNumber());
        tv_time.setText(askDetail.getGmtCreate());
        tv_remark.setText(askDetail.getRemarks());
        if (askDetail.getPayMethod() == 1) {
            tv_pay.setText("采购人垫付");
        } else {
            tv_pay.setText("公司支付");
        }
        if (askDetail.getStatus() == 1) {
            List<Auth> auths = LitePal.findAll(Auth.class);
            for (int i = 0; i < auths.size(); i++) {
                int id = auths.get(i).getAuthId();
                if (id == 1041 || id == 1043) {
                    ll_bottom.setVisibility(View.VISIBLE);
                }
            }
        }
        askDetailAdapter = new AskDetailAdapter(this, askDetail.getRequisitionList());
        lv_ask.setAdapter(askDetailAdapter);


    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("groupId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.getAskDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        askDetail = new Gson().fromJson(data.toString(), AskDetail.class);
                        initData();
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
