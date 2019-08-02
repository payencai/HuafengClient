package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.order.ClothesSize;

import java.util.List;

public class SelectClothesSizeAdapter extends BaseAdapter {
    Context context;
    List<ClothesSize> materialListBeans;

    public SelectClothesSizeAdapter(Context context, List<ClothesSize> materialListBeans) {
        this.context = context;
        this.materialListBeans = materialListBeans;
    }

    @Override
    public int getCount() {
        return materialListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return materialListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_select_clothes,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        ImageView iv_check=convertView.findViewById(R.id.iv_check);
        if(materialListBeans.get(position).isCheck()){
            iv_check.setImageResource(R.mipmap.ic_check);
        }else{
            iv_check.setImageResource(R.mipmap.ic_uncheck);
        }
        tv_name.setText(materialListBeans.get(position).getName());
        return convertView;
    }
}
