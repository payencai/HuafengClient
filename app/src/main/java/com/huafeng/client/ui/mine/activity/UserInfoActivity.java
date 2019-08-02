package com.huafeng.client.ui.mine.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.mine.model.UserMsg;
import com.huafeng.client.ui.start.activity.LoginActivity;
import com.huafeng.client.view.CircleImageView;
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
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class UserInfoActivity extends BaseActivity {
    String head;
    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    String nickname;
    @BindView(R.id.tv_nick)
    TextView tv_nick;
    @BindView(R.id.tv_factory)
    TextView tv_factory;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    CityPickerView mPicker = new CityPickerView();
    String province;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initCity();
        getDetail();
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
                tv_factory.setText(provinceBean.getName()+ " "+ cityBean.getName());
                city=cityBean.getName();
                province=provinceBean.getName();
                updateUserInfo();
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        });
    }
    @OnClick({R.id.iv_head, R.id.rl_nick, R.id.back, R.id.tv_exit,R.id.rl_sex,R.id.rl_city})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_city:
                mPicker.showCityPicker();
                break;
            case R.id.rl_sex:
                showSexDialog();
                break;
            case R.id.tv_exit:
                SPUtils.getInstance().put("phone", "");
                SPUtils.getInstance().put("pwd", "");
                SPUtils.getInstance().put("token", "");
                SPUtils.getInstance().put("user", "");
                MyApp.isLogin=false;
                MyApp.token="";
                MyApp.setUserInfo(null);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.iv_head:
                openPhoto(188);
                break;
            case R.id.rl_nick:
                showUpdateNickDialog();
                break;
        }
    }

    private void showUpdateNickDialog() {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_updatenick, null);
        final EditText et_season = (EditText) view.findViewById(R.id.et_season);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                nickname = et_season.getEditableText().toString();
                if (TextUtils.isEmpty(nickname)) {
                    ToastUtils.showShort("昵称不能为空！");
                    return;
                }
                tv_nick.setText(nickname);
                updateUserInfo();

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
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }
    int sex=0;
    private void showSexDialog() {
        ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
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
                if("男".equals(list.get(loopView.getSelectedItem()))){
                    sex =1;
                    tv_sex.setText("男");
                }else{
                    sex=2;
                    tv_sex.setText("女");
                }
                updateUserInfo();
            }
        });


        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

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
    private void getDetail() {

        NetUtils.getInstance().get(MyApp.token, Api.EmployeeUser.getInformation, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        UserMsg userMsg = new Gson().fromJson(data.toString(), UserMsg.class);
                        if (userMsg.getSex()==null) {
                            tv_sex.setText("未设置");
                        }else{
                            if (userMsg.getSex().intValue() == 1) {
                                tv_sex.setText("男");
                            } else {
                                tv_sex.setText("女");
                            }
                        }


                        if(!TextUtils.isEmpty(userMsg.getHeadPortraitUrl())){
                            Glide.with(UserInfoActivity.this)
                                    .load(userMsg.getHeadPortraitUrl())
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(iv_head);
                        }else{
                            Glide.with(UserInfoActivity.this)
                                    .load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png")
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(iv_head);

                        }
                        tv_nick.setText(userMsg.getNickname());
                        tv_factory.setText(userMsg.getProvince()+userMsg.getCity());
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


    private void openPhoto(int code) {
        PictureSelector.create(UserInfoActivity.this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageSpanCount(4)// 每行显示个数 int
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            String path = selectList.get(0).getPath();
            File file = new File(path);
            upLoadImg(file, path);
        }
    }

    private void upLoadImg(File file, String path) {
        NetUtils.getInstance().upLoadImage(Api.Image.uploadImage, file, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.getString("data");
                    head = data;
                    Glide.with(UserInfoActivity.this)
                            .load(path)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(iv_head);
                    updateUserInfo();
                    // Glide.with(UserInfoActivity.this).load(head).into(iv_head);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void updateUserInfo() {
        Map<String, Object> params = new HashMap<>();
        if (!TextUtils.isEmpty(head))
            params.put("headPortrait", head);
        if (!TextUtils.isEmpty(nickname))
            params.put("nickname", nickname);
        if (!TextUtils.isEmpty(province))
            params.put("province", province);
        if (!TextUtils.isEmpty(city))
            params.put("city", city);
        if(sex>0){
            params.put("sex",sex);
        }
        params.put("id", MyApp.getUserInfo().getId());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.EmployeeUser.edit, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                ToastUtils.showShort("修改成功");
                LogUtils.e(response);
                head = "";
                nickname = "";
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}

