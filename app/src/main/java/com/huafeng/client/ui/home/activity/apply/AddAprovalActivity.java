package com.huafeng.client.ui.home.activity.apply;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.select.SelectApproversActivity;
import com.huafeng.client.ui.home.model.Approvers;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.selectimage.EvaluationChoiceImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

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

public class AddAprovalActivity extends BaseActivity {
    @BindView(R.id.addimgs)
    EvaluationChoiceImageView mEvaluationChoiceImageView;
    List<String> images;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_app)
    TextView tv_name;
    @BindView(R.id.rl_money)
    RelativeLayout rl_money;
    @BindView(R.id.et_title)
    CursorEditText et_title;
    @BindView(R.id.et_money)
    CursorEditText et_money;
    int type=0;
    Approvers mApprovers;
    String content;
    String title;
    double amountOfMoney=0;
    ArrayList<String> pathList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aproval);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        ButterKnife.bind(this);
        initView();
    }

    private boolean checkInput() {
        boolean isOk = true;
        content = et_content.getEditableText().toString();
        title=et_title.getEditableText().toString();

        if (TextUtils.isEmpty(content)) {
            isOk = false;
            ToastUtils.showShort("请输入内容");
        }
        if (TextUtils.isEmpty(title)) {
            isOk = false;
            ToastUtils.showShort("请输入标题");
        }

        if (type<=0) {
            isOk = false;
            ToastUtils.showShort("请选择审批类型");
        }
        if (mApprovers == null) {
            isOk = false;
            ToastUtils.showShort("请选择审批人");
        }

        return isOk;
    }
    private void submit() {
        Map<String, Object> params = new HashMap<>();
        params.put("approveBy", mApprovers.getId());
        params.put("content", content);
        params.put("images", StringUtils.listToString2(images,','));
        params.put("title",title);
        params.put("type", type);
        if(type==2){
            amountOfMoney= Double.parseDouble(et_money.getEditableText().toString());
            params.put("amountOfMoney", amountOfMoney);
        }
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Approval.addApproval, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                ToastUtils.showShort("提交成功");
                finish();
            }

            @Override
            public void onError(String error) {

            }
        });
        LogUtils.e(json);
    }

    private void showTypeDialog(){
        new XPopup.Builder(this)
                .asBottomList("请选择审批类型", new String[]{"人事审批", "报销审批"},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                tv_type.setText(text);
                                if(position==0){
                                    type=1;
                                    rl_money.setVisibility(View.GONE);
                                }else{
                                    type=2;
                                    rl_money.setVisibility(View.VISIBLE);
                                }
                            }
                        })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (requestCode){
                case 1:
                    mApprovers= (Approvers) data.getSerializableExtra("data");
                    if(mApprovers!=null)
                         tv_name.setText(mApprovers.getName());
                    break;
                case 2:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    File file = new File(path);
                    upLoadImg(file, path);
                    break;
            }

        }
    }

    @OnClick({R.id.iv_back,R.id.rl_type, R.id.rl_approvers, R.id.tv_add})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_type:
                showTypeDialog();
                break;
            case R.id.rl_approvers:
                if(type==0){
                    ToastUtils.showShort("请先选择审批类型");
                    return;
                }
                startActivityForResult(new Intent(AddAprovalActivity.this, SelectApproversActivity.class), 1);
                break;
            case R.id.tv_add:
                if(checkInput()){
                    submit();
                }
                break;

        }
    }
    private void initView() {
        images=new ArrayList<>();
        mEvaluationChoiceImageView.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
            @Override
            public void onClickAddImage() {
                openPhoto(2);
            }
        });
        mEvaluationChoiceImageView.setOnClickDeleteImageListener(new EvaluationChoiceImageView.OnClickDeleteImageListener() {
            @Override
            public void onClickDeleteImage(int position) {
                pathList.remove(position);
                images.remove(position);
            }
        });
        mEvaluationChoiceImageView.setOnClickImageListener(new EvaluationChoiceImageView.OnClickImageListener() {
            @Override
            public void onClickImage(int position) {
                int pos=pathList.size()-1-position;
                PhotoUtil.seeBigPhoto(AddAprovalActivity.this,pos,pathList,mEvaluationChoiceImageView.getmFlowlayoutChilds().get(pos));
            }
        });
    }

    private void openPhoto(int code) {
        PictureSelector.create(AddAprovalActivity.this)
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
    private void upLoadImg(File file, String path) {
        NetUtils.getInstance().upLoadImage(Api.Image.uploadImage, file, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.getString("data");
                    images.add(data);
                    pathList.add(path);
                    mEvaluationChoiceImageView.addImage(path);
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
