package vn.loitp.core.utilities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
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
        Glide.with(activity).load(url)
                .apply(new RequestOptions()
                                //.placeholder(resPlaceHolder)
                                //.fitCenter()
                                .override(sizeW, sizeH)
                        //.error(resError)
                )
                .into(imageView);
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
        Glide.with(activity).load(url)
                .apply(new RequestOptions()
                        //.placeholder(resPlaceHolder)
                        //.fitCenter()
                        //.override(sizeW, sizeH)
                        .error(R.drawable.err)
                )
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (avLoadingIndicatorView != null) {
                            avLoadingIndicatorView.smoothToHide();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (avLoadingIndicatorView != null) {
                            avLoadingIndicatorView.smoothToHide();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }
}
