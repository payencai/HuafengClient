package com.huafeng.client.ui.home.activity.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

public class SelectProcessChildAdapter extends BaseAdapter {
    Context context;
    List<SelectProcess.ProcessListBean> sampleSizes;

    public SelectProcessChildAdapter(Context context, List<SelectProcess.ProcessListBean> sampleSizes) {
        this.context = context;
        this.sampleSizes = sampleSizes;
    }

    @Override
    public int getCount() {
        return sampleSizes.size();
    }

    @Override
    public Object getItem(int position) {
        return sampleSizes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SelectProcess.ProcessListBean sampleSize = sampleSizes.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_process_child, null);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        if(sampleSize.getPrice()!=null)
           tv_name.setText(sampleSize.getName()+"("+sampleSize.getPrice()+")");
        else{
            tv_name.setText(sampleSize.getName());
        }
        return convertView;
    }
}
