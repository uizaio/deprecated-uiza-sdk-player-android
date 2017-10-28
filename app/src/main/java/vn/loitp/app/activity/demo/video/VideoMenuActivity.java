package vn.loitp.app.activity.demo.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import vn.loitp.app.activity.demo.video.videodemo1.VideoDemo1Activity;
import vn.loitp.app.activity.demo.video.videodemo4.VideoDemo4Activity;
import vn.loitp.app.activity.demo.video.videodemo3.VideoDemo3Activity;
import vn.loitp.app.activity.demo.video.videodemo3.lib.model.UriSample;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

public class VideoMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, VideoDemo1Activity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String js = "{\"uri\":\"http://yt-dash-mse-test.commondatastorage.googleapis.com/media/feelings_vp9-20130806-manifest.mpd\",\"drmLicenseUrl\":\"https://proxy.uat.widevine.com/proxy?video_id\\u003dd286538032258a1c\\u0026provider\\u003dwidevine_test\",\"drmSchemeUuid\":\"edef8ba9-79d6-4ace-a3c8-27dcd51d21ed\",\"name\":\"WV: HDCP not specified\",\"preferExtensionDecoders\":false}";
                UriSample sample = LSApplication.getInstance().getGson().fromJson(js, UriSample.class);
                LLog.d(TAG, ">>>>>>>>" + LSApplication.getInstance().getGson().toJson(sample));
                startActivity(sample.buildIntent(activity));
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, VideoDemo3Activity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }
        });
        findViewById(R.id.bt_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, VideoDemo4Activity.class);
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
        return R.layout.activity_menu_video;
    }
}
