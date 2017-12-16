package com.uiza.player.ui.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.uiza.player.core.uiza.api.model.getentityinfo.Item;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.data.UizaRepositoryObserver;
import com.uiza.player.ui.data.UizaSubject;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmUizaVideoInfo extends BaseFragment implements UizaRepositoryObserver {
    private final String TAG = getClass().getSimpleName();
    private UizaSubject mUserDataRepository;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private TextView tvVideoName;
    private TextView tvVideoTime;
    private TextView tvVideoRate;
    private TextView tvVideoDescription;
    private TextView tvVideoStarring;
    private TextView tvVideoDirector;
    private TextView tvVideoGenres;
    private TextView tvDebugJson;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mUserDataRepository.removeObserver(this);
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.uiza_video_info_frm, container, false);

        mUserDataRepository = UizaData.getInstance();
        mUserDataRepository.registerObserver(this);

        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scroll_view);
        LUIUtil.setPullLikeIOSVertical(scrollView);

        avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        avLoadingIndicatorView.smoothToShow();

        tvVideoName = (TextView) view.findViewById(R.id.tv_video_name);
        tvVideoTime = (TextView) view.findViewById(R.id.tv_video_time);
        tvVideoRate = (TextView) view.findViewById(R.id.tv_video_rate);
        tvVideoDescription = (TextView) view.findViewById(R.id.tv_video_description);
        tvVideoStarring = (TextView) view.findViewById(R.id.tv_video_starring);
        tvVideoDirector = (TextView) view.findViewById(R.id.tv_video_director);
        tvVideoGenres = (TextView) view.findViewById(R.id.tv_video_genres);
        tvDebugJson = (TextView) view.findViewById(R.id.tv_debug_json);

        return view;
    }


    @Override
    public void onInputModelChange(InputModel inputModel) {
        if (inputModel == null) {
            LLog.d(TAG, "onInputModelChange -> return");
            return;
        }
        this.mInputModel = inputModel;
        if (mItem == null && !avLoadingIndicatorView.isShown()) {
            //getDetailEntity();
            if (mInputModel.getEntityInfo() != null) {
                mItem = mInputModel.getEntityInfo().getItem();
                updateUI();
            }
            avLoadingIndicatorView.smoothToHide();
        }
    }

    private InputModel mInputModel;
    private Item mItem;

    /*private void getDetailEntity() {
        LLog.d(TAG, "getDetailEntity");
        if (mInputModel == null) {
            LLog.d(TAG, "mInputModel == null -> return");
            return;
        }
        UizaService service = RestClient.createService(UizaService.class);
        int entity;
        try {
            entity = Integer.parseInt(mInputModel.getEntityID());
        } catch (Exception e) {
            handleException("Exception " + e.toString());
            return;
        }
        LLog.d(TAG, "entity " + entity);
        subscribe(service.getDetailEntity(entity), new ApiSubscriber<DetailEntity>() {
            @Override
            public void onSuccess(DetailEntity detailEntity) {
                Gson gson = new Gson();
                LLog.d(TAG, "getDetailEntity onSuccess " + gson.toJson(detailEntity));
                if (detailEntity != null) {
                    mItem = detailEntity.getItem().get(0);
                    updateUI();
                } else {
                    handleException("getDetailEntity Error");
                }
                avLoadingIndicatorView.smoothToHide();
            }

            @Override
            public void onFail(Throwable e) {
                avLoadingIndicatorView.smoothToHide();
                handleException(e);
            }
        });
    }*/

    private void updateUI() {
        String empty = "Empty";
        try {
            tvVideoName.setText(mItem.getName());
        } catch (NullPointerException e) {
            tvVideoName.setText(empty);
        }
        try {
            tvVideoTime.setText(mItem.getCreatedAt());
        } catch (NullPointerException e) {
            tvVideoTime.setText(empty);
        }

        tvVideoRate.setText("18+");
        try {
            tvVideoDescription.setText(mItem.getDescription().isEmpty() ? mItem.getShortDescription().isEmpty() ? empty : mItem.getShortDescription() : mItem.getDescription());
        } catch (NullPointerException e) {
            tvVideoDescription.setText(empty);
        }

        //TODO
        tvVideoStarring.setText(empty);
        tvVideoDirector.setText(empty);
        tvVideoGenres.setText(empty);

        LUIUtil.printBeautyJson(mItem, tvDebugJson);
    }
}