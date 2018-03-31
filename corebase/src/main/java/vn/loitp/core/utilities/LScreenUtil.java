package vn.loitp.core.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

/**
 * File created on 8/31/2017.
 *
 * @author anhdv
 */

public class LScreenUtil {
    public static int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        //Log.d(TAG,"result bar height: "+result);
        return result;
    }

    public static int getBottomBarHeight(Context mContext) {
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
            v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    // This snippet hides the system bars.
    /*public static void hideSystemUI(Activity activity) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }*/

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    /*public static void showSystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }*/

    public static void hideDefaultControls(@NonNull final Activity activity) {
        final Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final View decorView = window.getDecorView();
        if (decorView != null) {
            int uiOptions = decorView.getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= 14) {
                uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= 19) {
                uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void showDefaultControls(@NonNull final Activity activity) {
        final Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        final View decorView = window.getDecorView();
        if (decorView != null) {
            int uiOptions = decorView.getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= 14) {
                uiOptions &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                uiOptions &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= 19) {
                uiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void hideNavigationBar(Activity activity) {
        // set navigation bar status, remember to disable "setNavigationBarTintEnabled"
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        // This work only for android 4.4+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = activity.getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }
    }

    public static void showNavigationBar(Activity activity) {
        // set navigation bar status, remember to disable "setNavigationBarTintEnabled"
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        // This work only for android 4.4+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = activity.getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }
    }

    public static int getActionbarSizePx(Context context) {
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        int actionBarHeight = context.getResources().getDimensionPixelSize(tv.resourceId);
        return actionBarHeight;
    }
}
