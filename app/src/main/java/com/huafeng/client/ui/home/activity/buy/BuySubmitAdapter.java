package com.huafeng.client.ui.home.activity.buy;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

public class BuySubmitAdapter extends BaseQuickAdapter<BuyOrderDetail.NoteListBean.SupplierListBean, BaseViewHolder> {
    public BuySubmitAdapter(@Nullable List<BuyOrderDetail.NoteListBean.SupplierListBean> data) {
        super(R.layout.item_buy_submit, data);
    }

    private OnEditChangeListener onEditChangeListener;

    public interface OnEditChangeListener {
        void onEdit(int type, int pos, String price, TextView tvTotal);

        void onSelect(int pos, TextView view);

        void onDelete(int pos);

        void onAdd(int pos);
    }

    public void setOnEditChangeListener(OnEditChangeListener onEditChangeListener) {
        this.onEditChangeListener = onEditChangeListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, BuyOrderDetail.NoteListBean.SupplierListBean item) {
        helper.setIsRecyclable(false);
        TextView tv_name = helper.getView(R.id.tv_name);
        LinearLayout ll_add = helper.getView(R.id.ll_add);
        TextView tv_add = helper.getView(R.id.tv_add);
        TextView tv_delete = helper.getView(R.id.tv_delete);
        RelativeLayout rl_shop = helper.getView(R.id.rl_shop);
        TextView tv_num = helper.getView(R.id.tv_num);
        TextView tv_shop = helper.getView(R.id.tv_shop);
        EditText et_price = helper.getView(R.id.et_price);
        EditText et_number = helper.getView(R.id.et_number);
        EditText et_cloth = helper.getView(R.id.et_cloth);
        TextView tv_total = helper.getView(R.id.tv_total);




        tv_shop.setText(item.getSupplierName());
        tv_name.setText(item.getMaterialName());
        if (item.getNeedQuantity() != null)
            tv_num.setText(item.getNeedQuantity() + "");
        et_cloth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onEditChangeListener.onEdit(1, helper.getAdapterPosition(), s.toString(), tv_total);
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
                onEditChangeListener.onEdit(2, helper.getAdapterPosition(), s.toString(), tv_total);
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
                onEditChangeListener.onEdit(3, helper.getAdapterPosition(), s.toString(), tv_total);
            }
        });
        if (item.isShowDel()) {
            tv_delete.setVisibility(View.VISIBLE);
        } else {
            tv_delete.setVisibility(View.GONE);
        }
        if (item.isShowAdd()) {
            ll_add.setVisibility(View.VISIBLE);
        } else {
            ll_add.setVisibility(View.GONE);
        }
        rl_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditChangeListener.onSelect(helper.getAdapterPosition(), tv_shop);
            }
        });
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditChangeListener.onDelete(helper.getAdapterPosition());
            }
        });
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditChangeListener.onAdd(helper.getAdapterPosition());
            }
        });
        if(!TextUtils.isEmpty(item.getClothQuantity())){
            et_cloth.setText(item.getClothQuantity());
        }
        if(item.getUnitPrice()!=null){
            et_price.setText(item.getUnitPrice()+"");
        }
        if(item.getQuantity()!=null){
            et_number.setText(item.getQuantity()+"");
        }
        if(!item.isHeader()){
            et_cloth.setVisibility(View.GONE);
        }
    }
}
