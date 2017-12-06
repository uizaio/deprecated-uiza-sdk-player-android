package vn.loitp.app.uiza.service;

import com.uiza.player.core.uiza.api.model.getlinkplay.GetLinkPlay;
import com.uiza.player.core.uiza.api.model.getposter.GetPoster;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;
import vn.loitp.app.uiza.home.model.GetAll;
import vn.loitp.livestar.corev3.api.model.v3.followidol.FollowIdol;

/**
 * @author loitp
 */

public interface UizaDemoService {
    @GET("v1/app/poster")
    Observable<GetPoster[]> getPoster(@Query("number") int number);

    @GET("api/public/v1/media/entity/get-link-play")
    Observable<GetLinkPlay> getLinkPlay(@Query("entityId") String entityId);

    @FormUrlEncoded
    @PUT("v1/room/follow")
    Observable<FollowIdol> followIdol(@Field("roomId") String roomId);

    @FormUrlEncoded
    @POST("/public/v1/auth/login")
    Observable<Object> login(@Field("username") String username, @Field("password") String password);

    @GET("/api/data/v1/metadata/list")
    Observable<Object> getListAllMetadata(@Query("limit") int limit, @Query("page") int page);

    @GET("/api/data/v1/entity/list")
    Observable<GetAll> getAll(@Query("limit") int limit, @Query("page") int page);
}
