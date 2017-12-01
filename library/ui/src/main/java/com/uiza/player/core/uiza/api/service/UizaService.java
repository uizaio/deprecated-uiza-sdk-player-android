package com.uiza.player.core.uiza.api.service;

import com.uiza.player.core.uiza.api.model.getlinkplay.GetLinkPlay;
import com.uiza.player.core.uiza.api.model.getposter.GetPoster;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author loitp
 */

public interface UizaService {
    @GET("v1/app/poster")
    Observable<GetPoster[]> getPoster(@Query("number") int number);

    @GET("api/public/v1/media/entity/get-link-play")
    Observable<GetLinkPlay> getLinkPlay(@Query("entityId") String entityId);
}
