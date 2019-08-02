package com.huafeng.client.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * 作者：凌涛 on 2019/4/11 10:43
 * 邮箱：771548229@qq..com
 */
public class LastInputEditText extends AppCompatEditText {

    public LastInputEditText(Context context) {
        super(context);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (selStart == selEnd) { // 防止不能多选
            setSelection(getText().length()); // 保证光标始终在最后面
        }
    }

}
