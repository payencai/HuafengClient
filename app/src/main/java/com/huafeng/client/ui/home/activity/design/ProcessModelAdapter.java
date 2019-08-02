package com.huafeng.client.ui.home.activity.design;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.sample.ProcesModel;

import java.util.List;

public class ProcessModelAdapter extends BaseQuickAdapter<ProcesModel.ProcessListBean, BaseViewHolder> {
    public ProcessModelAdapter( @Nullable List<ProcesModel.ProcessListBean> data) {
        super(R.layout.item_process_module, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProcesModel.ProcessListBean item) {
        TextView tv_stage=helper.getView(R.id.tv_stage);
        TextView tv_process=helper.getView(R.id.tv_process);
        TextView tv_price=helper.getView(R.id.tv_price);
        if(item.getPrice()!=null)
          tv_price.setText("￥"+item.getPrice());
        else{
            tv_price.setText("￥-");
        }
        tv_stage.setText(item.getStageName());
        tv_process.setText(item.getProcessName());
        helper.addOnClickListener(R.id.tv_delete).addOnClickListener(R.id.tv_up).addOnClickListener(R.id.tv_down);
    }
}
