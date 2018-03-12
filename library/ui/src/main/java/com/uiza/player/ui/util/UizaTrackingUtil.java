package com.uiza.player.ui.util;

import android.content.Context;
import android.provider.Settings;

import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;

import vn.loitp.core.utilities.LDateUtils;
import vn.loitp.core.utilities.LPref;
import vn.loitp.restapi.uiza.model.tracking.UizaTracking;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;

/**
 * Created by LENOVO on 3/12/2018.
 */

public class UizaTrackingUtil {
    public static final String EVENT_TYPE_DISPLAY = "display";
    public static final String EVENT_TYPE_PLAYS_REQUESTED = "plays_requested";
    public static final String EVENT_TYPE_VIDEO_STARTS = "video_starts";
    public static final String EVENT_TYPE_VIEW = "view";
    public static final String EVENT_TYPE_REPLAY = "replay";
    public static final String EVENT_TYPE_PLAY_THROUGHT = "play_through";

    public static UizaTracking createTrackingInput(Context context, String eventType) {
        return createTrackingInput(context, "0", eventType);
    }

    public static UizaTracking createTrackingInput(Context context, String playThrough, String eventType) {
        UizaTracking uizaTracking = new UizaTracking();
        //app_id
        Auth auth = LPref.getAuth(context, new Gson());
        if (auth != null) {
            uizaTracking.setAppId(auth.getAppId());
        }
        //page_type
        uizaTracking.setPageType("app");
        //TODO viewer_user_id
        uizaTracking.setViewerUserId("");
        //user_agent
        uizaTracking.setUserAgent(context.getPackageName());
        //referrer
        uizaTracking.setReferrer("");
        //device_id
        uizaTracking.setDeviceId(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        //timestamp
        uizaTracking.setTimestamp(LDateUtils.getCurrent(LDateUtils.FORMAT_1));
        //uizaTracking.setTimestamp("2018-01-11T07:46:06.176Z");
        //player_id
        uizaTracking.setPlayerId(UizaData.getInstance().getPlayerId());
        //TODO player_name
        uizaTracking.setPlayerName("LoitpSDK PlayerName");
        //TODO player_version
        uizaTracking.setPlayerVersion("1.0.0");
        //entity_id
        uizaTracking.setEntityId(UizaData.getInstance().getInputModel().getEntityID());
        //entity_name
        uizaTracking.setEntityName(UizaData.getInstance().getInputModel().getTitle());
        //TODO entity_series
        uizaTracking.setEntitySeries("");
        //TODO entity_producer
        uizaTracking.setEntityProducer("");
        //TODO entity_content_type
        uizaTracking.setEntityContentType("video");
        //TODO entity_language_code
        uizaTracking.setEntityLanguageCode("");
        //TODO entity_variant_name
        uizaTracking.setEntityVariantName("");
        //TODO entity_variant_id
        uizaTracking.setEntityVariantId("");
        //TODO entity_duration
        uizaTracking.setEntityDuration("0");
        //TODO entity_stream_type
        uizaTracking.setEntityStreamType("on-demand");
        //TODO entity_encoding_variant
        uizaTracking.setEntityEncodingVariant("");
        //TODO entity_cdn
        uizaTracking.setEntityCdn("");
        //play_through
        uizaTracking.setPlayThrough(playThrough);
        //event_type
        uizaTracking.setEventType(eventType);
        return uizaTracking;
    }
}
