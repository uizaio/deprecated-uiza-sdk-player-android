package vn.loitp.app.uiza.home.v2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v1.FrmUizaVideo;
import com.uiza.player.ui.views.helper.InputModel;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.data.EventBusData;
import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.app.uiza.home.view.BlankView;
import vn.loitp.app.uiza.home.view.EntityItem;
import vn.loitp.app.uiza.home.view.LoadingView;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LDisplayUtils;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.restapi.uiza.model.v2.listallentity.Item;
import vn.loitp.restapi.uiza.model.v2.listallentity.ListAllEntity;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.JsonBody;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.utils.util.ToastUtils;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmChannel2 extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    private TextView tv;
    private TextView tvMsg;
    private PlaceHolderView placeHolderView;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private DraggablePanel draggablePanel;

    private final int NUMBER_OF_COLUMN = 2;
    private final int NUMBER_OF_COLUMN_1 = 1;
    private final int NUMBER_OF_COLUMN_2 = 2;
    private final int POSITION_OF_LOADING_REFRESH = 2;

    private boolean isRefreshing;
    private boolean isLoadMoreCalling;
    private final int limit = 15;
    private int page = 0;
    private int totalPage = Integer.MAX_VALUE;
    private final String orderBy = "name";
    private final String orderType = "ASC";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.uiza_frm_channel_2, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        tvMsg = (TextView) view.findViewById(R.id.tv_msg);
        tv.setText("Debug V2: " + HomeData.getInstance().getItem().getName());

        placeHolderView = (PlaceHolderView) view.findViewById(R.id.place_holder_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), NUMBER_OF_COLUMN_2);
        placeHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(gridLayoutManager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position) {
                    case POSITION_OF_LOADING_REFRESH:
                        return isRefreshing ? NUMBER_OF_COLUMN_2 : NUMBER_OF_COLUMN_1;
                    default:
                        return NUMBER_OF_COLUMN_1;
                }
            }
        });
        LUIUtil.setPullLikeIOSVertical(placeHolderView, new LUIUtil.Callback() {
            @Override
            public void onUpOrLeft(float offset) {
                //do nothing
            }

            @Override
            public void onUpOrLeftRefresh(float offset) {
                LLog.d(TAG, "onUpOrLeftRefresh");
                swipeToRefresh();
            }

            @Override
            public void onDownOrRight(float offset) {
                //do nothing
            }

            @Override
            public void onDownOrRightRefresh(float offset) {
                LLog.d(TAG, "onDownOrRightRefresh");
                loadMore();
            }
        });

        avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        draggablePanel = (DraggablePanel) view.findViewById(R.id.draggable_panel);
        avLoadingIndicatorView.smoothToShow();

        getData(false);
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                LLog.d(TAG, "draggablePanel onMaximized");
            }

            @Override
            public void onMinimized() {
                LLog.d(TAG, "draggablePanel onMinimized");
            }

            @Override
            public void onClosedToLeft() {
                LLog.d(TAG, "draggablePanel onClosedToLeft");
            }

            @Override
            public void onClosedToRight() {
                LLog.d(TAG, "draggablePanel onClosedToRight");
            }
        });
        return view;
    }

    /*private List<Item> getSubList(List<Item> itemList, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > itemList.size()) {
            return null;
        }
        List<Item> items = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            items.add(itemList.get(i));
        }
        LLog.d(TAG, "getList " + startIndex + " - " + endIndex + " -> " + items.size());
        return items;
    }*/

    private void setupData(List<Item> itemList, boolean isCallFromLoadMore) {
        /*//poster
        List<Item> itemListPoster = getSubList(itemList, 0, 5);
        placeHolderView.addView(new PosterView(getActivity(), itemListPoster, new PosterView.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));

        //top movie
        ChannelObject channelObjectTopMovies = new ChannelObject();
        channelObjectTopMovies.setChannelName("Top Movies");
        List<Item> itemListTopMovies = getSubList(itemList, 6, 15);
        channelObjectTopMovies.setItemList(itemListTopMovies);
        placeHolderView.addView(new ChannelList(getActivity(), channelObjectTopMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));

        //top movie
        ChannelObject channelObjectNewestMovies = new ChannelObject();
        channelObjectNewestMovies.setChannelName("Newest Movies");
        List<Item> itemListNewestMovies = getSubList(itemList, 16, itemList.size() - 1);
        channelObjectNewestMovies.setItemList(itemListNewestMovies);
        placeHolderView.addView(new ChannelList(getActivity(), channelObjectNewestMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));

        //top movie
        ChannelObject channelObjectAllMovies = new ChannelObject();
        channelObjectAllMovies.setChannelName("All Movies");
        channelObjectAllMovies.setItemList(itemList);
        placeHolderView.addView(new ChannelList(getActivity(), channelObjectAllMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));*/

        int sizeW = LDisplayUtils.getScreenW(getActivity()) / 2;
        int sizeH = sizeW * 9 / 16;

        if (isCallFromLoadMore) {
            placeHolderView.removeView(getListSize() - 1);//remove loading view
        } else {
            addBlankView();
        }
        for (Item item : itemList) {
            placeHolderView.addView(new EntityItem(getActivity(), item, sizeW, sizeH, new EntityItem.Callback() {
                @Override
                public void onClick(Item item, int position) {
                    onClickVideo(item, position);
                }
            }));
        }
        if (!isCallFromLoadMore) {
            avLoadingIndicatorView.smoothToHide();
        } else {
            isLoadMoreCalling = false;
        }
        initializeDraggablePanel();
    }

    private int getListSize() {
        return placeHolderView.getAllViewResolvers().size();
    }

    private void addBlankView() {
        for (int i = 0; i < NUMBER_OF_COLUMN; i++) {
            placeHolderView.addView(new BlankView());
        }
    }

    private void onClickVideo(Item item, int position) {
        LLog.d(TAG, "onClickVideo at " + position + ": " + LSApplication.getInstance().getGson().toJson(item));
        if (draggablePanel.isClosedAtLeft() || draggablePanel.isClosedAtRight()) {
            LLog.d(TAG, "isClosedAtLeft || isClosedAtRight");
            draggablePanel.minimize();
            if (draggablePanel.getVisibility() != View.VISIBLE) {
                draggablePanel.setVisibility(View.VISIBLE);
            }
        }
        onClick(item.getId(), item.getThumbnail(), item.getName());
        EventBusData.getInstance().sendClickVideoEvent(item.getId());
    }

    private void onClick(String entityId, String entityCover, String entityTitle) {
        LLog.d(TAG, "entityId " + entityId);
        if (entityId == null || entityId.isEmpty()) {
            showDialogError("entityId == null || entityId.isEmpty()");
            return;
        }

        RestClient.init(UizaData.getInstance().getApiEndPoint(), UizaData.getInstance().getToken());
        getPlayerConfig(entityId, entityCover, entityTitle);

        //inputModel = createInputModel(entityId, entityCover, entityTitle);
        //UizaData.getInstance().setInputModel(inputModel);
    }

    private InputModel inputModel;

    private void getPlayerConfig(String entityId, String entityCover, String entityTitle) {
        LLog.d(TAG, ">>>getPlayerConfig");
        if (UizaData.getInstance().getPlayerConfig() == null) {
            LLog.d(TAG, "UizaData.getInstance().getPlayerConfig() == null");
            UizaService service = RestClient.createService(UizaService.class);
            subscribe(service.getPlayerInfo(UizaData.getInstance().getPlayerId()), new vn.loitp.rxandroid.ApiSubscriber<PlayerConfig>() {
                @Override
                public void onSuccess(PlayerConfig playerConfig) {
                    //TODO custom theme
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
        inputModel.setAction(inputModel.getPlaylist() == null ? FrmUizaVideo.ACTION_VIEW : FrmUizaVideo.ACTION_VIEW_LIST);
        inputModel.setPreferExtensionDecoders(false);

        //TODO remove this code below
        //inputModel.setUri("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");
        //inputModel.setUri("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");
        //inputModel.setUri("http://cdn-broadcast.yuptv.vn/ba_dash/0c45905848ca4ec99d2ed7c11bc8f8ad-a1556c60605a4fe4a1a22eafb4e89b44/index.mpd");

        //inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=");
        return inputModel;
    }

    private void getData(boolean isCallFromLoadMore) {
        LLog.d(TAG, ">>>getData " + page + "/" + totalPage);

        if (page >= totalPage) {
            LLog.d(TAG, "page >= totalPage -> return");
            ToastUtils.showShort("This is last page");
            placeHolderView.removeView(getListSize() - 1);//remove loading view
            if (isCallFromLoadMore) {
                isLoadMoreCalling = false;
            }
            return;
        }

        ToastUtils.showShort("getData page " + page);
        if (tvMsg.getVisibility() != View.GONE) {
            tvMsg.setVisibility(View.GONE);
        }

        UizaService service = RestClient.createService(UizaService.class);

        JsonBody jsonBody = new JsonBody();
        List<String> metadataId = new ArrayList<>();
        metadataId.add(HomeData.getInstance().getItem().getId());
        jsonBody.setMetadataId(metadataId);
        jsonBody.setLimit(limit);
        jsonBody.setPage(page);
        jsonBody.setOrderBy(orderBy);
        jsonBody.setOrderType(orderType);
        LLog.d(TAG, "jsonBody " + LSApplication.getInstance().getGson().toJson(jsonBody));
        LLog.d(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<");

        subscribe(service.listAllEntity(jsonBody), new ApiSubscriber<ListAllEntity>() {
            @Override
            public void onSuccess(ListAllEntity listAllEntity) {
                LLog.d(TAG, "getData onSuccess " + LSApplication.getInstance().getGson().toJson(listAllEntity));
                LLog.d(TAG, "getLimit " + listAllEntity.getLimit());
                LLog.d(TAG, "getPage " + listAllEntity.getPage());
                LLog.d(TAG, "getTotal " + listAllEntity.getTotal());
                LLog.d(TAG, "getItems().size " + listAllEntity.getItems().size());

                if (totalPage == Integer.MAX_VALUE) {
                    int totalItem = listAllEntity.getTotal();
                    float ratio = (float) (totalItem / limit);
                    LLog.d(TAG, "ratio: " + ratio);
                    if (ratio == 0) {
                        totalPage = (int) ratio;
                    } else if (ratio > 0) {
                        totalPage = (int) ratio + 1;
                    } else {
                        totalPage = (int) ratio;
                    }
                    LLog.d(TAG, ">>>totalPage: " + totalPage);
                }
                List<Item> itemList = listAllEntity.getItems();
                if (itemList == null || itemList.isEmpty()) {
                    if (tvMsg.getVisibility() != View.VISIBLE) {
                        tvMsg.setVisibility(View.VISIBLE);
                        tvMsg.setText(getString(R.string.empty_list));
                    }
                    if (!isCallFromLoadMore) {
                        avLoadingIndicatorView.smoothToHide();
                    } else {
                        isLoadMoreCalling = false;
                    }
                } else {
                    setupData(itemList, isCallFromLoadMore);
                }
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "listAllEntity onFail " + e.toString());
                //handleException(e);
                if (tvMsg.getVisibility() != View.VISIBLE) {
                    tvMsg.setVisibility(View.VISIBLE);
                    if (e != null && e.getMessage() != null) {
                        tvMsg.setText(e.getMessage());
                    }
                }
                if (!isCallFromLoadMore) {
                    avLoadingIndicatorView.smoothToHide();
                } else {
                    isLoadMoreCalling = false;
                }
            }
        });
    }

    private void initializeDraggablePanel() throws Resources.NotFoundException {
        FrmTop frmTop = new FrmTop();
        FrmBottom frmBottom = new FrmBottom();
        draggablePanel.setFragmentManager(getActivity().getSupportFragmentManager());
        draggablePanel.setTopFragment(frmTop);
        draggablePanel.setBottomFragment(frmBottom);

        //draggablePanel.setXScaleFactor(xScaleFactor);
        //draggablePanel.setYScaleFactor(yScaleFactor);

        int widthScreen = LDisplayUtils.getScreenW(getActivity());
        LLog.d(TAG, "widthScreen " + widthScreen);
        int heightFrmTop = widthScreen * 9 / 16;
        LLog.d(TAG, "heightFrmTop " + heightFrmTop);

        draggablePanel.setTopViewHeight(heightFrmTop);//px
        draggablePanel.setEnableHorizontalAlphaEffect(false);
        //draggablePanel.setTopFragmentMarginRight(topViewMarginRight);
        //draggablePanel.setTopFragmentMarginBottom(topViewMargnBottom);
        draggablePanel.setClickToMaximizeEnabled(false);
        draggablePanel.setClickToMinimizeEnabled(false);

        draggablePanel.initializeView();
    }

    private void swipeToRefresh() {
        if (isRefreshing) {
            return;
        }
        isRefreshing = true;
        placeHolderView.addView(POSITION_OF_LOADING_REFRESH, new LoadingView());

        //TODO refresh
        LUIUtil.setDelay(2000, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                placeHolderView.removeView(POSITION_OF_LOADING_REFRESH);
                isRefreshing = false;
            }
        });
    }

    private void loadMore() {
        if (isLoadMoreCalling) {
            LLog.d(TAG, "isLoadMoreCalling true -> return");
            return;
        }
        isLoadMoreCalling = true;
        placeHolderView.addView(new LoadingView());
        placeHolderView.smoothScrollToPosition(getListSize() - 1);
        page++;
        getData(true);
    }
}