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

public class ModelSizeChildAdapter extends BaseAdapter {
    Context context;
    List<ModelDetail.SizeListBean.SampleSizeInformationListBean> materialListBeans;

    public ModelSizeChildAdapter(Context context, List<ModelDetail.SizeListBean.SampleSizeInformationListBean> materialListBeans) {
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
        convertView= LayoutInflater.from(context).inflate(R.layout.item_design_type,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        View view=convertView.findViewById(R.id.line);

        TextView tv_number=convertView.findViewById(R.id.tv_number);
        tv_name.setText(materialListBeans.get(position).getSizeInformationName());
        tv_number.setText(materialListBeans.get(position).getQuantity()+"");
        return convertView;
    }
}
