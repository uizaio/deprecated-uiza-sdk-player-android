package vn.loitp.app.uiza.home.v2;

/**
 * Created by www.muathu@gmail.com on 12/24/2017.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.data.EventBusData;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.Item;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;
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

    private PlaceHolderView placeHolderView;
    //private NestedScrollView nestedScrollView;

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
        avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(io.uiza.sdk.ui.R.id.avi);
        avLoadingIndicatorView.smoothToShow();

        tvVideoName = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_video_name);
        tvVideoTime = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_video_time);
        tvVideoRate = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_video_rate);
        tvVideoDescription = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_video_description);
        tvVideoStarring = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_video_starring);
        tvVideoDirector = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_video_director);
        tvVideoGenres = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_video_genres);
        tvDebug = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.tv_debug);
        placeHolderView = (PlaceHolderView) view.findViewById(io.uiza.sdk.ui.R.id.place_holder_view);
        return view;
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

        //list more like this
        placeHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        LUIUtil.setPullLikeIOSHorizontal(placeHolderView);
        //LUIUtil.setPullLikeIOSVertical(nestedScrollView);

        getListAllEntityRelation();

        if (Constants.IS_DEBUG) {
            tvDebug.setVisibility(View.VISIBLE);
            LUIUtil.printBeautyJson(getDetailEntity, tvDebug);
        }
    }

    private void getListAllEntityRelation() {
        //TODO http://dev-api.uiza.io/resource/index.html#api-Entity-List_All_Entity_Relation
        //TODO
        LUIUtil.setDelay(1000, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                avLoadingIndicatorView.smoothToHide();
            }
        });
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
        LLog.d(TAG, ">>>getDetailEntity entityId " + entityId);
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
        /*UizaService service = RestClient.createService(UizaService.class);
        String entityId = inputModel.getEntityID();
        LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntity(entityId), new ApiSubscriber<Object>() {
            @Override
            public void onSuccess(Object getDetailEntity) {
                //TODO
                LLog.d(TAG, "getDetailEntity onSuccess " + gson.toJson(getDetailEntity));
                *//*if (getDetailEntity != null) {
                    UizaData.getInstance().setDetailEntity(getDetailEntity);
                } else {
                    showDialogError("Error: getDetailEntity onSuccess detailEntity == null");
                }*//*
                isGetDetailEntityDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });*/
        //End API v1

        //API v2
        UizaService service = RestClient.createService(UizaService.class);
        //LLog.d(TAG, "entityId: " + entityId);
        subscribe(service.getDetailEntityV2(entityId), new ApiSubscriber<GetDetailEntity>() {
            @Override
            public void onSuccess(GetDetailEntity getDetailEntity) {
                LLog.d(TAG, "getDetailEntityV2 onSuccess " + LSApplication.getInstance().getGson().toJson(getDetailEntity));
                /*if (getDetailEntity != null) {
                    UizaData.getInstance().setDetailEntity(getDetailEntity);
                } else {
                    showDialogError("Error: getDetailEntity onSuccess detailEntity == null");
                }*/
                updateUI(getDetailEntity);
            }

            @Override
            public void onFail(Throwable e) {
                LLog.e(TAG, "getDetailEntityV2 onFail " + e.toString());
                handleException(e);
            }
        });
        //EndAPI v2
    }
}