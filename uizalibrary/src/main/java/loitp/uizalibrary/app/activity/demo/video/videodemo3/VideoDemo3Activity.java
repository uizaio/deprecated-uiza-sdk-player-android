package loitp.uizalibrary.app.activity.demo.video.videodemo3;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.Toast;

import vn.loitp.app.activity.demo.video.videodemo3.lib.helper.InputModel;
import vn.loitp.app.activity.demo.video.videodemo3.lib.view.UizaVideoView;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.utilities.LLog;
import vn.loitp.livestar.R;

public class VideoDemo3Activity extends BaseActivity {

    private UizaVideoView uizaVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LLog.d(TAG, ">>>>>>>onCreate");
        super.onCreate(savedInstanceState);
        uizaVideoView = (UizaVideoView) findViewById(R.id.uiza_video_view);

        InputModel inputModel = new InputModel();
        inputModel.setUri(Uri.parse("http://yt-dash-mse-test.commondatastorage.googleapis.com/media/feelings_vp9-20130806-manifest.mpd"));
        inputModel.setDrmLicenseUrl("https://proxy.uat.widevine.com/proxy?video_id\\u003dd286538032258a1c\\u0026provider\\u003dwidevine_test");
        inputModel.setAction("com.google.android.exoplayer.demo.action.VIEW");
        inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpreonly&cmsid=496&vid=short_onecue&correlator=");
        inputModel.setPreferExtensionDecoders(false);
        //inputModel.setExtension(sample.getExtension());

        uizaVideoView.setInputModel(inputModel, true);
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
        return R.layout.activity_video_demo_3;
    }

    @Override
    public void onStart() {
        super.onStart();
        uizaVideoView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        uizaVideoView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uizaVideoView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        uizaVideoView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uizaVideoView.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            uizaVideoView.initializePlayer();
        } else {
            Toast.makeText(activity, getString(R.string.storage_permission_denied), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || uizaVideoView.getPlayerView().dispatchKeyEvent(event);
    }
}
