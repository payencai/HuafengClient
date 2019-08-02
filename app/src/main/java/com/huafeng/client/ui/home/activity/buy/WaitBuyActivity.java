package com.huafeng.client.ui.home.activity.buy;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.search.SearchWaitorderActivity;
import com.huafeng.client.ui.home.fragment.BuyFragment;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaitBuyActivity extends BaseActivity {

    @BindView(R.id.tab_order)
    SlidingTabLayout tab_design;
    @BindView(R.id.viewPager)
    ViewPager vp_design;
    ArrayList<Fragment> mFragments;
    String []mTitles={"订单采购","生产单采购","原料统计"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_buy);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
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
    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaitBuyActivity.this, SearchWaitorderActivity.class));
            }
        });
        mFragments=new ArrayList<>();
        for (int i = 1; i <3 ; i++) {
            BuyFragment orderListFragment=BuyFragment.newInstance(i);
            mFragments.add(orderListFragment);
        }
        mFragments.add(new StatisticsFragment());
        tab_design.setViewPager(vp_design,mTitles,this,mFragments);
    }
}
