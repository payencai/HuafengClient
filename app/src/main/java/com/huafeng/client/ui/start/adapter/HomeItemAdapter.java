package com.huafeng.client.ui.start.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.model.HomeItem;

import java.util.List;

/**
 * 作者：凌涛 on 2019/3/21 14:45
 * 邮箱：771548229@qq..com
 */
public class HomeItemAdapter extends BaseAdapter {
    Context mContext;
    List<HomeItem> mHomeItems;

    public HomeItemAdapter(Context context, List<HomeItem> homeItems) {
        mContext = context;
        mHomeItems = homeItems;
    }

    @Override

    public int getCount() {
        return mHomeItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mHomeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_gv_home,null);
        ImageView iv_logo=convertView.findViewById(R.id.iv_logo);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        HomeItem homeItem=mHomeItems.get(position);
        switch (homeItem.getIndex()){
            case 0:
                iv_logo.setImageResource(R.mipmap.ic_home1);
                tv_name.setText("设计制版");
                break;
            case 1:
                iv_logo.setImageResource(R.mipmap.ic_home2);
                tv_name.setText("销售订单");
                break;
            case 2:
                iv_logo.setImageResource(R.mipmap.ic_home3);
                tv_name.setText("生产管理");
                break;
            case 3:
                iv_logo.setImageResource(R.mipmap.ic_home4);
                tv_name.setText("采购管理");
                break;
            case 4:
                iv_logo.setImageResource(R.mipmap.ic_home5);
                tv_name.setText("仓储管理");
                break;
            case 5:
                iv_logo.setImageResource(R.mipmap.ic_home6);
                tv_name.setText("资料中心");
                break;
            case 6:
                iv_logo.setImageResource(R.mipmap.ic_home7);
                tv_name.setText("审批申请");
                break;
            case 7:
                iv_logo.setImageResource(R.mipmap.ic_home8);
                tv_name.setText("审批中心");
                break;
            case 8:
                iv_logo.setImageResource(R.mipmap.ic_home9);
                tv_name.setText("客户管理");
                break;
            case 9:
                iv_logo.setImageResource(R.mipmap.ic_home10);
                tv_name.setText("数据统计");
                break;

        }
        return convertView;
    }
}
