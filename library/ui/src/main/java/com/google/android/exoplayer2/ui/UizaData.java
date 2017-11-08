package com.google.android.exoplayer2.ui;

import android.os.Handler;

import com.google.android.exoplayer2.ui.settingview.SettingObject;

/**
 * Created by www.muathu@gmail.com on 11/5/2017.
 */

public class UizaData {
    private static final UizaData ourInstance = new UizaData();

    public static UizaData getInstance() {
        return ourInstance;
    }

    private UizaData() {
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
}
