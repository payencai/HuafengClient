package com.huafeng.client.ui.start.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.huafeng.client.MyApp;
import com.huafeng.client.R;
import com.huafeng.client.ui.home.activity.BaseActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyQrCodeActivity extends BaseActivity {
    @BindView(R.id.iv_code)
    ImageView iv_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr_code);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        initView();
    }

    private void initView() {
        String qrcodeUrl = "华枫制衣好友:" + MyApp.getUserInfo().getHxUsername();
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.ic_deault_head);
        Bitmap bitmap = (Bitmap) CodeUtils.createImage(qrcodeUrl, 200, 200, toRoundBitmap(logo));
        iv_code.setImageBitmap(bitmap);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public Bitmap toRoundBitmap(Bitmap bitmap) {//圆形图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();//正方形的边长
        int r = 0;//取最短边做边长
        if (width > height) {
            r = height;
        } else {
            r = width;
        }//构建一个
        Bitmap backgroundBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();//设置边缘光滑，去掉锯齿
        paint.setAntiAlias(true);//宽高相等，即正方形
        RectF rect = new RectF(0, 0, r, r);//通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，//且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, paint);//设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, paint);//返回已经绘画好的
        return backgroundBmp;
    }

}
