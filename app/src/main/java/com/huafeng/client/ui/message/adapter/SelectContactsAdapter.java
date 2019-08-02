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
import com.huafeng.client.ui.contacts.model.Contacts;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/13 17:00
 * 邮箱：771548229@qq..com
 */
public class SelectContactsAdapter extends BaseAdapter {
    Context mContext;
    List<Contacts> mContacts;

    public SelectContactsAdapter(Context context, List<Contacts> contacts) {
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_select_contacts, null);
        Contacts contacts = mContacts.get(position);
        ImageView iv_check = convertView.findViewById(R.id.iv_check);
        TextView tv_nick = convertView.findViewById(R.id.tv_nick);
        CircleImageView iv_head = convertView.findViewById(R.id.iv_head);
        if (!TextUtils.isEmpty(contacts.getHeadPortraitUrl())) {
            Glide.with(mContext).load(contacts.getHeadPortraitUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
        } else {
            Glide.with(mContext).load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png").apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
        }
        if (contacts.isCheck()) {
            iv_check.setImageResource(R.mipmap.ic_check);
        } else {
            iv_check.setImageResource(R.mipmap.ic_uncheck);
        }
        tv_nick.setText(contacts.getNickname());
        return convertView;
    }
}
