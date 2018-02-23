package vn.loitp.app.uiza.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.uiza.player.ui.data.UizaData;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.home.HomeActivity;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.restapi.uiza.UizaV2Service;
import vn.loitp.restapi.uiza.model.auth.Auth;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Auth auth = LPref.getAuth(activity, LSApplication.getInstance().getGson());
        if (auth == null) {
            auth();
        } else {
            //TODO check token is expired

            LUIUtil.setDelay(2000, new LUIUtil.DelayCallback() {
                @Override
                public void doAfter(int mls) {
                    goToHome(auth);
                }
            });
        }
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

    private void auth() {
        UizaV2Service service = RestClient.createService(UizaV2Service.class);
        String accessKeyId = "BNEU77HJAPWYVIF1DEU5";
        String secretKeyId = "8yro1j369cCj6VR7cD2kzQbzJ2vDiswt7jxhtGjp";
        subscribe(service.auth(accessKeyId, secretKeyId), new ApiSubscriber<Auth>() {
            @Override
            public void onSuccess(Auth auth) {
                if (auth == null) {
                    showDialogError(getString(R.string.err_unknow));
                    return;
                }
                LLog.d(TAG, "getData onSuccess " + LSApplication.getInstance().getGson().toJson(auth));
                LPref.setAuth(activity, auth, LSApplication.getInstance().getGson());

                LLog.d(TAG, ">>>>token " + auth.getToken());

                goToHome(auth);
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail " + e.getMessage());
                handleException(e);
            }
        });
    }

    private void goToHome(Auth auth) {
        RestClient.init(getString(R.string.dev_uiza_v2_URL), auth.getToken());
        UizaData.getInstance().init(getString(R.string.dev_uiza_v2_URL), auth.getToken(), UizaData.PLAYER_ID_SKIN_1);

        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        LUIUtil.transActivityFadeIn(activity);
        finish();
    }
}
