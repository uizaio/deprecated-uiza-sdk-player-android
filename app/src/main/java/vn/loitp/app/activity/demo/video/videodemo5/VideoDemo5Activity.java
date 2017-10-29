package vn.loitp.app.activity.demo.video.videodemo5;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import vn.loitp.app.activity.demo.video.videodemo3.lib.helper.InputModel;
import vn.loitp.app.activity.demo.video.videodemo4.frm.FrmUizaVideo;
import vn.loitp.app.activity.demo.video.videodemo5.model.Sample;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.common.Constants;
import vn.loitp.app.utilities.LLog;
import vn.loitp.livestar.R;

public class VideoDemo5Activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orientVideoDescriptionFragment(getResources().getConfiguration().orientation);

        Sample sample = (Sample) getIntent().getSerializableExtra(Constants.KEY_UIZA);
        if (sample != null) {
            LLog.d(TAG, "sample " + LSApplication.getInstance().getGson().toJson(sample));
        }

        //TODO
        InputModel inputModel = new InputModel();
        inputModel.setUri(Uri.parse("http://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"));
        inputModel.setDrmLicenseUrl("https://proxy.uat.widevine.com/proxy?video_id\\u003dd286538032258a1c\\u0026provider\\u003dwidevine_test");
        inputModel.setAction("com.google.android.exoplayer.demo.action.VIEW");
        inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpreonly&cmsid=496&vid=short_onecue&correlator=");
        inputModel.setPreferExtensionDecoders(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment frmUizaVideo = fragmentManager.findFragmentById(R.id.uiza_video);
        if (frmUizaVideo != null) {
            if (frmUizaVideo instanceof FrmUizaVideo) {
                ((FrmUizaVideo) frmUizaVideo).setInputModel(inputModel, true);
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        orientVideoDescriptionFragment(configuration.orientation);
    }

    private void orientVideoDescriptionFragment(int orientation) {
        // Hide the extra content when in landscape so the video is as large as possible.
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment FrmInfoVideo = fragmentManager.findFragmentById(R.id.uiza_video_info);

        if (FrmInfoVideo != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentTransaction.hide(FrmInfoVideo);
            } else {
                fragmentTransaction.show(FrmInfoVideo);
            }
            fragmentTransaction.commit();
        }
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
        return R.layout.activity_video_5;
    }
}
