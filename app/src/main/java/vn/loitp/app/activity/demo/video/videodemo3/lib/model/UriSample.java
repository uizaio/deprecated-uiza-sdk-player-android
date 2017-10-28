package vn.loitp.app.activity.demo.video.videodemo3.lib.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.UUID;

import vn.loitp.app.activity.demo.video.videodemo2.VideoDemo2Activity;

/**
 * Created by www.muathu@gmail.com on 10/28/2017.
 */

public class UriSample extends Sample {

    public final String uri;
    public final String extension;
    public final String adTagUri;

    public UriSample(String name, UUID drmSchemeUuid, String drmLicenseUrl,
                     String[] drmKeyRequestProperties, boolean preferExtensionDecoders, String uri,
                     String extension, String adTagUri) {
        super(name, drmSchemeUuid, drmLicenseUrl, drmKeyRequestProperties, preferExtensionDecoders);
        this.uri = uri;
        this.extension = extension;
        this.adTagUri = adTagUri;
    }

    @Override
    public Intent buildIntent(Context context) {
        return super.buildIntent(context)
                .setData(Uri.parse(uri))
                .putExtra(VideoDemo2Activity.EXTENSION_EXTRA, extension)
                .putExtra(VideoDemo2Activity.AD_TAG_URI_EXTRA, adTagUri)
                .setAction(VideoDemo2Activity.ACTION_VIEW);
    }

}
