package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.WashProcess;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 17:30
 * 邮箱：771548229@qq..com
 */
public class WashProcessAdapter extends BaseAdapter {
    Context  mContext;
    List<WashProcess> mFirstTypes;

    public WashProcessAdapter(Context context, List<WashProcess> firstTypes) {
        mContext = context;
        mFirstTypes = firstTypes;
    }



    @Override
    public int getCount() {
        return mFirstTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return mFirstTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_list_type,null);
        TextView tv_type=convertView.findViewById(R.id.tv_type);
        WashProcess firstType=mFirstTypes.get(position);
        tv_type.setText(firstType.getName());

        return convertView;
    }
}
