package com.corepateco.lib.main.api;

import com.corepateco.lib.main.model.CategoryResponse;
import com.corepateco.lib.main.model.Channel;
import com.corepateco.lib.main.model.CollectionResponse;
import com.corepateco.lib.main.model.DetailsResponse;
import com.corepateco.lib.main.model.EpgResponse;
import com.corepateco.lib.main.model.HomeResponse;
import com.corepateco.lib.main.model.Login;
import com.corepateco.lib.main.model.MenuResponse;
import com.corepateco.lib.main.model.MovieDetail;
import com.corepateco.lib.main.model.MovieResponse;
import com.corepateco.lib.main.model.OtpTokenResponse;
import com.corepateco.lib.main.model.PackageResponse;
import com.corepateco.lib.main.model.PlayListResponse;
import com.corepateco.lib.main.model.ProfileBean;
import com.corepateco.lib.main.model.RelateResponse;
import com.corepateco.lib.main.model.SuccessResponse;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by HieuHD on 5/16/2016.
 */
public interface MainService {

    public static final String HOME = "/v1/page/{id}";
    String MENU = "/v1/menu/menu/";
    String CAGTEGORY = "";
    String COLLECTION = "/v1/collection/{collectionId}";
    String ENTITY = "/v1/entity/{id}";
    String RELATED = "/v1/entity/{id}/relative";
    String CHIRLD = "/v1/entity/{id}/children";
    String PLAYLIST = "/v1/entity/{id}/linkplay";
    String SEARCH = "/v1/search";
    public String FAVORITE_LIST = "/v1/favorite";
    public static String GET_CHANNEL = "v1/channel";
    public static String GET_MOVIEINFO = "v1/video";
    public static String ADD_FAVORITES = "v1/channel/favorite";
    public static String GET_LSIT_MOVIE = "v1/category";
    public static String GET_LSIT_MOVIE_BY_CATE = "v1/category/video";

    public String LOGIN = "v1/auth/login";
    public String REGISTER = "v1/auth/register";

    public String PACKAGE = "v1/package";
    public String SUBSCRIBE = "v1/package/subscribe";

    String GET_PROFILE = "v1/user";
    String GET_EPG = "channel/program";
    String GET_LIST_ENTITY = "v1/entity";
    String FAVORITES = "/v1/entity/{id}/favorite";
    String GET_OTP = "v1/auth/otp";
    String CHECK_OPT = "v1/auth/otp";

    /**
     * get return data home
     *
     * @param id menu id
     * @return HomeResponse
     */
    @GET(HOME)
    Call<HomeResponse> getHome(@Path("id") String id);

    /**
     * return list menu
     *
     * @param slug fix slug
     * @return MenuResponse
     */
    @GET(MENU)
    Call<MenuResponse> getMenu(@Query("type") String slug);

    /**
     * @param collectionId
     * @param type         if id is slug, type = 'slug'
     * @param offset       Default value: 0
     * @param limit        Default value: 20
     * @return CollectionResponse
     */
    @GET(COLLECTION)
    Call<CollectionResponse> getCollection(@Path("collectionId") String collectionId, @Query("type") String type, @Query("offset") String offset, @Query("limit") String limit);

    /**
     * get details entity
     *
     * @param id entity id
     * @return DetailsResponse
     */
    @GET(ENTITY)
    Call<DetailsResponse> getDetailsEntity(@Path("id") String id);

    /**
     * get list related entity
     *
     * @param id entity id
     * @return RelateResponse
     */
    @GET(RELATED)
    Call<RelateResponse> getRelate(@Path("id") String id);

    /**
     * get list episode entity
     *
     * @param id     entity id
     * @param type   if id is slug, type = 'slug'
     * @param offset default 0
     * @param limit  default 20
     * @return
     */
    @GET(CHIRLD)
    Call<RelateResponse> getChirld(@Path("id") String id, @Query("type") String type, @Query("offset") String offset, @Query("limit") String limit);

    /**
     * get link play entity
     *
     * @param id   entity id
     * @param type if id is slug, type = 'slug'
     * @return PlayListResponse
     */
    @GET(PLAYLIST)
    Call<PlayListResponse> getPlayList(@Path("id") String id, @Query("originType") String type);


    /**
     * search entity by keyword
     *
     * @param keyword keyword search
     * @param offset
     * @param limit
     * @return
     */
    @GET(SEARCH)
    Call<RelateResponse> search(@Query("keyword") String keyword, @Query("offset") String offset, @Query("limit") String limit);

    /**
     * get list entity add favorites
     *
     * @param page  default 0
     * @param limit default 20
     * @return
     */
    @GET(FAVORITE_LIST)
    Call<CollectionResponse> getListFavorites(@Query("offset") int page, @Query("limit") int limit);

