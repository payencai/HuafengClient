package com.huafeng.client.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * 作者：凌涛 on 2019/4/11 10:50
 * 邮箱：771548229@qq..com
 */
public class CursorEditText extends AppCompatEditText {

    private CharSequence mHint;
    private Paint mPaint;
    private int mHintTextColor;

    public CursorEditText(Context context) {
        this(context, null);
    }

    public CursorEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
    }

    public CursorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mHint = getHint();
        setHint("");
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setTextSize(getTextSize());
        mPaint.setTextAlign(Paint.Align.RIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(mHint) || !TextUtils.isEmpty(getText())) {
            return;
        }
        canvas.save();
        ColorStateList hintTextColors = getHintTextColors();
        if (hintTextColors != null) {
            int color = hintTextColors.getColorForState(getDrawableState(), 0);
            if (color != mHintTextColor) {
                mHintTextColor = color;
                mPaint.setColor(color);
            }
        }

        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (getHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(mHint, 0, mHint.length(),getWidth() - getPaddingRight() + getScrollX(), baseline, mPaint);
        canvas.restore();
    }
}

