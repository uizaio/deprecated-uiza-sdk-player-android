package vn.loitp.app.uiza.player;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.exoplayer2.ui.UizaData;
import com.google.android.exoplayer2.ui.fragment.FrmUizaVideo;
import com.google.android.exoplayer2.ui.fragment.helper.InputModel;
import com.google.android.exoplayer2.ui.fragment.model.Sample;

import loitp.utils.util.ToastUtils;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseActivity;
import vn.loitp.app.common.Constants;
import vn.loitp.app.uiza.home.model.VideoObject;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LScreenUtil;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

public class PlayerActivity extends BaseActivity {
    private VideoObject videoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoObject = (VideoObject) getIntent().getSerializableExtra(Constants.KEY_UIZA_PLAYER);
        if (videoObject == null) {
            ToastUtils.showShort("Error videoObject == null");
            return;
        }
        orientVideoDescriptionFragment(getResources().getConfiguration().orientation);

        //TODO replace sample to inputmodel
        Sample sample = new Sample();
        String json = "{\n" +
                "        \"name\": \"Google Glass (MP4,H264)\",\n" +
                "        \"uri\": \"http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0\",\n" +
                "        \"extension\": \"mpd\"\n" +
                "      }";
        sample = vn.loitp.app.app.LSApplication.getInstance().getGson().fromJson(json, Sample.class);

        LLog.d(TAG, "sample " + vn.loitp.app.app.LSApplication.getInstance().getGson().toJson(sample));
        if (sample != null) {
            InputModel inputModel = new InputModel();
            inputModel.setUri(Uri.parse(sample.getUri()));
            inputModel.setDrmLicenseUrl(sample.getDrmLicenseUrl());
            inputModel.setAction(sample.getPlaylist() == null ? FrmUizaVideo.ACTION_VIEW : FrmUizaVideo.ACTION_VIEW_LIST);
            inputModel.setAdTagUri(sample.getAdTagUri());
            inputModel.setPreferExtensionDecoders(false);
            inputModel.setExtension(sample.getExtension());
            //inputModel.setKeyRequestPropertiesArray(sample.ge);

            //inputModel.setUri(Uri.parse("http://www.youtube.com/api/manifest/dash/id/3aa39fa2cc27967f/source/youtube?as=fmp4_audio_clear,webm2_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=B1C2A74783AC1CC4865EB312D7DD2D48230CC9FD.BD153B9882175F1F94BFE5141A5482313EA38E8D&key=ik0"));
            //inputModel.setDrmLicenseUrl("https://proxy.uat.widevine.com/proxy?video_id\\u003dd286538032258a1c\\u0026provider\\u003dwidevine_test");
            //inputModel.setAction("com.google.android.exoplayer.demo.action.VIEW");
            //inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpreonly&cmsid=496&vid=short_onecue&correlator=");
            //inputModel.setPreferExtensionDecoders(false);

            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment frmUizaVideo = fragmentManager.findFragmentById(R.id.uiza_video);
            if (frmUizaVideo != null) {
                if (frmUizaVideo instanceof FrmUizaVideo) {
                    ((FrmUizaVideo) frmUizaVideo).setInputModel(inputModel, true);
                }
            }
        } else {
            ToastUtils.showShort("Error: sample is null");
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
        return R.layout.uiza_player_activity;
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        orientVideoDescriptionFragment(configuration.orientation);
    }

    private void orientVideoDescriptionFragment(int orientation) {
        LLog.d(TAG, "orientVideoDescriptionFragment");
        // Hide the extra content when in landscape so the video is as large as possible.
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment frmInfoVideo = fragmentManager.findFragmentById(R.id.uiza_video_info);
        if (frmInfoVideo != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentTransaction.hide(frmInfoVideo);
                LUIUtil.setDelay(300, new LUIUtil.DelayCallback() {
                    @Override
                    public void doAfter(int mls) {
                        LScreenUtil.hideNavBar(getWindow().getDecorView());
                    }
                });
            } else {
                fragmentTransaction.show(frmInfoVideo);
            }
            fragmentTransaction.commit();
        }
        Fragment frmUizaVideo = fragmentManager.findFragmentById(R.id.uiza_video);
        if (frmUizaVideo != null) {
            if (frmUizaVideo instanceof FrmUizaVideo) {
                LLog.d(TAG, "UizaData.getInstance().getCurrentPosition() " + UizaData.getInstance().getCurrentPosition());
                ((FrmUizaVideo) frmUizaVideo).seekTo(UizaData.getInstance().getCurrentPosition());
            }
        }
    }

    @Override
    public void onBackPressed() {
        UizaData.getInstance().reset();
        super.onBackPressed();
    }
}
