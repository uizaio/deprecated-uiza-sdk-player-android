package vn.loitp.app.activity.demo.video.videodemo3;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import vn.loitp.app.activity.demo.video.videodemo3.lib.frm.UizaVideo;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.livestar.R;

public class TestVideoDemo3Activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orientVideoDescriptionFragment(getResources().getConfiguration().orientation);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        orientVideoDescriptionFragment(configuration.orientation);
    }

    private void orientVideoDescriptionFragment(int orientation) {
        // Hide the extra content when in landscape so the video is as large as possible.
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment infoVideoFrm = fragmentManager.findFragmentById(R.id.uiza_video_info);

        if (infoVideoFrm != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentTransaction.hide(infoVideoFrm);
            } else {
                fragmentTransaction.show(infoVideoFrm);
            }
            fragmentTransaction.commit();
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
        return R.layout.activity_test_video_demo_3;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment frmUizaVideo = fragmentManager.findFragmentById(R.id.uiza_video);
        //super.onNewIntent(intent);
        if (frmUizaVideo != null) {
            if (frmUizaVideo instanceof UizaVideo) {
                ((UizaVideo) frmUizaVideo).onNewInten(intent);
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment frmUizaVideo = fragmentManager.findFragmentById(R.id.uiza_video);

        SimpleExoPlayerView simpleExoPlayerView = null;
        if (frmUizaVideo != null) {
            if (frmUizaVideo instanceof UizaVideo) {
                simpleExoPlayerView = ((UizaVideo) frmUizaVideo).getSimpleExoPlayerView();
            }
        }
        if (simpleExoPlayerView == null) {
            return super.dispatchKeyEvent(event);
        }

        // If the event was not handled then see if the player view can handle it.
        return super.dispatchKeyEvent(event) || simpleExoPlayerView.dispatchKeyEvent(event);
    }
}
