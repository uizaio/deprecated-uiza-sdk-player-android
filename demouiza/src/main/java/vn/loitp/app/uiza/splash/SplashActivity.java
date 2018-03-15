package vn.loitp.app.uiza.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.uiza.player.ui.data.UizaData;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.home.v1.HomeActivity;
import vn.loitp.app.uiza.home.v2.Home2Activity;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LDateUtils;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.utils.util.ToastUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Auth auth = LPref.getAuth(activity, LSApplication.getInstance().getGson());
        if (auth == null) {
            auth();
        } else {
            //TODO
            //checkToken(auth);

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

    private void auth() {
        RestClientV2.init(Constants.URL_DEV_UIZA2);
        UizaService service = RestClientV2.createService(UizaService.class);
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
        boolean isSlide = getIntent().getBooleanExtra(OptionActivity.KEY_SDK_VERSION, false);
        Intent intent;
        if (isSlide) {
            //v2
            RestClientV2.init(Constants.URL_DEV_UIZA2, auth.getToken());
            UizaData.getInstance().init(Constants.VS_SDK_2, Constants.URL_DEV_UIZA2, Constants.URL_TRACKING_DEV, auth.getToken(), UizaData.PLAYER_ID_SKIN_1);
            intent = new Intent(activity, Home2Activity.class);
        } else {
            //v1
            RestClientV2.init(Constants.URL_DEV_UIZA2, auth.getToken());
            UizaData.getInstance().init(Constants.VS_SDK_1, Constants.URL_DEV_UIZA2, Constants.URL_TRACKING_DEV, auth.getToken(), UizaData.PLAYER_ID_SKIN_1);
            intent = new Intent(activity, HomeActivity.class);
        }
        startActivity(intent);
        LUIUtil.transActivityFadeIn(activity);
        finish();

        //Test
        /*RestClientV2.init(getString(R.string.dev_uiza_v2_URL), auth.getToken());
        UizaData.getInstance().init(getString(R.string.dev_uiza_v2_URL), auth.getToken(), UizaData.PLAYER_ID_SKIN_1);
        Intent intent = new Intent(activity, UizaPlayerActivity.class);
        intent.putExtra(KEY_UIZA_ENTITY_ID, "f5dd9c0a-87fd-4bf8-be44-fe8cf394a885");
        intent.putExtra(KEY_UIZA_ENTITY_COVER, "");
        intent.putExtra(KEY_UIZA_ENTITY_TITLE, "KEY_
        UIZA_ENTITY_TITLE");
        startActivity(intent);
        LUIUtil.transActivityFadeIn(activity);*/
    }

    private void checkToken(Auth auth) {
        RestClientV2.init(Constants.URL_DEV_UIZA2, auth.getToken());
        UizaService service = RestClientV2.createService(UizaService.class);
        subscribe(service.checkToken(), new ApiSubscriber<Auth>() {
            @Override
            public void onSuccess(Auth a) {
                LLog.d(TAG, "checkToken: " + LSApplication.getInstance().getGson().toJson(a));
                LLog.d(TAG, "getExpired " + a.getExpired());
                long expiredTime = LDateUtils.convertDateToTimeStamp(a.getExpired());
                long currentTime = System.currentTimeMillis();
                LLog.d(TAG, "expiredTime " + expiredTime);
                LLog.d(TAG, "currentTime " + currentTime);
                if (currentTime > expiredTime) {
                    showDialogOne("Token đã hết hạn.", true);
                } else {
                    ToastUtils.showLong("Token hết hạn vào " + LDateUtils.convertTimestampToDate(expiredTime));
                    goToHome(a);
                }
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail " + e.getMessage());
                handleException(e);
            }
        });
    }
}
