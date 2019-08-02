package com.huafeng.client.ui.message.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
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
import com.huafeng.client.tools.CheckDoubleClick;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.message.adapter.GridUserAdapter;
import com.huafeng.client.ui.message.model.GroupDetail;
import com.hyphenate.chat.EMClient;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupDetailActivity extends BaseActivity {
    @BindView(R.id.gv_user)
    GridView gv_user;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.iv_delete)
    ImageView iv_delete;
    @BindView(R.id.iv_pen)
    ImageView iv_pen;
    @BindView(R.id.tv_name)
    TextView tv_name;
    String head;
    String nickname;
    GridUserAdapter mGridUserAdapter;
    List<GroupDetail.CrowdUserListBean> mCrowdUserListBeans;
    String id;
    GroupDetail groupDetail;
    boolean isAdmin = false;

    @OnClick({R.id.iv_head, R.id.iv_back, R.id.rl_bottom,R.id.rl_add,R.id.rl_del})
    void OnClick(View view) {
        Intent intent;
        if(CheckDoubleClick.isFastDoubleClick()){
            return;
        }
        switch (view.getId()) {
            case R.id.rl_del:
                intent = new Intent(GroupDetailActivity.this, GroupUserDeleteActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, 3);
                break;
            case R.id.rl_add:
                intent = new Intent(GroupDetailActivity.this, GroupInvateActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, 2);
                break;
            case R.id.rl_bottom:
                if (isAdmin) {
                    delCrow();
                } else {
                    quitCrow();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_head:
                if (isAdmin)
                    openPhoto(1);
                break;


        }
    }

    private void quitCrow() {
        HttpParams params = new HttpParams();
        params.put("crowdId", groupDetail.getId());
        NetUtils.getInstance().post(Api.Huanxin.quitCrowdByCrowdId, MyApp.token, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("退出成功");
                        EMClient.getInstance().chatManager().deleteConversation(groupDetail.getId() + "", true);
                        EventBus.getDefault().post(new MsgEvent(800));
                        finish();
                    } else {
                        String msg = jsonObject.getString("message");
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
    }

    private void delCrow() {
        HttpParams params = new HttpParams();
        params.put("crowdId", groupDetail.getId());
        NetUtils.getInstance().post(Api.Huanxin.dismissCrowdByCrowdId, MyApp.token, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("解散成功");
                        EMClient.getInstance().chatManager().deleteConversation(groupDetail.getId() + "", true);
                        EventBus.getDefault().post(new MsgEvent(800));
                        finish();
                    } else {
                        String msg = jsonObject.getString("message");
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        showLoading(500);
        initView();

    }

    KProgressHUD kProgressHUD;

    private void showLoading(long time) {
        kProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(1000)
                .setCancellable(true)
                .setDimAmount(0.5f)
                .show();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, time);//3秒后执行TimeTask的run方法
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
                    ToastUtils.showShort("名称不能为空！");
                    return;
                }
                updateUserInfo();

                tv_name.setText(nickname);

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

    private void openPhoto(int code) {
        PictureSelector.create(GroupDetailActivity.this)
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
        if (requestCode == 1 && data != null) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            String path = selectList.get(0).getPath();
            File file = new File(path);
            upLoadImg(file, path);
        }
        if (requestCode > 1) {
            mCrowdUserListBeans.clear();
            getDetail();
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
                    Glide.with(GroupDetailActivity.this)
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
        HttpParams params = new HttpParams();
        params.put("crowdId", groupDetail.getId());
        if (!TextUtils.isEmpty(head))
            params.put("image", head);
        if (!TextUtils.isEmpty(nickname))
            params.put("crowdName", nickname);
        NetUtils.getInstance().post(Api.Huanxin.updateCrowds, MyApp.token, params, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    if (code == 0) {
                        ToastUtils.showShort("修改成功");
                        head = "";

                        nickname = "";
                        EventBus.getDefault().post(new MsgEvent(701));
                    } else {
                        String msg = jsonObject.getString("message");
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
    }

    private void initView() {
        mCrowdUserListBeans = new ArrayList<>();
        mGridUserAdapter = new GridUserAdapter(this, mCrowdUserListBeans);
        gv_user.setAdapter(mGridUserAdapter);
        iv_pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateNickDialog();
            }
        });
        getDetail();
    }

    private void getDetail() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("crowdId", id);
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getCrowdDetailsByCrowdId, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        groupDetail = new Gson().fromJson(data.toString(), GroupDetail.class);
                        setUI();
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

    private void setUI() {
        if (!TextUtils.isEmpty(groupDetail.getImageUrl()))
            Glide.with(this)
                    .load(groupDetail.getImageUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_head);
        else{
            Glide.with(this)
                    .load("http://icons.iconarchive.com/icons/blackvariant/button-ui-system-folders-drives/1024/Group-icon.png")
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_head);
        }
        mCrowdUserListBeans.addAll(groupDetail.getCrowdUserList());
        mGridUserAdapter.notifyDataSetChanged();
        tv_name.setText(groupDetail.getCrowdName());
        if (!MyApp.getUserInfo().getHxUsername().equals(groupDetail.getCrowdUserId() + "")) {
            iv_delete.setVisibility(View.GONE);
            iv_add.setVisibility(View.GONE);
            iv_pen.setVisibility(View.GONE);
        } else {
            isAdmin = true;
        }

    }
}
