package com.huafeng.client.ui.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.huafeng.client.R;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/13 17:00
 * 邮箱：771548229@qq..com
 */
public class GridheaderAdapter extends BaseAdapter {
    Context mContext;
    List<String> mContacts;

    public GridheaderAdapter(Context context, List<String> contacts) {
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
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_head_select,null);

        ImageView iv_head=convertView.findViewById(R.id.iv_head);
        Glide.with(mContext)
                .load(mContacts.get(position))
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iv_head);

        return convertView;
    }
}
