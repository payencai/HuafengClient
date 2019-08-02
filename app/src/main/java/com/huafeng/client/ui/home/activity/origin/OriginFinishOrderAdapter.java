package com.huafeng.client.ui.home.activity.origin;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class OriginFinishOrderAdapter extends BaseQuickAdapter<OriginFinishDetail.OrdersListBean, BaseViewHolder> {
    public OriginFinishOrderAdapter(@Nullable List<OriginFinishDetail.OrdersListBean> data) {
        super(R.layout.item_finish_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OriginFinishDetail.OrdersListBean item) {
        TextView tv_item1=helper.getView(R.id.tv_item1);
        TextView tv_item2=helper.getView(R.id.tv_item2);
        TextView tv_item3=helper.getView(R.id.tv_item3);
        TextView tv_item4=helper.getView(R.id.tv_item4);
        TextView tv_item5=helper.getView(R.id.tv_item5);
        tv_item1.setText(item.getOrderNumber());
        tv_item2.setText(item.getQuantity()+"");
        tv_item4.setText("");
        if(item.getStatus()==2){
            tv_item4.setText("进行中");
        }else if(item.getStatus()==3){
            tv_item4.setText("已完成");
        }else if(item.getStatus()==4){
            tv_item4.setText("已取消");
        }
        tv_item5.setText(item.getRemarks());
        tv_item3.setText(item.getGmtCreate().substring(0,10));
    }
}
