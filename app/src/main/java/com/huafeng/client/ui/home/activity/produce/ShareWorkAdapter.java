package com.huafeng.client.ui.home.activity.produce;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.view.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class ShareWorkAdapter extends BaseQuickAdapter<ShareWork, BaseViewHolder> {
    public ShareWorkAdapter(@Nullable List<ShareWork> data) {
        super(R.layout.item_share_work, data);
    }

    private OnAddListener onAddListener;

    public interface OnAddListener {
        void onAdd(int pos, ShareChildAdapter shareChildAdapter);
        void onSelect(int pos,ShareChildAdapter shareChildAdapter);
        void onSubmit(int pos);
    }

    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShareWork item) {
        helper.setIsRecyclable(false);
        //helper.addOnClickListener(R.id.tv_add1);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_number = helper.getView(R.id.tv_number);
        tv_number.setText("待分配数量:" + item.getNum());
        TextView tv_add2 = helper.getView(R.id.tv_add2);
        TextView tv_add1 = helper.getView(R.id.tv_add1);
        TextView tv_submit=helper.getView(R.id.tv_submit);

        if(item.getStatus()>=2){
            tv_add1.setVisibility(View.GONE);
            tv_add2.setVisibility(View.GONE);
            tv_submit.setVisibility(View.GONE);
        }else{
            tv_add1.setVisibility(View.VISIBLE);
            tv_add2.setVisibility(View.VISIBLE);
            tv_submit.setVisibility(View.VISIBLE);
        }
        tv_name.setText(item.getProcessName());
        RecyclerView rv_child = helper.getView(R.id.rv_child);
        List<ShareWork.WorkallocationListBean> workallocationListBeans = new ArrayList<>();
        if (item.getWorkallocationList() != null)
            for (int i = 0; i < item.getWorkallocationList().size(); i++) {
                ShareWork.WorkallocationListBean workallocationListBean = item.getWorkallocationList().get(i);
                // workallocationListBean.setPrice(item.getPrice());
                workallocationListBeans.add(workallocationListBean);
            }
        ShareChildAdapter shareChildAdapter = new ShareChildAdapter(workallocationListBeans);
        shareChildAdapter.setOnEditChangeListener(new ShareChildAdapter.OnEditChangeListener() {
            @Override
            public void onEdit(int type, int pos, String value) {
                if (TextUtils.isEmpty(value)) {
                    return;
                }
                switch (type) {
                    case 1:
                        getData().get(helper.getAdapterPosition()).getWorkallocationList().get(pos).setTemporaryWorkerName(value);
                        break;
                    case 2:
                        if(MathUtils.isInteger(value))
                        getData().get(helper.getAdapterPosition()).getWorkallocationList().get(pos).setQuantityAllotted(Integer.parseInt(value));
                        break;
                    case 3:
                        if(MathUtils.isNumber(value))
                        getData().get(helper.getAdapterPosition()).getWorkallocationList().get(pos).setPrice(Double.parseDouble(value));
                        break;
                }
            }

            @Override
            public void onSelect(int pos) {
                onAddListener.onSelect(pos,shareChildAdapter);
            }
        });
        rv_child.setLayoutManager(new LinearLayoutManager(mContext));

        rv_child.setAdapter(shareChildAdapter);
        tv_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShareWork.WorkallocationListBean> workallocationListBeans = new ArrayList<>();
                ShareWork.WorkallocationListBean workallocationListBean = new ShareWork.WorkallocationListBean();
                workallocationListBean.setPayType(2);
                workallocationListBean.setEmployeeRecordId(-1);
                workallocationListBean.setPrice(item.getPrice());

                workallocationListBeans.add(workallocationListBean);
                shareChildAdapter.addData(workallocationListBean);
                getData().get(helper.getAdapterPosition()).setWorkallocationList(shareChildAdapter.getData());

            }
        });
        tv_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddListener.onAdd(helper.getAdapterPosition(), shareChildAdapter);
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddListener.onSubmit(helper.getAdapterPosition());
            }
        });
    }


}
