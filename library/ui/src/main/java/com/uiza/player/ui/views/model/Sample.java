
package com.uiza.player.ui.views.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Sample implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("drm_scheme")
    @Expose
    private String drmScheme;
    @SerializedName("drm_license_url")
    @Expose
    private String drmLicenseUrl;
    @SerializedName("playlist")
    @Expose
    private List<Playlist> playlist = null;
    @SerializedName("ad_tag_uri")
    @Expose
    private String adTagUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDrmScheme() {
        return drmScheme;
    }

    public void setDrmScheme(String drmScheme) {
        this.drmScheme = drmScheme;
    }

    public String getDrmLicenseUrl() {
        return drmLicenseUrl;
    }

    public void setDrmLicenseUrl(String drmLicenseUrl) {
        this.drmLicenseUrl = drmLicenseUrl;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }

    public String getAdTagUri() {
        return adTagUri;
    }

    public void setAdTagUri(String adTagUri) {
        this.adTagUri = adTagUri;
    }

}
