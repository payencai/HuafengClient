package com.huafeng.client.ui.home.activity.produce;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.Auth;

import org.litepal.LitePal;

import java.util.List;

public class ProduceFlowListAdapter extends BaseQuickAdapter<ProduceDetail.FlowListBean, BaseViewHolder> {


    public ProduceFlowListAdapter(@Nullable List<ProduceDetail.FlowListBean> data) {
        super(R.layout.item_product_flow, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ProduceDetail.FlowListBean item) {
        helper.setIsRecyclable(false);
        int position=helper.getAdapterPosition();
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_num = helper.getView(R.id.tv_num);
        LinearLayout ll_item1=helper.getView(R.id.ll_item1);
        LinearLayout ll_item2=helper.getView(R.id.ll_item2);
        TextView tv_cost = helper.getView(R.id.tv_cost);
        TextView tv_name1 = helper.getView(R.id.tv_name1);
        TextView tv_name2 = helper.getView(R.id.tv_name2);
        TextView tv_name3 = helper.getView(R.id.tv_name3);
        TextView tv_time1 = helper.getView(R.id.tv_time1);
        TextView tv_time2 = helper.getView(R.id.tv_time2);
        TextView tv_time3 = helper.getView(R.id.tv_time3);
        TextView tv_content = helper.getView(R.id.tv_content);
        TextView tv_do1 = helper.getView(R.id.tv_do1);
        TextView tv_do2 = helper.getView(R.id.tv_do2);
        RelativeLayout rl_type1 = helper.getView(R.id.rl_type1);
        RelativeLayout rl_type2 = helper.getView(R.id.rl_type2);
        RelativeLayout rl_type3 = helper.getView(R.id.rl_type3);
        LinearLayout ll_type4 = helper.getView(R.id.rl_type4);

        helper.addOnClickListener(R.id.tv_do1).addOnClickListener(R.id.tv_do2);
        if (position == 0) {
            rl_type1.setVisibility(View.GONE);
            rl_type2.setVisibility(View.GONE);
            tv_content.setVisibility(View.GONE);
            rl_type3.setVisibility(View.VISIBLE);
            ll_type4.setVisibility(View.GONE);
            tv_name3.setText("生产单创建");
            tv_name.setText("生产单创建");
            tv_time3.setText(item.getGmtCreate());
        } else if (position == 1) {
            tv_name.setText("裁片");
            tv_name1.setText("裁片工序开始");
            tv_do1.setText("用料统计");
            tv_time1.setText(item.getGmtCreate());
            tv_content.setText("工序内容：" + item.getProcessNames());
            tv_time3.setText("裁片工序完成");
            tv_time3.setText(item.getGmtFinish());
            rl_type2.setVisibility(View.GONE);
            int count=(item.getQuantity()-item.getLossQuantity());
            tv_num.setText( count+ "");
            tv_cost.setText(item.getLossQuantity() + "");
            List<Auth> auths= LitePal.findAll(Auth.class);
            for (int i = 0; i <auths.size() ; i++) {
                int authid=auths.get(i).getAuthId();
                if(authid==1021||authid==1022||authid==1023||authid==1031){
                    ll_item1.setVisibility(View.VISIBLE);
                    break;
                }else{
                    ll_item1.setVisibility(View.GONE);
                }
            }
        } else {
            int count=0;
            tv_content.setText("工序内容：" + item.getProcessNames());
            if(item.getLossQuantity()!=null){
                count=(item.getQuantity()-item.getLossQuantity().intValue());
            }else{
                count=item.getQuantity();

            }

            tv_num.setText( count+ "");
            if (item.getOutsourcingStatus() == -1) {
                rl_type2.setVisibility(View.GONE);
                tv_name.setText(item.getTitle());
                tv_name1.setText(item.getTitle() + "工序开始");
                tv_name3.setText(item.getTitle() + "工序完成");
                tv_content.setText("工序内容：" + item.getProcessNames());
                tv_time1.setText(item.getGmtCreate() + "");
                tv_time3.setText(item.getGmtFinish());

                tv_cost.setText(item.getLossQuantity() + "");
                tv_do1.setText("工作分配");
                List<Auth> auths= LitePal.findAll(Auth.class);
                for (int i = 0; i <auths.size() ; i++) {
                    int authid=auths.get(i).getAuthId();
                    if(authid==1031||(item.getPrincipalRecordId()== MyApp.getUserInfo().getEmployeeRecordId())){
                        ll_item1.setVisibility(View.VISIBLE);
                        break;
                    }else{
                        ll_item1.setVisibility(View.GONE);
                    }
                }

            } else {
                tv_name.setText(item.getTitle());
                tv_name1.setText(item.getTitle() + "取货");
                tv_name2.setText(item.getTitle() + "送货");
                tv_name3.setText(item.getTitle() + "工序完成");
                tv_do1.setText("取货单");
                tv_do2.setText("送货单");
                tv_time1.setText(item.getOutsourcingPickupTime() + "");
                tv_time1.setVisibility(View.VISIBLE);
                tv_time2.setText(item.getOutsourcingDeliveryTime() + "");
                tv_time3.setText(item.getGmtFinish());
                tv_cost.setText(item.getLossQuantity() + "");
                List<Auth> auths= LitePal.findAll(Auth.class);
                for (int i = 0; i <auths.size() ; i++) {
                    int authid=auths.get(i).getAuthId();
                    if(authid==1031||(item.getPrincipalRecordId()== MyApp.getUserInfo().getEmployeeRecordId())){
                        ll_item1.setVisibility(View.VISIBLE);
                        ll_item2.setVisibility(View.VISIBLE);
                        break;
                    }else{
                        ll_item1.setVisibility(View.GONE);
                        ll_item2.setVisibility(View.GONE);
                    }
                }
                if (item.getOutsourcingStatus() == 0) {
                    rl_type2.setVisibility(View.GONE);
                    tv_time1.setText(item.getGmtCreate());
                    tv_time1.setVisibility(View.VISIBLE);
                } else if (item.getOutsourcingStatus() == 1) {
                    rl_type2.setVisibility(View.VISIBLE);
                    rl_type1.setVisibility(View.VISIBLE);
                    tv_time2.setVisibility(View.VISIBLE);
                }
                if(TextUtils.isEmpty(item.getOutsourcingPickupTime())){
                    tv_time1.setVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(item.getOutsourcingDeliveryTime())){
                    tv_time2.setVisibility(View.GONE);
                }else{
                    tv_time2.setVisibility(View.VISIBLE);
                }

            }
            int len=getData().size()-1;
            if(len==helper.getAdapterPosition()){
                if(TextUtils.isEmpty(item.getGmtFinish())){
                    rl_type3.setVisibility(View.GONE);
                    ll_type4.setVisibility(View.GONE);
                }
                if(item.isShow()){
                    ll_type4.setVisibility(View.GONE);
                }

            }
        }


    }

}
