package com.huafeng.client.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.model.Account;

import java.util.List;

public class AccountAdapter extends BaseAdapter {
    int pos=0;
    Context context;
    List<Account> accounts;
    public interface OnItemClickListener{
        void onClick(int pos);
        void onDel(int position);
    }
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return accounts.size();
    }

    public int getPos() {
        return pos;
    }

    public AccountAdapter(Context context, List<Account> accounts) {
        this.context = context;
        this.accounts = accounts;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public Object getItem(int position) {
        return accounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_account,null);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        LinearLayout ll_item=convertView.findViewById(R.id.ll_item);
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);
            }
        });

        Button btnDelete=convertView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onDel(position);
            }
        });
        tv_name.setText(accounts.get(position).getUsername());
        TextView tv_current=convertView.findViewById(R.id.tv_current);
        if(pos==position){
            tv_current.setVisibility(View.VISIBLE);
        }else{
            tv_current.setVisibility(View.GONE);
        }
        return convertView;
    }
}
