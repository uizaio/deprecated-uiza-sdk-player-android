package com.google.android.exoplayer2.ui.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by www.muathu@gmail.com on 10/7/2017.
 */

public class LImageUtil {
    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void load(Context context, String url, ImageView imageView, int sizeW, int sizeH) {
        Glide.with(context).load(url).override(sizeW, sizeH).into(imageView);
    }
}
