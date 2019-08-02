package com.huafeng.client.ui.home.activity.origin;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class OriginPriceInfoAdapter extends BaseQuickAdapter<OriginStorageDetail.PurchaseNoteSupplierListBean, BaseViewHolder> {
    public OriginPriceInfoAdapter( @Nullable List<OriginStorageDetail.PurchaseNoteSupplierListBean> data) {
        super(R.layout.item_origin_table, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OriginStorageDetail.PurchaseNoteSupplierListBean item) {
        TextView tv_item1=helper.getView(R.id.tv_item1);
        TextView tv_item2=helper.getView(R.id.tv_item2);
        TextView tv_item3=helper.getView(R.id.tv_item3);
        TextView tv_item4=helper.getView(R.id.tv_item4);
        tv_item1.setText(item.getCreateTime().substring(0,10));
        tv_item2.setText(item.getUnitPrice()+"");
        tv_item3.setText(item.getQuantity()+"");
        tv_item4.setText(item.getSupplierName());
    }
}
