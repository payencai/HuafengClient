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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.view.MathUtils;
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

public class OriginStorageDetailActivity extends BaseActivity {
    int id;
    OriginStorageDetail originStorageDetail;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_post1)
    TextView tv_post1;
    @BindView(R.id.tv_post3)
    TextView tv_post3;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.rv_origin)
    RecyclerView rv_origin;
    @BindView(R.id.rv_log)
    RecyclerView rv_log;
    OriginPriceInfoAdapter originPriceInfoAdapter;
    OriginLogInfoAdapter originLogInfoAdapter;
    List<OriginStorageDetail.InventoryMaterialLogListBean> logListBeans;
    List<OriginStorageDetail.PurchaseNoteSupplierListBean> supplierListBeans;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin_storage_detail);
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
    private void initView() {
        logListBeans=new ArrayList<>();
        supplierListBeans=new ArrayList<>();
        originPriceInfoAdapter = new OriginPriceInfoAdapter(supplierListBeans);
        originLogInfoAdapter = new OriginLogInfoAdapter(logListBeans);
        rv_origin.setLayoutManager(new LinearLayoutManager(this));
        rv_origin.setAdapter(originPriceInfoAdapter);
        rv_log.setLayoutManager(new LinearLayoutManager(this));
        rv_log.setAdapter(originLogInfoAdapter);
        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoUtil.seeSinglePhoto(OriginStorageDetailActivity.this,originStorageDetail.getRawMaterial().getImageUrl(),iv_img);
            }
        });
        getDetail();
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("materialId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getOriginStorageDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        originStorageDetail = new Gson().fromJson(data.toString(), OriginStorageDetail.class);
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
        tv_name.setText(originStorageDetail.getRawMaterial().getName());
        tv_style.setText(originStorageDetail.getRawMaterial().getCategory1Name() + " " + originStorageDetail.getRawMaterial().getCategory2Name());
        if (originStorageDetail.getInventoryMaterial() != null)
            tv_num.setText(originStorageDetail.getInventoryMaterial().getNormalQuantity() + "");
        else{
            tv_num.setText("0");
            tv_post1.setVisibility(View.GONE);
            tv_post3.setVisibility(View.GONE);
        }
        logListBeans.addAll(originStorageDetail.getInventoryMaterialLogList());
        supplierListBeans.addAll(originStorageDetail.getPurchaseNoteSupplierList());
        originPriceInfoAdapter.setNewData(supplierListBeans);
        originLogInfoAdapter.setNewData(logListBeans);
        if(!TextUtils.isEmpty(originStorageDetail.getRawMaterial().getImageUrl())){
            Glide.with(this).load(originStorageDetail.getRawMaterial().getImageUrl()).into(iv_img);
        }else{
            iv_img.setVisibility(View.GONE);
        }

    }

    private void piandan(String num, String remarks, String cloth) {
        Map<String, Object> params = new HashMap<>();
        params.put("materialId", originStorageDetail.getRawMaterial().getId());
        params.put("normalQuantity", Double.valueOf(num));
        params.put("remarks", remarks);
        if (!TextUtils.isEmpty(cloth)) {
            params.put("clothQuantity", cloth);
        }
        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.check, json, MyApp.token, new OnMessageReceived() {
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

    private void entry(String num, String remarks, String cloth) {
        Map<String, Object> params = new HashMap<>();
        params.put("materialId", originStorageDetail.getRawMaterial().getId());
        params.put("normalQuantity", Double.valueOf(num));
        params.put("remarks", remarks);
        if (!TextUtils.isEmpty(cloth)) {
            params.put("clothQuantity", cloth);
        }
        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.entry, json, MyApp.token, new OnMessageReceived() {
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

    private void scrap(String num, String remarks, String cloth) {
        Map<String, Object> params = new HashMap<>();
        params.put("materialId", originStorageDetail.getRawMaterial().getId());
        params.put("normalQuantity", Double.valueOf(num));
        params.put("remarks", remarks);
        if (!TextUtils.isEmpty(cloth)) {
            params.put("clothQuantity", cloth);
        }
        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.scrap, json, MyApp.token, new OnMessageReceived() {
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

    private double calTotal(ArrayList<String> cloths) {
        double total = 0;
        for (int i = 0; i < cloths.size(); i++) {
            LogUtils.e(cloths.get(i));
            if (MathUtils.isNumber(cloths.get(i))) {
                total = total + Double.parseDouble(cloths.get(i));
            }
        }
        return total;
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_origin, null);
        EditText et_num = view.findViewById(R.id.et_num);
        EditText et_remark = view.findViewById(R.id.et_remark);
        EditText et_cloth = view.findViewById(R.id.et_cloth);
        TextView tv_submit = view.findViewById(R.id.tv_submit);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        LinearLayout ll_cloth = view.findViewById(R.id.ll_cloth);
        if (type == 1) {
            tv_title.setText("盘点");
        } else if (type == 2) {
            tv_title.setText("入库");
        } else {
            tv_title.setText("报废");
        }
        if (originStorageDetail.getRawMaterial().getCategory1Id() == 1) {
            ll_cloth.setVisibility(View.VISIBLE);
        }
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String num = et_num.getEditableText().toString();
                String remark = et_remark.getEditableText().toString();
                String cloth = et_cloth.getEditableText().toString();
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入数量！");
                    return;
                }
                if (!MathUtils.isNumber(num)) {
                    ToastUtils.showShort("请输入数字！");
                    return;
                }
                if (originStorageDetail.getRawMaterial().getCategory1Id() == 1 && TextUtils.isEmpty(cloth)) {
                    ToastUtils.showShort("请输入布料");
                    return;
                }
                if (!TextUtils.isEmpty(cloth)) {
                    double value = calTotal(StringUtils.StringToArrayList(cloth, ","));
                    if (value != Double.parseDouble(num)) {
                        ToastUtils.showShort("输入的数量要等于布料之和");
                        return;
                    }
                }
                if (type == 1) {
                    piandan(num, remark, cloth);
                } else if (type == 2) {
                    entry(num, remark, cloth);
                } else {
                    scrap(num, remark, cloth);
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
        logListBeans.clear();
        supplierListBeans.clear();
        getDetail();
    }

    @OnClick({R.id.iv_back, R.id.tv_post1, R.id.tv_post2, R.id.tv_post3,R.id.tv_post4})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.tv_post4:
                Intent intent2=new Intent(OriginStorageDetailActivity.this,UpdateOriginDataActivity.class);
                intent2.putExtra("data",originStorageDetail.getRawMaterial());
                startActivityForResult(intent2,2);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post1:
                type = 1;
                showDialog();
                break;
            case R.id.tv_post2:
                Intent intent=new Intent(OriginStorageDetailActivity.this,EntryOriginDataActivity.class);
                intent.putExtra("id",originStorageDetail.getRawMaterial().getId());
                if(originStorageDetail.getRawMaterial().getCategory1Id()==1){
                    intent.putExtra("isShow",true);
                }
                startActivityForResult(intent,1);
                break;
            case R.id.tv_post3:
                type = 3;
                showDialog();
                break;

        }
    }
}
