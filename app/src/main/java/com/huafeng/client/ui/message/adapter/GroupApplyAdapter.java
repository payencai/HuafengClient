package com.huafeng.client.ui.message.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.message.model.GroupApply;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class GroupApplyAdapter extends BaseQuickAdapter<GroupApply, BaseViewHolder> {

    public GroupApplyAdapter(int layoutResId, @Nullable List<GroupApply> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupApply item) {
        helper.addOnClickListener(R.id.tv_refuse).addOnClickListener(R.id.tv_agree);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_content = helper.getView(R.id.tv_content);
        TextView tv_refuse = helper.getView(R.id.tv_refuse);
        TextView tv_agree = helper.getView(R.id.tv_agree);
        TextView tv_status = helper.getView(R.id.tv_status);
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
