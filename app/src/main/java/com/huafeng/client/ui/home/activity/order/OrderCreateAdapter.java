package com.huafeng.client.ui.home.activity.order;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class OrderCreateAdapter extends BaseQuickAdapter<OrderCreate, BaseViewHolder> {
    public OrderCreateAdapter( @Nullable List<OrderCreate> data) {
        super(R.layout.item_create_produce, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderCreate item) {
        TextView tv_start=helper.getView(R.id.tv_start);
        TextView tv_finishname=helper.getView(R.id.tv_finishname);
        TextView tv_finish=helper.getView(R.id.tv_finish);
        TextView tv_locate=helper.getView(R.id.tv_locate);
        TextView tv_num=helper.getView(R.id.tv_num);
        TextView tv_real=helper.getView(R.id.tv_real);
        TextView tv_sampleno=helper.getView(R.id.tv_sampleno);
        TextView tv_month=helper.getView(R.id.tv_month);
        TextView tv_status=helper.getView(R.id.tv_status);
        ImageView iv_img=helper.getView(R.id.iv_img);
        Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        tv_locate.setText(item.getProductOrderNumber());
        tv_month.setText(item.getMonth());
        tv_status.setText(item.getStageName());
        tv_sampleno.setText(item.getSampleNo());
        tv_num.setText("生产数量："+item.getQuantity());
        tv_start.setText(item.getGmtCreate());
        if(item.getStatus()==1){
            tv_finishname.setText("预期时间：");
            tv_real.setVisibility(View.GONE);
            tv_finish.setText(item.getEstimatedTimeOfFinishment().substring(0,10));
        }else if(item.getStatus()==2){
            tv_real.setText("实际产出："+(item.getQuantity()-item.getLossQuantity()));
            tv_finishname.setText("完成时间：");
            tv_finish.setText(item.getFinishTime());
        }else{
            tv_finishname.setText("终止时间：");
            tv_finish.setText(item.getStopTime());
        }
    }
}
