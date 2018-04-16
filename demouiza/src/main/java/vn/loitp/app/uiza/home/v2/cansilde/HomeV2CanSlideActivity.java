package vn.loitp.app.uiza.home.v2.cansilde;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v2.WrapperCallback;
import com.uiza.player.ui.player.v2.canslide.ClickCallback;
import com.uiza.player.ui.player.v2.canslide.FrmBottomV2;
import com.uiza.player.ui.player.v2.canslide.FrmTopV2;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaUIUtil;
import com.uiza.player.ui.views.SimpleExoPlayerView;
import com.uiza.player.ui.views.helper.InputModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.data.HomeDataV2;
import vn.loitp.app.uiza.home.IOnBackPressed;
import vn.loitp.app.uiza.home.view.UizaDrawerHeader;
import vn.loitp.app.uiza.home.view.UizaDrawerMenuItemV2;
import vn.loitp.app.uiza.login.LoginActivity;
import vn.loitp.app.uiza.view.UizaActionBar;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LActivityUtil;
import vn.loitp.core.utilities.LDisplayUtils;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.data.EventBusData;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.restapi.uiza.model.v2.listallentity.Item;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.Datum;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.JsonBodyMetadataList;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.ListAllMetadata;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.views.LToast;
import vn.loitp.views.draggablepanel.DraggableListener;
import vn.loitp.views.draggablepanel.DraggablePanel;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;

