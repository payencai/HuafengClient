package com.huafeng.client.ui.home.activity.client;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientDetailActivity extends BaseActivity {

    @BindView(R.id.tab_order)
    SlidingTabLayout tab_design;
    @BindView(R.id.viewPager)
    ViewPager vp_design;
    ArrayList<Fragment> mFragments;
    String[] mTitles = {"基本信息", "客户订单"};

    int id;
    int clientid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        clientid = getIntent().getIntExtra("clientid", 0);
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

    @OnClick({R.id.iv_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }



    private void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(BasicFragment.newInstance(id));
        mFragments.add(ClientOrderFragment.newInstance(id));
      //  mFragments.add(FinanceFragment.newInstance(id));
        tab_design.setViewPager(vp_design, mTitles, this, mFragments);
    }
}
