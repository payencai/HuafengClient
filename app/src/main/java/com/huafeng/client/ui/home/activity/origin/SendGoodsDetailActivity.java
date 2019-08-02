package com.huafeng.client.ui.home.activity.origin;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.adapter.PhotoAdapter;
import com.huafeng.client.view.MyGridView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendGoodsDetailActivity extends BaseActivity {

    SendTicket backTicket;
    int id;
    int noteid;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_backnum)
    TextView tv_backnum;
    @BindView(R.id.tv_sampleno)
    TextView tv_sampleno;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.tv_carrier)
    TextView tv_carrier;
    @BindView(R.id.tv_no)
    TextView tv_no;
    @BindView(R.id.tv_cost)
    TextView tv_cost;
    @BindView(R.id.iv_goods)
    ImageView iv_goods;
    @BindView(R.id.gv_photo)
    MyGridView gv_photo;
    PhotoAdapter photoAdapter;
    OriginFinishDetail originFinishDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_goods_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        noteid = getIntent().getIntExtra("noteid", 0);
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
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, time);//3秒后执行TimeTask的run方法
    }
    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoUtil.seeSinglePhoto(SendGoodsDetailActivity.this,backTicket.getImagesUrl(),iv_goods);
            }
        });
        getDetail();
    }
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("sampleId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getFinishDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        originFinishDetail = new Gson().fromJson(data.toString(), OriginFinishDetail.class);
                        getSend();
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
    private void getSend() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("dispatchBillId", noteid);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getDispatchBill, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        backTicket = new Gson().fromJson(data.toString(), SendTicket.class);
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

    private void initData() {
        if(backTicket.getDispatchType()==1){
            tv_send.setText("物流发货");
        }else{
            tv_send.setText("客户自提");
        }
        tv_carrier.setText(backTicket.getCarrier());
        tv_no.setText(backTicket.getTrackingNumber());
        tv_cost.setText("￥"+backTicket.getFreightCharge());
        tv_address.setText(backTicket.getAddress());
        tv_backnum.setText(backTicket.getDispatchQuantity()+"");
        tv_name.setText(backTicket.getName());
        tv_phone.setText(backTicket.getContactNumber());
        tv_remark.setText(backTicket.getRemarks());
        tv_price.setText("￥"+backTicket.getPrice());
        tv_total.setText("￥"+backTicket.getAmount());
        if(backTicket.getPriceType()==1)
            tv_type.setText("按单价");
        else{
            tv_type.setText("按总价");
        }
        if(!TextUtils.isEmpty(originFinishDetail.getSampleVo().getImagesUrl())){
            Glide.with(this).load(originFinishDetail.getSampleVo().getImagesUrl()).into(iv_goods);
            iv_goods.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(backTicket.getImagesUrl())){
            photoAdapter=new PhotoAdapter(this, StringUtils.StringToArrayList(backTicket.getImagesUrl(),","));
            gv_photo.setAdapter(photoAdapter);
            gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PhotoUtil.seeBigPhoto(SendGoodsDetailActivity.this,position,StringUtils.StringToArrayList(backTicket.getImagesUrl(),","),gv_photo.getChildAt(position));
                }
            });
        }
        tv_num.setText(originFinishDetail.getInventorySample().getQuantity()+"");
        tv_sampleno.setText(originFinishDetail.getSampleVo().getSampleNo());
        tv_style.setText(originFinishDetail.getSampleVo().getProductCategory1Name() + originFinishDetail.getSampleVo().getProductCategory2Name());
    }
}
