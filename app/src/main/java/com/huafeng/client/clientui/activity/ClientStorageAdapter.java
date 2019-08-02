package com.huafeng.client.clientui.activity;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class ClientStorageAdapter extends BaseQuickAdapter<ClientStorage,BaseViewHolder> {
    public ClientStorageAdapter(@Nullable List<ClientStorage> data) {
        super(R.layout.item_model_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClientStorage item) {
        TextView tv_sampleno=helper.getView(R.id.tv_sampleno);
        CircleImageView iv_img=helper.getView(R.id.iv_img);
        TextView tv_type=helper.getView(R.id.tv_type);
        TextView tv_content=helper.getView(R.id.tv_content);
        TextView tv_time=helper.getView(R.id.tv_time);
        tv_sampleno.setText(item.getSampleNo());
        tv_type.setText("款式："+item.getCategory1Name()+" "+item.getCategory2Name());
        tv_content.setText("工厂："+item.getFactoryName());
        tv_time.setText("库存量："+item.getQuantity());
        if(!TextUtils.isEmpty(item.getImageUrl()))
           Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        else{
            iv_img.setImageResource(R.drawable.ic_empty_img);
        }
    }
}
