package com.huafeng.client.ui.start.activity;

import android.content.Intent;
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
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.select.SelectFactoryActivity;
import com.huafeng.client.ui.home.model.Factory;
import com.huafeng.client.view.CursorEditText;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyFactoryActivity extends BaseActivity {
    //申明对象
    CityPickerView mPicker = new CityPickerView();
    Factory mFactory;
    @BindView(R.id.tv_factory)
    TextView tv_factory;
    @BindView(R.id.tv_address)
    TextView tv_address;

    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.et_name)
    CursorEditText et_name;
    @BindView(R.id.et_phone)
    CursorEditText et_phone;
    @BindView(R.id.et_mark)
    EditText et_mark;
    String city;
    String province;
    int sex=0;
    String remarks;
    String name;
    String contactNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_factory);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initCity();
    }

    private boolean checkInput() {
        boolean isOk = true;
        remarks = et_mark.getEditableText().toString();
        name=et_name.getEditableText().toString();
        contactNumber=et_phone.getEditableText().toString();
        if (TextUtils.isEmpty(name)) {
            isOk = false;
            ToastUtils.showShort("请输入姓名");
        }
        if (TextUtils.isEmpty(contactNumber)) {
            isOk = false;
            ToastUtils.showShort("请输入联系人电话");
        }
        if (sex<=0) {
            isOk = false;
            ToastUtils.showShort("请选择性别");
        }
        if (mFactory == null) {
            isOk = false;
            ToastUtils.showShort("请选择工厂");
        }
        if (TextUtils.isEmpty(province)) {
            isOk = false;
            ToastUtils.showShort("请选择所在地");
        }

        return isOk;
    }
    private void submit() {
        Map<String, Object> params = new HashMap<>();
        params.put("city", city);
        params.put("contactNumber", contactNumber);
        params.put("factoryId", mFactory.getId());
        params.put("name",name);
        params.put("province",province);
        params.put("remarks", remarks);
        params.put("sex", sex);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Employ.applyJoinFactory, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        LogUtils.e(response);
                        ToastUtils.showShort("提交成功,请耐心等候,审核通过才能进入app");
                        finish();
                    }else{
                        String msg=jsonObject.getString("message");
                        ToastUtils.showLong(msg);
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
    private void initCity() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择所在地")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#E9E9E9")//标题栏背景色
                .confirTextColor("#585858")//确认按钮文字颜色
                .confirmText("确认")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#585858")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(3)//显示item的数量
                .province("广东省")//默认显示的省份
                .city("广州市")//默认显示省份下面的城市
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .drawShadows(true)//滚轮不显示模糊效果
                .setLineColor("#f3f3f3")//中间横线的颜色
                .setLineHeigh(2)//中间横线的高度
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();
        mPicker.init(this);
        mPicker.setConfig(cityConfig);
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean provinceBean, CityBean cityBean, DistrictBean district) {
                tv_address.setText(provinceBean.getName()+ " "+ cityBean.getName());
                city=cityBean.getName();
                province=provinceBean.getName();
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.rl_city, R.id.rl_factory, R.id.rl_sex,R.id.tv_add})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                if(checkInput()){
                    submit();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_city:
                mPicker.showCityPicker();
                break;
            case R.id.rl_factory:
                startActivityForResult(new Intent(ApplyFactoryActivity.this, SelectFactoryActivity.class),1);
                break;
            case R.id.rl_sex:
                showSexDialog();
                break;
        }
    }
    private void showSexDialog(){
        new XPopup.Builder(this)
                .asBottomList("请选择性别", new String[]{"男", "女"},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                tv_sex.setText(text);
                                if(position==0){
                                    sex=1;
                                }else{
                                    sex=2;
                                }
                            }
                        })
                .show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            mFactory= (Factory) data.getSerializableExtra("data");
            tv_factory.setText(mFactory.getName());
        }
    }
}
