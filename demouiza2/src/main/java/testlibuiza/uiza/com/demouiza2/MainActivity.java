package testlibuiza.uiza.com.demouiza2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v1.cannotslide.UizaPlayerActivityV1;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LPref;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String currentPlayerId = UizaData.PLAYER_ID_SKIN_1;
        boolean canSlide = false;
        int currentApiVersion = Constants.API_VERSION_1;
        String currentApiEndPoint = Constants.URL_DEV_UIZA_VERSION_2_STAG;
        String currentApiTrackingEndPoint = Constants.URL_TRACKING_STAG;

        String token = Constants.TOKEN_STAG;
        RestClientV2.init(currentApiEndPoint, token);
        UizaData.getInstance().init(currentApiEndPoint, currentApiTrackingEndPoint, token, currentPlayerId);
        UizaData.getInstance().setVideoCanSlide(canSlide);
        UizaData.getInstance().setApiVersion(currentApiVersion);

        Auth auth = new Auth();
        auth.setToken(token);
        auth.setAppId("a204e9cdeca44948a33e0d012ef74e90");
        LPref.setAuth(activity, auth, new Gson());

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UizaPlayerActivityV1.class);
                //intent.putExtra(KEY_UIZA_ENTITY_ID, "bf427eb6-51e4-43f9-b668-d15e5f324d9e");
                intent.putExtra(KEY_UIZA_ENTITY_ID, "6c16a6b2-905f-4b5d-ad3c-94c7793d0074");
                intent.putExtra(KEY_UIZA_ENTITY_COVER, Constants.URL_IMG_THUMBNAIL);
                intent.putExtra(KEY_UIZA_ENTITY_TITLE, "Test");
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
        return R.layout.activity_main;
    }
}