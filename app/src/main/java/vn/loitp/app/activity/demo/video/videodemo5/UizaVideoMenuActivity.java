package vn.loitp.app.activity.demo.video.videodemo5;

import android.app.Activity;
import android.os.Bundle;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.activity.demo.video.videodemo5.model.WrapperUiza;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LStoreUtil;
import vn.loitp.livestar.R;

public class UizaVideoMenuActivity extends BaseActivity {

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
