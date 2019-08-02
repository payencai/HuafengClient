package com.huafeng.client.ui.home.activity.produce;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class ShareChildAdapter extends BaseQuickAdapter<ShareWork.WorkallocationListBean, BaseViewHolder> {
    public ShareChildAdapter(@Nullable List<ShareWork.WorkallocationListBean> data) {
        super(R.layout.item_work_child, data);
    }

    private OnEditChangeListener onEditChangeListener;

    public interface OnEditChangeListener {
        void onEdit(int type, int pos, String price);
        void onSelect(int pos);
    }

    public void setOnEditChangeListener(OnEditChangeListener onEditChangeListener) {
        this.onEditChangeListener = onEditChangeListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShareWork.WorkallocationListBean item) {
        helper.setIsRecyclable(false);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_now = helper.getView(R.id.tv_now);
        Button btnDelete=helper.getView(R.id.btnDelete);
        FrameLayout fr_type=helper.getView(R.id.fr_type);
        SwipeMenuLayout sml_del=helper.getView(R.id.sml_del);
        RelativeLayout rl_type = helper.getView(R.id.rl_type);
        EditText et_price = helper.getView(R.id.et_price);
        EditText et_number = helper.getView(R.id.et_number);
        EditText et_name = helper.getView(R.id.et_name);
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 onEditChangeListener.onEdit(1, helper.getAdapterPosition(), s.toString());
            }
        });
        et_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onEditChangeListener.onEdit(2, helper.getAdapterPosition(), s.toString());
            }
        });
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onEditChangeListener.onEdit(3, helper.getAdapterPosition(), s.toString());
            }
        });
        if(item.getStatus()>=2){
            et_name.setFocusableInTouchMode(false);//不可编辑
            et_number.setFocusableInTouchMode(false);//不可编辑
            et_price.setFocusableInTouchMode(false);//不可编辑
            fr_type.setClickable(false);
            fr_type.setEnabled(false);
            fr_type.setFocusable(false);
            sml_del.setSwipeEnable(false);
            if (!TextUtils.isEmpty(item.getTemporaryWorkerName())){
                et_name.setText(item.getTemporaryWorkerName());
                et_name.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.GONE);
            }else{
                tv_name.setText(item.getName());
                tv_name.setVisibility(View.VISIBLE);
                et_name.setVisibility(View.GONE);
            }
        }else{
            sml_del.setSwipeEnable(true);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(helper.getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            if (item.getEmployeeRecordId()>0){
                tv_name.setText(item.getName());
                tv_name.setVisibility(View.VISIBLE);
                et_name.setVisibility(View.GONE);
            }else{
                et_name.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.GONE);
            }
        }

        if (item.getPayType() == 1) {
            tv_now.setVisibility(View.GONE);
            rl_type.setVisibility(View.VISIBLE);
        } else {
            tv_now.setVisibility(View.VISIBLE);
            rl_type.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(item.getName())){
            tv_name.setText(item.getName());
        }
        fr_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditChangeListener.onSelect(helper.getAdapterPosition());
            }
        });
        if(item.getPrice()>0)
            et_price.setText(item.getPrice() + "");
        if (item.getQuantityAllotted() > 0)
            et_number.setText(item.getQuantityAllotted() + "");
    }
}
