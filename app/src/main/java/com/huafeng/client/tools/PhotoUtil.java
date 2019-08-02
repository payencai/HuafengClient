package com.huafeng.client.tools;

import android.content.Context;
import android.view.View;

import com.huafeng.client.R;
import com.maning.imagebrowserlibrary.MNImageBrowser;
import com.maning.imagebrowserlibrary.model.ImageBrowserConfig;

import java.util.ArrayList;

public class PhotoUtil {
    public static ImageBrowserConfig.TransformType transformType = ImageBrowserConfig.TransformType.Transform_Default;
    public static ImageBrowserConfig.IndicatorType indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
    public static ImageBrowserConfig.ScreenOrientationType screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Default;

    public static void seeBigPhoto(Context context, int position, ArrayList<String> sourceImageList, View view) {
        MNImageBrowser.with(context)
                //必须-当前位置
                .setCurrentPosition(position)
                //必须-图片加载用户自己去选择
                .setImageEngine(new SeePhotoLoader())
                //必须（setImageList和setImageUrl二选一，会覆盖）-图片集合
                .setImageList(sourceImageList)
                //必须（setImageList和setImageUrl二选一，会覆盖）-设置单张图片
                .setTransformType(transformType)
                //非必须-指示器样式（默认文本样式：两种模式）
                .setIndicatorType(indicatorType)
                //设置隐藏指示器
                .setIndicatorHide(false)
                //设置自定义遮盖层，定制自己想要的效果，当设置遮盖层后，原本的指示器会被隐藏
                //非必须-屏幕方向：横屏，竖屏，Both（默认：横竖屏都支持）
                .setScreenOrientationType(screenOrientationType)
                //非必须-图片单击监听

                //全屏模式：默认非全屏模式
                .setFullScreenMode(false)
                //打开动画
                .setActivityOpenAnime(R.anim.activity_anmie_in)
                //关闭动画
                .setActivityExitAnime(R.anim.activity_anmie_out)
                //打开
                .show(view);
    }
    public static void seeSinglePhoto(Context context,  String url, View view) {
        MNImageBrowser.with(context)
                //必须-当前位置
                .setCurrentPosition(0)
                //必须-图片加载用户自己去选择
                .setImageEngine(new SeePhotoLoader())
                //必须（setImageList和setImageUrl二选一，会覆盖）-图片集合
                .setImageUrl(url)
                //必须（setImageList和setImageUrl二选一，会覆盖）-设置单张图片
                .setTransformType(transformType)
                //非必须-指示器样式（默认文本样式：两种模式）
                .setIndicatorType(indicatorType)
                //设置隐藏指示器
                .setIndicatorHide(false)
                //设置自定义遮盖层，定制自己想要的效果，当设置遮盖层后，原本的指示器会被隐藏
                //非必须-屏幕方向：横屏，竖屏，Both（默认：横竖屏都支持）
                .setScreenOrientationType(screenOrientationType)
                //非必须-图片单击监听

                //全屏模式：默认非全屏模式
                .setFullScreenMode(false)
                //打开动画
                .setActivityOpenAnime(R.anim.activity_anmie_in)
                //关闭动画
                .setActivityExitAnime(R.anim.activity_anmie_out)
                //打开
                .show(view);
    }
}