public class HomeV2CanSlideActivity extends BaseActivity {
    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawerLayout;
    private FrameLayout flLeftContainer;
    private List<Datum> datumList = new ArrayList<>();
    private UizaActionBar uizaActionBar;
    private DraggablePanel draggablePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView) findViewById(R.id.drawerView);
        flLeftContainer = (FrameLayout) findViewById(R.id.fl_left_container);
        uizaActionBar = (UizaActionBar) findViewById(R.id.uiza_action_bar);
        draggablePanel = (DraggablePanel) findViewById(R.id.draggable_panel);

        LUIUtil.setPullLikeIOSVertical(mDrawerView);

        setupDrawer();
        setupActionBar();

        getSupportFragmentManager().addOnBackStackChangedListener(onBackStackChangedListener());

        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                LLog.d(TAG, "setDraggableListener draggablePanel onMaximized");
            }

            @Override
            public void onMinimized() {
                LLog.d(TAG, "setDraggableListener draggablePanel onMinimized");
                if (frmTopV2 != null) {
                    frmTopV2.hideController();
                }
            }

            @Override
            public void onClosedToLeft() {
                LLog.d(TAG, "setDraggableListener draggablePanel onClosedToLeft");
                releasePlayer();
            }

            @Override
            public void onClosedToRight() {
                LLog.d(TAG, "setDraggableListener draggablePanel onClosedToRight");
                releasePlayer();
            }
        });
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return "TAG" + getClass().getSimpleName();
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
                LToast.show(activity, "Click");
            }

            @Override
            public void onClickLogin() {
                closeDrawer();
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                LActivityUtil.tranIn(activity);
            }

            /*@Override
            public void onClickSetting() {
                closeDrawer();
                Intent intent = new Intent(activity, SettingActivity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }*/
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

    public void setVisibilityOfActionBar(int visibilityOfActionBar) {
        LLog.d(TAG, "setVisibilityOfActionBar " + visibilityOfActionBar);
        uizaActionBar.setVisibility(visibilityOfActionBar);
    }

    private void setupActionBar() {
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
                if (draggablePanel.isMaximized()) {
                    draggablePanel.minimize();
                }
                setVisibilityOfActionBar(View.GONE);
                UizaScreenUtil.addFragment(activity, R.id.fragment_container, new FrmSearchV2(), true);
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

    private void getListAllMetadata() {
        LLog.d(TAG, "getListAllMetadata");
        genHomeMenu();
        UizaService service = RestClientV2.createService(UizaService.class);
        int limit = 100;
        String orderBy = "name";
        String orderType = "ASC";

        JsonBodyMetadataList jsonBodyMetadataList = new JsonBodyMetadataList();
        jsonBodyMetadataList.setLimit(limit);
        jsonBodyMetadataList.setOrderBy(orderBy);
        jsonBodyMetadataList.setOrderType(orderType);

        subscribe(service.listAllMetadataV2(jsonBodyMetadataList), new ApiSubscriber<ListAllMetadata>() {
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
                LLog.e(TAG, "getListAllMetadata onFail " + e.getMessage());
                handleException(e);
                genListDrawerLayout(null);
            }
        });
    }

    private void genHomeMenu() {
        //add home menu
        Datum item = new Datum();
        item.setName("Home");
        item.setId(String.valueOf(Constants.NOT_FOUND));
        item.setType("folder");
        datumList.add(0, item);
        //emd add home menu
    }

    private void genListDrawerLayout(ListAllMetadata listAllMetadata) {
        datumList.addAll(listAllMetadata.getData());
        for (int i = 0; i < this.datumList.size(); i++) {
            mDrawerView.addView(new UizaDrawerMenuItemV2(this.getApplicationContext(), datumList, i, new UizaDrawerMenuItemV2.Callback() {
                @Override
                public void onMenuItemClick(int pos) {
                    if (draggablePanel.isMaximized()) {
                        draggablePanel.minimize();
                    }
                    HomeDataV2.getInstance().setCurrentPosition(pos);
                    HomeDataV2.getInstance().setDatum(datumList.get(pos));
                    mDrawerLayout.closeDrawers();
                    UizaScreenUtil.replaceFragment(activity, R.id.fragment_container, new FrmChannelV2(), true);
                }
            }));
        }

        //init data first
        HomeDataV2.getInstance().setDatum(datumList.get(HomeDataV2.getInstance().getCurrentPosition()));
        currentFrm = new FrmChannelV2();
        UizaScreenUtil.replaceFragment(activity, R.id.fragment_container, currentFrm, true);
    }

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            LLog.d(TAG, "onBackPressed exit");
            finish();
            LActivityUtil.tranOut(activity);
            return;
        }
        if (UizaData.getInstance().isLandscape()) {
            if (frmTopV2 != null) {
                SimpleExoPlayerView simpleExoPlayerView = frmTopV2.getPlayerView();
                simpleExoPlayerView.getController().getFullscreenButton().performClick();
                LLog.d(TAG, "onBackPressed isLandscape");
                return;
            }
        } else {
            LLog.d(TAG, "onBackPressed !isLandscape");
            if (draggablePanel != null) {
                LLog.d(TAG, "onBackPressed draggablePanel != null");
                if (draggablePanel.isMaximized()) {
                    LLog.d(TAG, "onBackPressed isMaximized");
                    if (draggablePanel.getVisibility() == View.VISIBLE) {
                        LLog.d(TAG, "onBackPressed draggablePanel VISIBLE");
                        draggablePanel.minimize();
                        return;
                    } else {
                        LLog.d(TAG, "onBackPressed draggablePanel !VISIBLE");
                    }
                }
            } else {
                LLog.d(TAG, "onBackPressed draggablePanel == null");
                confirmExit();
                return;
            }
        }
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            LLog.d(TAG, "onBackPressed instanceof if");
            confirmExit();
        }
    }

    private void confirmExit() {
        this.doubleBackToExitPressedOnce = true;
        LToast.show(activity, getString(R.string.press_again_to_exit));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private FragmentManager.OnBackStackChangedListener onBackStackChangedListener() {
        FragmentManager.OnBackStackChangedListener result = new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                if (doubleBackToExitPressedOnce) {
                    return;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager != null) {
                    BaseFragment currFrag = (BaseFragment) fragmentManager.findFragmentById(R.id.fragment_container);
                    if (currentFrm != null) {
                        if (currentFrm == currFrag) {
                            //do nothing
                        } else {
                            currentFrm.onFragmentPause();
                            currentFrm = currFrag;
                        }
                    }
                }
            }
        };
        return result;
    }

    private BaseFragment currentFrm;

    private void releasePlayer() {
        if (frmTopV2 != null) {
            frmTopV2.releasePlayer();
            frmTopV2.removeCallbacks();
        }
        if (frmBottomV2 != null) {
            frmBottomV2.setRootViewVisibility(View.GONE);
        }
    }

    public void onClickVideo(Item item, int position) {
        LLog.d(TAG, "onClickVideo at " + position + ": " + LSApplication.getInstance().getGson().toJson(item));
        if (draggablePanel.isClosedAtLeft() || draggablePanel.isClosedAtRight()) {
            LLog.d(TAG, "isClosedAtLeft || isClosedAtRight");
            draggablePanel.maximize();
            if (draggablePanel.getVisibility() != View.VISIBLE) {
                draggablePanel.setVisibility(View.VISIBLE);
            }
        } else {
            if (!draggablePanel.isMaximized()) {
                draggablePanel.maximize();
            }
        }
        onClick(item.getId(), item.getThumbnail(), item.getName());
    }

    private void onClick(String entityId, String entityCover, String entityTitle) {
        LLog.d(TAG, "entityId " + entityId);
        if (entityId == null || entityId.isEmpty()) {
            showDialogError("entityId == null || entityId.isEmpty()");
            return;
        }
        RestClientV2.init(UizaData.getInstance().getApiEndPoint(), UizaData.getInstance().getToken());
        getPlayerConfig(entityId, entityCover, entityTitle);
    }

    private InputModel inputModel;

    private void getPlayerConfig(String entityId, String entityCover, String entityTitle) {
        LLog.d(TAG, ">>>getPlayerConfig");
        if (UizaData.getInstance().getPlayerConfig() == null) {
            LLog.d(TAG, "UizaData.getInstance().getPlayerConfig() == null");
            UizaService service = RestClientV2.createService(UizaService.class);
            subscribe(service.getPlayerInfo(UizaData.getInstance().getPlayerId()), new vn.loitp.rxandroid.ApiSubscriber<PlayerConfig>() {
                @Override
                public void onSuccess(PlayerConfig playerConfig) {
                    LLog.d(TAG, "getPlayerConfig onSuccess " + LSApplication.getInstance().getGson().toJson(playerConfig));
                    UizaData.getInstance().setPlayerConfig(playerConfig);

                    inputModel = UizaUIUtil.createInputModel(entityId, entityCover, entityTitle);
                    UizaData.getInstance().setInputModel(inputModel);
                }

                @Override
                public void onFail(Throwable e) {
                    LLog.e(TAG, "getPlayerConfig onFail " + e.toString());
                    handleException(e);
                }
            });
        } else {
            LLog.d(TAG, "UizaData.getInstance().getPlayerConfig() != null -> play new");
            inputModel = UizaUIUtil.createInputModel(entityId, entityCover, entityTitle);
            UizaData.getInstance().setInputModel(inputModel);
        }
        EventBusData.getInstance().sendClickVideoEvent(entityId);
    }

    private FrmTopV2 frmTopV2;
    private FrmBottomV2 frmBottomV2;

    public void initializeDraggablePanel() throws Resources.NotFoundException {
        LLog.d(TAG, "initializeDraggablePanel");
        if (frmTopV2 != null && frmBottomV2 != null) {
            LLog.d(TAG, "initializeDraggablePanel done before -> return");
            return;
        }
        frmTopV2 = new FrmTopV2();
        frmTopV2.setWrapperCallback(new WrapperCallback() {
            @Override
            public void initializePlayer(Uri[] uris) {
                LLog.d(TAG, "setWrapperCallback initializePlayer ");
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                LLog.d(TAG, "setWrapperCallback onPlayerStateChanged ");
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                LLog.d(TAG, "setWrapperCallback onTracksChanged ");
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                LLog.d(TAG, "setWrapperCallback onLoadingChanged ");
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {
                LLog.d(TAG, "setWrapperCallback onRepeatModeChanged ");
            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                LLog.d(TAG, "setWrapperCallback onPlaybackParametersChanged ");
            }

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {
                LLog.d(TAG, "setWrapperCallback onTimelineChanged ");
            }

            @Override
            public void onTrackVideoStart() {
                LLog.d(TAG, "setWrapperCallback onTrackVideoStart ");
            }

            @Override
            public void onTrackVideoView() {
                LLog.d(TAG, "setWrapperCallback onTrackVideoView ");
            }

            @Override
            public void onTrackPlayThrough(int percentOfVideoDuration) {
                LLog.d(TAG, "setWrapperCallback onTrackPlayThrough ");
            }

            @Override
            public void onPlaybackControllerClickSetting() {
                LLog.d(TAG, "setWrapperCallback onPlaybackControllerClickSetting ");
            }

            @Override
            public void onClickItemPlayList(Item item, int position) {
                LLog.d(TAG, "setWrapperCallback onClickItemPlayList onClick " + item.getName() + ", position: " + position);
                onClickVideo(item, position);
            }

            @Override
            public void onVisibilityChange(int visibility) {
                //LLog.d(TAG, "setWrapperCallback onVisibilityChange");
                if (draggablePanel != null && !UizaData.getInstance().isLandscape()) {
                    if (draggablePanel.isMaximized()) {
                        if (visibility == View.VISIBLE) {
                            LLog.d(TAG, TAG + " onVisibilityChange visibility == View.VISIBLE");
                            draggablePanel.setEnableSlide(false);
                        } else {
                            LLog.d(TAG, TAG + " onVisibilityChange visibility != View.VISIBLE");
                            draggablePanel.setEnableSlide(true);
                        }
                    } else {
                        draggablePanel.setEnableSlide(true);
                    }
                }
            }

            @Override
            public void onErrorNoLinkPlay() {
                LLog.d(TAG, "setWrapperCallback onErrorNoLinkPlay ");
            }

            @Override
            public void onErrorCannotPlayAnyLinkPlay() {
                LLog.d(TAG, "setWrapperCallback onErrorCannotPlayAnyLinkPlay ");
            }

            @Override
            public void onReleasePlayer() {
                LLog.d(TAG, "setWrapperCallback onReleasePlayer ");
            }

            @Override
            public void onAdLoadError(IOException error) {
                LLog.d(TAG, "setWrapperCallback onAdLoadError ");
            }

            @Override
            public void onAdClicked() {
                LLog.d(TAG, "setWrapperCallback onAdClicked ");
            }

            @Override
            public void onAdTapped() {
                LLog.d(TAG, "setWrapperCallback onAdTapped ");
            }
        });

        frmBottomV2 = new FrmBottomV2();
        frmBottomV2.setClickCallback(new ClickCallback() {
            @Override
            public void onClick(Item item, int position) {
                LLog.d(TAG, "setClickCallback onClick " + item.getName());
                onClickVideo(item, position);
            }
        });
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(frmTopV2);
        draggablePanel.setBottomFragment(frmBottomV2);

        //draggablePanel.setXScaleFactor(xScaleFactor);
        //draggablePanel.setYScaleFactor(yScaleFactor);

        /*int widthScreen = LDisplayUtils.getScreenW(activity);
        LLog.d(TAG, "initializeDraggablePanel widthScreen " + widthScreen);
        int heightFrmTop = widthScreen * 9 / 16;
        LLog.d(TAG, "initializeDraggablePanel heightFrmTop " + heightFrmTop);

        draggablePanel.setTopViewHeight(heightFrmTop);//px*/
        draggablePanel.setEnableHorizontalAlphaEffect(false);
        //draggablePanel.setTopFragmentMarginRight(topViewMarginRight);
        //draggablePanel.setTopFragmentMarginBottom(topViewMargnBottom);
        draggablePanel.setClickToMaximizeEnabled(false);
        draggablePanel.setClickToMinimizeEnabled(false);

        draggablePanel.initializeView();

        updateUIStatusNavigationBar(true);
    }

    //true: show status bar, hide navigation bar
    //false: hide status bar, hide navigation bar
    private void updateUIStatusNavigationBar(boolean isShowStatusNavigationBar) {
        int widthScreen = LDisplayUtils.getScreenW(activity);
        int heightFrmTop;
        if (isShowStatusNavigationBar) {
            //PORTRAIT SCREEN

            LLog.d(TAG, "updateUIStatusNavigationBar PORTRAIT");
            LUIUtil.setMarginsInDp(draggablePanel, 0, 55, 0, 0);
            if (currentFrm != null) {
                LLog.d(TAG, "updateUIStatusNavigationBar currentFrm " + currentFrm.getClass().getSimpleName());
                if (currentFrm.getClass().getSimpleName().equals(FrmChannelV2.class.getSimpleName())) {
                    setVisibilityOfActionBar(View.VISIBLE);
                }
            }
            //LLog.d(TAG, "updateUIStatusNavigationBar widthScreen " + widthScreen);
            heightFrmTop = widthScreen * 9 / 16;
            //LLog.d(TAG, "updateUIStatusNavigationBar heightFrmTop " + heightFrmTop);
            UizaData.getInstance().setSizeHeightOfSimpleExoPlayerView(heightFrmTop);//cannot remove this
            draggablePanel.setTopViewHeightApllyNow(heightFrmTop);//px
            draggablePanel.setEnableSlide(true);
        } else {
            //LANDSCAPE SCREEN

            LLog.d(TAG, "updateUIStatusNavigationBar LANDSCAPE");
            LUIUtil.setMarginsInDp(draggablePanel, 0, 0, 0, 0);
            setVisibilityOfActionBar(View.GONE);

            heightFrmTop = LDisplayUtils.getScreenH(activity);//px
            UizaData.getInstance().setSizeHeightOfSimpleExoPlayerView(heightFrmTop);//cannot remove this
            //LLog.d(TAG, "updateUIStatusNavigationBar heightFrmTop " + heightFrmTop);
            draggablePanel.setTopViewHeightApllyNow(heightFrmTop);
            draggablePanel.setEnableSlide(false);
        }
        LLog.d(TAG, "************************** updateUIStatusNavigationBar");
        LLog.d(TAG, "updateUIStatusNavigationBar isLandscape: " + UizaData.getInstance().isLandscape());
        LLog.d(TAG, "updateUIStatusNavigationBar widthScreen " + widthScreen);
        LLog.d(TAG, "updateUIStatusNavigationBar heightFrmTop " + heightFrmTop);
        LLog.d(TAG, "====================================== updateUIStatusNavigationBar");

        if (frmTopV2 != null) {
            LLog.d(TAG, "updateUIStatusNavigationBar =>>> update size ");
            frmTopV2.updateSize();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LLog.d(TAG, "onConfigurationChanged");
        UizaScreenUtil.toggleFullscreen(activity);
        if (frmTopV2 != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LLog.d(TAG, "onConfigurationChanged ORIENTATION_LANDSCAPE");
                updateUIStatusNavigationBar(false);
                setDrawerLockMode(true);
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LLog.d(TAG, "onConfigurationChanged ORIENTATION_PORTRAIT");
                updateUIStatusNavigationBar(true);
                setDrawerLockMode(false);
            }
        }
    }

    public DraggablePanel getDraggablePanel() {
        return draggablePanel;
    }

    private void setDrawerLockMode(boolean isLock) {
        if (mDrawerLayout != null) {
            if (isLock) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    }
}
