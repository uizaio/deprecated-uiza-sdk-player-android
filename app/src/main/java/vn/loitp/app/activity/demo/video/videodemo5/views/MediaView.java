package vn.loitp.app.activity.demo.video.videodemo5.views;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Click;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.ChildPosition;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.ParentPosition;
import vn.loitp.app.activity.demo.video.videodemo5.VideoDemo5Activity;
import vn.loitp.app.activity.demo.video.videodemo5.model.Sample;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.common.Constants;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LUIUtil;
import vn.uiza.app.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

@Layout(R.layout.row_media_uiza_video)
public class MediaView {

    private final String TAG = getClass().getSimpleName();

    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.tv_name)
    private TextView tvName;

    @View(R.id.tv_json)
    private TextView tvJson;

    @View(R.id.main_view)
    private LinearLayout mainView;

    private Sample mSample;
    private Activity mActivity;

    public MediaView(Activity activity, Sample sample) {
        mActivity = activity;
        mSample = sample;
    }

    @Resolve
    private void onResolved() {
        tvName.setText(mSample.getName());
        tvName.setVisibility(mSample.getName().isEmpty() || mSample.getName() == null ? android.view.View.GONE : android.view.View.VISIBLE);

        LUIUtil.printBeautyJson(mSample, tvJson);
    }

    @Click(R.id.main_view)
    private void onClickMainView() {
        LLog.d(TAG, "onClickMainView " + LSApplication.getInstance().getGson().toJson(mSample));
        Intent intent = new Intent(mActivity, VideoDemo5Activity.class);
        intent.putExtra(Constants.KEY_UIZA, mSample);
        mActivity.startActivity(intent);
        LUIUtil.transActivityFadeIn(mActivity);
    }
}