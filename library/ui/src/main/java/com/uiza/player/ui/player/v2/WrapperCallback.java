package com.uiza.player.ui.player.v2;

import android.net.Uri;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

import java.io.IOException;

import vn.loitp.restapi.uiza.model.v2.listallentity.Item;

/**
 * Created by LENOVO on 4/4/2018.
 */

public interface WrapperCallback {
    public void initializePlayer(Uri[] uris);

    public void onPlayerStateChanged(boolean playWhenReady, int playbackState);

    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections);

    public void onLoadingChanged(boolean isLoading);

    public void onRepeatModeChanged(int repeatMode);

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters);

    public void onTimelineChanged(Timeline timeline, Object manifest);

    public void onTrackVideoStart();

    public void onTrackVideoView();

    public void onTrackPlayThrough(int percentOfVideoDuration);

    public void onPlaybackControllerClickSetting();

    public void onClickItemPlayList(Item item, int position);

    public void onVisibilityChange(int visibility);

    public void onErrorNoLinkPlay();

    public void onErrorCannotPlayAnyLinkPlay();

    public void onReleasePlayer();

    public void onAdLoadError(IOException error);

    public void onAdClicked();

    public void onAdTapped();
}
