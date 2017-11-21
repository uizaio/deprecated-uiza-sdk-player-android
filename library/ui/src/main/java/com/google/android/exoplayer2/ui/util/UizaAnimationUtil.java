package com.google.android.exoplayer2.ui.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.exoplayer2.ui.R;

/**
 * Created by www.muathu@gmail.com on 11/21/2017.
 */

public class UizaAnimationUtil {
    public static void playFadeIn(Context context, View view, Animation.AnimationListener animationListener) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fadein);
        if (animationListener != null) {
            animation.setAnimationListener(animationListener);
        }
        view.startAnimation(animation);
    }

    public static void playFadeOut(Context context, View view, Animation.AnimationListener animationListener) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fadeout);
        if (animationListener != null) {
            animation.setAnimationListener(animationListener);
        }
        view.startAnimation(animation);
    }
}
