package com.uiza.player.ui.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v2.cannotslide.FrmUizaVideoV2;
import com.uiza.player.ui.views.PlaybackControlView;
import com.uiza.player.ui.views.SimpleExoPlayerView;
import com.uiza.player.ui.views.helper.InputModel;

import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LDialogUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LScreenUtil;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.Setting;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.Styling;
import vn.loitp.views.LToast;

/**
 * Created by www.muathu@gmail.com on 11/8/2017.
 */

public class UizaUIUtil {
    private static final String TAG = LDialogUtil.class.getSimpleName();

    public interface DelayCallback {
        public void doAfter(int mls);
    }

    public static void setDelay(final int mls, final DelayCallback delayCallback) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (delayCallback != null) {
                    delayCallback.doAfter(mls);
                }
            }
        }, mls);
    }

    public static void setColorProgressBar(ProgressBar progressBar) {
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    public static void setUIUizaDialogPlayControlView(Dialog dialog, final View view, final Activity activity) {
        //LLog.d(TAG, "setUIUizaDialogPlayControlView");
        final Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final WindowManager.LayoutParams param = window.getAttributes();
            param.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            view.post(new Runnable() {
                @Override
                public void run() {
                    if (UizaData.getInstance().isVideoCanSlide()) {
                        //v2, video can slide
                        if (UizaData.getInstance().isLandscape()) {
                            //LLog.d(TAG, "isLandscape");
                            //LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());
                            //LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                            param.y = UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                            window.setAttributes(param);
                            //window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        } else {
                            //LLog.d(TAG, "!isLandscape");
                            //LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());

                            int actionbarSizePx = LScreenUtil.getActionbarSizePx(activity);
                            //LLog.d(TAG, "actionbarSizePx: " + actionbarSizePx);
                            //LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                            param.y = actionbarSizePx + UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                            window.setAttributes(param);
                            //window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        }
                    } else {
                        //v1, no silde video
                        //LLog.d(TAG, "isLandscape");
                        //LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());
                        //LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                        param.y = UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                        window.setAttributes(param);
                        //window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    }
                }
            });
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
    }

    public static InputModel createInputModel(String entityId, String entityCover, String entityTitle) {
        InputModel inputModel = new InputModel();
        inputModel.setEntityID(entityId);

        if (entityCover == null || entityCover.isEmpty()) {
            inputModel.setUrlImg(Constants.URL_IMG_9x16);
        } else {
            if (!entityCover.contains(Constants.PREFIXS)) {
                inputModel.setUrlImg(Constants.PREFIXS + entityCover);
            } else if (!entityCover.contains(Constants.PREFIXS_SHORT)) {
                inputModel.setUrlImg(Constants.PREFIXS_SHORT + entityCover);
            } else {
                inputModel.setUrlImg(entityCover);
            }
        }
        inputModel.setTitle(entityTitle + "");

        inputModel.setExtension("mpd");
        //inputModel.setDrmLicenseUrl("");
        inputModel.setAction(inputModel.getPlaylist() == null ? Constants.ACTION_VIEW : Constants.ACTION_VIEW_LIST);
        inputModel.setPreferExtensionDecoders(false);

        //TODO remove this code below
        //inputModel.setUri("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");
        //inputModel.setUri("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");
        //inputModel.setUri("http://cdn-broadcast.yuptv.vn/ba_dash/0c45905848ca4ec99d2ed7c11bc8f8ad-a1556c60605a4fe4a1a22eafb4e89b44/index.mpd");

        //TODO freuss47 ad
        //inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=");
        return inputModel;
    }

    public static void setConfigUIPlayer(SimpleExoPlayerView simpleExoPlayerView, InputModel inputModel, PlayerConfig mPlayerConfig) {
        //TODO freuss47 customize UI
        PlaybackControlView playbackControlView = simpleExoPlayerView.getController();
        if (playbackControlView == null) {
            return;
        }
        playbackControlView.setTitle(inputModel.getTitle());

        if (mPlayerConfig == null) {
            //init default value for player config
            mPlayerConfig = new PlayerConfig();

            Setting setting = new Setting();
            setting.setAllowFullscreen(Constants.F);
            setting.setShowQuality(Constants.T);
            setting.setDisplayPlaylist(Constants.F);
            setting.setAutoStart(Constants.T);
            mPlayerConfig.setSetting(setting);

            Styling styling = new Styling();
            styling.setIcons("#FF0000");
            styling.setBuffer("#00FF00");
            styling.setProgress("#0000FF");

            mPlayerConfig.setStyling(styling);

            LToast.show(simpleExoPlayerView.getContext(), "You play video with default skin", Toast.LENGTH_LONG);
        }

        playbackControlView.setVisibilityFullscreenButton(mPlayerConfig.getSetting().getAllowFullscreen().equals(Constants.T));
        playbackControlView.setVisibilityShowQuality(mPlayerConfig.getSetting().getShowQuality().equals(Constants.T));
        playbackControlView.setVisibilityDisplayPlaylist(mPlayerConfig.getSetting().getDisplayPlaylist().equals(Constants.T));

        //set auto play video or not
        if (mPlayerConfig.getSetting().getAutoStart().equals(Constants.T)) {
            simpleExoPlayerView.getPlayer().setPlayWhenReady(true);
        } else {
            simpleExoPlayerView.getPlayer().setPlayWhenReady(false);
        }

        //set icon color
        try {
            int color = Color.parseColor(mPlayerConfig.getStyling().getIcons());
            playbackControlView.setColorAllIcons(color);
        } catch (Exception e) {
            LLog.e(TAG, "setConfigUIPlayer Color.parseColor setColorAllIcons " + e.toString());
        }

        //set buffer color
        try {
            int color = Color.parseColor(mPlayerConfig.getStyling().getBuffer());
            playbackControlView.setBufferColor(color);
        } catch (Exception e) {
            LLog.e(TAG, "setConfigUIPlayer Color.parseColor setBufferColor " + e.toString());
        }

        //set progress color
        try {
            int color = Color.parseColor(mPlayerConfig.getStyling().getProgress());
            playbackControlView.setProgressColor(color);
        } catch (Exception e) {
            LLog.e(TAG, "setConfigUIPlayer Color.parseColor setProgressColor " + e.toString());
        }

        //TODO set background???
        //mPlayerConfig.getStyling().getBackground()

        //TODO show hide title
        /*try {
            playbackControlView.showOrHideTitleView(false);
            //playbackControlView.showOrHideTitleView(mPlayerConfig.getStyling().getTitle().equals(UizaData.T));
        } catch (NullPointerException e) {
            LLog.e(TAG, "setConfigUIPlayer NullPointerException " + e.toString());
        }*/
    }

    private static int heightSizeOfExpPlayerViewInPortrait = 0;

    public static void setSizeOfContainerVideo(final FrameLayout containerUizaVideo, final BaseFragment frmUizaVideoV2) {
        if (containerUizaVideo != null && frmUizaVideoV2 != null) {
            if (UizaData.getInstance().isLandscape()) {
                LLog.d(TAG, "setSizeOfContainerVideo isLandscape");
                //in landscape oritaion, width screen includes navigation bar height
                int widthScreen = UizaScreenUtil.getScreenHeightIncludeNavigationBar(frmUizaVideoV2.getActivity());
                int heightScreen = UizaScreenUtil.getScreenHeight();
                updateSizeOfContainerVideo(containerUizaVideo, widthScreen, heightScreen);
            } else {
                LLog.d(TAG, "setSizeOfContainerVideo !isLandscape");
                if (frmUizaVideoV2 instanceof FrmUizaVideoV2) {
                    ((FrmUizaVideoV2) frmUizaVideoV2).getPlayerView().post(new Runnable() {
                        @Override
                        public void run() {
                            LLog.d(TAG, "setSizeOfContainerVideo run");
                            //height screen includes statusbar and navigation bar's height
                            if (heightSizeOfExpPlayerViewInPortrait == 0) {
                                heightSizeOfExpPlayerViewInPortrait = ((FrmUizaVideoV2) frmUizaVideoV2).getPlayerView().getVideoSurfaceView().getHeight();
                                LLog.d(TAG, "~~~heightSizeOfExpPlayerViewInPortrait " + heightSizeOfExpPlayerViewInPortrait);
                            }
                            updateSizeOfContainerVideo(containerUizaVideo, UizaScreenUtil.getScreenWidth(), heightSizeOfExpPlayerViewInPortrait);
                        }
                    });
                }
            }
        } else {
            LLog.d(TAG, "setSizeOfContainerVideo else");
        }
    }

    public static void updateSizeOfContainerVideo(FrameLayout containerUizaVideo, int widthScreen, int heightScreen) {
        //LLog.d(TAG, "setSizeOfContainerVideo after run");
        UizaData.getInstance().setSizeHeightOfSimpleExoPlayerView(heightScreen);
        //LLog.d(TAG, "setSizeOfContainerVideo " + widthScreen + "x" + heightScreen);
        containerUizaVideo.getLayoutParams().width = widthScreen;
        containerUizaVideo.getLayoutParams().height = heightScreen;
        containerUizaVideo.requestLayout();
    }

    public static void setDuration(TextView textView, String duration) {
        LLog.d(TAG, "duration: " + duration);
        if (textView == null || duration == null || duration.isEmpty()) {
            return;
        }
        try {
            int min = (int) Double.parseDouble(duration) + 1;
            String minutes = Integer.toString(min % 60);
            minutes = minutes.length() == 1 ? "0" + minutes : minutes;
            textView.setText((min / 60) + ":" + minutes);
        } catch (Exception e) {
            LLog.e(TAG, "setDuration " + e.toString());
            textView.setText(" - ");
        }
    }
}
