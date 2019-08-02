package com.huafeng.client.ui.home.activity.origin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

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

public class OriginFinishDetailActivity extends BaseActivity {
    int id;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_client)
    TextView tv_client;
    @BindView(R.id.tv_designer)
    TextView tv_designer;
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.rv_log)
    RecyclerView rv_log;
    @BindView(R.id.rv_size)
    RecyclerView rv_size;
    @BindView(R.id.tv_num)
    TextView tv_num;
    int type = 0;
    OriginFinishDetail originFinishDetail;
    OriginFinishLogAdapter originFinishLogAdapter;
    FinishSizeAdapter finishSizeAdapter;
    OriginFinishOrderAdapter originFinishOrderAdapter;
    List<OriginFinishDetail.InventorySampleLogListBean> logListBeans=new ArrayList<>();
    List<OriginFinishDetail.OrdersListBean> ordersListBeanList=new ArrayList<>();
    List<OriginFinishDetail.SizeListBean> sizeListBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin_finish_detail);
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

    private void piandan(String num, String remarks) {
        Map<String, Object> params = new HashMap<>();
        params.put("sampleId", id);
        params.put("quantity", Integer.valueOf(num));
        params.put("remarks", remarks);

        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.finishcheck, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
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

    private void entry(String num, String remarks) {
        Map<String, Object> params = new HashMap<>();
        params.put("sampleId", id);
        params.put("quantity", Integer.valueOf(num));
        params.put("remarks", remarks);
        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.finishentry, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
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

    private void initView() {
        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoUtil.seeSinglePhoto(OriginFinishDetailActivity.this,originFinishDetail.getSampleVo().getImagesUrl(),iv_img);
            }
        });
        originFinishLogAdapter = new OriginFinishLogAdapter(logListBeans);
        originFinishLogAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent;
                OriginFinishDetail.InventorySampleLogListBean sampleLogListBean= (OriginFinishDetail.InventorySampleLogListBean) adapter.getItem(position);
                if(view.getId()==R.id.tv_item6){
                    if(sampleLogListBean.getType()==1){
                        intent=new Intent(OriginFinishDetailActivity.this,RebackGoodsDetailActivity.class);
                        intent.putExtra("id",id);
                        intent.putExtra("noteid",sampleLogListBean.getNoteId());
                        startActivity(intent);
                    }else{
                        intent=new Intent(OriginFinishDetailActivity.this,SendGoodsDetailActivity.class);
                        intent.putExtra("id",id);
                        intent.putExtra("noteid",sampleLogListBean.getNoteId());
                        startActivity(intent);
                    }
                }
            }
        });
        originFinishOrderAdapter = new OriginFinishOrderAdapter(ordersListBeanList);
        finishSizeAdapter=new FinishSizeAdapter(sizeListBeanList);
        rv_size.setLayoutManager(new LinearLayoutManager(this));
        rv_size.setAdapter(finishSizeAdapter);
        rv_log.setNestedScrollingEnabled(false);
        rv_order.setNestedScrollingEnabled(false);
        rv_log.setLayoutManager(new LinearLayoutManager(this));
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        rv_order.setAdapter(originFinishOrderAdapter);
        rv_log.setAdapter(originFinishLogAdapter);
        getDetail();
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("sampleId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getFinishDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        originFinishDetail = new Gson().fromJson(data.toString(), OriginFinishDetail.class);
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

    private void initData() {
        if(!TextUtils.isEmpty(originFinishDetail.getSampleVo().getImagesUrl())){
            Glide.with(this).load(originFinishDetail.getSampleVo().getImagesUrl()).into(iv_img);
        }else{
            iv_img.setVisibility(View.GONE);
        }
        tv_num.setText(originFinishDetail.getInventorySample().getQuantity()+"");
        tv_name.setText(originFinishDetail.getSampleVo().getSampleNo());
        tv_client.setText(originFinishDetail.getSampleVo().getClientRecordName());
        tv_style.setText(originFinishDetail.getSampleVo().getProductCategory1Name() + originFinishDetail.getSampleVo().getProductCategory2Name());
        tv_designer.setText(originFinishDetail.getSampleVo().getDesignerName());
        originFinishLogAdapter.setNewData(originFinishDetail.getInventorySampleLogList());
        originFinishOrderAdapter.setNewData(originFinishDetail.getOrdersList());
        finishSizeAdapter.setNewData(originFinishDetail.getSizeList());
        //rv_log.setAdapter(originLogInfoAdapter);
    }

    @OnClick({R.id.iv_back, R.id.tv_post1, R.id.tv_post2, R.id.tv_post3, R.id.tv_post4})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post1:
                type = 1;
                intent = new Intent(OriginFinishDetailActivity.this, CheckStorageActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type",type);
                startActivityForResult(intent, 3);
                break;
            case R.id.tv_post2:
                type = 2;
                intent = new Intent(OriginFinishDetailActivity.this, CheckStorageActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type",type);
                startActivityForResult(intent, 4);
                break;
            case R.id.tv_post3:
                intent = new Intent(OriginFinishDetailActivity.this, CancelGoodsActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_post4:
                intent = new Intent(OriginFinishDetailActivity.this, SendGoodsActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, 2);
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_origin, null);
        EditText et_num = view.findViewById(R.id.et_num);
        EditText et_remark = view.findViewById(R.id.et_remark);

        TextView tv_submit = view.findViewById(R.id.tv_submit);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);

        if (type == 1) {
            tv_title.setText("盘点");
        } else if (type == 2) {
            tv_title.setText("入库");
        } else {
            tv_title.setText("报废");
        }

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String num = et_num.getEditableText().toString();
                String remark = et_remark.getEditableText().toString();

                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入数量！");
                    return;
                }

                if (type == 1) {
                    piandan(num, remark);
                } else if (type == 2) {
                    entry(num, remark);
                } else {

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
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = (int) (d.getWidth() * 0.8);
        window.setAttributes(layoutParams);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
          getDetail();
    }
}
