package com.uiza.player.ui.player;

/**
 * Created by www.muathu@gmail.com on 12/24/2017.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.data.EventBusData;
import vn.loitp.restapi.restclient.RestClientTracking;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.tracking.UizaTracking;
import vn.loitp.rxandroid.ApiSubscriber;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmBaseUiza extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    //TODO remove gson
    public Gson gson = new Gson();

    public void trackUiza(final UizaTracking uizaTracking) {
        LLog.d(TAG, ">>>>>>>>>>>>>>>>trackuiza getEventType: " + uizaTracking.getEventType() + ", getPlayThrough: " + uizaTracking.getPlayThrough());
        LLog.d(TAG, ">>>trackuiza object: " + gson.toJson(uizaTracking));
        LLog.d(TAG, ">>>trackuiza endpoint: " + UizaData.getInstance().getApiTrackingEndPoint());
        RestClientTracking.init(UizaData.getInstance().getApiTrackingEndPoint());
        UizaService service = RestClientTracking.createService(UizaService.class);
        subscribe(service.track(uizaTracking), new ApiSubscriber<Object>() {
            @Override
            public void onSuccess(Object tracking) {
                LLog.d(TAG, "<<<<<<<<<<<<<<<trackuiza onSuccess - " + uizaTracking.getEventType() + " -> " + gson.toJson(tracking));
            }

            @Override
            public void onFail(Throwable e) {
                //TODO send event fail? Try to send again
                LLog.e(TAG, "trackuiza onFail " + e.toString());
                handleException(e);
            }
        });
    }
}