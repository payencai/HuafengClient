package com.huafeng.client.ui.home.activity.sample;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SampleSectionAdapter extends BaseSectionQuickAdapter<SampleSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *

     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SampleSectionAdapter(List data) {
        super(R.layout.item_order_process, R.layout.item_process_header, data);
    }
    private  OnItemSelectListener onItemSelectListener;

    @Override
    protected void convertHead(BaseViewHolder helper, SampleSection item) {
        RelativeLayout rl_header = helper.getView(R.id.rl_header);
        if(helper.getAdapterPosition()==0){
            rl_header.setVisibility(View.VISIBLE);
        }else{
            rl_header.setVisibility(View.GONE);
        }

    }

    public interface OnItemSelectListener{
        void onSelect(int pos, ImageView imageView);
        void onChange(int pos,String price);
    }
    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }
    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleSection item) {
        helper.setIsRecyclable(false);
        SelectProcess.ProcessListBean productProcessListBean = item.t;
        TextView tv_process = helper.getView(R.id.tv_process);
        EditText et_price=helper.getView(R.id.et_price);
        et_price.setVisibility(View.VISIBLE);
        TextView tv_name = helper.getView(R.id.tv_name);
        if(productProcessListBean.isHeader()){
            tv_name.setText(productProcessListBean.getProcessName());
            tv_name.setVisibility(View.VISIBLE);
        }else{
            tv_name.setVisibility(View.GONE);
        }
        if(productProcessListBean.getPrice()!=null)
           et_price.setText(productProcessListBean.getPrice()+"");
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onItemSelectListener.onChange(helper.getLayoutPosition(),s.toString());
            }
        });

        LogUtils.e(helper.getOldPosition()+"**"+helper.getLayoutPosition());
        LinearLayout ll_out = helper.getView(R.id.ll_out);
        ImageView iv_check=helper.getView(R.id.iv_check);
        tv_process.setText(productProcessListBean.getName());
        ll_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelectListener.onSelect(helper.getAdapterPosition(),iv_check);
            }
        });
        if(productProcessListBean.getIsOutsourcing()==1){
            iv_check.setImageResource(R.mipmap.ic_check);
        }else{
            iv_check.setImageResource(R.mipmap.ic_uncheck);
        }
        if(productProcessListBean.getProcessName().equals("裁片")){
            ll_out.setVisibility(View.GONE);
        }else{
            ll_out.setVisibility(View.VISIBLE);
        }
    }
}
