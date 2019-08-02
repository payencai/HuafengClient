package com.huafeng.client.clientui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.clientui.ClientModelDetail;
import com.huafeng.client.clientui.ClientModelDetailSizeAdapter;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.adapter.PhotoAdapter;
import com.huafeng.client.view.GridViewForScrollView;
import com.huafeng.client.view.ListViewForScrollView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientModelDetailActivity extends AppCompatActivity {
    int id;
    @BindView(R.id.gv_photo)
    GridViewForScrollView gv_photo;
    @BindView(R.id.lv_size)
    ListViewForScrollView lv_size;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.tv_category)
    TextView tv_category;
    PhotoAdapter mPhotoAdapter;
    ArrayList<String> images;
    ClientModelDetailSizeAdapter clientModelDetailSizeAdapter;
    List<ClientModelDetail.SizeListBean> sizeListBeans;
    ClientModelDetail clientModelDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_model_detail);
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
    @OnClick({R.id.iv_back, R.id.tv_create})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_create:
                Intent intent = new Intent(this, ClientCreateOrderActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, 1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void initView() {
        images = new ArrayList<>();
        sizeListBeans = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(this, images);
        clientModelDetailSizeAdapter = new ClientModelDetailSizeAdapter(this, sizeListBeans);
        gv_photo.setAdapter(mPhotoAdapter);
        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoUtil.seeBigPhoto(ClientModelDetailActivity.this, position, images, view);
            }
        });
        lv_size.setAdapter(clientModelDetailSizeAdapter);
        getDetail();
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Sample.getForCustomer, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        clientModelDetail = new Gson().fromJson(data.toString(), ClientModelDetail.class);
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
        String imgs = clientModelDetail.getImagesUrl();
        if (!TextUtils.isEmpty(imgs)) {
            images.addAll(StringUtils.StringToArrayList(imgs, ","));
        }
        sizeListBeans.addAll(clientModelDetail.getSizeList());
        clientModelDetailSizeAdapter.notifyDataSetChanged();
        mPhotoAdapter.notifyDataSetChanged();
        tv_color.setText(clientModelDetail.getColor());
        tv_category.setText(clientModelDetail.getProductCategory1Name() );
        tv_style.setText(clientModelDetail.getProductCategory2Name());

    }
}
