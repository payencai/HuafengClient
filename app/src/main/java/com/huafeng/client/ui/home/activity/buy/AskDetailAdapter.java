package com.huafeng.client.ui.home.activity.buy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

public class AskDetailAdapter extends BaseAdapter {
    Context context;
    List<AskDetail.RequisitionListBean> requisitionListBeans;

    public AskDetailAdapter(Context context, List<AskDetail.RequisitionListBean> requisitionListBeans) {
        this.context = context;
        this.requisitionListBeans = requisitionListBeans;
    }

    @Override
    public int getCount() {
        return requisitionListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return requisitionListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_ask_detail,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        TextView tv_num1=convertView.findViewById(R.id.tv_num);
        TextView tv_num2=convertView.findViewById(R.id.tv_num2);
        tv_name.setText(requisitionListBeans.get(position).getMaterialName());
        tv_num1.setText(requisitionListBeans.get(position).getQuantityDemanded()+"");
        tv_num2.setText(requisitionListBeans.get(position).getRealQuantity()+"");
        return convertView;
    }
}
