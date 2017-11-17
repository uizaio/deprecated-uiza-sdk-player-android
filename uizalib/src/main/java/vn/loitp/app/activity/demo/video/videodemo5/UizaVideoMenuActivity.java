package vn.loitp.app.activity.demo.video.videodemo5;

import android.app.Activity;
import android.os.Bundle;

import vn.loitp.app.activity.customviews.viewpager.placeholderview._lib.placeholderview.ExpandablePlaceHolderView;
import vn.loitp.app.activity.demo.video.videodemo5.model.Sample;
import vn.loitp.app.activity.demo.video.videodemo5.model.WrapperUiza;
import vn.loitp.app.activity.demo.video.videodemo5.views.MediaView;
import vn.loitp.app.activity.demo.video.videodemo5.views.TypeView;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LStoreUtil;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

public class UizaVideoMenuActivity extends BaseActivity {
    private ExpandablePlaceHolderView mExpandableView;
    private ExpandablePlaceHolderView.OnScrollListener mOnScrollListener;
    private boolean mIsLoadingMore = false;
    private boolean mNoMoreToLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String json = LStoreUtil.readTxtFromAsset(activity, "medialist.json");
        //LLog.d(TAG, "json: " + json);
        if (json == null || json.isEmpty()) {

        } else {
            WrapperUiza[] wrapperUiza = LSApplication.getInstance().getGson().fromJson(json, WrapperUiza[].class);
            LLog.d(TAG, "size: " + wrapperUiza.length);

            mExpandableView = (ExpandablePlaceHolderView) findViewById(R.id.expandableView);

            //LUIUtil.setPullLikeIOSVertical(mExpandableView);

            for (WrapperUiza w : wrapperUiza) {
                mExpandableView.addView(new TypeView(activity, w.getName()));
                for (Sample s : w.getSamples()) {
                    mExpandableView.addView(new MediaView(activity, s));
                }
            }
        }
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_uizavideo_menu;
    }
}
