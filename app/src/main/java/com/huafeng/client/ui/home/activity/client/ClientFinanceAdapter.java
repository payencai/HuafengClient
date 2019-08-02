package com.huafeng.client.ui.home.activity.client;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class ClientFinanceAdapter extends BaseQuickAdapter<ClientFinance, BaseViewHolder> {
    public ClientFinanceAdapter(@Nullable List<ClientFinance> data) {
        super(R.layout.item_finance, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClientFinance item) {

    }
}
