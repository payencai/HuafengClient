package com.huafeng.client.ui.contacts.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.contacts.model.Stranger;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class StrangerAdapter extends BaseQuickAdapter<Stranger, BaseViewHolder> {

    public StrangerAdapter(int layoutResId, @Nullable List<Stranger> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Stranger item) {
          helper.addOnClickListener(R.id.tv_agree);
          TextView tv_name=helper.getView(R.id.tv_name);
          CircleImageView  ivhead=helper.getView(R.id.iv_head);
          tv_name.setText(item.getNickname());
          if(!TextUtils.isEmpty(item.getHeadPortraitUrl()))
          Glide.with(mContext)
                .load(item.getHeadPortraitUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(ivhead);
          else{
              Glide.with(mContext)
                      .load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png")
                      .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                      .into(ivhead);
          }
    }
}
