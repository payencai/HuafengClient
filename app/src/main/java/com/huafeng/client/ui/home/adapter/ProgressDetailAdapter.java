package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.DesignDetail;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 20:27
 * 邮箱：771548229@qq..com
 */
public class ProgressDetailAdapter extends BaseAdapter {
    Context mContext;
    List<DesignDetail.OperatorRecordListBean> mRecordListBeans;

    public ProgressDetailAdapter(Context context, List<DesignDetail.OperatorRecordListBean> recordListBeans) {
        mContext = context;
        mRecordListBeans = recordListBeans;
    }

    @Override
    public int getCount() {
        return mRecordListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecordListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_work_progress,null);
        TextView tv_time=convertView.findViewById(R.id.tv_time);
        TextView tv_title=convertView.findViewById(R.id.tv_title);
        TextView tv_content=convertView.findViewById(R.id.tv_content);
        tv_title.setText(mRecordListBeans.get(position).getTitle());
        tv_time.setText(mRecordListBeans.get(position).getTime());
        tv_content.setText(mRecordListBeans.get(position).getContent());
        return convertView;
    }
}
