package vn.loitp.restapi.uiza;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import vn.loitp.restapi.uiza.model.v1.getlinkplay.GetLinkPlay;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v1.getentityinfo.EntityInfo;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.restapi.uiza.model.v2.listallentity.ListAllEntity;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.JsonBody;
import vn.loitp.restapi.uiza.model.v2.listallmetadata.ListAllMetadata;

/**
 * @author loitp
 */

public interface UizaService {
    //=====================================================Sample
    /*@GET("v1/app/poster")
    Observable<GetPoster[]> getPoster(@Query("number") int number);

    @GET("api/public/v1/media/entity/get-link-play")
    Observable<GetLinkPlay> getLinkPlay(@Query("entityId") String entityId);

    @FormUrlEncoded
    @PUT("v1/room/follow")
    Observable<Object> followIdol(@Field("roomId") String roomId);

    @FormUrlEncoded
    @POST("/public/v1/auth/login")
    Observable<Object> login(@Field("username") String username, @Field("password") String password);

    @Headers("Content-Type: application/vnd.api+json")
    @GET("/api/data/v1/metadata/list")
    Observable<Object> getListAllMetadata();

    @GET("/api/data/v1/entity/list")
    Observable<GetAll> getAll(@Query("limit") int limit, @Query("page") int page);

    @GET("/api/data/v1/entity/list")
    Observable<Object> testGetAll();*/
    //=====================================================End Sample


    //=====================================================v2 dev-api.uiza.io/resource/index.html
    @FormUrlEncoded
    @POST("/api/public/v1/auth/credentical")
    Observable<Auth> auth(@Field("accessKeyId") String accessKeyId, @Field("secretKeyId") String secretKeyId);

    @GET("/api/resource/v1/auth/check-token")
    Observable<Auth> checkToken();

    @FormUrlEncoded
    @POST("/api/resource/v1/media/metadata/list")
    Observable<ListAllMetadata> listAllMetadata(@Field("limit") int limit);

    @POST("/api/resource/v1/media/entity/list")
    Observable<ListAllEntity> listAllEntity(@Body JsonBody jsonBody);

    @FormUrlEncoded
    @POST("/api/resource/v1/media/entity/detail")
    Observable<GetDetailEntity> getDetailEntity(@Field("id") String id);

    @GET("/api/public/v2/media/entity/get-link-play")
    Observable<Object> getLinkPlayV2(@Query("entityId") String entityId, @Query("appId") String appId);

    //=====================================================end v2 dev-api.uiza.io/resource/index.html


    //=====================================================v1 http://dev-api.uiza.io/data/index.html
    /////for player
    /*@GET("v1/app/poster")
    Observable<GetPoster[]> getPoster(@Query("number") int number);*/

    @GET("/api/public/v1/media/entity/get-link-play")
    Observable<GetLinkPlay> getLinkPlay(@Query("entityId") String entityId, @Query("appId") String appId);

    @GET("api/public/v1/media/entity/info/{id}")
    Observable<EntityInfo> getEntityInfo(@Path("id") String id);

    //getPlayerConfig
    @GET("/api/public/v1/player/info/{id}")
    Observable<PlayerConfig> getPlayerInfo(@Path("id") String playerId);
    //=====================================================end v1 http://dev-api.uiza.io/data/index.html
}
