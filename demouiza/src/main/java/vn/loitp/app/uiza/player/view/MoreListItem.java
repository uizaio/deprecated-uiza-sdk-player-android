package vn.loitp.app.uiza.player.view;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.google.android.exoplayer2.ui.fragment.helper.InputModel;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.Animation;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.PlaceHolderView;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Animate;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Click;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.NonReusable;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.utilities.LAnimationUtil;
import vn.loitp.app.utilities.LImageUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

//@Animate(Animation.CARD_TOP_IN_DESC)
@Animate(Animation.CARD_BOTTOM_IN_ASC)
@NonReusable
@Layout(R.layout.uiza_more_list_item)
public class MoreListItem {

    @View(R.id.imageView)
    private ImageView imageView;

    private InputModel mInputModel;
    private Context mContext;
    private PlaceHolderView mPlaceHolderView;
    private Callback mCallback;
    private int mPosition;

    public MoreListItem(Context context, PlaceHolderView placeHolderView, InputModel inputModel, int position, Callback callback) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mInputModel = inputModel;
        mPosition = position;
        mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        LImageUtil.load((Activity) mContext, mInputModel.getUrlImg(), imageView);
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
                    mCallback.onClick(mInputModel, mPosition);
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
        public void onClick(InputModel inputModel, int position);
    }
}