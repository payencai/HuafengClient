package com.huafeng.client.ui.home.activity.sample;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huafeng.client.R;

import java.util.List;

/**
 * 作者：凌涛 on 2019/5/8 10:50
 * 邮箱：771548229@qq..com
 */
public class SampleSizeInputAdapter extends BaseAdapter {
    private int mTouchItemPosition = -1;
    Context mContext;
    List<SampleSize.SampleSizeInformationListBean> mSizeTypes;
    private ViewHolder mViewHolder;

    public SampleSizeInputAdapter(Context context, List<SampleSize.SampleSizeInformationListBean> sizeTypes) {
        mContext = context;
        mSizeTypes = sizeTypes;
    }

    private onChangeListener mOnChangeListener;

    public void setOnChangeListener(onChangeListener mOnChangeListener) {
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
        SampleSize.SampleSizeInformationListBean sizeType = mSizeTypes.get(position);
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_model_child, null);
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.mEditText = (EditText) convertView.findViewById(R.id.et_input);
            mViewHolder.mRelativeLayout=convertView.findViewById(R.id.rl_del);
            mViewHolder.mEditText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                    mTouchItemPosition = (Integer) view.getTag();
                    return false;
                }
            });
            mViewHolder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnChangeListener.onChange(position);
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
        Log.e("getQuantity",sizeType.getQuantity()+"");
        mViewHolder.mTextView.setText(sizeType.getSizeInformationName());
        mViewHolder.mEditText.setText(sizeType.getQuantity()+"");
        mViewHolder.mEditText.setTag(position);
        mViewHolder.mRelativeLayout.setVisibility(View.GONE);
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
        RelativeLayout mRelativeLayout;
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
            SampleSize.SampleSizeInformationListBean sizeType = mSizeTypes.get(mPosition);
            if(!TextUtils.isEmpty(s.toString()))
                if(isNumber(s.toString()))
                   sizeType.setQuantity(Double.parseDouble(s.toString()));
            else{
                sizeType.setQuantity(0);
            }
            mSizeTypes.set(mPosition, sizeType);
        }
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


}
