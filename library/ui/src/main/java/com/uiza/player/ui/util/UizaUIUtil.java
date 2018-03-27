package com.uiza.player.ui.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.uiza.player.ui.data.UizaData;

import io.uiza.sdk.ui.R;
import vn.loitp.core.utilities.LDialogUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LScreenUtil;

/**
 * Created by www.muathu@gmail.com on 11/8/2017.
 */

public class UizaUIUtil {
    private static final String TAG = LDialogUtil.class.getSimpleName();

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

    public static void setUIUizaDialogPlayControlView(Dialog dialog, final View view, final Activity activity) {
        LLog.d(TAG, "setUIUizaDialogPlayControlView");
        final Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final WindowManager.LayoutParams param = window.getAttributes();
            param.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            view.post(new Runnable() {
                @Override
                public void run() {
                    if (UizaData.getInstance().isVideoCanSlide()) {
                        //v2, video can slide
                        if (UizaData.getInstance().isLandscape()) {
                            LLog.d(TAG, "isLandscape");
                            LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());
                            LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                            param.y = UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                            window.setAttributes(param);
                            //window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        } else {
                            LLog.d(TAG, "!isLandscape");
                            LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());

                            int actionbarSizePx = LScreenUtil.getActionbarSizePx(activity);
                            LLog.d(TAG, "actionbarSizePx: " + actionbarSizePx);
                            LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                            param.y = actionbarSizePx + UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                            window.setAttributes(param);
                            //window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        }
                    } else {
                        //v1, no silde video
                        LLog.d(TAG, "isLandscape");
                        LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());
                        LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                        param.y = UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                        window.setAttributes(param);
                        //window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    }
                }
            });
        }
        dialog.setCancelable(true);
    }
}
