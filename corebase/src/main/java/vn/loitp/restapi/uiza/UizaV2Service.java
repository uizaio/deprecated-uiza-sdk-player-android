package vn.loitp.restapi.uiza;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author loitp
 */

public interface UizaV2Service {
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

    @FormUrlEncoded
    @POST("/api/public/v1/auth/credentical\n")
    Observable<Object> auth(@Field("accessKeyId") String accessKeyId, @Field("secretKeyId") String secretKeyId);
}
