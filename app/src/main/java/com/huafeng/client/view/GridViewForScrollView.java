package com.huafeng.client.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 作者：凌涛 on 2019/5/5 20:21
 * 邮箱：771548229@qq..com
 */
public class GridViewForScrollView extends GridView {
    private boolean haveScrollbar = true;

    public GridViewForScrollView(Context context) {
        super(context);
    }
    public GridViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public GridViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    /**
     * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true
     *
     *
     */
    public void setHaveScrollbar(boolean haveScrollbar) {
        this.haveScrollbar = haveScrollbar;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (haveScrollbar == false) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
