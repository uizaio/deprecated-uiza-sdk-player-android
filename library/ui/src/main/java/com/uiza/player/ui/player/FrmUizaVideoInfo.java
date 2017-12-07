package com.uiza.player.ui.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uiza.player.core.uiza.api.model.getdetailentity.DetailEntity;
import com.uiza.player.core.uiza.api.service.UizaService;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.data.UizaRepositoryObserver;
import com.uiza.player.ui.data.UizaSubject;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.rxandroid.ApiSubscriber;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmUizaVideoInfo extends BaseFragment implements UizaRepositoryObserver {
    private final String TAG = getClass().getSimpleName();
    private UizaSubject mUserDataRepository;

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

        return view;
    }


    @Override
    public void onInputModelChange(InputModel inputModel) {
        if (inputModel == null) {
            LLog.d(TAG, "onInputModelChange -> return");
            return;
        }
        this.mInputModel = inputModel;
        if (mDetailEntity == null) {
            getDetailEntity();
        }
    }

    private InputModel mInputModel;
    private DetailEntity mDetailEntity;

    private void getDetailEntity() {
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
                //Gson gson = new Gson();
                //LLog.d(TAG, "getDetailEntity onSuccess " + gson.toJson(detailEntity));
                if (detailEntity != null) {
                    mDetailEntity = detailEntity;

                } else {
                    handleException("getDetailEntity Error");
                }
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });
    }
}