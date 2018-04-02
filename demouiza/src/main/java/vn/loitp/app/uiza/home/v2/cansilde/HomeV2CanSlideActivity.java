package vn.loitp.app.uiza.home.v2.cansilde;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v2.cannotslide.FrmUizaVideoV2;
import com.uiza.player.ui.player.v2.canslide.ClickCallback;
import com.uiza.player.ui.player.v2.canslide.FrmBottomV2;
import com.uiza.player.ui.player.v2.canslide.FrmTopV2;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.views.SimpleExoPlayerView;
import com.uiza.player.ui.views.helper.InputModel;

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
import vn.loitp.core.utilities.LDialogUtil;
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

                uizaActionBar.setVisibility(View.GONE);
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
            }
        });
    }

    private void genListDrawerLayout(ListAllMetadata listAllMetadata) {
        datumList = listAllMetadata.getData();

        //add home menu
        Datum item = new Datum();
        item.setName("Home");
        item.setId(String.valueOf(Constants.NOT_FOUND));
        item.setType("folder");
        datumList.add(0, item);
        //emd add home menu

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

    @Override
    public void onBackPressed() {
        LLog.d(TAG, TAG + " onBackPressed");

        if (UizaData.getInstance().isLandscape()) {
            if (frmTopV2 != null) {
                SimpleExoPlayerView simpleExoPlayerView = frmTopV2.getPlayerView();
                simpleExoPlayerView.getController().getFullscreenButton().performClick();
                LLog.d(TAG, "isLandscape");
                return;
            }
        } else {
            LLog.d(TAG, "!isLandscape");
            if (draggablePanel != null) {
                LLog.d(TAG, "draggablePanel != null");
                if (draggablePanel.isMaximized()) {
                    LLog.d(TAG, "isMaximized");
                    if (draggablePanel.getVisibility() == View.VISIBLE) {
                        LLog.d(TAG, "draggablePanel VISIBLE");
                        draggablePanel.minimize();
                        return;
                    } else {
                        LLog.d(TAG, "draggablePanel !VISIBLE");
                    }
                }
            } else {
                LLog.d(TAG, "draggablePanel == null");
                confirmExit();
                return;
            }
        }

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            confirmExit();
        }
    }

    private void confirmExit() {
        LDialogUtil.showOne(activity, getString(R.string.app_name), "Bạn muốn thoát ứng dụng đúng không?", getString(R.string.confirm), new LDialogUtil.CallbackShowOne() {
            @Override
            public void onClick() {
                UizaData.getInstance().reset();
                if (draggablePanel != null && draggablePanel.isMinimized()) {
                    draggablePanel.closeToRight();
                }
                finish();
                LActivityUtil.tranOut(activity);
            }
        });
    }

    private FragmentManager.OnBackStackChangedListener onBackStackChangedListener() {
        FragmentManager.OnBackStackChangedListener result = new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager != null) {
                    BaseFragment currFrag = (BaseFragment) fragmentManager.findFragmentById(R.id.fragment_container);
                    currFrag.onFragmentResume();
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
        if (frmTopV2 == null) {
            LLog.d(TAG, "draggablePanel frmTopV2 == null");
        } else {
            LLog.d(TAG, "draggablePanel frmTopV2 != null");
            frmTopV2.releasePlayer();
            frmTopV2.removeCallbacks();
        }
    }

    public void onClickVideo(Item item, int position) {
        LLog.d(TAG, "onClickVideo at " + position + ": " + LSApplication.getInstance().getGson().toJson(item));
        if (draggablePanel.isClosedAtLeft() || draggablePanel.isClosedAtRight()) {
            LLog.d(TAG, "isClosedAtLeft || isClosedAtRight");
            draggablePanel.minimize();
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

                    inputModel = createInputModel(entityId, entityCover, entityTitle);
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
            inputModel = createInputModel(entityId, entityCover, entityTitle);
            UizaData.getInstance().setInputModel(inputModel);
        }
        EventBusData.getInstance().sendClickVideoEvent(entityId);
    }

    private InputModel createInputModel(String entityId, String entityCover, String entityTitle) {
        InputModel inputModel = new InputModel();
        inputModel.setEntityID(entityId);

        if (entityCover == null || entityCover.isEmpty()) {
            inputModel.setUrlImg(Constants.URL_IMG_9x16);
        } else {
            inputModel.setUrlImg(Constants.PREFIXS + entityCover);
        }
        inputModel.setTitle(entityTitle + "");

        inputModel.setExtension("mpd");
        //inputModel.setDrmLicenseUrl("");
        inputModel.setAction(inputModel.getPlaylist() == null ? FrmUizaVideoV2.ACTION_VIEW : FrmUizaVideoV2.ACTION_VIEW_LIST);
        inputModel.setPreferExtensionDecoders(false);

        //TODO remove this code below
        //inputModel.setUri("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");
        //inputModel.setUri("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");
        //inputModel.setUri("http://cdn-broadcast.yuptv.vn/ba_dash/0c45905848ca4ec99d2ed7c11bc8f8ad-a1556c60605a4fe4a1a22eafb4e89b44/index.mpd");

        //inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=");
        return inputModel;
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
        /*frmTopV2.setVisibilityChange(new FrmTopV2.VisibilityChange() {
            @Override
            public void onVisibilityChange(int visibility) {
                if (visibility == View.VISIBLE) {
                    LLog.d(TAG, ">>>onVisibilityChange VISIBLE " + visibility);
                } else {
                    LLog.d(TAG, ">>>onVisibilityChange !VISIBLE " + visibility);
                }
            }
        });*/
        frmTopV2.setClickCallback(new ClickCallback() {
            @Override
            public void onClick(Item item, int position) {
                LLog.d(TAG, "onClick " + item.getName() + ", position: " + position);
                onClickVideo(item, position);
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
        draggablePanel.setClickToMaximizeEnabled(true);
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
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LLog.d(TAG, "onConfigurationChanged ORIENTATION_PORTRAIT");
                updateUIStatusNavigationBar(true);
            }
        }
    }

    public DraggablePanel getDraggablePanel() {
        return draggablePanel;
    }
}
