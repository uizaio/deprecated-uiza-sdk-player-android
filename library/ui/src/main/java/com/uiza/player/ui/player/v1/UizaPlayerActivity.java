package com.uiza.player.ui.player.v1;

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
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v2.getlinkplay.Mpd;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class UizaPlayerActivity extends BaseActivity {
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

        updateUIStatusNavigationBar(true);

        String entityId = getIntent().getStringExtra(KEY_UIZA_ENTITY_ID);
        String entityCover = getIntent().getStringExtra(KEY_UIZA_ENTITY_COVER);
        String entityTitle = getIntent().getStringExtra(KEY_UIZA_ENTITY_TITLE);
        LLog.d(TAG, "entityId " + entityId);
        if (entityId == null || entityId.isEmpty()) {
            showDialogError("entityId == null || entityId.isEmpty()");
            return;
        }

        RestClient.init(UizaData.getInstance().getApiEndPoint(), UizaData.getInstance().getToken());

        inputModel = createInputModel(entityId, entityCover, entityTitle);
        UizaData.getInstance().setInputModel(inputModel);

        getPlayerConfig();
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
        inputModel.setAction(inputModel.getPlaylist() == null ? FrmUizaVideo.ACTION_VIEW : FrmUizaVideo.ACTION_VIEW_LIST);
        inputModel.setPreferExtensionDecoders(false);

        //TODO remove this code below
        //inputModel.setUri("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");
        //inputModel.setUri("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");
        //inputModel.setUri("http://cdn-broadcast.yuptv.vn/ba_dash/0c45905848ca4ec99d2ed7c11bc8f8ad-a1556c60605a4fe4a1a22eafb4e89b44/index.mpd");

        //inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=");
        return inputModel;
    }

    private void initContainerVideo() {
        FrmUizaVideo objFragment = new FrmUizaVideo();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_uiza_video, objFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initContainerVideoInfo() {
        FrmUizaVideoInfo objFragment = new FrmUizaVideoInfo();
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

    private void removeCoverVideo() {
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
            LUIUtil.setDelay(500, new LUIUtil.DelayCallback() {
                @Override
                public void doAfter(int mls) {
                    removeCoverVideo();
                }
            });
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
        return "TAGUizaPlayerActivity";
    }

    @Override
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.uiza_player_activity;
    }

    //true: show status bar, hide navigation bar
    //false: hide status bar, hide navigation bar
    public void updateUIStatusNavigationBar(boolean isShow) {
        UizaScreenUtil.hideNavBar(getWindow().getDecorView());
        if (isShow) {
            UizaScreenUtil.showStatusBar(activity);
        } else {
            UizaScreenUtil.hideStatusBar(activity);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LLog.d(TAG, "onConfigurationChanged ORIENTATION_LANDSCAPE");
            updateUIStatusNavigationBar(false);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LLog.d(TAG, "onConfigurationChanged ORIENTATION_PORTRAIT");
            updateUIStatusNavigationBar(true);
        }
    }

    /*@Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        orientVideoDescriptionFragment(configuration.orientation);
    }

    private void orientVideoDescriptionFragment(int orientation) {
        //LLog.d(TAG, "orientVideoDescriptionFragment");
        //Hide the extra content when in landscape so the video is as large as possible.
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment frmInfoVideo = fragmentManager.findFragmentById(R.id.uiza_video_info);
        if (frmInfoVideo != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentTransaction.hide(frmInfoVideo);
                LUIUtil.setDelay(300, new LUIUtil.DelayCallback() {
                    @Override
                    public void doAfter(int mls) {
                        UizaScreenUtil.hideNavBar(getWindow().getDecorView());
                    }
                });
            } else {
                fragmentTransaction.show(frmInfoVideo);
            }
            fragmentTransaction.commit();
        }
        Fragment frmUizaVideo = fragmentManager.findFragmentById(R.id.uiza_video);
        if (frmUizaVideo != null) {
            if (frmUizaVideo instanceof FrmUizaVideo) {
                //LLog.d(TAG, "UizaData.getInstance().getCurrentPosition() " + UizaData.getInstance().getCurrentPosition());
                ((FrmUizaVideo) frmUizaVideo).seekTo(UizaData.getInstance().getCurrentPosition());
            }
        }
    }*/

    @Override
    public void onBackPressed() {
        UizaData.getInstance().reset();
        super.onBackPressed();
    }

    private void getLinkPlay() {
        //LLog.d(TAG, ">>>getLinkPlay entityId: " + inputModel.getEntityID());
        UizaService service = RestClient.createService(UizaService.class);
        Auth auth = LPref.getAuth(activity, gson);
        if (auth == null || auth.getAppId() == null) {
            showDialogError("Error auth == null || auth.getAppId() == null");
            return;
        }
        //LLog.d(TAG, ">>>getLinkPlay appId: " + auth.getAppId());

        //API v1
        /*subscribe(service.getLinkPlay(inputModel.getEntityID(), auth.getAppId()), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(GetLinkPlay getLinkPlay) {
                LLog.d(TAG, "getLinkPlay onSuccess " + gson.toJson(getLinkPlay));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                //UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");

                //TODO play link mpd0 (pre cdn la vn) ko dc thi play link mpd1(cdn la QT)
                LLog.d(TAG, "getLinkplayMpd " + getLinkPlay.getLinkplayMpd());
                UizaData.getInstance().setLinkPlay(getLinkPlay.getLinkplayMpd());
                isGetLinkPlayDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.d(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });*/
        //End API v1

        //API v2
        subscribe(service.getLinkPlayV2(inputModel.getEntityID(), auth.getAppId()), new ApiSubscriber<vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay>() {
            @Override
            public void onSuccess(vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay getLinkPlay) {
                //LLog.d(TAG, "getLinkPlay onSuccess " + gson.toJson(getLinkPlay));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                //UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");

                try {
                    //Mpd mpdVN = getLinkPlay.getMpd().get(0);
                    Mpd mpdInter = getLinkPlay.getMpd().get(1);

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
                //LLog.d(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });
        //End API v2
    }

    private void getDetailEntity() {
        //LLog.d(TAG, ">>>getDetailEntity");
        if (inputModel == null) {
            //LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        //API v1
        /*UizaService service = RestClient.createService(UizaService.class);
        String entityId = inputModel.getEntityID();
        LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntity(entityId), new ApiSubscriber<Object>() {
            @Override
            public void onSuccess(Object getDetailEntity) {
                //TODO
                LLog.d(TAG, "getDetailEntity onSuccess " + gson.toJson(getDetailEntity));
                *//*if (getDetailEntity != null) {
                    UizaData.getInstance().setDetailEntity(getDetailEntity);
                } else {
                    showDialogError("Error: getDetailEntity onSuccess detailEntity == null");
                }*//*
                isGetDetailEntityDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });*/
        //End API v1

        //API v2
        UizaService service = RestClient.createService(UizaService.class);
        String entityId = inputModel.getEntityID();
        //LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntityV2(entityId), new ApiSubscriber<GetDetailEntity>() {
            @Override
            public void onSuccess(GetDetailEntity getDetailEntity) {
                //LLog.d(TAG, "getDetailEntityV2 onSuccess " + gson.toJson(getDetailEntity));
                if (getDetailEntity != null) {
                    UizaData.getInstance().setDetailEntity(getDetailEntity);
                } else {
                    showDialogError("Error: getDetailEntity onSuccess detailEntity == null");
                }
                isGetDetailEntityDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                //LLog.e(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });
        //EndAPI v2
    }

    /*private void getEntityInfo() {
        LLog.d(TAG, ">>>getEntityInfo");
        if (inputModel == null) {
            LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        UizaService service = RestClient.createService(UizaService.class);
        String id = inputModel.getEntityID();
        //LLog.d(TAG, "getEntityInfo id " + id);
        subscribe(service.getEntityInfo(id), new ApiSubscriber<EntityInfo>() {
            @Override
            public void onSuccess(EntityInfo entityInfo) {
                LLog.d(TAG, "getEntityInfo onSuccess " + gson.toJson(entityInfo));
                if (entityInfo != null) {
                    UizaData.getInstance().setEntityInfo(entityInfo);
                } else {
                    handleException("getEntityInfo onSuccess entityInfo == null");
                }
                isGetDetailEntityDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }*/

    private void getPlayerConfig() {
        //LLog.d(TAG, ">>>getPlayerConfig");
        if (inputModel == null) {
            //LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        setCoverVideo();
        UizaService service = RestClient.createService(UizaService.class);
        subscribe(service.getPlayerInfo(UizaData.getInstance().getPlayerId()), new vn.loitp.rxandroid.ApiSubscriber<PlayerConfig>() {
            @Override
            public void onSuccess(PlayerConfig playerConfig) {
                //TODO custom theme
                //LLog.d(TAG, "getPlayerConfig onSuccess " + gson.toJson(playerConfig));
                UizaData.getInstance().setPlayerConfig(playerConfig);

                getLinkPlay();
                getDetailEntity();
                //getEntityInfo();
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }
}
