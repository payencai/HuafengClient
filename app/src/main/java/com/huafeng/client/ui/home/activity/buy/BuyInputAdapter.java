package com.huafeng.client.ui.home.activity.buy;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/8 10:50
 * 邮箱：771548229@qq..com
 */
public class BuyInputAdapter extends BaseAdapter {
    private int mTouchItemPosition = -1;
    Context mContext;

    List<BuyOrderDetail.NoteListBean.SupplierListBean> supplierListBeans;
    private ViewHolder mViewHolder;

    public BuyInputAdapter(Context context, List<BuyOrderDetail.NoteListBean.SupplierListBean> supplierListBeans) {
        mContext = context;
        supplierListBeans = supplierListBeans;
    }

    private BuyInputAdapter.onChangeListener mOnChangeListener;

    public void setOnChangeListener(BuyInputAdapter.onChangeListener mOnChangeListener) {
        this.mOnChangeListener = mOnChangeListener;
    }

    public interface onChangeListener {
        void onChange(int pos);

    }

    @Override
    public int getCount() {
        return supplierListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return supplierListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean = supplierListBeans.get(position);
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_buy_submit, null);
            mViewHolder.mTvName =  convertView.findViewById(R.id.tv_name);
            mViewHolder.mTvNum =  convertView.findViewById(R.id.tv_num);
            mViewHolder.mEtRaw = convertView.findViewById(R.id.et_cloth);
            mViewHolder.mEtNum = convertView.findViewById(R.id.et_number);
            mViewHolder.mEtPrice = convertView.findViewById(R.id.et_price);
            mViewHolder.mEtRaw.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                    mTouchItemPosition = (Integer) view.getTag();
                    return false;
                }
            });
            mViewHolder.mEtNum.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                    mTouchItemPosition = (Integer) view.getTag();
                    return false;
                }
            });
            mViewHolder.mEtPrice.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                    mTouchItemPosition = (Integer) view.getTag();
                    return false;
                }
            });
            // 让ViewHolder持有一个TextWathcer，动态更新position来防治数据错乱；不能将position定义成final直接使用，必须动态更新
            mViewHolder.mTextWatcher = new MyTextWatcher();
            mViewHolder.mEtRaw.addTextChangedListener(mViewHolder.mTextWatcher);
            mViewHolder.mEtNum.addTextChangedListener(mViewHolder.mTextWatcher);
            mViewHolder.mEtPrice.addTextChangedListener(mViewHolder.mTextWatcher);
            mViewHolder.updatePosition(position);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
            mViewHolder.updatePosition(position);
        }

        mViewHolder.mTvName.setText(supplierListBean.getMaterialName());
        mViewHolder.mTvNum.setText(supplierListBean.getNeedQuantity() + "");
        mViewHolder.mEtRaw.setText(supplierListBean.getClothQuantity()+"");

        mViewHolder.mEtRaw.setTag(position);
        mViewHolder.mEtPrice.setTag(position);
        mViewHolder.mEtNum.setTag(position);

        if (mTouchItemPosition == position) {

            mViewHolder.mEtPrice.requestFocus();
            mViewHolder.mEtNum.requestFocus();
            mViewHolder.mEtRaw.requestFocus();

            mViewHolder.mEtPrice.setSelection(mViewHolder.mEtPrice.getText().length());
            mViewHolder.mEtNum.setSelection(mViewHolder.mEtNum.getText().length());
            mViewHolder.mEtRaw.setSelection(mViewHolder.mEtRaw.getText().length());
        } else {
            mViewHolder.mEtPrice.clearFocus();
            mViewHolder.mEtNum.clearFocus();
            mViewHolder.mEtRaw.clearFocus();
        }

        return convertView;
    }

    static final class ViewHolder {
        TextView mTvName;
        TextView mTvNum;
        EditText mEtRaw;
        EditText mEtNum;
        EditText mEtPrice;
        MyTextWatcher mTextWatcher;

        //动态更新TextWathcer的position
        public void updatePosition(int position) {
            mTextWatcher.updatePosition(position);
        }
    }

    class MyTextWatcher implements TextWatcher {
        //由于TextWatcher的afterTextChanged中拿不到对应的position值，所以自己创建一个子类
        private int mPosition;

        public void updatePosition(int position) {
            mPosition = position;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            BuyOrderDetail.NoteListBean.SupplierListBean supplierListBean = supplierListBeans.get(mPosition);
            if (!TextUtils.isEmpty(s.toString())) {
                supplierListBean.setClothQuantity(s.toString());
                supplierListBeans.set(mPosition, supplierListBean);
            }
        }
    }



}
