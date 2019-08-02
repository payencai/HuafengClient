package com.huafeng.client.ui.home.activity.produce;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.ModelDataDetailActivity;
import com.huafeng.client.view.ListViewForScrollView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProduceDetailActivity extends BaseActivity {
    @BindView(R.id.tv_locate)
    TextView tv_locate;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_sampleno)
    TextView tv_sampleno;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_designer)
    TextView tv_designer;
    @BindView(R.id.tv_create)
    TextView tv_create;
    @BindView(R.id.tv_no)
    TextView tv_no;
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.tv_finish)
    TextView tv_finish;
    @BindView(R.id.tv_by)
    TextView tv_by;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.rv_flow)
    RecyclerView rv_flow;
    @BindView(R.id.rv_draw)
    RecyclerView rv_want;
    @BindView(R.id.rv_size)
    RecyclerView rv_size;
    @BindView(R.id.lv_use)
    ListViewForScrollView lv_use;
    @BindView(R.id.tv_want)
    TextView tv_want;
    @BindView(R.id.tv_real)
    TextView tv_real;
    ProduceDetail produceDetail;
    ProduceUseAdapter produceUseAdapter;
    ProduceWantSizeAdapter produceWantSizeAdapter;
    ProduceRealSizeAdapter produceRealSizeAdapter;
    ProduceFlowListAdapter produceFlowListAdapter;
    String json = "";
    List<ProduceDetail.FlowListBean> flowListBeans;
    List<ProduceDetail.MaterialTakeListBean> materialTakeListBeans;
    List<ProduceDetail.SizeQuantityListBean> sizeQuantityListBeans;
    List<ProduceDetail.InventorySizeQuantityListBean> inventorySizeQuantityListBeans;
    int id;
    boolean isShow = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produce_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        materialTakeListBeans = new ArrayList<>();
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

    }

    private void entry() {
        Map<String, Object> params = new HashMap<>();
        params.put("productionOrderId", produceDetail.getProductionOrder().getId());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Production.entryInventorySample, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("入库成功");
                        finish();
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
        LogUtils.e(json);
    }


    private void getAuth() {
        int len = produceDetail.getFlowList().size() - 1;
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
                            //如果当前流程是自己或者是管理员权限，在满足这个前提下，如果是status==1或者如果是status==2
                            if (authId == 1031||MyApp.getUserInfo().getEmployeeRecordId() == produceDetail.getFlowList().get(len).getPrincipalRecordId()) {
                                if (produceDetail.getProductionOrder().getStatus() == 1) {
                                    tv_next.setVisibility(View.VISIBLE);
                                    tv_next.setText("下一步");
                                    isShow = true;
                                } else if (produceDetail.getProductionOrder().getStatus() == 2) {
                                    if (produceDetail.getProductionOrder().getGoStatus() == 0) {
                                        tv_next.setVisibility(View.VISIBLE);
                                        tv_next.setText("成品入库");

                                    } else {
                                        tv_next.setVisibility(View.GONE);
                                    }
                                } else {
                                    tv_next.setVisibility(View.GONE);
                                }
                            }

                        }
                        if(isShow)
                            produceDetail.getFlowList().get(len).setShow(true);
                        produceFlowListAdapter.setNewData(produceDetail.getFlowList());
                        flowListBeans.addAll(produceDetail.getFlowList());
                        isShow=false;
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

    @OnClick({R.id.tv_next, R.id.iv_back, R.id.ll_detail})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_detail:
                json = new Gson().toJson(produceDetail.getOrderSampleVo());
                intent = new Intent(ProduceDetailActivity.this, ModelDataDetailActivity.class);
                intent.putExtra("id", produceDetail.getOrderSampleVo().getSampleId());
                intent.putExtra("json", json);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_next:
                if (produceDetail.getProductionOrder().getGoStatus() == 0 && produceDetail.getProductionOrder().getStatus() == 2) {
                    intent = new Intent(ProduceDetailActivity.this, FinishEntryActivity.class);
                    intent.putExtra("id", id);
                    startActivityForResult(intent, 11);
                } else {
                    intent = new Intent(ProduceDetailActivity.this, NextFlowActivity.class);
                    int len=produceDetail.getFlowList().size()-1;
                    ProduceDetail.FlowListBean flowListBean=produceDetail.getFlowList().get(len);
                    if(flowListBean.getLossQuantity()==null){
                        intent.putExtra("num", produceDetail.getProductionOrder().getQuantity());
                    }else{
                        intent.putExtra("num",-1);
                    }

                    intent.putExtra("id", produceDetail.getProductionOrder().getId());
                    intent.putExtra("stageId", produceDetail.getNextFlowStageId());
                    startActivityForResult(intent, 10);
                }
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }
    private void initView() {

        sizeQuantityListBeans=new ArrayList<>();
        inventorySizeQuantityListBeans=new ArrayList<>();
        flowListBeans = new ArrayList<>();
        produceFlowListAdapter = new ProduceFlowListAdapter(flowListBeans);
        produceFlowListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                Intent intent;
                ProduceDetail.FlowListBean item= (ProduceDetail.FlowListBean) adapter.getItem(position);
                switch (view.getId()){
                    case R.id.tv_do1:
                        if (position == 1) {
                            intent = new Intent(ProduceDetailActivity.this, SeeDataActivity.class);
                            intent.putExtra("id", item.getId());
                            intent.putExtra("total", total);
                            startActivity(intent);
                        } else {
                            if (item.getOutsourcingStatus() == -1) {
                                intent = new Intent(ProduceDetailActivity.this, WorkAssignActivity.class);
                                intent.putExtra("id", item.getId());
                                intent.putExtra("status", item.getWorkAllcationStatus());
                                intent.putExtra("num", item.getQuantity());
                                intent.putExtra("orderId", item.getProductionOrderId());
                                startActivityForResult(intent, 3);
                                //工作分配
                            } else if (item.getOutsourcingStatus() == 0) {

                                intent = new Intent(ProduceDetailActivity.this, CreateGetOrderActivity.class);
                                intent.putExtra("id", item.getOutsourcingPickupNoteId());
                                if (item.getStageId() == 3) {
                                    intent.putExtra("type", 2);
                                } else {
                                    intent.putExtra("type", 3);
                                }
                                intent.putExtra("orderId", item.getProductionOrderId());
                                startActivityForResult(intent, 1);
                            } else if (item.getOutsourcingStatus() >= 1) {
                                intent = new Intent(ProduceDetailActivity.this, SeeReceiverOrderActivity.class);
                                intent.putExtra("id", item.getOutsourcingPickupNoteId());
                                startActivity(intent);
                            }
                        }
                        break;
                    case R.id.tv_do2:
                        if (item.getOutsourcingStatus() == 1) {
                            intent = new Intent(ProduceDetailActivity.this, CreateSendOrderActivity.class);
                            intent.putExtra("id", item.getOutsourcingDeliveryNoteId());
                            intent.putExtra("orderId", item.getProductionOrderId());
                            startActivityForResult(intent, 2);
                            //创建
                        } else if (item.getOutsourcingStatus() == 2) {
                            //查看
                            intent = new Intent(ProduceDetailActivity.this, SeeSendOrderActivity.class);
                            intent.putExtra("id", item.getOutsourcingDeliveryNoteId());
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
        rv_flow.setLayoutManager(new LinearLayoutManager(this));
        rv_flow.setAdapter(produceFlowListAdapter);
        produceUseAdapter = new ProduceUseAdapter(this, materialTakeListBeans, 1);
        produceUseAdapter.setOnItemUserClickListener(new ProduceUseAdapter.OnItemUserClickListener() {
            @Override
            public void OnClick(int pos, TextView view) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                int id = produceDetail.getMaterialTakeList().get(pos).getId();
                use(id, pos);
            }
        });
        lv_use.setAdapter(produceUseAdapter);
        produceWantSizeAdapter=new ProduceWantSizeAdapter(sizeQuantityListBeans);
        produceRealSizeAdapter=new ProduceRealSizeAdapter(inventorySizeQuantityListBeans);
        rv_want.setLayoutManager(new LinearLayoutManager(this));
        rv_want.setAdapter(produceWantSizeAdapter);
        rv_size.setLayoutManager(new LinearLayoutManager(this));
        rv_size.setAdapter(produceRealSizeAdapter);

        getDetail();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        flowListBeans.clear();
        materialTakeListBeans.clear();
        getDetail();
    }
    String total;
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        Log.e("test", id + "");
        NetUtils.getInstance().get(MyApp.token, Api.Production.getProductionOrder, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        produceDetail = new Gson().fromJson(data.toString(), ProduceDetail.class);
                        initData();
                    } else {
                        String msg=jsonObject.getString("message");
                        ToastUtils.showShort(msg);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    kProgressHUD.dismiss();
                }
                kProgressHUD.dismiss();

            }

            @Override
            public void onError(String error) {
                LogUtils.e(error);
                kProgressHUD.dismiss();
            }
        });
    }

    private void initData() {
        int length = (produceDetail.getFlowList().size() - 1);
        tv_status.setText(produceDetail.getFlowList().get(length).getTitle());
        tv_time.setText(produceDetail.getProductionOrder().getMonth());
        tv_sampleno.setText(produceDetail.getOrderSampleVo().getSampleNo());
        tv_locate.setText(produceDetail.getProductionOrder().getProductOrderNumber());
        tv_num.setText(produceDetail.getOrderSampleVo().getProductCategory1Name() + " " + produceDetail.getOrderSampleVo().getProductCategory2Name());
        tv_designer.setText("设计师：" + produceDetail.getOrderSampleVo().getDesignerName());
        tv_create.setText("创建日期：" + produceDetail.getOrderSampleVo().getGmtCreate());
        if(!TextUtils.isEmpty(produceDetail.getProductionOrder().getEstimatedTimeOfFinishment()))
           tv_finish.setText(produceDetail.getProductionOrder().getEstimatedTimeOfFinishment().substring(0,10));
        tv_no.setText(produceDetail.getProductionOrder().getMonth() + " " + produceDetail.getProductionOrder().getProductOrderNumber());
        tv_by.setText(produceDetail.getCreateByName());
        String imgs = produceDetail.getOrderSampleVo().getImagesUrl();
        if (!TextUtils.isEmpty(imgs)) {
            if (imgs.contains(",")) {
                Glide.with(this).load(imgs.split(",")[0]).into(iv_img);

            } else {
                Glide.with(this).load(imgs).into(iv_img);
            }
        }
        iv_img.setVisibility(View.VISIBLE);
        int real=(produceDetail.getProductionOrder().getQuantity()-produceDetail.getProductionOrder().getLossQuantity());
        tv_want.setText("总数量: "+produceDetail.getProductionOrder().getQuantity()+"");
        tv_real.setText("总数量: "+real);
        produceWantSizeAdapter.setNewData(produceDetail.getSizeQuantityList());
        produceRealSizeAdapter.setNewData(produceDetail.getInventorySizeQuantityList());
        materialTakeListBeans.addAll(produceDetail.getMaterialTakeList());
        produceUseAdapter.setState(produceDetail.getProductionOrder().getStatus());
        produceUseAdapter.notifyDataSetChanged();
        ll_content.setVisibility(View.VISIBLE);
        setTotal();
        getAuth();
    }
    //设置用料统计用到的数据
    private  void setTotal(){
        String value="";
        for (int i = 0; i < produceDetail.getSizeQuantityList().size(); i++) {
            String name = produceDetail.getSizeQuantityList().get(i).getSizeName() + ":" + produceDetail.getSizeQuantityList().get(i).getQuantity();
            value = name + " " + value;
        }
        total = produceDetail.getProductionOrder().getQuantity() + "," + value;
    }
    private void use(int productionOrderMaterialTakeId, int pos) {
        Map<String, Object> params = new HashMap<>();
        params.put("productionOrderMaterialTakeId", productionOrderMaterialTakeId);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Production.materialTake, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("领用成功");
                        materialTakeListBeans.get(pos).setIsTake(1);
                        produceUseAdapter.notifyDataSetChanged();
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
        LogUtils.e(json);
    }

}
