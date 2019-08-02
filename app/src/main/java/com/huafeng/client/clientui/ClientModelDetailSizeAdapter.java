package com.huafeng.client.clientui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.view.MyGridView;

import java.util.List;

public class ClientModelDetailSizeAdapter extends BaseAdapter {
    Context context;
    List<ClientModelDetail.SizeListBean> sizeListBeans;

    public ClientModelDetailSizeAdapter(Context context, List<ClientModelDetail.SizeListBean> materialListBeans) {
        this.context = context;
        this.sizeListBeans = materialListBeans;
    }

    @Override
    public int getCount() {
        return sizeListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return sizeListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClientModelDetail.SizeListBean sizeListBean=sizeListBeans.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.item_detail_size,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        MyGridView gv_child=convertView.findViewById(R.id.gv_child);
        ClientModelSizeChildAdapter modelSizeChildAdapter=new ClientModelSizeChildAdapter(context,sizeListBean.getSampleSizeInformationList());
        gv_child.setAdapter(modelSizeChildAdapter);
        tv_name.setText("尺码"+sizeListBean.getSizeName());
        return convertView;
    }
}
