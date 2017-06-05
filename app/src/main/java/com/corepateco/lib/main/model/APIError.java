package com.corepateco.lib.main.model;

/**
 * Created by HieuHD on 8/3/2016.
 */

public class APIError {

    private int code;
    private String message;

    public APIError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
