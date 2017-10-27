package vn.loitp.app.activity.demo.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.UUID;

import vn.loitp.app.activity.demo.video.videodemo1.VideoDemo1Activity;
import vn.loitp.app.activity.demo.video.videodemo2.VideoDemo2Activity;
import vn.loitp.app.activity.demo.video.videodemo3.TestVideoDemo3Activity;
import vn.loitp.app.activity.demo.video.videodemo3.VideoDemo3Activity;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

public class VideoMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, VideoDemo1Activity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String js = "{\"uri\":\"http://yt-dash-mse-test.commondatastorage.googleapis.com/media/feelings_vp9-20130806-manifest.mpd\",\"drmLicenseUrl\":\"https://proxy.uat.widevine.com/proxy?video_id\\u003dd286538032258a1c\\u0026provider\\u003dwidevine_test\",\"drmSchemeUuid\":\"edef8ba9-79d6-4ace-a3c8-27dcd51d21ed\",\"name\":\"WV: HDCP not specified\",\"preferExtensionDecoders\":false}";
                UriSample sample = LSApplication.getInstance().getGson().fromJson(js, UriSample.class);
                LLog.d(TAG, ">>>>>>>>" + LSApplication.getInstance().getGson().toJson(sample));
                startActivity(sample.buildIntent(activity));
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TestVideoDemo3Activity.class);
                startActivity(intent);
                LUIUtil.transActivityFadeIn(activity);

                //String js = "{\"uri\":\"http://yt-dash-mse-test.commondatastorage.googleapis.com/media/feelings_vp9-20130806-manifest.mpd\",\"drmLicenseUrl\":\"https://proxy.uat.widevine.com/proxy?video_id\\u003dd286538032258a1c\\u0026provider\\u003dwidevine_test\",\"drmSchemeUuid\":\"edef8ba9-79d6-4ace-a3c8-27dcd51d21ed\",\"name\":\"WV: HDCP not specified\",\"preferExtensionDecoders\":false}";
                //UriSample sample = LSApplication.getInstance().getGson().fromJson(js, UriSample.class);
                //sample.setSwitchToVideoDemo3Activity(true);
                //startActivity(sample.buildIntent(activity));
            }
        });
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_menu_video;
    }


    private abstract static class Sample {

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
            intent.putExtra(VideoDemo2Activity.PREFER_EXTENSION_DECODERS, preferExtensionDecoders);
            if (drmSchemeUuid != null) {
                intent.putExtra(VideoDemo2Activity.DRM_SCHEME_UUID_EXTRA, drmSchemeUuid.toString());
                intent.putExtra(VideoDemo2Activity.DRM_LICENSE_URL, drmLicenseUrl);
                intent.putExtra(VideoDemo2Activity.DRM_KEY_REQUEST_PROPERTIES, drmKeyRequestProperties);
            }
            return intent;
        }

    }

    private static final class UriSample extends Sample {

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

    private static final class PlaylistSample extends Sample {

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
}
