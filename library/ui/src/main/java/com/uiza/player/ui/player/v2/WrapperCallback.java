package com.uiza.player.ui.player.v2;

/**
 * Created by LENOVO on 4/4/2018.
 */

public interface WrapperCallback {
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState);

    public void onTrackVideoStart();

    public void onTrackVideoView();
}
