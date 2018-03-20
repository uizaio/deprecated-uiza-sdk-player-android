package com.uiza.player.ui.player.v1.cannotslide;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.util.UizaAnimationUtil;
import com.uiza.player.ui.util.UizaImageUtil;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaTrackingUtil;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.restapi.restclient.RestClientTracking;
import vn.loitp.restapi.restclient.RestClientV1;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.tracking.UizaTracking;
import vn.loitp.restapi.uiza.model.v1.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v1.getlinkplay.GetLinkPlay;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class UizaPlayerActivityV1 extends BaseActivity {
    private InputModel inputModel;
    //TODO remove gson later
    private Gson gson = new Gson();

    public Gson getGson() {
        return gson;
    }

    private boolean isGetLinkPlayDone;
    private boolean isGetDetailEntityDone;

    private FrameLayout flRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flRootView = (FrameLayout) findViewById(R.id.fl_root_view);

        String entityId = getIntent().getStringExtra(KEY_UIZA_ENTITY_ID);
        String entityCover = getIntent().getStringExtra(KEY_UIZA_ENTITY_COVER);
        String entityTitle = getIntent().getStringExtra(KEY_UIZA_ENTITY_TITLE);
        LLog.d(TAG, "entityId " + entityId);
        if (entityId == null || entityId.isEmpty()) {
            showDialogError("entityId == null || entityId.isEmpty()");
            return;
        }

        RestClientV2.init(UizaData.getInstance().getApiEndPoint(), UizaData.getInstance().getToken());

        inputModel = createInputModel(entityId, entityCover, entityTitle);
        UizaData.getInstance().setInputModel(inputModel);

        getPlayerConfig();

        //track event eventype display
        trackUiza(UizaTrackingUtil.createTrackingInput(activity, UizaTrackingUtil.EVENT_TYPE_DISPLAY));

        //track event plays_requested
        trackUiza(UizaTrackingUtil.createTrackingInput(activity, UizaTrackingUtil.EVENT_TYPE_PLAYS_REQUESTED));
    }

    private InputModel createInputModel(String entityId, String entityCover, String entityTitle) {
        InputModel inputModel = new InputModel();
        inputModel.setEntityID(entityId);

        if (entityCover == null || entityCover.isEmpty()) {
            inputModel.setUrlImg(Constants.URL_IMG_9x16);
        } else {
            inputModel.setUrlImg(Constants.PREFIXS + entityCover);
        }
        inputModel.setTitle(entityTitle + "");

        inputModel.setExtension("mpd");
        //inputModel.setDrmLicenseUrl("");
        inputModel.setAction(inputModel.getPlaylist() == null ? FrmUizaVideoV1.ACTION_VIEW : FrmUizaVideoV1.ACTION_VIEW_LIST);
        inputModel.setPreferExtensionDecoders(false);

        //TODO remove this code below
        //inputModel.setUri("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");
        //inputModel.setUri("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");
        //inputModel.setUri("http://cdn-broadcast.yuptv.vn/ba_dash/0c45905848ca4ec99d2ed7c11bc8f8ad-a1556c60605a4fe4a1a22eafb4e89b44/index.mpd");

        //inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=");
        return inputModel;
    }

    private void initContainerVideo() {
        FrmUizaVideoV1 objFragment = new FrmUizaVideoV1();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_uiza_video, objFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initContainerVideoInfo() {
        FrmUizaVideoInfoV1 objFragment = new FrmUizaVideoInfoV1();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_uiza_video_info, objFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    private ImageView ivCoverVideo;
    private ImageView ivCoverLogo;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    private void setCoverVideo() {
        if (flRootView != null && inputModel != null) {
            //LLog.d(TAG, "setCoverVideo " + inputModel.getUrlImg());
            if (ivCoverVideo != null || ivCoverLogo != null || avLoadingIndicatorView != null) {
                return;
            }
            ivCoverVideo = new ImageView(activity);
            ivCoverVideo.setScaleType(ImageView.ScaleType.CENTER_CROP);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ivCoverVideo.setLayoutParams(layoutParams);
            UizaImageUtil.load(activity, inputModel.getUrlImg(), ivCoverVideo);
            flRootView.addView(ivCoverVideo);

            ivCoverLogo = new ImageView(activity);
            ivCoverLogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
            FrameLayout.LayoutParams layoutParamsIvLogo = new FrameLayout.LayoutParams(150, 150);
            ivCoverLogo.setLayoutParams(layoutParamsIvLogo);
            ivCoverLogo.setImageResource(R.drawable.uiza_logo_512);
            layoutParamsIvLogo.gravity = Gravity.CENTER;
            flRootView.addView(ivCoverLogo);

            avLoadingIndicatorView = new AVLoadingIndicatorView(activity);
            avLoadingIndicatorView.setIndicatorColor(Color.WHITE);
            avLoadingIndicatorView.setIndicator("BallSpinFadeLoaderIndicator");
            FrameLayout.LayoutParams aviLayout = new FrameLayout.LayoutParams(100, 100);
            aviLayout.setMargins(0, 0, 0, 200);
            aviLayout.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            avLoadingIndicatorView.setLayoutParams(aviLayout);
            flRootView.addView(avLoadingIndicatorView);
            LLog.d(TAG, "setCoverVideo");
        }
    }

    public void removeCoverVideo() {
        if (flRootView != null && ivCoverVideo != null && ivCoverLogo != null && avLoadingIndicatorView != null) {
            UizaAnimationUtil.playFadeOut(activity, ivCoverVideo, null);

            avLoadingIndicatorView.smoothToHide();
            ivCoverVideo.setVisibility(View.GONE);
            flRootView.removeView(ivCoverVideo);
            ivCoverVideo = null;

            ivCoverLogo.setVisibility(View.GONE);
            flRootView.removeView(ivCoverLogo);
            ivCoverLogo = null;
            avLoadingIndicatorView = null;

            LLog.d(TAG, "removeCoverVideo success");
        }
    }

    private void init() {
        if (isGetLinkPlayDone && isGetDetailEntityDone) {
            initContainerVideo();
            initContainerVideoInfo();
            /*LUIUtil.setDelay(500, new LUIUtil.DelayCallback() {
                @Override
                public void doAfter(int mls) {
                    LLog.d(TAG, "init success");
                    removeCoverVideo();
                }
            });*/
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
        super.onConfigurationChanged(newConfig);
        UizaScreenUtil.toggleFullscreen(activity);
    }

    @Override
    public void onBackPressed() {
        UizaData.getInstance().reset();
        super.onBackPressed();
    }

    private void getLinkPlay() {
        LLog.d(TAG, ">>>getLinkPlayV1 entityId: " + inputModel.getEntityID());
        UizaService service = RestClientV2.createService(UizaService.class);
        Auth auth = LPref.getAuth(activity, gson);
        if (auth == null || auth.getAppId() == null) {
            showDialogError("Error auth == null || auth.getAppId() == null");
            return;
        }
        LLog.d(TAG, ">>>getLinkPlayV1 appId: " + auth.getAppId());

        //API v1
        subscribe(service.getLinkPlayV1(inputModel.getEntityID(), auth.getAppId()), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(GetLinkPlay getLinkPlay) {
                LLog.d(TAG, "getLinkPlayV1 onSuccess " + gson.toJson(getLinkPlay));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                //UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");

                //TODO play link mpd0 (pre cdn la vn) ko dc thi play link mpd1(cdn la QT)
                //LLog.d(TAG, "getLinkplayMpd " + getLinkPlay.getMpd().get(1));
                //UizaData.getInstance().setLinkPlay(getLinkPlay.getMpd().get(1));

                //LLog.d(TAG, "getLinkplayMpd " + getLinkPlay.getMpd());
                UizaData.getInstance().setLinkPlay(getLinkPlay.getMpd());
                isGetLinkPlayDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.d(TAG, "onFail getLinkPlayV1: " + e.toString());
                handleException(e);
            }
        });
        //End API v1

        //API v2
        /*subscribe(service.getLinkPlayV2(inputModel.getEntityID(), auth.getAppId()), new ApiSubscriber<vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay>() {
            @Override
            public void onSuccess(vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay getLinkPlayV1) {
                LLog.d(TAG, "getLinkPlayV1 onSuccess " + gson.toJson(getLinkPlayV1));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                //UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");

                try {
                    //Mpd mpdVN = getLinkPlayV1.getMpd().get(0);
                    Mpd mpdInter = getLinkPlayV1.getMpd().get(1);

                    String linkPlay = mpdInter.getUrl();
                    //LLog.d(TAG, "linkPlay " + linkPlay);
                    UizaData.getInstance().setLinkPlay(linkPlay);
                    isGetLinkPlayDone = true;
                    init();
                } catch (NullPointerException e) {
                    showDialogError("Error NullPointerException " + e.toString());
                }
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail getLinkPlayV1 " + e.toString());
                handleException(e);
            }
        });*/
        //End API v2
    }

    private void getDetailEntity() {
        //LLog.d(TAG, ">>>getDetailEntityV2");
        if (inputModel == null) {
            //LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        //API v1
        UizaService service = RestClientV2.createService(UizaService.class);
        String entityId = inputModel.getEntityID();
        LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntityV1(entityId), new ApiSubscriber<GetDetailEntity>() {
            @Override
            public void onSuccess(GetDetailEntity getDetailEntity) {
                LLog.d(TAG, "getDetailEntityV1 onSuccess " + gson.toJson(getDetailEntity));
                if (getDetailEntity != null) {
                    UizaData.getInstance().setDetailEntityV1(getDetailEntity);
                } else {
                    showDialogError("Error: getDetailEntityV1 onSuccess detailEntity == null");
                }
                isGetDetailEntityDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail getDetailEntityV1: " + e.toString());
                handleException(e);
            }
        });
        //End API v1

        //API v2
        /*UizaService service = RestClientV2.createService(UizaService.class);
        String entityId = inputModel.getEntityID();
        //LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntityV2(entityId), new ApiSubscriber<GetDetailEntity>() {
            @Override
            public void onSuccess(GetDetailEntity getDetailEntityV2) {
                //LLog.d(TAG, "getDetailEntityV2 onSuccess " + gson.toJson(getDetailEntityV2));
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
        });*/
        //EndAPI v2
    }

    private void getPlayerConfig() {
        //LLog.d(TAG, ">>>getPlayerConfig");
        if (inputModel == null) {
            //LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        setCoverVideo();
        RestClientV1.init(Constants.URL_DEV_UIZA_VERSION_1);
        UizaService service = RestClientV1.createService(UizaService.class);
        subscribe(service.getPlayerInfo(UizaData.getInstance().getPlayerId()), new vn.loitp.rxandroid.ApiSubscriber<PlayerConfig>() {
            @Override
            public void onSuccess(PlayerConfig playerConfig) {
                //TODO custom theme
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

    public void trackUiza(final UizaTracking uizaTracking) {
        LLog.d(TAG, ">>>>>>>>>>>>>>>>trackUiza getEventType: " + uizaTracking.getEventType() + ", getPlayThrough: " + uizaTracking.getPlayThrough());
        RestClientTracking.init(UizaData.getInstance().getApiTrackingEndPoint());
        UizaService service = RestClientTracking.createService(UizaService.class);
        subscribe(service.track(uizaTracking), new ApiSubscriber<Object>() {
            @Override
            public void onSuccess(Object tracking) {
                LLog.d(TAG, "<<<<<<<<<<<<<<<trackUiza onSuccess - " + uizaTracking.getEventType() + " -> " + gson.toJson(tracking));
            }

            @Override
            public void onFail(Throwable e) {
                //TODO send event fail? Try to send again
                LLog.d(TAG, "trackUiza onFail " + e.toString());
                handleException(e);
            }
        });
    }
}
