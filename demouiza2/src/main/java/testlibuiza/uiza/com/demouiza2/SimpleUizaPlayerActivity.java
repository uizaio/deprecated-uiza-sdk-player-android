package testlibuiza.uiza.com.demouiza2;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v2.WrapperCallback;
import com.uiza.player.ui.player.v2.cannotslide.FrmUizaVideoV2;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaUIUtil;
import com.uiza.player.ui.views.helper.InputModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LLog;
import vn.loitp.restapi.uiza.model.v2.listallentity.Item;

public class SimpleUizaPlayerActivity extends BaseActivity {

    private FrameLayout containerUizaVideo;
    private FrmUizaVideoV2 frmUizaVideoV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        containerUizaVideo = (FrameLayout) findViewById(io.uiza.sdk.ui.R.id.container_uiza_video);
        initContainerVideo();
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
        return R.layout.activity_simple_uiza_player;
    }

    private void initContainerVideo() {
        String entityId = "69af37f9-b5de-446a-bb58-44fe1e315ba2";
        //String entityCover = "//dev-static.uiza.io/69af37f9-b5de-446a-bb58-44fe1e315ba2-thumbnail-1522730799619-1522730799098.jpeg";
        String entityCover = "https://kenh14cdn.com/2018/2/25/photo-3-15195643072361674959682.jpg";
        String entityTitle = "Japan girl bikini part8!日本妹比堅尼戰鬥格!!";

        InputModel inputModel = UizaUIUtil.createInputModel(entityId, entityCover, entityTitle);
        UizaData.getInstance().setInputModel(inputModel);

        List<String> listLinkPlay = new ArrayList<>();
        listLinkPlay.add("https://dev-cdn.uiza.io:443/mx5Z5wIs/package/playlist.mpd");
        listLinkPlay.add("https://cdn-vn-cache-3.uiza.io:443/a204e9cdeca44948a33e0d012ef74e90/mx5Z5wIs/package/playlist.mpd");
        UizaData.getInstance().setLinkPlay(listLinkPlay);

        frmUizaVideoV2 = new FrmUizaVideoV2();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerUizaVideo.getId(), frmUizaVideoV2);
        transaction.commit();

        frmUizaVideoV2.setWrapperCallback(new WrapperCallback() {
            @Override
            public void initializePlayer(Uri[] uris) {
                LLog.d(TAG, "setWrapperCallback initializePlayer");
                UizaUIUtil.updateSizeOfContainerVideo(containerUizaVideo, UizaScreenUtil.getScreenWidth(), UizaScreenUtil.getScreenWidth() * 9 / 16);
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                LLog.d(TAG, "setWrapperCallback onPlayerStateChanged " + playbackState);
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                LLog.d(TAG, "setWrapperCallback onTracksChanged");
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                LLog.d(TAG, "setWrapperCallback onLoadingChanged");
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {
                LLog.d(TAG, "setWrapperCallback onRepeatModeChanged");
            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                LLog.d(TAG, "setWrapperCallback onPlaybackParametersChanged");
            }

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {
                LLog.d(TAG, "setWrapperCallback onTimelineChanged");
            }

            @Override
            public void onTrackVideoStart() {
                LLog.d(TAG, "setWrapperCallback onTrackVideoStart");
                UizaUIUtil.setSizeOfContainerVideo(containerUizaVideo, frmUizaVideoV2);
            }

            @Override
            public void onTrackVideoView() {
                LLog.d(TAG, "setWrapperCallback onTrackVideoView");
            }

            @Override
            public void onTrackPlayThrough(int percentOfVideoDuration) {
                LLog.d(TAG, "setWrapperCallback onTrackPlayThrough " + percentOfVideoDuration);
            }

            @Override
            public void onPlaybackControllerClickSetting() {
                LLog.d(TAG, "setWrapperCallback onPlaybackControllerClickSetting");
            }

            @Override
            public void onClickItemPlayList(Item item, int position) {
                LLog.d(TAG, "setWrapperCallback onClickItemPlayList");
            }

            @Override
            public void onVisibilityChange(int visibility) {
                LLog.d(TAG, "setWrapperCallback onVisibilityChange");
            }

            @Override
            public void onErrorNoLinkPlay() {
                LLog.d(TAG, "setWrapperCallback onErrorNoLinkPlay");
            }

            @Override
            public void onErrorCannotPlayAnyLinkPlay() {
                LLog.d(TAG, "setWrapperCallback onErrorCannotPlayAnyLinkPlay");
            }

            @Override
            public void onReleasePlayer() {
                LLog.d(TAG, "setWrapperCallback onReleasePlayer");
            }

            @Override
            public void onAdLoadError(IOException error) {
                LLog.d(TAG, "setWrapperCallback onAdLoadError");
            }

            @Override
            public void onAdClicked() {
                LLog.d(TAG, "setWrapperCallback onAdClicked");
            }

            @Override
            public void onAdTapped() {
                LLog.d(TAG, "setWrapperCallback onAdTapped");
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LLog.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        UizaScreenUtil.toggleFullscreen(activity);
        UizaUIUtil.setSizeOfContainerVideo(containerUizaVideo, frmUizaVideoV2);
    }
}