    /**
     * schedule a channel
     *
     * @param id       : ids of channels separating by comma. Ex: "123,124"
     * @param fromTime : From (now - back) seconds
     * @param toTime   : To (now + forward) seconds
     * @return
     */
    @GET(GET_EPG)
    Call<EpgResponse> getEpg(@Query("channelIds") String id, @Query("fromTime") String fromTime, @Query("toTime") String toTime);


    /**
     * @param movieid
     * @return
     */
    @GET(GET_MOVIEINFO)
    Call<MovieDetail> getMovieDetails(@Query("videoId") String movieid);

    /**
     * get schedule all channel
     *
     * @return
     */
    @GET(GET_CHANNEL)
    Call<Channel> getChannel();

    /**
     * @return
     */
    @GET(GET_LSIT_MOVIE)
    Call<CategoryResponse> getCategory();

    /**
     * @param movieid
     * @param page
     * @param limit
     * @return
     */
    @GET(GET_LSIT_MOVIE_BY_CATE)
    Call<MovieResponse> getListMovieByCate(@Query("categoryId") String movieid, @Query("offset") int page, @Query("limit") int limit);

    /**
     * @param url
     * @return
     */
    @GET()
    Call<Channel> getChannel(@Url String url);

    /**
     * add favorites
     *
     * @param movieid
     * @return
     */
    @GET(ADD_FAVORITES)
    Call<JsonObject> addFavorites(@Query("id") String movieid);


    /**
     * login by username and password
     *
     * @param username
     * @param password
     * @return
     */
    @POST(LOGIN)
    Call<Login> login(@Query("mobile") String username, @Query("password") String password);


    /**
     * register user
     *
     * @param otp_token OTP
     * @param email
     * @param password
     * @param lastname
     * @return
     */
    @POST(REGISTER)
    Call<Login> register(@Query("otp_token") String otp_token, @Query("email") String email, @Query("password") String password, @Query("name") String lastname);


    /**
     * get list package payment
     *
     * @return
     */
    @GET(PACKAGE)
    Call<PackageResponse> getPackage();

    /**
     * profile user
     *
     * @return
     */
    /*user*/
    @GET(GET_PROFILE)
    Call<ProfileBean> getProfile();

    /**
     * update first name
     *
     * @param firstname
     * @return
     */
    @PUT(GET_PROFILE)
    Call<String> updateFirstname(@Query("firstname") String firstname);

    /**
     * update last name
     *
     * @param lastname
     * @return
     */
    @PUT(GET_PROFILE)
    Call<String> updateLasttname(@Query("lastname") String lastname);

    /**
     * update moblie
     *
     * @param mobile
     * @return
     */
    @PUT(GET_PROFILE)
    Call<String> updateMobile(@Query("mobile") String mobile);

    /**
     * update email
     *
     * @param email
     * @return
     */
    @PUT(GET_PROFILE)
    Call<String> updateEmail(@Query("email") String email);

    /**
     * update password
     *
     * @param password
     * @param currentPassword
     * @return
     */
    @PUT(GET_PROFILE)
    Call<String> updatePassword(@Query("password") String password, @Query("currentPassword") String currentPassword);

    /**
     * @param url
     * @return
     */
    @GET()
    Call<String> detect3G(@Url String url);

    /**
     * @param url
     * @return
     */
    @GET
    Call<String> autoLogin3G(@Url String url);

    /**
     * @param entityType
     * @param page
     * @param limit
     * @return
     */
    @GET(GET_LIST_ENTITY)
    Call<CollectionResponse> getListByType(@Query("entityType") String entityType, @Query("offset") int page, @Query("limit") int limit);


    /**
     * requrst otp
     *
     * @param mobile
     * @param isExist
     * @return
     */
    @GET(GET_OTP)
    Call<SuccessResponse> getOTP(@Query("mobile") String mobile, @Query("isExist") String isExist);

    /**
     * check otp validate
     *
     * @param mobile
     * @param otp
     * @return
     */
    @POST(CHECK_OPT)
    Call<OtpTokenResponse> checkOTP(@Query("mobile") String mobile, @Query("otp") String otp);

    String FORGET_PASSWORD = "v1/auth/password";

    /**
     * forget password
     *
     * @param otp_token
     * @param password
     * @return
     */
    @PUT(FORGET_PASSWORD)
    Call<SuccessResponse> forgetPassword(@Query("otp_token") String otp_token, @Query("password") String password);

    /**
     * remove favorites
     *
     * @param id
     * @param favorite
     * @return
     */
    @POST(FAVORITES)
    Call<SuccessResponse> addRemoveFav(@Path("id") String id, @Query("favorite") String favorite);

    String PATH_CONFIGURE = "/v1/config";

}
