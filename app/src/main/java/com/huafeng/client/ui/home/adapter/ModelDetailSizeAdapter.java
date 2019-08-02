package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.ModelDetail;
import com.huafeng.client.view.MyGridView;

import java.util.List;

public class ModelDetailSizeAdapter extends BaseAdapter {
    Context context;
    List<ModelDetail.SizeListBean> sizeListBeans;

    public ModelDetailSizeAdapter(Context context, List<ModelDetail.SizeListBean> materialListBeans) {
        this.context = context;
        this.sizeListBeans = materialListBeans;
    }

    @Override
    public int getCount() {
        return sizeListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return sizeListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelDetail.SizeListBean sizeListBean=sizeListBeans.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.item_detail_size,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        MyGridView gv_child=convertView.findViewById(R.id.gv_child);
        ModelSizeChildAdapter modelSizeChildAdapter=new ModelSizeChildAdapter(context,sizeListBean.getSampleSizeInformationList());
        gv_child.setAdapter(modelSizeChildAdapter);
        tv_name.setText("尺码"+sizeListBean.getSizeName());
        return convertView;
    }
}
