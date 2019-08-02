package com.huafeng.client.ui.home.activity.buy;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.search.SearchAskActivity;
import com.huafeng.client.ui.home.adapter.AskOrderAdapter;
import com.huafeng.client.ui.home.model.AskOrder;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fj.dropdownmenu.lib.concat.DropdownI;
import fj.dropdownmenu.lib.ion.ViewInject;
import fj.dropdownmenu.lib.ion.ViewUtils;
import fj.dropdownmenu.lib.pojo.DropdownItemObject;
import fj.dropdownmenu.lib.utils.DropdownUtils;
import fj.dropdownmenu.lib.view.DropdownButton;
import fj.dropdownmenu.lib.view.DropdownColumnView;

public class AskOrderActivity extends BaseActivity implements DropdownI.SingleRow {
    int page = 1;
    boolean isLoadMore = false;
    List<DropdownItemObject> mLeftList;
    List<DropdownItemObject> mRightList;
    int type = -1;
    int status = -1;
    @BindView(R.id.mask)
    View mask;
    @ViewInject(R.id.btn_status)
    @BindView(R.id.btn_status)
    DropdownButton btn_status;

    @ViewInject(R.id.btn_type)
    @BindView(R.id.btn_type)
    DropdownButton btn_type;

    @ViewInject(R.id.lvStatus)
    @BindView(R.id.lvStatus)
    DropdownColumnView lvStatus;

    @ViewInject(R.id.lvType)
    @BindView(R.id.lvType)
    DropdownColumnView lvType;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;


    @BindView(R.id.rv_design)
    RecyclerView rv_finish;
    AskOrderAdapter mAskOrderAdapter;
    List<AskOrder> mAskOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_order);
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
    @OnClick({R.id.iv_back,R.id.iv_search})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                startActivity(new Intent(AskOrderActivity.this, SearchAskActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }

    private void initSelect() {
        mLeftList = new ArrayList<>();
        mRightList = new ArrayList<>();
        DropdownItemObject dropdownItemObject = new DropdownItemObject(1, -1, "全部", "全部");
        DropdownItemObject dropdownItemObject1 = new DropdownItemObject(1, 1, "待审批", "待审批");
        DropdownItemObject dropdownItemObject2 = new DropdownItemObject(1, 2, "已通过", "已通过");
        DropdownItemObject dropdownItemObject6 = new DropdownItemObject(1, 3, "已拒绝", "已拒绝");
        DropdownItemObject dropdownItemObject5 = new DropdownItemObject(2, 0, "全部", "全部");
        DropdownItemObject dropdownItemObject3 = new DropdownItemObject(2, 1, "订单采购", "订单采购");
        DropdownItemObject dropdownItemObject4 = new DropdownItemObject(2, 2, "生产单采购", "生产单采购");
        mLeftList.add(dropdownItemObject);
        mLeftList.add(dropdownItemObject1);
        mLeftList.add(dropdownItemObject2);
        mLeftList.add(dropdownItemObject6);
        mRightList.add(dropdownItemObject5);
        mRightList.add(dropdownItemObject3);
        mRightList.add(dropdownItemObject4);
        lvStatus.setSingleRow(AskOrderActivity.this)
                .setSingleRowList(mLeftList, -1)  //单列数据
                .setButton(btn_status) //按钮
                .show();
        lvType.setSingleRow(AskOrderActivity.this)
                .setSingleRowList(mRightList, 0)  //单列数据
                .setButton(btn_type) //按钮
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        refresh();
    }

    private void initView() {
        mAskOrders = new ArrayList<>();
        DropdownUtils.init(this, mask);
        ViewUtils.injectViews(this, mask);
        initSelect();
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        mAskOrderAdapter = new AskOrderAdapter(R.layout.item_ask_order, mAskOrders);
        mAskOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                AskOrder askOrder = (AskOrder) adapter.getItem(position);
                Intent intent = new Intent(AskOrderActivity.this, AskDetailActivity.class);
                intent.putExtra("id", askOrder.getId());
                startActivityForResult(intent,1);
            }
        });
        rv_finish.setLayoutManager(new LinearLayoutManager(this));
        rv_finish.setAdapter(mAskOrderAdapter);
        getData();
    }

    private void refresh() {
        page = 1;
        mAskOrders.clear();
        mAskOrderAdapter.setNewData(mAskOrders);
        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        if (status > 0) {
            httpParams.put("status", status);
        }
        if (type > 0) {
            httpParams.put("type", type);
        }
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.getRequestList, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<AskOrder> buyOrders = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            AskOrder buyOrder = new Gson().fromJson(item.toString(), AskOrder.class);
                            mAskOrders.add(buyOrder);
                            buyOrders.add(buyOrder);
                        }
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() > 0) {
                                mAskOrderAdapter.addData(buyOrders);
                                mAskOrderAdapter.loadMoreComplete();
                            } else {
                                mAskOrderAdapter.loadMoreEnd(true);
                            }
                        } else {
                            mAskOrderAdapter.setNewData(mAskOrders);
                        }

                    } else {
                        ToastUtils.showShort("你没有权限！");
                        finish();
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

    @Override
    public void onSingleChanged(DropdownItemObject dropdownItemObject) {
        if (dropdownItemObject.parentId == 1) {
            status = dropdownItemObject.id;
            if (dropdownItemObject.id == -1) {
                status = -1;
            }
        } else {
            type = dropdownItemObject.id;
            if (dropdownItemObject.id == 0) {
                type = -1;
            }
        }
        refresh();
    }
}
