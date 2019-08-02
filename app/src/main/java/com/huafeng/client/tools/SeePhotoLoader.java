package com.huafeng.client.tools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.maning.imagebrowserlibrary.ImageEngine;

public class SeePhotoLoader implements ImageEngine {
    @Override
    public void loadImage(Context context, String url, ImageView imageView, View progressView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
