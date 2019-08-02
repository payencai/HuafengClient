package com.huafeng.client.clientui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.huafeng.client.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientOrderActivity extends AppCompatActivity {

    @BindView(R.id.tab_order)
    SlidingTabLayout tab_design;
    @BindView(R.id.viewPager)
    ViewPager vp_design;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    ArrayList<Fragment> mFragments;
    String []mTitles={"全部","待接单","执行中","已完成"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }
    @OnClick({R.id.iv_add, R.id.iv_search, R.id.iv_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search:

                break;
            case R.id.iv_add:
                startActivityForResult(new Intent(ClientOrderActivity.this, ClientCreateOrderActivity.class),1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
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

        showLoading();
        mFragments=new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            ClientOrderFragment orderListFragment=ClientOrderFragment.newInstance(i);
            mFragments.add(orderListFragment);
        }
        tab_design.setViewPager(vp_design,mTitles,this,mFragments);

    }
}
