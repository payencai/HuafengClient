package com.huafeng.client.ui.home.activity.design;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.sample.ProcesModel;

import java.util.List;

public class RawModelAdapter extends BaseQuickAdapter<ProcesModel.MaterialListBean, BaseViewHolder> {
    public RawModelAdapter(@Nullable List<ProcesModel.MaterialListBean> data) {
        super(R.layout.item_raw_module, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProcesModel.MaterialListBean item) {
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_id=helper.getView(R.id.tv_id);
        TextView tv_tyle=helper.getView(R.id.tv_tyle);
        tv_name.setText(item.getRawMaterialName());
        tv_id.setText(item.getRawMaterialId()+"");
        tv_tyle.setText(item.getRawMaterialCategory1Name()+"/"+item.getRawMaterialCategory2Name());
    }
}
