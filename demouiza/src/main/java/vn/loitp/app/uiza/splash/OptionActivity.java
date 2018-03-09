package vn.loitp.app.uiza.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uiza.player.ui.data.UizaData;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.home.v2.Home2Activity;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LDateUtils;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.utils.util.ToastUtils;

public class OptionActivity extends BaseActivity {
    public static final String KEY_TEST = "isSlide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.bt_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SplashActivity.class);
                intent.putExtra(KEY_TEST, false);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }
        });
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SplashActivity.class);
                intent.putExtra(KEY_TEST, true);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }
        });
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
        return R.layout.uiza_option_activity;
    }
}
