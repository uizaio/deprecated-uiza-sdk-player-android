package com.uiza.player.ui.data;

import android.os.Handler;

import com.uiza.player.ui.views.helper.InputModel;
import com.uiza.player.ui.views.view.language.LanguageObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.restapi.uiza.model.v2.listallentityrelation.ListAllEntityRelation;

/**
 * Created by www.muathu@gmail.com on 11/5/2017.
 */

//public class UizaData implements UizaSubject {
public class UizaData {
    private final String TAG = getClass().getSimpleName();

    private static final UizaData ourInstance = new UizaData();

    public static UizaData getInstance() {
        return ourInstance;
    }

    //current position of player
    private long currentPosition;

    public long getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(long currentPosition) {
        this.currentPosition = currentPosition;
    }

    //reset all
    public void reset() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPosition = 0;
                isLandscape = false;
                languageObject = null;
                sizeHeightOfSimpleExoPlayerView = 0;
                playerConfig = null;
            }
        }, 100);
    }

    //detect screen oritation isLandscape or not
    private boolean isLandscape;

    public boolean isLandscape() {
        return isLandscape;
    }

    public void setLandscape(boolean landscape) {
        isLandscape = landscape;
    }

    private LanguageObject languageObject;

    public LanguageObject getLanguageObject() {
        return languageObject;
    }

    public void setLanguageObject(LanguageObject languageObject) {
        this.languageObject = languageObject;
    }

    private InputModel inputModel;

    public InputModel getInputModel() {
        if (inputModel == null) {
            if (Constants.IS_DEBUG) {
                throw new NullPointerException("getInputModel zinputModel cannot be null, pls init it first");
            } else {
                return null;
            }
        }
        return inputModel;
    }

    public void setInputModel(InputModel inputModel) {
        LLog.d(TAG, "setInputModel getEntityID: " + inputModel.getEntityID());
        this.inputModel = inputModel;
    }

    public void setLinkPlay(List<String> listLinkPlay) {
        if (inputModel == null) {
            if (Constants.IS_DEBUG) {
                throw new NullPointerException("setLinkPlay inputModel cannot be null, pls init it first");
            } else {
                return;
            }
        }
        inputModel.setListLinkPlay(listLinkPlay);
        if (Constants.IS_DEBUG) {
            for (String linkPlay : listLinkPlay) {
                LLog.d(TAG, "setLinkPlay linkPlay: " + linkPlay);
            }
        }
    }

    private PlayerConfig playerConfig;

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public void setPlayerConfig(PlayerConfig playerConfig) {
        this.playerConfig = playerConfig;
    }

    public void setDetailEntityV1(vn.loitp.restapi.uiza.model.v1.getdetailentity.GetDetailEntity getDetailEntity) {
        if (inputModel == null) {
            if (Constants.IS_DEBUG) {
                throw new NullPointerException("setDetailEntityV1 inputModel cannot be null, pls init it first");
            } else {
                return;
            }
        }
        inputModel.setDetailEntityV1(getDetailEntity);
        //notifyObservers();
    }

    public void setDetailEntityV2(GetDetailEntity getDetailEntity) {
        if (inputModel == null) {
            if (Constants.IS_DEBUG) {
                throw new NullPointerException("setDetailEntityV2 inputModel cannot be null, pls init it first");
            } else {
                return;
            }
        }
        inputModel.setDetailEntityV2(getDetailEntity);
    }

    private boolean isVideoCanSlide;
    private String apiEndPoint;
    private String apiTrackingEndPoint;
    private String token;
    private String playerId;//for select theme

    public boolean isVideoCanSlide() {
        return isVideoCanSlide;
    }

    public void setVideoCanSlide(boolean videoCanSlide) {
        isVideoCanSlide = videoCanSlide;
    }

    public String getApiEndPoint() {
        if (apiEndPoint == null || apiEndPoint.isEmpty()) {
            throw new NullPointerException("Please init apiEndPoint first");
        }
        return apiEndPoint;
    }

    public String getApiTrackingEndPoint() {
        if (apiTrackingEndPoint == null || apiTrackingEndPoint.isEmpty()) {
            throw new NullPointerException("Please init apiTrackingEndPoint first");
        }
        return apiTrackingEndPoint;
    }

    public String getToken() {
        if (token == null || token.isEmpty()) {
            throw new NullPointerException("Please init token first");
        }
        return token;
    }

    public String getPlayerId() {
        if (playerId == null || playerId.isEmpty()) {
            throw new NullPointerException("Please init playerId first");
        }
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void init(String apiEndPoint, String apiTrackingEndPoint, String token, String playerId) {
        this.apiEndPoint = apiEndPoint;
        this.apiTrackingEndPoint = apiTrackingEndPoint;
        this.token = token;
        this.playerId = playerId;

        LLog.d(TAG, "apiEndPoint " + apiEndPoint);
        LLog.d(TAG, "apiTrackingEndPoint " + apiTrackingEndPoint);
        LLog.d(TAG, "token " + token);
        LLog.d(TAG, "playerId " + playerId);
    }

    //size height cua SimpleExoPlayerView se duoc update khi fullscreen hoac tat fullscreen
    private int sizeHeightOfSimpleExoPlayerView;

    public int getSizeHeightOfSimpleExoPlayerView() {
        return sizeHeightOfSimpleExoPlayerView;
    }

    public void setSizeHeightOfSimpleExoPlayerView(int sizeHeightOfSimpleExoPlayerView) {
        LLog.d(TAG, "-----updateUIStatusNavigationBar setSizeHeightOfSimpleExoPlayerView " + sizeHeightOfSimpleExoPlayerView);
        this.sizeHeightOfSimpleExoPlayerView = sizeHeightOfSimpleExoPlayerView;
    }

    //for list entity relation (only support api v2)
    private Map<String, ListAllEntityRelation> mapListAllEntityRelation = new HashMap<String, ListAllEntityRelation>();

    public void putToListAllEntityRelation(String entityId, ListAllEntityRelation listAllEntityRelation) {
        LLog.d(TAG, "putToListAllEntityRelation entityId: " + entityId + ", listAllEntityRelation size: " + listAllEntityRelation.getItemList().size());
        mapListAllEntityRelation.put(entityId, listAllEntityRelation);
    }

    public ListAllEntityRelation getListAllEntityRelation(String entityId) {
        ListAllEntityRelation listAllEntityRelation = mapListAllEntityRelation.get(entityId);
        if (listAllEntityRelation == null) {
            LLog.d(TAG, "listAllEntityRelation == null -> null");
            return null;
        }
        LLog.d(TAG, "getListAllEntityRelation entityId: " + entityId + ", listAllEntityRelation size: " + listAllEntityRelation.getItemList().size());
        return listAllEntityRelation;
    }

    public void removeListAllEntityRelation(String entityId) {
        mapListAllEntityRelation.remove(entityId);
    }
    //end for list entity relation (only support api v2)
}
