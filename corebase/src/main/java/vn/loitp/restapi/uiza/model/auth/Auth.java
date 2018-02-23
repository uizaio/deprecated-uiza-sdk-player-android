package vn.loitp.restapi.uiza.model.auth;

/**
 * Created by LENOVO on 2/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Auth {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expired")
    @Expose
    private String expired;
    @SerializedName("appId")
    @Expose
    private String appId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}