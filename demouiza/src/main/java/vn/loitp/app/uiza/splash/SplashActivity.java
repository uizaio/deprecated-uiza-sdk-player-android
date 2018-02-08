package vn.loitp.app.uiza.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import vn.loitp.app.common.Constants;
import vn.loitp.app.uiza.home.HomeActivity;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.uiza.R;
import vn.loitp.restapi.restclient.RestClient;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LUIUtil.setDelay(1500, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                Intent intent = new Intent(activity, HomeActivity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
                finish();
            }
        });
    }

    @Override
    protected boolean setFullScreen() {
        return true;
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
