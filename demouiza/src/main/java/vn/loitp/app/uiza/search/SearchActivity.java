package vn.loitp.app.uiza.search;

import android.app.Activity;
import android.os.Bundle;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.uiza.R;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean setFullScreen() {
        return true;
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
        return R.layout.uiza_search_activity;
    }
}
