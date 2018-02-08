package vn.loitp.app.uiza.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.app.uiza.home.view.UizaDrawerHeader;
import vn.loitp.app.uiza.home.view.UizaDrawerMenuItem;
import vn.loitp.app.uiza.login.LoginActivity;
import vn.loitp.app.uiza.service.UizaWTTService;
import vn.loitp.app.uiza.setting.SettingActivity;
import vn.loitp.app.uiza.view.UizaActionBar;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.utils.util.ToastUtils;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;

public class HomeActivity extends BaseActivity {
    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawerLayout;
    private List<String> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView) findViewById(R.id.drawerView);

        LUIUtil.setPullLikeIOSVertical(mDrawerView);

        setupDrawer();
        setupActionBar();
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
        return R.layout.uiza_home_activity;
    }

    private void setupDrawer() {
        UizaDrawerHeader uizaDrawerHeader = new UizaDrawerHeader();
        uizaDrawerHeader.setCallback(new UizaDrawerHeader.Callback() {
            @Override
            public void onClickLogOut() {
                ToastUtils.showShort("Click");
            }

            @Override
            public void onClickLogin() {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawers();
                }
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }

            @Override
            public void onClickSetting() {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawers();
                }
                Intent intent = new Intent(activity, SettingActivity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }
        });
        mDrawerView.addView(uizaDrawerHeader);

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
                    HomeData.getInstance().setData(menuList.get(pos));
                    mDrawerLayout.closeDrawers();
                    replaceFragment(new FrmChannel());
                }
            }));
        }

        //init data first
        HomeData.getInstance().setData(menuList.get(0));
        replaceFragment(new FrmChannel());

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //do nothing
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                updateUIDrawer();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                updateUIDrawer();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //do nothing
            }
        });

        getData();
    }

    private void setupActionBar() {
        UizaActionBar uizaActionBar = (UizaActionBar) findViewById(R.id.uiza_action_bar);
        uizaActionBar.hideTvTitle();
        uizaActionBar.setOnClickBack(new UizaActionBar.Callback() {
            @Override
            public void onClickLeft() {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    updateUIDrawer();
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }

            @Override
            public void onClickRight() {
                ToastUtils.showShort("Click");
            }
        });
        uizaActionBar.showMenuIcon();
        uizaActionBar.setImageRightIcon(R.drawable.ic_search_black_48dp);
        uizaActionBar.setImageLeftIcon(R.drawable.ic_menu_black_48dp);
        uizaActionBar.setTvTitle("Logo");
    }

    private void updateUIDrawer() {
        if (mDrawerView != null) {
            mDrawerView.refresh();
        }
    }

    public void replaceFragment(Fragment fragment) {
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();*/
    }

    private void getData() {
        LLog.d(TAG, "getData");
        UizaWTTService service = RestClient.createService(UizaWTTService.class);
        subscribe(service.getListAllMetadata(100), new ApiSubscriber<Object>() {
            @Override
            public void onSuccess(Object getAll) {
                LLog.d(TAG, "getData onSuccess " + LSApplication.getInstance().getGson().toJson(getAll));
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail " + e.getMessage());
                handleException(e);
            }
        });
    }
}
