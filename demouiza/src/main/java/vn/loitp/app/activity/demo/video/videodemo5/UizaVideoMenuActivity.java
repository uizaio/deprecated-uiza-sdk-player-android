package vn.loitp.app.activity.demo.video.videodemo5;

import android.app.Activity;
import android.os.Bundle;

import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LStoreUtil;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.utils.util.ToastUtils;
import vn.loitp.app.activity.demo.video.videodemo5.model.Sample;
import vn.loitp.app.activity.demo.video.videodemo5.model.WrapperUiza;
import vn.loitp.app.activity.demo.video.videodemo5.views.MediaView;
import vn.loitp.app.activity.demo.video.videodemo5.views.TypeView;
import vn.loitp.app.app.LSApplication;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.livestar.R;
import vn.loitp.views.placeholderview.lib.placeholderview.ExpandablePlaceHolderView;

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
            ToastUtils.showShort("Cannot load json from Asset :(");
        } else {
            WrapperUiza[] wrapperUiza = LSApplication.getInstance().getGson().fromJson(json, WrapperUiza[].class);
            LLog.d(TAG, "size: " + wrapperUiza.length);

            mExpandableView = (ExpandablePlaceHolderView) findViewById(R.id.expandableView);

            LUIUtil.setPullLikeIOSVertical(mExpandableView);

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
