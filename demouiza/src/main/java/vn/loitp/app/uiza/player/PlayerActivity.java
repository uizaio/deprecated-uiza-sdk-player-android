package vn.loitp.app.uiza.player;

import android.app.Activity;
import android.os.Bundle;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.common.Constants;
import vn.loitp.app.uiza.home.model.VideoObject;
import vn.loitp.livestar.R;

public class PlayerActivity extends BaseActivity {
    private VideoObject videoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoObject = (VideoObject) getIntent().getSerializableExtra(Constants.KEY_UIZA_PLAYER);
        if (videoObject == null) {
            ToastUtils.showShort("Error videoObject == null");
            return;
        }
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
        return R.layout.uiza_player_activity;
    }
}
