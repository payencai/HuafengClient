package com.hyphenate.easeui.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;

public class EaseUserUtils {

    static MyUserProvider userProvider;

    static {
        userProvider = MyUserProvider.getInstance();
    }

    /**
     * get EaseUser according username
     *
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username) {
        if (userProvider != null)
            return userProvider.getUser(username);

        return null;
    }

    /**
     * set user avatar
     *
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        EaseUser user = getUserInfo(username);
        //Log.e("header",user.getAvatar());
        if (user != null && user.getAvatar() != null) {
            Glide.with(context)
                    .load(user.getAvatar())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        } else {
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }


    }

    public static void setUserAvatar2(Context context, String imgUrl, ImageView imageView) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(context)
                    .load(imgUrl)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        } else {
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username, TextView textView) {
        if (textView != null) {
            EaseUser user = getUserInfo(username);
            if (user != null && user.getNickname() != null) {
                textView.setText(user.getNickname());
            } else {
                textView.setText(username);
            }
        }
    }

    public static void setUserNick2(String username, TextView textView) {
        if (textView != null)
            textView.setText(username);

    }

}
