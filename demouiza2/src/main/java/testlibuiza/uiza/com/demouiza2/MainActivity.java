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
        String currentApiEndPoint = Constants.URL_WTT;
        String currentApiTrackingEndPoint = Constants.URL_TRACKING_PROD;

        String token = Constants.TOKEN_WTT;
        RestClientV2.init(currentApiEndPoint, token);
        UizaData.getInstance().init(currentApiEndPoint, currentApiTrackingEndPoint, token, currentPlayerId);
        UizaData.getInstance().setVideoCanSlide(canSlide);

        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"token\": \"lsn9LZdm0MBrhGlyrFYqJYSjJfIXX27e-1512986583784\",\n" +
                "        \"expired\": \"22/04/2018 03:32:46\",\n" +
                "        \"appId\": \"a204e9cdeca44948a33e0d012ef74e90\"\n" +
                "    },\n" +
                "    \"version\": 2,\n" +
                "    \"datetime\": \"2018-03-23T03:32:46.242Z\",\n" +
                "    \"name\": \"Resource\",\n" +
                "    \"message\": \"ok\",\n" +
                "    \"code\": 200,\n" +
                "    \"type\": \"SUCCESS\"\n" +
                "}";
        Auth auth = new Gson().fromJson(json, Auth.class);
        LLog.d(TAG, "auth: " + new Gson().toJson(auth));
        LPref.setAuth(activity, auth, new Gson());

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UizaPlayerActivityV1.class);
                //intent.putExtra(KEY_UIZA_ENTITY_ID, "bf427eb6-51e4-43f9-b668-d15e5f324d9e");
                intent.putExtra(KEY_UIZA_ENTITY_ID, "98fa3908-2523-4291-aa43-851d08694094");
                intent.putExtra(KEY_UIZA_ENTITY_COVER, "//wtt-static.uiza.io/98fa3908-2523-4291-aa43-851d08694094_thumbnail_1516694859408.jpeg");
                intent.putExtra(KEY_UIZA_ENTITY_TITLE, "Trấn Thành: \"Là Lạ Lắm À Nghen\" - Thùy Dương, Trấn Thành | Ơn Giời Cậu Đây Rồi | Tập 01 | Phần 2");
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
