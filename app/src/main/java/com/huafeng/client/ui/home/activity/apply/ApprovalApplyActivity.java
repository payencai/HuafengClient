package com.huafeng.client.ui.home.activity.apply;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.search.SearchMyApprovalActivity;
import com.huafeng.client.ui.home.adapter.ApprovalApplyAdapter;
import com.huafeng.client.ui.home.model.ApprovalApply;
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

public class ApprovalApplyActivity extends BaseActivity implements DropdownI.SingleRow {
    int page = 1;
    boolean isLoadMore = false;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.mask)
    View mask;
    @ViewInject(R.id.db_type)
    @BindView(R.id.db_type)
    DropdownButton db_type;
    @ViewInject(R.id.lv_type)
    @BindView(R.id.lv_type)
    DropdownColumnView lv_type;

    @ViewInject(R.id.db_state)
    @BindView(R.id.db_state)
    DropdownButton db_state;
    @ViewInject(R.id.lv_state)
    @BindView(R.id.lv_state)
    DropdownColumnView lv_state;
    List<DropdownItemObject> mLeftList;
    List<DropdownItemObject> mRightList;
    @BindView(R.id.rv_design)
    RecyclerView rv_finish;
    ApprovalApplyAdapter mApprovalApplyAdapter;
    List<ApprovalApply> mApprovalApplies;
    int type = 0;
    int status = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_apply);
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
    @OnClick({R.id.iv_add, R.id.iv_back,R.id.iv_search})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_search:
                startActivity(new Intent(ApprovalApplyActivity.this, SearchMyApprovalActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                startActivityForResult(new Intent(ApprovalApplyActivity.this, AddAprovalActivity.class), 2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refresh();
    }

    private void initSelect() {
        mLeftList = new ArrayList<>();
        mRightList = new ArrayList<>();
        DropdownItemObject left = new DropdownItemObject(-1, 0, "全部", "全部");

        mLeftList.add(left);

        DropdownItemObject dropdownItemObject1 = new DropdownItemObject(-1, 1, "人事审批", "type");
        DropdownItemObject dropdownItemObject2 = new DropdownItemObject(-1, 2, "财务审批", "type");
        mLeftList.add(dropdownItemObject1);
        mLeftList.add(dropdownItemObject2);
        lv_type.setSingleRow(ApprovalApplyActivity.this)
                .setSingleRowList(mLeftList, 0)  //单列数据
                .setButton(db_type) //按钮
                .show();
        DropdownItemObject right = new DropdownItemObject(-2, -1, "全部", "全部");
        mRightList.add(right);
        DropdownItemObject dropdownItemObject3 = new DropdownItemObject(-2, 0, "待审批", "state");
        DropdownItemObject dropdownItemObject4 = new DropdownItemObject(-2, 1, "已通过", "state");
        DropdownItemObject dropdownItemObject5 = new DropdownItemObject(-2, 2, "已拒绝", "state");
        mRightList.add(dropdownItemObject3);
        mRightList.add(dropdownItemObject4);
        mRightList.add(dropdownItemObject5);

        lv_state.setSingleRow(ApprovalApplyActivity.this)
                .setSingleRowList(mRightList, -1)  //单列数据
                .setButton(db_state) //按钮
                .show();
    }

    private void initView() {
        DropdownUtils.init(this, mask);
        ViewUtils.injectViews(this, mask);
        mApprovalApplies = new ArrayList<>();

        mApprovalApplyAdapter = new ApprovalApplyAdapter(R.layout.item_approval_apply, mApprovalApplies);
        mApprovalApplyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                ApprovalApply approvalApply = (ApprovalApply) adapter.getItem(position);
                Intent intent = new Intent(ApprovalApplyActivity.this, ApprovalApplyDetailActivity.class);
                intent.putExtra("id", approvalApply.getId());
                intent.putExtra("flag", 1);
                startActivityForResult(intent, 1);
            }
        });
        mApprovalApplyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                isLoadMore = true;
                getData();
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });
        rv_finish.setLayoutManager(new LinearLayoutManager(this));
        rv_finish.setAdapter(mApprovalApplyAdapter);
        initSelect();
        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", page);
        if (type > 0) {
            httpParams.put("type", type);
        }
        if (status > -1) {
            httpParams.put("status", status);
        }

        NetUtils.getInstance().get(MyApp.token, Api.Approval.getMyApprovals, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                refresh.finishRefresh();
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        jsonObject = jsonObject.getJSONObject("data");
                        List<ApprovalApply> applyList = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("beanList");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            ApprovalApply approvalApply = new Gson().fromJson(item.toString(), ApprovalApply.class);
                            applyList.add(approvalApply);
                        }
                        mApprovalApplyAdapter.addData(applyList);
                        if (isLoadMore) {
                            isLoadMore = false;
                            if (data.length() > 0) {
                                mApprovalApplyAdapter.loadMoreComplete();
                            } else {
                                mApprovalApplyAdapter.loadMoreEnd(true);
                            }
                        } else {
                            mApprovalApplyAdapter.loadMoreComplete();
                        }

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

    private void refresh() {
        page = 1;
        mApprovalApplies.clear();
        mApprovalApplyAdapter.setNewData(mApprovalApplies);
        getData();
    }

    @Override
    public void onSingleChanged(DropdownItemObject dropdownItemObject) {

        if (dropdownItemObject.parentId == -1) {
            type = dropdownItemObject.id;
            Log.e("value", dropdownItemObject.value + "-" + type + "-" + status);
        } else {
            status = dropdownItemObject.id;
            Log.e("value", dropdownItemObject.value + "-" + type + "-" + status);
        }

        refresh();
    }
}
