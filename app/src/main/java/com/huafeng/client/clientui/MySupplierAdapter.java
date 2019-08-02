package com.huafeng.client.clientui;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;


import java.util.List;

public class MySupplierAdapter extends BaseQuickAdapter<Supplier, BaseViewHolder> {

    public MySupplierAdapter(@Nullable List<Supplier> data) {
        super(R.layout.item_my_supplier, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Supplier item) {
        ImageView iv_img=helper.getView(R.id.iv_img);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_no=helper.getView(R.id.tv_no);
        TextView tv_address=helper.getView(R.id.tv_address);
        tv_name.setText(item.getName());
        tv_no.setText(item.getSystemId());
        tv_address.setText(item.getProvince()+item.getCity());
        if(!TextUtils.isEmpty(item.getImageUrl()))
        Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
    }
}
