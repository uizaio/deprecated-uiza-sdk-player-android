package vn.loitp.app.activity.demo.video.videodemo3.lib.model;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

import vn.loitp.app.activity.demo.video.videodemo2.VideoDemo2Activity;

/**
 * Created by www.muathu@gmail.com on 10/28/2017.
 */

public class Sample {
    public final String name;
    public final boolean preferExtensionDecoders;
    public final UUID drmSchemeUuid;
    public final String drmLicenseUrl;
    public final String[] drmKeyRequestProperties;

    public Sample(String name, UUID drmSchemeUuid, String drmLicenseUrl, String[] drmKeyRequestProperties, boolean preferExtensionDecoders) {
        this.name = name;
        this.drmSchemeUuid = drmSchemeUuid;
        this.drmLicenseUrl = drmLicenseUrl;
        this.drmKeyRequestProperties = drmKeyRequestProperties;
        this.preferExtensionDecoders = preferExtensionDecoders;
    }

    public Intent buildIntent(Context context) {
        Intent intent = new Intent(context, VideoDemo2Activity.class);
        //Intent intent = new Intent(context, VideoDemo4Activity.class);
        intent.putExtra(VideoDemo2Activity.PREFER_EXTENSION_DECODERS, preferExtensionDecoders);
        if (drmSchemeUuid != null) {
            intent.putExtra(VideoDemo2Activity.DRM_SCHEME_UUID_EXTRA, drmSchemeUuid.toString());
            intent.putExtra(VideoDemo2Activity.DRM_LICENSE_URL, drmLicenseUrl);
            intent.putExtra(VideoDemo2Activity.DRM_KEY_REQUEST_PROPERTIES, drmKeyRequestProperties);
        }
        return intent;
    }
}
