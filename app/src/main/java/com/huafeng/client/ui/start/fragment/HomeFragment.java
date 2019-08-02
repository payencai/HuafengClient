package com.huafeng.client.ui.start.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;

import com.huafeng.client.clientui.activity.ClientModelDataActivity;
import com.huafeng.client.clientui.activity.ClientOrderActivity;
import com.huafeng.client.clientui.activity.ClientStorageActivity;
import com.huafeng.client.clientui.activity.MySupplierActivity;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.model.MyBanner;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;


import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.GlideImageLoader;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.ui.contacts.activity.StrangerDetailActivity;
import com.huafeng.client.ui.home.activity.apply.ApprovalApplyActivity;
import com.huafeng.client.ui.home.activity.apply.ApprovalCenterActivity;
import com.huafeng.client.ui.home.activity.WebviewActivity;
import com.huafeng.client.ui.home.activity.buy.AskOrderActivity;
import com.huafeng.client.ui.home.activity.buy.BuyOrderActivity;
import com.huafeng.client.ui.home.activity.ClockInActivity;
import com.huafeng.client.ui.home.activity.client.CustomManaActivity;
import com.huafeng.client.ui.home.activity.design.DesignDetailActivity;
import com.huafeng.client.ui.home.activity.origin.FinishedStorageActivity;
import com.huafeng.client.ui.home.activity.origin.HalfStorageActivity;
import com.huafeng.client.ui.home.activity.ModelDataActivity;
import com.huafeng.client.ui.home.activity.design.ModelDesignActivity;
import com.huafeng.client.ui.home.activity.OriginalDataActivity;
import com.huafeng.client.ui.home.activity.origin.OriginalStorageActivity;
import com.huafeng.client.ui.home.activity.produce.ProduceDetailActivity;
import com.huafeng.client.ui.home.activity.produce.ProduceManaActivity;
import com.huafeng.client.ui.home.activity.order.SaleOrderActivity;
import com.huafeng.client.ui.home.activity.buy.WaitBuyActivity;

import com.huafeng.client.ui.home.model.Auth;
import com.marquee.dingrui.marqueeviewlib.MarqueeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<String> images;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiver(MsgEvent msgEvent) {
        if(msgEvent.getmMsg()==111||msgEvent.getmMsg()==900){
            images.clear();
            getBanner();

        }
    }




    private void getBanner() {
        NetUtils.getInstance().get(MyApp.token, Api.Banner.getBanner, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(MyApp.token);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            MyBanner myBanner = new Gson().fromJson(item.toString(), MyBanner.class);
                            images.add(myBanner.getImageUrl());
                        }
                        updateBanner();
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
    private void initBanner(){
        images = new ArrayList<>();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                PhotoUtil.seeBigPhoto(getContext(),position,images,mBanner.getChildAt(position));
            }
        });
        mBanner.setImageLoader(new GlideImageLoader());
        List<String> bannerUrl=new ArrayList<>();
        //设置图片集 合
        mBanner.setImages(bannerUrl);

        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }
    private void initView() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                images.clear();
                getBanner();

            }
        });
        initBanner();
        getBanner();


    }
    private void updateBanner() {
        mBanner.update(images);

    }


    @OnClick({ R.id.tv_item1,R.id.tv_item2,R.id.tv_item3,R.id.tv_item4,R.id.tv_item5})
    void onClick(View view) {
        if (CheckDoubleClick.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {

            case R.id.tv_item5:
                startActivity(new Intent(getContext(), MySupplierActivity.class));
                break;
            case R.id.tv_item1:
                startActivity(new Intent(getContext(), ClientOrderActivity.class));
                break;
            case R.id.tv_item2:
                startActivity(new Intent(getContext(), ClientModelDataActivity.class));
                break;
            case R.id.tv_item3:
                startActivity(new Intent(getContext(), ClientStorageActivity.class));
                break;
            case R.id.tv_item4:
                break;

        }
    }


}
