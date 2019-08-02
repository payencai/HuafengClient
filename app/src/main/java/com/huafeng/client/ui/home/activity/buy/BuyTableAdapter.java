package com.huafeng.client.ui.home.activity.buy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

public class BuyTableAdapter extends BaseAdapter {
    Context context;
    List<BuyOrderDetail.NoteListBean.SupplierListBean> supplierListBeans;

    public BuyTableAdapter(Context context, List<BuyOrderDetail.NoteListBean.SupplierListBean> supplierListBeans) {
        this.context = context;
        this.supplierListBeans = supplierListBeans;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<BuyOrderDetail.NoteListBean.SupplierListBean> getSupplierListBeans() {
        return supplierListBeans;
    }

    public void setSupplierListBeans(List<BuyOrderDetail.NoteListBean.SupplierListBean> supplierListBeans) {
        this.supplierListBeans = supplierListBeans;
    }

    @Override
    public int getCount() {
        return supplierListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return supplierListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_buy_order_detail,null);
        BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean=supplierListBeans.get(position);
        TextView tvItem1=convertView.findViewById(R.id.tv_item1);
        TextView tvItem2=convertView.findViewById(R.id.tv_item2);
        TextView tvItem3=convertView.findViewById(R.id.tv_item3);
        TextView tvItem4=convertView.findViewById(R.id.tv_item4);
        TextView tvItem5=convertView.findViewById(R.id.tv_item5);
        TextView tvItem6=convertView.findViewById(R.id.tv_item6);
        TextView tvItem7=convertView.findViewById(R.id.tv_item7);
        tvItem1.setText(supplierListBean.getMaterialName());
        if(supplierListBean.getNeedQuantity()!=null)
           tvItem2.setText(supplierListBean.getNeedQuantity()+"");
        tvItem3.setText(supplierListBean.getClothQuantity());
        tvItem4.setText(supplierListBean.getSupplierName());
        tvItem5.setText(supplierListBean.getQuantity()+"");
        tvItem6.setText(""+supplierListBean.getUnitPrice());
        tvItem7.setText("￥"+supplierListBean.getQuantity()*supplierListBean.getUnitPrice()+"");
        return convertView;
    }
}
