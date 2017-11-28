package com.google.android.exoplayer2.ui.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import io.uiza.sdk.ui.R;

/**
 * Created by www.muathu@gmail.com on 10/7/2017.
 */

public class UizaImageUtil {
    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).centerCrop().placeholder(R.drawable.bkg).into(imageView);
    }

    public static void load(Context context, String url, ImageView imageView, int sizeW, int sizeH) {
        Glide.with(context).load(url).centerCrop().placeholder(R.drawable.bkg).override(sizeW, sizeH).into(imageView);
    }
}
