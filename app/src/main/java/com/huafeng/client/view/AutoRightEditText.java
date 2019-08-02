package com.huafeng.client.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：凌涛 on 2019/4/11 10:47
 * 邮箱：771548229@qq..com
 */
public class AutoRightEditText extends AppCompatEditText {

    public boolean isFirstOnClick = true;
    private boolean isShowEnd;

    public AutoRightEditText(Context context) {
        super(context);
        initView();
    }


    public AutoRightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AutoRightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setClickable(true);
        /**
         * 如果你本身使用的该方法记得 !hasFocus--->isFirstOnClick = true
         */

        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isFirstOnClick = true;
                }
            }
        });
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);

        if (isFirstOnClick && isPressed()) {
            isShowEnd = true;
            int len = getText().length();
            setSelection(len);
        } else {
            isShowEnd = false;
            isFirstOnClick = false;
        }

    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (isShowEnd && selStart == selEnd) {
            setSelection(getText().length());
        }


    }


}
