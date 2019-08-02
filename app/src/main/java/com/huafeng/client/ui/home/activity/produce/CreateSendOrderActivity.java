package com.huafeng.client.ui.home.activity.produce;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.view.CursorEditText;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateSendOrderActivity extends BaseActivity implements OnDateSetListener {

    @BindView(R.id.et_no)
    CursorEditText et_no;
    @BindView(R.id.et_lost)
    CursorEditText et_lost;
    @BindView(R.id.et_price)
    CursorEditText et_price;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_process)
    TextView tv_process;
    @BindView(R.id.tv_supplier)
    TextView tv_supplier;

    int id;
    int type;
    int orderId;
    DeliveryNote pickup;
    String executeTime;
    TimePickerDialog mDialogYearMonthDay;
    SimpleDateFormat sf_time = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_send_order);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getIntExtra("id", 0);
        orderId = getIntent().getIntExtra("orderId", 0);
        initView();
    }

    private boolean checkInput() {
        boolean isOk = true;
        if (TextUtils.isEmpty(et_no.getEditableText().toString())) {
            isOk = false;
            ToastUtils.showShort("请输入送货单");
            return isOk;
        }
        if (TextUtils.isEmpty(et_price.getEditableText().toString())) {
            isOk = false;
            ToastUtils.showShort("请输入价格");
            return isOk;
        }
        if (TextUtils.isEmpty(et_lost.getEditableText().toString())) {
            isOk = false;
            ToastUtils.showShort("请输入损耗数量");
            return isOk;
        }
        if (TextUtils.isEmpty(executeTime)) {
            isOk = false;
            ToastUtils.showShort("请选择送货时间！");
            return isOk;
        }

        return isOk;
    }

    private void submit() {
        Map<String, Object> params = new HashMap<>();
        params.put("executeTime", executeTime);
        params.put("noteNo", et_no.getEditableText().toString());
        params.put("id", pickup.getId());
        params.put("productionOrderId", orderId);
        params.put("price", Double.parseDouble(et_price.getEditableText().toString()));
        params.put("lossQuantity", Integer.parseInt(et_lost.getEditableText().toString()));
        params.put("remarks", et_remark.getEditableText().toString());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Production.setDeliveryNote, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("设置成功");
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
    }

    private void initView() {
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("送货时间")
                .setMinMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.color_orange))
                .setCallBack(this)
                .build();
        getDely();
    }

    private void getDely() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Production.getDeliveryNote, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        pickup = new Gson().fromJson(data.toString(), DeliveryNote.class);
                        tv_num.setText(pickup.getQuantity() + "");
                        String value = "";
                        for (int i = 0; i < pickup.getProcessNameList().size(); i++) {
                            value = value + " " + pickup.getProcessNameList().get(i);
                        }
                        if (pickup.getLossQuantity() != null) {
                            et_lost.setText(pickup.getLossQuantity() + "");
                            et_lost.setFocusable(false);
                            et_lost.setFocusableInTouchMode(false);
                        }
                        tv_process.setText(value);
                        tv_supplier.setText(pickup.getSupplierName());
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


    @OnClick({R.id.tv_add, R.id.iv_back, R.id.rl_time})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.rl_time:
                mDialogYearMonthDay.show(getSupportFragmentManager(), "b");
                break;
            case R.id.tv_add:
                if (checkInput()) {
                    submit();
                }
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }

    public String getTime(long time) {
        Date d = new Date(time);
        return sf_time.format(d);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        executeTime = getTime(millseconds);
        tv_time.setText(executeTime);
    }
}
