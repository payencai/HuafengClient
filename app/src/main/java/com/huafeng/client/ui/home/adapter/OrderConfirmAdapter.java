package com.huafeng.client.ui.home.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.model.Materials;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.List;

public class OrderConfirmAdapter extends BaseAdapter {
    private int mTouchItemPosition = -1;
    Context mContext;
    List<Materials> mmaterialss;
    private ViewHolder mViewHolder;
    int  num;

    public OrderConfirmAdapter(Context context, List<Materials> materialss, int num) {
        mContext = context;
        mmaterialss = materialss;
        this.num = num;
    }
    private  OnItemChangeListener onItemChangeListener;
    public interface OnItemChangeListener{
        void onChange(int pos,int type);
    }
    public void setOnItemChangeListener(OnItemChangeListener onItemChangeListener){
        this.onItemChangeListener=onItemChangeListener;
    }
    private void showDialog(View view, int pos) {
        EasyPopup mCirclePop = EasyPopup.create()
                .setContentView(mContext, R.layout.dialog_four_menu)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                .apply();
        TextView tv_type1 = (TextView) mCirclePop.findViewById(R.id.tv_type1);
        TextView tv_type2 = (TextView) mCirclePop.findViewById(R.id.tv_type2);
        TextView tv_type3 = (TextView) mCirclePop.findViewById(R.id.tv_type3);
        tv_type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmaterialss.get(pos).setPurchaseType(1);
                notifyDataSetChanged();
            }
        });
        tv_type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmaterialss.get(pos).setPurchaseType(2);
                notifyDataSetChanged();
            }
        });
        tv_type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mmaterialss.get(pos).setPurchaseType(3);
                notifyDataSetChanged();
            }
        });

        mCirclePop.showAtAnchorView(view, YGravity.BELOW, XGravity.LEFT, 0, 0);

    }

    @Override
    public int getCount() {
        return mmaterialss.size();
    }

    @Override
    public Object getItem(int position) {
        return mmaterialss.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Materials materials = mmaterialss.get(position);
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_confirm_order, null);
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolder.mEditText = (EditText) convertView.findViewById(R.id.et_number);
            mViewHolder.ivPhoto = convertView.findViewById(R.id.iv_photo);
            mViewHolder.mTvNum = convertView.findViewById(R.id.tv_num);
            mViewHolder.mTvStyle = convertView.findViewById(R.id.tv_style);
            mViewHolder.llType = convertView.findViewById(R.id.ll_type);
            mViewHolder.mTvType = convertView.findViewById(R.id.tv_type);
            mViewHolder.mTvCount = convertView.findViewById(R.id.tv_count);
            mViewHolder.llBuy = convertView.findViewById(R.id.ll_buy);
            mViewHolder.ivDel = convertView.findViewById(R.id.iv_del);
            mViewHolder.ivAdd = convertView.findViewById(R.id.iv_add);
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
        mViewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemChangeListener.onChange(position,1);
            }
        });
        mViewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemChangeListener.onChange(position,2);
            }
        });
        mViewHolder.mTextView.setText(materials.getRawMaterialName());
        mViewHolder.mEditText.setText(materials.getInput());
        mViewHolder.mTvStyle.setText(materials.getRawMaterialCategory1Name() + " " + materials.getRawMaterialCategory2Name());
        mViewHolder.mEditText.setTag(position);
        mViewHolder.mTvCount.setText(materials.getQuantity() * num + "");
        mViewHolder.mTvNum.setText("当前库存：" + materials.getQuantity());
        if (materials.getPurchaseType() == 1) {
            mViewHolder.llBuy.setVisibility(View.VISIBLE);
            mViewHolder.mTvType.setText("产前采购");
        } else {
            mViewHolder.llBuy.setVisibility(View.GONE);
            if (materials.getPurchaseType() == 2) {
                mViewHolder.mTvType.setText("车间采购");
            } else {
                mViewHolder.mTvType.setText("后整采购");
            }
        }


        mViewHolder.llType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog(position);
            }
        });

        if (mTouchItemPosition == position) {
            mViewHolder.mEditText.requestFocus();
            mViewHolder.mEditText.setSelection(mViewHolder.mEditText.getText().length());
        } else {
            mViewHolder.mEditText.clearFocus();
        }
        String imgs = materials.getImageUrl();
        if (!TextUtils.isEmpty(imgs)) {
            if (imgs.contains(",")) {
                Glide.with(mContext).load(imgs.split(",")[0]).into(mViewHolder.ivPhoto);
            } else {
                Glide.with(mContext).load(materials.getImageUrl()).into(mViewHolder.ivPhoto);
            }
        }

        return convertView;
    }

    private void showTypeDialog(int pos) {
        final Dialog dialog = new Dialog(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_select_type, null);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_type1 = (TextView) view.findViewById(R.id.tv_type1);
        TextView tv_type2 = (TextView) view.findViewById(R.id.tv_type2);
        TextView tv_type3 = (TextView) view.findViewById(R.id.tv_type3);
        tv_type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mmaterialss.get(pos).setPurchaseType(1);
                notifyDataSetChanged();
            }
        });
        tv_type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mmaterialss.get(pos).setPurchaseType(2);
                notifyDataSetChanged();
            }
        });
        tv_type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mmaterialss.get(pos).setPurchaseType(3);
                notifyDataSetChanged();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);

        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

    }

    static final class ViewHolder {
        TextView mTvNum;
        TextView mTextView;
        TextView mTvStyle;
        EditText mEditText;
        ImageView ivPhoto;
        ImageView ivAdd;
        ImageView ivDel;
        LinearLayout llType;
        LinearLayout llBuy;
        TextView mTvType;
        TextView mTvCount;
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
            Materials materials = mmaterialss.get(mPosition);
            materials.setInput(s.toString());
            mmaterialss.set(mPosition, materials);
        }
    }

}
