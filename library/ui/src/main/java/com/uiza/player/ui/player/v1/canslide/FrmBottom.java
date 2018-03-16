package com.uiza.player.ui.player.v1.canslide;

/**
 * Created by www.muathu@gmail.com on 12/24/2017.
 */

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v1.cannotslide.ItemAdapterV1;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LDisplayUtils;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.data.EventBusData;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v1.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v1.listallentity.Item;
import vn.loitp.restapi.uiza.model.v1.listallentityrelation.ListAllEntityRelation;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmBottom extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private TextView tvVideoName;
    private TextView tvVideoTime;
    private TextView tvVideoRate;
    private TextView tvVideoDescription;
    private TextView tvVideoStarring;
    private TextView tvVideoDirector;
    private TextView tvVideoGenres;
    private TextView tvDebug;
    private TextView tvMoreLikeThisMsg;
    //TODO remove gson later
    private Gson gson = new Gson();

    //private NestedScrollView nestedScrollView;
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapterV1 mAdapter;

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
        View view = inflater.inflate(R.layout.frm_bottom, container, false);
        //nestedScrollView = (NestedScrollView) view.findViewById(R.id.scroll_view);
        //nestedScrollView.setNestedScrollingEnabled(false);
        avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        avLoadingIndicatorView.smoothToShow();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        tvVideoName = (TextView) view.findViewById(R.id.tv_video_name);
        tvVideoTime = (TextView) view.findViewById(R.id.tv_video_time);
        tvVideoRate = (TextView) view.findViewById(R.id.tv_video_rate);
        tvVideoDescription = (TextView) view.findViewById(R.id.tv_video_description);
        tvVideoStarring = (TextView) view.findViewById(R.id.tv_video_starring);
        tvVideoDirector = (TextView) view.findViewById(R.id.tv_video_director);
        tvVideoGenres = (TextView) view.findViewById(R.id.tv_video_genres);
        tvDebug = (TextView) view.findViewById(R.id.tv_debug);
        tvMoreLikeThisMsg = (TextView) view.findViewById(R.id.tv_more_like_this_msg);

        int sizeW = LDisplayUtils.getScreenW(getActivity()) / 2;
        int sizeH = sizeW * 9 / 16;
        mAdapter = new ItemAdapterV1(getActivity(), itemList, sizeW, sizeH, new ItemAdapterV1.Callback() {
            @Override
            public void onClick(Item item, int position) {
                LLog.d(TAG, "onClick " + position);
                //V1
                /*Intent intent = new Intent(getActivity(), UizaPlayerActivity.class);
                intent.putExtra(KEY_UIZA_ENTITY_ID, item.getId());
                intent.putExtra(KEY_UIZA_ENTITY_COVER, item.getThumbnail());
                intent.putExtra(KEY_UIZA_ENTITY_TITLE, item.getName());
                startActivity(intent);
                LUIUtil.transActivityFadeIn(getActivity());*/

                //V2
                if (clickCallback != null) {
                    clickCallback.onClick(item, position);
                }
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });

        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    public interface ClickCallback {
        public void onClick(Item item, int position);
    }

    private ClickCallback clickCallback;

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    private void updateUI(GetDetailEntity getDetailEntity) {
        Item mItem = getDetailEntity.getItem().get(0);
        final String emptyS = "Empty string";
        final String nullS = "Data is null";
        try {
            tvVideoName.setText(mItem.getName());
        } catch (NullPointerException e) {
            tvVideoName.setText(nullS);
        }
        try {
            //TODO
            tvVideoTime.setText("Dummy Time");
        } catch (NullPointerException e) {
            tvVideoTime.setText(nullS);
        }
        //TODO
        tvVideoRate.setText("Dummy 18+");
        try {
            tvVideoDescription.setText(mItem.getDescription().isEmpty() ? mItem.getShortDescription().isEmpty() ? emptyS : mItem.getShortDescription() : mItem.getDescription());
        } catch (NullPointerException e) {
            tvVideoDescription.setText(nullS);
        }

        //TODO
        tvVideoStarring.setText(emptyS);
        tvVideoDirector.setText(emptyS);
        tvVideoGenres.setText(emptyS);

        getListAllEntityRelation(mItem.getId());

        if (Constants.IS_DEBUG) {
            tvDebug.setVisibility(View.VISIBLE);
            LUIUtil.printBeautyJson(getDetailEntity, tvDebug);
        }
    }

    private void getListAllEntityRelation(String entityId) {
        //API v2
        this.itemList.clear();
        mAdapter.notifyDataSetChanged();
        UizaService service = RestClientV2.createService(UizaService.class);
        LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getListAllEntityRalationV1(entityId), new ApiSubscriber<ListAllEntityRelation>() {
            @Override
            public void onSuccess(ListAllEntityRelation getDetailEntity) {
                LLog.d(TAG, "getListAllEntityRelation onSuccess " + gson.toJson(getDetailEntity));
                if (getDetailEntity == null || getDetailEntity.getItems().isEmpty()) {
                    tvMoreLikeThisMsg.setText("Data is empty");
                    tvMoreLikeThisMsg.setVisibility(View.VISIBLE);
                } else {
                    tvMoreLikeThisMsg.setVisibility(View.GONE);
                    setupUIMoreLikeThis(getDetailEntity.getItems());
                }
                avLoadingIndicatorView.smoothToHide();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "getListAllEntityRelation onFail " + e.toString());
                handleException(e);
            }
        });
        //EndAPI v2
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusData.ClickVideoEvent clickVideoEvent) {
        LLog.d(TAG, TAG + " clickVideoEvent");
        if (clickVideoEvent != null) {
            LLog.d(TAG, "update UI getEntityId " + clickVideoEvent.getEntityId());
            getDetailEntity(clickVideoEvent.getEntityId());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void getDetailEntity(String entityId) {
        LLog.d(TAG, ">>>getDetailEntityV2 entityId " + entityId);
        tvVideoName.setText("");
        tvVideoTime.setText("");
        tvVideoRate.setText("");
        tvVideoDescription.setText("");
        tvVideoStarring.setText("");
        tvVideoDirector.setText("");
        tvVideoGenres.setText("");
        tvDebug.setText("");
        avLoadingIndicatorView.smoothToShow();

        //API v1
        UizaService service = RestClientV2.createService(UizaService.class);
        LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntityV1(entityId), new ApiSubscriber<GetDetailEntity>() {
            @Override
            public void onSuccess(GetDetailEntity getDetailEntity) {
                LLog.d(TAG, "getDetailEntityV2 onSuccess " + gson.toJson(getDetailEntity));
                if (getDetailEntity != null) {
                    UizaData.getInstance().setDetailEntityV1(getDetailEntity);
                } else {
                    showDialogError("Error: getDetailEntityV2 onSuccess detailEntity == null");
                }
                updateUI(getDetailEntity);
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });
        //End API v1

        //API v2
        /*UizaService service = RestClientV2.createService(UizaService.class);
        //LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntityV2(entityId), new ApiSubscriber<GetDetailEntity>() {
            @Override
            public void onSuccess(GetDetailEntity getDetailEntity) {
                LLog.d(TAG, "getDetailEntityV2 onSuccess " + gson.toJson(getDetailEntity));
                *//*if (getDetailEntityV2 != null) {
                    UizaData.getInstance().setDetailEntityV2(getDetailEntityV2);
                } else {
                    showDialogError("Error: getDetailEntityV2 onSuccess detailEntity == null");
                }*//*
                updateUI(getDetailEntity);
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "getDetailEntityV2 onFail " + e.toString());
                handleException(e);
            }
        });*/
        //EndAPI v2
    }

    private void setupUIMoreLikeThis(List<Item> itemList) {
        LLog.d(TAG, "setupUIMoreLikeThis " + itemList.size());
        this.itemList.addAll(itemList);
        mAdapter.notifyDataSetChanged();
    }

    private void loadMore() {
        //TODO
        LLog.d(TAG, "loadMore");
    }
}