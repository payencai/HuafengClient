package com.huafeng.client.clientui;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;


import java.util.List;

public class SupplierApplyAdapter extends BaseQuickAdapter<SupplierApply, BaseViewHolder> {

    public SupplierApplyAdapter(@Nullable List<SupplierApply> data) {
        super(R.layout.item_new_supplier, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierApply item) {
        helper.setIsRecyclable(false);
        helper.addOnClickListener(R.id.tv_refuse).addOnClickListener(R.id.tv_agree);
        ImageView iv_img=helper.getView(R.id.iv_img);
        TextView tv_status=helper.getView(R.id.tv_status);
        TextView tv_status2=helper.getView(R.id.tv_status2);
        TextView tv_address=helper.getView(R.id.tv_address);
        TextView tv_agree=helper.getView(R.id.tv_agree);
        TextView tv_refuse=helper.getView(R.id.tv_refuse);
        TextView tv_content=helper.getView(R.id.tv_content);
        TextView tv_name=helper.getView(R.id.tv_name);
        tv_name.setText(item.getFactoryName()+" "+item.getFactorySystemId());
        if(!TextUtils.isEmpty(item.getImageUrl()))
            Glide.with(mContext).load(item.getImageUrl()).into(iv_img);
        if(item.getType()==1){
            tv_content.setText(item.getClientApplication().getRemarks());
            tv_status.setVisibility(View.VISIBLE);
            tv_address.setText(item.getClientApplication().getProvince()+item.getClientApplication().getCity());
            if(item.getClientApplication().getStatus()==0){
                tv_status.setText("已申请待通过");
            }else{
                if(item.getClientApplication().getStatus()==1){
                    tv_status.setText("你的申请已通过");
                }else{
                    tv_status.setText("你的申请被拒绝");
                }
            }
        }else{
            tv_content.setText(item.getClientInvitation().getRemarks());
            if(item.getClientInvitation().getStatus()==0){
                tv_agree.setVisibility(View.VISIBLE);
                tv_refuse.setVisibility(View.VISIBLE);
            }else{
                tv_status2.setVisibility(View.VISIBLE);
                if(item.getClientInvitation().getStatus()==1){
                    tv_status2.setText("已通过");
                }else{
                    tv_status2.setText("已拒绝");
                }
            }
        }
    }
}
