package vn.loitp.app.uiza.home.view;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.Animation;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.PlaceHolderView;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Animate;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Click;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.NonReusable;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.uiza.home.model.VideoObject;
import vn.loitp.app.utilities.LAnimationUtil;
import vn.loitp.app.utilities.LImageUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

@Animate(Animation.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.uiza_channel_item)
public class ChannelItem {

    @View(R.id.imageView)
    private ImageView imageView;

    private VideoObject mVideoObject;
    private Context mContext;
    private PlaceHolderView mPlaceHolderView;
    private Callback mCallback;
    private int mPosition;

    public ChannelItem(Context context, PlaceHolderView placeHolderView, VideoObject videoObject, int position, Callback callback) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mVideoObject = videoObject;
        mPosition = position;
        mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        LImageUtil.load((Activity) mContext, mVideoObject.getUrl(), imageView);
    }

    /*@LongClick(R.id.imageView)
    private void onLongClick() {
        mPlaceHolderView.removeView(this);
    }*/

    @Click(R.id.imageView)
    private void onClick() {
        LAnimationUtil.play(imageView, Techniques.Pulse, new LAnimationUtil.Callback() {
            @Override
            public void onCancel() {
                //do nothing
            }

            @Override
            public void onEnd() {
                if (mCallback != null) {
                    mCallback.onClick(mVideoObject, mPosition);
                }
            }

            @Override
            public void onRepeat() {
                //do nothing
            }

            @Override
            public void onStart() {
                //do nothing
            }
        });
    }

    public interface Callback {
        public void onClick(VideoObject videoObject, int position);
    }
}