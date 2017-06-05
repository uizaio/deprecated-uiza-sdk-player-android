package com.corepateco.lib.main.api;
import android.util.Log;
import com.corepateco.lib.BuildConfig;
import com.corepateco.lib.main.Constants;

import java.util.HashMap;
import java.util.Map;
import okhttp3.logging.HttpLoggingInterceptor;
public class ServiceUtils {
    public static HttpLoggingInterceptor getLogging() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (BuildConfig.DEBUG)
                    Log.e("--okhttp-", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    /**
     * call normal
     *
     * @param mToken    token user
     * @param mLanguage languge response api
     * @param options   set other data
     * @return
     */
    public static MainService getMainService(String mToken, String mLanguage, Map<String, String> options) {

        if (options == null) {
            options = new HashMap<>();
        }
        options.put("pateco_platform", Constants.PLAFORM);
        options.put("pateco_version", BuildConfig.VERSION_NAME);
        options.put("pateco_version_code", String.valueOf(BuildConfig.VERSION_CODE));
        MainService mCasService = MainGenerator.createService(MainService.class, mToken, mLanguage, options);
        return mCasService;
    }

    /**
     * call Rx
     *
     * @param mToken    token user
     * @param mLanguage languge response api
     * @param options   set other data
     * @return
     */
    public static MainServiceRx getMainServiceRx(String mToken, String mLanguage, Map<String, String> options) {

        if (options == null) {
            options = new HashMap<>();
        }
        options.put("pateco_platform", Constants.PLAFORM);
        options.put("pateco_version", BuildConfig.VERSION_NAME);
        options.put("pateco_version_code", String.valueOf(BuildConfig.VERSION_CODE));
        MainServiceRx mCasService = MainGenerator.createService(MainServiceRx.class, mToken, mLanguage, options);
        return mCasService;
    }
}
