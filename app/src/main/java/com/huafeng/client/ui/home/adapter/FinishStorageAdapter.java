package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.FinishStorage;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class FinishStorageAdapter extends BaseQuickAdapter<FinishStorage,BaseViewHolder> {
    public FinishStorageAdapter(int layoutResId, @Nullable List<FinishStorage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinishStorage item) {
        ImageView iv_img=helper.getView(R.id.iv_clothes);
        TextView tv_style=helper.getView(R.id.tv_style);
        TextView tv_number=helper.getView(R.id.tv_number);
        TextView tv_materialId=helper.getView(R.id.tv_sampleno);
        TextView tv_name=helper.getView(R.id.tv_name);
        tv_name.setText(item.getClientName());
        tv_materialId.setText(item.getSampleNo()+"");
        tv_style.setText("款式："+item.getCategory1Name()+" "+ item.getCategory2Name());
        tv_number.setText("当前库存:"+item.getQuantity());
        if(!TextUtils.isEmpty(item.getImageUrl()))
            Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        else{
            iv_img.setImageResource(R.mipmap.image_load_err);
        }
    }
}
