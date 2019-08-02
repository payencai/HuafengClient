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

public class SelectBigModelAdapter extends BaseAdapter {
    int pos=0;
    Context context;
    List<BigModel> sampleSizes;

    public SelectBigModelAdapter(Context context, List<BigModel> sampleSizes) {
        this.context = context;
        this.sampleSizes = sampleSizes;
    }
    private OnSeeLisenter onSeeLisenter;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public OnSeeLisenter getOnSeeLisenter() {
        return onSeeLisenter;
    }

    public void setOnSeeLisenter(OnSeeLisenter onSeeLisenter) {
        this.onSeeLisenter = onSeeLisenter;
    }

    public interface OnSeeLisenter{
        void onSee(int pos);
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
        BigModel bigModel = sampleSizes.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_select_big_model, null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        TextView tv_time=convertView.findViewById(R.id.tv_time);
        TextView tv_see=convertView.findViewById(R.id.tv_see);
        tv_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSeeLisenter.onSee(position);
            }
        });
        tv_name.setText(bigModel.getName());
        tv_time.setText(bigModel.getGmtCreate());
        ImageView iv_check=convertView.findViewById(R.id.iv_check);
        if(pos==position){
            iv_check.setImageResource(R.mipmap.ic_check);
        }else{
            iv_check.setImageResource(R.mipmap.ic_uncheck);
        }
        return convertView;
    }
}
