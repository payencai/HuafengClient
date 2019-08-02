package com.huafeng.client.ui.home.activity.design;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.huafeng.client.ui.home.activity.sample.BigSampleDetail;
import com.huafeng.client.ui.home.activity.sample.ProcesModel;
import com.huafeng.client.ui.home.activity.sample.SampleRaw;
import com.huafeng.client.ui.home.activity.sample.SampleRawInputAdapter;
import com.huafeng.client.ui.home.activity.sample.SampleSection;
import com.huafeng.client.ui.home.activity.sample.SampleSectionAdapter;
import com.huafeng.client.ui.home.activity.sample.SampleSize;
import com.huafeng.client.ui.home.activity.sample.SampleSizeAdapter;
import com.huafeng.client.ui.home.activity.sample.SampleSubmit;
import com.huafeng.client.ui.home.activity.select.SelectBigModelActivity;
import com.huafeng.client.ui.home.activity.sample.SelectProcess;
import com.huafeng.client.ui.home.activity.select.FirstStyleSelectActivity;
import com.huafeng.client.ui.home.activity.select.SelectCategoryActivity;
import com.huafeng.client.ui.home.activity.select.SelectClientActivity;
import com.huafeng.client.ui.home.activity.select.SelectDesignerActivity;
import com.huafeng.client.ui.home.activity.select.SelectMaterialActivity;
import com.huafeng.client.ui.home.activity.select.SelectMoreSizeActivity;
import com.huafeng.client.ui.home.activity.select.SelectProcessActivity;
import com.huafeng.client.ui.home.activity.select.SelectSingleSizeActivity;
import com.huafeng.client.ui.home.model.Client;
import com.huafeng.client.ui.home.model.Designer;
import com.huafeng.client.ui.home.model.FirstSize;
import com.huafeng.client.ui.home.model.FirstType;
import com.huafeng.client.ui.home.model.RawCategory;
import com.huafeng.client.ui.home.model.RawMatrerial;
import com.huafeng.client.ui.home.model.SizeType;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.ListViewForScrollView;
import com.huafeng.client.view.MathUtils;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddModelDataActivity extends BaseActivity {
    int id;
    int flowId;
    BigSampleDetail bigSampleDetail;
    SampleSubmit sampleSubmit;
    List<SampleRaw> sampleRaws;
    List<SampleSize> sampleSizes;
    @BindView(R.id.lv_raw)
    ListViewForScrollView lv_raw;
    @BindView(R.id.lv_size)
    ListViewForScrollView lv_size;
    @BindView(R.id.et_color)
    CursorEditText et_color;
    @BindView(R.id.et_no1)
    CursorEditText et_no1;
    @BindView(R.id.et_no2)
    CursorEditText et_no2;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.rv_process)
    RecyclerView rv_process;
    @BindView(R.id.tv_style)
    TextView tv_style;

    @BindView(R.id.tv_designer)
    TextView tv_designer;
    @BindView(R.id.tv_client)
    TextView tv_client;
    @BindView(R.id.tv_data)
    TextView tv_data;
    @BindView(R.id.tv_washprocess)
    TextView tv_washprocess;
    @BindView(R.id.addimgs)
    EvaluationChoiceImageView mEvaluationChoiceImageView;
    SampleRawInputAdapter sampleRawInputAdapter;
    SampleSizeAdapter sampleSizeAdapter;
    Client client;
    Designer designer;
    FirstType firstType;
    String color;
    List<String> images;
    ArrayList<FirstSize.SizesBean> mSelectSizes;
    ArrayList<SizeType> mSelectSizeTypes;
    ArrayList<SelectProcess.ProcessListBean> selectProcess;
    SampleSectionAdapter sampleSectionAdapter;
    ArrayList<SampleSection> sampleSections;
    ArrayList<String> pathList=new ArrayList<>();
    int moduleId;
    ProcesModel bigModel;

    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model_data);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        flowId = getIntent().getIntExtra("flowId", 0);
        id = getIntent().getIntExtra("id", 0);
        sampleSubmit = new SampleSubmit();
        initView();
    }

    private void initRaw() {
        sampleRaws = new ArrayList<>();
        sampleRawInputAdapter = new SampleRawInputAdapter(this, sampleRaws);
        sampleRawInputAdapter.setOnChangeListener(new SampleRawInputAdapter.onChangeListener() {
            @Override
            public void onChange(int pos) {
                sampleRaws.remove(pos);
                sampleRawInputAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSelect(int position) {
                pos=position;
                int id = sampleRaws.get(position).getCategory2Id();
                Intent intent = new Intent(AddModelDataActivity.this, SelectMaterialActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("id", id);
                //ToastUtils.showLong(id+"-");
                startActivityForResult(intent, 12);
            }
        });
        lv_raw.setAdapter(sampleRawInputAdapter);
    }

    private void initProcess() {
        selectProcess = new ArrayList<>();
        sampleSections = new ArrayList<>();
        sampleSectionAdapter = new SampleSectionAdapter(sampleSections);
        sampleSectionAdapter.setOnItemSelectListener(new SampleSectionAdapter.OnItemSelectListener() {
            @Override
            public void onSelect(int pos, ImageView imageView) {

                SelectProcess.ProcessListBean productProcessListBean = sampleSections.get(pos).t;
                if (productProcessListBean.getIsOutsourcing()==1) {
                    sampleSections.get(pos).t.setIsOutsourcing(0);
                } else {
                    sampleSections.get(pos).t.setIsOutsourcing(1);
                }
                sampleSectionAdapter.setNewData(sampleSections);

            }

            @Override
            public void onChange(int pos, String price) {
                SelectProcess.ProcessListBean productProcessListBean = sampleSections.get(pos).t;
                if (!TextUtils.isEmpty(price)) {
                    if (MathUtils.isNumber(price)) {
                        if(productProcessListBean!=null){
                            sampleSections.get(pos).t.setPrice(Double.valueOf(price));
                        }

                    }
                }
            }
        });

        rv_process.setLayoutManager(new LinearLayoutManager(this));
        rv_process.setAdapter(sampleSectionAdapter);


    }

    private void initPhoto() {
        images = new ArrayList<>();
        mEvaluationChoiceImageView.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
            @Override
            public void onClickAddImage() {
                openPhoto(4);
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
                PhotoUtil.seeBigPhoto(AddModelDataActivity.this,pos,pathList,mEvaluationChoiceImageView.getmFlowlayoutChilds().get(pos));
            }
        });
    }

    private void initSize() {
        mSelectSizeTypes = new ArrayList<>();
        mSelectSizes = new ArrayList<>();
        sampleSizes = new ArrayList<>();
        sampleSizeAdapter = new SampleSizeAdapter(this, sampleSizes);
        sampleSizeAdapter.setOnChildSelectListener(new SampleSizeAdapter.OnChildSelectListener() {
            @Override
            public void onSelect(int pos) {
                sampleSizes.remove(pos);
                sampleSizeAdapter.notifyDataSetChanged();
                if(sampleSizes.size()==0){
                    tv_data.setVisibility(View.GONE);
                }
            }
        });
        lv_size.setAdapter(sampleSizeAdapter);
        if(sampleSizes.size()==0){
            tv_data.setVisibility(View.GONE);
        }else{
            tv_data.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        initRaw();
        initSize();
        initPhoto();
        initProcess();
        if (id > 0)
            getDetail();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            switch (requestCode) {

                case 1:
                    designer = (Designer) data.getSerializableExtra("data");
                    tv_designer.setText(designer.getName());
                    break;
                case 2:
                    client = (Client) data.getSerializableExtra("data");
                    tv_client.setText(client.getName());
                    break;
                case 3:
                    firstType = (FirstType) data.getSerializableExtra("data");
                    tv_style.setText(firstType.getName());
                    break;
                case 4:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    File file = new File(path);
                    upLoadImg(file, path);
                    break;
                case 5:
                    RawCategory rawCategory2 = (RawCategory) data.getSerializableExtra("data");
                    RawCategory rawCategory1 = (RawCategory) data.getSerializableExtra("data1");
                    SampleRaw sampleRaw = new SampleRaw();
                    sampleRaw.setCategory1Id(rawCategory1.getId());
                    sampleRaw.setRawMaterialName("请选择具体材质");
                    sampleRaw.setQuantity(null);
                    sampleRaw.setCategory1Name(rawCategory1.getName());
                    sampleRaw.setCategory2Id(rawCategory2.getId());
                    sampleRaw.setCategory2Name(rawCategory2.getName());
                    sampleRaws.add(sampleRaw);
                    sampleRawInputAdapter.notifyDataSetChanged();
                    break;
                case 6:
                    mSelectSizes = (ArrayList<FirstSize.SizesBean>) data.getSerializableExtra("data");
                    if (mSelectSizes.size() > 0) {
                        tv_data.setVisibility(View.VISIBLE);
                    }
                    for (int i = 0; i < mSelectSizes.size(); i++) {
                        SampleSize sampleSize = new SampleSize();
                        sampleSize.setSizeId(mSelectSizes.get(i).getId());
                        sampleSize.setSizeName(mSelectSizes.get(i).getName());
                        if (isShouldAddSampleSize(sampleSize))
                            sampleSizes.add(sampleSize);
                    }
                    sampleSizeAdapter.notifyDataSetChanged();
                    break;
                case 7:
                    mSelectSizeTypes = (ArrayList<SizeType>) data.getSerializableExtra("data");
                    for (int i = 0; i < sampleSizes.size(); i++) {
                        List<SampleSize.SampleSizeInformationListBean> sizeInformationListBeans = new ArrayList<>();
                        for (int j = 0; j < mSelectSizeTypes.size(); j++) {
                            SampleSize.SampleSizeInformationListBean sampleSizeInformationListBean = new SampleSize.SampleSizeInformationListBean();
                            sampleSizeInformationListBean.setSizeInfomationId(mSelectSizeTypes.get(j).getId());
                            sampleSizeInformationListBean.setSizeInformationName(mSelectSizeTypes.get(j).getName());
                            sampleSizeInformationListBean.setQuantity(0);
                            sizeInformationListBeans.add(sampleSizeInformationListBean);
                        }
                        sampleSizes.get(i).setSampleSizeInformationList(sizeInformationListBeans);
                    }
                    sampleSizeAdapter.notifyDataSetChanged();
                    break;
                case 8:
                    sampleSections.clear();
                    selectProcess.clear();
                    selectProcess = (ArrayList<SelectProcess.ProcessListBean>) data.getSerializableExtra("data");
                    SelectProcess.ProcessListBean old = selectProcess.get(0);
                    old.setHeader(true);
                    sampleSections.add(new SampleSection(true, old.getProcessName()));
                    sampleSections.add(new SampleSection(old));
                    String washname = "";
                    for (int i = 1; i < selectProcess.size(); i++) {
                        SelectProcess.ProcessListBean current = selectProcess.get(i);
                        if (current.getStageId() == 3) {
                            washname = washname + " " + current.getName();
                        }
                        if (old.getStageId() != current.getStageId()) {
                            current.setHeader(true);
                            sampleSections.add(new SampleSection(true, current.getProcessName()));
                            sampleSections.add(new SampleSection(current));
                            old = current;
                        } else {
                            current.setHeader(false);
                            sampleSections.add(new SampleSection(current));
                        }
                    }
                    tv_washprocess.setText(washname);
                    sampleSectionAdapter.setNewData(sampleSections);
                    break;
                case 9:
                    moduleId = data.getIntExtra("id", 0);
                    getModel(Api.Sample.getSizeModule, 1);
                    break;
                case 10:
                    moduleId = data.getIntExtra("id", 0);
                    getModel(Api.Sample.getMaterialModule, 2);
                    break;
                case 11:
                    moduleId = data.getIntExtra("id", 0);
                    getModel(Api.Sample.getProcessModule, 3);
                    break;
                case 12:
                    RawMatrerial rawMatrerial1 = (RawMatrerial) data.getSerializableExtra("data");
                    if (isShouldAddToRawList(rawMatrerial1)) {
                        sampleRaws.get(pos).setRawMaterialId(rawMatrerial1.getId());
                        sampleRaws.get(pos).setRawMaterialName(rawMatrerial1.getName());
                    }
                    sampleRawInputAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private void setModel(int type) {
        if (type == 1) {
            sampleSizes.clear();
            for (int i = 0; i < bigModel.getSizeList().size(); i++) {
                ProcesModel.SizeListBean sizeListBean = bigModel.getSizeList().get(i);
                SampleSize sampleSize = new SampleSize();
                sampleSize.setSizeId(sizeListBean.getSizeId());
                sampleSize.setSizeName(sizeListBean.getSizeName());
                List<SampleSize.SampleSizeInformationListBean> sizeInformationListBeans = new ArrayList<>();
                for (int j = 0; j < sizeListBean.getSampleSizeInformationList().size(); j++) {
                    ProcesModel.SizeListBean.SampleSizeInformationListBean informationListBean = sizeListBean.getSampleSizeInformationList().get(j);
                    SampleSize.SampleSizeInformationListBean sampleSizeInformationListBean = new SampleSize.SampleSizeInformationListBean();
                    sampleSizeInformationListBean.setQuantity(informationListBean.getQuantity());
                    sampleSizeInformationListBean.setSizeInformationName(informationListBean.getSizeInformationName());
                    sampleSizeInformationListBean.setSizeInfomationId(informationListBean.getSizeInfomationId());
                    sizeInformationListBeans.add(sampleSizeInformationListBean);
                }
                sampleSize.setSampleSizeInformationList(sizeInformationListBeans);
                sampleSizes.add(sampleSize);
            }
            sampleSizeAdapter.notifyDataSetChanged();
        } else if (type == 2) {
            sampleRaws.clear();
            for (int i = 0; i < bigModel.getMaterialList().size(); i++) {
                SampleRaw sampleRaw = new SampleRaw();
                sampleRaw.setQuantity(bigModel.getMaterialList().get(i).getQuantity());
                sampleRaw.setRawMaterialId(bigModel.getMaterialList().get(i).getRawMaterialId());
                sampleRaw.setRawMaterialName(bigModel.getMaterialList().get(i).getRawMaterialName());
                sampleRaw.setCategory1Name(bigModel.getMaterialList().get(i).getRawMaterialCategory1Name());
                sampleRaw.setCategory2Name(bigModel.getMaterialList().get(i).getRawMaterialCategory2Name());
                sampleRaw.setCategory1Id(bigModel.getMaterialList().get(i).getRawMaterialCategory1Id());
                sampleRaw.setCategory2Id(bigModel.getMaterialList().get(i).getRawMaterialCategory2Id());
                sampleRaws.add(sampleRaw);
            }
            sampleRawInputAdapter.notifyDataSetChanged();
        } else {
            sampleSections.clear();
            if (bigModel.getProcessList() != null && bigModel.getProcessList().size() > 0) {
                SelectProcess.ProcessListBean old = new SelectProcess.ProcessListBean();
                old.setPrice(bigModel.getProcessList().get(0).getPrice());
                old.setStageId(bigModel.getProcessList().get(0).getStageId());
                old.setId(bigModel.getProcessList().get(0).getProcessId());
                old.setName(bigModel.getProcessList().get(0).getProcessName());
                old.setProcessName(bigModel.getProcessList().get(0).getStageName());
                old.setIsOutsourcing(bigModel.getProcessList().get(0).getIsOutsourcing());
                sampleSections.add(new SampleSection(true, old.getProcessName()));
                old.setHeader(true);
                sampleSections.add(new SampleSection(old));
                String washname = "";
                for (int i = 1; i < bigModel.getProcessList().size(); i++) {
                    SelectProcess.ProcessListBean current = new SelectProcess.ProcessListBean();
                    current.setPrice(bigModel.getProcessList().get(i).getPrice());
                    current.setStageId(bigModel.getProcessList().get(i).getStageId());
                    current.setId(bigModel.getProcessList().get(i).getProcessId());
                    current.setName(bigModel.getProcessList().get(i).getProcessName());
                    current.setProcessName(bigModel.getProcessList().get(i).getStageName());
                    current.setIsOutsourcing(bigModel.getProcessList().get(i).getIsOutsourcing());
                    if (current.getStageId() == 3) {
                        washname = washname + " " + current.getName();
                    }
                    if (old.getStageId() != current.getStageId()) {
                        current.setHeader(true);
                        sampleSections.add(new SampleSection(true, current.getProcessName()));
                        sampleSections.add(new SampleSection(current));
                        old = current;
                    } else {
                        sampleSections.add(new SampleSection(current));
                    }
                }
                tv_washprocess.setText(washname);
                sampleSectionAdapter.notifyDataSetChanged();
            }
        }
    }

    private void getModel(String url, int type) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("moduleId", moduleId);
        NetUtils.getInstance().get(MyApp.token, url, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        bigModel = new Gson().fromJson(data.toString(), ProcesModel.class);
                        setModel(type);
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


    private boolean isShouldAddToRawList(RawMatrerial rawMatrerial) {
        boolean isAdd = true;
        if(rawMatrerial.getId()==-1){
            return isAdd;
        }
        for (int i = 0; i < sampleRaws.size(); i++) {
            SampleRaw sampleRaw = sampleRaws.get(i);
            if (sampleRaw.getRawMaterialId() == rawMatrerial.getId()) {
                isAdd = false;
                ToastUtils.showLong("材质不能重复！");
                break;
            }
        }
        return isAdd;

    }

    private boolean isShouldAddSampleSize(SampleSize sampleSize) {
        boolean isAdd = true;
        for (int i = 0; i < sampleSizes.size(); i++) {
            SampleSize size = sampleSizes.get(i);
            if (size.getSizeId() == sampleSize.getSizeId()) {
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

    private void openPhoto(int code) {
        PictureSelector.create(AddModelDataActivity.this)
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

    private boolean checkInput() {
        boolean isOk = true;

        color = et_color.getEditableText().toString();
        if (images.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请选择图片");
            return isOk;
        }
        if (TextUtils.isEmpty(color)) {
            isOk = false;
            ToastUtils.showShort("请输入颜色");
            return isOk;
        }
        if (TextUtils.isEmpty(et_no1.getEditableText().toString())) {
            isOk = false;
            ToastUtils.showShort("请输入纸样编号");
            return isOk;
        }
        if (TextUtils.isEmpty(et_no2.getEditableText().toString())) {
            isOk = false;
            ToastUtils.showShort("请输入样板编号");
            return isOk;
        }
        if (designer == null) {
            isOk = false;
            ToastUtils.showShort("请选择设计师");
            return isOk;
        }
        if (client == null) {
            isOk = false;
            ToastUtils.showShort("请选择客户");
            return isOk;
        }
        if (sampleSections.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请选择生产工序！");
            return isOk;
        }
        if (sampleRaws.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请添加原料信息");
            return isOk;
        }
        if (sampleSizes.size() == 0) {
            isOk = false;
            ToastUtils.showShort("请添加尺寸信息");
            return isOk;
        }

        for (int i = 0; i < sampleRaws.size(); i++) {
            SampleSubmit.MaterialListBean materialListBean = new SampleSubmit.MaterialListBean();
            if(sampleRaws.get(i).getRawMaterialId()==0){
                ToastUtils.showLong("没有选择材质！");
                isOk=false;
                break;
            }else if(sampleRaws.get(i).getRawMaterialId()==-1){
                ToastUtils.showLong("不能提交新材质！");
                isOk=false;
                break;
            }
        }
        List<SampleSubmit.ProductProcessListBean> productProcessListBeans = new ArrayList<>();
        List<SampleSubmit.WashingProcessListBean> washingProcessListBeans = new ArrayList<>();
        List<SampleSubmit.MaterialListBean> materialListBeans = new ArrayList<>();
        List<SampleSubmit.SizeListBean> sizeListBeans = new ArrayList<>();
        for (int i = 0; i < sampleSections.size(); i++) {
            if (sampleSections.get(i).t != null) {
                SampleSubmit.ProductProcessListBean productProcessListBean = new SampleSubmit.ProductProcessListBean();
                productProcessListBean.setProcessId(sampleSections.get(i).t.getId());
                productProcessListBean.setIsOutsourcing(sampleSections.get(i).t.getIsOutsourcing());
                productProcessListBean.setPrice(sampleSections.get(i).t.getPrice());
                productProcessListBeans.add(productProcessListBean);
            }
        }
        for (int i = 0; i < selectProcess.size(); i++) {
            if (selectProcess.get(i).getStageId() == 3) {
                SampleSubmit.WashingProcessListBean washingProcessListBean = new SampleSubmit.WashingProcessListBean();
                washingProcessListBean.setWashingProcessId(selectProcess.get(i).getId());
                washingProcessListBeans.add(washingProcessListBean);
            }
        }
        for (int i = 0; i < sampleRaws.size(); i++) {
            SampleSubmit.MaterialListBean materialListBean = new SampleSubmit.MaterialListBean();
            materialListBean.setQuantity(sampleRaws.get(i).getQuantity());
            materialListBean.setRawMaterialId(sampleRaws.get(i).getRawMaterialId());
            materialListBean.setRawMaterialCategory2Id(sampleRaws.get(i).getCategory2Id());
            materialListBean.setRawMaterialName(sampleRaws.get(i).getRawMaterialName());
            materialListBeans.add(materialListBean);
        }
        for (int i = 0; i < sampleSizes.size(); i++) {
            SampleSize sampleSize = sampleSizes.get(i);
            SampleSubmit.SizeListBean sizeListBean = new SampleSubmit.SizeListBean();
            List<SampleSubmit.SizeListBean.SampleSizeInformationListBean> sampleSizeInformationListBeans = new ArrayList<>();
            for (int j = 0; j < sampleSize.getSampleSizeInformationList().size(); j++) {
                SampleSubmit.SizeListBean.SampleSizeInformationListBean sampleSizeInformationListBean = new SampleSubmit.SizeListBean.SampleSizeInformationListBean();
                sampleSizeInformationListBean.setQuantity(sampleSize.getSampleSizeInformationList().get(j).getQuantity());
                sampleSizeInformationListBean.setSizeInfomationId(sampleSize.getSampleSizeInformationList().get(j).getSizeInfomationId());
                sampleSizeInformationListBeans.add(sampleSizeInformationListBean);
            }
            sizeListBean.setSampleSizeInformationList(sampleSizeInformationListBeans);
            sizeListBean.setSizeId(sampleSizes.get(i).getSizeId());
            sizeListBeans.add(sizeListBean);
        }
        sampleSubmit.setSizeList(sizeListBeans);
        sampleSubmit.setMaterialList(materialListBeans);
        sampleSubmit.setWashingProcessList(washingProcessListBeans);
        sampleSubmit.setProductProcessList(productProcessListBeans);
        sampleSubmit.setColor(et_color.getEditableText().toString());
        sampleSubmit.setFlowId(flowId);
        sampleSubmit.setId(-1);
        sampleSubmit.setRemarks(et_remark.getEditableText().toString());
        sampleSubmit.setClientRecordId(client.getId());
        sampleSubmit.setDesignerBy(designer.getId());
        sampleSubmit.setImages(StringUtils.listToString2(images, ','));
        sampleSubmit.setProductCategoryId(firstType.getId());
        sampleSubmit.setPatternNo(et_no1.getEditableText().toString());
        sampleSubmit.setSampleNo(et_no2.getEditableText().toString());
        String json = new Gson().toJson(sampleSubmit);
        LogUtils.e(json);
        return isOk;
    }

    private void submit() {
        String json = new Gson().toJson(sampleSubmit);
        LogUtils.e(json);
        NetUtils.getInstance().post(Api.Sample.add, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("录入成功");
                        setResult(RESULT_OK,new Intent());
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

    @OnClick({R.id.tv_flow, R.id.tv_raw, R.id.tv_size, R.id.tv_process, R.id.tv_data, R.id.rl_client, R.id.rl_designer, R.id.tv_submit, R.id.rl_style, R.id.iv_back, R.id.tv_addraw, R.id.tv_addsize})
    void OnClick(View view) {
        Intent intent;
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {

            case R.id.tv_size:
                intent = new Intent(AddModelDataActivity.this, SelectBigModelActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 9);
                break;
            case R.id.tv_raw:
                intent = new Intent(AddModelDataActivity.this, SelectBigModelActivity.class);
                intent.putExtra("type", 2);
                startActivityForResult(intent, 10);
                break;
            case R.id.tv_flow:
                intent = new Intent(AddModelDataActivity.this, SelectBigModelActivity.class);
                intent.putExtra("type", 3);
                startActivityForResult(intent, 11);
                break;
            case R.id.tv_process:
                startActivityForResult(new Intent(AddModelDataActivity.this, SelectProcessActivity.class), 8);
                break;
            case R.id.tv_data:
                startActivityForResult(new Intent(AddModelDataActivity.this, SelectMoreSizeActivity.class), 7);
                break;
            case R.id.tv_addsize:
                startActivityForResult(new Intent(AddModelDataActivity.this, SelectSingleSizeActivity.class), 6);
                break;
            case R.id.tv_addraw:
                startActivityForResult(new Intent(AddModelDataActivity.this, SelectCategoryActivity.class), 5);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                if (checkInput()) {
                    submit();
                }
                break;
            case R.id.rl_designer:
                startActivityForResult(new Intent(AddModelDataActivity.this, SelectDesignerActivity.class), 1);
                break;
            case R.id.rl_client:
                startActivityForResult(new Intent(AddModelDataActivity.this, SelectClientActivity.class), 2);
                break;
            case R.id.rl_style:
                startActivityForResult(new Intent(AddModelDataActivity.this, FirstStyleSelectActivity.class), 3);
                break;
        }
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Pattern.prepareToSample, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        bigSampleDetail = new Gson().fromJson(data.toString(), BigSampleDetail.class);
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

    private void initData() {
        if (bigSampleDetail != null) {
            for (int i = 0; i < bigSampleDetail.getMaterialList().size(); i++) {
                SampleRaw sampleRaw = new SampleRaw();
                sampleRaw.setCategory1Id(bigSampleDetail.getMaterialList().get(i).getRawMaterialCategory1Id());
                sampleRaw.setCategory1Name(bigSampleDetail.getMaterialList().get(i).getRawMaterialCategory1Name());
                sampleRaw.setCategory2Id(bigSampleDetail.getMaterialList().get(i).getRawMaterialCategory2Id());
                sampleRaw.setCategory2Name(bigSampleDetail.getMaterialList().get(i).getRawMaterialCategory2Name());
                sampleRaw.setQuantity(bigSampleDetail.getMaterialList().get(i).getQuantity());
                sampleRaw.setRawMaterialId(bigSampleDetail.getMaterialList().get(i).getRawMaterialId());
                sampleRaw.setRawMaterialName(bigSampleDetail.getMaterialList().get(i).getRawMaterialName());
                sampleRaws.add(sampleRaw);
            }
            for (int i = 0; i < bigSampleDetail.getSizeList().size(); i++) {
                BigSampleDetail.SizeListBean sizeListBean = bigSampleDetail.getSizeList().get(i);
                SampleSize sampleSize = new SampleSize();
                sampleSize.setSizeId(sizeListBean.getSizeId());
                sampleSize.setSizeName(sizeListBean.getSizeName());
                List<SampleSize.SampleSizeInformationListBean> sizeInformationListBeans = new ArrayList<>();
                for (int j = 0; j < sizeListBean.getSampleSizeInformationList().size(); j++) {
                    BigSampleDetail.SizeListBean.SampleSizeInformationListBean informationListBean = sizeListBean.getSampleSizeInformationList().get(j);
                    SampleSize.SampleSizeInformationListBean sampleSizeInformationListBean = new SampleSize.SampleSizeInformationListBean();
                    sampleSizeInformationListBean.setQuantity(informationListBean.getQuantity());
                    sampleSizeInformationListBean.setSizeInformationName(informationListBean.getSizeInformationName());
                    sampleSizeInformationListBean.setSizeInfomationId(informationListBean.getSizeInfomationId());
                    sizeInformationListBeans.add(sampleSizeInformationListBean);
                }
                sampleSize.setSampleSizeInformationList(sizeInformationListBeans);
                sampleSizes.add(sampleSize);
            }
            if (bigSampleDetail.getProductProcessList() != null && bigSampleDetail.getProductProcessList().size() > 0) {
                SelectProcess.ProcessListBean old = new SelectProcess.ProcessListBean();
                old.setPrice(bigSampleDetail.getProductProcessList().get(0).getPrice());
                old.setStageId(bigSampleDetail.getProductProcessList().get(0).getStageId());
                old.setId(bigSampleDetail.getProductProcessList().get(0).getId());
                old.setName(bigSampleDetail.getProductProcessList().get(0).getStageName());
                old.setProcessName(bigSampleDetail.getProductProcessList().get(0).getProcessName());
                old.setIsOutsourcing(bigSampleDetail.getProductProcessList().get(0).getIsOutsourcing());
                sampleSections.add(new SampleSection(true, old.getProcessName()));
                sampleSections.add(new SampleSection(old));
                for (int i = 1; i < bigSampleDetail.getProductProcessList().size(); i++) {
                    SelectProcess.ProcessListBean current = new SelectProcess.ProcessListBean();
                    current.setPrice(bigSampleDetail.getProductProcessList().get(i).getPrice());
                    current.setStageId(bigSampleDetail.getProductProcessList().get(i).getStageId());
                    current.setId(bigSampleDetail.getProductProcessList().get(i).getId());
                    current.setName(bigSampleDetail.getProductProcessList().get(i).getStageName());
                    current.setProcessName(bigSampleDetail.getProductProcessList().get(i).getProcessName());
                    current.setIsOutsourcing(bigSampleDetail.getProductProcessList().get(i).getIsOutsourcing());
                    if (old.getStageId() != current.getStageId()) {
                        sampleSections.add(new SampleSection(true, current.getProcessName()));
                        sampleSections.add(new SampleSection(current));
                        old = current;
                    } else {
                        sampleSections.add(new SampleSection(current));
                    }
                }
                sampleSectionAdapter.notifyDataSetChanged();
            }

            if (bigSampleDetail.getSizeList().size() > 0) {
                tv_data.setVisibility(View.VISIBLE);
            } else {
                tv_data.setVisibility(View.GONE);
            }
            sampleSizeAdapter.notifyDataSetChanged();
            sampleRawInputAdapter.notifyDataSetChanged();
            String imgskey = bigSampleDetail.getImages();
            String imgs = bigSampleDetail.getImagesUrl();
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
            client = new Client();
            designer = new Designer();
            firstType = new FirstType();
            firstType.setId(bigSampleDetail.getProductCategoryId());
            firstType.setName(bigSampleDetail.getProductCategory1Name() + " " + bigSampleDetail.getProductCategory2Name());
            client.setId(bigSampleDetail.getClientRecordId());
            client.setName(bigSampleDetail.getClientRecordName());
            designer.setId(bigSampleDetail.getDesignerBy());
            designer.setName(bigSampleDetail.getDesignerName());
            tv_client.setText(client.getName());
            tv_designer.setText(designer.getName());
            tv_style.setText(firstType.getName());
            et_color.setText(bigSampleDetail.getColor());
            et_no1.setText(bigSampleDetail.getPatternNo());
            et_no2.setText(bigSampleDetail.getSampleNo());

        }


    }
}
