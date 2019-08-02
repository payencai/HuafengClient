package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.FirstType;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 17:30
 * 邮箱：771548229@qq..com
 */
public class FirstTypeAdapter extends BaseAdapter {
    Context  mContext;
    List<FirstType> mFirstTypes;
    int pos=0;
    boolean isShow=true;
    public FirstTypeAdapter(Context context, List<FirstType> firstTypes) {
        mContext = context;
        mFirstTypes = firstTypes;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
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
        FirstType firstType=mFirstTypes.get(position);
        tv_type.setText(firstType.getName());

        if(pos==position){
            if(isShow)
               tv_type.setTextColor(mContext.getResources().getColor(R.color.color_blue));
        }else{
            tv_type.setTextColor(mContext.getResources().getColor(R.color.color_333));
        }
        return convertView;
    }
}
