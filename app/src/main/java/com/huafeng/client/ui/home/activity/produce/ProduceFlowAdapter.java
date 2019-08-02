package com.huafeng.client.ui.home.activity.produce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.Auth;

import org.litepal.LitePal;

import java.util.List;

public class ProduceFlowAdapter extends BaseAdapter {
    Context context;
    ProduceDetailActivity activity;
    List<ProduceDetail.FlowListBean> materialListBeans;
    String total;

    public ProduceFlowAdapter(Context context, List<ProduceDetail.FlowListBean> materialListBeans, String total) {
        this.context = context;
        this.materialListBeans = materialListBeans;
        this.total = total;
        activity= (ProduceDetailActivity) context;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public int getCount() {
        return materialListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return materialListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProduceDetail.FlowListBean item = materialListBeans.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_product_flow, null);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_num = convertView.findViewById(R.id.tv_num);
        LinearLayout ll_item1=convertView.findViewById(R.id.ll_item1);
        LinearLayout ll_item2=convertView.findViewById(R.id.ll_item2);
        TextView tv_cost = convertView.findViewById(R.id.tv_cost);
        TextView tv_name1 = convertView.findViewById(R.id.tv_name1);
        TextView tv_name2 = convertView.findViewById(R.id.tv_name2);
        TextView tv_name3 = convertView.findViewById(R.id.tv_name3);
        TextView tv_time1 = convertView.findViewById(R.id.tv_time1);
        TextView tv_time2 = convertView.findViewById(R.id.tv_time2);
        TextView tv_time3 = convertView.findViewById(R.id.tv_time3);
        TextView tv_content = convertView.findViewById(R.id.tv_content);
        TextView tv_do1 = convertView.findViewById(R.id.tv_do1);
        TextView tv_do2 = convertView.findViewById(R.id.tv_do2);
        RelativeLayout rl_type1 = convertView.findViewById(R.id.rl_type1);
        RelativeLayout rl_type2 = convertView.findViewById(R.id.rl_type2);
        RelativeLayout rl_type3 = convertView.findViewById(R.id.rl_type3);
        LinearLayout ll_type4 = convertView.findViewById(R.id.rl_type4);
        tv_do1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (position == 1) {
                    intent = new Intent(context, SeeDataActivity.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("total", total);
                    context.startActivity(intent);
                } else {
                    if (item.getOutsourcingStatus() == -1) {
                        intent = new Intent(activity, WorkAssignActivity.class);
                        intent.putExtra("id", item.getId());
                        intent.putExtra("status", item.getWorkAllcationStatus());
                        intent.putExtra("num", item.getQuantity());
                        intent.putExtra("orderId", item.getProductionOrderId());
                        activity.startActivityForResult(intent,3);
                        //工作分配
                    } else if (item.getOutsourcingStatus() == 0) {

                        intent = new Intent(activity, CreateGetOrderActivity.class);
                        intent.putExtra("id", item.getOutsourcingPickupNoteId());
                        if(item.getStageId()==3){
                            intent.putExtra("type",2);
                        }else{
                            intent.putExtra("type",3);
                        }
                        intent.putExtra("orderId", item.getProductionOrderId());
                        activity.startActivityForResult(intent,1);
                    } else if (item.getOutsourcingStatus() >= 1) {
                        intent = new Intent(context, SeeReceiverOrderActivity.class);
                        intent.putExtra("id", item.getOutsourcingPickupNoteId());
                        context.startActivity(intent);
                    }
                }
            }
        });
        tv_do2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (item.getOutsourcingStatus() == 1) {
                    intent = new Intent(activity, CreateSendOrderActivity.class);
                    intent.putExtra("id", item.getOutsourcingDeliveryNoteId());
                    intent.putExtra("orderId", item.getProductionOrderId());
                    activity.startActivityForResult(intent,2);
                    //创建
                } else if (item.getOutsourcingStatus() == 2) {
                    //查看
                    intent = new Intent(context, SeeSendOrderActivity.class);
                    intent.putExtra("id", item.getOutsourcingDeliveryNoteId());
                    context.startActivity(intent);
                }
            }

        });
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
            if (item.getOutsourcingStatus() == -1) {
                rl_type2.setVisibility(View.GONE);
                tv_name.setText(item.getTitle());
                tv_name1.setText(item.getTitle() + "工序开始");
                tv_name3.setText(item.getTitle() + "工序完成");
                tv_content.setText("工序内容：" + item.getProcessNames());
                tv_time1.setText(item.getGmtCreate() + "");
                tv_time3.setText(item.getGmtFinish());
                tv_num.setText(item.getQuantity() + "");
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
                tv_time2.setText(item.getOutsourcingDeliveryTime() + "");
                tv_time3.setText(item.getGmtFinish());
                tv_num.setText(item.getQuantity() + "");
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
                } else if (item.getOutsourcingStatus() == 1) {
                    rl_type2.setVisibility(View.VISIBLE);
                } else {

                }

            }
        }
        return convertView;
    }
}
