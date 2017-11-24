package com.google.android.exoplayer2.ui.data;

import android.os.Handler;

import com.google.android.exoplayer2.ui.fragment.helper.InputModel;
import com.google.android.exoplayer2.ui.language.LanguageObject;
import com.google.android.exoplayer2.ui.settingview.SettingObject;

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
        if (mCallbackInputModelChange != null) {
            mCallbackInputModelChange.onInputModelChange(this.inputModel);
            notifyObservers();
        }
    }

    public interface CallbackInputModelChange {
        public void onInputModelChange(InputModel inputModel);
    }

    private CallbackInputModelChange mCallbackInputModelChange;

    public void setCallbackInputModelChange(CallbackInputModelChange callbackInputModelChange) {
        mCallbackInputModelChange = callbackInputModelChange;
    }

    public String getInputModelTitle() {
        if (inputModel != null && inputModel.getTitle() != null) {
            return inputModel.getTitle();
        }
        return "-";
    }
}
