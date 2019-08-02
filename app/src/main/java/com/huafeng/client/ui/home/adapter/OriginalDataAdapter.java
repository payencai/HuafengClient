package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.OriginalData;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class OriginalDataAdapter extends BaseQuickAdapter<OriginalData,BaseViewHolder> {
    public OriginalDataAdapter(int layoutResId, @Nullable List<OriginalData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OriginalData item) {
        ImageView iv_img=helper.getView(R.id.iv_img);
        TextView tv_name =helper.getView(R.id.tv_name);
        TextView tv_type =helper.getView(R.id.tv_type);
        TextView tv_date =helper.getView(R.id.tv_date);
        tv_name.setText(item.getName());
        tv_type.setText(item.getCategory1Name()+" "+item.getCategory2Name());
        tv_date.setText("创建日期："+item.getGmtCreate());
        if(!TextUtils.isEmpty(item.getImageUrl()))
          Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
    }
}
