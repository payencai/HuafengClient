package com.huafeng.client.ui.home.activity.produce;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.select.SelectEmployeeActivity;
import com.huafeng.client.ui.home.model.Designer;
import com.lzy.okgo.model.HttpParams;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WorkAssignActivity extends BaseActivity {
    int productionOrderFlowId;
    int productionOrderId;
    int num;
    @BindView(R.id.rv_work)
    RecyclerView rv_work;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    List<ShareWork> shareWorks;
    ShareWorkAdapter adapter;
    Designer designer;
    int pos = 0;
    int status;
    WorkSubmit workSubmit;
    ShareChildAdapter childAdapter;
    PreWork preWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_assign);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        status = getIntent().getIntExtra("status", 0);
        if (status >= 2) {
            tv_submit.setVisibility(View.GONE);
        }
        productionOrderFlowId = getIntent().getIntExtra("id", 0);
        productionOrderId = getIntent().getIntExtra("orderId", 0);
        num = getIntent().getIntExtra("num", 0);
        workSubmit = new WorkSubmit();

        initView();
    }

    private boolean checkInput(ShareWork shareWork) {
        boolean isOk = true;
        for (int i = 0; i <shareWork.getWorkallocationList().size() ; i++) {
            if(shareWork.getWorkallocationList().get(i).getPrice()<=0){
                ToastUtils.showLong("价格不正确");
                isOk=false;
                break;
            }
        }
        for (int i = 0; i <shareWork.getWorkallocationList().size() ; i++) {
            if(shareWork.getWorkallocationList().get(i).getQuantityAllotted()<=0){
                ToastUtils.showLong("数量不正确");
                isOk=false;
                break;
            }
        }
        return isOk;
    }

    private void setData(ShareWork shareWork) {
        List<WorkSubmit.WorkallocationListBean> workAllocationProcessListBeans = new ArrayList<>();
        for (int j = 0; j < shareWork.getWorkallocationList().size(); j++) {
            ShareWork.WorkallocationListBean bean = shareWork.getWorkallocationList().get(j);

            WorkSubmit.WorkallocationListBean workallocationListBean = new WorkSubmit.WorkallocationListBean();
            workallocationListBean.setPrice(bean.getPrice());
            workallocationListBean.setPayType(bean.getPayType());
            workallocationListBean.setEmployeeRecordId(bean.getEmployeeRecordId());
            workallocationListBean.setTemporaryWorkerName(bean.getTemporaryWorkerName());
            workallocationListBean.setQuantityAllotted(bean.getQuantityAllotted());
            workAllocationProcessListBeans.add(workallocationListBean);
        }
        workSubmit.setProductionOrderWorkAllocationProcessId(shareWork.getProductionOrderProductProcessId());
        workSubmit.setWorkallocationList(workAllocationProcessListBeans);

    }

    private void submit() {
        String json = new Gson().toJson(workSubmit);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Production.leaderAllocate, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
                        finish();
//                        shareWorks.clear();
//                        getData();
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
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

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shareWorks = new ArrayList<>();
        adapter = new ShareWorkAdapter(shareWorks);

        adapter.setOnAddListener(new ShareWorkAdapter.OnAddListener() {
            @Override
            public void onAdd(int position, ShareChildAdapter shareChildAdapter) {
                childAdapter = shareChildAdapter;
                pos = position;
                startActivityForResult(new Intent(WorkAssignActivity.this, SelectEmployeeActivity.class), 1);
            }

            @Override
            public void onSelect(int position, ShareChildAdapter shareChildAdapter) {
                childAdapter = shareChildAdapter;
                pos = position;
                //onSinglePicker();
                showDialog();
            }

            @Override
            public void onSubmit(int pos) {
                ShareWork shareWork=shareWorks.get(pos);
                if (checkInput(shareWork)&&shareWork!=null) {
                    setData(shareWork);
                    submit();
                }
            }
        });
        rv_work.setLayoutManager(new LinearLayoutManager(this));
        rv_work.setAdapter(adapter);
        getData();
    }

