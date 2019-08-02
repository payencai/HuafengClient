package com.huafeng.client.ui.start.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.model.MsgEvent;
import com.huafeng.client.net.Api;
import com.huafeng.client.net.NetUtils;
import com.huafeng.client.net.OnMessageReceived;
import com.huafeng.client.ui.contacts.activity.NewFriendsActivity;
import com.huafeng.client.ui.contacts.model.Contacts;
import com.huafeng.client.ui.contacts.model.FriendRequest;
import com.huafeng.client.ui.message.activity.MyGroupActivity;
import com.huafeng.client.ui.message.activity.SingleChatActivity;
import com.huafeng.client.ui.start.adapter.ContactAdapter;
import com.huafeng.client.ui.start.adapter.MenuEntity;
import com.huafeng.client.view.sidebar.IndexableAdapter;
import com.huafeng.client.view.sidebar.IndexableHeaderAdapter;
import com.huafeng.client.view.sidebar.IndexableLayout;
import com.hyphenate.easeui.utils.MyUserProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewContactsFragment extends Fragment {

    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;

    @BindView(R.id.et_content)
    EditText et_content;

    ContactAdapter mAdapter;
    List<Contacts> mDatas;
    TextView tvNum;
    int num = 0;
    boolean isback = false;
    MenuHeaderAdapter menuHeaderAdapter;

    public NewContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnMsg(MsgEvent msgEvent) {
        if (msgEvent.getmMsg() == 900) {
            refresh();
        }
        if(msgEvent.getmMsg()==700){
            isback = true;
            num = 0;
            getNum();
            mDatas.clear();
            getMyFriend();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_contacts, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        getNum();

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                if (TextUtils.isEmpty(value)) {
                    refresh();
                } else {
                    List<Contacts> oldContacts = new ArrayList<>();
                    oldContacts.addAll(mDatas);
                    mDatas.clear();
                    for (int i = 0; i < oldContacts.size(); i++) {
                        if (oldContacts.get(i).getNickname().contains(value)) {
                            mDatas.add(oldContacts.get(i));
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    private void refresh() {
        num=0;
        mDatas.clear();
        getMyFriend();
        isback = true;
        getNum();
    }





    private void getNum() {
        NetUtils.getInstance().get(MyApp.token, Api.Huanxin.getFriendApplyList, new OnMessageReceived() {
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
                            FriendRequest contacts = new Gson().fromJson(item.toString(), FriendRequest.class);
                            if (contacts.getState() == 0)
                                num++;
                        }
                        if (!isback) {
                            initView();
                        } else {
                            if (tvNum != null)
                                if (num > 0) {
                                    tvNum.setText(num + "");
                                    tvNum.setVisibility(View.VISIBLE);
                                } else {
                                    tvNum.setVisibility(View.GONE);
                                }
                        }

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
        mDatas = new ArrayList<>();
        setList();
        getMyFriend();

    }

    private void getMyFriend() {
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
                            MyUserProvider.getInstance().setUser(contacts.getUserId() + "", contacts.getNickname(), contacts.getHeadPortraitUrl(), contacts.getHeadPortrait());
                            mDatas.add(contacts);
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                ToastUtils.showLong("获取联系人失败！");
            }
        });
    }

    private void setList() {
        mAdapter = new ContactAdapter(getContext());
        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<Contacts>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, Contacts entity) {
                Contacts contacts = mDatas.get(originalPosition);
                Intent intent = new Intent(getContext(), SingleChatActivity.class);
                intent.putExtra("id", contacts.getUserId() + "");
                intent.putExtra("name", contacts.getNickname());
                startActivity(intent);
            }
        });
        indexableLayout.setLayoutManager(new LinearLayoutManager(getContext()));
        indexableLayout.setOverlayStyle_MaterialDesign(Color.RED);
        indexableLayout.setCompareMode(IndexableLayout.MODE_ALL_LETTERS);
        mAdapter.setDatas(mDatas);
        indexableLayout.setAdapter(mAdapter);
        menuHeaderAdapter = new MenuHeaderAdapter("↑", null, initMenuDatas());
        menuHeaderAdapter.setOnItemHeaderClickListener(new IndexableHeaderAdapter.OnItemHeaderClickListener<MenuEntity>() {
            @Override
            public void onItemClick(View v, int currentPosition, MenuEntity entity) {
                if (entity.getMenuId() == 1) {
                    startActivityForResult(new Intent(getContext(), NewFriendsActivity.class), 3);
                } else {
                    startActivity(new Intent(getContext(), MyGroupActivity.class));
                }
                // ToastUtil.showShort(PickContactActivity.this, entity.getMenuTitle());
            }
        });
        indexableLayout.addHeaderAdapter(menuHeaderAdapter);


    }

    /**
     * 自定义的MenuHeader
     */
    class MenuHeaderAdapter extends IndexableHeaderAdapter<MenuEntity> {
        private static final int TYPE = 1;

        public MenuHeaderAdapter(String index, String indexTitle, List<MenuEntity> datas) {
            super(index, indexTitle, datas);
        }

        @Override
        public int getItemViewType() {
            return TYPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
            return new VH(LayoutInflater.from(getContext()).inflate(R.layout.header_contact_menu, parent, false));
        }

        @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, MenuEntity entity, List<Object> payloads) {
            VH vh = (VH) holder;
            vh.tv.setText(entity.getMenuTitle());
            vh.img.setImageResource(entity.getMenuIconRes());
            if (entity.getMenuId() == 1) {
                if (num > 0) {
                    tvNum = vh.tvNum;
                    vh.tvNum.setText(num + "");
                    vh.tvNum.setVisibility(View.VISIBLE);
                } else {
                    vh.tvNum.setVisibility(View.GONE);
                }
            }


        }

        private class VH extends RecyclerView.ViewHolder {
            private TextView tv;
            private TextView tvNum;
            private ImageView img;

            public VH(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv_title);
                tvNum = (TextView) itemView.findViewById(R.id.tv_num);
                img = (ImageView) itemView.findViewById(R.id.img);
            }
        }
    }

    private List<MenuEntity> initMenuDatas() {
        List<MenuEntity> list = new ArrayList<>();
        list.add(new MenuEntity(1, "新加朋友", R.drawable.ic_friend));
        list.add(new MenuEntity(2, "群聊", R.drawable.ic_group));

        return list;
    }
}
