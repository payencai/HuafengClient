package com.huafeng.client.ui.message.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.tools.BitmapUtil;
import com.huafeng.client.tools.StringUtils;
import com.huafeng.client.ui.contacts.model.Contacts;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.huafeng.client.ui.message.adapter.GridheaderAdapter;
import com.huafeng.client.ui.message.adapter.SelectContactsAdapter;
import com.huafeng.client.view.GridViewForScrollView;
import com.huafeng.client.view.ListViewForScrollView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupOptions;
import com.hyphenate.exceptions.HyphenateException;
import com.lzy.okgo.model.HttpParams;
import com.othershe.combinebitmap.CombineBitmap;
import com.othershe.combinebitmap.layout.WechatLayoutManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateGroupActivity extends BaseActivity {
    @BindView(R.id.lv_contact)
    ListViewForScrollView lv_contact;
    @BindView(R.id.gv_user)
    GridViewForScrollView gv_user;

    ImageView imageView;
    SelectContactsAdapter mSelectContactsAdapter;
    List<Contacts> mContacts;
    GridheaderAdapter mGridheaderAdapter;
    List<String> headerList;
    List<String> userIdList;

    String groupHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createGroup();
            }
        });

        imageView =new ImageView(this);
        BitmapUtil.layoutView(imageView,200,200);
        initView();
    }

    private void initView() {
        headerList = new ArrayList<>();
        userIdList = new ArrayList<>();
        mGridheaderAdapter = new GridheaderAdapter(this, headerList);
        gv_user.setAdapter(mGridheaderAdapter);
        mContacts = new ArrayList<>();
        mSelectContactsAdapter = new SelectContactsAdapter(this, mContacts);

        lv_contact.setAdapter(mSelectContactsAdapter);
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacts contacts = mContacts.get(position);
                if (contacts.isCheck()) {
                    mContacts.get(position).setCheck(false);
                    headerList.remove(contacts.getHeadPortraitUrl());
                } else {
                    mContacts.get(position).setCheck(true);
                    headerList.add(contacts.getHeadPortraitUrl());
                }
                mGridheaderAdapter.notifyDataSetChanged();
                mSelectContactsAdapter.notifyDataSetChanged();
            }
        });
        getMyContacts();
    }


    private void loadWechatBitmap() {
        String[] urls = new String[headerList.size()];
        for (int i = 0; i < headerList.size(); i++) {
            urls[i] = headerList.get(i);
        }
        CombineBitmap.init(CreateGroupActivity.this)
                .setLayoutManager(new WechatLayoutManager())
                .setSize(180)
                .setGap(3)
                .setGapColor(Color.parseColor("#E8E8E8"))
                .setUrls(urls)
                .setImageView(imageView)
                .build();

        Bitmap bitmap=BitmapUtil.loadBitmapFromView(imageView);
        if(bitmap!=null){
            String path = BitmapUtil.saveBitmap(this, bitmap);
            upLoadImg(new File(path));
        }

    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

    private void upLoadImg(File file) {
        NetUtils.getInstance().upLoadImage(Api.Image.uploadImage, file, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    groupHead = jsonObject.getString("data");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void getUserIds() {


        for (int i = 0; i < mContacts.size(); i++) {
            if (mContacts.get(i).isCheck()) {
                userIdList.add(mContacts.get(i).getUserId() + "");
            }
        }
        String[] userIds = new String[userIdList.size()];
        for (int i = 0; i < userIdList.size(); i++) {
            userIds[i] = userIdList.get(i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] members = new String[]{""};//群组初始成员，如果只有自己传空数组即可
                final String groupName = "群" + System.currentTimeMillis();
                String desc = "哈哈哈";
                try {
                    EMGroupOptions option = new EMGroupOptions();
                    option.maxUsers = 100;

                    option.inviteNeedConfirm = true;
                    String reason = "理由";
                    option.style = EMGroupManager.EMGroupStyle.EMGroupStylePrivateMemberCanInvite;
                    EMGroup emGroup = EMClient.getInstance().groupManager().createGroup(groupName, desc, userIds, reason, option);
                    addToGroup(emGroup.getGroupId(), emGroup.getGroupName());
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            ToastUtils.showShort(e.getDescription());
                        }
                    });
                }

            }
        }).start();


    }

    private void addToGroup(String groupId, String groupName) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("crowdId", groupId);
        httpParams.put("crowdName", groupName);
        httpParams.put("image", groupHead);
        userIdList.add(0, MyApp.getUserInfo().getHxUsername());
        String ids = StringUtils.listToString2(userIdList, ',');
        httpParams.put("userIds", ids);
        //httpParams.put("image",);
        Log.e("userIds", ids);
        NetUtils.getInstance().post(Api.Huanxin.addCrowd, MyApp.token, httpParams, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e(response);
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        ToastUtils.showShort("创建成功");
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

    private void createGroup() {
        //loadWechatBitmap();
        getUserIds();

    }

    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    public void notifyDataSetChanged(ListView listView, int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            mSelectContactsAdapter.getView(position, view, listView);
        }
    }

    private void getMyContacts() {
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getMyFriendList, new OnMessageReceived() {
            @Override
            public void onSuccess(String response) {

                LogUtils.e(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code = jsonObject.getInt("resultCode");
                    String msg = jsonObject.getString("message");
                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            Contacts contacts = new Gson().fromJson(item.toString(), Contacts.class);
                            mContacts.add(contacts);
                        }
                        mSelectContactsAdapter.notifyDataSetChanged();
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
}
