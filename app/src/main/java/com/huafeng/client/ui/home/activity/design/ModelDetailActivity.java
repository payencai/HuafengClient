package com.huafeng.client.ui.home.activity.design;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.DetailMaterialAdapter;
import com.huafeng.client.ui.home.adapter.DetailSizeAdapter;
import com.huafeng.client.ui.home.adapter.PhotoAdapter;
import com.huafeng.client.ui.home.model.DesignDetail;
import com.huafeng.client.view.ListViewForScrollView;
import com.huafeng.client.view.MyGridView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
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

public class ModelDetailActivity extends BaseActivity {
    DesignDetail mDesignDetail;
    int id;
    @BindView(R.id.gv_photo)
    MyGridView gv_photo;
    @BindView(R.id.lv_size)
    ListViewForScrollView lv_size;
    @BindView(R.id.lv_raw)
    ListViewForScrollView lv_raw;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.tv_size)
    TextView tv_size;
    @BindView(R.id.rl_zhi)
    RelativeLayout rl_zhi;
    @BindView(R.id.tv_zhi)
    TextView tv_zhi;
    @BindView(R.id.tv_wash)
    TextView tv_wash;
    @BindView(R.id.tv_create)
    TextView tv_create;
    @BindView(R.id.tv_copy)
    TextView tv_copy;
    @BindView(R.id.tv_remark)
    TextView tv_remark;

    PhotoAdapter mPhotoAdapter;
    ArrayList<String> images;
    DetailSizeAdapter sizeAdapter;
    DetailMaterialAdapter materialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_detail);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
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

    private void getAuth() {

        NetUtils.getInstance().get(MyApp.token, Api.Authority.getMyAuthority, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            int authId = data.getInt(i);
                            if (authId == 1011 || (mDesignDetail.getPatternMakingFlow().getDesignById() == MyApp.getUserInfo().getEmployeeRecordId())) {
                                if (mDesignDetail.getPatternMakingFlow().getStatus() == 6 && mDesignDetail.getPatternMakingFlow().getSampleId() == -1) {
                                    tv_create.setVisibility(View.VISIBLE);
                                } else {
                                    tv_create.setVisibility(View.GONE);
                                }
                                tv_copy.setVisibility(View.VISIBLE);
                            }

                        }
                    } else {

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

    @OnClick({R.id.iv_back, R.id.tv_copy, R.id.tv_create})
    public void OnClick(View view) {
        if (CheckDoubleClick.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_create:
                Intent intent = new Intent(ModelDetailActivity.this, AddModelDataActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("flowId", mDesignDetail.getPatternMakingFlow().getId());
                startActivityForResult(intent, 3);
                break;
            case R.id.tv_copy:
                copy();
                break;
        }
    }

    private void initView() {
        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoUtil.seeBigPhoto(ModelDetailActivity.this, position, images, view);
            }
        });

        images = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(this, images);
        gv_photo.setAdapter(mPhotoAdapter);
        getDetail();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 3 && resultCode == RESULT_OK) {
            tv_create.setVisibility(View.GONE);
        }
    }

    private void copy() {
        Map<String, Object> params = new HashMap<>();
        params.put("flowId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Pattern.copyCard, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("复版成功");
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

    private void initData() {
        materialAdapter = new DetailMaterialAdapter(this, mDesignDetail.getPatternMakingCard().getMaterialList());
        sizeAdapter = new DetailSizeAdapter(this, mDesignDetail.getPatternMakingCard().getSizeInformationList());
        lv_raw.setAdapter(materialAdapter);
        lv_size.setAdapter(sizeAdapter);
        String imgs = mDesignDetail.getPatternMakingCard().getImagesUrl();
        if (!TextUtils.isEmpty(imgs)) {
            images.addAll(StringUtils.StringToArrayList(imgs, ","));
        }
        String no = mDesignDetail.getPatternMakingCard().getPatternNo();
        if (!TextUtils.isEmpty(no)) {
            rl_zhi.setVisibility(View.VISIBLE);
            tv_zhi.setText(no);
        } else {
            rl_zhi.setVisibility(View.GONE);
        }
        mPhotoAdapter.notifyDataSetChanged();
        tv_size.setText(mDesignDetail.getPatternMakingCard().getSizeName());
        tv_color.setText(mDesignDetail.getPatternMakingCard().getColor());
        tv_style.setText(mDesignDetail.getPatternMakingCard().getProductCategory1Name() + " " + mDesignDetail.getPatternMakingCard().getProductCategory2Name());
        tv_remark.setText(mDesignDetail.getPatternMakingCard().getRemarks());
        String wash = "";
        for (int i = 0; i < mDesignDetail.getPatternMakingCard().getWashingProcessList().size(); i++) {
            wash = wash + " " + mDesignDetail.getPatternMakingCard().getWashingProcessList().get(i).getWashingProcessName();
        }
        tv_wash.setText(wash);

        getAuth();
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Pattern.getFlow, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        mDesignDetail = new Gson().fromJson(data.toString(), DesignDetail.class);
                        initData();
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
                LogUtils.e(error);
            }
        });
    }
}
