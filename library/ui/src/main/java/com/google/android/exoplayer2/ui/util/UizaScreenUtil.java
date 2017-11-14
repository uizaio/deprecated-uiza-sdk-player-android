package com.google.android.exoplayer2.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * File created on 8/31/2017.
 *
 * @author anhdv
 */

public class UizaScreenUtil {
    public int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        //Log.d(TAG,"result bar height: "+result);
        return result;
    }

    public int getBottomBarHeight(Context mContext) {
        boolean hasMenuKey = ViewConfiguration.get(mContext).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // Do whatever you need to do, this device has a navigation bar
            int result = 0;
            int resourceId = mContext.getResources().getIdentifier("design_bottom_navigation_height", "dimen", mContext.getPackageName());
            if (resourceId > 0) {
                result = mContext.getResources().getDimensionPixelSize(resourceId);
            }
            //Log.d(TAG,"result botbar height: "+result);
            return result;
        }
        return 0;
    }

    public static void hideNavBar(View v) {
        if (Build.VERSION.SDK_INT >= 19) {
            //View v = getWindow().getDecorView();
            v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
