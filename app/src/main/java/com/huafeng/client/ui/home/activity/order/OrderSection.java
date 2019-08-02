package com.huafeng.client.ui.home.activity.order;

import com.chad.library.adapter.base.entity.SectionEntity;

public class OrderSection extends SectionEntity<OrderDetail.OrderSampleVoBean.ProductProcessListBean> {

    public OrderSection(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public OrderSection(OrderDetail.OrderSampleVoBean.ProductProcessListBean t) {
        super(t);
    }
}
