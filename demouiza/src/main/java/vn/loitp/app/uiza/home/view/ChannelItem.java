package vn.loitp.app.uiza.home.view;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.Animation;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.PlaceHolderView;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Animate;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Click;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.NonReusable;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.uiza.home.model.VideoObject;
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

    public ChannelItem(Context context, PlaceHolderView placeHolderView, VideoObject videoObject) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mVideoObject = videoObject;
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
        ToastUtils.showShort("Touch");
    }
}