package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.ApprovalCenter;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class ApprovalCenterAdapter extends BaseQuickAdapter<ApprovalCenter,BaseViewHolder> {
    public ApprovalCenterAdapter(int layoutResId, @Nullable List<ApprovalCenter> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApprovalCenter item) {
        TextView tv_type1=helper.getView(R.id.tv_type1);
        TextView tv_type2=helper.getView(R.id.tv_type2);
        TextView tv_title=helper.getView(R.id.tv_title);
        TextView tv_status=helper.getView(R.id.tv_status);
        TextView tv_content=helper.getView(R.id.tv_content);
        TextView tv_applvers=helper.getView(R.id.tv_applvers);
        TextView tv_time=helper.getView(R.id.tv_time);
        tv_applvers.setText("申请人："+item.getApplyByName());
        tv_content.setText(item.getContent());
        tv_time.setText(item.getApplyTime());
        tv_title.setText(item.getTitle());
        if(item.getType()==1){
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.GONE);
        }else{
            tv_type2.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.GONE);
        }
        if(item.getStatus()==0){
            tv_status.setText("待审批");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_org));
        }else if(item.getStatus()==1){
            tv_status.setText("已通过");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.btn_green_noraml));
        }else{
            tv_status.setText("已拒绝");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_red));
        }
    }
}
