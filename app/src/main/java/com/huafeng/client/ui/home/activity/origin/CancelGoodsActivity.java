package com.huafeng.client.ui.home.activity.origin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.client.ClientDetail;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.MathUtils;
import com.huafeng.client.view.selectimage.EvaluationChoiceImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.HttpParams;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CancelGoodsActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.et_lost)
    CursorEditText et_lost;
    @BindView(R.id.et_price)
    CursorEditText et_price;
    @BindView(R.id.iv_single)
    ImageView iv_single;
    @BindView(R.id.iv_total)
    ImageView iv_total;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.tv_client)
    TextView tv_client;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.addimgs)
    EvaluationChoiceImageView addimgs;
    OriginFinishDetail originFinishDetail;
    ClientDetail clientDetail;
    @BindView(R.id.iv_goods)
    ImageView iv_goods;
    List<String> phoneList=new ArrayList<>();
    List<String> addressList=new ArrayList<>();
    ChecDataAdapter checDataAdapter;
    List<ChecData> checDataList;
    @BindView(R.id.rv_check)
    RecyclerView rv_check;
    BackgoodsSubmit backgoodsSubmit=new BackgoodsSubmit();
    int id;
    int pay=1;
    double amount;
    List<String> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_goods);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        initView();
    }
    private void initSizeInfo(){

        checDataList=new ArrayList<>();
        checDataAdapter=new ChecDataAdapter(checDataList);
        rv_check.setLayoutManager(new LinearLayoutManager(this));
        rv_check.setAdapter(checDataAdapter);
        checDataAdapter.setOnEditListener(new ChecDataAdapter.OnEditListener() {
            @Override
            public void onEdit(String value) {

            }
        });
        getSizeList();
    }
    private void getSizeList() {
        HttpParams httpParams=new HttpParams();
        httpParams.put("sampleId",id);
        NetUtils.getInstance().get(MyApp.token, Api.Inventory.getSizeList,httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            ChecData checData=new Gson().fromJson(item.toString(),ChecData.class);
                            checDataList.add(checData);
                        }
                        checDataAdapter.setNewData(checDataList);
                    }else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
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
    private void getClientDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", originFinishDetail.getSampleVo().getClientRecordId());
        NetUtils.getInstance().get(MyApp.token, Api.Client.getDetail, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        clientDetail = new Gson().fromJson(data.toString(), ClientDetail.class);
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
                        getClientDetail();
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
    private void initPhoto(){
        images=new ArrayList<>();
        addimgs.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
            @Override
            public void onClickAddImage() {
                openPhoto(5);
            }
        });
        addimgs.setOnClickDeleteImageListener(new EvaluationChoiceImageView.OnClickDeleteImageListener() {
            @Override
            public void onClickDeleteImage(int position) {
                images.remove(position);
            }
        });
        addimgs.setOnClickImageListener(new EvaluationChoiceImageView.OnClickImageListener() {
            @Override
            public void onClickImage(int position) {

            }
        });
    }
    private void openPhoto(int code) {
        PictureSelector.create(CancelGoodsActivity.this)
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
    private void initData() {
        tv_name.setText(originFinishDetail.getSampleVo().getSampleNo());
        tv_num.setText(originFinishDetail.getInventorySample().getQuantity()+"");
        tv_style.setText(originFinishDetail.getSampleVo().getProductCategory1Name() + originFinishDetail.getSampleVo().getProductCategory2Name());
        tv_client.setText(clientDetail.getClientRecord().getName());
        if(!TextUtils.isEmpty(clientDetail.getClientRecord().getContactNumber())){
            if(clientDetail.getClientRecord().getContactNumber().contains(",")){
                String [] phoneArray=clientDetail.getClientRecord().getContactNumber().split(",");
                for (int i = 0; i <phoneArray.length ; i++) {
                    phoneList.add(phoneArray[i]);
                }
            }else{
                phoneList.add(clientDetail.getClientRecord().getContactNumber());
            }
            tv_phone.setText(phoneList.get(0));
        }
        if(!TextUtils.isEmpty(originFinishDetail.getSampleVo().getImagesUrl())){
            Glide.with(this).load(originFinishDetail.getSampleVo().getImagesUrl()).into(iv_goods);
            iv_goods.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(clientDetail.getClientRecord().getReceivingAddress())){
            if(clientDetail.getClientRecord().getReceivingAddress().contains(",")){
                String [] addrArray=clientDetail.getClientRecord().getReceivingAddress().split(",");
                for (int i = 0; i <addrArray.length ; i++) {
                    addressList.add(addrArray[i]);
                }
            }else{
                addressList.add(clientDetail.getClientRecord().getReceivingAddress());
            }
            tv_address.setText(addressList.get(0));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {

                case 5:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    File file = new File(path);
                    upLoadImg(file, path);
                    break;
            }
        }
    }

    private void upLoadImg(File file, String path) {
        NetUtils.getInstance().upLoadImage(Api.Image.uploadImage, file, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.getString("data");
                    images.add(data);
                    addimgs.addImage(path);
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
        initSizeInfo();
        iv_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoUtil.seeSinglePhoto(CancelGoodsActivity.this,originFinishDetail.getSampleVo().getImagesUrl(),iv_goods);
            }
        });
        et_lost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 if(!TextUtils.isEmpty(s.toString())){
                     String price=et_price.getEditableText().toString();
                     if(!TextUtils.isEmpty(price)){
                         if(MathUtils.isNumber(price)){
                             amount=Double.valueOf(price)*Integer.valueOf(s.toString());
                             tv_total.setText("总价：￥"+amount);
                         }
                     }
                 }else{
                     tv_total.setText("总价：￥"+"-");
                 }
            }
        });
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString())){
                    String num=et_lost.getEditableText().toString();
                    if(!TextUtils.isEmpty(num)){
                        if(MathUtils.isInteger(num)){
                            amount=Integer.valueOf(num)*Double.valueOf(s.toString());
                            tv_total.setText("总价：￥"+amount);
                        }
                    }
                } else{
                    tv_total.setText("总价：￥"+"-");
                }
            }
        });
        initPhoto();
        getDetail();
    }
    private void submit(){
        backgoodsSubmit.setSampleId(id);
        backgoodsSubmit.setAmount(amount);
        backgoodsSubmit.setContactNumber(tv_phone.getText().toString());
        backgoodsSubmit.setAddress(tv_address.getText().toString());
        backgoodsSubmit.setRemarks(et_remark.getEditableText().toString());
        backgoodsSubmit.setName(clientDetail.getClientRecord().getName());
        backgoodsSubmit.setPrice(Double.valueOf(et_price.getEditableText().toString()));
        backgoodsSubmit.setPriceType(pay);
        backgoodsSubmit.setImages( StringUtils.listToString2(images,','));
        backgoodsSubmit.setReturnQuantity( Integer.valueOf(et_lost.getEditableText().toString()));
        List<BackgoodsSubmit.SizeListBean> sizeListBeans=new ArrayList<>();
        for (int i = 0; i <checDataAdapter.getData().size() ; i++) {
            BackgoodsSubmit.SizeListBean sizeListBean=new BackgoodsSubmit.SizeListBean();
            sizeListBean.setQuantity(checDataAdapter.getData().get(i).getQuantity());
            sizeListBean.setSizeId(checDataAdapter.getData().get(i).getId());
            sizeListBeans.add(sizeListBean);
        }
        backgoodsSubmit.setSizeList(sizeListBeans);
        String json = new Gson().toJson(backgoodsSubmit);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Inventory.addReturnBill, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
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
    @OnClick({R.id.iv_back, R.id.tv_submit,R.id.ll_single,R.id.ll_total,R.id.rl_phone,R.id.rl_address})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.rl_phone:
                showPhoneDialog();
                break;
            case R.id.rl_address:
                showAddrDialog();
                break;
            case R.id.ll_total:
                pay=2;
                iv_single.setImageResource(R.mipmap.ic_uncheck);
                iv_total.setImageResource(R.mipmap.ic_check);
                String price=et_price.getEditableText().toString();
                if(!TextUtils.isEmpty(price)&&MathUtils.isNumber(price)){
                    tv_total.setText("总价：￥"+price);
                    amount=Double.valueOf(price);
                }else{
                    tv_total.setText("总价：￥-");
                }
                break;
            case R.id.ll_single:
                pay=1;
                iv_single.setImageResource(R.mipmap.ic_check);
                iv_total.setImageResource(R.mipmap.ic_uncheck);
                tv_total.setText("总价：￥"+amount);
                String single=et_price.getEditableText().toString();
                String num=et_lost.getEditableText().toString();
                if(!TextUtils.isEmpty(single)&&!TextUtils.isEmpty(num)&&MathUtils.isNumber(single)&&MathUtils.isNumber(num)){
                    amount=Double.valueOf(single)*Integer.valueOf(num);
                    tv_total.setText("总价：￥"+amount);
                }else{
                    tv_total.setText("总价：￥-");
                }
                break;
            case R.id.tv_submit:
                if(checkInput()){
                    submit();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    private void showPhoneDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_type, null);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        LoopView loopView = (LoopView) view.findViewById(R.id.loopView);
        tv_title.setText("选择电话");
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
                tv_phone.setText(phoneList.get(loopView.getSelectedItem()));

            }
        });

        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        // 设置原始数据
        loopView.setItems(phoneList);
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
    private void showAddrDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_type, null);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        LoopView loopView = (LoopView) view.findViewById(R.id.loopView);
        tv_title.setText("选择地址");
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
                tv_address.setText(addressList.get(loopView.getSelectedItem()));

            }
        });

        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        // 设置原始数据
        loopView.setItems(addressList);
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
    private boolean checkInput() {
        boolean isOk = true;
        if (TextUtils.isEmpty(et_lost.getEditableText().toString())) {
            isOk = false;
            ToastUtils.showShort("请输入退货数量");
            return isOk;
        }
        if (TextUtils.isEmpty(et_price.getEditableText().toString())) {
            isOk = false;
            ToastUtils.showShort("请输入价格");
            return isOk;
        }

        return isOk;
    }
}
