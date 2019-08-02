package com.huafeng.client.ui.home.activity.buy;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.view.MyListView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

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

public class CreateAskActivity extends BaseActivity {
    WaitInputAdapter waitInputAdapter;
    List<AskList.RequisitionListBean> requisitionListBeanList;
    @BindView(R.id.lv_ask)
    MyListView lv_content;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.tv_pay)
            TextView tv_pay;
    int id;
    int groupId;
    AskList askList;
    AskSubmit askSubmit=new AskSubmit();
    int pay=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ask);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
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
    private void showPayDialog() {
        ArrayList<String> list = new ArrayList<>();
        list.add("采购人垫付");
        list.add("公司支付");
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_type, null);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        LoopView loopView = (LoopView) view.findViewById(R.id.loopView);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if("公司支付".equals(list.get(loopView.getSelectedItem()))){
                    pay=2;
                    tv_pay.setText("公司支付");
                }else{
                    pay=1;
                    tv_pay.setText("采购人垫付");
                }
            }
        });


        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        // 设置原始数据
        loopView.setItems(list);
        loopView.setNotLoop();
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }
    private void initView() {
        requisitionListBeanList = new ArrayList<>();
        waitInputAdapter = new WaitInputAdapter(this, requisitionListBeanList);
        lv_content.setAdapter(waitInputAdapter);
        add();
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.rl_size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayDialog();
            }
        });
        findViewById(R.id.tv_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                askSubmit.setId(groupId);
                askSubmit.setRemarks(et_remark.getEditableText().toString());
                askSubmit.setPayMethod(pay);
                List<AskSubmit.RequisitionListBean> requisitionListBeans = new ArrayList<>();
                for (int i = 0; i < requisitionListBeanList.size(); i++) {
                    AskSubmit.RequisitionListBean requisitionListBean = new AskSubmit.RequisitionListBean();
                    requisitionListBean.setId(requisitionListBeanList.get(i).getId());
                    requisitionListBean.setRealQuantity(requisitionListBeanList.get(i).getAskNum());
                    requisitionListBeans.add(requisitionListBean);
                }
                askSubmit.setRequisitionList(requisitionListBeans);
                submit();
            }
        });
    }

    private void submit() {
        String json = new Gson().toJson(askSubmit);
        showLoading(300);
        NetUtils.getInstance().post(Api.Purchase.fill, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("创建成功");
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
    }

    private void add() {
        Map<String, Object> params = new HashMap<>();
        params.put("purchaseRequirementGroupId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Purchase.add, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        groupId = jsonObject.getInt("data");
                        getlist();
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

    private void getlist() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("groupId", groupId);
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.get, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        askList = new Gson().fromJson(data.toString(), AskList.class);
                        requisitionListBeanList.addAll(askList.getRequisitionList());
                        waitInputAdapter.notifyDataSetChanged();
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

}
