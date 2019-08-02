package com.huafeng.client.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class BitmapUtil {
    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }
    /**
     * 保存bitmap到本地
     *
     * @param
     * @param
     * @return
     */
    public static Bitmap loadBitmapFromView(View v) {

        int w=v.getWidth();

        int h=v.getHeight();

        Bitmap bmp=Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        Canvas c=new Canvas(bmp);

        c.drawColor(Color.WHITE);

/** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);

        v.draw(c);

        return bmp;

    }
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(savePath + generateFileName() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }
    public static void  layoutView(View v,int width,int height) {

// 指定整个View的大小 参数是左上角 和右下角的坐标

        v.layout(0,0, width, height);

        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);

        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST);

/** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。

 * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。

 */

        v.measure(measuredWidth, measuredHeight);

        v.layout(0,0, v.getMeasuredWidth(), v.getMeasuredHeight());

    }
}
