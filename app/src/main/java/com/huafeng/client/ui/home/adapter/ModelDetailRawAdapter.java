package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.ModelDetail;

import java.util.List;

public class ModelDetailRawAdapter extends BaseAdapter {
    Context context;
    List<ModelDetail.MaterialListBean> materialListBeans;

    public ModelDetailRawAdapter(Context context, List<ModelDetail.MaterialListBean> materialListBeans) {
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
        convertView= LayoutInflater.from(context).inflate(R.layout.item_detail_raw,null);
        TextView tv_value=convertView.findViewById(R.id.tv_value);
        TextView tv_number=convertView.findViewById(R.id.tv_number);
        if(materialListBeans.get(position).getRawMaterialId()==-1){
            tv_value.setText(materialListBeans.get(position).getRawMaterialName()+"(新)");
        }else{
            tv_value.setText(materialListBeans.get(position).getRawMaterialName());
        }

        tv_number.setText(materialListBeans.get(position).getQuantity()+"");
        return convertView;
    }
}
