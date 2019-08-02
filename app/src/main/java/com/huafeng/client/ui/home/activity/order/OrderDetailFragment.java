package com.huafeng.client.ui.home.activity.order;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.ModelDataDetailActivity;
import com.huafeng.client.ui.home.model.OrderCreate;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailFragment extends Fragment {

    OrderDetail orderDetail;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.iv_clothes)
    ImageView iv_clothes;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_addr1)
    TextView tv_addr1;
    @BindView(R.id.tv_addr2)
    TextView tv_addr2;
    @BindView(R.id.tv_sampleno)
    TextView tv_sampleno;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_designer)
    TextView tv_designer;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_no)
    TextView tv_no;
    @BindView(R.id.tv_stop)
    TextView tv_stop;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.tv_finish)
    TextView tv_finish;
    @BindView(R.id.tv_time2)
    TextView tv_time2;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.tv_num1)
    TextView tv_num1;
    @BindView(R.id.tv_num2)
    TextView tv_num2;
    @BindView(R.id.tv_num3)
    TextView tv_num3;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;
    @BindView(R.id.tv_by)
    TextView tv_by;
    int id;
    OrderDetailActivity activity;

    public OrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        ButterKnife.bind(this, view);
        activity = (OrderDetailActivity) getActivity();
        id = activity.getId();
        initView();
        return view;
    }

    private void initView() {

        getDetail();
    }
    private void showConfirmDialog(int type) {
        final Dialog dialog = new Dialog(getContext(),R.style.dialog);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_stop, null);

        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_confirm);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                switch (type){
                    case 1:
                        stopOrder();
                        break;
                    case 2:
                        finishOrder();
                        break;
                    case 3:
                        cancelOrder();
                        break;
                }

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = (int) (display.getWidth() * 0.8);
        window.setAttributes(layoutParams);

    }
    @OnClick({R.id.tv_cancel, R.id.tv_finish, R.id.ll_detail, R.id.tv_stop,R.id.tv_confirm})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.tv_confirm:
                Intent intent1 = new Intent(getContext(), ConfirmOrderActivity.class);
                intent1.putExtra("id", orderDetail.getOrders().getSampleId());
                OrderCreate orderCreate=new OrderCreate();
                orderCreate.setOrderId(orderDetail.getOrders().getId());
                orderCreate.setQuantity(orderDetail.getOrders().getQuantity());
                intent1.putExtra("data", orderCreate);
                startActivityForResult(intent1, 2);
                break;
            case R.id.tv_stop:
                showConfirmDialog(1);
                break;
            case R.id.tv_cancel:
                showConfirmDialog(3);
                break;
            case R.id.tv_finish:
                showConfirmDialog(2);
                break;
            case R.id.ll_detail:
                Intent intent = new Intent(getContext(), ModelDataDetailActivity.class);
                intent.putExtra("id", orderDetail.getOrderSampleVo().getSampleId());
                intent.putExtra("json",new Gson().toJson(orderDetail.getOrderSampleVo()));
                startActivityForResult(intent, 1);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         if(requestCode==2&&resultCode==RESULT_OK){
             getActivity().finish();
         }
    }

    private void getButton() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Order.getButtonOptions, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        int finish = data.getInt("finish");
                        int stop = data.getInt("stop");
                        int cancel = data.getInt("cancel");
                        if (cancel == 1) {
                            tv_cancel.setVisibility(View.VISIBLE);
                        } else {
                            if(finish==1){
                                tv_finish.setVisibility(View.VISIBLE);
                            }
                            if(stop==1){
                                tv_stop.setVisibility(View.VISIBLE);
                            }

                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                LogUtils.e(error);
            }
        });
    }
    private void getAuth() {
       // LitePal.deleteAll(Auth.class);
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
                            if(authId==1021||authId==1022){
                                getButton();
                                if(orderDetail.getOrders().getStatus()==1)
                                    tv_confirm.setVisibility(View.VISIBLE);
                            }

                        }

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
    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Order.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        orderDetail = new Gson().fromJson(data.toString(), OrderDetail.class);
                        initData();
                        getAuth();
                    }else{
                        ToastUtils.showShort("你没有该权限！");
                        getActivity().finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                LogUtils.e(error);
            }
        });
    }

    private void initData() {
        if (orderDetail.getClientRecord() != null) {
            tv_name.setText(orderDetail.getClientRecord().getName());
            tv_addr1.setText(orderDetail.getClientRecord().getProvince() + " " + orderDetail.getClientRecord().getCity());
            tv_addr2.setText(orderDetail.getClientRecord().getReceivingAddress());
            tv_phone.setText(orderDetail.getClientRecord().getContactNumber());
            String url = orderDetail.getClientRecord().getImageUrl();
            if (!TextUtils.isEmpty(url)) {
                if (url.contains(",")) {
                    url = url.split(",")[0];
                    Glide.with(this).load(url).into(iv_head);
                } else {
                    Glide.with(this).load(url).into(iv_head);
                }
            }

        }

        String imgs = orderDetail.getOrderSampleVo().getImagesUrl();
        if (!TextUtils.isEmpty(imgs)) {
            if (imgs.contains(",")) {
                Glide.with(this).load(imgs.split(",")[0]).into(iv_clothes);
            } else {
                Glide.with(this).load(imgs).into(iv_clothes);
            }
        }
        tv_sampleno.setText(orderDetail.getOrderSampleVo().getSampleNo());
        tv_style.setText("款式：" + orderDetail.getOrderSampleVo().getProductCategory1Name() + " " + orderDetail.getOrderSampleVo().getProductCategory2Name());
        tv_designer.setText("设计师：" + orderDetail.getOrderSampleVo().getDesignerName());
        tv_time.setText("创建日期：" + orderDetail.getOrderSampleVo().getGmtCreate());
        tv_number.setText("" + orderDetail.getOrders().getQuantity());
        tv_remark.setText(orderDetail.getOrders().getRemarks());
        tv_no.setText(orderDetail.getOrders().getOrderNumber());
        tv_by.setText(orderDetail.getCreateByName() + "");
        tv_time2.setText(orderDetail.getOrders().getGmtCreate());
        if (orderDetail.getOrders().getStatus() == 1) {
            tv_status.setText("待确认");
        } else if (orderDetail.getOrders().getStatus() == 2) {
            tv_status.setText("进行中");
        } else if (orderDetail.getOrders().getStatus() == 3) {
            tv_status.setText("已完成");
        } else {
            tv_status.setText("已终止");
        }
        tv_num3.setText(orderDetail.getQuantityInventory() + "");
        tv_num2.setText(orderDetail.getQuantityProducted() + "");
        tv_num1.setText(orderDetail.getOrders().getQuantity() + "");
    }

    private void stopOrder() {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderDetail.getOrders().getId());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Order.stop, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("成功终止");
                        getActivity().finish();
                    } else {
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

    private void cancelOrder() {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderDetail.getOrders().getId());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Order.cancel, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("取消成功");
                        getActivity().finish();
                    } else {
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

    private void finishOrder() {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderDetail.getOrders().getId());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Order.finish, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("已完成");
                        getActivity().finish();
                    } else {
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

}
