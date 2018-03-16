package com.uiza.player.ui.views.helper;

import android.net.Uri;

import com.uiza.player.ui.views.model.Playlist;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;

/**
 * Created by www.muathu@gmail.com on 10/24/2017.
 */

public class InputModel implements Serializable {
    private String entityID;
    private GetDetailEntity getDetailEntityV2;
    private vn.loitp.restapi.uiza.model.v1.getdetailentity.GetDetailEntity getDetailEntityV1;
    private String adTagUri;

    private UUID drmSchemeUuid;
    private String drmLicenseUrl;
    private String[] keyRequestPropertiesArray;
    private Boolean preferExtensionDecoders;
    private String action;
    private String uri;
    private String extension;
    private String[] uriStrings;
    private String[] extensionList;
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

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public vn.loitp.restapi.uiza.model.v1.getdetailentity.GetDetailEntity getGetDetailEntityV1() {
        return getDetailEntityV1;
    }

    public void setDetailEntityV1(vn.loitp.restapi.uiza.model.v1.getdetailentity.GetDetailEntity getDetailEntityV1) {
        this.getDetailEntityV1 = getDetailEntityV1;
    }

    public GetDetailEntity getDetailEntityV2() {
        return getDetailEntityV2;
    }

    public void setDetailEntityV2(GetDetailEntity getDetailEntity) {
        this.getDetailEntityV2 = getDetailEntity;
    }

    /*public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }*/

    //uiza variable
    private String urlImg;
    private String title;

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
}
