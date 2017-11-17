package loitp.uizalibrary.app.activity.demo.video.videodemo3.lib.model;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

import vn.loitp.app.activity.demo.video.videodemo2.VideoDemo2Activity;

/**
 * Created by www.muathu@gmail.com on 10/28/2017.
 */

public class PlaylistSample extends Sample {

    public final UriSample[] children;

    public PlaylistSample(String name, UUID drmSchemeUuid, String drmLicenseUrl,
                          String[] drmKeyRequestProperties, boolean preferExtensionDecoders,
                          UriSample... children) {
        super(name, drmSchemeUuid, drmLicenseUrl, drmKeyRequestProperties, preferExtensionDecoders);
        this.children = children;
    }

    @Override
    public Intent buildIntent(Context context) {
        String[] uris = new String[children.length];
        String[] extensions = new String[children.length];
        for (int i = 0; i < children.length; i++) {
            uris[i] = children[i].uri;
            extensions[i] = children[i].extension;
        }
        return super.buildIntent(context)
                .putExtra(VideoDemo2Activity.URI_LIST_EXTRA, uris)
                .putExtra(VideoDemo2Activity.EXTENSION_LIST_EXTRA, extensions)
                .setAction(VideoDemo2Activity.ACTION_VIEW_LIST);
    }

}
