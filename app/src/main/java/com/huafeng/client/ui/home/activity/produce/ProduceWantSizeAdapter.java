package com.huafeng.client.ui.home.activity.produce;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class ProduceWantSizeAdapter extends BaseQuickAdapter<ProduceDetail.SizeQuantityListBean, BaseViewHolder> {
    public ProduceWantSizeAdapter(@Nullable List<ProduceDetail.SizeQuantityListBean> data) {
        super(R.layout.item_finsh_size, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProduceDetail.SizeQuantityListBean item) {
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_value=helper.getView(R.id.tv_value);
        tv_name.setText(item.getSizeName());
        tv_value.setText(item.getQuantity()+"");
    }
}
