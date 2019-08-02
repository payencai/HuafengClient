package com.huafeng.client.clientui;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class ClientOrderAdapter extends BaseQuickAdapter<ClientOrder, BaseViewHolder> {
    public ClientOrderAdapter(int layoutResId, @Nullable List<ClientOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClientOrder item) {
        ImageView iv_img = helper.getView(R.id.iv_img);
        TextView tv_status = helper.getView(R.id.tv_status);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_ordernum = helper.getView(R.id.tv_ordernumber);
        TextView tv_content = helper.getView(R.id.tv_content);
        TextView tv_num = helper.getView(R.id.tv_num);
        tv_ordernum.setText(item.getOrderNumber());
        tv_num.setText("订单数量：" + item.getQuantity());
        tv_name.setText(item.getSampleNo());
        tv_content.setText("工厂：" + item.getFactoryName() + " ");
        if (item.getStatus() == 1) {
            tv_status.setText("待确认");
        } else if (item.getStatus() == 2) {
            tv_status.setText("进行中");
        } else if (item.getStatus() == 3) {
            tv_status.setText("已完成");
        } else if(item.getStatus()==4){
            tv_status.setText("已终止");
        }else{
            tv_status.setText("已取消");
        }
        if (!TextUtils.isEmpty(item.getImageUrl()))
            Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        else
            iv_img.setImageResource(R.drawable.ic_empty_img);
    }
}
