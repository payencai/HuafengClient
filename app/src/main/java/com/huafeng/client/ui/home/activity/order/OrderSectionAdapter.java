package com.huafeng.client.ui.home.activity.order;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class OrderSectionAdapter extends BaseSectionQuickAdapter<OrderSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *

     * @param data             A new list is created out of this one to avoid mutable list
     */
    public OrderSectionAdapter(List data) {
        super(R.layout.item_order_process, R.layout.item_process_header, data);
    }
    private  OnItemSelectListener onItemSelectListener;
    public interface OnItemSelectListener{
        void onSelect(int pos,ImageView imageView);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final OrderSection item) {
        RelativeLayout rl_header = helper.getView(R.id.rl_header);

            rl_header.setVisibility(View.GONE);

    }


    @Override
    protected void convert(BaseViewHolder helper, OrderSection item) {
        helper.setIsRecyclable(false);
        OrderDetail.OrderSampleVoBean.ProductProcessListBean productProcessListBean = item.t;
        TextView tv_process = helper.getView(R.id.tv_process);
        TextView tv_name = helper.getView(R.id.tv_name);
        if(productProcessListBean.isHeader()){
            tv_name.setText(productProcessListBean.getStageName());
            tv_name.setVisibility(View.VISIBLE);
        }else{
            tv_name.setVisibility(View.GONE);
        }
        LinearLayout ll_out = helper.getView(R.id.ll_out);
        ImageView iv_check=helper.getView(R.id.iv_check);
        tv_process.setText(productProcessListBean.getProcessName());
        ll_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelectListener.onSelect(helper.getAdapterPosition(),iv_check);
            }
        });
        if(productProcessListBean.getIsOutsourcing()==1){
            iv_check.setImageResource(R.mipmap.ic_check);
        }else{
            iv_check.setImageResource(R.mipmap.ic_uncheck);
        }
        if(productProcessListBean.getProcessName().equals("裁片")){
            ll_out.setVisibility(View.GONE);
        }else{
            ll_out.setVisibility(View.VISIBLE);
        }
    }
}
