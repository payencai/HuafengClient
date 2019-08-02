package com.huafeng.client.ui.home.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.RawMatrerial;
import com.huafeng.client.view.CursorEditText;


import java.util.List;

/**
 * 作者：凌涛 on 2019/5/8 10:50
 * 邮箱：771548229@qq..com
 */
public class RawInputAdapter extends BaseAdapter {
    private int mTouchItemPosition = -1;
    Context mContext;
    List<RawMatrerial> mraws;
    private ViewHolder mViewHolder;

    public RawInputAdapter(Context context, List<RawMatrerial> raws) {
        mContext = context;
        mraws = raws;
    }

    private onChangeListener mOnChangeListener;

    public void setOnChangeListener(onChangeListener mOnChangeListener) {
        this.mOnChangeListener = mOnChangeListener;
    }

    public interface onChangeListener {
        void onChange(int pos);

        void onSelect(int position);
    }

    @Override
    public int getCount() {
        return mraws.size();
    }

    @Override
    public Object getItem(int position) {
        return mraws.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RawMatrerial raw = mraws.get(position);
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_model_category, null);
            mViewHolder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.mTvType = convertView.findViewById(R.id.tv_type);
            mViewHolder.mEditText = (CursorEditText) convertView.findViewById(R.id.et_input);
            mViewHolder.mRelativeLayout = convertView.findViewById(R.id.rl_del);
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
        mViewHolder.mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnChangeListener.onSelect(position);
            }
        });
        if (raw.getId() == -1) {
            mViewHolder.mTvName.setText(raw.getName() + "(新)");
        } else if (raw.getId() > 0) {
            mViewHolder.mTvName.setText(raw.getName());
        }else{
            mViewHolder.mTvName.setText("请选择具体材质");
        }
        mViewHolder.mTvType.setText(raw.getCategory1Name() + " " + raw.getCategory2Name());
        mViewHolder.mEditText.setText(raw.getInput());
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
        TextView mTvName;
        TextView mTvType;
        CursorEditText mEditText;
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
            RawMatrerial raw = mraws.get(mPosition);
            raw.setInput(s.toString());
            mraws.set(mPosition, raw);
        }
    }

    ;

}
