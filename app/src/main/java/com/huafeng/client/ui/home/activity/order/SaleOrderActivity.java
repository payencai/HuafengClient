package com.huafeng.client.ui.home.activity.order;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.search.SearchSaleActivity;
import com.huafeng.client.ui.home.fragment.OrderFragment;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaleOrderActivity extends BaseActivity {


    @BindView(R.id.tab_order)
    SlidingTabLayout tab_design;
    @BindView(R.id.viewPager)
    ViewPager vp_design;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    ArrayList<Fragment> mFragments;
    String []mTitles={"全部","待确认","执行中","已完成"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @OnClick({R.id.iv_add,R.id.iv_search,R.id.iv_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search:
                startActivity(new Intent(SaleOrderActivity.this, SearchSaleActivity.class));
                break;
            case R.id.iv_add:
                startActivityForResult(new Intent(SaleOrderActivity.this, AddSaleOrderActivity.class),1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
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
                            if (authId == 1022||authId==1021) {
                                iv_add.setVisibility(View.VISIBLE);
                                break;
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
        timer.schedule(task, 1000);//3秒后执行TimeTask的run方法
    }
    private void initView() {
        getAuth();

        showLoading();
        mFragments=new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            OrderFragment orderListFragment=OrderFragment.newInstance(i);
            mFragments.add(orderListFragment);
        }
        tab_design.setViewPager(vp_design,mTitles,this,mFragments);

    }
}
