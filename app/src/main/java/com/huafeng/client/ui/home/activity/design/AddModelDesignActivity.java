package com.huafeng.client.ui.home.activity.design;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.select.SelectClientActivity;
import com.huafeng.client.ui.home.activity.select.SelectDesignerActivity;
import com.huafeng.client.ui.home.activity.select.SelectNoprefixsActivity;
import com.huafeng.client.ui.home.model.Auth;
import com.huafeng.client.ui.home.model.Client;
import com.huafeng.client.ui.home.model.Designer;
import com.huafeng.client.ui.home.model.NoPrefixs;
import com.huafeng.client.view.selectimage.EvaluationChoiceImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddModelDesignActivity extends BaseActivity {
    @BindView(R.id.tv_prefixs)
    TextView tv_prefixs;
    @BindView(R.id.tv_client)
    TextView tv_client;
    @BindView(R.id.tv_designer)
    TextView tv_designer;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.addimgs)
    EvaluationChoiceImageView mEvaluationChoiceImageView;
    NoPrefixs mNoPrefixs;
    Client mClient;
    String requirement;
    Designer mDesigner;
    List<String> images;
    ArrayList<String> pathList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model_design);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void upLoadImg(File file, String path) {
        NetUtils.getInstance().upLoadImage(Api.Image.uploadImage, file, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        String data = jsonObject.getString("data");
                        images.add(data);
                        pathList.add(path);
                        if (!TextUtils.isEmpty(path))
                            mEvaluationChoiceImageView.addImage(path);
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

    private void openPhoto(int code) {
        PictureSelector.create(AddModelDesignActivity.this)
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

    private void initView() {
        images = new ArrayList<>();
        pathList=new ArrayList<>();
        mEvaluationChoiceImageView.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
            @Override
            public void onClickAddImage() {
                openPhoto(5);
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
                PhotoUtil.seeBigPhoto(AddModelDesignActivity.this,pos,pathList,mEvaluationChoiceImageView.getmFlowlayoutChilds().get(pos));
            }
        });
    }

    private void submit() {
        String url=Api.Pattern.create;
        List<Auth> auths=LitePal.findAll(Auth.class);
        for (int i = 0; i <auths.size() ; i++) {
            if(auths.get(i).getAuthId()==1012){
                url=Api.Pattern.designCreate;
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("requirement", requirement);
        params.put("clientId", mClient.getId());
        params.put("images", StringUtils.listToString2(images, ','));
        params.put("designById", mDesigner.getId());
        params.put("patternMakingNoPrefixId", mNoPrefixs.getId());
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(url, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                Log.e("tag", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("创建成功");
                        finish();
                    } else {
                        String msg = jsonObject.getString("message");
                        ToastUtils.showShort(msg);
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onError(String error) {

            }
        });
        LogUtils.e(json);
    }

    private boolean checkInput() {
        boolean isOk = true;
        requirement = et_content.getEditableText().toString();
        if (TextUtils.isEmpty(requirement)) {
            isOk = false;
            ToastUtils.showShort("请输入设计要求");
            return isOk;
        }
        if (images.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请选择图片");
            return isOk;
        }
        if (mDesigner == null) {
            isOk = false;
            ToastUtils.showShort("请选择设计师");
            return isOk;
        }
        if (mClient == null) {
            isOk = false;
            ToastUtils.showShort("请选择客户");
            return isOk;
        }
        if (mNoPrefixs == null) {
            isOk = false;
            ToastUtils.showShort("请选择标头");
            return isOk;
        }
        return isOk;
    }

    @OnClick({R.id.rl_prefixs, R.id.rl_client, R.id.rl_designer, R.id.tv_add, R.id.iv_back})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                if (checkInput()) {
                    submit();
                }
                break;
            case R.id.rl_prefixs:
                startActivityForResult(new Intent(AddModelDesignActivity.this, SelectNoprefixsActivity.class), 1);
                break;
            case R.id.rl_client:
                startActivityForResult(new Intent(AddModelDesignActivity.this, SelectClientActivity.class), 2);
                break;
            case R.id.rl_designer:
                startActivityForResult(new Intent(AddModelDesignActivity.this, SelectDesignerActivity.class), 3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
                    mNoPrefixs = (NoPrefixs) data.getSerializableExtra("data");
                    tv_prefixs.setText(mNoPrefixs.getName());
                    tv_number.setText(mNoPrefixs.getName()+"-"+(mNoPrefixs.getNumber() + 1));
                    break;
                case 2:
                    mClient = (Client) data.getSerializableExtra("data");
                    tv_client.setText(mClient.getName());
                    break;
                case 3:
                    mDesigner = (Designer) data.getSerializableExtra("data");
                    tv_designer.setText(mDesigner.getName());
                    break;
                case 5:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    File file = new File(path);
                    upLoadImg(file, path);
                    break;
            }
        }
    }
}
