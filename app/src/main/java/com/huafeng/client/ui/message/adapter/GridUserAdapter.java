package com.huafeng.client.ui.message.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.huafeng.client.R;
import com.huafeng.client.ui.message.model.GroupDetail;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/13 17:00
 * 邮箱：771548229@qq..com
 */
public class GridUserAdapter extends BaseAdapter {
    Context mContext;
    List<GroupDetail.CrowdUserListBean> mContacts;

    public GridUserAdapter(Context context, List<GroupDetail.CrowdUserListBean> contacts) {
        mContext = context;
        mContacts = contacts;
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_group_user,null);
        GroupDetail.CrowdUserListBean crowdUserListBean=mContacts.get(position);
        ImageView iv_head=convertView.findViewById(R.id.iv_head);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        if(!TextUtils.isEmpty(crowdUserListBean.getHeadPortraitUrl())){
            Glide.with(mContext)
                    .load(crowdUserListBean.getHeadPortraitUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_head);
        }else{
            Glide.with(mContext)
                    .load("https://www.7duba.com/uploads/avatars/default-avatar.png")
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_head);
        }

        tv_name.setText(crowdUserListBean.getNickname()+"");
        return convertView;
    }
}
