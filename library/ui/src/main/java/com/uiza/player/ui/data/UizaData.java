package com.uiza.player.ui.data;

import android.os.Handler;

import com.uiza.player.core.uiza.api.model.getdetailentity.DetailEntity;
import com.uiza.player.ui.views.helper.InputModel;
import com.uiza.player.ui.views.view.language.LanguageObject;
import com.uiza.player.ui.views.view.settingview.SettingObject;

import java.util.ArrayList;

/**
 * Created by www.muathu@gmail.com on 11/5/2017.
 */

public class UizaData implements UizaSubject {
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

    public void setDetailEntity(DetailEntity detailEntity) {
        if (inputModel == null) {
            throw new NullPointerException("inputModel cannot be null, pls init it first");
        }
        inputModel.setDetailEntity(detailEntity);
        notifyObservers();
    }

    public final static int SKIN_1 = 1;
    public final static int SKIN_2 = 2;
    public final static int SKIN_3 = 3;
    private int mSkinNo = SKIN_1;

    public int getSkinNo() {
        return mSkinNo;
    }

    public void setSkinNo(int mSkinNo) {
        this.mSkinNo = mSkinNo;
    }

    private String apiEndPoint;
    private String token;

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

    public void init(String apiEndPoint, String token) {
        this.apiEndPoint = apiEndPoint;
        this.token = token;
    }
}
