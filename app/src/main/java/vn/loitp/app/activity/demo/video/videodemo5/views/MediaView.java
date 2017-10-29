package vn.loitp.app.activity.demo.video.videodemo5.views;

import android.content.Context;
import android.widget.TextView;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.ChildPosition;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.ParentPosition;
import vn.loitp.app.activity.demo.video.videodemo5.model.Sample;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

@Layout(R.layout.row_media_uiza_video)
public class MediaView {

    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.tv_name)
    private TextView tvName;

    @View(R.id.tv_json)
    private TextView tvJson;

    private Sample mSample;
    private Context mContext;

    public MediaView(Context context, Sample sample) {
        mContext = context;
        mSample = sample;
    }

    @Resolve
    private void onResolved() {
        tvName.setText(mSample.getName());
        tvName.setVisibility(mSample.getName().isEmpty() || mSample.getName() == null ? android.view.View.GONE : android.view.View.VISIBLE);

        LUIUtil.printBeautyJson(mSample, tvJson);
    }

    /*@Click(R.id.imageView)
    private void onClickImage() {
        ToastUtils.showShort(mInfo.getImageUrl());
    }*/
}