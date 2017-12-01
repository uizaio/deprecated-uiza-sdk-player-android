package com.google.android.exoplayer2.ui.fragment.view.settingview;

/**
 * Created by www.muathu@gmail.com on 11/7/2017.
 */

public class SettingObject {
    public static final String AUTO = "Auto";
    public static final String T560 = "560p";
    public static final String T720 = "720p";
    public static final String T1080 = "1080p";
    private String description;
    private boolean isCheck;

    public SettingObject(String description, boolean isCheck) {
        this.description = description;
        this.isCheck = isCheck;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}