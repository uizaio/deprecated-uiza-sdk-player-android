package com.uiza.player.ui.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import io.uiza.sdk.ui.R;

/**
 * Created by www.muathu@gmail.com on 10/7/2017.
 */

public class UizaImageUtil {
    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.bkg)
                        //.fitCenter()
                        //.override(sizeW, sizeH)
                        //.error(resError)
                        .centerCrop()
                ).into(imageView);
    }

    public static void load(Context context, String url, ImageView imageView, int sizeW, int sizeH) {
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.bkg)
                        //.fitCenter()
                        .override(sizeW, sizeH)
                        //.error(resError)
                        .centerCrop()
                ).into(imageView);
    }
}
