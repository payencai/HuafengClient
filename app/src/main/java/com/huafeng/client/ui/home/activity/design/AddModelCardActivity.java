package com.huafeng.client.ui.home.activity.design;

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
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.tools.PhotoUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.home.activity.select.SelectCategoryActivity;
import com.huafeng.client.ui.home.activity.select.SelectMaterialActivity;
import com.huafeng.client.ui.home.activity.select.SelectSizeActivity;
import com.huafeng.client.ui.home.activity.select.SelectSizeTypeActivity;
import com.huafeng.client.ui.home.activity.select.SelectStyleActivity;
import com.huafeng.client.ui.home.activity.select.SelectWashProcessActivity;
import com.huafeng.client.ui.home.adapter.RawInputAdapter;
import com.huafeng.client.ui.home.adapter.SizeTypeInputAdapter;
import com.huafeng.client.ui.home.model.DesignDetail;
import com.huafeng.client.ui.home.model.FirstSize;
import com.huafeng.client.ui.home.model.FirstType;
import com.huafeng.client.ui.home.model.RawCategory;
import com.huafeng.client.ui.home.model.RawMatrerial;
import com.huafeng.client.ui.home.model.SizeType;
import com.huafeng.client.ui.home.model.WashProcess;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.ListViewForScrollView;
import com.huafeng.client.view.selectimage.EvaluationChoiceImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.HttpParams;

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

