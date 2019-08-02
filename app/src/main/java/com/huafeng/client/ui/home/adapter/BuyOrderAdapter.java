package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.BuyOrder;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class BuyOrderAdapter extends BaseQuickAdapter<BuyOrder,BaseViewHolder> {
    public BuyOrderAdapter(int layoutResId, @Nullable List<BuyOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuyOrder item) {
        TextView tv_order=helper.getView(R.id.tv_ordernumber);
        TextView tv_content=helper.getView(R.id.tv_content);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_time=helper.getView(R.id.tv_time);
        TextView tv_status=helper.getView(R.id.tv_status);
        if(item.getStatus()==0){
            tv_status.setText("采购中");
        }else{
            tv_status.setText("已完成");
        }
        tv_content.setText("款号:"+item.getSampleNo()+"   原料号:"+item.getMaterialNames());
        tv_order.setText(item.getNoteNumber());
        tv_time.setText(item.getRequisitionCreateTime());
        tv_name.setText("请购人："+item.getRequisitionCreateByName());
    }
}
