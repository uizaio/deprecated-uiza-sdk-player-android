package com.corepateco.lib.main.api;

import android.util.Log;

import com.corepateco.lib.BuildConfig;
import com.corepateco.lib.main.Constants;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by HieuHD on 3/17/2016.
 */
public class MainGenerator {
    private static OkHttpClient.Builder httpClient;
    private static String API_BASE_URL = Constants.API_USERBEHAVIOR;
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
    private static String TAG = "--MyGenerator--";

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken, final String language, final Map<String, String> options) {
        if (authToken != null) {
            httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS);
            httpClient.connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();


                    HttpUrl.Builder builder = originalHttpUrl.newBuilder();

                    for (Map.Entry<String, String> entry : options.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        builder.addQueryParameter(key, value);
                    }
                    HttpUrl url = builder.build();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
                            .header("Accept-Language", language)
                            .url(url);
                    // Request customization: add request headers
                    Request request = requestBuilder.build();

                    Log.e(TAG, request.toString());
                    return chain.proceed(request);
                }
            });
        }

        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(ServiceUtils.getLogging()).build();
        }
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
