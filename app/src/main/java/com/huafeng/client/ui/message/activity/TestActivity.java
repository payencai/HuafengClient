package com.huafeng.client.ui.message.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.fragment.OrderFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity {


    @BindView(R.id.tab_order)
    SlidingTabLayout tab_design;
    @BindView(R.id.viewPager)
    ViewPager vp_design;
    ArrayList<Fragment> mFragments;
    String []mTitles={"全部","待确认","执行中","已完成"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mFragments=new ArrayList<>();
        for (int i = 1; i <5 ; i++) {
            OrderFragment orderListFragment=OrderFragment.newInstance(i);
            mFragments.add(orderListFragment);
        }
        tab_design.setViewPager(vp_design,mTitles,this,mFragments);
    }
}
