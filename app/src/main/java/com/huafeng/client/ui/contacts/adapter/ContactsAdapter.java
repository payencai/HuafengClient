package com.huafeng.client.ui.contacts.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.contacts.model.Contacts;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class ContactsAdapter extends BaseQuickAdapter<Contacts, BaseViewHolder> {

    public ContactsAdapter(int layoutResId, @Nullable List<Contacts> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Contacts item) {

        TextView tv_index = helper.getView(R.id.tv_index);
        int pos = helper.getAdapterPosition();
        CircleImageView iv_head=helper.getView(R.id.iv_head);
        Glide.with(mContext)
                .load(item.getHeadPortraitUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iv_head);
        if (pos == 0 || !item.getIndex().equals(getData().get(pos - 1).getIndex())) {
            tv_index.setVisibility(View.VISIBLE);
            tv_index.setText(item.getIndex());
        } else {
            tv_index.setVisibility(View.GONE);
        }


        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item.getNickname());
    }
}
