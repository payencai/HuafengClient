package com.huafeng.client.ui.home.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.sample.SeeProcessActivity;
import com.huafeng.client.ui.home.adapter.ModelDetailRawAdapter;
import com.huafeng.client.ui.home.adapter.ModelDetailSizeAdapter;
import com.huafeng.client.ui.home.adapter.PhotoAdapter;
import com.huafeng.client.ui.home.model.ModelDetail;
import com.huafeng.client.view.ListViewForScrollView;
import com.huafeng.client.view.MyGridView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModelDataDetailActivity extends BaseActivity {
    int id;
    @BindView(R.id.gv_photo)
    MyGridView gv_photo;
    @BindView(R.id.lv_size)
    ListViewForScrollView lv_size;
    @BindView(R.id.gv_raw)
    MyGridView gv_raw;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.tv_no)
    TextView tv_no;
    @BindView(R.id.rl_zhi)
    RelativeLayout rl_zhi;
    @BindView(R.id.tv_zhi)
    TextView tv_zhi;
    @BindView(R.id.tv_designer)
    TextView tv_designer;
    @BindView(R.id.tv_client)
    TextView tv_client;
    @BindView(R.id.tv_wash)
    TextView tv_wash;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    PhotoAdapter mPhotoAdapter;
    ArrayList<String> images;
    ModelDetailSizeAdapter modelDetailSizeAdapter;
    ModelDetailRawAdapter modelDetailRawAdapter;
    ModelDetail modelDetail;
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_data_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        json = getIntent().getStringExtra("json");
        id = getIntent().getIntExtra("id", 0);
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

    @OnClick({R.id.iv_back, R.id.tv_see})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_see:
                Intent intent = new Intent(ModelDataDetailActivity.this, SeeProcessActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("json",json);
                startActivityForResult(intent, 1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void initView() {
        if (!TextUtils.isEmpty(json)) {
            modelDetail = new Gson().fromJson(json, ModelDetail.class);
            initData();
        } else {
            getDetail();
        }

    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        modelDetail = new Gson().fromJson(data.toString(), ModelDetail.class);
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
        images = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(this, images);
        gv_photo.setAdapter(mPhotoAdapter);
        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoUtil.seeBigPhoto(ModelDataDetailActivity.this, position, images, view);
            }
        });
        modelDetailSizeAdapter = new ModelDetailSizeAdapter(this, modelDetail.getSizeList());
        modelDetailRawAdapter = new ModelDetailRawAdapter(this, modelDetail.getMaterialList());
        lv_size.setAdapter(modelDetailSizeAdapter);
        gv_raw.setAdapter(modelDetailRawAdapter);
        String imgs = modelDetail.getImagesUrl();
        if (!TextUtils.isEmpty(imgs)) {
            images.addAll(StringUtils.StringToArrayList(imgs, ","));
        }
        String no = modelDetail.getPatternNo();
        if (!TextUtils.isEmpty(no)) {
            rl_zhi.setVisibility(View.VISIBLE);
            tv_zhi.setText(no);
        } else {
            rl_zhi.setVisibility(View.GONE);
        }
        mPhotoAdapter.notifyDataSetChanged();
        tv_color.setText(modelDetail.getColor());
        tv_client.setText(modelDetail.getClientRecordName());
        tv_designer.setText(modelDetail.getDesignerName());
        tv_no.setText(modelDetail.getSampleNo());
        tv_style.setText(modelDetail.getProductCategory1Name() + " " + modelDetail.getProductCategory2Name());
        tv_remark.setText(modelDetail.getRemarks());
        String wash = "";
        for (int i = 0; i < modelDetail.getWashingProcessList().size(); i++) {
            wash = wash + " " + modelDetail.getWashingProcessList().get(i).getWashingProcessName();
        }
        tv_wash.setText(wash);
    }
}
