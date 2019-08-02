package com.huafeng.client.ui.home.activity.produce;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

public class ProduceUseAdapter extends BaseAdapter {
    Context context;
    List<ProduceDetail.MaterialTakeListBean> materialListBeans;
    private  OnItemUserClickListener onItemUserClickListener;
    public  interface  OnItemUserClickListener{
        void OnClick(int pos,TextView view);
    }


    public void setOnItemUserClickListener(OnItemUserClickListener onItemUserClickListener) {
        this.onItemUserClickListener = onItemUserClickListener;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    int state;
    public ProduceUseAdapter(Context context, List<ProduceDetail.MaterialTakeListBean> materialListBeans,int state) {
        this.context = context;
        this.materialListBeans = materialListBeans;
        this.state=state;
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
        ProduceDetail.MaterialTakeListBean item=materialListBeans.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.item_detail_user,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        TextView tv_num1=convertView.findViewById(R.id.tv_num1);
        TextView tv_num2=convertView.findViewById(R.id.tv_num2);
        TextView tv_state=convertView.findViewById(R.id.tv_state);
        TextView tv_work=convertView.findViewById(R.id.tv_work);
        tv_name.setText(item.getMaterialName());
        tv_num1.setText(""+item.getQuantityNeed());
        tv_num2.setText(""+item.getQuantityCanTake());
        if(state==1&&item.getIsTake()==0&&item.getQuantityCanTake()>=item.getQuantityNeed()){
            tv_state.setText("已领用");
            tv_work.setBackgroundResource(R.drawable.bg_btn_blue);
            tv_work.setEnabled(true);

        }else{
            tv_state.setText("未领用");
            tv_work.setBackgroundResource(R.drawable.bg_btn_grey);
            tv_work.setEnabled(false);
        }
        tv_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemUserClickListener.OnClick(position,tv_work);
            }
        });
        Log.e("state",state+"-"+item.getIsTake());
        return convertView;
    }
}