//    public void onSinglePicker() {
//        List<String> data = new ArrayList<>();
//        data.add("月结");
//        data.add("现结");
//        SinglePicker<String> picker = new SinglePicker<>(this, data);
//        picker.setCanceledOnTouchOutside(false);
//        picker.setSelectedIndex(1);
//        picker.setCycleDisable(true);
//        picker.setItemWidth(100);
//
//        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
//            @Override
//            public void onItemPicked(int index, String item) {
//                if (item.equals("月结")) {
//                    childAdapter.getData().get(pos).setPayType(1);
//                } else {
//                    childAdapter.getData().get(pos).setPayType(2);
//                }
//                childAdapter.notifyDataSetChanged();
//
//            }
//        });
//        picker.show();
//    }


    int select = 0;

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_type, null);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        LoopView loopView = (LoopView) view.findViewById(R.id.loopView);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (select == 0) {
                    childAdapter.getData().get(pos).setPayType(1);
                } else {
                    childAdapter.getData().get(pos).setPayType(2);
                }
                childAdapter.notifyDataSetChanged();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add("月结");
        list.add("现结");
        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                select = index;
            }
        });
        // 设置原始数据
        loopView.setItems(list);
        loopView.setNotLoop();
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            designer = (Designer) data.getSerializableExtra("data");
            ShareWork shareWork = shareWorks.get(pos);
            List<ShareWork.WorkallocationListBean> workallocationListBeans = new ArrayList<>();
            ShareWork.WorkallocationListBean workallocationListBean = new ShareWork.WorkallocationListBean();
            workallocationListBean.setPayType(1);
            workallocationListBean.setName(designer.getName());
            workallocationListBean.setPrice(shareWork.getPrice());
            workallocationListBean.setEmployeeRecordId(designer.getId());
            childAdapter.addData(workallocationListBean);
            adapter.getData().get(pos).setWorkallocationList(childAdapter.getData());
        }
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("productionOrderFlowId", productionOrderFlowId);
        NetUtils.getInstance().get(MyApp.token, Api.Production.getAllocationForLeader, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            PreWork preWork = new Gson().fromJson(item.toString(), PreWork.class);
                            ShareWork shareWork = new ShareWork();
                            shareWork.setStatus(preWork.getStatus());
                            shareWork.setNum(preWork.getQuantityAllotted());
                            shareWork.setPrice(preWork.getProcessPrice());
                            shareWork.setProcessName(preWork.getProcessName());
                            shareWork.setProductionOrderProductProcessId(preWork.getId());
                            List<ShareWork.WorkallocationListBean> workallocationListBeanList = new ArrayList<>();
                            for (int j = 0; j < preWork.getWorkAllocationList().size(); j++) {
                                PreWork.WorkAllocationListBean workAllocationListBean = preWork.getWorkAllocationList().get(j);
                                ShareWork.WorkallocationListBean workallocationListBean = new ShareWork.WorkallocationListBean();
                                workallocationListBean.setPayType(workAllocationListBean.getPayType());
                                Log.e("name",workAllocationListBean.getEmployeeRecordId()+"");
                                workallocationListBean.setName(workAllocationListBean.getEmployeeName());
                                workallocationListBean.setTemporaryWorkerName(workAllocationListBean.getTemporaryWorkerName());
                                workallocationListBean.setPrice(workAllocationListBean.getPrice());
                                workallocationListBean.setStatus(shareWork.getStatus());
                                workallocationListBean.setQuantityAllotted(workAllocationListBean.getQuantityAllotted());
                                workallocationListBean.setEmployeeRecordId(workallocationListBean.getEmployeeRecordId());
                                workallocationListBeanList.add(workallocationListBean);
                            }
                            shareWork.setWorkallocationList(workallocationListBeanList);
                            shareWorks.add(shareWork);
                        }
                        adapter.setNewData(shareWorks);


                    } else {
                        ToastUtils.showShort("你没有权限");
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


}
