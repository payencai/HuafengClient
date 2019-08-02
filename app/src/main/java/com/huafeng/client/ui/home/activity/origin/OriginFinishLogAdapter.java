package com.huafeng.client.ui.home.activity.origin;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class OriginFinishLogAdapter extends BaseQuickAdapter<OriginFinishDetail.InventorySampleLogListBean, BaseViewHolder> {
    public OriginFinishLogAdapter(@Nullable List<OriginFinishDetail.InventorySampleLogListBean> data) {
        super(R.layout.item_finish_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OriginFinishDetail.InventorySampleLogListBean item) {
        helper.setIsRecyclable(false);
        TextView tv_item1 = helper.getView(R.id.tv_item1);
        TextView tv_item2 = helper.getView(R.id.tv_item2);
        TextView tv_item3 = helper.getView(R.id.tv_item3);
        TextView tv_item4 = helper.getView(R.id.tv_item4);
        TextView tv_item5 = helper.getView(R.id.tv_item5);
        TextView tv_item6 = helper.getView(R.id.tv_item6);
        TextView tv_item7 = helper.getView(R.id.tv_item7);
        TextView tv_item8 = helper.getView(R.id.tv_item8);
        tv_item1.setText(item.getGmtCreate().substring(0, 10));
        tv_item2.setText(item.getTitle() + "");
        if (item.getType() == 1) {
            tv_item3.setText("入库");
        } else {
            tv_item3.setText("出库");
        }
        tv_item4.setText(item.getQuantity() + "");
        tv_item5.setText(item.getEmployeeRecordName());
        tv_item7.setText(item.getRemainingQuantity() + "");
        if (item.getNoteId() ==null) {
            tv_item6.setVisibility(View.GONE);
        } else {
            tv_item6.setVisibility(View.VISIBLE);
            if (item.getType() == 1) {
                tv_item6.setText("退货单");
            } else {
                tv_item6.setText("发货单");
            }
        }
        helper.addOnClickListener(R.id.tv_item6);

        tv_item8.setText(item.getRemarks());
    }
}
