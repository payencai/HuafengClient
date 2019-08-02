package com.huafeng.client.ui.message.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.ui.message.model.Notify;
import com.huafeng.client.view.CircleImageView;

import java.util.List;

/**
 * 作者：凌涛 on 2019/4/3 11:12
 * 邮箱：771548229@qq..com
 */
public class NotifyAdapter extends BaseQuickAdapter<Notify,BaseViewHolder> {
    public NotifyAdapter(int layoutResId, @Nullable List<Notify> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Notify item) {
        helper.setIsRecyclable(false);
        TextView tv_name=helper.getView(R.id.tv_name);
        TextView tv_time=helper.getView(R.id.tv_time);
        tv_name.setText(item.getPush().getContent());
        tv_time.setText(item.getPush().getGmtCreate());
        int status=item.getPush().getType();
        View view1=helper.getView(R.id.view1);
        View view2=helper.getView(R.id.view2);
        View view3=helper.getView(R.id.view3);
        View view4=helper.getView(R.id.view4);
        if(item.getPush().getIsLinked()==1){
            switch (status){
                case  1:
                    view1.setVisibility(View.VISIBLE);
                    setDesignData(view1,item);
                    if(item.getPatternMaking()==null){
                        view1.setVisibility(View.GONE);
                    }
                    break;
                case  2:
                    view2.setVisibility(View.VISIBLE);
                    setProduceData(view2,item);
                    if(item.getProductionOrder()==null){
                        view2.setVisibility(View.GONE);
                    }
                    break;
                case  3:
                case  4:
                    view3.setVisibility(View.VISIBLE);
                    setApplyData(view3,item);
                    break;
                case  5:
                    view4.setVisibility(View.VISIBLE);
                    setOrderData(view4,item);
                    if(item.getOrders()==null){
                        view4.setVisibility(View.GONE);
                    }
                    break;
            }
        }else{
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.GONE);
            view3.setVisibility(View.GONE);
            view4.setVisibility(View.GONE);
        }

    }
    private void setOrderData(View view,Notify item){
        ImageView iv_img = view.findViewById(R.id.iv_img);
        TextView tv_status = view.findViewById(R.id.tv_status);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_ordernum = view.findViewById(R.id.tv_ordernumber);
        TextView tv_content = view.findViewById(R.id.tv_content);
        TextView tv_num = view.findViewById(R.id.tv_num);
        tv_ordernum.setText(item.getOrders().getOrderNumber());
        tv_num.setText("计划数量：" + item.getOrders().getQuantity());
        tv_name.setText(item.getOrders().getSampleNo());
        tv_content.setText("客户：" + item.getOrders().getClientRecordName() + " ");
        if (item.getOrders().getStatus() == 1) {
            tv_status.setText("待确认");
        } else if (item.getOrders().getStatus() == 2) {
            tv_status.setText("进行中");
        } else if (item.getOrders().getStatus() == 3) {
            tv_status.setText("已完成");
        } else if(item.getOrders().getStatus()==4){
            tv_status.setText("已终止");
        }else{
            tv_status.setText("已取消");
        }
        if (!TextUtils.isEmpty(item.getOrders().getImageUrl()))
            Glide.with(mContext).load(item.getOrders().getImageUrl()).into(iv_img);
        else
            iv_img.setImageResource(R.mipmap.image_load_err);
    }
    private void setDesignData(View view,Notify item){
        TextView tv_no=view.findViewById(R.id.tv_no);
        TextView tv_client=view.findViewById(R.id.tv_client);
        TextView tv_code=view.findViewById(R.id.tv_code);
        TextView tv_status=view.findViewById(R.id.tv_status);
        TextView tv_name=view.findViewById(R.id.tv_name);
        TextView tv_designer=view.findViewById(R.id.tv_designer);
        TextView tv_time=view.findViewById(R.id.tv_time);
        CircleImageView iv_logo=view.findViewById(R.id.iv_logo);
        Glide.with(mContext).load(item.getPatternMaking().getImageUrl()).into(iv_logo);
        tv_code.setText(item.getPatternMaking().getPatternMakingNo());
        tv_status.setText(item.getPatternMaking().getStatusName());
        tv_client.setText("客户："+item.getPatternMaking().getClientName());
        if(TextUtils.isEmpty(item.getPatternMaking().getPatternNo())||"null".equals(item.getPatternMaking().getPatternNo())){
            tv_no.setText("纸样编号："+"未录入");
        }else{
            tv_no.setText("纸样编号："+item.getPatternMaking().getPatternNo());
        }
        tv_designer.setText("设计师："+item.getPatternMaking().getDesignByName());
        String type="款式："+item.getPatternMaking().getProductCategory1Name()+" "+item.getPatternMaking().getProductCategory2Name();
        tv_name.setText(type.replace("null",""));
        tv_time.setText(item.getPatternMaking().getCreateTime().substring(0,10));
    }
    private void setProduceData(View view,Notify item){
        ImageView iv_img=view.findViewById(R.id.iv_img);
        TextView tv_locate=view.findViewById(R.id.tv_locate);
        TextView tv_time=view.findViewById(R.id.tv_time);
        TextView tv_sampleno=view.findViewById(R.id.tv_sampleno);
        TextView tv_num=view.findViewById(R.id.tv_num);
        TextView tv_start=view.findViewById(R.id.tv_start);
        TextView tv_end=view.findViewById(R.id.tv_end);
        TextView tv_status=view.findViewById(R.id.tv_status);
        tv_sampleno.setText(item.getProductionOrder().getSampleNo());
        tv_time.setText(item.getProductionOrder().getMonth());
        tv_locate.setText(item.getProductionOrder().getProductOrderNumber());
        tv_num.setText("裁片数量："+item.getProductionOrder().getQuantity());
        tv_start.setText("开始时间："+item.getProductionOrder().getGmtCreate().substring(0,14));
        if(item.getProductionOrder().getStatus()==2){
            tv_end.setText("结束时间："+item.getProductionOrder().getFinishTime());
        }else if(item.getProductionOrder().getStatus()==1){
            tv_end.setText("预计时间："+item.getProductionOrder().getEstimatedTimeOfFinishment());
        }else{
            tv_end.setText("终止时间："+item.getProductionOrder().getStopTime());
        }
        if (!TextUtils.isEmpty(item.getProductionOrder().getImageUrl()))
            Glide.with(mContext).load(item.getProductionOrder().getImageUrl()).into(iv_img);
        else
            iv_img.setImageResource(R.mipmap.image_load_err);
        tv_status.setText(item.getProductionOrder().getStageName());
        switch (item.getProductionOrder().getStageName()){
            case "车间":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_sew));
                break;
            case "洗水":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_wash));
                break;
            case "后整":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_after));
                break;
            case "完成":
            case "完成（已入库）":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_finsh));
                break;
            case "终止":
                tv_status.setTextColor(mContext.getResources().getColor(R.color.produce_stop));
                break;

        }
    }
    private void setApplyData(View view,Notify item){
          if(item.getPush().getType()==3){
              setApply(view,item);
          }else{
              setCenter(view,item);
          }
    }
    private void setCenter(View view,Notify item){
        TextView tv_type1=view.findViewById(R.id.tv_type1);
        TextView tv_type2=view.findViewById(R.id.tv_type2);
        TextView tv_title=view.findViewById(R.id.tv_title);
        TextView tv_status=view.findViewById(R.id.tv_status);
        TextView tv_content=view.findViewById(R.id.tv_content);
        TextView tv_applvers=view.findViewById(R.id.tv_applvers);
        TextView tv_time=view.findViewById(R.id.tv_time);
        tv_applvers.setText("申请人："+item.getApproval().getApplyByName());
        tv_content.setText(item.getApproval().getContent());
        tv_time.setText(item.getApproval().getApplyTime());
        tv_title.setText(item.getApproval().getTitle());
        if(item.getApproval().getType()==1){
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.GONE);
        }else{
            tv_type2.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.GONE);
        }
        if(item.getApproval().getStatus()==0){
            tv_status.setText("待审批");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_org));
        }else if(item.getApproval().getStatus()==1){
            tv_status.setText("已通过");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.btn_green_noraml));
        }else{
            tv_status.setText("已拒绝");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_red));
        }
    }
    private void setApply(View view,Notify item){
        TextView tv_type1=view.findViewById(R.id.tv_type1);
        TextView tv_type2=view.findViewById(R.id.tv_type2);
        TextView tv_title=view.findViewById(R.id.tv_title);
        TextView tv_status=view.findViewById(R.id.tv_status);
        TextView tv_content=view.findViewById(R.id.tv_content);
        TextView tv_applvers=view.findViewById(R.id.tv_applvers);
        TextView tv_time=view.findViewById(R.id.tv_time);
        tv_applvers.setText("审批人："+item.getApproval().getApproveByName());
        tv_content.setText(item.getApproval().getContent());
        tv_time.setText(item.getApproval().getApplyTime());
        tv_title.setText(item.getApproval().getTitle());
        if(item.getApproval().getType()==1){
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.GONE);
        }else{
            tv_type2.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.GONE);
        }
        if(item.getApproval().getStatus()==0){
            tv_status.setText("待审批");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_org));
        }else if(item.getApproval().getStatus()==1){
            tv_status.setText("已通过");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.btn_green_noraml));
        }else{
            tv_status.setText("已拒绝");
            tv_status.setTextColor(mContext.getResources().getColor(R.color.color_red));
        }
    }
}
