package com.uiza.player.core.uiza.api.service;

import com.uiza.player.core.uiza.api.model.getdetailentity.DetailEntity;
import com.uiza.player.core.uiza.api.model.getentityinfo.EntityInfo;
import com.uiza.player.core.uiza.api.model.getlinkplay.GetLinkPlay;
import com.uiza.player.core.uiza.api.model.getplayerinfo.PlayerConfig;
import com.uiza.player.core.uiza.api.model.getposter.GetPoster;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @GET("api/public/v1/media/entity/info/{id}")
    Observable<EntityInfo> getEntityInfo(@Path("id") String id);

    @FormUrlEncoded
    @POST("/api/resource/v1/media/entity/detail")
    Observable<DetailEntity> getDetailEntity(@Query("id") int id);

    //getPlayerConfig
    @GET("/api/public/v1/player/info/{id}")
    Observable<PlayerConfig> getPlayerInfo(@Path("id") String playerId);
}
