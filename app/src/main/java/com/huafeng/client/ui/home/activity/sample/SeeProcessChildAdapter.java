package com.huafeng.client.ui.home.activity.sample;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class SeeProcessChildAdapter extends BaseQuickAdapter<SeeProcess.Child, BaseViewHolder> {
    public SeeProcessChildAdapter(@Nullable List<SeeProcess.Child> data) {
        super(R.layout.item_see_process_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SeeProcess.Child item) {
        TextView tv_name = helper.getView(R.id.tv_process);
        TextView tv_price = helper.getView(R.id.tv_price);
        ImageView iv_check = helper.getView(R.id.iv_check);
        helper.setIsRecyclable(false);
        if (item.getIsout() == 1) {
            iv_check.setImageResource(R.mipmap.ic_check);

        } else {
            iv_check.setImageResource(R.mipmap.ic_uncheck);
        }
        tv_price.setText("￥"+item.getPrice());
        tv_name.setText(item.getName());

    }
}
