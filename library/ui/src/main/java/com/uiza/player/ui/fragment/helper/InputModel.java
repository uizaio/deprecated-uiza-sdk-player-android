package com.uiza.player.ui.fragment.helper;

import android.net.Uri;

import com.uiza.player.ui.fragment.model.Playlist;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by www.muathu@gmail.com on 10/24/2017.
 */

public class InputModel implements Serializable {
    private UUID drmSchemeUuid;
    private String drmLicenseUrl;
    private String[] keyRequestPropertiesArray;
    private Boolean preferExtensionDecoders;
    private String action;
    private String uri;
    private String extension;
    private String[] uriStrings;
    private String[] extensionList;
    private String adTagUri;
    private List<Playlist> playlist = null;

    public UUID getDrmSchemeUuid() {
        return drmSchemeUuid;
    }

    public void setDrmSchemeUuid(UUID drmSchemeUuid) {
        this.drmSchemeUuid = drmSchemeUuid;
    }

    public String getDrmLicenseUrl() {
        return drmLicenseUrl;
    }

    public void setDrmLicenseUrl(String drmLicenseUrl) {
        this.drmLicenseUrl = drmLicenseUrl;
    }

    public String[] getKeyRequestPropertiesArray() {
        return keyRequestPropertiesArray;
    }

    public void setKeyRequestPropertiesArray(String[] keyRequestPropertiesArray) {
        this.keyRequestPropertiesArray = keyRequestPropertiesArray;
    }

    public Boolean getPreferExtensionDecoders() {
        return preferExtensionDecoders;
    }

    public void setPreferExtensionDecoders(Boolean preferExtensionDecoders) {
        this.preferExtensionDecoders = preferExtensionDecoders;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Uri getUri() {
        if (uri == null) {
            return null;
        }
        return Uri.parse(uri);
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

    public String[] getUriStrings() {
        return uriStrings;
    }

    public void setUriStrings(String[] uriStrings) {
        this.uriStrings = uriStrings;
    }

    public String[] getExtensionList() {
        return extensionList;
    }

    public void setExtensionList(String[] extensionList) {
        this.extensionList = extensionList;
    }

    public String getAdTagUri() {
        return adTagUri;
    }

    public void setAdTagUri(String adTagUri) {
        this.adTagUri = adTagUri;
    }

    //uiza variable
    private String urlImg;
    private String title;
    private String time;
    private String duration;
    private int rate;
    private String description;
    private String starring;
    private String director;
    private String genres;

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }
}
