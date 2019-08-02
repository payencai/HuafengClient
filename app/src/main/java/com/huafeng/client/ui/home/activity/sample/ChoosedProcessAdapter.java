package com.huafeng.client.ui.home.activity.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

public class ChoosedProcessAdapter extends BaseAdapter {
    Context context;
    List<SelectProcess.ProcessListBean> sampleSizes;

    public ChoosedProcessAdapter(Context context, List<SelectProcess.ProcessListBean> sampleSizes) {
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
        SelectProcess.ProcessListBean sampleSize = sampleSizes.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_process_child, null);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        ImageView iv_del=convertView.findViewById(R.id.iv_del);
        iv_del.setVisibility(View.VISIBLE);
        iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChildSelectListener.onSelect(position);
            }
        });
        if(sampleSize.getPrice()!=null)
           tv_name.setText(sampleSize.getProcessName()+"-"+sampleSize.getName()+"("+sampleSize.getPrice()+")");
        else{
            tv_name.setText(sampleSize.getProcessName()+"-"+sampleSize.getName());
        }
        return convertView;
    }
}
