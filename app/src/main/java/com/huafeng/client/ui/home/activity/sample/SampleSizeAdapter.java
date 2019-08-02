package com.huafeng.client.ui.home.activity.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class SampleSizeAdapter extends BaseAdapter {
    Context context;
    List<SampleSize> sampleSizes;

    public SampleSizeAdapter(Context context, List<SampleSize> sampleSizes) {
        this.context = context;
        this.sampleSizes = sampleSizes;
    }
    private OnChildSelectListener onChildSelectListener;
    public interface OnChildSelectListener{
        void onSelect(int pos);
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
        SampleSize sampleSize = sampleSizes.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_model_size, null);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        RelativeLayout rl_del=convertView.findViewById(R.id.rl_del);
        rl_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChildSelectListener.onSelect(position);
            }
        });
        tv_name.setText("尺码"+sampleSizes.get(position).getSizeName());
        MyGridView gv_child = convertView.findViewById(R.id.gv_child);
        List<SampleSize.SampleSizeInformationListBean> sizeInformationListBeans = new ArrayList<>();
        if (sampleSize.getSampleSizeInformationList() != null)
            sizeInformationListBeans.addAll(sampleSize.getSampleSizeInformationList());
        SampleSizeInputAdapter sampleSizeInputAdapter = new SampleSizeInputAdapter(context, sizeInformationListBeans);
        gv_child.setAdapter(sampleSizeInputAdapter);
        return convertView;
    }
}
