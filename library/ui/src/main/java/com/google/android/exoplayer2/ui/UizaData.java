package com.google.android.exoplayer2.ui;

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
        currentPosition = 0;
    }
}
