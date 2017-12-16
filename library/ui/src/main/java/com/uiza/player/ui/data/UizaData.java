package com.uiza.player.ui.data;

import android.os.Handler;

import com.uiza.player.core.uiza.api.model.getentityinfo.EntityInfo;
import com.uiza.player.core.uiza.api.model.getplayerinfo.PlayerConfig;
import com.uiza.player.ui.views.helper.InputModel;
import com.uiza.player.ui.views.view.language.LanguageObject;
import com.uiza.player.ui.views.view.settingview.SettingObject;

import java.util.ArrayList;

import vn.loitp.core.utilities.LLog;

/**
 * Created by www.muathu@gmail.com on 11/5/2017.
 */

public class UizaData implements UizaSubject {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<UizaRepositoryObserver> mObservers;

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
    }

    private static final UizaData ourInstance = new UizaData();

    public static UizaData getInstance() {
        return ourInstance;
    }

    private UizaData() {
        mObservers = new ArrayList<>();
    }

    private long currentPosition;

    public long getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(long currentPosition) {
        this.currentPosition = currentPosition;
    }

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
        notifyObservers();
    }

    public void setLinkPlay(String linkPlay) {
        if (inputModel == null) {
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        inputModel.setUri(linkPlay);
        notifyObservers();
    }

    private PlayerConfig playerConfig;

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public void setPlayerConfig(PlayerConfig playerConfig) {
        this.playerConfig = playerConfig;
    }

    /*public void setDetailEntity(DetailEntity detailEntity) {
        if (inputModel == null) {
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        inputModel.setDetailEntity(detailEntity);
        notifyObservers();
    }*/

    public void setEntityInfo(EntityInfo entityInfo) {
        if (inputModel == null) {
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        inputModel.setEntityInfo(entityInfo);
        notifyObservers();
    }

    public final static String PLAYER_ID_SKIN_1 = "b825c07e-2ed2-48a8-a8ee-012baf8614e8";
    public final static String PLAYER_ID_SKIN_2 = "29c2bb31-e1f9-4992-ac89-e7d2759a2d6b";
    public final static String PLAYER_ID_SKIN_3 = "d1a9eca0-8bef-4985-8260-af3300191200";

    private String apiEndPoint;
    private String token;
    private String playerId;//for select theme

    public String getApiEndPoint() {
        if (apiEndPoint == null || apiEndPoint.isEmpty()) {
            throw new NullPointerException("Pls init apiEndPoint first");
        }
        return apiEndPoint;
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

    public void init(String apiEndPoint, String token, String playerId) {
        this.apiEndPoint = apiEndPoint;
        this.token = token;
        this.playerId = playerId;

        LLog.d(TAG, "apiEndPoint " + apiEndPoint);
        LLog.d(TAG, "token " + token);
        LLog.d(TAG, "playerId " + playerId);
    }
}
