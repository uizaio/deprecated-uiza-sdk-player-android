package vn.loitp.app.activity.demo.video.videodemo3;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.activity.demo.video.videodemo3.lib.UizaVideoView;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.livestar.R;

public class VideoDemo3Activity extends BaseActivity {

    private UizaVideoView uizaVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uizaVideoView = (UizaVideoView) findViewById(R.id.uiza_video_view);
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
    public void onNewIntent(Intent intent) {
        uizaVideoView.onNewIntent(intent);
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

    // Activity input

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // If the event was not handled then see if the player view can handle it.
        return super.dispatchKeyEvent(event) || uizaVideoView.getPlayerView().dispatchKeyEvent(event);
    }
}
