package com.uiza.player.ui.util;

import android.graphics.Color;
import android.os.Handler;
import android.widget.ProgressBar;

/**
 * Created by www.muathu@gmail.com on 11/8/2017.
 */

public class UizaUIUtil {
    public interface DelayCallback {
        public void doAfter(int mls);
    }

    public static void setDelay(final int mls, final DelayCallback delayCallback) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (delayCallback != null) {
                    delayCallback.doAfter(mls);
                }
            }
        }, mls);
    }

    public static void setColorProgressBar(ProgressBar progressBar) {
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);
    }
}
