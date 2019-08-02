package com.huafeng.client.ui.home.activity.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class SelectSampleProcessAdapter extends BaseAdapter {
    Context context;
    List<SelectProcess> sampleSizes;

    public SelectSampleProcessAdapter(Context context, List<SelectProcess> sampleSizes) {
        this.context = context;
        this.sampleSizes = sampleSizes;
    }
    private  OnChildSelectListener onChildSelectListener;
    public interface OnChildSelectListener{
        void onSelect(int parent,int child);
    }



    public void setOnChildSelectListener(OnChildSelectListener onChildSelectListener) {
        this.onChildSelectListener = onChildSelectListener;
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
        SelectProcess sampleSize = sampleSizes.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_select_process, null);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        tv_name.setText(sampleSize.getName());
        MyGridView lv_child = convertView.findViewById(R.id.lv_child);
        List<SelectProcess.ProcessListBean> processListBeans = new ArrayList<>();
        if (sampleSize.getProcessList() != null)
            processListBeans.addAll(sampleSize.getProcessList());
        SelectProcessChildAdapter selectProcessChildAdapter = new SelectProcessChildAdapter(context, processListBeans);
        lv_child.setAdapter(selectProcessChildAdapter);
        lv_child.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                onChildSelectListener.onSelect(position,pos);
            }
        });
        return convertView;
    }
}
