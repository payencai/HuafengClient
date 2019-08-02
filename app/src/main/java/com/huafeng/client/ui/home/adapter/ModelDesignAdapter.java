package com.huafeng.client.ui.home.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.ModelDesign;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class ModelDesignAdapter extends BaseQuickAdapter<ModelDesign,BaseViewHolder> {
    public ModelDesignAdapter(int layoutResId, @Nullable List<ModelDesign> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelDesign item) {

        TextView tv_no=helper.getView(R.id.tv_no);
        TextView tv_client=helper.getView(R.id.tv_client);
        TextView tv_code=helper.getView(R.id.tv_code);
        TextView tv_status=helper.getView(R.id.tv_status);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_designer=helper.getView(R.id.tv_designer);
        TextView tv_time=helper.getView(R.id.tv_time);
        CircleImageView iv_logo=helper.getView(R.id.iv_logo);
        Glide.with(mContext).load(item.getImageUrl()).into(iv_logo);
        tv_code.setText(item.getPatternMakingNo());
        tv_status.setText(item.getStatusName());
        tv_client.setText("客户："+item.getClientName());
        if(TextUtils.isEmpty(item.getPatternNo())||"null".equals(item.getPatternNo())){
            tv_no.setText("纸样编号："+"未录入");
        }else{
            tv_no.setText("纸样编号："+item.getPatternNo());
        }
        tv_designer.setText("设计师："+item.getDesignByName());
        String type="款式："+item.getProductCategory1Name()+" "+item.getProductCategory2Name();
        tv_name.setText(type.replace("null",""));
        tv_time.setText(item.getCreateTime().substring(0,10));
    }
}
