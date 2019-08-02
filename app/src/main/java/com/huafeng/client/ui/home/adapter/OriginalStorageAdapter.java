package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.OriginalStorage;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class OriginalStorageAdapter extends BaseQuickAdapter<OriginalStorage,BaseViewHolder> {
    public OriginalStorageAdapter(int layoutResId, @Nullable List<OriginalStorage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OriginalStorage item) {

        ImageView iv_img=helper.getView(R.id.iv_clothes);
        TextView tv_style=helper.getView(R.id.tv_style);
        TextView tv_number=helper.getView(R.id.tv_number);
        TextView tv_materialId=helper.getView(R.id.tv_sampleno);
        TextView tv_name=helper.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        tv_materialId.setText(item.getMaterialId()+"");
        tv_style.setText("款式："+item.getCategory1Name()+" "+ item.getCategory2Name());
        tv_number.setText("当前库存:"+item.getInventoryQuantity());
        if(!TextUtils.isEmpty(item.getImageUrl()))
            Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        else{
            iv_img.setImageResource(R.mipmap.image_load_err);
        }
    }
}
