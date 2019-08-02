package com.huafeng.client.ui.home.activity.buy;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class WaitBuyAdapter extends BaseQuickAdapter<WaitBuy, BaseViewHolder> {
    public WaitBuyAdapter(int layoutResId, @Nullable List<WaitBuy> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WaitBuy item) {
        TextView tv_num=helper.getView(R.id.tv_num);
        ImageView iv_img=helper.getView(R.id.iv_img);
        if(!TextUtils.isEmpty(item.getImageUrl()))
           Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        TextView tv_content=helper.getView(R.id.tv_content);
        TextView tv_ordernumber=helper.getView(R.id.tv_ordernumber);
        TextView tv_name=helper.getView(R.id.tv_name);
        tv_name.setText(item.getSampleNo());
        tv_ordernumber.setText(item.getOrderNumber());
        if(item.getType()==2){
            tv_content.setText("生产单采购");
            tv_num.setVisibility(View.VISIBLE);
            tv_num.setText("生产单号："+item.getProductionOrderNumber());
        }else{
            tv_content.setText("订单采购");
            tv_num.setVisibility(View.GONE);
        }
    }
}
