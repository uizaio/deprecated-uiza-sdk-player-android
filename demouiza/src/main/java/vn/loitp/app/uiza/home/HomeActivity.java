package vn.loitp.app.uiza.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.PlaceHolderView;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

public class HomeActivity extends BaseActivity {
    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView) findViewById(R.id.drawerView);

        LUIUtil.setPullLikeIOSVertical(mDrawerView);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setupDrawer();
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
        return R.layout.uiza_home_activity;
    }

    private void setupDrawer() {
        mDrawerView.addView(new UizaDrawerHeader());

        List<String> menuList = new ArrayList<>();
        menuList.add("Home");
        menuList.add("Action");
        menuList.add("Drama");
        menuList.add("Hornor");
        menuList.add("Kids");

        for (int i = 0; i < menuList.size(); i++) {
            mDrawerView.addView(new UizaDrawerMenuItem(this.getApplicationContext(), menuList, i, new UizaDrawerMenuItem.Callback() {
                @Override
                public void onMenuItemClick(int pos) {
                    HomeData.getInstance().setCurrentPosition(pos);
                    mDrawer.closeDrawers();
                }
            }));
        }
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                /*if (mDrawerView != null) {
                    mDrawerView.refresh();
                }*/
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mDrawerView != null) {
                    mDrawerView.refresh();
                }
            }
        };
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
}
