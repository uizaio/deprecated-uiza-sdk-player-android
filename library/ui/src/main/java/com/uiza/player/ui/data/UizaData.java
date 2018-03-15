package com.uiza.player.ui.data;

import android.os.Handler;

import com.uiza.player.ui.views.helper.InputModel;
import com.uiza.player.ui.views.view.language.LanguageObject;
import com.uiza.player.ui.views.view.settingview.SettingObject;

import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;

/**
 * Created by www.muathu@gmail.com on 11/5/2017.
 */

//public class UizaData implements UizaSubject {
public class UizaData {
    private final String TAG = getClass().getSimpleName();
    /*private ArrayList<UizaRepositoryObserver> mObservers;

    @Override
    public void registerObserver(UizaRepositoryObserver uizaRepositoryObserver) {
        if (!mObservers.contains(uizaRepositoryObserver)) {
            mObservers.add(uizaRepositoryObserver);
        }
    }

    @Override
    public void removeObserver(UizaRepositoryObserver uizaRepositoryObserver) {
        if (mObservers.contains(uizaRepositoryObserver)) {
            mObservers.remove(uizaRepositoryObserver);
        }
    }

    @Override
    public void notifyObservers() {
        for (UizaRepositoryObserver observer : mObservers) {
            observer.onInputModelChange(inputModel);
        }
    }*/

    private static final UizaData ourInstance = new UizaData();

    public static UizaData getInstance() {
        return ourInstance;
    }

    private UizaData() {
        //mObservers = new ArrayList<>();
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
                settingObject = null;
                languageObject = null;
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

    private SettingObject settingObject;

    public SettingObject getSettingObject() {
        return settingObject;
    }

    public void setSettingObject(SettingObject settingObject) {
        this.settingObject = settingObject;
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
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        return inputModel;
    }

    public void setInputModel(InputModel inputModel) {
        this.inputModel = inputModel;
        //notifyObservers();
    }

    public void setLinkPlay(String linkPlay) {
        if (inputModel == null) {
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        inputModel.setUri(linkPlay);
        //notifyObservers();
    }

    private PlayerConfig playerConfig;

    public PlayerConfig getPlayerConfig() {
        /*if (playerConfig == null) {
            throw new NullPointerException("Error playerConfig null");
        }*/
        return playerConfig;
    }

    public void setPlayerConfig(PlayerConfig playerConfig) {
        this.playerConfig = playerConfig;
    }

    public void setDetailEntity(GetDetailEntity getDetailEntity) {
        if (inputModel == null) {
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        inputModel.setDetailEntity(getDetailEntity);
        //notifyObservers();
    }

    /*public void setEntityInfo(EntityInfo entityInfo) {
        if (inputModel == null) {
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        inputModel.setEntityInfo(entityInfo);
        //notifyObservers();
    }*/

    public final static String PLAYER_ID_SKIN_1 = "b825c07e-2ed2-48a8-a8ee-012baf8614e8";
    public final static String PLAYER_ID_SKIN_2 = "29c2bb31-e1f9-4992-ac89-e7d2759a2d6b";
    public final static String PLAYER_ID_SKIN_3 = "d1a9eca0-8bef-4985-8260-af3300191200";
    public final static String T = "true";
    public final static String F = "false";

    private int versionSDK = Constants.VS_SDK_1;
    private String apiEndPoint;
    private String apiTrackingEndPoint;
    private String token;
    private String playerId;//for select theme

    public int getVersionSDK() {
        return versionSDK;
    }

    public void setVersionSDK(int versionSDK) {
        this.versionSDK = versionSDK;
    }

    public String getApiEndPoint() {
        if (apiEndPoint == null || apiEndPoint.isEmpty()) {
            throw new NullPointerException("Pls init apiEndPoint first");
        }
        return apiEndPoint;
    }

    public String getApiTrackingEndPoint() {
        if (apiTrackingEndPoint == null || apiTrackingEndPoint.isEmpty()) {
            throw new NullPointerException("Pls init apiTrackingEndPoint first");
        }
        return apiTrackingEndPoint;
    }

    public String getToken() {
        if (token == null || token.isEmpty()) {
            throw new NullPointerException("Pls init token first");
        }
        return token;
    }

    public String getPlayerId() {
        if (playerId == null || playerId.isEmpty()) {
            throw new NullPointerException("Pls init playerId first");
        }
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void init(int versionSDK, String apiEndPoint, String apiTrackingEndPoint, String token, String playerId) {
        this.versionSDK = versionSDK;
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
        this.sizeHeightOfSimpleExoPlayerView = sizeHeightOfSimpleExoPlayerView;
    }
}
