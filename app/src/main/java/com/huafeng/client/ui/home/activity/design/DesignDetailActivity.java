package com.huafeng.client.ui.home.activity.design;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.huafeng.client.ui.home.activity.ModelDataDetailActivity;
import com.huafeng.client.ui.home.adapter.PhotoAdapter;
import com.huafeng.client.ui.home.adapter.ProgressDetailAdapter;
import com.huafeng.client.ui.home.model.DesignDetail;
import com.huafeng.client.view.GridViewForScrollView;
import com.huafeng.client.view.ListViewForScrollView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesignDetailActivity extends BaseActivity {
    @BindView(R.id.lv_progress)
    ListViewForScrollView lv_progress;
    @BindView(R.id.gv_photo)
    GridViewForScrollView gv_photo;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.ll_content2)
    LinearLayout ll_content2;
    @BindView(R.id.tv_name2)
    TextView tv_name2;
    @BindView(R.id.iv_design2)
    ImageView iv_design2;
    @BindView(R.id.tv_style2)
    TextView tv_style2;
    @BindView(R.id.tv_designer2)
    TextView tv_designer2;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_design)
    ImageView iv_design;
    @BindView(R.id.tv_style)
    TextView tv_style;
    @BindView(R.id.tv_designer)
    TextView tv_designer;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_stop)
    TextView tv_stop;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    PhotoAdapter mPhotoAdapter;
    ArrayList<String> images;
    ProgressDetailAdapter mProgressDetailAdapter;
    DesignDetail mDesignDetail;
    List<DesignDetail.OperatorRecordListBean> operatorRecordListBeans;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_detail);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        id = getIntent().getIntExtra("id", 0);
        kProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("加载中")
                .setCancellable(false)
                .show();
        initView();
    }

    private void showConfirmDialog(int type) {
        final Dialog dialog = new Dialog(this, R.style.dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_stop, null);

        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_confirm);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckDoubleClick.isFastDoubleClick()){
                    return;
                }
                dialog.dismiss();
                switch (type) {
                    case 1:
                        stop();
                        break;
                    case 2:
                        sewingStatus();
                        break;
                    case 3:
                        washingStatus();
                        break;
                    case 4:
                        afterStatus();
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
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (display.getWidth() * 0.7);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }

    private void getAuth() {

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

                            if (mDesignDetail.getPatternMakingFlow().getStatus() == 1) {
                                if (authId == 1011 || (mDesignDetail.getPatternMakingFlow().getDesignById() == MyApp.getUserInfo().getEmployeeRecordId())) {
                                    tv_submit.setVisibility(View.VISIBLE);
                                    tv_stop.setVisibility(View.VISIBLE);
                                }

                            } else if (mDesignDetail.getPatternMakingFlow().getStatus() <= 5) {
                                tv_submit.setText("下一步");
                                if (mDesignDetail.getPatternMakingFlow().getStatus() == 2 && authId == 1013) {
                                    tv_submit.setVisibility(View.VISIBLE);
                                }
                                if (mDesignDetail.getPatternMakingFlow().getStatus() == 3 && authId == 1014) {
                                    tv_submit.setVisibility(View.VISIBLE);
                                }
                                if (mDesignDetail.getPatternMakingFlow().getStatus() == 4 && authId == 1015) {
                                    tv_submit.setVisibility(View.VISIBLE);
                                }
                                if (mDesignDetail.getPatternMakingFlow().getStatus() == 5) {
                                    if (mDesignDetail.getPatternMakingFlow().getDesignById() == MyApp.getUserInfo().getEmployeeRecordId()) {
                                        tv_submit.setVisibility(View.VISIBLE);
                                    }
                                }
                                if (authId == 1011) {
                                    tv_stop.setVisibility(View.VISIBLE);
                                    tv_submit.setVisibility(View.VISIBLE);
                                    break;
                                }

                            }


                        }
                        if (mDesignDetail.getPatternMakingFlow().getStatus() == -1 || mDesignDetail.getPatternMakingFlow().getStatus() == 6) {
                            tv_stop.setVisibility(View.GONE);
                            tv_submit.setVisibility(View.GONE);
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

    @OnClick({R.id.tv_submit, R.id.tv_stop, R.id.iv_back})
    void onClick(View view) {
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                switch (mDesignDetail.getPatternMakingFlow().getStatus()) {
                    case 1:
                        Intent intent = new Intent(DesignDetailActivity.this, AddModelCardActivity.class);
                        intent.putExtra("id", mDesignDetail.getPatternMakingFlow().getId());
                        startActivityForResult(intent, 1);
                        break;
                    case 2:
                        showDialog();
                        break;
                    case 3:
                        showConfirmDialog(2);
                        break;
                    case 4:
                        showConfirmDialog(3);
                        break;
                    case 5:
                        showConfirmDialog(4);

                        break;


                }
                break;
            case R.id.tv_stop:
                showConfirmDialog(1);
                break;
        }
    }

    private void patternStatus(String patternNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("patternNo", patternNo);
        params.put("flowId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Pattern.pattern, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("纸样阶段已完成");
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
    }

    private void sewingStatus() {
        Map<String, Object> params = new HashMap<>();
        params.put("flowId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Pattern.sewing, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("车间阶段已完成");
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
    }

    private void washingStatus() {
        Map<String, Object> params = new HashMap<>();
        params.put("flowId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Pattern.washing, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("洗水阶段已完成");
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
    }

    private void afterStatus() {
        Map<String, Object> params = new HashMap<>();
        params.put("flowId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Pattern.afterfinish, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("后整阶段已完成");
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
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_input_number, null);
        final EditText et_season = (EditText) view.findViewById(R.id.et_season);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String patternNo = et_season.getEditableText().toString();
                if (TextUtils.isEmpty(patternNo)) {
                    ToastUtils.showShort("请输入纸样编号");
                    return;
                }
                patternStatus(patternNo);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getDetail();

    }

    private void stop() {
        Map<String, Object> params = new HashMap<>();
        params.put("flowId", id);
        String json = new Gson().toJson(params);
        NetUtils.getInstance().post(Api.Pattern.stop, json, MyApp.token, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("成功终止");
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
    }

    private void initView() {
        operatorRecordListBeans = new ArrayList<>();
        images = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(this, images);
        gv_photo.setAdapter(mPhotoAdapter);
        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoUtil.seeBigPhoto(DesignDetailActivity.this, position, images, view);
            }
        });
        mProgressDetailAdapter = new ProgressDetailAdapter(this, operatorRecordListBeans);
        lv_progress.setAdapter(mProgressDetailAdapter);
        ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesignDetailActivity.this, ModelDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        ll_content2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesignDetailActivity.this, ModelDataDetailActivity.class);
                intent.putExtra("id", mDesignDetail.getSampleVo().getId());
                startActivityForResult(intent, 3);
            }
        });
        getDetail();
    }

    KProgressHUD kProgressHUD;

    private void initData() {
        images.clear();
        operatorRecordListBeans.clear();
        String imgs = "";
        if (mDesignDetail.getPatternMakingFlow() != null) {
            if (mDesignDetail.getPatternMakingFlow().getSampleId() != -1) {
                ll_content2.setVisibility(View.VISIBLE);
                String images = mDesignDetail.getSampleVo().getImagesUrl();
                if (!TextUtils.isEmpty(images)) {
                    if (images.contains(",")) {
                        Glide.with(this).load(images.split(",")[0]).into(iv_design2);
                    } else
                        Glide.with(this).load(images).into(iv_design2);
                }

                tv_name2.setText(mDesignDetail.getSampleVo().getSampleNo());
                tv_designer2.setText("设计师："+mDesignDetail.getSampleVo().getDesignerName());
                tv_style2.setText("款式：" + mDesignDetail.getSampleVo().getProductCategory1Name() + " " + mDesignDetail.getSampleVo().getProductCategory2Name());
            }
            tv_content.setText(mDesignDetail.getPatternMakingFlow().getRequirement());
            imgs = mDesignDetail.getPatternMakingFlow().getImagesUrl();
            tv_designer.setText("设计师："+mDesignDetail.getPatternMakingFlow().getDesignByName());
            if (mDesignDetail.getPatternMakingFlow().getStatus() >= 2 || (mDesignDetail.getPatternMakingFlow().getStatus() == -1 && !TextUtils.isEmpty(mDesignDetail.getPatternMakingFlow().getDesignTime()))) {
                ll_content.setVisibility(View.VISIBLE);
                String images = mDesignDetail.getPatternMakingCard().getImagesUrl();
                if (!TextUtils.isEmpty(images)) {
                    if (images.contains(",")) {
                        Glide.with(this).load(images.split(",")[0]).into(iv_design);
                    } else
                        Glide.with(this).load(images).into(iv_design);
                }

                tv_name.setText(mDesignDetail.getPatternMakingCard().getPatternMakingNo());
                tv_time.setText("创建时间：" + mDesignDetail.getPatternMakingCard().getGmtCreate());
                tv_style.setText("款式：" + mDesignDetail.getPatternMakingCard().getProductCategory1Name() + " " + mDesignDetail.getPatternMakingCard().getProductCategory2Name());
            }
        }
        if (!TextUtils.isEmpty(imgs)) {
            images.addAll(StringUtils.StringToArrayList(imgs, ","));
        }
        if (mDesignDetail.getPatternMakingCard() != null) {
            tv_title.setText(mDesignDetail.getPatternMakingCard().getPatternMakingNo());
        }

        mPhotoAdapter.notifyDataSetChanged();
        operatorRecordListBeans.addAll(mDesignDetail.getOperatorRecordList());
        mProgressDetailAdapter.notifyDataSetChanged();
        getAuth();
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        NetUtils.getInstance().get(MyApp.token, Api.Pattern.getFlow, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                if (kProgressHUD != null && kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        mDesignDetail = new Gson().fromJson(data.toString(), DesignDetail.class);
                        initData();

                    } else {
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
                LogUtils.e(error);
                if (kProgressHUD != null && kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
            }
        });
    }
}
