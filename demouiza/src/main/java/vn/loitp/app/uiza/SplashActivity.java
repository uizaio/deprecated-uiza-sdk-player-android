package vn.loitp.app.uiza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import vn.loitp.app.activity.animation.MenuAnimationActivity;
import vn.loitp.app.activity.api.MenuAPIActivity;
import vn.loitp.app.activity.customviews.MenuCustomViewsActivity;
import vn.loitp.app.activity.demo.MenuDemoActivity;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return R.layout.uiza_splash_activity;
    }
}
