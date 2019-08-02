package com.huafeng.client.ui.contacts.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.contacts.model.FriendRequest;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class FriendAskAdapter extends BaseQuickAdapter<FriendRequest, BaseViewHolder> {

    public FriendAskAdapter(int layoutResId, @Nullable List<FriendRequest> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendRequest item) {
        helper.addOnClickListener(R.id.tv_refuse).addOnClickListener(R.id.tv_agree);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_content = helper.getView(R.id.tv_content);
        TextView tv_refuse = helper.getView(R.id.tv_refuse);
        TextView tv_agree = helper.getView(R.id.tv_agree);
        TextView tv_status = helper.getView(R.id.tv_status);
        CircleImageView iv_head=helper.getView(R.id.iv_head);
        if(!TextUtils.isEmpty(item.getHeadPortrait())){
            Glide.with(mContext).load(item.getHeadPortraitUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
        }else{
            Glide.with(mContext).load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png").apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
        }
        if(item.getState()==1){
            tv_agree.setVisibility(View.GONE);
            tv_refuse.setVisibility(View.GONE);
            tv_status.setText("已通过");
            tv_status.setVisibility(View.VISIBLE);
        }else if(item.getState()==2){
            tv_agree.setVisibility(View.GONE);
            tv_refuse.setVisibility(View.GONE);
            tv_status.setText("已拒绝");
            tv_status.setVisibility(View.VISIBLE);
        }else{
            tv_agree.setVisibility(View.VISIBLE);
            tv_refuse.setVisibility(View.VISIBLE);
            tv_status.setVisibility(View.GONE);
        }
        tv_name.setText(item.getNickname());
        tv_content.setText(item.getApplyReason());


    }
}
