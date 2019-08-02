package com.huafeng.client.ui.home.activity.origin;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huafeng.client.R;
import com.huafeng.client.view.CursorEditText;
import com.huafeng.client.view.MathUtils;

import java.util.List;

public class ChecDataAdapter extends BaseQuickAdapter<ChecData, BaseViewHolder> {
    public ChecDataAdapter( @Nullable List<ChecData> data) {
        super(R.layout.item_check_entry, data);
    }

    public void setOnEditListener(OnEditListener onEditListener) {
        this.onEditListener = onEditListener;
    }

    OnEditListener onEditListener;
    public interface OnEditListener{
        void onEdit(String value);
    }
    @Override
    protected void convert(BaseViewHolder helper, ChecData item) {
        TextView tvName=helper.getView(R.id.tv_item2);
        CursorEditText etName=helper.getView(R.id.et_name);
        tvName.setText(item.getName());
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String value=s.toString();
                if(!TextUtils.isEmpty(value)&& MathUtils.isInteger(value)){
                    getData().get(helper.getAdapterPosition()).setQuantity(Integer.valueOf(value));
                }
                onEditListener.onEdit(value);
            }
        });
    }
}
