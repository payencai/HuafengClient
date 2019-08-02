package com.huafeng.client.ui.home.activity.buy;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class WaitBuyDetailAdapter extends BaseQuickAdapter<WaitBuyDetail, BaseViewHolder> {
    public WaitBuyDetailAdapter(int layoutResId, @Nullable List<WaitBuyDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WaitBuyDetail item) {
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_type = helper.getView(R.id.tv_type);
        TextView tv_doing = helper.getView(R.id.tv_doing);
        TextView tv_wait = helper.getView(R.id.tv_wait);
        TextView tv_num = helper.getView(R.id.tv_num);
        tv_name.setText(item.getMaterialName());
        tv_doing.setText(item.getInPurchaseNumber()+"");
        tv_num.setText(item.getInventoryNumber()+"");
        tv_wait.setText(item.getToPurchaseNumber()+"");
    }

}
