package com.google.android.exoplayer2.ui.util;

import android.os.Handler;

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
}
