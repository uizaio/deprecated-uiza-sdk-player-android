package com.uiza.player.ui.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.data.UizaRepositoryObserver;
import com.uiza.player.ui.data.UizaSubject;
import com.uiza.player.ui.views.helper.InputModel;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;

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
        LLog.d(TAG, "onInputModelChange");
        if (inputModel == null) {
            return;
        }
    }

    private void getLinkPlay() {
        /*UizaService service = RestClient.createService(UizaService.class);
        subscribe(service.getLinkPlay(inputModel.getEntityID()), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(GetLinkPlay getLinkPlay) {
                //Gson gson = new Gson();
                //LLog.d(TAG, "getLinkPlay onSuccess " + gson.toJson(getLinkPlay));
                UizaData.getInstance().setLinkPlay(getLinkPlay.getLinkplayMpd());
                //UizaData.getInstance().setLinkPlay("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });*/
    }
}