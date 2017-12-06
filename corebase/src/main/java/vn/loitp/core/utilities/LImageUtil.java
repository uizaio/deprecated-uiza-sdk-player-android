package vn.loitp.core.utilities;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import loitp.core.R;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

/**
 * Created by www.muathu@gmail.com on 10/7/2017.
 */

public class LImageUtil {
    private static final String TAG = LImageUtil.class.getSimpleName();

    public static void load(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url).into(imageView);
    }

    public static void load(Activity activity, String url, ImageView imageView, int sizeW, int sizeH) {
        Glide.with(activity).load(url).override(sizeW, sizeH).into(imageView);
    }

    public static void load(Activity activity, final String[] url, ImageView imageView, final AVLoadingIndicatorView avLoadingIndicatorView) {
        String u = null;
        for (int i = 0; i < url.length; i++) {
            if (url[i] != null) {
                u = url[i];
                break;
            }
        }
        if (u != null) {
            load(activity, u, imageView, avLoadingIndicatorView);
        }
    }

    public static void load(Activity activity, final String url, ImageView imageView, final AVLoadingIndicatorView avLoadingIndicatorView) {
        avLoadingIndicatorView.smoothToShow();
        Glide.with(activity).load(url).error(R.drawable.err).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                if (avLoadingIndicatorView != null) {
                    avLoadingIndicatorView.smoothToHide();
                }
                //LLog.d(TAG, "load onException " + url);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (avLoadingIndicatorView != null) {
                    avLoadingIndicatorView.smoothToHide();
                }
                return false;
            }
        }).into(imageView);
    }
}
