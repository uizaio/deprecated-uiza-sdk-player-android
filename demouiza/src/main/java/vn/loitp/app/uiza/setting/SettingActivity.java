package vn.loitp.app.uiza.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.uiza.home.HomeActivity;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean setFullScreen() {
        return false;
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
        return R.layout.uiza_setting_activity;
    }
}
