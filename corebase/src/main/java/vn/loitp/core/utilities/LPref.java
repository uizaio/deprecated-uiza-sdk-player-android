package vn.loitp.core.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import vn.loitp.restapi.uiza.model.auth.Auth;

/**
 * File created on 11/15/2016.
 *
 * @author loitp
 */
public class LPref {
    private String TAG = getClass().getSimpleName();

    private final static String PREFERENCES_FILE_NAME = "uizav2";
    public static String AUTH = "AUTH";

    public static String JSON_LIST_DATA = "JSON_LIST_DATA";
    public static String JSON_FAV_DATA = "JSON_FAV_DATA";
    public static String JSON_AD_DATA = "JSON_AD_DATA";
    public static String FIRST_RUN_APP = "FIRST_RUN_APP";
    public static final String SAVED_NUMBER_VERSION = "saved.number.version";
    public static final String NOT_READY_USE_APPLICATION = "not.ready.use.application";
    //object
    /*public User getUser() {
        SharedPreferences pref = context.getSharedPreferences(Const.PREFERENCES_FILE_NAME, 0);
        return gson.fromJson(pref.getString(KEY_USER, ""), User.class);
    }

    public void setUser(User user) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Const.PREFERENCES_FILE_NAME, 0).edit();
        editor.putString(KEY_USER, gson.toJson(user));
        editor.apply();
    }*/

    /////////////////////////////////STRING
    /*public static String getJsonListData(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        return pref.getString(JSON_LIST_DATA, null);
    }

    public static void setJsonListData(Context context, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0).edit();
        editor.putString(JSON_LIST_DATA, value);
        editor.apply();
    }*/
    /////////////////////////////////BOOLEAN

    /*public static Boolean isFirstRunApp(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        return prefs.getBoolean(FIRST_RUN_APP, true);
    }

    public static void setFirstRunApp(Context context, Boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0).edit();
        editor.putBoolean(FIRST_RUN_APP, value);
        editor.apply();
    }*/

    /////////////////////////////////INT
    /*public static int getViewBy(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Const.PREFERENCES_FILE_NAME, 0);
        return prefs.getInt(Const.ATTR_INDEX_VIEWBY, 0);
    }

    public static void setViewBy(Context context, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Const.PREFERENCES_FILE_NAME, 0).edit();
        editor.putInt(Const.ATTR_INDEX_VIEWBY, value);
        editor.apply();
    }*/

    /////////////////////////////////OBJECT
    public static Auth getAuth(Context context, Gson gson) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        String json = pref.getString(AUTH, null);
        return gson.fromJson(json, Auth.class);
    }

    public static void setAuth(Context context, Auth auth, Gson gson) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0).edit();
        editor.putString(AUTH, gson.toJson(auth));
        editor.apply();
    }
}