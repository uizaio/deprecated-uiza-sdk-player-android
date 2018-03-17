package vn.loitp.app.uiza.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.uiza.player.ui.data.UizaData;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.home.v1.cannotslide.HomeV1CannotSlideActivity;
import vn.loitp.app.uiza.home.v1.cansilde.HomeV1CanSlideActivity;
import vn.loitp.app.uiza.home.v2.cannotslide.HomeV2CannotSlideActivity;
import vn.loitp.app.uiza.home.v2.cansilde.HomeV2CanSlideActivity;
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
            //TODO chi lay token neu la apiv2
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
        LLog.d(TAG, ">>>>>>>>>.auth");
        RestClientV2.init(Constants.URL_DEV_UIZA_VERSION_2);
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
        String currentPlayerId = getIntent().getStringExtra(OptionActivity.KEY_SKIN);
        boolean canSlide = getIntent().getBooleanExtra(OptionActivity.KEY_CAN_SLIDE, false);
        int currentApiVersion = getIntent().getIntExtra(OptionActivity.KEY_API_VERSION, Constants.API_VERSION_1);
        String currentApiEndPoint = getIntent().getStringExtra(OptionActivity.KEY_API_END_POINT);
        String currentApiTrackingEndPoint = getIntent().getStringExtra(OptionActivity.KEY_API_TRACKING_END_POINT);

        LLog.d(TAG, "currentPlayerId " + currentPlayerId);
        LLog.d(TAG, "canSlide " + canSlide);
        LLog.d(TAG, "currentApiVersion " + currentApiVersion);
        LLog.d(TAG, "currentApiEndPoint " + currentApiEndPoint);
        LLog.d(TAG, "currentApiTrackingEndPoint " + currentApiTrackingEndPoint);

        String token = null;
        if (currentApiVersion == Constants.API_VERSION_1) {
            switch (currentApiEndPoint) {
                case Constants.URL_DEV_UIZA_VERSION_2:
                    LLog.d(TAG, "API_VERSION_1 Constants.TOKEN_DEV_V1");
                    token = Constants.TOKEN_DEV_V1;
                    break;
                case Constants.URL_DEV_UIZA_VERSION_2_STAG:
                    LLog.d(TAG, "API_VERSION_1 Constants.TOKEN_STAG;");
                    token = Constants.TOKEN_STAG;
                    break;
                case Constants.URL_WTT:
                    LLog.d(TAG, "API_VERSION_1 Constants.TOKEN_WTT;");
                    token = Constants.TOKEN_WTT;
                    break;
            }
        } else {
            switch (currentApiEndPoint) {
                case Constants.URL_DEV_UIZA_VERSION_2:
                    LLog.d(TAG, "API_VERSION_2 auth.getToken");
                    token = auth.getToken();
                    break;
                case Constants.URL_DEV_UIZA_VERSION_2_STAG:
                    LLog.d(TAG, "API_VERSION_2 null");
                    token = null;
                    break;
                case Constants.URL_WTT:
                    LLog.d(TAG, "API_VERSION_2 null");
                    token = null;
                    break;
            }
        }

        LLog.d(TAG, "goToHome token: " + token);
        LLog.d(TAG, "goToHome appId: " + auth.getAppId());

        if (token == null) {
            showDialogOne("token==null", true);
            return;
        }

        Intent intent = null;
        RestClientV2.init(currentApiEndPoint, token);
        UizaData.getInstance().init(currentApiEndPoint, currentApiTrackingEndPoint, token, currentPlayerId);
        UizaData.getInstance().setVideoCanSlide(canSlide);
        UizaData.getInstance().setApiVersion(currentApiVersion);
        if (currentApiVersion == Constants.API_VERSION_1) {
            if (canSlide) {
                LLog.d(TAG, "goToHome HomeV1CanSlideActivity");
                intent = new Intent(activity, HomeV1CanSlideActivity.class);
            } else {
                LLog.d(TAG, "goToHome HomeV1CannotSlideActivity");
                intent = new Intent(activity, HomeV1CannotSlideActivity.class);
            }
        } else {
            if (canSlide) {
                LLog.d(TAG, "goToHome HomeV2CanSlideActivity");
                intent = new Intent(activity, HomeV2CanSlideActivity.class);
            } else {
                LLog.d(TAG, "goToHome HomeV2CannotSlideActivity");
                intent = new Intent(activity, HomeV2CannotSlideActivity.class);
            }
        }
        startActivity(intent);
        LUIUtil.transActivityFadeIn(activity);
        finish();
    }

    private void checkToken(Auth auth) {
        RestClientV2.init(Constants.URL_DEV_UIZA_VERSION_2, auth.getToken());
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
