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
import com.huafeng.client.view.MathUtils;


import java.util.List;

/**
 * 作者：凌涛 on 2019/5/8 10:50
 * 邮箱：771548229@qq..com
 */
public class WaitInputAdapter extends BaseAdapter {
    private int mTouchItemPosition = -1;
    Context mContext;

    List<AskList.RequisitionListBean> mSizeTypes;
    private ViewHolder mViewHolder;

    public WaitInputAdapter(Context context, List<AskList.RequisitionListBean> sizeTypes) {
        mContext = context;
        mSizeTypes = sizeTypes;
    }

    private WaitInputAdapter.onChangeListener mOnChangeListener;

    public void setOnChangeListener(WaitInputAdapter.onChangeListener mOnChangeListener) {
        this.mOnChangeListener = mOnChangeListener;
    }

    public interface onChangeListener {
        void onChange(int pos);

    }

    @Override
    public int getCount() {
        return mSizeTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return mSizeTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AskList.RequisitionListBean sizeType = mSizeTypes.get(position);
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ask_create, null);
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.mEditText = (EditText) convertView.findViewById(R.id.et_input);
            mViewHolder.mTvNum = convertView.findViewById(R.id.tv_num);
            mViewHolder.mEditText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                    mTouchItemPosition = (Integer) view.getTag();
                    return false;
                }
            });

            // 让ViewHolder持有一个TextWathcer，动态更新position来防治数据错乱；不能将position定义成final直接使用，必须动态更新
            mViewHolder.mTextWatcher = new MyTextWatcher();
            mViewHolder.mEditText.addTextChangedListener(mViewHolder.mTextWatcher);
            mViewHolder.updatePosition(position);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
            mViewHolder.updatePosition(position);
        }

        mViewHolder.mTextView.setText(sizeType.getMaterialName());
        if(sizeType.getAskNum()!=null)
           mViewHolder.mEditText.setText(sizeType.getAskNum() + "");
        mViewHolder.mTvNum.setText(sizeType.getQuantityDemanded()+"");
        mViewHolder.mEditText.setTag(position);
        if (mTouchItemPosition == position) {
            mViewHolder.mEditText.requestFocus();
            mViewHolder.mEditText.setSelection(mViewHolder.mEditText.getText().length());
        } else {
            mViewHolder.mEditText.clearFocus();
        }

        return convertView;
    }

    static final class ViewHolder {
        TextView mTextView;
        EditText mEditText;
        TextView mTvNum;
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
            AskList.RequisitionListBean requisitionListBean = mSizeTypes.get(mPosition);
            if (!TextUtils.isEmpty(s.toString())) {
                if(MathUtils.isNumber(s.toString())){
                    requisitionListBean.setAskNum(Double.parseDouble(s.toString()));
                    mSizeTypes.set(mPosition, requisitionListBean);
                }

            }
        }
    }



}
