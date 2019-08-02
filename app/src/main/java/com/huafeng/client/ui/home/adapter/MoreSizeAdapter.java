package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.FirstSize;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 17:30
 * 邮箱：771548229@qq..com
 */
public class MoreSizeAdapter extends BaseAdapter {
    Context  mContext;
    List<FirstSize.SizesBean> mFirstTypes;

    public MoreSizeAdapter(Context context, List<FirstSize.SizesBean> firstTypes) {
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
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_more_select,null);
        TextView tv_type=convertView.findViewById(R.id.tv_type);
        ImageView iv_check=convertView.findViewById(R.id.iv_check);
        FirstSize.SizesBean firstType=mFirstTypes.get(position);
        tv_type.setText(firstType.getName());
        if(mFirstTypes.get(position).isSelect()){
            iv_check.setImageResource(R.mipmap.ic_check);
        }else{
            iv_check.setImageResource(R.mipmap.ic_uncheck);
        }
        return convertView;
    }
}
