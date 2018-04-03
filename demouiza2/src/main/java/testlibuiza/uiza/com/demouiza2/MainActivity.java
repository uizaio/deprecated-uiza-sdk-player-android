package testlibuiza.uiza.com.demouiza2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v1.cannotslide.UizaPlayerActivityV1;
import com.uiza.player.ui.player.v2.cannotslide.UizaPlayerActivityV2;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LActivityUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String currentPlayerId = Constants.PLAYER_ID_SKIN_1;
        boolean canSlide = false;
        String currentApiEndPoint = Constants.URL_DEV_UIZA_VERSION_2;
        String currentApiTrackingEndPoint = Constants.URL_TRACKING_DEV;

        String token = "30e23580-f326-4db4-9f3e-a01d609b32b3";
        RestClientV2.init(currentApiEndPoint, token);
        UizaData.getInstance().init(currentApiEndPoint, currentApiTrackingEndPoint, token, currentPlayerId);
        UizaData.getInstance().setVideoCanSlide(canSlide);

        String json = "{\"code\":200,\"data\":{\"appId\":\"a204e9cdeca44948a33e0d012ef74e90\",\"expired\":\"22/04/2018 03:32:46\",\"token\":\"30e23580-f326-4db4-9f3e-a01d609b32b3\"},\"datetime\":\"2018-03-23T03:32:46.242Z\",\"message\":\"ok\",\"name\":\"Resource\",\"type\":\"SUCCESS\",\"version\":2}";
        Auth auth = new Gson().fromJson(json, Auth.class);
        LLog.d(TAG, "auth: " + new Gson().toJson(auth));
        LPref.setAuth(activity, auth, new Gson());

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UizaPlayerActivityV2.class);
                intent.putExtra(KEY_UIZA_ENTITY_ID, "69af37f9-b5de-446a-bb58-44fe1e315ba2");
                intent.putExtra(KEY_UIZA_ENTITY_COVER, "//dev-static.uiza.io/69af37f9-b5de-446a-bb58-44fe1e315ba2-thumbnail-1522730799619-1522730799098.jpeg");
                intent.putExtra(KEY_UIZA_ENTITY_TITLE, "Japan girl bikini part8!日本妹比堅尼戰鬥格!!");
                startActivity(intent);
                LActivityUtil.tranIn(activity);
            }
        });

        findViewById(R.id.bt_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SimpleUizaPlayerActivity.class);
                startActivity(intent);
                LActivityUtil.tranIn(activity);
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
