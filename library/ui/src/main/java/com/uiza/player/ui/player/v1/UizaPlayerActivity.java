package com.uiza.player.ui.player.v1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.uiza.player.core.uiza.api.model.getentityinfo.EntityInfo;
import com.uiza.player.core.uiza.api.model.getlinkplay.GetLinkPlay;
import com.uiza.player.core.uiza.api.model.getplayerinfo.PlayerConfig;
import com.uiza.player.core.uiza.api.service.UizaService;
import com.uiza.player.rxandroid.ApiSubscriber;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.util.UizaAnimationUtil;
import com.uiza.player.ui.util.UizaImageUtil;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.utils.util.ToastUtils;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

public class UizaPlayerActivity extends BaseActivity {
    private InputModel inputModel;
    //TODO remove gson later
    private Gson gson = new Gson();

    private boolean isGetLinkPlayDone;
    private boolean isgetEntityInfoDone;

    private FrameLayout flRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flRootView = (FrameLayout) findViewById(R.id.fl_root_view);

        UizaScreenUtil.hideNavBar(getWindow().getDecorView());
        RestClient.init(UizaData.getInstance().getApiEndPoint(), UizaData.getInstance().getToken());
        inputModel = (InputModel) getIntent().getSerializableExtra(Constants.KEY_UIZA_PLAYER);
        if (inputModel == null) {
            ToastUtils.showShort("Error inputModel == null");
            return;
        }
        //orientVideoDescriptionFragment(getResources().getConfiguration().orientation);

        UizaData.getInstance().setInputModel(inputModel);

        getPlayerConfig();
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
            //Log.d(TAG, "setCoverVideo " + inputModel.getUrlImg());
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
        if (isGetLinkPlayDone && isgetEntityInfoDone) {
            initContainerVideo();
            initContainerVideoInfo();
            removeCoverVideo();
        } else {
            LLog.d(TAG, "init failed: isGetLinkPlayDone: " + isGetLinkPlayDone + ", isgetEntityInfoDone: " + isgetEntityInfoDone);
        }
    }

    @Override
    protected boolean setFullScreen() {
        return true;
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
        return R.layout.uiza_player_activity;
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
        LLog.d(TAG, "getLinkPlay " + inputModel.getEntityID());
        UizaService service = RestClient.createService(UizaService.class);
        subscribe(service.getLinkPlay(inputModel.getEntityID()), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(GetLinkPlay getLinkPlay) {
                LLog.d(TAG, "getLinkPlay onSuccess " + gson.toJson(getLinkPlay));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");
                //TODO revert
                //UizaData.getInstance().setLinkPlay(getLinkPlay.getLinkplayMpd());
                isGetLinkPlayDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
                //TODO remove this code
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");
                isGetLinkPlayDone = true;
                init();
            }
        });
    }

    /*private void getDetailEntity() {
        LLog.d(TAG, "getDetailEntity");
        if (inputModel == null) {
            LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        UizaService service = RestClient.createService(UizaService.class);
        int entity;
        try {
            entity = Integer.parseInt(inputModel.getEntityID());
        } catch (Exception e) {
            LLog.d(TAG, "getDetailEntity Exception " + e.toString());
            handleException("Exception " + e.toString());
            return;
        }
        subscribe(service.getDetailEntity(entity), new vn.loitp.rxandroid.ApiSubscriber<DetailEntity>() {
            @Override
            public void onSuccess(DetailEntity detailEntity) {
                LLog.d(TAG, "getDetailEntity onSuccess " + gson.toJson(detailEntity));
                if (detailEntity != null) {
                    //mItem = detailEntity.getItem().get(0);
                    //updateUI();
                    UizaData.getInstance().setDetailEntity(detailEntity);
                } else {
                    handleException("getDetailEntity onSuccess detailEntity == null");
                }
            }

            @Override
            public void onFail(Throwable e) {
                LLog.d(TAG, "getDetailEntity onFail " + e.toString());
                handleException(e);
            }
        });
    }*/

    private void getEntityInfo() {
        //LLog.d(TAG, "getEntityInfo");
        if (inputModel == null) {
            LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        UizaService service = RestClient.createService(UizaService.class);
        String id = inputModel.getEntityID();
        //LLog.d(TAG, "getEntityInfo id " + id);
        subscribe(service.getEntityInfo(id), new vn.loitp.rxandroid.ApiSubscriber<EntityInfo>() {
            @Override
            public void onSuccess(EntityInfo entityInfo) {
                LLog.d(TAG, "getEntityInfo onSuccess " + gson.toJson(entityInfo));
                if (entityInfo != null) {
                    UizaData.getInstance().setEntityInfo(entityInfo);
                } else {
                    handleException("getEntityInfo onSuccess entityInfo == null");
                }
                isgetEntityInfoDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }

    private void getPlayerConfig() {
        //LLog.d(TAG, "getPlayerConfig");
        if (inputModel == null) {
            LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        setCoverVideo();
        UizaService service = RestClient.createService(UizaService.class);
        subscribe(service.getPlayerInfo(UizaData.getInstance().getPlayerId()), new vn.loitp.rxandroid.ApiSubscriber<PlayerConfig>() {
            @Override
            public void onSuccess(PlayerConfig playerConfig) {
                LLog.d(TAG, "getPlayerConfig getEntityInfo onSuccess " + gson.toJson(playerConfig));

                UizaData.getInstance().setPlayerConfig(playerConfig);

                getLinkPlay();
                //getDetailEntity();
                getEntityInfo();
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }
}
