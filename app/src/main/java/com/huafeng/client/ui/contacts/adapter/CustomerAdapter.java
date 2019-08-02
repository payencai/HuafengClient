package com.huafeng.client.ui.contacts.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.CustomerData;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class CustomerAdapter extends BaseQuickAdapter<CustomerData, BaseViewHolder> {

    public CustomerAdapter(int layoutResId, @Nullable List<CustomerData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerData item) {

        TextView tv_index = helper.getView(R.id.tv_index);
        TextView tv_time = helper.getView(R.id.tv_time);
        TextView tv_num = helper.getView(R.id.tv_num);
        TextView tv_address = helper.getView(R.id.tv_address);
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        tv_time.setText("添加时间："+item.getGmtCreate().substring(0,10));
        tv_num.setText("订单数量："+item.getOrderQuantity());
        tv_address.setText(item.getProvince()+" "+item.getCity());
        int pos = helper.getAdapterPosition();


        if (pos == 0 || !item.getIndex().equals(getData().get(pos - 1).getIndex())) {
            tv_index.setVisibility(View.VISIBLE);
            tv_index.setText(item.getIndex());
        } else {
            tv_index.setVisibility(View.GONE);
        }



    }
}
