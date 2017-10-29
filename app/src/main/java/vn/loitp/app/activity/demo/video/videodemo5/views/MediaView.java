package vn.loitp.app.activity.demo.video.videodemo5.views;

import android.content.Context;
import android.widget.TextView;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Layout;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.Resolve;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.View;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.ChildPosition;
import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.annotations.expand.ParentPosition;
import vn.loitp.app.activity.demo.video.videodemo5.model.Sample;
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

    @View(R.id.tv_uri)
    private TextView tvUri;

    @View(R.id.tv_extension)
    private TextView tvExtension;

    @View(R.id.tv_ad_tag_uri)
    private TextView tvAdTagUri;

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

        tvUri.setText(mSample.getUri());
        tvUri.setVisibility(mSample.getUri().isEmpty() || mSample.getUri() == null ? android.view.View.GONE : android.view.View.VISIBLE);

        tvExtension.setText(mSample.getExtension());
        tvExtension.setVisibility(mSample.getExtension().isEmpty() || mSample.getExtension() == null ? android.view.View.GONE : android.view.View.VISIBLE);

        tvAdTagUri.setText(mSample.getAdTagUri());
        tvAdTagUri.setVisibility(mSample.getAdTagUri().isEmpty() || mSample.getAdTagUri() == null ? android.view.View.GONE : android.view.View.VISIBLE);
    }

    /*@Click(R.id.imageView)
    private void onClickImage() {
        ToastUtils.showShort(mInfo.getImageUrl());
    }*/
}