package com.huafeng.client.ui.home.activity.origin;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.view.MathUtils;

import java.util.List;

public class BackgoodsAdapter extends BaseQuickAdapter<Backgoods, BaseViewHolder> {
    public BackgoodsAdapter( @Nullable List<Backgoods> data) {
        super(R.layout.item_back_good, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Backgoods item) {
        TextView tv_no=helper.getView(R.id.tv_no);
        TextView tv_type=helper.getView(R.id.tv_type);
        EditText et_remark=helper.getView(R.id.et_remark);
        EditText et_num=helper.getView(R.id.et_num);
        EditText et_cloth=helper.getView(R.id.et_cloth);
        EditText et_sew=helper.getView(R.id.et_sew);
        EditText et_wash=helper.getView(R.id.et_wash);
        EditText et_back=helper.getView(R.id.et_back);
        et_remark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 getData().get(helper.getAdapterPosition()).setRemarks(s.toString());
            }
        });
        et_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 if(!TextUtils.isEmpty(s.toString())){
                     if(MathUtils.isNumber(s.toString())){
                         int num= Integer.parseInt(s.toString());
                         getData().get(helper.getAdapterPosition()).setQuantity(num);
                     }

                 }
            }
        });
        et_cloth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString())){
                    if(MathUtils.isNumber(s.toString())){
                        int num= Integer.parseInt(s.toString());
                        getData().get(helper.getAdapterPosition()).setClothQuantity(num);
                    }

                }
            }
        });
        et_back.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString())){
                    if(MathUtils.isNumber(s.toString())){
                        int num= Integer.parseInt(s.toString());
                        getData().get(helper.getAdapterPosition()).setRepairQuantity(num);
                    }

                }
            }
        });
        et_sew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString())){
                    if(MathUtils.isNumber(s.toString())){
                        int num= Integer.parseInt(s.toString());
                        getData().get(helper.getAdapterPosition()).setSewQuantity(num);
                    }

                }
            }
        });
        et_wash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString())){
                    if(MathUtils.isNumber(s.toString())){
                        int num= Integer.parseInt(s.toString());
                        getData().get(helper.getAdapterPosition()).setWashQuantity(num);
                    }

                }
            }
        });
        tv_no.setText(item.getSampleNo());
        tv_type.setText(item.getProductCategory1Name()+"/"+item.getProductCategory2Name());

    }
}
