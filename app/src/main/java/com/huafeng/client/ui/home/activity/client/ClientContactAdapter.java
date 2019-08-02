package com.huafeng.client.ui.home.activity.client;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.CustomerData;
import com.huafeng.client.view.sidebar.IndexableAdapter;


import java.util.List;


/**
 * Created by YoKey on 16/10/8.
 */
public class ClientContactAdapter extends IndexableAdapter<CustomerData> {
    private LayoutInflater mInflater;

    public ClientContactAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_list_index, parent, false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_custom_data, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle, List<Object> payloads) {
        IndexVH vh = (IndexVH) holder;
        vh.tv.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, CustomerData item, List<Object> payloads) {
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(item.getName());
        vh.tvTime.setText("添加时间："+item.getGmtCreate().substring(0,10));
        vh.tvNum.setText("订单数量："+item.getOrderQuantity());
        vh.tvAddress.setText(item.getProvince()+" "+item.getCity());
        if(!TextUtils.isEmpty(item.getImageUrl())){
            Glide.with(holder.itemView.getContext()).load(item.getImageUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(vh.ivAvatar);
        }else{
            Glide.with(holder.itemView.getContext()).load("http://img2.3png.com/09541447ec671987f5e015b924384b89cf5d.png").apply(RequestOptions.bitmapTransform(new CircleCrop())).into(vh.ivAvatar);
        }

    }

    private class IndexVH extends RecyclerView.ViewHolder {
        TextView tv;

        public IndexVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivAvatar;
        TextView tvAddress;
        TextView tvTime;
        TextView tvNum;
        public ContentVH(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_head);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvNum = (TextView) itemView.findViewById(R.id.tv_num);
        }
    }
}
