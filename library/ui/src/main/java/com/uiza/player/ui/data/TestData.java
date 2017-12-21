package com.uiza.player.ui.data;

import com.google.android.exoplayer2.trackselection.MappingTrackSelector;

/**
 * Created by www.muathu@gmail.com on 12/21/2017.
 */

public class TestData {
    private static final TestData ourInstance = new TestData();

    public static TestData getInstance() {
        return ourInstance;
    }

    private TestData() {
    }

    private MappingTrackSelector.MappedTrackInfo trackInfo;

    public MappingTrackSelector.MappedTrackInfo getTrackInfo() {
        return trackInfo;
    }

    public void setTrackInfo(MappingTrackSelector.MappedTrackInfo trackInfo) {
        this.trackInfo = trackInfo;
    }
}
