package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.Factory;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/9 16:00
 * 邮箱：771548229@qq..com
 */
public class FactoryAdapter extends BaseQuickAdapter<Factory, BaseViewHolder> {
    public FactoryAdapter( @Nullable List<Factory> data) {
        super(R.layout.item_list_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Factory item) {
        TextView tv_type=helper.getView(R.id.tv_type);
        tv_type.setText(item.getName());
    }
}
