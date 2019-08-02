package com.huafeng.client.ui.message.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.message.model.MyGroup;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class MyGroupAdapter extends BaseQuickAdapter<MyGroup, BaseViewHolder> {
    public MyGroupAdapter( @Nullable List<MyGroup> data) {
        super(R.layout.item_my_group, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyGroup item) {
        TextView tv_index = helper.getView(R.id.tv_index);
        tv_index.setVisibility(View.GONE);
        TextView tv_my=helper.getView(R.id.tv_my);
        if(MyApp.getUserInfo().getHxUsername().equals(""+item.getCrowdUserId())){
            tv_my.setVisibility(View.VISIBLE);
        }else{
            tv_my.setVisibility(View.GONE);
        }
        CircleImageView iv_head = helper.getView(R.id.iv_head);
        if (!TextUtils.isEmpty(item.getImageUrl())) {
            Glide.with(mContext)
                    .load(item.getImageUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_head);
        } else {
            Glide.with(mContext)
                    .load("http://icons.iconarchive.com/icons/blackvariant/button-ui-system-folders-drives/1024/Group-icon.png")
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_head);
        }
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item.getCrowdName());
    }
}
