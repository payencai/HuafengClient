package com.huafeng.client.ui.home.activity.buy;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.view.MyListView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyOrderDetailActivity extends BaseActivity {
    @BindView(R.id.tv_reqest)
    TextView tv_reqest;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.tv_produce)
    TextView tv_produce;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_pay)
    TextView tv_pay;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.lv_ask)
    MyListView lv_ask;
    int id;
    BuyOrderDetail askDetail;
    BuyTableAdapter buyTableAdapter;
    List<BuyOrderDetail.NoteListBean.SupplierListBean> supplierListBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_order_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
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
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        supplierListBeans = new ArrayList<>();
        buyTableAdapter=new BuyTableAdapter(this,supplierListBeans);
        lv_ask.setAdapter(buyTableAdapter);
        getDetail();
    }

    private void initData() {
        tv_reqest.setText(askDetail.getRequisitionNumber());
        tv_name.setText(askDetail.getRequisitionCreateByName());
        tv_number.setText(askDetail.getSampleNo());
        tv_order.setText(askDetail.getOrderNumber());
        tv_produce.setText(askDetail.getProductionOrderNumber());
        tv_time.setText(askDetail.getGmtCreate());
        tv_remark.setText(askDetail.getRemarks());
        if (askDetail.getPayMethod() == 1) {
            tv_pay.setText("采购人垫付");
        } else {
            tv_pay.setText("公司支付");
        }
        for (int i = 0; i <askDetail.getNoteList().size() ; i++) {
            BuyOrderDetail.NoteListBean noteListBean=askDetail.getNoteList().get(i);

            for (int j = 0; j <noteListBean.getSupplierList().size() ; j++) {
                BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean=noteListBean.getSupplierList().get(j);

                if(j==0){
                    supplierListBean.setClothQuantity(noteListBean.getClothQuantity());
                    supplierListBean.setMaterialName(noteListBean.getMaterialName());
                    supplierListBean.setNeedQuantity(noteListBean.getNeedQuantity());
                }
                supplierListBeans.add(supplierListBean);
            }
        }
        buyTableAdapter.notifyDataSetChanged();
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("groupId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Purchase.getBuyDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        askDetail = new Gson().fromJson(data.toString(), BuyOrderDetail.class);
                        initData();
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
