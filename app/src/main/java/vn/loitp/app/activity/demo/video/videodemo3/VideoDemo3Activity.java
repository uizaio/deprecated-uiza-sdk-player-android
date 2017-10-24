package vn.loitp.app.activity.demo.video.videodemo3;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import java.util.UUID;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.activity.demo.video.videodemo3.lib.InputModel;
import vn.loitp.app.activity.demo.video.videodemo3.lib.UizaVideoView;
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
        //inputModel.setUri(Uri.parse("http://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"));
        inputModel.setDrmLicenseUrl("https://proxy.uat.widevine.com/proxy?video_id\\u003dd286538032258a1c\\u0026provider\\u003dwidevine_test");
        inputModel.setAction("com.google.android.exoplayer.demo.action.VIEW");
        inputModel.setPreferExtensionDecoders(false);

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
            ToastUtils.showShort(R.string.storage_permission_denied);
            finish();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || uizaVideoView.getPlayerView().dispatchKeyEvent(event);
    }
}
