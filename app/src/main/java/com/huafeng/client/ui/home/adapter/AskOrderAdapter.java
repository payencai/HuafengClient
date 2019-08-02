package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.AskOrder;

import java.util.List;


public class AskOrderAdapter extends BaseQuickAdapter<AskOrder,BaseViewHolder> {
    public AskOrderAdapter(int layoutResId, @Nullable List<AskOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AskOrder item) {
        TextView tv_order=helper.getView(R.id.tv_ordernumber);
        TextView tv_content=helper.getView(R.id.tv_content);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_time=helper.getView(R.id.tv_time);
        TextView tv_status=helper.getView(R.id.tv_status);
        if(item.getStatus()==1){
            tv_status.setText("待审核");
        }else if(item.getStatus()==2){
            tv_status.setText("已通过");
        }else{
            tv_status.setText("已拒绝");
        }
        if(item.getType()==2){
            tv_content.setText("生产单采购"+item.getProductionOrderNumber()+"原料采购");
        }else{
            tv_content.setText("订单采购"+item.getOrderNumber()+"原料采购");
        }
        tv_order.setText(item.getRequisitionNumber());
        tv_time.setText(item.getGmtCreate());
        tv_name.setText("请购人："+item.getCreateByName());
    }
}
