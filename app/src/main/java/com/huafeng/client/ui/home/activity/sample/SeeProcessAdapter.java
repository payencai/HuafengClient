package com.huafeng.client.ui.home.activity.sample;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class SeeProcessAdapter extends BaseQuickAdapter<SeeProcess, BaseViewHolder> {
    public SeeProcessAdapter( @Nullable List<SeeProcess> data) {
        super(R.layout.item_see_process, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SeeProcess item) {
        helper.setIsRecyclable(false);
        TextView tv_name=helper.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        RecyclerView rv_child=helper.getView(R.id.rv_child);
        rv_child.setNestedScrollingEnabled(false);
        SeeProcessChildAdapter seeProcessChildAdapter=new SeeProcessChildAdapter(item.getChildList());
        rv_child.setLayoutManager(new LinearLayoutManager(mContext));
        rv_child.setAdapter(seeProcessChildAdapter);
    }
}
