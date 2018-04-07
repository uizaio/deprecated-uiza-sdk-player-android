package testlibuiza.uiza.com.demouiza2;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v2.WrapperCallback;
import com.uiza.player.ui.player.v2.cannotslide.FrmUizaVideoV2;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaUIUtil;
import com.uiza.player.ui.views.AspectRatioFrameLayout;
import com.uiza.player.ui.views.helper.InputModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
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
        //set theme
        //UizaData.getInstance().setPlayerConfig(getDummyPlayerConfig());

        String entityId = "69af37f9-b5de-446a-bb58-44fe1e315ba2";
        String entityCover = "//dev-static.uiza.io/69af37f9-b5de-446a-bb58-44fe1e315ba2-thumbnail-1522730799619-1522730799098.jpeg";
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
            //when frmUizaVideoV2 is init success
            @Override
            public void initializePlayer(Uri[] uris) {
                LLog.d(TAG, "setWrapperCallback initializePlayer");
                UizaUIUtil.updateSizeOfContainerVideo(containerUizaVideo, UizaScreenUtil.getScreenWidth(), UizaScreenUtil.getScreenWidth() * 9 / 16);

                testFunc();
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

    private PlayerConfig getDummyPlayerConfig() {
        String json = "{\"endscreen\":{},\"setting\":{\"allowFullscreen\":\"true\",\"autoStart\":\"true\",\"displayPlaylist\":\"true\",\"showQuality\":\"true\"},\"socialSharing\":{\"allow\":\"false\",\"controller\":{\"facebook\":\"true\",\"linkedin\":\"true\",\"pinterest\":\"true\",\"tumblr\":\"true\",\"twitter\":\"true\"}},\"styling\":{\"background\":\"#ffffff\",\"buffer\":\"#ffffff\",\"icons\":\"#ffffff\",\"name\":\"uiza\",\"progress\":\"#ffffff\",\"title\":\"true\"}}";
        return new Gson().fromJson(json, PlayerConfig.class);
    }

    private void testFunc() {
        //hide/show controller
        //frmUizaVideoV2.getPlayerView().hideController();
        //frmUizaVideoV2.getPlayerView().showController();

        //use controller or not
        //frmUizaVideoV2.getPlayerView().setUseController(false);

        //frmUizaVideoV2.getPlayerView().setControllerAutoShow(true);
        //boolean isControllerAutoShow = frmUizaVideoV2.getPlayerView().getControllerAutoShow();
        //LLog.d(TAG, "testFunc isControllerAutoShow " + isControllerAutoShow);

        //frmUizaVideoV2.getPlayerView().setControllerHideOnTouch(false);
        //boolean getControllerHideOnTouch = frmUizaVideoV2.getPlayerView().getControllerHideOnTouch();
        //LLog.d(TAG, "testFunc getControllerHideOnTouch " + getControllerHideOnTouch);

        //frmUizaVideoV2.getPlayerView().setControllerShowTimeoutMs(10000);
        //int getControllerShowTimeoutMs = frmUizaVideoV2.getPlayerView().getControllerShowTimeoutMs();
        //LLog.d(TAG, "testFunc getControllerShowTimeoutMs " + getControllerShowTimeoutMs);

        //frmUizaVideoV2.getPlayerView().getDefaultArtwork();
        //frmUizaVideoV2.getPlayerView().getOverlayFrameLayout();
        //frmUizaVideoV2.getPlayerView().getSubtitleView();

        //pause and resume
        /*LUIUtil.setDelay(2000, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                frmUizaVideoV2.getPlayerView().pausePlayVideo();
                LUIUtil.setDelay(2000, new LUIUtil.DelayCallback() {
                    @Override
                    public void doAfter(int mls) {
                        frmUizaVideoV2.getPlayerView().resumePlayVideo();
                    }
                });
            }
        });*/

        //frmUizaVideoV2.getPlayerView().setFastForwardIncrementMs(10000);
        //frmUizaVideoV2.getPlayerView().setRewindIncrementMs(10000);

        //show repeat view in playbackcontroller
        //frmUizaVideoV2.getPlayerView().setRepeatToggleModes(RepeatModeUtil.REPEAT_TOGGLE_MODE_ALL);
        //frmUizaVideoV2.getPlayerView().getController().getRepeatToggleModes();

        //frmUizaVideoV2.getPlayerView().setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
    }
}
