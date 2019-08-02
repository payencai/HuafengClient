package com.huafeng.client.ui.home.activity.buy;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class StatisticeAdapter extends BaseQuickAdapter<StatisticsOrigin, BaseViewHolder> {
    public StatisticeAdapter(@Nullable List<StatisticsOrigin> data) {
        super(R.layout.item_statistics_origin, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatisticsOrigin item) {
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_item1 = helper.getView(R.id.tv_item1);
        TextView tv_item2 = helper.getView(R.id.tv_item2);
        TextView tv_item3 = helper.getView(R.id.tv_item3);
        tv_name.setText(item.getMaterialName());
        tv_item1.setText(item.getInventoryNumber()+"");
        tv_item2.setText(item.getToPurchaseNumber()+"");
        tv_item3.setText(item.getInPurchaseNumber()+"");

    }
}
