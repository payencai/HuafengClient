package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huafeng.client.R;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 20:49
 * 邮箱：771548229@qq..com
 */
public class PhotoAdapter extends BaseAdapter {
    Context mContext;
    List<String> images;

    public PhotoAdapter(Context context, List<String> images) {
        mContext = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_photo,null);
        ImageView iv_logo=convertView.findViewById(R.id.iv_logo);
        Glide.with(mContext).load(images.get(position)).into(iv_logo);
        return convertView;
    }
}
