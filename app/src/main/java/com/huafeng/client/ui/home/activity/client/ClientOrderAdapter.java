package com.huafeng.client.ui.home.activity.client;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.Order;

import java.util.List;

public class ClientOrderAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {
    public ClientOrderAdapter(@Nullable List<Order> data) {
        super(R.layout.item_home_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {

    }
}
