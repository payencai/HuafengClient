package com.huafeng.client.ui.home.activity.client;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.view.CircleImageView;
import com.huafeng.client.view.CursorEditText;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditClientActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.et_email)
    CursorEditText et_email;
    @BindView(R.id.et_address)
    CursorEditText et_address;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.et_man)
    CursorEditText et_man;
    @BindView(R.id.et_name)
    CursorEditText et_name;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    String principal;
    String city;
    String province;
    String contactAddress;
    String contactNumber;
    String receivingAddress;
    String remarks;
    String name;
    String email;

    ClientDetail clientDetail;
    //申明对象
    CityPickerView mPicker = new CityPickerView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        clientDetail = (ClientDetail) getIntent().getSerializableExtra("data");
        initView();
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
                tv_address.setText(provinceBean.getName() + " " + cityBean.getName());
                city = cityBean.getName();
                province = provinceBean.getName();
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        });
    }

    private void initView() {

        initCity();
        initData();
    }

    private boolean checkInput() {
        boolean isOk = true;
        remarks = et_remark.getEditableText().toString();
        name = et_name.getEditableText().toString();
        contactNumber = tv_phone.getText().toString();
        email = et_email.getEditableText().toString();
        principal = et_man.getEditableText().toString();
        contactAddress = et_address.getEditableText().toString();
        receivingAddress = tv_address.getText().toString();
        return isOk;
    }


    private void initData() {
        name = clientDetail.getClientRecord().getName();
        remarks = clientDetail.getClientRecord().getRemarks();
        contactAddress = clientDetail.getClientRecord().getContactAddress();
        receivingAddress = clientDetail.getClientRecord().getReceivingAddress();
        contactNumber = clientDetail.getClientRecord().getContactNumber();
        principal = clientDetail.getClientRecord().getPrincipal();
        province = clientDetail.getClientRecord().getProvince();
        city = clientDetail.getClientRecord().getCity();

        if (!TextUtils.isEmpty(clientDetail.getClientRecord().getImageUrl()))
            Glide.with(this).load(clientDetail.getClientRecord().getImageUrl()).into(iv_head);
        et_name.setText(clientDetail.getClientRecord().getName());
        et_man.setText(clientDetail.getClientRecord().getPrincipal());
        et_remark.setText(clientDetail.getClientRecord().getRemarks());
        tv_phone.setText(clientDetail.getClientRecord().getContactNumber());
        tv_city.setText(province + city);
        et_address.setText(clientDetail.getClientRecord().getContactAddress());
        tv_address.setText(clientDetail.getClientRecord().getReceivingAddress());
    }

    private void submit() {
        Map<String, Object> params = new HashMap<>();
        params.put("city", city);
        params.put("id", clientDetail.getClientRecord().getId());
        params.put("contactNumber", contactNumber);
        params.put("receivingAddress", receivingAddress);
        params.put("name", name);
        if(!TextUtils.isEmpty(head))
           params.put("image",head);
        params.put("province", province);
        params.put("principal", principal);
        params.put("remarks", remarks);
        params.put("contactAddress", contactAddress);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Client.edit, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("resultCode");
                    if(code==0){
                        ToastUtils.showShort("提交成功");
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

    @OnClick({R.id.iv_head, R.id.rl_addr, R.id.iv_back, R.id.tv_add,R.id.rl_phone,R.id.rl_city})
    void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_phone:
                intent=new Intent(EditClientActivity.this,ClientManaPhoneActivity.class);
                intent.putExtra("id",clientDetail.getClientRecord().getId());
                intent.putExtra("phone",tv_phone.getText().toString());
                startActivityForResult(intent,2);
                break;
            case R.id.rl_city:
                mPicker.showCityPicker();
                break;
            case R.id.iv_head:
                openPhoto(1);
                break;
            case R.id.rl_addr:
                intent=new Intent(EditClientActivity.this,ClientManaAddressActivity.class);
                intent.putExtra("id",clientDetail.getClientRecord().getId());
                intent.putExtra("address",tv_address.getText().toString());
                startActivityForResult(intent,3);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                if (checkInput()) {
                    submit();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    File file = new File(path);
                    upLoadImg(file, path);
                    break;
                case 2:
                    contactNumber=data.getStringExtra("phone");
                    tv_phone.setText(contactNumber);
                    break;
                case 3:
                    receivingAddress=data.getStringExtra("address");
                    tv_address.setText(receivingAddress);
                    break;
            }
        }

    }

    private void openPhoto(int code) {
        PictureSelector.create(EditClientActivity.this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .withAspectRatio(16, 9)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .forResult(code);//结果回调onActivityResult code
    }
    String head;
    private void upLoadImg(File file, String path) {
        NetUtils.getInstance().upLoadImage(Api.Image.uploadImage, file, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    head = jsonObject.getString("data");
                    Glide.with(EditClientActivity.this).load(path).into(iv_head);

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
