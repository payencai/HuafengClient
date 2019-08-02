package com.huafeng.client.ui.message.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.ui.message.model.Msg;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class MsgAdapter extends BaseQuickAdapter<Msg,BaseViewHolder> {
    public MsgAdapter(int layoutResId, @Nullable List<Msg> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Msg item) {

    }
}
