package vn.loitp.app.activity.demo.video.videodemo3.lib.helper;

import android.net.Uri;

import java.io.Serializable;
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
    private Uri uri;
    private String extension;
    private String[] uriStrings;
    private String[] extensionList;
    private String adTagUri;

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
        return uri;
    }

    public void setUri(Uri uri) {
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
}
