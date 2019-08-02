package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.SizeType;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/8 10:12
 * 邮箱：771548229@qq..com
 */
public class SizeTypeAdapter extends BaseQuickAdapter<SizeType, BaseViewHolder> {
    public SizeTypeAdapter( @Nullable List<SizeType> data) {
        super(R.layout.item_list_type, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SizeType item) {
        TextView tv_name=helper.getView(R.id.tv_type);
        tv_name.setText(item.getName());
    }
}
