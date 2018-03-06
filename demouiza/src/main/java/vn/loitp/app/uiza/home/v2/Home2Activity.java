package vn.loitp.app.uiza.home.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.app.uiza.home.view.UizaDrawerHeader;
import vn.loitp.app.uiza.home.view.UizaDrawerMenuItem;
import vn.loitp.app.uiza.login.LoginActivity;
import vn.loitp.app.uiza.search.SearchActivity;
import vn.loitp.app.uiza.setting.SettingActivity;
import vn.loitp.app.uiza.view.UizaActionBar;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LDialogUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.Item;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.ListAllMetadata;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.utils.util.ToastUtils;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;

public class Home2Activity extends BaseActivity {
    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawerLayout;
    private FrameLayout flLeftContainer;
    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView) findViewById(R.id.drawerView);
        flLeftContainer = (FrameLayout) findViewById(R.id.fl_left_container);

        LUIUtil.setPullLikeIOSVertical(mDrawerView);

        setupDrawer();
        setupActionBar();
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
        return R.layout.uiza_home_2_activity;
    }

    private void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        }
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
                closeDrawer();
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }

            @Override
            public void onClickSetting() {
                closeDrawer();
                Intent intent = new Intent(activity, SettingActivity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }
        });
        mDrawerView.addView(uizaDrawerHeader);

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

        getListAllMetadata();
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
                Intent intent = new Intent(activity, SearchActivity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    private void getListAllMetadata() {
        LLog.d(TAG, "getListAllMetadata");
        UizaService service = RestClient.createService(UizaService.class);

        int limit = 100;

        subscribe(service.listAllMetadata(limit), new ApiSubscriber<ListAllMetadata>() {
            @Override
            public void onSuccess(ListAllMetadata listAllMetadata) {
                LLog.d(TAG, "getListAllMetadata onSuccess " + LSApplication.getInstance().getGson().toJson(listAllMetadata));
                if (listAllMetadata == null) {
                    showDialogError(getString(R.string.err_unknow));
                    return;
                }
                genListDrawerLayout(listAllMetadata);
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail " + e.getMessage());
                handleException(e);
            }
        });
    }

    private void genListDrawerLayout(ListAllMetadata listAllMetadata) {
        itemList = listAllMetadata.getItems();
        if (itemList == null || itemList.isEmpty()) {
            showDialogOne("itemList == null || itemList.isEmpty()");
            return;
        }

        for (int i = 0; i < this.itemList.size(); i++) {
            mDrawerView.addView(new UizaDrawerMenuItem(this.getApplicationContext(), itemList, i, new UizaDrawerMenuItem.Callback() {
                @Override
                public void onMenuItemClick(int pos) {
                    HomeData.getInstance().setCurrentPosition(pos);
                    HomeData.getInstance().setItem(itemList.get(pos));
                    mDrawerLayout.closeDrawers();
                    replaceFragment(new FrmChannel2());
                }
            }));
        }

        //init data first
        HomeData.getInstance().setItem(itemList.get(HomeData.getInstance().getCurrentPosition()));
        replaceFragment(new FrmChannel2());
    }

    @Override
    public void onBackPressed() {
        LDialogUtil.showOne(activity, getString(R.string.app_name), "Bạn muốn thoát ứng dụng đúng không?", getString(R.string.confirm), new LDialogUtil.CallbackShowOne() {
            @Override
            public void onClick() {
                finish();
                LUIUtil.transActivityFadeIn(activity);
            }
        });
    }
}
