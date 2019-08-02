package com.huafeng.client.ui.home.activity.produce;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.huafeng.client.R;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.search.SearchProduceActivity;
import com.huafeng.client.ui.home.fragment.ProduceFragment;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProduceManaActivity extends BaseActivity {



    @BindView(R.id.tab_order)
    SlidingTabLayout tab_design;
    @BindView(R.id.viewPager)
    ViewPager vp_design;
    ArrayList<Fragment> mFragments;
    String []mTitles={"全部","车间","洗水","后整","已完成","终止"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produce_mana);
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
    @OnClick({R.id.iv_search,R.id.iv_back})
    void onClick(View view){
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()){
            case R.id.iv_search:
                startActivity(new Intent(ProduceManaActivity.this, SearchProduceActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void initView() {
        mFragments=new ArrayList<>();
        ProduceFragment produceFragment=ProduceFragment.newInstance(0);
        mFragments.add(produceFragment);
        for (int i = 2; i <7 ; i++) {
            ProduceFragment orderListFragment=ProduceFragment.newInstance(i);
            mFragments.add(orderListFragment);
        }
        tab_design.setViewPager(vp_design,mTitles,this,mFragments);
    }
}
