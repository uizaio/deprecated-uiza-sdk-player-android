package com.uiza.player.ui.player.v2.cannotslide;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v2.WrapperCallback;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaTrackingUtil;
import com.uiza.player.ui.util.UizaUIUtil;
import com.uiza.player.ui.views.helper.InputModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LActivityUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.restapi.restclient.RestClientV1;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.JsonBodyGetDetailEntity;
import vn.loitp.restapi.uiza.model.v2.getlinkdownload.GetLinkDownload;
import vn.loitp.restapi.uiza.model.v2.getlinkdownload.JsonBodyGetLinkDownload;
import vn.loitp.restapi.uiza.model.v2.getlinkdownload.Mpd;
import vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.restapi.uiza.model.v2.listallentity.Item;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.views.LToast;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class UizaPlayerActivityV2 extends BaseActivity {
    private InputModel inputModel;
    //TODO remove gson later
    private Gson gson = new Gson();

    public Gson getGson() {
        return gson;
    }

    private boolean isGetLinkPlayDone;
    private boolean isGetDetailEntityDone;

    private FrameLayout containerUizaVideo;
    private FrameLayout containerUizaVideoInfo;
    private FrmUizaVideoV2 frmUizaVideoV2;
    private FrmUizaVideoInfoV2 frmUizaVideoInfoV2;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avi);
        containerUizaVideo = (FrameLayout) findViewById(R.id.container_uiza_video);
        containerUizaVideoInfo = (FrameLayout) findViewById(R.id.container_uiza_video_info);

        String entityId = getIntent().getStringExtra(KEY_UIZA_ENTITY_ID);
        String entityCover = getIntent().getStringExtra(KEY_UIZA_ENTITY_COVER);
        String entityTitle = getIntent().getStringExtra(KEY_UIZA_ENTITY_TITLE);
        LLog.d(TAG, "entityId " + entityId);
        if (entityId == null || entityId.isEmpty()) {
            showDialogError("entityId == null || entityId.isEmpty()");
            return;
        }

        RestClientV2.init(UizaData.getInstance().getApiEndPoint(), UizaData.getInstance().getToken());

        inputModel = UizaUIUtil.createInputModel(entityId, entityCover, entityTitle);
        UizaData.getInstance().setInputModel(inputModel);

        getPlayerConfig();
    }

    private void initContainerVideo() {
        frmUizaVideoV2 = new FrmUizaVideoV2();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_down_enter, R.anim.slide_up_exit);
        transaction.replace(containerUizaVideo.getId(), frmUizaVideoV2);
        //transaction.addToBackStack(null);
        transaction.commit();

        frmUizaVideoV2.setWrapperCallback(new WrapperCallback() {
            @Override
            public void initializePlayer(Uri[] uris) {
                LLog.d(TAG, "setWrapperCallback initializePlayer");
                UizaUIUtil.updateSizeOfContainerVideo(containerUizaVideo, UizaScreenUtil.getScreenWidth(), UizaScreenUtil.getScreenWidth() * 9 / 16);
                avLoadingIndicatorView.smoothToHide();
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

        //track event eventype display
        frmUizaVideoV2.trackUiza(UizaTrackingUtil.createTrackingInput(activity, UizaTrackingUtil.EVENT_TYPE_DISPLAY));

        //track event plays_requested
        frmUizaVideoV2.trackUiza(UizaTrackingUtil.createTrackingInput(activity, UizaTrackingUtil.EVENT_TYPE_PLAYS_REQUESTED));
    }

    private void initContainerVideoInfo() {
        frmUizaVideoInfoV2 = new FrmUizaVideoInfoV2();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_up_enter, R.anim.slide_down_exit);
        transaction.replace(containerUizaVideoInfo.getId(), frmUizaVideoInfoV2);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    private void init() {
        if (isGetLinkPlayDone && isGetDetailEntityDone) {
            initContainerVideo();
            initContainerVideoInfo();
            LLog.d(TAG, "init success: isGetLinkPlayDone: " + isGetLinkPlayDone + ", isGetDetailEntityDone: " + isGetDetailEntityDone);
        } else {
            LLog.d(TAG, "init failed: isGetLinkPlayDone: " + isGetLinkPlayDone + ", isGetDetailEntityDone: " + isGetDetailEntityDone);
        }
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return "TAG" + getClass().getSimpleName();
    }

    @Override
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.uiza_player_activity;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LLog.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        UizaScreenUtil.toggleFullscreen(activity);
        UizaUIUtil.setSizeOfContainerVideo(containerUizaVideo, frmUizaVideoV2);
    }

    @Override
    public void onBackPressed() {
        LLog.d(TAG, "onBackPressed");
        UizaData.getInstance().reset();
        if (UizaData.getInstance().isLandscape()) {
            UizaScreenUtil.setFullScreen(activity, UizaScreenUtil.isFullScreen(activity));
        }
        super.onBackPressed();
    }

    private void getLinkPlay() {
        LLog.d(TAG, ">>>getLinkPlay entityId: " + inputModel.getEntityID());
        UizaService service = RestClientV2.createService(UizaService.class);
        Auth auth = LPref.getAuth(activity, gson);
        if (auth == null || auth.getData().getAppId() == null) {
            showDialogError("Error auth == null || auth.getAppId() == null");
            return;
        }
        LLog.d(TAG, ">>>getLinkPlay appId: " + auth.getData().getAppId());

        String entityId = inputModel.getEntityID();
        String appId = auth.getData().getAppId();

        //API v2
        subscribe(service.getLinkPlayV2(entityId, appId), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(GetLinkPlay getLinkPlay) {
                LLog.d(TAG, "getLinkPlayV2 onSuccess " + gson.toJson(getLinkPlay));
                List<String> listLinkPlay = new ArrayList<>();
                List<Mpd> mpdList = getLinkPlay.getMpd();
                for (Mpd mpd : mpdList) {
                    if (mpd.getUrl() != null) {
                        listLinkPlay.add(mpd.getUrl());
                    }
                }
                LLog.d(TAG, "getLinkPlayV2 toJson: " + gson.toJson(listLinkPlay));
                if (listLinkPlay == null || listLinkPlay.isEmpty()) {
                    LLog.d(TAG, "listLinkPlay == null || listLinkPlay.isEmpty()");
                    showDialogOne(getString(R.string.has_no_linkplay), true);
                    return;
                }
                UizaData.getInstance().setLinkPlay(listLinkPlay);
                isGetLinkPlayDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.d(TAG, "onFail getLinkDownloadV2: " + e.toString());
                handleException(e);
            }
        });
        //End API v2
    }

    /*private void getLinkDownload() {
        LLog.d(TAG, ">>>getLinkDownload entityId: " + inputModel.getEntityID());
        UizaService service = RestClientV2.createService(UizaService.class);
        Auth auth = LPref.getAuth(activity, gson);
        if (auth == null || auth.getData().getAppId() == null) {
            showDialogError("Error auth == null || auth.getAppId() == null");
            return;
        }
        LLog.d(TAG, ">>>getLinkDownload appId: " + auth.getData().getAppId());

        JsonBodyGetLinkDownload jsonBodyGetLinkDownload = new JsonBodyGetLinkDownload();
        List<String> listEntityIds = new ArrayList<>();
        listEntityIds.add(inputModel.getEntityID());
        jsonBodyGetLinkDownload.setListEntityIds(listEntityIds);

        //API v2
        subscribe(service.getLinkDownloadV2(jsonBodyGetLinkDownload), new ApiSubscriber<GetLinkDownload>() {
            @Override
            public void onSuccess(GetLinkDownload getLinkDownload) {
                LLog.d(TAG, "getLinkDownloadV2 onSuccess " + gson.toJson(getLinkDownload));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                //UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");

                List<String> listLinkPlay = new ArrayList<>();
                List<Mpd> mpdList = getLinkDownload.getData().get(0).getMpd();
                for (Mpd mpd : mpdList) {
                    if (mpd.getUrl() != null) {
                        listLinkPlay.add(mpd.getUrl());
                    }
                }
                LLog.d(TAG, "getLinkDownloadV2 toJson: " + gson.toJson(listLinkPlay));

                if (listLinkPlay == null || listLinkPlay.isEmpty()) {
                    LLog.d(TAG, "listLinkPlay == null || listLinkPlay.isEmpty()");
                    showDialogOne(getString(R.string.has_no_linkplay), true);
                    return;
                }

                UizaData.getInstance().setLinkPlay(listLinkPlay);
                isGetLinkPlayDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.d(TAG, "onFail getLinkDownloadV2: " + e.toString());
                handleException(e);
            }
        });
        //End API v2
    }*/

    private void getDetailEntity() {
        //LLog.d(TAG, ">>>getDetailEntityV2");
        if (inputModel == null) {
            //LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        //API v2
        UizaService service = RestClientV2.createService(UizaService.class);
        String entityId = inputModel.getEntityID();
        LLog.d(TAG, "entityId: " + entityId);

        JsonBodyGetDetailEntity jsonBodyGetDetailEntity = new JsonBodyGetDetailEntity();
        jsonBodyGetDetailEntity.setId(entityId);

        subscribe(service.getDetailEntityV2(jsonBodyGetDetailEntity), new ApiSubscriber<GetDetailEntity>() {
            @Override
            public void onSuccess(GetDetailEntity getDetailEntityV2) {
                LLog.d(TAG, "getDetailEntityV2 onSuccess " + gson.toJson(getDetailEntityV2));
                if (getDetailEntityV2 != null) {
                    UizaData.getInstance().setDetailEntityV2(getDetailEntityV2);
                } else {
                    showDialogError("Error: getDetailEntityV2 onSuccess detailEntity == null");
                }
                isGetDetailEntityDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "getDetailEntityV2 onFail " + e.toString());
                handleException(e);
            }
        });
        //EndAPI v2
    }

    private void getPlayerConfig() {
        //LLog.d(TAG, ">>>getPlayerConfig");
        if (inputModel == null) {
            //LLog.d(TAG, "mInputModel == null -> return");
            return;
        }

        if (UizaData.getInstance().getPlayerConfig() != null) {
            if (Constants.IS_DEBUG) {
                LToast.show(activity, "Player config is exist");
            }
            getLinkPlay();
            getDetailEntity();
            return;
        }

        RestClientV1.init(Constants.URL_DEV_UIZA_VERSION_1);
        UizaService service = RestClientV1.createService(UizaService.class);
        subscribe(service.getPlayerInfo(UizaData.getInstance().getPlayerId()), new ApiSubscriber<PlayerConfig>() {
            @Override
            public void onSuccess(PlayerConfig playerConfig) {
                LLog.d(TAG, "getPlayerConfig onSuccess " + gson.toJson(playerConfig));
                UizaData.getInstance().setPlayerConfig(playerConfig);

                getLinkPlay();
                getDetailEntity();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "getPlayerConfig onFail: " + e.toString());
                handleException(e);
            }
        });
    }

    public void playOnClickItem(Item item, int position) {
        LLog.d(TAG, "onClick " + position);
        Intent intent = new Intent(activity, UizaPlayerActivityV2.class);
        intent.putExtra(KEY_UIZA_ENTITY_ID, item.getId());
        intent.putExtra(KEY_UIZA_ENTITY_COVER, item.getThumbnail());
        intent.putExtra(KEY_UIZA_ENTITY_TITLE, item.getName());
        startActivity(intent);
        LActivityUtil.tranIn(activity);
    }
}
