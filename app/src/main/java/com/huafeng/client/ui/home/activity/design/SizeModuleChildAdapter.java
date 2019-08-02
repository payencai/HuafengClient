package com.huafeng.client.ui.home.activity.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

public class SizeModuleChildAdapter extends BaseAdapter {
    Context context;
    List<SizeModule.SampleSizeInformationListBean> listBeans;

    public SizeModuleChildAdapter(Context context, List<SizeModule.SampleSizeInformationListBean> sampleSizeInformationListBeans) {
        this.context = context;
        this.listBeans = sampleSizeInformationListBeans;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_grey_bg,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        tv_name.setText(listBeans.get(position).getSizeInformationName());
        return convertView;
    }
}
