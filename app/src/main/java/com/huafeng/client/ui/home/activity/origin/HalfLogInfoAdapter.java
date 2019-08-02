package com.huafeng.client.ui.home.activity.origin;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class HalfLogInfoAdapter extends BaseQuickAdapter<HalfStorageDetail.InventorySimiLogListBean, BaseViewHolder> {
    public HalfLogInfoAdapter(@Nullable List<HalfStorageDetail.InventorySimiLogListBean> data) {
        super(R.layout.item_origin_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HalfStorageDetail.InventorySimiLogListBean item) {
        TextView tv_item1=helper.getView(R.id.tv_item1);
        TextView tv_item2=helper.getView(R.id.tv_item2);
        TextView tv_item3=helper.getView(R.id.tv_item3);
        TextView tv_item4=helper.getView(R.id.tv_item4);
        TextView tv_item5=helper.getView(R.id.tv_item5);
        TextView tv_item6=helper.getView(R.id.tv_item6);
        tv_item1.setText(item.getGmtCreate().substring(0,10));
        tv_item2.setText(item.getTitle()+"");
        if(item.getType()==1){
            tv_item3.setText("入库");
        }else{
            tv_item3.setText("出库");
        }
        tv_item4.setText(item.getQuantity()+"");
        tv_item5.setText(item.getEmployeeRecordName());
        tv_item6.setText(item.getRemainingQuantity()+"");
    }
}
