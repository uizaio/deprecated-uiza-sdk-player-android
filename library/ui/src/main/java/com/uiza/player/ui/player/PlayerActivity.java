package com.uiza.player.ui.player;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.Gson;
import com.uiza.player.core.uiza.api.model.getdetailentity.DetailEntity;
import com.uiza.player.core.uiza.api.model.getlinkplay.GetLinkPlay;
import com.uiza.player.core.uiza.api.model.getplayerinfo.PlayerConfig;
import com.uiza.player.core.uiza.api.service.UizaService;
import com.uiza.player.rxandroid.ApiSubscriber;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.utils.util.ToastUtils;

public class PlayerActivity extends BaseActivity {
    private InputModel inputModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UizaScreenUtil.hideNavBar(getWindow().getDecorView());
        RestClient.init(UizaData.getInstance().getApiEndPoint(), UizaData.getInstance().getToken());
        inputModel = (InputModel) getIntent().getSerializableExtra(Constants.KEY_UIZA_PLAYER);
        if (inputModel == null) {
            ToastUtils.showShort("Error inputModel == null");
            return;
        }
        orientVideoDescriptionFragment(getResources().getConfiguration().orientation);

        UizaData.getInstance().setInputModel(inputModel);

        getPlayerConfig();
        getLinkPlay();
        getDetailEntity();
        //getEntityInfo();
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

    @Override
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
                /*LUIUtil.setDelay(300, new LUIUtil.DelayCallback() {
                    @Override
                    public void doAfter(int mls) {
                        UizaScreenUtil.hideNavBar(getWindow().getDecorView());
                    }
                });*/
            } else {
                fragmentTransaction.show(frmInfoVideo);
            }
            fragmentTransaction.commit();
        }
        /*Fragment frmUizaVideo = fragmentManager.findFragmentById(R.id.uiza_video);
        if (frmUizaVideo != null) {
            if (frmUizaVideo instanceof FrmUizaVideo) {
                //LLog.d(TAG, "UizaData.getInstance().getCurrentPosition() " + UizaData.getInstance().getCurrentPosition());
                ((FrmUizaVideo) frmUizaVideo).seekTo(UizaData.getInstance().getCurrentPosition());
            }
        }*/
    }

    @Override
    public void onBackPressed() {
        UizaData.getInstance().reset();
        super.onBackPressed();
    }

    private void getLinkPlay() {
        UizaService service = RestClient.createService(UizaService.class);
        subscribe(service.getLinkPlay(inputModel.getEntityID()), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(GetLinkPlay getLinkPlay) {
                //Gson gson = new Gson();
                //LLog.d(TAG, "getLinkPlay onSuccess " + gson.toJson(getLinkPlay));
                //UizaData.getInstance().setLinkPlay("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");
                UizaData.getInstance().setLinkPlay(getLinkPlay.getLinkplayMpd());
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }

    private void getDetailEntity() {
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
            handleException("Exception " + e.toString());
            return;
        }
        LLog.d(TAG, "entity " + entity);
        subscribe(service.getDetailEntity(entity), new vn.loitp.rxandroid.ApiSubscriber<DetailEntity>() {
            @Override
            public void onSuccess(DetailEntity detailEntity) {
                //Gson gson = new Gson();
                //LLog.d(TAG, "getDetailEntity onSuccess " + gson.toJson(detailEntity));
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
                handleException(e);
            }
        });
    }

    private void getEntityInfo() {
        LLog.d(TAG, "getEntityInfo");
        if (inputModel == null) {
            LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        UizaService service = RestClient.createService(UizaService.class);
        //String id = inputModel.getEntityID();
        String id = "81";
        subscribe(service.getEntityInfo(id), new vn.loitp.rxandroid.ApiSubscriber<Object>() {
            @Override
            public void onSuccess(Object o) {
                Gson gson = new Gson();
                LLog.d(TAG, "getEntityInfo onSuccess " + gson.toJson(o));
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }

    private void getPlayerConfig() {
        LLog.d(TAG, "getPlayerConfig");
        if (inputModel == null) {
            LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        UizaService service = RestClient.createService(UizaService.class);
        //String id = inputModel.getEntityID();
        String id = "108";
        subscribe(service.getPlayerInfo(id), new vn.loitp.rxandroid.ApiSubscriber<PlayerConfig>() {
            @Override
            public void onSuccess(PlayerConfig playerConfig) {
                Gson gson = new Gson();
                LLog.d(TAG, "getEntityInfo onSuccess " + gson.toJson(playerConfig));
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }
}
