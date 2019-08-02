package com.huafeng.client.ui.home.activity.design;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.view.MyGridView;

import java.util.List;

public class SizeModuleAdapter extends BaseQuickAdapter<SizeModule, BaseViewHolder> {

    public SizeModuleAdapter( @Nullable List<SizeModule> data) {
        super(R.layout.item_size_module, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SizeModule item) {
        TextView tv_size=helper.getView(R.id.tv_size);
        MyGridView gv_size=helper.getView(R.id.gv_size);
        SizeModuleChildAdapter sizeModuleChildAdapter=new SizeModuleChildAdapter(mContext,item.getSampleSizeInformationList());
        gv_size.setAdapter(sizeModuleChildAdapter);
        tv_size.setText(item.getSizeName());
    }
}
