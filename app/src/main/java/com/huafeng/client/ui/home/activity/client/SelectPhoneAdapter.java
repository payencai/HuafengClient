package com.huafeng.client.ui.home.activity.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 17:30
 * 邮箱：771548229@qq..com
 */
public class SelectPhoneAdapter extends BaseAdapter {
    Context  mContext;
    List<String> phoneList;

    public SelectPhoneAdapter(Context context, List<String> phoneList) {
        mContext = context;
        this.phoneList = phoneList;
    }
    private OnTouchListener onTouchListener;
    public  interface OnTouchListener{
        void onDel(int pos);
        void onItem(int pos);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public int getCount() {
        return phoneList.size();
    }

    @Override
    public Object getItem(int position) {
        return phoneList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_single_select,null);
        TextView tv_type=convertView.findViewById(R.id.tv_name);
        Button button=convertView.findViewById(R.id.btnDelete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTouchListener.onDel(position);
//                phoneList.remove(position);
//                notifyDataSetChanged();
            }
        });
        tv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTouchListener.onItem(position);
            }
        });
        String name=phoneList.get(position);
        tv_type.setText(name);
        return convertView;
    }
}