public class AddModelCardActivity extends BaseActivity {
    int flowId = 0;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.addimgs)
    EvaluationChoiceImageView mEvaluationChoiceImageView;
    @BindView(R.id.tv_size)
    TextView tv_size;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.tv_washprocess)
    TextView tv_wash;
    @BindView(R.id.et_color)
    CursorEditText et_color;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.lv_size)
    ListViewForScrollView lv_size;
    @BindView(R.id.lv_raw)
    ListViewForScrollView lv_raw;
    List<SizeType> mSizeTypes;
    List<RawMatrerial> mRawMatrerials;
    SizeTypeInputAdapter mSizeTypeInputAdapter;
    RawInputAdapter mRawInputAdapter;
    List<String> images;
    FirstType firstType;
    ArrayList<String> pathList=new ArrayList<>();
    String color;
    FirstSize.SizesBean sizesBean;
    List<Wash> mWashes;
    List<Material> mMaterials;
    List<Size> mSizes;
    ArrayList<WashProcess> mSelectWash;
    List<RawCategory> rawCategories;
    int pos = 0;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model_card);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        flowId = getIntent().getIntExtra("id", 0);
        initView();

    }

    DesignDetail mDesignDetail;

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", flowId);
        NetUtils.getInstance().get(MyApp.token, Api.Pattern.getFlow, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        mDesignDetail = new Gson().fromJson(data.toString(), DesignDetail.class);
                        initData();
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

    private void openPhoto(int code) {
        PictureSelector.create(AddModelCardActivity.this)
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
        if (!TextUtils.isEmpty(mDesignDetail.getPatternMakingCard().getColor())) {
            color = mDesignDetail.getPatternMakingCard().getColor();
            et_color.setText(mDesignDetail.getPatternMakingCard().getColor());
        }
        if (mDesignDetail.getPatternMakingCard().getSizeInformationList().size() > 0) {
            for (int i = 0; i < mDesignDetail.getPatternMakingCard().getSizeInformationList().size(); i++) {
                DesignDetail.PatternMakingCardBean.SizeInformationListBean sizeInformationListBean
                        = mDesignDetail.getPatternMakingCard().getSizeInformationList().get(i);
                SizeType sizeType = new SizeType();
                sizeType.setId(sizeInformationListBean.getSizeInfomationId());
                sizeType.setName(sizeInformationListBean.getSizeInfomationName());
                sizeType.setInput(sizeInformationListBean.getQuantity() + "");
                mSizeTypes.add(sizeType);
            }
            mSizeTypeInputAdapter.notifyDataSetChanged();
        }
        if (mDesignDetail.getPatternMakingCard().getMaterialList().size() > 0) {
            for (int i = 0; i < mDesignDetail.getPatternMakingCard().getMaterialList().size(); i++) {
                DesignDetail.PatternMakingCardBean.MaterialListBean sizeInformationListBean
                        = mDesignDetail.getPatternMakingCard().getMaterialList().get(i);
                RawMatrerial rawMatrerial = new RawMatrerial();
                rawMatrerial.setCategory1Name(sizeInformationListBean.getRawMaterialCategory1Name());
                rawMatrerial.setCategory1Id(sizeInformationListBean.getRawMaterialCategory1Id());
                rawMatrerial.setCategory2Name(sizeInformationListBean.getRawMaterialCategory2Name());
                rawMatrerial.setCategory2Id(sizeInformationListBean.getRawMaterialCategory2Id());
                rawMatrerial.setId(sizeInformationListBean.getRawMaterialId());
                rawMatrerial.setName(sizeInformationListBean.getRawMaterialName());
                rawMatrerial.setInput(sizeInformationListBean.getQuantity() + "");
                mRawMatrerials.add(rawMatrerial);
            }
            mRawInputAdapter.notifyDataSetChanged();
        }
        if (mDesignDetail.getPatternMakingCard().getWashingProcessList().size() > 0) {
            String name = "";
            for (int i = 0; i < mDesignDetail.getPatternMakingCard().getWashingProcessList().size(); i++) {
                DesignDetail.PatternMakingCardBean.WashingProcessListBean washingProcessListBean
                        = mDesignDetail.getPatternMakingCard().getWashingProcessList().get(i);
                WashProcess washProcess = new WashProcess();
                washProcess.setId(washingProcessListBean.getWashingProcessId());
                washProcess.setName(washingProcessListBean.getWashingProcessName());
                mSelectWash.add(washProcess);
                name = name + " " + washingProcessListBean.getWashingProcessName();
            }
            tv_wash.setText(name);
        }
        if (mDesignDetail.getPatternMakingCard().getSizeId() > 0) {
            sizesBean = new FirstSize.SizesBean();
            sizesBean.setId(mDesignDetail.getPatternMakingCard().getSizeId());
            sizesBean.setName(mDesignDetail.getPatternMakingCard().getSizeName());
            tv_size.setText(mDesignDetail.getPatternMakingCard().getSizeGroupName()+" "+mDesignDetail.getPatternMakingCard().getSizeName());
        }
        if (mDesignDetail.getPatternMakingCard().getProductCategory2Id() > 0) {
            firstType = new FirstType();
            firstType.setId(mDesignDetail.getPatternMakingCard().getProductCategory2Id());
            firstType.setName(mDesignDetail.getPatternMakingCard().getProductCategory2Name());
            tv_style.setText(mDesignDetail.getPatternMakingCard().getProductCategory1Name()+" "+mDesignDetail.getPatternMakingCard().getProductCategory2Name());
        }
        String imgskey = mDesignDetail.getPatternMakingCard().getImages();
        String imgs = mDesignDetail.getPatternMakingCard().getImagesUrl();
        if (!TextUtils.isEmpty(imgskey)) {
            if (imgskey.contains(",")) {
                String[] keys = imgskey.split(",");
                String[] photos = imgs.split(",");
                for (int i = 0; i < keys.length; i++) {
                    mEvaluationChoiceImageView.addImage(photos[i]);
                    images.add(keys[i]);
                    pathList.add(photos[i]);
                }
            } else {
                images.add(imgskey);
                pathList.add(imgs);
                mEvaluationChoiceImageView.addImage(imgs);

            }

        }
        if (!TextUtils.isEmpty(mDesignDetail.getPatternMakingCard().getRemarks())) {
            et_remark.setText(mDesignDetail.getPatternMakingCard().getRemarks());

        }
        tv_title.setText(mDesignDetail.getPatternMakingCard().getPatternMakingNo());
    }

    private void initView() {
        rawCategories = new ArrayList<>();
        mSizeTypes = new ArrayList<>();
        mRawMatrerials = new ArrayList<>();
        images = new ArrayList<>();
        mWashes = new ArrayList<>();
        mSizes = new ArrayList<>();
        mMaterials = new ArrayList<>();
        mSelectWash = new ArrayList<>();
        mEvaluationChoiceImageView.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
            @Override
            public void onClickAddImage() {
                openPhoto(5);
            }
        });
        mEvaluationChoiceImageView.setOnClickDeleteImageListener(new EvaluationChoiceImageView.OnClickDeleteImageListener() {
            @Override
            public void onClickDeleteImage(int position) {
                images.remove(position);
                pathList.remove(position);
            }
        });
        mEvaluationChoiceImageView.setOnClickImageListener(new EvaluationChoiceImageView.OnClickImageListener() {
            @Override
            public void onClickImage(int position) {
                int pos=pathList.size()-1-position;
                PhotoUtil.seeBigPhoto(AddModelCardActivity.this,pos,pathList,mEvaluationChoiceImageView.getmFlowlayoutChilds().get(pos));
            }
        });

        mRawInputAdapter = new RawInputAdapter(this, mRawMatrerials);
        mRawInputAdapter.setOnChangeListener(new RawInputAdapter.onChangeListener() {
            @Override
            public void onChange(int pos) {
                mRawMatrerials.remove(pos);
                mRawInputAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSelect(int position) {

                pos=position;
                int id = mRawMatrerials.get(position).getCategory2Id();
                Intent intent = new Intent(AddModelCardActivity.this, SelectMaterialActivity.class);
                intent.putExtra("id", id);
                //ToastUtils.showLong(id+"-");
                startActivityForResult(intent, 6);
            }
        });
        mSizeTypeInputAdapter = new SizeTypeInputAdapter(this, mSizeTypes);
        mSizeTypeInputAdapter.setOnChangeListener(new SizeTypeInputAdapter.onChangeListener() {
            @Override
            public void onChange(int pos) {
                mSizeTypes.remove(pos);
                mSizeTypeInputAdapter.notifyDataSetChanged();
            }
        });
        lv_size.setAdapter(mSizeTypeInputAdapter);
        lv_raw.setAdapter(mRawInputAdapter);
        getDetail();
    }

    private boolean checkInput() {
        boolean isOk = true;
        color = et_color.getEditableText().toString();

        if (images.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请选择图片");
            return isOk;
        }
        if (firstType == null) {
            isOk = false;
            ToastUtils.showShort("请选择款式");
            return isOk;
        }
        if (TextUtils.isEmpty(color)) {
            isOk = false;
            ToastUtils.showShort("请输入颜色");
            return isOk;
        }
        if (sizesBean == null) {
            isOk = false;
            ToastUtils.showShort("请选择尺码");
            return isOk;
        }
        if (mSelectWash.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请选择洗水工艺");
            return isOk;
        }
        if (mRawMatrerials.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请选择材质类型");
            return isOk;
        }
        if (mSizeTypes.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请选择尺寸类型");
            return isOk;
        }
        for (int i = 0; i < mSizeTypes.size(); i++) {
            String input = mSizeTypes.get(i).getInput();
            if (TextUtils.isEmpty(input)) {
                isOk = false;
                ToastUtils.showShort("输入不能为空");
                break;
            }
        }
        for (int i = 0; i < mRawMatrerials.size(); i++) {
            String input = mRawMatrerials.get(i).getInput();
            if (TextUtils.isEmpty(input)) {
                isOk = false;
                ToastUtils.showShort("输入不能为空");
                break;
            }
            if(mRawMatrerials.get(i).getId()==0){
                isOk = false;
                ToastUtils.showShort("请选择材质");
                break;
            }
        }
        return isOk;
    }

    private void save() {
        Map<String, Object> params = new HashMap<>();
        params.put("color", color);
        params.put("flowId", flowId);
        params.put("images", StringUtils.listToString2(images, ','));
        params.put("productCategoryId", firstType.getId());
        params.put("sizeId", sizesBean.getId());
        params.put("remarks", et_remark.getEditableText().toString());
        for (int i = 0; i < mSizeTypes.size(); i++) {
            Size size = new Size(mSizeTypes.get(i).getId(), Double.parseDouble(mSizeTypes.get(i).getInput()));
            mSizes.add(size);
        }
        for (int i = 0; i < mRawMatrerials.size(); i++) {
            Material material = new Material(mRawMatrerials.get(i).getId(), Double.parseDouble(mRawMatrerials.get(i).getInput()),mRawMatrerials.get(i).getCategory2Id(),mRawMatrerials.get(i).getName());
            mMaterials.add(material);
        }
        for (int i = 0; i < mSelectWash.size(); i++) {
            Wash wash = new Wash(mSelectWash.get(i).getId());
            mWashes.add(wash);
        }
        params.put("materialList", mMaterials.toArray());
        params.put("sizeInformationList", mSizes.toArray());
        params.put("washingProcessList", mWashes.toArray());
        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Pattern.saveCard, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("保存成功");
                        finish();
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
        LogUtils.e(json);
    }

    private void submit() {
        Map<String, Object> params = new HashMap<>();
        params.put("color", color);
        params.put("flowId", flowId);
        params.put("images", StringUtils.listToString2(images, ','));
        params.put("productCategoryId", firstType.getId());
        params.put("sizeId", sizesBean.getId());
        params.put("remarks", et_remark.getEditableText().toString());
        for (int i = 0; i < mSizeTypes.size(); i++) {
            Size size = new Size(mSizeTypes.get(i).getId(), Double.parseDouble(mSizeTypes.get(i).getInput()));
            mSizes.add(size);
        }
        for (int i = 0; i < mRawMatrerials.size(); i++) {
            Material material = new Material(mRawMatrerials.get(i).getId(), Double.parseDouble(mRawMatrerials.get(i).getInput()),mRawMatrerials.get(i).getCategory2Id(),mRawMatrerials.get(i).getName());
            mMaterials.add(material);
        }
        for (int i = 0; i < mSelectWash.size(); i++) {
            Wash wash = new Wash(mSelectWash.get(i).getId());
            mWashes.add(wash);
        }
        params.put("materialList", mMaterials.toArray());
        params.put("sizeInformationList", mSizes.toArray());
        params.put("washingProcessList", mWashes.toArray());
        String json = new Gson().toJson(params);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Pattern.addDesign, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("提交成功");
                        finish();
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
        LogUtils.e(json);
    }

    class Wash {
        private int washingProcessId;

        public Wash(int washingProcessId) {
            this.washingProcessId = washingProcessId;
        }
    }

    class Material {
        private int rawMaterialId;
        private double quantity;
        private int rawMaterialCategory2Id;
        private String rawMaterialName;

        public Material(int rawMaterialId, double quantity, int rawMaterialCategory2Id, String rawMaterialName) {
            this.rawMaterialId = rawMaterialId;
            this.quantity = quantity;
            this.rawMaterialCategory2Id = rawMaterialCategory2Id;
            this.rawMaterialName = rawMaterialName;
        }
    }

    class Size {
        private int sizeInfomationId;
        private double quantity;

        public Size(int sizeInfomationId, double quantity) {
            this.sizeInfomationId = sizeInfomationId;
            this.quantity = quantity;
        }


    }

    @OnClick({R.id.rl_wash, R.id.rl_size, R.id.tv_submit, R.id.rl_style, R.id.tv_sel_material, R.id.tv_sel_size, R.id.iv_back, R.id.tv_edit})
    void OnClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {

            case R.id.tv_edit:
                if (checkInput()) {
                    save();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkInput()) {
                    submit();
                }
                break;
            case R.id.tv_sel_size:
                startActivityForResult(new Intent(AddModelCardActivity.this, SelectSizeTypeActivity.class), 2);
                break;
            case R.id.tv_sel_material:
                startActivityForResult(new Intent(AddModelCardActivity.this, SelectCategoryActivity.class), 3);
                break;
            case R.id.rl_style:
                startActivityForResult(new Intent(AddModelCardActivity.this, SelectStyleActivity.class), 0);
                break;
            case R.id.rl_wash:
                startActivityForResult(new Intent(AddModelCardActivity.this, SelectWashProcessActivity.class), 4);
                break;
            case R.id.rl_size:
                startActivityForResult(new Intent(AddModelCardActivity.this, SelectSizeActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 0:
                    firstType = (FirstType) data.getSerializableExtra("data");
                    FirstType firstType1= (FirstType) data.getSerializableExtra("data1");
                    tv_style.setText(firstType1.getName()+" "+firstType.getName());
                    break;
                case 1:
                    sizesBean = (FirstSize.SizesBean) data.getSerializableExtra("data");
                    String sizename=data.getStringExtra("name");
                    tv_size.setText(sizename+" "+sizesBean.getName());
                    break;
                case 2:
                    SizeType sizeType = (SizeType) data.getSerializableExtra("data");
                    if (isShouldAddToSizeList(sizeType)) {
                        mSizeTypes.add(sizeType);
                        mSizeTypeInputAdapter.notifyDataSetChanged();
                    }
                    break;
                case 3:
                    RawCategory rawCategory2 = (RawCategory) data.getSerializableExtra("data");
                    RawCategory rawCategory1 = (RawCategory) data.getSerializableExtra("data1");
                    RawMatrerial rawMatrerial = new RawMatrerial();
                    rawMatrerial.setCategory1Id(rawCategory1.getId());
                    rawMatrerial.setName("请选择具体材质");
                    rawMatrerial.setInput("");
                    rawMatrerial.setId(0);
                    rawMatrerial.setCategory1Name(rawCategory1.getName());
                    rawMatrerial.setCategory2Id(rawCategory2.getId());
                    rawMatrerial.setCategory2Name(rawCategory2.getName());
                    mRawMatrerials.add(rawMatrerial);
                    mRawInputAdapter.notifyDataSetChanged();
                    break;
                case 4:
                    mSelectWash = (ArrayList<WashProcess>) data.getSerializableExtra("data");
                    String name = "";
                    for (int i = 0; i < mSelectWash.size(); i++) {
                        name = name + " " + mSelectWash.get(i).getName();
                    }
                    tv_wash.setText(name);
                    break;
                case 5:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    File file = new File(path);
                    upLoadImg(file, path);
                    break;
                case 6:

                    RawMatrerial rawMatrerial1 = (RawMatrerial) data.getSerializableExtra("data");

                    if (isShouldAddToRawList(rawMatrerial1)) {
                        mRawMatrerials.get(pos).setId(rawMatrerial1.getId());
                        mRawMatrerials.get(pos).setName(rawMatrerial1.getName());
                    }
                    mRawInputAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private boolean isShouldAddToRawList(RawMatrerial matrerial) {
        boolean isAdd = true;
        if (matrerial.getId() == -1) {
            isAdd = true;
            return isAdd;
        }
        for (int i = 0; i < mRawMatrerials.size(); i++) {
            RawMatrerial rawMatrerial = mRawMatrerials.get(i);
            if (rawMatrerial.getId() == matrerial.getId()) {
                isAdd = false;
                ToastUtils.showLong("材质不能重复！");
                break;
            }
        }

        return isAdd;

    }

    private boolean isShouldAddToSizeList(SizeType sizeType) {
        boolean isAdd = true;
        for (int i = 0; i < mSizeTypes.size(); i++) {
            SizeType mSizeType = mSizeTypes.get(i);
            if (mSizeType.getId() == sizeType.getId()) {
                isAdd = false;
                break;
            }
        }
        return isAdd;

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
